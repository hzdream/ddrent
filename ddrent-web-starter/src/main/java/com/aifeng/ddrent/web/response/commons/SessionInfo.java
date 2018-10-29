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

import com.aifeng.ddrent.common.enums.system.UserRoleEnum;

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

	/** 用户编号 */
	private Long id;

	/** 昵称 */
	private String nickName;
	
	/** 姓名 */
	private String realName;
	
	/** 手机号码 */
	private String phoneNum;
	
	/** 微信openId */
	private String originOpenId;

	/** 用户来源 */
	private String origin;

	/**
	 * 电话号码是否保密
	 */
	private Boolean contactProtect;

	/**
	 * 是否激活0、未激活；1、已激活；2、被停用
	 */
	private Integer isActive;

	/**
	 * 登陆账号
	 */
	private String loginAccount;

	/**
	 * 自我介绍
	 */
	private String introduce;

	/**
	 * 头像地址
	 */
	private String headImgUrl;

	/** 性别 */
	private Integer sex;
	
	/** 登陆时间 */
	private Date loginedTime;

	/** 当前角色列表信息 */
	private String roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNum(String phoneNumber) {
		this.phoneNum = phoneNumber;
	}

	public String getOriginOpenId() {
		return originOpenId;
	}

	public void setOriginOpenId(String originOpenId) {
		this.originOpenId = originOpenId;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Boolean getContactProtect() {
		return contactProtect;
	}

	public void setContactProtect(Boolean contactProtect) {
		this.contactProtect = contactProtect;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public UserRoleEnum getRole(){
		if(null != roles){
			String[] roleIds = roles.split(",");
		}

		return UserRoleEnum.TOURIST;
	}
}
