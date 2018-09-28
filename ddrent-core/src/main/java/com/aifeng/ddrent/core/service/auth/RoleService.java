/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:40:08
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.auth;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.auth.RoleMapper;
import com.aifeng.ddrent.core.dao.model.auth.RoleDO;
import com.aifeng.ddrent.core.service.BaseService;

import tk.mybatis.mapper.entity.Example;

/** 
 * @ClassName: RoleService 
 * @Description: 角色服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:40:08  
 */
@Service
public class RoleService extends BaseService<RoleDO, RoleMapper> {

	/**
	 * 根据roleCode获取角色
	 * @param tourist
	 * @return
	 */
	public RoleDO getByRoleCode(String roleCode) {
		
		Example example = new Example(RoleDO.class);
		example.createCriteria().andEqualTo("roleCode", roleCode);
		
		return getByExample(example);
	}
}
