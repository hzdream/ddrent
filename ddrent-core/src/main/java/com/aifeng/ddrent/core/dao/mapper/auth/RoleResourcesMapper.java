package com.aifeng.ddrent.core.dao.mapper.auth;

import com.aifeng.ddrent.core.dao.model.auth.RoleResourcesDO;

import com.aifeng.ddrent.core.dao.model.auth.RoleResourcesViewDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleResourcesMapper extends Mapper<RoleResourcesDO> {

    public List<RoleResourcesViewDO> selectViewByRoleResouce(RoleResourcesDO params);
}