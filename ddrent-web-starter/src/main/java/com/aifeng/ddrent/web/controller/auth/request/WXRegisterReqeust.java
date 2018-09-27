/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.auth.request 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午4:11:14
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.request;

/** 
 * @ClassName: WXRegisterReqeust 
 * @Description: 微信认证
 * @author: imart·deng
 * @date: 2018年9月25日 下午4:11:14  
 */
public class WXRegisterReqeust {

    /**
     * 来源openapi
     */
    private String originOpenId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    private Boolean contactProtect;

    /**
     * 登陆账号
     */
    private String loginAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 自我介绍
     */
    private String introduce;

    /**
     * 头像地址
     */
    private String headImgUrl;

	/**
	 * @return the originOpenId
	 */
	public String getOriginOpenId() {
		return originOpenId;
	}

	/**
	 * @param originOpenId the originOpenId to set
	 */
	public void setOriginOpenId(String originOpenId) {
		this.originOpenId = originOpenId;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
