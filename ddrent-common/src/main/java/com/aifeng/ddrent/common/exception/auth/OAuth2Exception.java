/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午12:52:07
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.auth;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.CoreException;

/** 
 * @ClassName: OAuth2Exception 
 * @Description: 授权校验异常
 * @author: imart·deng
 * @date: 2018年9月19日 上午12:52:07  
 */
public abstract class OAuth2Exception extends CoreException {

	private static final long serialVersionUID = 4781267983674510964L;
	
	protected static final int BAD_REQUEST = 400;
    protected static final int UNAUTHORIZED = 401;
    protected static final int REQUEST_FAILED = 402;
    protected static final int FORBIDDEN = 403;
    protected static final int NOT_FOUND = 404;
    protected static final int INTERNAL_SERVER_ERROR = 500;
    
    /** 请求不支持400 */
    protected static final String BAD_REQUEST_TYPE = "REQUEST_NOT_SUPPORT_FAILD";
    /** 未授权401 */
    protected static final String UNAUTHORIZED_TYPE = "AUTHENTICATION_FAILD";
    /** 请求失败  402*/
    protected static final String RQUEST_FAILED_TYPE = "REQUEST_FAILD";
    /** 请求无权限 403 */
    protected static final String FORBIDDEN_TYPE = "ACCESS_DENIED";
    /** 资源不存在 404 */
    protected static final String NOT_FOUND_TYPE = "NOT_FOUND";
    /** 服务异常 500 */
    protected static final String INTERNAL_SERVER_ERROR_TYPE = "INTERNAL_SERVER_ERROR";

    public OAuth2Exception() {
        super();
    }
    
    /**
	 * @param errorCodeEnum
	 * @param message
	 */
	public OAuth2Exception(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public OAuth2Exception(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	public abstract int getHttpCode();

    public abstract String getErrorType();
    
}
