/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:41
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import java.util.HashMap;
import java.util.Map;

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
import com.aifeng.ddrent.core.dao.model.auth.RoleDO;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.dao.model.auth.UserRoleDO;
import com.aifeng.ddrent.core.dao.model.auth.UserTokenDO;
import com.aifeng.ddrent.core.service.BaseService;
import com.aifeng.ddrent.core.service.weixin.MiniWeixinService;

import tk.mybatis.mapper.entity.Example;

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
	UserTokenService userTokenService;
	
	@Autowired
	UserRoleService userRoleService;
	
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
	 * 根据登陆code 获取用户token
	 * @param code
	 * @return
	 */
	public BaseResult<UserTokenDO> getUserTokenByWeixinCode(String code) {
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
			
			return userTokenService.addTokenByWxSessionKey(openId, weixinSession.getSession_key());
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
	private BaseResult<UserTokenDO> addTokenByWxSessionKey(String openId, String session_key) {
		
		try {
			BaseResult<UserTokenDO> result = new BaseResult<>();
			
			if(StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(session_key)) {
				//查询用户是否存在
				UserDO user = userService.getByOpenId(openId);
				
				// 授权oken
				UserTokenDO token = new UserTokenDO();
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
					user.setIsActive(UserActiveEnum.UNACTIVE.ordinal());
					
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
					BaseResult<UserRoleDO> userRoleResult = userRoleService.findByUserId(user.getId());
					if(userRoleResult.isSuccess() && userRoleResult.getData().getRows().size()>0) {
						StringBuilder sb = new StringBuilder();
						userRoleResult.getData().getRows().stream().forEach(userRole->{
							sb.append(userRole.getRoleId() + ",");
						});
						token.setUserRoles(sb.toString());
					}else {
						logger.info("[微信session添加token] 失败-->查询用户角色失败， 请求参数{},失败原因{}", "{\"openId\":\"" 
								+ openId + "\",\"session_key\":\"" + session_key + "\"}", "参数异常");
						result.setCode(ErrorCodeEnum.PARAMS_ERROR);
						return result;
					}
				}
				
				//设置用户编号
				token.setUserId(user.getId());
				
				//生成token
				Map<String, String> claims = token2UnActiveClaims(token);
				token.setAccessToken(JwtUtil.encodeToken(claims));
				
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

	/**
	 * @param token
	 * @return
	 */
	private Map<String, String> token2UnActiveClaims(UserTokenDO token) {
		Map<String, String> claims = new HashMap<>(12);
		
		claims.put(USER_ID, token.getId()+"");
		claims.put(TOKEN_TYPE, token.getTokenType()+ "");
		
		return claims;
	}
	
}
