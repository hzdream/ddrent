/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:41
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;


import com.aifeng.ddrent.common.enums.auth.ResTypeEnum;
import com.aifeng.ddrent.common.model.auth.JwtToken;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.core.dao.model.auth.*;
import com.aifeng.ddrent.core.service.auth.RoleResourcesService;
import com.aifeng.ddrent.core.service.auth.RoleService;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum;
import com.aifeng.ddrent.common.enums.auth.TokenTypeEnum;
import com.aifeng.ddrent.common.enums.auth.UserRoleLevelEnum;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.enums.user.OriginEnum;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.data.JwtUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.common.model.dto.WeixinSessionDTO;
import com.aifeng.ddrent.core.dao.mapper.auth.UserTokenMapper;
import com.aifeng.ddrent.core.service.BaseService;
import com.aifeng.ddrent.core.service.weixin.MiniWeixinService;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** 
 * @ClassName: UserTokenService 
 * @Description: 用户授权token服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:41  
 */
@Service
public class UserTokenService extends BaseService<UserTokenDO, UserTokenMapper> {
	
	//用户编号
	private final static String USER_ID = "userId";
	
	//token 类型
	private final static String TOKEN_TYPE = "tokenType";
	
	@Autowired
	MiniWeixinService miniWeixinService;
	
	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	RoleResourcesService roleResourcesService;
	
	/**
	 * 根据用户编号和授权类型把符合条件的token置为失效状态
	 * @param tokenType		可选，如果tokenType为空则更新用户所有token IS_ACTIVE =FALSE
	 * @param userId		必填
	 */
	public int update2invalid(String tokenType, Long userId) {

		if(null != userId) {
			Example example = new Example(UserTokenDO.class);
			Example.Criteria criteria = example.createCriteria().andEqualTo("isActive", Boolean.TRUE).andEqualTo("userId", userId);
			
			if(StringUtils.isNotBlank(tokenType)) 
				criteria.andEqualTo("authType", tokenType);
			
			UserTokenDO updateRecord = new UserTokenDO();
			updateRecord.setIsActive(Boolean.FALSE);
			return mapper.updateByExampleSelective(updateRecord, example);
		}
		
		return -1;
	}

	/**
	 * 根据token使 userToken 失效
	 * @param accessToken
	 * @return
	 */
	public int update2invalid(String accessToken) {

		if(StringUtils.isBlank(accessToken)) return 0;

		Example example = new Example(UserTokenDO.class);
		example.createCriteria().andEqualTo("accessToken", accessToken);

		UserTokenDO updateRecord = new UserTokenDO();
		updateRecord.setIsActive(Boolean.FALSE);
		return mapper.updateByExampleSelective(updateRecord, example);
	}
	
