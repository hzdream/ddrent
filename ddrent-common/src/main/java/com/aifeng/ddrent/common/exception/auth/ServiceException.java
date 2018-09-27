/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:18:39
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: ServiceException 
 * @Description: 资源服务器异常
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:18:39  
 */
public class ServiceException extends OAuth2Exception {

	private static final long serialVersionUID = -184301398939117856L;
	
	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public ServiceException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public ServiceException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return INTERNAL_SERVER_ERROR;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return INTERNAL_SERVER_ERROR_TYPE;
	}

}
