/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.common.model.auth
 * @author imart·deng
 * @date 创建时间：2018/10/17 14:55
 * @version 1.0
 */
package com.aifeng.ddrent.common.model.auth;

import com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum;
import com.aifeng.ddrent.common.enums.auth.TokenTypeEnum;
import com.aifeng.ddrent.common.util.system.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtToken
 * @Description: jwt token
 * @author: imart·deng
 * @date: 2018/10/17 14:55 
 *
 */
public class JwtToken implements Serializable {
    /**
     * token userId号
     */
    private Long userId;

    /**
     * 登陆用户名称
     */
    private String userName;

    /**
     * 0 
     */
    private TokenTypeEnum tokenType;

    /**
     * 登陆ip
     */
    private String loginIp;
    
    /**
     * @see com.aifeng.ddrent.common.enums.auth.TokenAuthTypeEnum
     */
    private String authType;

    public JwtToken(Long userId, TokenTypeEnum tokenType, String authType) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.authType = authType;
    }

    public JwtToken(Long userId, String userName, TokenTypeEnum tokenType, String loginIp, TokenAuthTypeEnum authType) {
        this.userId = userId;
        this.userName = userName;
        this.tokenType = tokenType;
        this.loginIp = loginIp;
        this.authType = authType.name();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TokenTypeEnum getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenTypeEnum tokenType) {
        this.tokenType = tokenType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Map<String, String> getClaims(){
        Map<String, String> claims = new HashMap<>(8);
        claims.put("userId", String.valueOf(userId));
        claims.put("authType", this.authType);
        claims.put("tokenType", this.tokenType.ordinal() + "");

        if(null != this.loginIp) claims.put("loginIp", this.loginIp);
        if(null != this.userName) claims.put("userName", this.userName);
        return claims;
    }

    public boolean isValidate(){
        return null != userId && StringUtils.isNotBlank(this.authType) && null != tokenType;
    }
}
