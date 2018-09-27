/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.house 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:35:43
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.house;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.core.dao.mapper.house.HouseMapper;
import com.aifeng.ddrent.core.dao.model.house.HouseDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: houseService 
 * @Description: 房源服务
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:35:43  
 */
@Service
public class HouseService extends BaseService<HouseDO, HouseMapper> {
}
