/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.enums.house 
 * @author imart·deng
 * @date 创建时间：2018年8月22日 上午2:04:01
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.user;


/** 
 * @ClassName: OriginEnum 
 * @Description: 用户来源
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:04:01  
 */
public enum OriginEnum {
	WEB_REGISTER,	//网页注册
	WX_REGISTER,	//微信注册
	SYS_ADD,		//系统添加
	UNKNOW,			//未知
	;
	
	/**
	 * 根据值获取枚举类型
	 * @param value
	 * @return
	 */
	public static OriginEnum getByOrdinal(int value) {
		for (OriginEnum houseTye : OriginEnum.values()) {
			if(houseTye.ordinal() == value) {
				return houseTye;
			}
		}
		return null;
	}
}
