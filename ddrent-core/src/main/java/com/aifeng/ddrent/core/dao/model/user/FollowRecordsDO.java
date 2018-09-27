package com.aifeng.ddrent.core.dao.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_follow_records")
public class FollowRecordsDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关注人
     */
    @Column(name = "FOLLOW_ORIGIN_ID")
    private Long followOriginId;

    /**
     * 被关注人
     */
    @Column(name = "CONTACT_TARGET_ID")
    private Long contactTargetId;

    /**
     * 关注类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    @Column(name = "FOLLOW_TYPE")
    private String followType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

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
     * 获取关注人
     *
     * @return FOLLOW_ORIGIN_ID - 关注人
     */
    public Long getFollowOriginId() {
        return followOriginId;
    }

    /**
     * 设置关注人
     *
     * @param followOriginId 关注人
     */
    public void setFollowOriginId(Long followOriginId) {
        this.followOriginId = followOriginId;
    }

    /**
     * 获取被关注人
     *
     * @return CONTACT_TARGET_ID - 被关注人
     */
    public Long getContactTargetId() {
        return contactTargetId;
    }

    /**
     * 设置被关注人
     *
     * @param contactTargetId 被关注人
     */
    public void setContactTargetId(Long contactTargetId) {
        this.contactTargetId = contactTargetId;
    }

    /**
     * 获取关注类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     *
     * @return FOLLOW_TYPE - 关注类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    public String getFollowType() {
        return followType;
    }

    /**
     * 设置关注类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     *
     * @param followType 关注类型TENANT2LANDLORD、TENANT2AGENT、AGENT2LANDLORD、AGENT2TENANT、LANDLORD2TENANT、LANDLORD2AGENT
     */
    public void setFollowType(String followType) {
        this.followType = followType;
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