/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.auth.request
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午4:11:14
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName: WXRegisterReqeust
 * @Description: 微信注册
 * @author: imart·deng
 * @date: 2018年9月25日 下午4:11:14
 */
public class WXRegisterRequest {

    /**
     * 来源openapi
     */
    private String originOpenId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 电话号码是否保密
     */
    private Boolean contactProtect;

    /**
     * 登陆账号
     */
//    private String loginAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 自我介绍
     */
    private String introduce;

    /**
     * 头像地址
     */
    private String headImgUrl;

    /**
     * 0女，1男
     */
    private Integer sex;

    /** 验证码  */
    @NotBlank(message="验证码不能为空")
    @Size(min=4, max=10, message="验证码不正确")
    private String captcha;

    /** 验证码编号 */
    @NotNull(message="没有找到匹配的验证码，请重新获取验证码")
    private Long captchaId;

    /**
     * @return the originOpenId
     */
    public String getOriginOpenId() {
        return originOpenId;
    }

    /**
     * @param originOpenId the originOpenId to set
     */
    public void setOriginOpenId(String originOpenId) {
        this.originOpenId = originOpenId;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return the contactProtect
     */
    public Boolean getContactProtect() {
        return contactProtect;
    }

    /**
     * @param contactProtect the contactProtect to set
     */
    public void setContactProtect(Boolean contactProtect) {
        this.contactProtect = contactProtect;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce the introduce to set
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * @return the headImgUrl
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * @param headImgUrl the headImgUrl to set
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Long getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(Long captchaId) {
        this.captchaId = captchaId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
