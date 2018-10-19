package com.aifeng.ddrent.core.dao.mapper.trad;

import com.aifeng.ddrent.core.dao.model.trad.TradingAreaDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TradingAreaMapper extends Mapper<TradingAreaDO> {

    /**
     * 批量插入区域
     * @param records   需要插入的数据v
     * @return 插入结果数
     */
    public int insertByBatch(List<TradingAreaDO> records);
}