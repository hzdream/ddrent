/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.api.auth.response
 * @author imart·deng
 * @date 创建时间：2018/10/19 10:40
 * @version 1.0
 */
package com.aifeng.ddrent.api.auth.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName: UserTokenInfoDTO
 * @Description: 用户授权信息
 * @author: imart·deng
 * @date: 2018/10/19 10:40 
 *
 */
public class UserTokenInfoDTO implements Serializable {
    private Long id;

    /**
     * 用户号
     */
    private Long userId;

    /**
     * 用户token
     */
    private String accessToken;

    /**
     * 用户登陆ip
     */
    private String loginIp;

    /**
     * 用户角色
     */
    private String userRoles;

    /**
     * 授权类型，WEIXIN,WEB_LOGIN
     */
    private String authType;

    /**
     * 是否激活
     */
    private Boolean isActive;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 外部id
     */
    private String externalId;

    /**
     * token token 类型,0.待完善用户，1.正常用户
     */
    private Integer tokenType;

    /**
     * 用户资源正则列表， “，”分隔
     */
    private String uriRegexp;

    private List<Pattern> uriPattern;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getTokenType() {
        return tokenType;
    }

    public void setTokenType(Integer tokenType) {
        this.tokenType = tokenType;
    }

    public String getUriRegexp() {
        return uriRegexp;
    }

    public void setUriRegexp(String uriRegexp) {
        this.uriRegexp = uriRegexp;
    }

    public List<Pattern> getUriPattern() {
        return uriPattern;
    }

    public void setUriPattern(List<Pattern> uriPattern) {
        this.uriPattern = uriPattern;
    }
}
