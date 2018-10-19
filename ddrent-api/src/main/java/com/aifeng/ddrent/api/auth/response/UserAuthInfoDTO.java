/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.api.auth.response
 * @author imart·deng
 * @date 创建时间：2018/10/18 17:49
 * @version 1.0
 */
package com.aifeng.ddrent.api.auth.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserAuthInfoDTO
 * @Description: 用户授权信息
 * @author: imart·deng
 * @date: 2018/10/18 17:49 
 *
 */
public class UserAuthInfoDTO implements Serializable {
    // 用户基础信息
    private UserInfoDTO userInfoDTO;

    // 用户授权信息
    private UserTokenInfoDTO userTokenInfoDTO;

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    public UserTokenInfoDTO getUserTokenInfoDTO() {
        return userTokenInfoDTO;
    }

    public void setUserTokenInfoDTO(UserTokenInfoDTO userTokenInfoDTO) {
        this.userTokenInfoDTO = userTokenInfoDTO;
    }
}
