/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.enums.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月28日 上午1:51:45
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.auth;

/** 
 * @ClassName: TokenTypeEnum 
 * @Description: TODO
 * @author: imart·deng
 * @date: 2018年9月28日 上午1:51:45  
 */
public enum TokenTypeEnum {

	TO_BE_PERFECTED,	// 待完善用户信息token
	NORMAL,				//正常token
	;
	
	/**
	 * 根据值获取枚举对象
	 * @param value
	 * @return
	 */
	public static TokenTypeEnum getByValue(int value) {
		for (TokenTypeEnum item : TokenTypeEnum.values()) {
			if(item.ordinal() == value)
				return item;
		}
		return null;
	}
	
}
