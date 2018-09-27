/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.enums.house 
 * @author imart·deng
 * @date 创建时间：2018年8月22日 上午2:04:01
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.house;


/** 
 * @ClassName: HouseEnum 
 * @Description: 房源枚举类型
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:04:01  
 */
public enum HouseEnum {
	PUB_HOUSE,		//公共房源
	SHA_HOUSE,		//共享房源
	PRI_HOUSE,		//私人房源
	;
	
	/**
	 * 根据值获取枚举类型
	 * @param value
	 * @return
	 */
	public static HouseEnum getByOrdinal(int value) {
		for (HouseEnum houseTye : HouseEnum.values()) {
			if(houseTye.ordinal() == value) {
				return houseTye;
			}
		}
		return null;
	}
}
