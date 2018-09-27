/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:39:34
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.user.FollowRecordsMapper;
import com.aifeng.ddrent.core.dao.model.user.FollowRecordsDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: FollowRecordsService 
 * @Description: 关注服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:39:34  
 */
@Service
public class FollowRecordsService extends BaseService<FollowRecordsDO, FollowRecordsMapper> {
}
