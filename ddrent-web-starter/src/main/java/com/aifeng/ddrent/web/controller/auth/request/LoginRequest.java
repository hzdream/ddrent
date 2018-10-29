/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.auth.request 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午12:44:28
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** 
 * @ClassName: LoginRequest 
 * @Description: 登陆请求
 * @author: imart·deng
 * @date: 2018年9月19日 上午12:44:28  
 */
public class LoginRequest {
	
	/** 登陆账号  */
	@NotBlank(message="登陆账号不能为空")
	private String username;
	
	/** 登陆密码  */
	@NotBlank(message="登陆密码不能为空")
	private transient String password;
	
	/** 验证码  */
	@NotBlank(message="验证码不能为空")
	@Size(min=4, max=10, message="验证码不正确")
	private String captcha;
	
	/** 验证码编号 */
	@NotNull(message="没有找到匹配的验证码，请重新获取验证码")
	private Long captchaId;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	

}
