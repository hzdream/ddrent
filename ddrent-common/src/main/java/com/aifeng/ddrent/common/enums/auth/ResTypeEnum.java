/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.common.enums.auth
 * @author imart·deng
 * @date 创建时间：2018/10/19 13:46
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.auth;

/**
 * @ClassName: ResTypeEnum
 * @Description: 资源类型枚举
 * @author: imart·deng
 * @date: 2018/10/19 13:46 
 *
 */
public enum ResTypeEnum {
    NORMAL,     // 正常资源
    REGEXP,     // 正则资源
    ;

    /**
     * 根据值获取枚举对象
     * @param value
     * @return
     */
    public static ResTypeEnum getByValue(int value) {
        for (ResTypeEnum item : ResTypeEnum.values()) {
            if(item.ordinal() == value)
                return item;
        }
        return null;
    }

}
