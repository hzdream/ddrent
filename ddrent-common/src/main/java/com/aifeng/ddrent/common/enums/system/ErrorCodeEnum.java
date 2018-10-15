/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.enums.system 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午4:39:45
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.system;

/** 
 * @ClassName: ErrorCodeEnum 
 * @Description: 错误编码
 * @author: imart·deng
 * @date: 2018年8月13日 下午4:39:45  
 */
public enum ErrorCodeEnum {
	SUCCESS("000000", "请求成功"),
	SUCCESS_BUT_NO_RESULT("000010", "请求成功，但是没有数据"),
	
	//请求问题
	PARAMS_ERROR("100001", "参数异常"),
	SYSTEM_ERROR("100002", "系统异常"),
	SYSTEM_BUSY("100003", "系统繁忙"),
	UNKNOW_SYSTEM_ERROR("100003", "系统未知异常"),

	// 实体问题
	INSTANCE_NOT_INIT("101001", "实例没有初始化"),

	//权限问题
	AUTH_NORIGHT("100011", "请求无权限"),
	AUTH_LOGIN_TIMEOUT("100012", "登陆超时"),
	
	//系统监控异常问题
	FREQUENT_REQUESTS("100021", "请求过于频繁，请您注意"),
	FREQUENT_REQUESTS_FORBIDDEN("100022", "请求过于频繁，为保护系统稳定性您已经被暂时禁止访问"), 
	
	//用户问题
	USER_NOT_EXIST("110001", "用户不存在"), 
	USER_PASSWORD_MISMATCH("110002", "用户密码不匹配"),
	USER_ACCOUNT_EXIST("110003", "登录名已经存在"), 
	
	//验证码问题
	CAPTCHA_MOBILE_INVALID("120001", "手机号码无效"), 
	CAPTCHA_BUSITYPE_INVALID("120002", "校验码类型无效"), 
	CAPTCHA_NOT_EXIST("120003", "指定校验码不存在"), 
	CAPTCHA_INVALID("120004", "验证码已失效"), 
	CAPTCHA_FOBIDDEN("120005", "验证码发送被禁止"),
	CAPTCHA_MISMATCH("120006", "验证码错误"), 
	
	//微信请求
	WX_REQUEST_ERROR("130001", "微信请求失败"),
	
	;	
	
	/** 错误编号*/
	private String code;
	
	/** 错误信息*/
	private String msg;

	/**
	 * @param code
	 * @param msg
	 */
	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public String code() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String msg() {
		return msg;
	}

	/**
	 * 判断当前异常是否成功
	 * @return
	 */
	public boolean isSuccess() {
		switch(this) {
		case SUCCESS:
		case SUCCESS_BUT_NO_RESULT:
			return true;
		default:
			return false;
		}
	}

}
