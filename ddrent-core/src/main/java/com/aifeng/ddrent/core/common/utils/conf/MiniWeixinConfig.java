/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.utils.conf 
 * @author imart·deng
 * @date 创建时间：2018年9月26日 上午1:08:04
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.utils.conf;

import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;

/** 
 * @ClassName: WeixinConfig 
 * @Description: 微信小程序配置
 * @author: imart·deng
 * @date: 2018年9月26日 上午1:08:04  
 */
public class MiniWeixinConfig {
	/** 用户配置前缀 */
	private static final String PREFIX = "mini.wx.";
	
	/** 微信小程序 id */
	private static final String APPID = "appId";
	
	/** 微信小程序 密钥 */
	private static final String SECRET = "secretKey";
	
	/**
	 * 获取用户配置对象
	 * @param key
	 * @return
	 */
	public static  SystemConfigDO getConfig(String key) {
		return SystemConfigManager.getConfig(PREFIX, key);
	}
	
	/**
	 * 获取用户配置值
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key) {
		return SystemConfigManager.getConfigValue(PREFIX, key);
	}

	/**
	 * 获取微信小程序id
	 * @return
	 */
	public static String getAppId() {
		return SystemConfigManager.getConfigValue(PREFIX, APPID);
	}

	/**
	 * 获取微信小程序密钥
	 * @return
	 */
	public static Object getSecret() {
		return SystemConfigManager.getConfigValue(PREFIX, SECRET);
	}
	
}
