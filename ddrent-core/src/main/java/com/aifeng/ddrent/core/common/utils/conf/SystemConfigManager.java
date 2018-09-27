/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.utils.conf 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午11:56:07
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.utils.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;

/** 
 * @ClassName: SystemConfigManager 
 * @Description: 系统配置管理器
 * @author: imart·deng
 * @date: 2018年9月17日 下午11:56:07  
 */
public class SystemConfigManager {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigManager.class);

	/** 配置项容器  */
	private static Map<String, Map<String, SystemConfigDO>> prefixProperties;
	
	/** 全匹配容器配置项容器  */
	private static Map<String, SystemConfigDO> fullKeyProperties;
	
	/** 配置项目前缀容器 */
	private static List<String> prefixLis;
	
	/** 配置项容量 */
	private static int size = 0;
	
	private static final String DEFAULT_VALUE = "";
	
	/**
	 * 初始化配置
	 * @param systemConfigLis
	 */
	public void init(List<SystemConfigDO> systemConfigLis) {
		reload(systemConfigLis, true);
	}
	
	/**
	 * 重新装载配置
	 * @param systemConfigLis
	 */
	public void reload(List<SystemConfigDO> systemConfigLis) {
		reload(systemConfigLis, false);
	};
	
	/**
	 * 重新装载配置
	 * @param systemConfigLis	系统配置项
	 * @param clear				 是否清空表
	 */
	private void reload(List<SystemConfigDO> systemConfigLis, boolean clear) {
		assert null != systemConfigLis : "systemConfigLis can't be null.";
		
		synchronized(prefixProperties) {
			logger.debug("[系统配置重新加载] 开始");
			
			if(null == prefixProperties || clear) {
				prefixProperties = new HashMap<>(systemConfigLis.size());
				fullKeyProperties = new HashMap<>(systemConfigLis.size());
			}
			
			String prefix = null;
			Map<String, SystemConfigDO> childMap = null;
			for (SystemConfigDO systemConfig : systemConfigLis) {
				prefix = systemConfig.getPreFixKey();
				childMap = prefixProperties.get(prefix);
				if(null == childMap) {
					childMap = new HashMap<>();
					prefixProperties.put(prefix, childMap);
					prefixLis.add(prefix);
				}
				
				childMap.put(systemConfig.getKey(), systemConfig);
				fullKeyProperties.put(prefix, systemConfig);
			}
			
			size = fullKeyProperties.size();
			logger.debug("[系统配置重新加载] 结束");
		}
		
	}
	
	/**
	 * 判断配置是否为空
	 * @return
	 */
	public static boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 获取容器配置大小
	 * @return
	 */
	public static int size() {
		return size;
	}

	/**
	 * 获取所有的前缀列表
	 * @return the prefixLis
	 */
	public static List<String> getPrefixLis() {
		return prefixLis;
	}
	
	//默认系统配置前缀
	public static final String SYSTEM_PREFIX = "system.";
	/**
	 * 获取系统配置键值,找不到的时候返回""字符串
	 * @param key
	 * @return
	 */
	public static String getSystemConfigValue(String key) {
		return getConfigValue(SYSTEM_PREFIX, key);
	}
	
	/**
	 * 获取系统配置键值对象
	 * @param key
	 * @return
	 */
	public static SystemConfigDO getSystemConfig(String key) {
		return getConfig(SYSTEM_PREFIX, key);
	}
	
	//默认系统配置前缀
	public static final String USER_PREFIX = "user.";
	/**
	 * 获取用户配置键值
	 * @param key
	 * @return
	 */
	public static String getUserConfigValue(String key) {
		return getConfigValue(USER_PREFIX, key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static SystemConfigDO getUserConfig(String key) {
		return getConfig(USER_PREFIX, key);
	}
	
	/**
	 * 获取指定prefix + key 的配置值
	 * @param prefix
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String prefix, String key) {
		SystemConfigDO systemConfig = getConfig(prefix, key);
		if(null != systemConfig)
			return systemConfig.getValue();
		
		return DEFAULT_VALUE;
	}
	
	/**
	 * 获取指定prefix + key 的配置对象
	 * @param prefix
	 * @param key
	 * @return
	 */
	public static SystemConfigDO getConfig(String prefix, String key) {
		assert null != key : "key can't be null.";
		assert null != prefix : "prefix can't be null.";
		
		Map<String, SystemConfigDO> temMap = prefixProperties.get(prefix);
		if(null != temMap)
			return temMap.get(key);
		
		return null;
	}
	
	/**
	 * 全名匹配获取配置值
	 * @param fullName
	 * @return
	 */
	public static String getConfigValueByFullName(String fullName) {
		assert null != fullName : "fullName can't be null";
		
		SystemConfigDO sysconfig = fullKeyProperties.get(fullName);
		
		if(null != sysconfig)
			return sysconfig.getValue();
		
		return DEFAULT_VALUE;
	}
	
	/**
	 * 全名匹配获取配置对象
	 * @param fullName
	 * @return
	 */
	public static SystemConfigDO getConfigByFullName(String fullName) {
		assert null != fullName : "fullName can't be null";
		return fullKeyProperties.get(fullName);
	}

	public static final String JWT_SECRET_KEY = "jwt.hmacal.key";
	/**
	 * @return
	 */
	public String getJwtSecretKey() {
		return getSystemConfigValue(JWT_SECRET_KEY);
	}
}
