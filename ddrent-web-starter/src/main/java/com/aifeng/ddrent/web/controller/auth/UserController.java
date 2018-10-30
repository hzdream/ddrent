/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.house 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午9:07:16
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth;

import com.aifeng.ddrent.common.constant.session.SessionConstant;
import com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum;
import com.aifeng.ddrent.common.enums.auth.TokenTypeEnum;
import com.aifeng.ddrent.common.enums.captcha.CaptchaEnum;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.enums.user.OriginEnum;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.exception.auth.RequestFailedException;
import com.aifeng.ddrent.common.model.auth.JwtToken;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.data.GsonUtil;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
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
import com.aifeng.ddrent.web.controller.auth.request.WXRegisterRequest;
import com.aifeng.ddrent.web.controller.auth.response.CaptchaImageResponse;
import com.aifeng.ddrent.web.controller.auth.response.UserLoginResponse;
import com.aifeng.ddrent.web.controller.auth.response.WeixinLoginResponse;
import com.aifeng.ddrent.web.response.commons.SessionInfo;
import com.google.code.kaptcha.Producer;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

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
	private Producer captchaProducer;

	@Autowired
	private UserTokenService userTokenService;

	/**
	 *
	 * @return
	 */
	@RequestMapping("/login/captcha")
	public BaseResult<CaptchaImageResponse> createCaptchaImage() {
		BaseResult<CaptchaImageResponse> result = new BaseResult<>();
		// 生成验证码文本
		String captchaText = captchaProducer.createText();

		ByteArrayOutputStream out = null;
		try {
			CaptchaImageResponse response = new CaptchaImageResponse();
			CaptchaDO captchaDO = new CaptchaDO();
			Long id = SequenceGeneratorUtil.nextId();
			captchaDO.setId(id);
			// 设置返回id
			response.setId(id);
			// 验证码类型,把id作为接收方
			captchaDO.setTo(id + "");
			captchaDO.setIsActive(Boolean.TRUE);
			captchaDO.setCaptcha(captchaText);
			captchaDO.setBusiType(CaptchaEnum.USER_LOGIN.name());
			captchaDO = captchaService.add(captchaDO);
			// 生成校验码图片
			BufferedImage bi = captchaProducer.createImage(captchaText);
			out = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", out);
			// 设置图片校验码
			response.setImage("data:image/jpg;base64," + new String(Base64.getEncoder().encode(out.toByteArray())));

			result.setCode(ErrorCodeEnum.SUCCESS).setData(new DataContainer<>(response));
		} catch (IOException e) {
			result.setCode(ErrorCodeEnum.CAPTCHA_IMAGE_CREATE_ERROR);
			logger.error("[生成校验码图片] 失败, 失败原因{}", e.getMessage());
		} finally {
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					logger.debug("[生成校验码图片] 关闭文件流失败, 失败原因{}", e.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 前端要求密码需要用md5加密之后传到后台
	 * @param params
	 * @param bind
	 * @return	BaseResult
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public BaseResult<UserLoginResponse> Login(@Valid LoginRequest params, BindingResult bind) {
		//参数校验
		validate(bind);
		
		BaseResult<UserLoginResponse> result = new BaseResult<>();

		// 判断校验码是否存在
		BaseResult<CaptchaDO> captchaResult = captchaService.captchaCheck(params.getCaptchaId(), params.getCaptcha(), CaptchaEnum.USER_LOGIN);
		if(!captchaResult.isSuccess()){
			return result.setCode(captchaResult);
		}

		//判断用户是否存在
		String loginId = params.getUsername();
		UserDO user = userService.getByLoginAccount(loginId);
		if(null != user) {
			// 用户被禁用
			if(Objects.equals(UserActiveEnum.FORBIDDEN.ordinal(), user.getIsActive())){
				return result.setCode(ErrorCodeEnum.USER_FORBIDDEN);
			}

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

	@RequestMapping(value = "login/wx")
	public BaseResult<WeixinLoginResponse> wxLogin(String code, String state) {
		BaseResult<WeixinLoginResponse> result = new BaseResult<>();
		BaseResult<UserTokenDO> tokenResult = userTokenService.addUserTokenByWeixinCode(code);
		WeixinLoginResponse response = new WeixinLoginResponse();
		if(!tokenResult.isSuccess()) return result.setCode(tokenResult);

		UserTokenDO userTokenDO = tokenResult.getData().getObj();

		BeanUtils.copyProperties(userTokenDO, response);
		return result.setData(new DataContainer<>(response)).setCode(ErrorCodeEnum.SUCCESS);
		
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public BaseResult<UserLoginResponse> register(UserRegisterRquest params, BindingResult bind) {
		
		//参数校验
//		validate(bind);
		
		//返回结果
		BaseResult<UserLoginResponse> result = new BaseResult<>();
		
		//判断验证码是否匹配
		BaseResult<CaptchaDO> smsResult = captchaService.captchaCheck(params.getCaptchaId(), params.getCaptcha(), CaptchaEnum.REGISTER);
		
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
	public BaseResult<Boolean> registerWx(WXRegisterRequest params, BindingResult bind){
		BaseResult<Boolean> result = new BaseResult<>();
		//参数校验
		validate(bind);

		// 获取用户session信息
		SessionInfo sessionInfo = getSessionInfo();

		// 用户被禁用
		if(Objects.equals(UserActiveEnum.FORBIDDEN.ordinal(), sessionInfo.getIsActive())){
			return result.setCode(ErrorCodeEnum.USER_FORBIDDEN);
		}

		// 用户已经注册
		if(Objects.equals(UserActiveEnum.ACTIVE.ordinal(), sessionInfo.getIsActive())){
			return result.setCode(ErrorCodeEnum.USER_ACTIVATED);
		}

		// 更新用户数据
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(params, userDO);
		userDO.setId(sessionInfo.getId());
		userService.updateByIdSelective(userDO);

		return result.setCode(ErrorCodeEnum.SUCCESS);
	}

	/**
	 * 获取token
	 * @param request
	 * @return
	 */
	private String getToken(ServletRequest request){
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		for (Cookie cookie: cookies) {
			if(SessionConstant.ACCESS_TOKEN.equals(cookie.getName())) return cookie.getValue();
		}
		return null;
	}
}
