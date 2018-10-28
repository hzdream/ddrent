/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.controller.house.response
 * @author imart·deng
 * @date 创建时间：2018/10/22 13:43
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.house.response;

import java.util.Date;

/**
 * @ClassName: HouseQueryResponse
 * @Description: 房源查询返回
 * @author: imart·deng
 * @date: 2018/10/22 13:43 
 *
 */
public class HouseQueryResponse {
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 房间编号
     */
    private String roomNumber;

    /**
     * 房间编号主卧次卧
     */
    private String roomType;

    /**
     * 房间面积
     */
    private Float roomAcreage;

    /**
     * 建筑楼层
     */
    private Integer houseFloor;

    /**
     * 房间朝向
     */
    private String roomOrientation;

    /**
     * 建筑总楼层
     */
    private Integer houseFloorTotal;

    /**
     * 房子总面积
     */
    private Float houseTotalAcreage;

    /**
     * 户型（3室2厅1卫）
     */
    private String houseType;

    /**
     * 总室数量
     */
    private Integer hoseTotalRoom;

    /**
     * 出租类型，整租、转租、合租
     */
    private String rentType;

    /**
     * 装修类型精装、普通
     */
    private String houseFitment;

    /**
     * 小区编号
     */
    private Integer estateId;

    /**
     * 小区名称
     */
    private String estateName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 坐标，JSON数据，用","隔开
     */
    private String coordinate;

    /**
     * 房间描述
     */
    private String description;

    /**
     * 创建人姓名
     */
    private String creator;

    /**
     * 创建人编号
     */
    private Integer creatorId;

    /**
     * 创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    private String creatorType;

    /**
     * 创建人称呼
     */
    private String creatorAppellation;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 支付方式押一付一
     */
    private String paymentType;

    /**
     * 是否付中介费
     */
    private Integer agencyFee;

    /**
     * 中介费方式，全付，一般，无中介费
     */
    private String agencyFeeType;

    /**
     * 共享房源、出租房源、ALL
     */
    private String showType;

    /**
     * 是否上架
     */
    private Integer isOnShelf;

    /**
     * 发布、暂存
     */
    private Integer isRelease;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Float getRoomAcreage() {
        return roomAcreage;
    }

    public void setRoomAcreage(Float roomAcreage) {
        this.roomAcreage = roomAcreage;
    }

    public Integer getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(Integer houseFloor) {
        this.houseFloor = houseFloor;
    }

    public String getRoomOrientation() {
        return roomOrientation;
    }

    public void setRoomOrientation(String roomOrientation) {
        this.roomOrientation = roomOrientation;
    }

    public Integer getHouseFloorTotal() {
        return houseFloorTotal;
    }

    public void setHouseFloorTotal(Integer houseFloorTotal) {
        this.houseFloorTotal = houseFloorTotal;
    }

    public Float getHouseTotalAcreage() {
        return houseTotalAcreage;
    }

    public void setHouseTotalAcreage(Float houseTotalAcreage) {
        this.houseTotalAcreage = houseTotalAcreage;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getHoseTotalRoom() {
        return hoseTotalRoom;
    }

    public void setHoseTotalRoom(Integer hoseTotalRoom) {
        this.hoseTotalRoom = hoseTotalRoom;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getHouseFitment() {
        return houseFitment;
    }

    public void setHouseFitment(String houseFitment) {
        this.houseFitment = houseFitment;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public String getCreatorAppellation() {
        return creatorAppellation;
    }

    public void setCreatorAppellation(String creatorAppellation) {
        this.creatorAppellation = creatorAppellation;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(Integer agencyFee) {
        this.agencyFee = agencyFee;
    }

    public String getAgencyFeeType() {
        return agencyFeeType;
    }

    public void setAgencyFeeType(String agencyFeeType) {
        this.agencyFeeType = agencyFeeType;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public Integer getIsOnShelf() {
        return isOnShelf;
    }

    public void setIsOnShelf(Integer isOnShelf) {
        this.isOnShelf = isOnShelf;
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
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
