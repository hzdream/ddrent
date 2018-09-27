package com.aifeng.ddrent.core.dao.model.house;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_house_estate")
public class HouseEstateDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小区名
     */
    @Column(name = "NAME_")
    private String name;

    /**
     * 小区编号
     */
    @Column(name = "ESATE_CODE")
    private String esateCode;

    /**
     * 坐标，JSON数据，用","隔开
     */
    @Column(name = "COORDINATE_")
    private String coordinate;

    /**
     * 详细地址
     */
    @Column(name = "ADDRESS_")
    private String address;

    /**
     * TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    @Column(name = "CREATOR_")
    private String creator;

    /**
     * 创建人编号
     */
    @Column(name = "CREATOR_ID")
    private Integer creatorId;

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
     * 获取小区名
     *
     * @return NAME - 小区名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置小区名
     *
     * @param name 小区名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取小区编号
     *
     * @return ESATE_CODE - 小区编号
     */
    public String getEsateCode() {
        return esateCode;
    }

    /**
     * 设置小区编号
     *
     * @param esateCode 小区编号
     */
    public void setEsateCode(String esateCode) {
        this.esateCode = esateCode;
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
     * 获取TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @return CREATOR - TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @param creator TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
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