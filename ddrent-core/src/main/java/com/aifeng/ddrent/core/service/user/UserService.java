/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:10
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import java.util.List;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.enums.auth.UserRoleLevelEnum;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.enums.user.OriginEnum;
import com.aifeng.ddrent.common.enums.user.UserActiveEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.json.GsonUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.dao.mapper.auth.UserMapper;
import com.aifeng.ddrent.core.dao.model.auth.RoleDO;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.dao.model.auth.UserRoleDO;
import com.aifeng.ddrent.core.service.BaseService;
import com.aifeng.ddrent.core.service.auth.RoleService;

/** 
 * @ClassName: UserService 
 * @Description: 用户服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:10  
 */
@Service
public class UserService extends BaseService<UserDO, UserMapper> {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserRoleService userRoleService;
	/**
	 * 根据登陆号获取用户信息
	 * @param loginId	用户号
	 */
	public UserDO getByLoginAccount(String loginId) {
		
		if(StringUtils.isNotBlank(loginId)) {
			UserDO params = new UserDO();
			params.setLoginAccount(loginId);
			List<UserDO> userList = mapper.select(params);
			
			if(null != userList && !userList.isEmpty()) {
				return userList.get(0);
			}
		}
		
		return null;
	}

	/**
	 * 根据微信openId 获取用户
	 * @param openId
	 */
	public UserDO getByOpenId(String openId) {
		
		if(StringUtils.isNotBlank(openId)) {
			UserDO params = new UserDO();
			params.setOriginOpenId(openId);
//			params.setOrigin(OriginEnum.WX_REGISTER.name());
			
			List<UserDO> userList = mapper.select(params);
			
			if(null != userList && !userList.isEmpty()) {
				return userList.get(0);
			}
		}
		
		return null;
	}

	/**
	 * @param user
	 * @return
	 */
	public BaseResult<UserDO> register(UserDO user, UserRoleLevelEnum userRoleEnum) {
		
		try {
			BaseResult<UserDO> result = new BaseResult<>();
			if(null != user) {
				if(null != user.getOrigin() && null != user.getOriginOpenId()) {
					//第三方注册用户
					
					//判断用户是否激活用户
					user.setIsActive(StringUtils.isBlank(user.getPhoneNum()) ? UserActiveEnum.UNACTIVATED.ordinal()
							: UserActiveEnum.ACTIVE.ordinal());
					
					if(user.getIsActive().equals(UserActiveEnum.ACTIVE.ordinal())) {
						//已激活用户没填角色，则默认为租客
						userRoleEnum = null == userRoleEnum ? UserRoleLevelEnum.TENANT : userRoleEnum;
					}else {
						//未激活用户，则默认为租客
						userRoleEnum = UserRoleLevelEnum.TOURIST;
					}
				}else if(normalRegisterValue(user)) {
					//正常注册用户
					user.setIsActive(UserActiveEnum.ACTIVE.ordinal());
					//加密存储密码
					user.setPassword(Md5Crypt.md5Crypt(user.getPassword().getBytes()));
					//设置用户注册来源
					user.setOrigin(OriginEnum.WEB_REGISTER.name());
					
					//已激活用户没填角色，则默认为租客
					userRoleEnum = null == userRoleEnum ? UserRoleLevelEnum.TENANT : userRoleEnum;
				}else {
					//参数异常
					logger.info("[用户注册] 失败, 请求参数{}, 失败原因：[参数异常]", GsonUtil.gson().toJson(user));
					result.setCode(ErrorCodeEnum.PARAMS_ERROR);
					return result;
				}
				
				// 用户入库
				user = addSelective(user);
				DataContainer<UserDO> data = new DataContainer<>(user);
				
				// 获取当前用户角色信息
				RoleDO role = roleService.getByRoleCode(userRoleEnum.name());
				//设置额外返回值，角色信息
				data.setOther(role);
				
				//添加用户角色信息
				UserRoleDO userRole = new UserRoleDO();
				userRole.setUserId(user.getId());
				userRole.setRoleId(role.getId());
				userRoleService.addSelective(userRole);
				
				//设置请求成功
				result.setCode(ErrorCodeEnum.SUCCESS, data);
			}else {
				//参数异常
				logger.info("[用户注册] 失败, 请求参数{}, 失败原因：[参数异常]", GsonUtil.gson().toJson(user));
				result.setCode(ErrorCodeEnum.PARAMS_ERROR);
			}
			return result;
		} catch (Exception e) {
			logger.info("[用户注册] 失败, 请求参数{}, 未知异常, 失败原因：[{}]", GsonUtil.gson().toJson(user), e.getMessage());
			throw e;
		}
		
		
	}

	/**
	 * 校验是否正确注册
	 * @param user
	 * @return
	 */
	private boolean normalRegisterValue(UserDO user) {
		return StringUtils.isNotBlank(user.getLoginAccount())
				&& StringUtils.isNotBlank(user.getNickName())
				&& StringUtils.isNotBlank(user.getPassword())
				&& StringUtils.isNotBlank(user.getPhoneNum());
	}

}
