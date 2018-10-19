package com.aifeng.ddrent.core.dao.model.trad;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ddrent_trading_estate")
public class TradingEstateDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商圈编号
     */
    @Column(name = "TRADING_ID")
    private Long tradingId;

    /**
     * 小区编号
     */
    @Column(name = "ESTATE_ID")
    private Long estateId;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商圈编号
     *
     * @return TRADING_ID - 商圈编号
     */
    public Long getTradingId() {
        return tradingId;
    }

    /**
     * 设置商圈编号
     *
     * @param tradingId 商圈编号
     */
    public void setTradingId(Long tradingId) {
        this.tradingId = tradingId;
    }

    /**
     * 获取小区编号
     *
     * @return ESTATE_ID - 小区编号
     */
    public Long getEstateId() {
        return estateId;
    }

    /**
     * 设置小区编号
     *
     * @param estateId 小区编号
     */
    public void setEstateId(Long estateId) {
        this.estateId = estateId;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}