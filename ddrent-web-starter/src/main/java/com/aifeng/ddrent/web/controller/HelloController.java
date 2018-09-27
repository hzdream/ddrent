/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:40:20
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.json.GsonUtil;
import com.aifeng.ddrent.core.common.model.PageBean;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.service.TestService;
import com.aifeng.ddrent.core.service.conf.SystemConfigService;
import com.aifeng.ddrent.core.service.user.UserService;
import com.aifeng.ddrent.web.controller.auth.request.UserRequest;
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
	UserService userService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	SystemConfigService systemConfigService;

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
	
	@RequestMapping(value="user/add")
	public BaseResult<UserDO> addUser(UserRequest request, BindingResult bind) {
		
		BaseResult<UserDO> result = new BaseResult<UserDO>();
		//参数校验
		validte(bind);
		
		UserDO record = new UserDO();
		
		BeanUtils.copyProperties(request, record);
		
		record = userService.addSelective(record);
		
		result.setData(new DataContainer<UserDO>(record));
		
		return result;
	}
	
	@RequestMapping(value="mutparams")
	public BaseResult<UserDO> testParams(@RequestBody PageBean page) {
		
		System.out.println(GsonUtil.gson().toJson(request));
		System.out.println(GsonUtil.gson().toJson(page));
		
		return null;
	}
	
	@RequestMapping("conf2")
	public BaseResult<Object> getConf(Long id){
		BaseResult<Object> result = new BaseResult<>();
		result.setData(new DataContainer<Object>(systemConfigService.getById(id)));
		return result;
	}
	
	@RequestMapping("getUser")
	public BaseResult<UserDO> getUser(Long id){
		id = null == id ?  6450298812944100606L : id;
		UserDO user = new UserDO();
//		user.setId(6450298812944100606L);
//		BaseResult<UserDO> result = new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(userService.getById(id)));
		return userService.findByParams(user, new PageBean(1, 100));
	}
}
