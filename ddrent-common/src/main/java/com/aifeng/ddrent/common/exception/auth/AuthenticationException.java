/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:10:42
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: AuthenticationException 
 * @Description: 授权异常
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:10:42  
 */
public class AuthenticationException extends OAuth2Exception {

	private static final long serialVersionUID = -4774857491846527232L;
	
	/**
	 * 
	 */
	public AuthenticationException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public AuthenticationException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public AuthenticationException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return UNAUTHORIZED;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return UNAUTHORIZED_TYPE;
	}

}
