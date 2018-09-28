/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午11:52:47
 * @version 1.0
 */
package com.aifeng.ddrent.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.data.JwtUtil;
import com.aifeng.ddrent.core.common.model.PageBean;
import com.aifeng.ddrent.core.common.utils.conf.SystemConfigManager;
import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;
import com.aifeng.ddrent.core.service.conf.SystemConfigService;

/** 
 * @ClassName: CoreConfig 
 * @Description: 基础服务配置
 * @author: imart·deng
 * @date: 2018年8月13日 下午11:52:47  
 */
@EnableConfigurationProperties
@Configuration
public class CoreConfig {
	@Autowired 
	SystemConfigService systemConfigService;
	
	/**
	 * 加载系统配置
	 * @param systemConfigService
	 * @return
	 */
	@Bean
	public SystemConfigManager getSystemConfig() {
		
		//获取系统配置
		SystemConfigManager systemConfigManager = new SystemConfigManager();
		
		SystemConfigDO systemConfigParams = new SystemConfigDO();
		systemConfigParams.setIsActive(true);
		
		PageBean page = new PageBean(PageBean.MIN_PAGE, PageBean.MAX_ROWS);
		
		BaseResult<SystemConfigDO> systemConfigResult = systemConfigService.findByParams(systemConfigParams, page);
		
		systemConfigManager.init(systemConfigResult.getData().getRows());
		
		//设置 jwt 默认密码
		JwtUtil.init(systemConfigManager.getJwtSecretKey());
		
		return systemConfigManager;
	}
}
