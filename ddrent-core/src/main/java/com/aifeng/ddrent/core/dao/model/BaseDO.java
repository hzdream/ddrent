/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.dao.model 
 * @author imart·deng
 * @date 创建时间：2018年8月21日 下午11:59:55
 * @version 1.0
 */
package com.aifeng.ddrent.core.dao.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: BaseDO 
 * @Description: 基础DO对象
 * @author: imart·deng
 * @date: 2018年8月21日 下午11:59:55  
 */
public class BaseDO implements Serializable {

	private static final long serialVersionUID = 7399888715583395528L;

	/** 创建时间  */
	Date createTime;
	
	/** 更新时间 */
	Date updateTime;

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
