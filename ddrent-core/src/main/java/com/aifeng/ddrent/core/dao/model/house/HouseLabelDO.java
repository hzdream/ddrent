package com.aifeng.ddrent.core.dao.model.house;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_house_label")
public class HouseLabelDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房源编号
     */
    @Column(name = "HOUSE_ID")
    private Long houseId;

    /**
     * 标签编号
     */
    @Column(name = "LABEL_ID")
    private Long labelId;

    /**
     * 创建人TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    @Column(name = "CREATOR_")
    private String creator;

    /**
     * 创建人编号
     */
    @Column(name = "CREATOR_ID")
    private Long creatorId;

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
     * 获取房源编号
     *
     * @return HOUSE_ID - 房源编号
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置房源编号
     *
     * @param houseId 房源编号
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取标签编号
     *
     * @return LABEL_ID - 标签编号
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置标签编号
     *
     * @param labelId 标签编号
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取创建人TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @return CREATOR - 创建人TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @param creator 创建人TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建人编号
     *
     * @return CREATOR_ID - 创建人编号
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人编号
     *
     * @param creatorId 创建人编号
     */
    public void setCreatorId(Long creatorId) {
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