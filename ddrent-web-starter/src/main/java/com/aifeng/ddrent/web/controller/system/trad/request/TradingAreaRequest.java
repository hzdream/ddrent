package com.aifeng.ddrent.web.controller.system.trad.request;

import java.util.Date;

public class TradingAreaRequest {
    /**
     * 名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 坐标，JSON数据，用","隔开
     */
    private String coordinate;

    /**
     * 城市
     */
    private String city;

    /**
     * 面积
     */
    private Float acreage;

    /**
     * TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    private String creator;

    /**
     * 创建人编号
     */
    private Long creatortId;

    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getAcreage() {
        return acreage;
    }

    public void setAcreage(Float acreage) {
        this.acreage = acreage;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatortId() {
        return creatortId;
    }

    public void setCreatortId(Long creatortId) {
        this.creatortId = creatortId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
