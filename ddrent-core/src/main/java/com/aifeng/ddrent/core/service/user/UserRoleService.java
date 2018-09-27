/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:41:23
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.auth.UserRoleMapper;
import com.aifeng.ddrent.core.dao.model.auth.UserRoleDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: UserRoleService 
 * @Description: 用户角色服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:41:23  
 */
@Service
public class UserRoleService extends BaseService<UserRoleDO, UserRoleMapper> {
}
