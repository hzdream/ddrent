/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.enums.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:43:06
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.auth;

/** 
 * @ClassName: UserRoleEnum 
 * @Description: 用户角色 
 * <br/><strong>Tips:</trong>Enum字段位置顺序不能改变 
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:43:06  
 */
public enum UserRoleLevelEnum {

	SUPER_MANAGER(0, "超级管理员"),
	MANAGER(0, "管理员"),
	TENANT(1, "租客"),
	AGENT(1, "经纪人"),
	LANDLORD(1, "房东"),
	APARTMENT_MANAGER(1, "公寓管理员"),
	TOURIST(2, "游客"),
	;
	
	private int level;
	private String name;
	
	/**
	 * 根据值获取枚举类型
	 * @param value
	 * @return
	 */
	public static UserRoleLevelEnum getByOrdinal(int value) {
		for (UserRoleLevelEnum userRole : UserRoleLevelEnum.values()) {
			if(userRole.ordinal() == value) {
				return userRole;
			}
		}
		return null;
	}
	
	/**
	 * @param level 角色分级
	 * @param name	角色名称
	 */
	private UserRoleLevelEnum(int level, String name) {
		this.level = level;
		this.name = name;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

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
	
	
}
