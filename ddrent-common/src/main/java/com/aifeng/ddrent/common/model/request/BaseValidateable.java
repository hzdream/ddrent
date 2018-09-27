/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.model.request 
 * @author imart·deng
 * @date 创建时间：2018年9月19日 上午12:49:32
 * @version 1.0
 */
package com.aifeng.ddrent.common.model.request;

import java.util.function.Consumer;

import com.aifeng.ddrent.common.exception.auth.RequestFailedException;

/** 
 * @ClassName: BaseValidateable 
 * @Description: 基础校验
 * @author: imart·deng
 * @date: 2018年9月19日 上午12:49:32  
 */
public class BaseValidateable {
	
	/**
	 * 参数校验
	 * @param validateable
	 * @throws RequestFailedException
	 */
	public void validate(Consumer<BaseValidateable> validateable) throws RequestFailedException {
		validateable.accept(this);
	}
}
