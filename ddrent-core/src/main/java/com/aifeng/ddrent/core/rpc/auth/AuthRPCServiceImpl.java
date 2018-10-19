/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.core.rpc.auth
 * @author imart·deng
 * @date 创建时间：2018/10/19 10:12
 * @version 1.0
 */
package com.aifeng.ddrent.core.rpc.auth;

import com.aifeng.ddrent.api.auth.AuthRPCService;
import com.aifeng.ddrent.api.auth.response.UserAuthInfoDTO;
import com.aifeng.ddrent.api.auth.response.UserInfoDTO;
import com.aifeng.ddrent.api.auth.response.UserTokenInfoDTO;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.util.data.JwtUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.dao.model.auth.UserTokenDO;
import com.aifeng.ddrent.core.rpc.BaseRPCService;
import com.aifeng.ddrent.core.service.user.UserService;
import com.aifeng.ddrent.core.service.user.UserTokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: AuthRPCServiceImpl
 * @Description: 授权RPC服务
 * @author: imart·deng
 * @date: 2018/10/19 10:12 
 *
 */
@Service
public class AuthRPCServiceImpl extends BaseRPCService implements AuthRPCService {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserService userService;

    @Override
    public UserAuthInfoDTO authByToken(String accessToken) {

        /** 1. 判断jwt的有效性 */
        // 判断jwt是否在可使用时间
        DecodedJWT decodedJWT = JwtUtil.verifyDefault(accessToken);
        if(null == decodedJWT){
            // 判断jwt 是否在可刷新时间
            decodedJWT = JwtUtil.verifyWithinLeeway(accessToken);
            if(null ==  decodedJWT){
                // token 过期， 使数据库失效
                logger.debug("[accessToken 校验] 失败， 请求参数{}，失败原因[accessToken失效], 数据库失效accesstoken结果[{}]",
                        accessToken, userTokenService.update2invalid(accessToken));
                return null;
            }
        }

        /** 2.获取用户数据库信息 并校验是否有效*/

        //获取数据库token信息token有效性
        UserTokenDO userTokenDO = userTokenService.getTokenByAccessToken(accessToken);
        if(null == userTokenDO || !userTokenDO.getIsActive()) {
            logger.debug("[accessToken 校验] 失败， 请求参数{}，失败原因[accessToken 数据库失效]", accessToken);
            return null;
        }

        //获取用户基础信息
        UserDO userDO = userService.getById(userTokenDO.getUserId());
        if(null == userDO || Objects.equals(UserActiveEnum.FORBIDDEN.ordinal(), userDO.getIsActive())){
            logger.debug("[accessToken 校验] 失败， 请求参数{}，失败原因[用户被停用]", accessToken);
            return null;
        }

        /** 3. 准备返回对象 */

        // 准备 DTO 对象
        UserAuthInfoDTO userAuthInfoDTO = new UserAuthInfoDTO();
        UserTokenInfoDTO userTokenInfoDTO = new UserTokenInfoDTO();
        BeanUtils.copyProperties(userTokenDO, userTokenInfoDTO);
        String uriRegexps = userTokenDO.getUriRegexp();
        if(StringUtils.isNotBlank(uriRegexps)){
            String[] uriRegexpArray = uriRegexps.split(",");
            List<Pattern> patterns = Arrays.asList(uriRegexpArray).stream().filter(StringUtils::isNotBlank).map(Pattern::compile).collect(Collectors.toList());
            userTokenInfoDTO.setUriPattern(patterns);
        }
        userAuthInfoDTO.setUserTokenInfoDTO(userTokenInfoDTO);

        // 准备用户信息DTO对象
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userDO, userInfoDTO);
        userAuthInfoDTO.setUserInfoDTO(userInfoDTO);

        // 返回用户授权信息
        return userAuthInfoDTO;
    }
}
