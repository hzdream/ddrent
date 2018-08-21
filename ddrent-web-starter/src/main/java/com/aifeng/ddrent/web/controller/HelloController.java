/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:40:20
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aifeng.ddrent.core.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.core.service.TestService;
import com.aifeng.ddrent.web.response.BaseResult;
import com.aifeng.ddrent.web.response.DataContainer;
import com.aifeng.ddrent.web.response.config.SystemConfig;

/** 
 * @ClassName: HelloController
 * @Description: 测试controller
 * @author: imart·deng
 * @date: 2018年8月13日 下午2:40:20  
 */
@RestController
@RequestMapping("/hello/")
public class HelloController extends BaseController {
	@Autowired
	SystemConfig sysconfig;
	
	@Autowired
	private TestService testService;

	@RequestMapping
	public BaseResult<Object> getName() {
		return new BaseResult<>();
	}
	
	@RequestMapping(value="conf", method=RequestMethod.GET)
	public BaseResult<Object> getConfig(){
		return new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(sysconfig));
	}
	
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public BaseResult<Object> helloService(){
		return new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(testService.hello()));
	}
}
