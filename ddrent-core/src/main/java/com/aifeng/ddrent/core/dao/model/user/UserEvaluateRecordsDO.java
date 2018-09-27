package com.aifeng.ddrent.core.dao.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_user_evaluate_records")
public class UserEvaluateRecordsDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评价人
     */
    @Column(name = "EVALUATE_ORGIN_ID")
    private Long evaluateOrginId;

    /**
     * 被评价人
     */
    @Column(name = "EVALUATE_TARGET_ID")
    private Long evaluateTargetId;

    /**
     * 评分值
     */
    @Column(name = "EVALUATE_SCORE")
    private Integer evaluateScore;

    /**
     * 相关图片，最大8M，最多5张
     */
    @Column(name = "IMAGE_URLS")
    private String imageUrls;

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
     * 说明
     */
    @Column(name = "REMARKS_")
    private String remarks;

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
     * 获取评价人
     *
     * @return EVALUATE_ORGIN_ID - 评价人
     */
    public Long getEvaluateOrginId() {
        return evaluateOrginId;
    }

    /**
     * 设置评价人
     *
     * @param evaluateOrginId 评价人
     */
    public void setEvaluateOrginId(Long evaluateOrginId) {
        this.evaluateOrginId = evaluateOrginId;
    }

    /**
     * 获取被评价人
     *
     * @return EVALUATE_TARGET_ID - 被评价人
     */
    public Long getEvaluateTargetId() {
        return evaluateTargetId;
    }

    /**
     * 设置被评价人
     *
     * @param evaluateTargetId 被评价人
     */
    public void setEvaluateTargetId(Long evaluateTargetId) {
        this.evaluateTargetId = evaluateTargetId;
    }

    /**
     * 获取评分值
     *
     * @return EVALUATE_SCORE - 评分值
     */
    public Integer getEvaluateScore() {
        return evaluateScore;
    }

    /**
     * 设置评分值
     *
     * @param evaluateScore 评分值
     */
    public void setEvaluateScore(Integer evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    /**
     * 获取相关图片，最大8M，最多5张
     *
     * @return IMAGE_URLS - 相关图片，最大8M，最多5张
     */
    public String getImageUrls() {
        return imageUrls;
    }

    /**
     * 设置相关图片，最大8M，最多5张
     *
     * @param imageUrls 相关图片，最大8M，最多5张
     */
    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
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
     * 获取说明
     *
     * @return REMARKS - 说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置说明
     *
     * @param remarks 说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}