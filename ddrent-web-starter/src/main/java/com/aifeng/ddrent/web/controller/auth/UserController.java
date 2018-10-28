/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.house 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午9:07:16
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth;

import com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum;
import com.aifeng.ddrent.common.enums.auth.TokenTypeEnum;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.enums.user.OriginEnum;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.exception.auth.RequestFailedException;
import com.aifeng.ddrent.common.model.auth.JwtToken;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.core.common.utils.data.Md5Util;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.dao.model.auth.UserTokenDO;
import com.aifeng.ddrent.core.dao.model.captcha.CaptchaDO;
import com.aifeng.ddrent.core.service.captcha.CaptchaService;
import com.aifeng.ddrent.core.service.user.UserService;
import com.aifeng.ddrent.core.service.user.UserTokenService;
import com.aifeng.ddrent.web.controller.BaseController;
import com.aifeng.ddrent.web.controller.auth.request.LoginRequest;
import com.aifeng.ddrent.web.controller.auth.request.UserRegisterRquest;
import com.aifeng.ddrent.web.controller.auth.request.WXRegisterReqeust;
import com.aifeng.ddrent.web.controller.auth.response.UserLoginResponse;
import com.aifeng.ddrent.web.controller.auth.response.WeixinLoginResponse;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/** 
 * @ClassName: UserController 
 * @Description: 用户控制器
 * @author: imart·deng
 * @date: 2018年9月17日 下午9:07:16  
 */
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaptchaService captchaService;

	@Autowired
	private UserTokenService userTokenService;

	/**
	 * 前端要求密码需要用md5加密之后传到后台
	 * @param params
	 * @param bind
	 * @return	BaseResult
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public BaseResult<UserLoginResponse> Login(@Valid LoginRequest params, BindingResult bind) {
		//参数校验
		validte(bind);
		
		BaseResult<UserLoginResponse> result = new BaseResult<>();
		
		//判断用户是否存在
		String loginId = params.getUsername();
		UserDO user = userService.getByLoginAccount(loginId);
		if(null != user) {
			String password = Md5Util.decode(params.getPassword().getBytes());
			
			//验证账户密码是否匹配
			if(password.equals(user.getPassword())) {
				UserLoginResponse data = new UserLoginResponse();
				BeanUtils.copyProperties(user, data);
				result.setData(new DataContainer<UserLoginResponse>(data));
				result.setCode(ErrorCodeEnum.SUCCESS);

				// 生成 web login jwt
				BaseResult<UserTokenDO> addTokenResult = userTokenService.addTokenByJwtToken(new JwtToken(user.getId(), user.getNickName(), TokenTypeEnum.NORMAL, getClientIp(), TokenAuthTypeEnum.WEB_LOGIN));

				if(!addTokenResult.isSuccess()) return result.setCode(ErrorCodeEnum.TOKEN_ADD_FAILD);

				String accessToken = addTokenResult.getData().getObj().getAccessToken();
				//token 写到cookie里面去
				addTokenCooike(accessToken);

			}else {
				throw new RequestFailedException(ErrorCodeEnum.USER_PASSWORD_MISMATCH);
			}
			
		}else {
			throw new RequestFailedException(ErrorCodeEnum.USER_NOT_EXIST);
		}
		
		return result;
	}

	public static void main1(String[] args) {
		String password = "123456";
		assert Md5Crypt.md5Crypt(password.getBytes()).equals(Md5Crypt.md5Crypt(password.getBytes())): "同一时刻md5加密结果为 true \n";
		System.out.format("同一时刻md5加密结果为 true \n");
		String md5str1 = Md5Crypt.md5Crypt(password.getBytes());
		String md5str2 = null;
		try {
			Thread.sleep(500);
			md5str2 = Md5Crypt.md5Crypt(password.getBytes());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(md5str1.equals(md5str2)) {
			System.out.format("不同时刻md5加密结果为 true \n两次结果分别为：\nmd5str1:\n%s\nmd5str2:\n%s\n", md5str1, md5str2);
		}else{
			System.out.format("不同时刻md5加密结果为 false \n两次结果分别为：\nmd5str1:\n%s\nmd5str2:\n%s\n", md5str1, md5str2);
		}
//		assert md5str1.equals(md5str2):"不同时刻md5加密结果为 false \n";
//		System.out.format("加密后的结果 %s", Md5Crypt.md5Crypt(password.getBytes()));

	}

	@RequestMapping(value = "login/wx")
	public BaseResult<WeixinLoginResponse> wxLogin(String code, String state) {
		BaseResult<WeixinLoginResponse> result = new BaseResult<>();
		BaseResult<UserTokenDO> tokenResult = userTokenService.addUserTokenByWeixinCode(code);
		WeixinLoginResponse response = new WeixinLoginResponse();
		if(tokenResult.isSuccess()) return result.setCode(tokenResult);

		UserTokenDO userTokenDO = tokenResult.getData().getObj();

		BeanUtils.copyProperties(userTokenDO, response);
		return result.setData(new DataContainer<>(response)).setCode(ErrorCodeEnum.SUCCESS);
		
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public Object register(UserRegisterRquest params, BindingResult bind) {
		
		//参数校验
		validte(bind);
		
		//返回结果
		BaseResult<UserLoginResponse> result = new BaseResult<>();
		
		//判断验证码是否匹配
		BaseResult<CaptchaDO> smsResult = captchaService.captchaCheck(params.getCaptchaId(), params.getCaptcha());
		
		if(smsResult.isSuccess()) {
			//判断登陆账号是否使用
			UserDO user = userService.getByLoginAccount(params.getLoginAccount());
			
			if(null == user) {
				user = new UserDO();
				
				BeanUtils.copyProperties(params, user);
				//设置注册源为网页
				user.setOrigin(OriginEnum.WEB_REGISTER.name());
				//设置加密密码
				user.setPassword(Md5Util.decode(params.getPassword().getBytes()));
				//设置用户已激活
				user.setIsActive(UserActiveEnum.ACTIVE.ordinal());
				
				//注册添加用户
				user = userService.addSelective(user);
				
				//设置返回值
				UserLoginResponse response = new UserLoginResponse();
				BeanUtils.copyProperties(user, response);
				result.setData(new DataContainer<UserLoginResponse>(response));
				result.setCode(ErrorCodeEnum.SUCCESS);
			}else {
				logger.info("[用户注册] 失败，登录名已存在, 请求参数为{}", params);
				throw new RequestFailedException(ErrorCodeEnum.USER_ACCOUNT_EXIST);
			}
		}else {
			logger.info("[用户注册] 失败，验证码验证失败, 请求参数为{}", params);
			throw new RequestFailedException(ErrorCodeEnum.CAPTCHA_MISMATCH);
		}
		
		return result;
	}
	
	@RequestMapping(value="register/wx",method=RequestMethod.POST)
	public BaseResult<Object> registerWx(WXRegisterReqeust request){
		//TODO 判断该用户是否已经注册
		
		//TODO 用户未注册则获取注册微信登录用户并置为激活状态
		
		return null;
	}
	
}
