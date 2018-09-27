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
public class UserLoginResponse {

	 /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 来源
     */
    private String origin;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    private Boolean contactProtect;

    /**
     * 是否激活0、未激活；1、已激活；2、被停用
     */
    private Integer isActive;

    /**
     * 上次登陆时间
     */
    private Date lastLoginTime;

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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
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
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the contactProtect
	 */
	public Boolean getContactProtect() {
		return contactProtect;
	}

	/**
	 * @param contactProtect the contactProtect to set
	 */
	public void setContactProtect(Boolean contactProtect) {
		this.contactProtect = contactProtect;
	}

	/**
	 * @return the isActive
	 */
	public Integer getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the loginAccount
	 */
	public String getLoginAccount() {
		return loginAccount;
	}

	/**
	 * @param loginAccount the loginAccount to set
	 */
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
    
}
