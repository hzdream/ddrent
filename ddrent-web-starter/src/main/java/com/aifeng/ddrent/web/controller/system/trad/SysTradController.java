package com.aifeng.ddrent.web.controller.system.trad;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.core.dao.model.trad.TradingAreaDO;
import com.aifeng.ddrent.core.service.trad.TradingAreaService;
import com.aifeng.ddrent.web.controller.BaseController;
import com.aifeng.ddrent.web.controller.system.trad.request.TradingAreaRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/trad/")
public class SysTradController extends BaseController {

    @Autowired
    private TradingAreaService tradingAreaService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public BaseResult<Long> add(TradingAreaRequest params, BindingResult bind){

        BaseResult<Long> result = new BaseResult<>();

        //参数校验
        validate(bind);

        TradingAreaDO record = new TradingAreaDO();
        BeanUtils.copyProperties(params, record);
        record = tradingAreaService.add(record);

        result.setCode(ErrorCodeEnum.SUCCESS, new DataContainer<>(record.getId()));

        return result;
    }
}