	/* 
	 * token 入库之前，得先把同类型已激活的token置为失效
	 * @see com.aifeng.ddrent.core.service.BaseService#add(com.aifeng.ddrent.core.dao.model.BaseDOI)
	 */
	@Override
	@Transactional
	public UserTokenDO add(UserTokenDO record) {
		
		if(null != record) {
			
			String tokenType = record.getAuthType();
			Long userId = record.getUserId();
			
			//更新所有同类型token置为失效状态
			int num = update2invalid(tokenType, userId);
			
			if(num < 0) {
				//更新token失败则返回空对象
				return null;
			}
			
			return super.add(record);
		}
		
		return record;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.core.service.BaseService#addSelective(com.aifeng.ddrent.core.dao.model.BaseDOI)
	 */
	@Override
	@Transactional
	public UserTokenDO addSelective(UserTokenDO record) {
		if(null != record) {
			
			String tokenType = record.getAuthType();
			Long userId = record.getUserId();
			
			//更新所有同类型token置为失效状态
			int num = update2invalid(tokenType, userId);
			
			if(num<0) {
				//更新token失败则返回空对象
				return null;
			}
			
			return super.addSelective(record);
		}
		return record;
	}

	/**
	 * 根据accessToken获取token信息
	 * @param accessToken
	 * @return
	 */
	public UserTokenDO getTokenByAccessToken(String accessToken){
		Example example = new Example(UserTokenDO.class);

		example.createCriteria().andEqualTo("accessToken", accessToken);

		List<UserTokenDO> userTokenDOS = mapper.selectByExample(example);

		if(null != userTokenDOS || userTokenDOS.isEmpty()) return null;

		return userTokenDOS.get(0);
	}

	/**
	 * 根据登陆code 获取用户token
	 * @param code
	 * @return
	 */
	public BaseResult<UserTokenDO> addUserTokenByWeixinCode(String code) {
		BaseResult<UserTokenDO> result = new BaseResult<>();
		
		//校验参数准确性
		if(null != code) {
			result.setCode(ErrorCodeEnum.PARAMS_ERROR);
			return result;
		}
		
		//获取微信session
		WeixinSessionDTO weixinSession = miniWeixinService.code2session(code);
		if(StringUtils.isBlank(weixinSession.getErrcode())) {
			String openId = weixinSession.getOpenid();
			
			return addTokenByWxSessionKey(openId, weixinSession.getSession_key());
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
	@Transactional
	public BaseResult<UserTokenDO> addTokenByWxSessionKey(String openId, String session_key) {
		
		try {
			BaseResult<UserTokenDO> result = new BaseResult<>();
			
			if(StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(session_key)) {
				//查询用户是否存在
				UserDO user = userService.getByOpenId(openId);
				
				// 授权oken
				UserTokenDO token = new UserTokenDO();
				token.setId(SequenceGeneratorUtil.nextId());
				token.setAuthType(TokenAuthTypeEnum.WX_LOGIN.name());
				token.setExternalId(session_key);
				//设置token为激活类型
				token.setIsActive(Boolean.TRUE);


				//用户不存在，则先帮用户预注册
				if(null == user) {
					user = new UserDO();
					//设置为微信注册
					user.setOriginOpenId(openId);
					user.setOrigin(OriginEnum.WX_REGISTER.name());
					//设置未激活
					user.setIsActive(UserActiveEnum.UNACTIVATED.ordinal());
					
					// 注册用户
					BaseResult<UserDO> userResult = userService.register(user, UserRoleLevelEnum.TOURIST);
					
					if(userResult.isSuccess()) {
						//完善token信息
						token.setUserRoles(((RoleDO)userResult.getData().getOther()).getId()+"");
						//待完善用户信息token类型
						token.setTokenType(TokenTypeEnum.TO_BE_PERFECTED.ordinal());
					}else {
						logger.info("[微信session添加token] 失败-->注册用户失败， 请求参数{},失败原因{}", "{\"openId\":\"" 
								+ openId + "\",\"session_key\":\"" + session_key + "\"}", "参数异常");
						result.setCode(ErrorCodeEnum.PARAMS_ERROR);
						return result;
					}
					
				}else {
					//正常用户信息token类型
					token.setTokenType(TokenTypeEnum.NORMAL.ordinal());
					if (getUserRoleAndUriResources(user.getId(), token)) return result.setCode(ErrorCodeEnum.PARAMS_ERROR);
				}
				
				//设置用户编号
				token.setUserId(user.getId());
				
				//生成token
				token.setAccessToken(JwtUtil.encodeToken(new JwtToken(token.getUserId(), TokenTypeEnum.getByValue(token.getTokenType()), token.getAuthType())));
				
				//token 入库
				token = add(token);
				
				result.setCode(ErrorCodeEnum.SUCCESS, new DataContainer<>(token));
			}
			
			return result;
		} catch (Exception e) {
			logger.info("[微信session添加token] 失败， 请求参数{}, 失败原因{}", "{\"openId\":\"" 
					+ openId + "\",\"session_key\":\"" + session_key + "\"}", e.getMessage());
			throw e;
		}
		
	}

	@Transactional
	public BaseResult<UserTokenDO> addTokenByJwtToken(JwtToken jwtToken){

		BaseResult<UserTokenDO> result = new BaseResult<>();

		// 校验参数有效性
		if(!jwtToken.isValidate()) return result.setCode(ErrorCodeEnum.PARAMS_ERROR);

		//查询用户是否存在
		UserDO user = userService.getById(jwtToken.getUserId());
		if(null == user) return result.setCode(ErrorCodeEnum.USER_NOT_EXIST);

		UserTokenDO userTokenDO = new UserTokenDO();

		userTokenDO.setUserId(user.getId());
		userTokenDO.setTokenType(TokenTypeEnum.NORMAL.ordinal());
		userTokenDO.setAuthType(TokenAuthTypeEnum.WEB_LOGIN.name());
		userTokenDO.setIsActive(Boolean.TRUE);
		userTokenDO.setLoginIp(jwtToken.getLoginIp());
		userTokenDO.setId(SequenceGeneratorUtil.nextId());

		// 设置用户角色列表
		if (getUserRoleAndUriResources(user.getId(), userTokenDO)) return result.setCode(ErrorCodeEnum.PARAMS_ERROR);

		//生成token
		userTokenDO.setAccessToken(JwtUtil.encodeToken(jwtToken));

		//token 入库
		userTokenDO = add(userTokenDO);

		return result.setData(new DataContainer<>(userTokenDO)).setCode(ErrorCodeEnum.SUCCESS);
	}

	/**
	 *  刷新授权token
	 * @param accessToken
	 * @return
	 */
	public BaseResult<UserTokenDO> refreshJwtToken(String accessToken){

		BaseResult<UserTokenDO> result = new BaseResult<>();

		DecodedJWT decodedJWT = JwtUtil.verifyWithinLeeway(accessToken);

		if(null == decodedJWT) return result.setCode(ErrorCodeEnum.AUTH_LOGIN_TIMEOUT);

		UserTokenDO tokenDO = getTokenByAccessToken(accessToken);

		/** 生成新的token */
		UserTokenDO newTokenDO = new UserTokenDO();
		newTokenDO.copy(tokenDO);

		JwtToken jwtToken = new JwtToken(newTokenDO.getUserId(), TokenTypeEnum.getByValue(newTokenDO.getTokenType()), newTokenDO.getAuthType());
		newTokenDO.setAccessToken(JwtUtil.encodeToken(jwtToken));

		//token 入库
		newTokenDO = add(newTokenDO);

		return result.setData(new DataContainer<>(newTokenDO)).setCode(ErrorCodeEnum.SUCCESS);
	}

	private static final String  START_PREFIX = "^";
	private static final String END_AFTERFIX = "$";
	/**
	 * 查询并设置用户角色列表
	 * @param userId
	 * @param token
	 * @return
	 */
	private boolean getUserRoleAndUriResources(Long userId, UserTokenDO token) {
		int tokenType = TokenAuthTypeEnum.getByName(token.getAuthType()).ordinal();

		// 查询用户角色列表
		BaseResult<UserRoleDO> userRoleResult = userRoleService.findByUserId(userId);
		if(userRoleResult.isSuccess() && userRoleResult.getData().getRows().size()>0) {
			StringBuffer roleSb = new StringBuffer();
			StringBuffer uriRegexpSb = new StringBuffer();
			userRoleResult.getData().getRows().stream().map(userRoleDO -> {
				roleSb.append(userRoleDO.getId()+ ",");
				return userRoleDO.getRoleId();
			}).forEach(roleId -> {
				// 获取每个角色的资源regexp 列表
				List<RoleResourcesViewDO> roleResourcesViewDOS = roleResourcesService.findRoleResourcesByRoleId(roleId);
				roleResourcesViewDOS.stream().filter(roleResourcesViewDO ->
						//筛选操作资源和资源类型相符或通用的资源
					roleResourcesViewDO.getType() == 2 &&
							(Objects.equals(tokenType, roleResourcesViewDO.getResType())
									|| Objects.equals(TokenAuthTypeEnum.COMMON.ordinal(), roleResourcesViewDO.getResType()))
				).forEach( roleResourcesView -> {
					// 提取uri pattern
					String uri = roleResourcesView.getResUri();
					int resType = roleResourcesView.getResType();

					if(Objects.equals(ResTypeEnum.NORMAL.ordinal(), resType)){
						uri = START_PREFIX + uri + END_AFTERFIX;
					}
					uriRegexpSb.append(uri + ",");
				});

			});

			// 去尾部逗号存入token
			if(roleSb.length() > 0){
				token.setUserRoles(roleSb.substring(0, roleSb.length() - 1));
			}else{
				token.setUserRoles("");
			}
			if(uriRegexpSb.length() > 0){
				token.setUriRegexp(uriRegexpSb.substring(0, uriRegexpSb.length() - 1));
			}else{
				token.setUriRegexp("");
			}

			return true;
		}else {
			logger.info("[查询用户角色] 失败 ， 请求参数{},失败原因{}", "{\"userId\":\""  + userId + "\"}", "参数异常");
			return false;
		}
	}

}
