/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.enums.user 
 * @author imart·deng
 * @date 创建时间：2018年9月26日 上午12:15:33
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.user;

/** 
 * @ClassName: UserActiveEnum 
 * @Description: 用户激活状态
 * @author: imart·deng
 * @date: 2018年9月26日 上午12:15:33  
 */
public enum UserActiveEnum {
	UNACTIVE,		//未激活
	ACTIVE,			//已激活
	FORBDDIEN,		//已经被禁止
	;
	
	/**
	 * 根据 value 值获取枚举对象
	 * @param value
	 * @return
	 */
	public static UserActiveEnum getByValue(int value) {
	
		for (UserActiveEnum item : UserActiveEnum.values()) {
			if(item.ordinal() == value) {
				return item;
			}
		}
		return null;
	}
}
