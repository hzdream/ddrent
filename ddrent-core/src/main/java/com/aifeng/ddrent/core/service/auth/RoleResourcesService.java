/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:40:19
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.auth;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.auth.RoleResourcesMapper;
import com.aifeng.ddrent.core.dao.model.auth.RoleResourcesDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: RoleResourcesService 
 * @Description: 角色资源服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:40:19  
 */
@Service
public class RoleResourcesService extends BaseService<RoleResourcesDO, RoleResourcesMapper> {
}
