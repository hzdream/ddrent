/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午1:16:32
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: NotFoundException 
 * @Description: 资源未找到
 * @author: imart·deng
 * @date: 2018年9月19日 上午1:16:32  
 */
public class NotFoundException extends OAuth2Exception {

	private static final long serialVersionUID = 2189372868903171756L;
	
	/**
	 * 
	 */
	public NotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public NotFoundException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public NotFoundException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getHttpCode()
	 */
	@Override
	public int getHttpCode() {
		return NOT_FOUND;
	}

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.common.exception.auth.OAuth2Exception#getErrorType()
	 */
	@Override
	public String getErrorType() {
		return NOT_FOUND_TYPE;
	}

}
