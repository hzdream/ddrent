package com.aifeng.ddrent.core.dao.model.trad;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ddrent_trading_area")
public class TradingAreaDO implements BaseDOI {
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
     * 详细地址
     */
    @Column(name = "ADDRESS_")
    private String address;

    /**
     * 坐标，JSON数据，用","隔开
     */
    @Column(name = "COORDINATE")
    private String coordinate;

    /**
     * 城市
     */
    @Column(name = "CITY_")
    private String city;

    /**
     * 面积
     */
    @Column(name = "ACREAGE_")
    private Float acreage;

    /**
     * TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    @Column(name = "CREATOR_")
    private String creator;

    /**
     * 创建人编号
     */
    @Column(name = "CREATORT_ID")
    private Long creatortId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 额外字段
     */
    @Column(name="EXTRA1_")
    private String extra1;

    @Column(name = "ORIGIN_")
    private String origin;

    /**
     * 地址层级0国1省2市3区县
     */
    @Column(name = "LEVEL_")
    private Integer level;

    /**
     * 地址链路结构
     */
    @Column(name = "STRUCTURE_")
    private String structure;

    @Column(name = "SNAPSHOT_")
    private String snapshot;

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
     * @return NAME_ - 名称
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
     * 获取详细地址
     *
     * @return ADDRESS_ - 详细地址
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
     * 获取城市
     *
     * @return CITY_ - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取面积
     *
     * @return ACREAGE_ - 面积
     */
    public Float getAcreage() {
        return acreage;
    }

    /**
     * 设置面积
     *
     * @param acreage 面积
     */
    public void setAcreage(Float acreage) {
        this.acreage = acreage;
    }

    /**
     * 获取TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @return CREATOR_ - TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
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
     * @return CREATORT_ID - 创建人编号
     */
    public Long getCreatortId() {
        return creatortId;
    }

    /**
     * 设置创建人编号
     *
     * @param creatortId 创建人编号
     */
    public void setCreatortId(Long creatortId) {
        this.creatortId = creatortId;
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
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public String toString() {
        return "TradingAreaDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", city='" + city + '\'' +
                ", acreage=" + acreage +
                ", creator='" + creator + '\'' +
                ", creatortId=" + creatortId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extra1='" + extra1 + '\'' +
                ", origin='" + origin + '\'' +
                ", level=" + level +
                ", structure='" + structure + '\'' +
                ", snapshot='" + snapshot + '\'' +
                '}';
    }
}