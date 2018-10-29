package com.aifeng.ddrent.core.dao.model.captcha;

import java.util.Date;
import javax.persistence.*;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_captcha")
public class CaptchaDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接收方
     */
    @Column(name = "TO_")
    private String to;

    /**
     * 验证码
     */
    @Column(name = "CAPTCHA_")
    private String captcha;

    /**
     * 是否激活，0失效，1激活
     */
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    /**
     * 剩余可验证次数
     */
    @Column(name = "TIMES_CHECK")
    private Integer timesCheck;

    /**
     * 业务类型
     */
    @Column(name = "BUSI_TYPE")
    private String busiType;

    /**
     * 额外字段
     */
    @Column(name = "EXTRA_")
    private String extra;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 失效时间
     */
    @Column(name = "INVALID_TIME")
    private Date invalidTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
    /**
     * 验证码类型,0短信，1邮件
     */
    @Column(name = "CAPTCHA_TYPE")
    private Integer captchaType;
    
    /**
     * 禁止时间
     */
    @Column(name = "DELAY_MINUTES")
    private Integer delayMinutes;

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
     * 获取接收方
     *
     * @return TO_ - 接收方
     */
    public String getTo() {
        return to;
    }

    /**
     * 设置接收方
     *
     * @param to 接收方
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * 获取验证码
     *
     * @return CAPTCHA_ - 验证码
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * 设置验证码
     *
     * @param captcha 验证码
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * 获取是否激活，0失效，1激活
     *
     * @return IS_ACTIVE - 是否激活，0失效，1激活
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活，0失效，1激活
     *
     * @param isActive 是否激活，0失效，1激活
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取剩余可验证次数
     *
     * @return TIMES_CHECK - 剩余可验证次数
     */
    public Integer getTimesCheck() {
        return timesCheck;
    }

    /**
     * 设置剩余可验证次数
     *
     * @param timesCheck 剩余可验证次数
     */
    public void setTimesCheck(Integer timesCheck) {
        this.timesCheck = timesCheck;
    }

    /**
     * 获取业务类型
     *
     * @return BUSI_TYPE - 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置业务类型
     *
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取额外字段
     *
     * @return EXTRA - 额外字段
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置额外字段
     *
     * @param extra 额外字段
     */
    public void setExtra(String extra) {
        this.extra = extra;
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
     * 获取失效时间
     *
     * @return INVALID_TIME - 失效时间
     */
    public Date getInvalidTime() {
        return invalidTime;
    }

    /**
     * 设置失效时间
     *
     * @param invalidTime 失效时间
     */
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
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
	 * @return the captchaType
	 */
	public Integer getCaptchaType() {
		return captchaType;
	}

	/**
	 * @param captchaType the captchaType to set
	 */
	public void setCaptchaType(Integer captchaType) {
		this.captchaType = captchaType;
	}

	/**
	 * @return the delayMinutes
	 */
	public Integer getDelayMinutes() {
		return delayMinutes;
	}

	/**
	 * @param delayMinutes the delayMinutes to set
	 */
	public void setDelayMinutes(Integer delayMinutes) {
		this.delayMinutes = delayMinutes;
	}

    @Override
    public String toString() {
        return "CaptchaDO{" +
                "id=" + id +
                ", to='" + to + '\'' +
                ", captcha='" + captcha + '\'' +
                ", isActive=" + isActive +
                ", timesCheck=" + timesCheck +
                ", busiType='" + busiType + '\'' +
                ", extra='" + extra + '\'' +
                ", createTime=" + createTime +
                ", invalidTime=" + invalidTime +
                ", updateTime=" + updateTime +
                ", captchaType=" + captchaType +
                ", delayMinutes=" + delayMinutes +
                '}';
    }
}