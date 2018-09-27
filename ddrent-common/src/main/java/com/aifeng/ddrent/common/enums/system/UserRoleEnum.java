/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.enums.system 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午5:23:26
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.system;

/** 
 * @ClassName: UserRoleEnum 
 * @Description: 用户角色信息
 * @author: imart·deng
 * @date: 2018年8月13日 下午5:23:26  
 */
public enum UserRoleEnum {
	TENANT,			//租客
	AGENT,			//经纪人
	LANDLORD,		//房东
	SYSTEM_MANAGER;	//管理员
	
	/**
	 * 获取枚举实际值
	 * @return
	 */
	public int getValue() {
		return this.ordinal();
	}
	
	/**
	 * 获取枚举名
	 * @return
	 */
	public String getName() {
		
		switch(this) {
		case TENANT:
			return "租客";
		case AGENT:
			return "经纪人";
		case LANDLORD:
			return "房东";
		default:
			return "管理员";
		}
		
	}
}
