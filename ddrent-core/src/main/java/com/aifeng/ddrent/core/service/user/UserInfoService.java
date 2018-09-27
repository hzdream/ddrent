/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:40:58
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.user;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.user.UserInfoMapper;
import com.aifeng.ddrent.core.dao.model.user.UserInfoDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: UserInfoService 
 * @Description: 用户信息服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:40:58  
 */
@Service
public class UserInfoService extends BaseService<UserInfoDO, UserInfoMapper> {
}
