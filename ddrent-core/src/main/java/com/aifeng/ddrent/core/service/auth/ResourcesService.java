/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:39:54
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.auth;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.auth.ResourcesMapper;
import com.aifeng.ddrent.core.dao.model.auth.ResourcesDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: ResourcesService 
 * @Description: 资源服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:39:54  
 */
@Service
public class ResourcesService extends BaseService<ResourcesDO, ResourcesMapper> {
}
