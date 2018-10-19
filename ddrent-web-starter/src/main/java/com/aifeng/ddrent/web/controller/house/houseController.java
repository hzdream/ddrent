/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.house 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午9:08:49
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.house;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.core.service.house.HouseService;
import com.aifeng.ddrent.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * @ClassName: houseController 
 * @Description: TODO
 * @author: imart·deng
 * @date: 2018年9月17日 下午9:08:49  
 */
public class houseController extends BaseController {

    @Autowired
    private HouseService houseService;

    public BaseResult add(){


        return null;
    }

}
