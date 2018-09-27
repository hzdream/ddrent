/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.weixin 
 * @author imart·deng
 * @date 创建时间：2018年9月26日 上午12:44:34
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.weixin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.model.weixin.mini.MiniWeixinConstant;
import com.aifeng.ddrent.common.util.http.impl.HttpProviderImpl;
import com.aifeng.ddrent.common.util.json.GsonUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.common.model.dto.WeixinSessionDTO;
import com.aifeng.ddrent.core.common.utils.conf.MiniWeixinConfig;
import com.aifeng.ddrent.core.dao.mapper.auth.UserTokenMapper;
import com.aifeng.ddrent.core.dao.model.auth.UserTokenDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: MiniWeixinService 
 * @Description: 微信小程序服务
 * @author: imart·deng
 * @date: 2018年9月26日 上午12:44:34  
 */
@Service
public class MiniWeixinService extends BaseService<UserTokenDO, UserTokenMapper> {
	
	public WeixinSessionDTO code2session(String code) {
		
		if(StringUtils.isNotBlank(code)) {
			
			Map<String, Object> params = new HashMap<>(8);
			params.put("appid", MiniWeixinConfig.getAppId());
			params.put("secret", MiniWeixinConfig.getSecret());
			params.put("js_code", code);
			params.put("grant_type", "authorization_code");
			
			try {
				String result = HttpProviderImpl.getInstance().sendGet(MiniWeixinConstant.CODE2SESSION, params);
				
				WeixinSessionDTO weixinSession = GsonUtil.gson().fromJson(result, WeixinSessionDTO.class);
				
				return weixinSession;
			} catch (Exception e) {
				logger.error("[获取微信小程序session] 失败, 失败原因:{}", e.getMessage());
			}
		}
		
		return null;
	}
}
