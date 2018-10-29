/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: org.ddrent.web.user 
 * @author imart·deng
 * @date 创建时间：2018年9月28日 下午5:24:19
 * @version 1.0
 */
package org.ddrent.web.user;

import com.aifeng.ddrent.web.controller.auth.UserController;
import com.aifeng.ddrent.web.controller.auth.request.UserRegisterRquest;
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

	public void register(){
		UserRegisterRquest registerRquest = new UserRegisterRquest();

		registerRquest.setCaptcha("");
		registerRquest.setCaptchaId(1L);
		registerRquest.setContactProtect(Boolean.TRUE);
		registerRquest.setHeadImgUrl("https://wx.images.head/AJSD23329FKSD");
		registerRquest.setIntroduce("接口测试");
		registerRquest.setNickName("马先森");
		registerRquest.setPhoneNum("15669960039");
		registerRquest.setLoginAccount("mart");
		registerRquest.setPassword("123abc");
	}
}
