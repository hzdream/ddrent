/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:10
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.dao.mapper.auth.UserMapper;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: UserService 
 * @Description: 用户服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:10  
 */
@Service
public class UserService extends BaseService<UserDO, UserMapper> {
	
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

}
