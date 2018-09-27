/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:40:32
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.user.UserContactRecordsMapper;
import com.aifeng.ddrent.core.dao.model.user.UserContactRecordsDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: UserContactRecordsService 
 * @Description: 用户联系记录服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:40:32  
 */
@Service
public class UserContactRecordsService extends BaseService<UserContactRecordsDO, UserContactRecordsMapper> {
}
