package com.aifeng.ddrent.core.dao.model.user;

import java.util.Date;
import javax.persistence.*;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_user_info")
public class UserInfoDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 地址
     */
    @Column(name = "ADDRESS_")
    private String address;

    /**
     * 身份证号
     */
    @Column(name = "IDCARD_NUM")
    private String idcardNum;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL_")
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "PHONE_NUM")
    private String phoneNum;

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
     * 获取姓名
     *
     * @return USER_NAME - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取地址
     *
     * @return ADDRESS_ - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取身份证号
     *
     * @return IDCARD_NUM - 身份证号
     */
    public String getIdcardNum() {
        return idcardNum;
    }

    /**
     * 设置身份证号
     *
     * @param idcardNum 身份证号
     */
    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL_ - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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