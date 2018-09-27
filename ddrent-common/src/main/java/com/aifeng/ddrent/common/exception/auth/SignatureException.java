/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:20:32
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: SignatureException 
 * @Description: 签名验证异常（用于开放api异常）
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:20:32  
 */
public class SignatureException extends OAuth2Exception {

	private static final long serialVersionUID = 5843294147615778284L;
	
	/**
	 * 
	 */
	public SignatureException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public SignatureException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public SignatureException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return BAD_REQUEST;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return BAD_REQUEST_TYPE;
	}

}
