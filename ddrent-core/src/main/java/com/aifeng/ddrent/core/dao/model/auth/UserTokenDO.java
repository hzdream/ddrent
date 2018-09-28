package com.aifeng.ddrent.core.dao.model.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_user_token")
public class UserTokenDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户号
     */
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 用户token
     */
    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    /**
     * 用户登陆ip
     */
    @Column(name = "LOGIN_IP")
    private String loginIp;

    /**
     * 用户角色
     */
    @Column(name = "USER_ROLES")
    private String userRoles;

    /**
     * 授权类型，WEIXIN,WEB_LOGIN
     */
    @Column(name = "AUTH_TYPE")
    private String authType;

    /**
     * 是否激活
     */
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

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
     * 外部id
     */
    @Column(name = "EXTERNAL_ID")
    private String externalId;
 
    /**
     * token token 类型,0.待完善用户，1.正常用户
     */
    @Column(name = "TOKEN_TYPE")
    private Integer tokenType;

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
     * 获取用户号
     *
     * @return USER_ID - 用户号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户号
     *
     * @param userId 用户号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户token
     *
     * @return ACCESS_TOKEN - 用户token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置用户token
     *
     * @param accessToken 用户token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取用户登陆ip
     *
     * @return LOGIN_IP - 用户登陆ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置用户登陆ip
     *
     * @param loginIp 用户登陆ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取用户角色
     *
     * @return USER_ROLES - 用户角色
     */
    public String getUserRoles() {
        return userRoles;
    }

    /**
     * 设置用户角色
     *
     * @param userRoles 用户角色
     */
    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * 获取授权类型，WEIXIN,WEB_LOGIN
     *
     * @return AUTH_TYPE - 授权类型，WEIXIN,WEB_LOGIN
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * 设置授权类型，WEIXIN,WEB_LOGIN
     *
     * @param authType 授权类型，WEIXIN,WEB_LOGIN
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * 获取是否激活
     *
     * @return IS_ACTIVE - 是否激活
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * @return the tokenType
	 */
	public Integer getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(Integer tokenType) {
		this.tokenType = tokenType;
	}
    
}