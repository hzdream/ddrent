package com.aifeng.ddrent.core.dao.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_user_contact_records")
public class UserContactRecordsDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发起请求方
     */
    @Column(name = "CONTACT_ORIGIN_ID")
    private Long contactOriginId;

    /**
     * 目标方
     */
    @Column(name = "CONTACT_TARGET_ID")
    private Long contactTargetId;

    /**
     * 联系类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    @Column(name = "CONTACT_TYPE")
    private String contactType;

    /**
     * 是否被允许
     */
    @Column(name = "IS_APPROVED")
    private Integer isApproved;

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
     * 快照
     */
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
     * 获取发起请求方
     *
     * @return CONTACT_ORIGIN_ID - 发起请求方
     */
    public Long getContactOriginId() {
        return contactOriginId;
    }

    /**
     * 设置发起请求方
     *
     * @param contactOriginId 发起请求方
     */
    public void setContactOriginId(Long contactOriginId) {
        this.contactOriginId = contactOriginId;
    }

    /**
     * 获取目标方
     *
     * @return CONTACT_TARGET_ID - 目标方
     */
    public Long getContactTargetId() {
        return contactTargetId;
    }

    /**
     * 设置目标方
     *
     * @param contactTargetId 目标方
     */
    public void setContactTargetId(Long contactTargetId) {
        this.contactTargetId = contactTargetId;
    }

    /**
     * 获取联系类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     *
     * @return CONTACT_TYPE - 联系类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * 设置联系类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     *
     * @param contactType 联系类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * 获取是否被允许
     *
     * @return IS_APPROVED - 是否被允许
     */
    public Integer getIsApproved() {
        return isApproved;
    }

    /**
     * 设置是否被允许
     *
     * @param isApproved 是否被允许
     */
    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
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

    /**
     * 获取快照
     *
     * @return SNAPSHOT - 快照
     */
    public String getSnapshot() {
        return snapshot;
    }

    /**
     * 设置快照
     *
     * @param snapshot 快照
     */
    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }
}