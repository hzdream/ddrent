/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.utils.conf 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午1:51:13
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.utils.conf;

import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;

/** 
 * @ClassName: UserConfig 
 * @Description: 用户配置
 * @author: imart·deng
 * @date: 2018年9月18日 上午1:51:13  
 */
public class UserConfig {

	/** 用户配置前缀 */
	private static final String PREFIX = "user.";
	
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
	public static  String getConfigValue(String key) {
		return SystemConfigManager.getConfigValue(PREFIX, key);
	}
	
}
