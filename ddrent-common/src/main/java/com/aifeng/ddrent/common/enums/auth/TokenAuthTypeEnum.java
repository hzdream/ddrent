/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.enums.auth 
 * @author imart·deng
 * @date 创建时间：2018年9月26日 下午7:19:00
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.auth;

/** 
 * @ClassName: TokenAuthTypeEnum 
 * @Description: token 授权类型
 * @author: imart·deng
 * @date: 2018年9月26日 下午7:19:00  
 */
public enum TokenAuthTypeEnum {

	WEB_LOGIN,		//网页登陆授权
	WX_LOGIN,		//微信登陆授权
	;
	
	/**
	 * 根据值获取枚举对象
	 * @param value
	 * @return
	 */
	public static TokenAuthTypeEnum getByValue(int value) {
		for (TokenAuthTypeEnum item : TokenAuthTypeEnum.values()) {
			if(item.ordinal() == value)
				return item;
		}
		return null;
	}
}
