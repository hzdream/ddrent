/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.house 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午9:08:49
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.house;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.core.dao.model.house.HouseDO;
import com.aifeng.ddrent.core.service.house.HouseService;
import com.aifeng.ddrent.web.controller.BaseController;
import com.aifeng.ddrent.web.controller.house.request.HouseQueryRequest;
import com.aifeng.ddrent.web.controller.house.request.HouseRequest;
import com.aifeng.ddrent.web.controller.house.response.HouseQueryResponse;
import com.aifeng.ddrent.web.response.commons.SessionInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/** 
 * @ClassName: houseController 
 * @Description: TODO
 * @author: imart·deng
 * @date: 2018年9月17日 下午9:08:49  
 */
@RequestMapping("/house/")
public class houseController extends BaseController {

    @Autowired
    private HouseService houseService;

    /**
     * 查询方法
     * @param params        请求参数
     * @param roleCode      路径参数，用来标记角色
     * @param bind          参数校验结果
     * @return              房源ID
     */
    @RequestMapping(value = "{roleCode}", method = RequestMethod.POST)
    public BaseResult<Long> add(@Valid @RequestBody HouseRequest params, @PathVariable Integer roleCode, BindingResult bind){
        //参数校验
        validate(bind);

        SessionInfo sessionInfo = getSessionInfo();
//        sessionInfo.geti

        BaseResult<Long> result = new BaseResult<>();

        HouseDO record = new HouseDO();
        BeanUtils.copyProperties(params, record);
        record = houseService.add(record);

        return result.setData(new DataContainer<>(record.getId())).setCode(ErrorCodeEnum.SUCCESS);
    }

    /**
     * 查询房源信息
     * @param params    请求参数
     * @param bind      参数校验结果
     * @return          房源列表信息
     */
    @RequestMapping(value = "{roleCode}", method = RequestMethod.GET)
    public BaseResult<HouseQueryResponse> query(@Valid @RequestBody HouseQueryRequest params, BindingResult bind){
        //参数校验
        validate(bind);

        BaseResult<HouseQueryResponse> result = new BaseResult<>();

        return  result;
    }

}
