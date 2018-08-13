/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.response.commons 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午5:20:49
 * @version 1.0
 */
package com.aifeng.ddrent.web.response.commons;

import java.io.Serializable;
import java.util.Date;

import com.aifeng.ddrent.core.common.enums.system.UserRoleEnum;

/** 
 * @ClassName: SessionInfo 
 * @Description: 用户信息
 * @author: imart·deng
 * @date: 2018年8月13日 下午5:20:49  
 */
public class SessionInfo implements Serializable {
	private static final long serialVersionUID = -4417635013853490125L;
	
	/** 缓存名*/
	public final static String SESSION_NAME = "_session_name";

	/** 昵称 */
	private String nickName;
	
	/** 姓名 */
	private String realName;
	
	/** 手机号码 */
	private String phoneNumber;
	
	/** 微信openId */
	private String openId;
	
	/** 登陆时间 */
	private Date loginedTime;
	
	/** 当前角色信息 */
	private UserRoleEnum role;

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the loginedTime
	 */
	public Date getLoginedTime() {
		return loginedTime;
	}

	/**
	 * @param loginedTime the loginedTime to set
	 */
	public void setLoginedTime(Date loginedTime) {
		this.loginedTime = loginedTime;
	}

	/**
	 * @return the role
	 */
	public UserRoleEnum getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRoleEnum role) {
		this.role = role;
	}
	
}
