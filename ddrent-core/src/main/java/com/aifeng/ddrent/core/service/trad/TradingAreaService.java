package com.aifeng.ddrent.core.service.trad;

import com.aifeng.ddrent.core.dao.mapper.trad.TradingAreaMapper;
import com.aifeng.ddrent.core.dao.model.trad.TradingAreaDO;
import com.aifeng.ddrent.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingAreaService extends BaseService<TradingAreaDO, TradingAreaMapper> {

    @Autowired
    private TradingAreaMapper tradingAreaMapper;

    public int insertByBatch(List<TradingAreaDO> records){

        if(null == records || records.isEmpty()) return 0;

        return tradingAreaMapper.insertByBatch(records);

    }
}
