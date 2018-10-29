/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.enums.captcha 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午10:57:06
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.captcha;

/** 
 * @ClassName: CaptchaEnum 
 * @Description: 验证码枚举类型
 * @author: imart·deng
 * @date: 2018年9月25日 下午10:57:06  
 */
public enum CaptchaEnum {

	USER_LOGIN(10, 6, 30, "[%s]您正在登陆，请妥善保管您的验证码"),
	REGISTER(10, 6, 30, "[%s]您正在注册滴滴租房用户，请妥善保管您的验证码"),
	;

	// 有效时间
	private int validMinute;
	
	// 有效次数
	private int checkTimes;
	
//	// 业务类型
//	private int busiType;
	
	// 禁止时间
	private int delayMinute;
	
	// 短信格式
	private String format;

	/**
	 * @param validMinute
	 * @param checkTimes
	 * @param delayMinute
	 * @param format
	 */
	private CaptchaEnum(int validMinute, int checkTimes, int delayMinute, String format) {
		this.validMinute = validMinute;
		this.checkTimes = checkTimes;
//		this.busiType = busiType;
		this.delayMinute = delayMinute;
		this.format = format;
	}

	/**
	 * @return the validMinute
	 */
	public int getValidMinute() {
		return validMinute;
	}

	/**
	 * @param validMinute the validMinute to set
	 */
	public void setValidMinute(int validMinute) {
		this.validMinute = validMinute;
	}

	/**
	 * @return the checkTimes
	 */
	public int getCheckTimes() {
		return checkTimes;
	}

	/**
	 * @param checkTimes the checkTimes to set
	 */
	public void setCheckTimes(int checkTimes) {
		this.checkTimes = checkTimes;
	}

	/**
	 * @return the delayMinute
	 */
	public int getDelayMinute() {
		return delayMinute;
	}

	/**
	 * @param delayMinute the delayMinute to set
	 */
	public void setDelayMinute(int delayMinute) {
		this.delayMinute = delayMinute;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
}
