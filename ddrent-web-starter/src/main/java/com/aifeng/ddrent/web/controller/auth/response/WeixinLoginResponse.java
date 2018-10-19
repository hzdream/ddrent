/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.auth.response 
 * @author imart·deng
 * @date 创建时间：2018年9月21日 下午5:04:44
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.response;

import java.util.Date;

/** 
 * @ClassName: UserLoginResponse 
 * @Description: 用户登陆返回
 * @author: imart·deng
 * @date: 2018年9月21日 下午5:04:44  
 */
public class WeixinLoginResponse {

	/**
	 * 用户号
	 */
	private Long userId;

	/**
	 * 用户token
	 */
	private String accessToken;

	/**
	 * 用户登陆ip
	 */
	private String loginIp;

	/**
	 * 用户角色
	 */
	private String userRoles;

	/**
	 * 授权类型，WEIXIN,WEB_LOGIN
	 */
	private String authType;

	/**
	 * 是否激活
	 */
	private Boolean isActive;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 外部id
	 */
	private String externalId;

	/**
	 * token token 类型,0.待完善用户，1.正常用户
	 */
	private Integer tokenType;

	/**
	 * 获取用户号
	 *
	 * @return USER_ID - 用户号
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户号
	 *
	 * @param userId 用户号
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户token
	 *
	 * @return ACCESS_TOKEN - 用户token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * 设置用户token
	 *
	 * @param accessToken 用户token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * 获取用户登陆ip
	 *
	 * @return LOGIN_IP - 用户登陆ip
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置用户登陆ip
	 *
	 * @param loginIp 用户登陆ip
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 获取用户角色
	 *
	 * @return USER_ROLES - 用户角色
	 */
	public String getUserRoles() {
		return userRoles;
	}

	/**
	 * 设置用户角色
	 *
	 * @param userRoles 用户角色
	 */
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * 获取授权类型，WEIXIN,WEB_LOGIN
	 *
	 * @return AUTH_TYPE - 授权类型，WEIXIN,WEB_LOGIN
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * 设置授权类型，WEIXIN,WEB_LOGIN
	 *
	 * @param authType 授权类型，WEIXIN,WEB_LOGIN
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * 获取是否激活
	 *
	 * @return IS_ACTIVE - 是否激活
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * 设置是否激活
	 *
	 * @param isActive 是否激活
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * 获取创建时间
	 *
	 * @return CREATE_TIME - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @return the tokenType
	 */
	public Integer getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(Integer tokenType) {
		this.tokenType = tokenType;
	}
}
