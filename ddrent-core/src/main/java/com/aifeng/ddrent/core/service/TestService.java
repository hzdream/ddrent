/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service 
 * @author imart·deng
 * @date 创建时间：2018年8月21日 下午11:45:10
 * @version 1.0
 */
package com.aifeng.ddrent.core.service;

import org.springframework.stereotype.Service;

/** 
 * @ClassName: TestService 
 * @Description: 测试service
 * @author: imart·deng
 * @date: 2018年8月21日 下午11:45:10  
 */
@Service
public class TestService {

	public String hello() {
		return "hello service";
	}
}
