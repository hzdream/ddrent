/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.api.auth.response
 * @author imart·deng
 * @date 创建时间：2018/10/19 10:39
 * @version 1.0
 */
package com.aifeng.ddrent.api.auth.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserInfoDTO
 * @Description: 用户基础信息
 * @author: imart·deng
 * @date: 2018/10/19 10:39 
 *
 */
public class UserInfoDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 来源openapi
     */
    private String originOpenId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 来源
     */
    private String origin;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    private Boolean contactProtect;

    /**
     * 是否激活0、未激活；1、已激活；2、被停用
     */
    private Integer isActive;

    /**
     * 登陆账号
     */
    private String loginAccount;

    /**
     * 自我介绍
     */
    private String introduce;

    /**
     * 头像地址
     */
    private String headImgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginOpenId() {
        return originOpenId;
    }

    public void setOriginOpenId(String originOpenId) {
        this.originOpenId = originOpenId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean getContactProtect() {
        return contactProtect;
    }

    public void setContactProtect(Boolean contactProtect) {
        this.contactProtect = contactProtect;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
