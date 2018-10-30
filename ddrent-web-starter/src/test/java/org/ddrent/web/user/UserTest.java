/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: org.ddrent.web.user 
 * @author imart·deng
 * @date 创建时间：2018年9月28日 下午5:24:19
 * @version 1.0
 */
package org.ddrent.web.user;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.auth.RequestFailedException;
import com.aifeng.ddrent.web.controller.auth.UserController;
import com.aifeng.ddrent.web.controller.auth.request.UserRegisterRquest;
import com.aifeng.ddrent.web.controller.auth.response.UserLoginResponse;
import com.aifeng.ddrent.web.controller.auth.response.WeixinLoginResponse;
import org.ddrent.web.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.json.GsonUtil;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.service.user.UserService;
import com.aifeng.ddrent.web.DdrentApplication;
import com.aifeng.ddrent.web.WebConfig;

import javax.validation.Validator;

/** 
 * @ClassName: UserTest 
 * @Description: TODO
 * @author: imart·deng
 * @date: 2018年9月28日 下午5:24:19  
 */
@SpringBootTest(classes=DdrentApplication.class)
public class UserTest extends BaseTest {


	@Autowired
	private UserService userService;

	@Autowired
	private UserController userController;

	//校验参数
	@Autowired
	Validator validator;

	@Test
	public void testFind() {
		
		BaseResult<UserDO> userResult = userService.findByParams(new UserDO(), null);
		
		System.out.format("[用户查询] 返回结果 %s", GsonUtil.gson().toJson(userResult));
	}

	public void createSmsCaptcha(){

	}

	/**
	 * 通过微信登陆
	 */
	@Test
	public void loginByWxCode(){
		String code = "0338FfIS0QSJvY1OSHJS0fLrIS08FfIH";

		BaseResult<WeixinLoginResponse> result = userController.wxLogin(code, "hello");

		System.out.println(GsonUtil.gson().toJson(result));
	}

	@Test
	public void register(){
		UserRegisterRquest registerRequest = new UserRegisterRquest();

		registerRequest.setCaptcha("5ndd");
		registerRequest.setCaptchaId(6462713797487856467L);
		registerRequest.setContactProtect(Boolean.TRUE);
		registerRequest.setHeadImgUrl("https://wx.images.head/AJSD23329FKSD");
		registerRequest.setIntroduce("接口测试");
		registerRequest.setNickName("马先森");
		registerRequest.setPhoneNum("15669960039");
		registerRequest.setLoginAccount("mart");
		registerRequest.setPassword("123abc");

//		validator.validate(registerRequest).forEach(violation -> {
//			throw new RequestFailedException(ErrorCodeEnum.PARAMS_ERROR);
//		});

		BaseResult<UserLoginResponse> result = userController.register(registerRequest, null);

		System.out.println(GsonUtil.gson().toJson(result));
	}
}
