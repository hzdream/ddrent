package com.aifeng.ddrent.core.dao.model.label;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_label")
public class LabelDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标签名
     */
    @Column(name = "NAME_")
    private String name;

    /**
     * 标签类型USER_AGENT、USER_TENANT、USER_LANDLORD、HOUSE
     */
    @Column(name = "LABEL_TYPE")
    private String labelType;

    /**
     * 排序编号
     */
    @Column(name = "SORT_CODE")
    private Integer sortCode;

    /**
     * 标签描述
     */
    @Column(name = "DESCRIPTION_")
    private String description;

    /**
     * 是否有效
     */
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    /**
     * 创建类型TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    @Column(name = "CREATE_TYPE")
    private String createType;

    /**
     * 创建人ID
     */
    @Column(name = "CREATORT_ID")
    private Integer creatortId;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 获取标签名
     *
     * @return NAME - 标签名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名
     *
     * @param name 标签名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标签类型USER_AGENT、USER_TENANT、USER_LANDLORD、HOUSE
     *
     * @return LABEL_TYPE - 标签类型USER_AGENT、USER_TENANT、USER_LANDLORD、HOUSE
     */
    public String getLabelType() {
        return labelType;
    }

    /**
     * 设置标签类型USER_AGENT、USER_TENANT、USER_LANDLORD、HOUSE
     *
     * @param labelType 标签类型USER_AGENT、USER_TENANT、USER_LANDLORD、HOUSE
     */
    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    /**
     * 获取排序编号
     *
     * @return SORT_CODE - 排序编号
     */
    public Integer getSortCode() {
        return sortCode;
    }

    /**
     * 设置排序编号
     *
     * @param sortCode 排序编号
     */
    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * 获取标签描述
     *
     * @return DESCRIPTION - 标签描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置标签描述
     *
     * @param description 标签描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否有效
     *
     * @return IS_ACTIVE - 是否有效
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效
     *
     * @param isActive 是否有效
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建类型TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @return CREATE_TYPE - 创建类型TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public String getCreateType() {
        return createType;
    }

    /**
     * 设置创建类型TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     *
     * @param createType 创建类型TENANT、AGENT、LANDLORD、SYSTEM_MANAGER、SYSTEM_AUTO
     */
    public void setCreateType(String createType) {
        this.createType = createType;
    }

    /**
     * 获取创建人ID
     *
     * @return CREATORT_ID - 创建人ID
     */
    public Integer getCreatortId() {
        return creatortId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatortId 创建人ID
     */
    public void setCreatortId(Integer creatortId) {
        this.creatortId = creatortId;
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
}