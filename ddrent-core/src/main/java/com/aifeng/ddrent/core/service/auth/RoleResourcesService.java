/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:40:19
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.auth;

import com.aifeng.ddrent.core.dao.model.auth.RoleResourcesViewDO;
import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.auth.RoleResourcesMapper;
import com.aifeng.ddrent.core.dao.model.auth.RoleResourcesDO;
import com.aifeng.ddrent.core.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: RoleResourcesService 
 * @Description: 角色资源服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:40:19  
 */
@Service
public class RoleResourcesService extends BaseService<RoleResourcesDO, RoleResourcesMapper> {

    public List<RoleResourcesViewDO> findRoleResourcesByRoleId(Long roleId){
        if(null == roleId) return new ArrayList<>(0);

        RoleResourcesDO params = new RoleResourcesDO();
        params.setRoleId(roleId);
        return ((RoleResourcesMapper)mapper).selectViewByRoleResouce(params);
    }
}
