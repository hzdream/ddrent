package com.aifeng.ddrent.core.dao.model.house;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_house")
public class HouseDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "NAME_")
    private String name;

    /**
     * 房间编号
     */
    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    /**
     * 房间编号主卧次卧
     */
    @Column(name = "ROOM_TYPE")
    private String roomType;

    /**
     * 房间面积
     */
    @Column(name = "ROOM_ACREAGE")
    private Float roomAcreage;

    /**
     * 建筑楼层
     */
    @Column(name = "HOUSE_FLOOR")
    private Integer houseFloor;

    /**
     * 房间朝向
     */
    @Column(name = "ROOM_ORIENTATION")
    private String roomOrientation;

    /**
     * 建筑总楼层
     */
    @Column(name = "HOUSE_FLOOR_TOTAL")
    private Integer houseFloorTotal;

    /**
     * 房子总面积
     */
    @Column(name = "HOUSE_TOTAL_ACREAGE")
    private Float houseTotalAcreage;

    /**
     * 户型（3室2厅1卫）
     */
    @Column(name = "HOUSE_TYPE")
    private String houseType;

    /**
     * 总室数量
     */
    @Column(name = "HOSE_TOTAL_ROOM")
    private Integer hoseTotalRoom;

    /**
     * 出租类型，整租、转租、合租
     */
    @Column(name = "RENT_TYPE")
    private String rentType;

    /**
     * 装修类型精装、普通
     */
    @Column(name = "HOUSE_FITMENT")
    private String houseFitment;

    /**
     * 小区编号
     */
    @Column(name = "ESTATE_ID")
    private Integer estateId;

    /**
     * 小区名称
     */
    @Column(name = "ESTATE_NAME")
    private String estateName;

    /**
     * 详细地址
     */
    @Column(name = "ADDRESS_")
    private String address;

    /**
     * 坐标，JSON数据，用","隔开
     */
    @Column(name = "COORDINATE_")
    private String coordinate;

    /**
     * 房间描述
     */
    @Column(name = "DESCRIPTION_")
    private String description;

    /**
     * 创建人姓名
     */
    @Column(name = "CREATOR_")
    private String creator;

    /**
     * 创建人编号
     */
    @Column(name = "CREATOR_ID")
    private Integer creatorId;

    /**
     * 创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    @Column(name = "CREATOR_TYPE")
    private String creatorType;

    /**
     * 创建人称呼
     */
    @Column(name = "CREATOR_APPELLATION")
    private String creatorAppellation;

    /**
     * 联系方式
     */
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    /**
     * 支付方式押一付一
     */
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    /**
     * 是否付中介费
     */
    @Column(name = "AGENCY_FEE")
    private Integer agencyFee;

    /**
     * 中介费方式，全付，一般，无中介费
     */
    @Column(name = "AGENCY_FEE_TYPE")
    private String agencyFeeType;

    /**
     * 共享房源、出租房源、ALL
     */
    @Column(name = "SHOW_TYPE")
    private String showType;

    /**
     * 是否上架
     */
    @Column(name = "IS_ON_SHELF")
    private Integer isOnShelf;

    /**
     * 发布、暂存
     */
    @Column(name = "IS_RELEASE")
    private Integer isRelease;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取房间编号
     *
     * @return ROOM_NUMBER - 房间编号
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 设置房间编号
     *
     * @param roomNumber 房间编号
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * 获取房间编号主卧次卧
     *
     * @return ROOM_TYPE - 房间编号主卧次卧
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 设置房间编号主卧次卧
     *
     * @param roomType 房间编号主卧次卧
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * 获取房间面积
     *
     * @return ROOM_ACREAGE - 房间面积
     */
    public Float getRoomAcreage() {
        return roomAcreage;
    }

    /**
     * 设置房间面积
     *
     * @param roomAcreage 房间面积
     */
    public void setRoomAcreage(Float roomAcreage) {
        this.roomAcreage = roomAcreage;
    }

    /**
     * 获取建筑楼层
     *
     * @return HOUSE_FLOOR - 建筑楼层
     */
    public Integer getHouseFloor() {
        return houseFloor;
    }

    /**
     * 设置建筑楼层
     *
     * @param houseFloor 建筑楼层
     */
    public void setHouseFloor(Integer houseFloor) {
        this.houseFloor = houseFloor;
    }

    /**
     * 获取房间朝向
     *
     * @return ROOM_ORIENTATION - 房间朝向
     */
    public String getRoomOrientation() {
        return roomOrientation;
    }

    /**
     * 设置房间朝向
     *
     * @param roomOrientation 房间朝向
     */
    public void setRoomOrientation(String roomOrientation) {
        this.roomOrientation = roomOrientation;
    }

    /**
     * 获取建筑总楼层
     *
     * @return HOUSE_FLOOR_TOTAL - 建筑总楼层
     */
    public Integer getHouseFloorTotal() {
        return houseFloorTotal;
    }

    /**
     * 设置建筑总楼层
     *
     * @param houseFloorTotal 建筑总楼层
     */
    public void setHouseFloorTotal(Integer houseFloorTotal) {
        this.houseFloorTotal = houseFloorTotal;
    }

    /**
     * 获取房子总面积
     *
     * @return HOUSE_TOTAL_ACREAGE - 房子总面积
     */
    public Float getHouseTotalAcreage() {
        return houseTotalAcreage;
    }

    /**
     * 设置房子总面积
     *
     * @param houseTotalAcreage 房子总面积
     */
    public void setHouseTotalAcreage(Float houseTotalAcreage) {
        this.houseTotalAcreage = houseTotalAcreage;
    }

    /**
     * 获取户型（3室2厅1卫）
     *
     * @return HOUSE_TYPE - 户型（3室2厅1卫）
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 设置户型（3室2厅1卫）
     *
     * @param houseType 户型（3室2厅1卫）
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    /**
     * 获取总室数量
     *
     * @return HOSE_TOTAL_ROOM - 总室数量
     */
    public Integer getHoseTotalRoom() {
        return hoseTotalRoom;
    }

    /**
     * 设置总室数量
     *
     * @param hoseTotalRoom 总室数量
     */
    public void setHoseTotalRoom(Integer hoseTotalRoom) {
        this.hoseTotalRoom = hoseTotalRoom;
    }

    /**
     * 获取出租类型，整租、转租、合租
     *
     * @return RENT_TYPE - 出租类型，整租、转租、合租
     */
    public String getRentType() {
        return rentType;
    }

    /**
     * 设置出租类型，整租、转租、合租
     *
     * @param rentType 出租类型，整租、转租、合租
     */
    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    /**
     * 获取装修类型精装、普通
     *
     * @return HOUSE_FITMENT - 装修类型精装、普通
     */
    public String getHouseFitment() {
        return houseFitment;
    }

    /**
     * 设置装修类型精装、普通
     *
     * @param houseFitment 装修类型精装、普通
     */
    public void setHouseFitment(String houseFitment) {
        this.houseFitment = houseFitment;
    }

    /**
     * 获取小区编号
     *
     * @return ESTATE_ID - 小区编号
     */
    public Integer getEstateId() {
        return estateId;
    }

    /**
     * 设置小区编号
     *
     * @param estateId 小区编号
     */
    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    /**
     * 获取小区名称
     *
     * @return ESTATE_NAME - 小区名称
     */
    public String getEstateName() {
        return estateName;
    }

    /**
     * 设置小区名称
     *
     * @param estateName 小区名称
     */
    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    /**
     * 获取详细地址
     *
     * @return ADDRESS - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取坐标，JSON数据，用","隔开
     *
     * @return COORDINATE - 坐标，JSON数据，用","隔开
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * 设置坐标，JSON数据，用","隔开
     *
     * @param coordinate 坐标，JSON数据，用","隔开
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * 获取房间描述
     *
     * @return DESCRIPTION - 房间描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置房间描述
     *
     * @param description 房间描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建人姓名
     *
     * @return CREATOR - 创建人姓名
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人姓名
     *
     * @param creator 创建人姓名
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建人编号
     *
     * @return CREATOR_ID - 创建人编号
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人编号
     *
     * @param creatorId 创建人编号
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @return CREATOR_TYPE - 创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public String getCreatorType() {
        return creatorType;
    }

    /**
     * 设置创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @param creatorType 创建人类型ETENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    /**
     * 获取创建人称呼
     *
     * @return CREATOR_APPELLATION - 创建人称呼
     */
    public String getCreatorAppellation() {
        return creatorAppellation;
    }

    /**
     * 设置创建人称呼
     *
     * @param creatorAppellation 创建人称呼
     */
    public void setCreatorAppellation(String creatorAppellation) {
        this.creatorAppellation = creatorAppellation;
    }

    /**
     * 获取联系方式
     *
     * @return CONTACT_PHONE - 联系方式
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系方式
     *
     * @param contactPhone 联系方式
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取支付方式押一付一
     *
     * @return PAYMENT_TYPE - 支付方式押一付一
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * 设置支付方式押一付一
     *
     * @param paymentType 支付方式押一付一
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取是否付中介费
     *
     * @return AGENCY_FEE - 是否付中介费
     */
    public Integer getAgencyFee() {
        return agencyFee;
    }

    /**
     * 设置是否付中介费
     *
     * @param agencyFee 是否付中介费
     */
    public void setAgencyFee(Integer agencyFee) {
        this.agencyFee = agencyFee;
    }

    /**
     * 获取中介费方式，全付，一般，无中介费
     *
     * @return AGENCY_FEE_TYPE - 中介费方式，全付，一般，无中介费
     */
    public String getAgencyFeeType() {
        return agencyFeeType;
    }

    /**
     * 设置中介费方式，全付，一般，无中介费
     *
     * @param agencyFeeType 中介费方式，全付，一般，无中介费
     */
    public void setAgencyFeeType(String agencyFeeType) {
        this.agencyFeeType = agencyFeeType;
    }

    /**
     * 获取共享房源、出租房源、ALL
     *
     * @return SHOW_TYPE - 共享房源、出租房源、ALL
     */
    public String getShowType() {
        return showType;
    }

    /**
     * 设置共享房源、出租房源、ALL
     *
     * @param showType 共享房源、出租房源、ALL
     */
    public void setShowType(String showType) {
        this.showType = showType;
    }

    /**
     * 获取是否上架
     *
     * @return IS_ON_SHELF - 是否上架
     */
    public Integer getIsOnShelf() {
        return isOnShelf;
    }

    /**
     * 设置是否上架
     *
     * @param isOnShelf 是否上架
     */
    public void setIsOnShelf(Integer isOnShelf) {
        this.isOnShelf = isOnShelf;
    }

    /**
     * 获取发布、暂存
     *
     * @return IS_RELEASE - 发布、暂存
     */
    public Integer getIsRelease() {
        return isRelease;
    }

    /**
     * 设置发布、暂存
     *
     * @param isRelease 发布、暂存
     */
    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}