/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:41
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.enums.user.OriginEnum;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.common.model.dto.WeixinSessionDTO;
import com.aifeng.ddrent.core.dao.mapper.auth.UserTokenMapper;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.dao.model.auth.UserTokenDO;
import com.aifeng.ddrent.core.service.BaseService;
import com.aifeng.ddrent.core.service.weixin.MiniWeixinService;

/** 
 * @ClassName: UserTokenService 
 * @Description: 用户授权token服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:41  
 */
@Service
public class UserTokenService extends BaseService<UserTokenDO, UserTokenMapper> {
	
	@Autowired
	MiniWeixinService miniWeixinService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTokenService userTokenService;
	
	/**
	 * 根据登陆code 获取用户token
	 * @param code
	 * @return
	 */
	public BaseResult<UserTokenDO> getUserTokenByWeixinCode(String code) {
		BaseResult<UserTokenDO> result = new BaseResult<>();
		
		//校验参数准确性
		if(null != code) {
			result.setCode(ErrorCodeEnum.PARAMS_ERROR);
		}
		
		//获取微信session
		WeixinSessionDTO weixinSession = miniWeixinService.code2session(code);
		if(StringUtils.isBlank(weixinSession.getErrcode())) {
			String openId = weixinSession.getOpenid();
			
			UserTokenDO userToken = userTokenService.addTokenByWxSessionKey(openId, weixinSession.getSession_key());
			UserDO user = userService.getByOpenId(openId);
			
			
			if(null == user) {
				user = new UserDO();
				user.setId(SequenceGeneratorUtil.nextId());
				//设置为微信注册
				user.setOriginOpenId(openId);
				user.setOrigin(OriginEnum.WX_REGISTER.name());
				//设置未激活
				user.setIsActive(UserActiveEnum.UNACTIVE.ordinal());
				//用户未注册
				//TODO 先注册用户
			}
			
			//TODO 生成授权token
			
		}else {
			logger.error("[根据code获取用户token] 失败，请求参数[{}], 失败原因 [{}]", code, weixinSession.getErrmsg());
			result.setCode(ErrorCodeEnum.WX_REQUEST_ERROR, weixinSession.getErrmsg());
		}
		
		return result;
	}

	/**
	 * @param openId
	 * @param session_key
	 * @return
	 */
	private UserTokenDO addTokenByWxSessionKey(String openId, String session_key) {
		
		if(StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(session_key)) {
			//查询用户是否存在
			UserDO user = userService.getByOpenId(openId);
			//是否为正常token
			boolean isNormal = true;
			
			//用户不存在，则先帮用户预注册
			if(null == user) {
				user = new UserDO();
				//设置为微信注册
				user.setOriginOpenId(openId);
				user.setOrigin(OriginEnum.WX_REGISTER.name());
				//设置未激活
				user.setIsActive(UserActiveEnum.UNACTIVE.ordinal());
				//用户注册
				user = userService.add(user);
				isNormal = false;
			}
			
			//生成token
			UserTokenDO token = new UserTokenDO();
			token.setAuthType(TokenAuthTypeEnum.WX_LOGIN.name());
//			JwtUtil.createToken(claims);

		}
		
		
		return null;
	}
	
}
