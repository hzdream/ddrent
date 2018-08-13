/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.configs.system 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午11:32:19
 * @version 1.0
 */
package com.aifeng.ddrent.web.response.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: SystemConfig 
 * @Description: 系统配置文件
 * @author: imart·deng
 * @date: 2018年8月13日 下午11:32:19  
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sys.conf")
//@PropertySource(value="system-config.properties")
public class SystemConfig {

	private String name;
	
	private String auth;
	
	private String createTime;
	
	private String description;
	
	private String version;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
}
