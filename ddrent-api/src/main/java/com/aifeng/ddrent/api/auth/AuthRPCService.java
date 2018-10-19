package com.aifeng.ddrent.api.auth;

import com.aifeng.ddrent.api.auth.response.UserAuthInfoDTO;

public interface AuthRPCService {

    /**
     *  根据accessToken 获取用户信息，和资源列表
     * @param accessToken
     * @return 返回UserAuthInfoDTO 对象，当返回结果为null时候，说明accessToken无效,或者用户已经被停用
     */
    public UserAuthInfoDTO authByToken(String accessToken);
}
