/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.exception.buiness 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午8:38:00
 * @version 1.0
 */
package com.aifeng.ddrent.common.exception.buiness;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.CoreException;

/** 
 * @ClassName: BusinessException 
 * @Description: 业务异常
 * @author: imart·deng
 * @date: 2018年9月25日 下午8:38:00  
 */
public class BusinessException extends CoreException {

	private static final long serialVersionUID = -8706486351756467763L;

	public BusinessException() {
		super();
	}

	/**
	 * @param errorCodeEnum
	 * @param message
	 */
	public BusinessException(ErrorCodeEnum errorCodeEnum, String message) {
		super(errorCodeEnum, message);
	}

	/**
	 * @param errorCodeEnum
	 */
	public BusinessException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}
	
	

}
