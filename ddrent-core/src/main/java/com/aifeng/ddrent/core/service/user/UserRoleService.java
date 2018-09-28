/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:23
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.core.dao.mapper.auth.UserRoleMapper;
import com.aifeng.ddrent.core.dao.model.auth.UserRoleDO;
import com.aifeng.ddrent.core.service.BaseService;

import tk.mybatis.mapper.entity.Example;

/** 
 * @ClassName: UserRoleService 
 * @Description: 用户角色服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:23  
 */
@Service
public class UserRoleService extends BaseService<UserRoleDO, UserRoleMapper> {

	/**
	 * 根据用户编号获取用户角色信息
	 * @param id
	 */
	public BaseResult<UserRoleDO> findByUserId(Long userId) {
		if(null != userId) {
			Example example = new Example(UserRoleDO.class);
			example.createCriteria().andEqualTo("userId", userId);
			return findByExample(example, null);
		}
		return null;
	}
}
