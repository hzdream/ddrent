/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.exceptions 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午1:27:23
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: CoreException 
 * @Description: 核心服务异常
 * @author: imart·deng
 * @date: 2018年9月18日 上午1:27:23  
 */
public class CoreException extends RuntimeException {

	private static final long serialVersionUID = -8433150221281408753L;
	
	protected ErrorCodeEnum code;
	
	protected String message;
	
	public CoreException() {
		super();
	}
	
	public CoreException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.msg());
		this.code = errorCodeEnum;
	}
	
	public CoreException(ErrorCodeEnum errorCodeEnum, String message) {
		super(message);
		this.code = errorCodeEnum;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public ErrorCodeEnum getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(ErrorCodeEnum code) {
		this.code = code;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		
		if(null != message)
			return message;
		
		return code.msg();
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CoreException [code=" + code.code() + ",message=" + code.msg() + "]";
	}

}
