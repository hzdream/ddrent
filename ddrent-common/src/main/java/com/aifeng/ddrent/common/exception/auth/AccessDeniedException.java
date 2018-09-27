/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:07:57
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: AccessDeniedException 
 * @Description: 请求无权限异常
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:07:57  
 */
public class AccessDeniedException extends OAuth2Exception {

	private static final long serialVersionUID = -6584575041500366535L;
	
	/**
	 * 
	 */
	public AccessDeniedException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public AccessDeniedException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public AccessDeniedException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return FORBIDDEN;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return FORBIDDEN_TYPE;
	}

}
