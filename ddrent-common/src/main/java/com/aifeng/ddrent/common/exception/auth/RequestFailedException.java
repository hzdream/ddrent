/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:03:36
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: RequestFaildException 
 * @Description: 请求失败异常，例如：参数校验失败
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:03:36  
 */
public class RequestFailedException extends OAuth2Exception {

	private static final long serialVersionUID = 986810230022513119L;
	
	/**
	 * 
	 */
	public RequestFailedException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public RequestFailedException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public RequestFailedException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return REQUEST_FAILED;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return RQUEST_FAILED_TYPE;
	}

}
