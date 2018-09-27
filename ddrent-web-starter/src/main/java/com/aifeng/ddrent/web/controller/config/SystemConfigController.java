/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller.config 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午1:58:23
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.core.common.model.PageBean;
import com.aifeng.ddrent.core.common.utils.conf.SystemConfigManager;
import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;
import com.aifeng.ddrent.core.service.conf.SystemConfigService;
import com.aifeng.ddrent.web.controller.BaseController;

/**
 * @ClassName: SystemConfigController
 * @Description: 系统配置controller
 * @author: imart·deng
 * @date: 2018年9月18日 上午1:58:23
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController extends BaseController {

	private SystemConfigService systemConfigservice;

	@RequestMapping(value = "/reload", method = RequestMethod.POST)
	public Object reloadConfig(String prefix) {
		
		//查询有效配置项目
		SystemConfigManager systemConfigManager = new SystemConfigManager();
		SystemConfigDO systemConfigParams = new SystemConfigDO();
		systemConfigParams.setIsActive(true);
		systemConfigParams.setPreFixKey(prefix);
		PageBean page = new PageBean(PageBean.MIN_PAGE, PageBean.MAX_ROWS);
		BaseResult<SystemConfigDO> systemConfigResult = systemConfigservice.findByParams(systemConfigParams, page);

		//重新加载配置
		systemConfigManager.reload(systemConfigResult.getData().getRows());

		return new BaseResult<>(ErrorCodeEnum.SUCCESS);
	}
}
