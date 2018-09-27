package com.aifeng.ddrent.core.dao.model.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_user")
public class UserDO implements BaseDOI {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 来源openapi
     */
    @Column(name = "ORIGIN_OPEN_ID")
    private String originOpenId;

    /**
     * 昵称
     */
    @Column(name = "NICK_NAME")
    private String nickName;

    /**
     * 来源
     */
    @Column(name = "ORIGIN_")
    private String origin;

    /**
     * 手机号码
     */
    @Column(name = "PHONE_NUM")
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    @Column(name = "CONTACT_PROTECT")
    private Boolean contactProtect;

    /**
     * 是否激活0、未激活；1、已激活；2、被停用
     */
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    /**
     * 上次登陆时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 登陆账号
     */
    @Column(name = "LOGIN_ACCOUNT")
    private String loginAccount;

    /**
     * 密码
     */
    @Column(name = "PASSWORD_")
    private String password;

    /**
     * 自我介绍
     */
    @Column(name = "INTRODUCE_")
    private String introduce;

    /**
     * 头像地址
     */
    @Column(name = "HEAD_IMG_URL")
    private String headImgUrl;

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
    
    @Column(name = "SEX_")
    private Integer sex;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取来源openapi
     *
     * @return ORIGIN_OPEN_ID - 来源openapi
     */
    public String getOriginOpenId() {
        return originOpenId;
    }

    /**
     * 设置来源openapi
     *
     * @param originOpenId 来源openapi
     */
    public void setOriginOpenId(String originOpenId) {
        this.originOpenId = originOpenId;
    }

    /**
     * 获取昵称
     *
     * @return NICK_NAME - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取来源
     *
     * @return ORIGIN - 来源
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * 设置来源
     *
     * @param origin 来源
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * 获取手机号码
     *
     * @return PHONE_NUM - 手机号码
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置手机号码
     *
     * @param phoneNum 手机号码
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取电话号码是否保密
     *
     * @return CONTACT_PROTECT - 电话号码是否保密
     */
    public Boolean getContactProtect() {
        return contactProtect;
    }

    /**
     * 设置电话号码是否保密
     *
     * @param contactProtect 电话号码是否保密
     */
    public void setContactProtect(Boolean contactProtect) {
        this.contactProtect = contactProtect;
    }

    /**
     * 获取是否激活0、未激活；1、已激活；2、被停用
     *
     * @return IS_ACTIVE - 是否激活0、未激活；1、已激活；2、被停用
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活0、未激活；1、已激活；2、被停用
     *
     * @param isActive 是否激活0、未激活；1、已激活；2、被停用
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取上次登陆时间
     *
     * @return LAST_LOGIN_TIME - 上次登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登陆时间
     *
     * @param lastLoginTime 上次登陆时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取登陆账号
     *
     * @return LOGIN_ACCOUNT - 登陆账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 设置登陆账号
     *
     * @param loginAccount 登陆账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取自我介绍
     *
     * @return INTRODUCE - 自我介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置自我介绍
     *
     * @param introduce 自我介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取头像地址
     *
     * @return HEAD_IMG_URL - 头像地址
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * 设置头像地址
     *
     * @param headImgUrl 头像地址
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
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