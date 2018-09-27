/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.conf 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午11:28:44
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.conf;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.CoreException;
import com.aifeng.ddrent.core.dao.mapper.conf.SystemConfigMapper;
import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;
import com.aifeng.ddrent.core.service.BaseService;

/** 
 * @ClassName: SystemConfigService 
 * @Description: 系统配置项目
 * @author: imart·deng
 * @date: 2018年9月17日 下午11:28:44  
 */
@Service
public class SystemConfigService extends BaseService<SystemConfigDO, SystemConfigMapper> {
	
//	/**
//	 * 精确查询
//	 * @param params
//	 * @param page
//	 * @return
//	 */
//	public List<SystemConfigDO> findByParams(SystemConfigDO params, PageBean page){
//		params = null == params ? new SystemConfigDO() : params;
//		return mapper.selectByExampleAndRowBounds(params, page.toRowBounds());
//	}
	
	/**
	 * 根据主键可选更新系统配置
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(SystemConfigDO record) {
		if(null != record && null != record.getId()) {
			try {
				logger.info("[根据主键可选更新系统配置] 查询调用，请求参数 {}", record.toString());
				return mapper.updateByPrimaryKeySelective(record);
			} catch (Exception e) {
				logger.error("[根据主键可选更新系统配置] 失败，请求参数 {}, 失败原因{}", record.toString(), e.getMessage());
				throw new CoreException(ErrorCodeEnum.UNKNOW_SYSTEM_ERROR);
			}
		}
		return 0;
	}
	
	/**
	 * 根据主键更新系统配置
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(SystemConfigDO record) {
		if(null != record && null != record.getId()) {
			try {
				logger.info("[根据主键更新系统配置] 查询调用，请求参数 {}", record.toString());
				return mapper.updateByPrimaryKey(record);
			} catch (Exception e) {
				logger.error("[根据主键更新系统配置] 失败，请求参数 {}, 失败原因{}", record.toString(), e.getMessage());
				throw new CoreException(ErrorCodeEnum.UNKNOW_SYSTEM_ERROR);
			}
		}
		return 0;
	}

}
