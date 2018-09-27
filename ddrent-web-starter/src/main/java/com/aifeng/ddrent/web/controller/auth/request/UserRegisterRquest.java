/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.auth.request 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午7:32:28
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** 
 * @ClassName: UserRegisterRquest 
 * @Description: 用户注册请求
 * @author: imart·deng
 * @date: 2018年9月25日 下午7:32:28  
 */
public class UserRegisterRquest {

    /**
     * 昵称
     */
	@NotBlank(message="用户昵称不能为空")
	@Size(max=32, message="用户名称过长，最大长度为{max}")
    private String nickName;

    /**
     * 手机号码
     */
	@NotBlank(message="手机号码不能为空")
	@Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", message="手机号码不正确")
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    private Boolean contactProtect;

    /**
     * 登陆账号
     */
    @NotBlank(message="登陆账号不能为空")
    @Pattern(regexp="^[A-Za-z_][0-9A-Za-z_@#\\.]{3,}$", message="登陆账号规则不符合")
    private String loginAccount;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    private transient String password;

    /**
     * 自我介绍
     */
    private String introduce;

    /**
     * 头像地址
     */
    private String headImgUrl;
    
    /**
     * 验证码id
     */
    @NotBlank(message="验证码不能为空")
    private Long captchaId;
    
    /**
     * 验证码
     */
    @NotBlank(message="验证码不能为空")
    private String captcha;

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

	/**
	 * @return the captchaId
	 */
	public Long getCaptchaId() {
		return captchaId;
	}

	/**
	 * @param captchaId the captchaId to set
	 */
	public void setCaptchaId(Long captchaId) {
		this.captchaId = captchaId;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
