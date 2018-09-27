/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.schedule.local.conf 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午11:20:36
 * @version 1.0
 */
package com.aifeng.ddrent.core.schedule.local.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.core.common.model.PageBean;
import com.aifeng.ddrent.core.common.utils.conf.SystemConfigManager;
import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;
import com.aifeng.ddrent.core.service.conf.SystemConfigService;

/** 
 * @ClassName: SystemConfigJob 
 * @Description: 系统配置任务
 * @author: imart·deng
 * @date: 2018年9月18日 上午11:20:36  
 */
@Component
public class SystemConfigJob {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigJob.class);
	
	@Autowired 
	SystemConfigService systemConfigService;
	
	/**
	 * 更新配置文件
	 */
	@Scheduled(cron="0 0 0 * * ? ")
	public void reloadSystemConfig() {
		
		try {
			logger.debug("[系统配置定时更新] 开始， 时间{}", System.currentTimeMillis()/1000);
			SystemConfigManager systemConfigManager = new SystemConfigManager();
			
			SystemConfigDO systemConfigParams = new SystemConfigDO();
			systemConfigParams.setIsActive(true);
			
			PageBean page = new PageBean(PageBean.MIN_PAGE, PageBean.MAX_ROWS);
			
			BaseResult<SystemConfigDO> systemConfigResult = systemConfigService.findByParams(systemConfigParams, page);
			
			systemConfigManager.init(systemConfigResult.getData().getRows());
			logger.debug("[系统配置定时更新] 结束， 时间{}", System.currentTimeMillis()/1000);
		} catch (Exception e) {
			logger.debug("[系统配置定时更新] 失败， 失败原因{}", e.getMessage());
		}
	}
}
