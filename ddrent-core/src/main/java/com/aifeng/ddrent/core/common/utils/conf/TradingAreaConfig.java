/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.core.common.utils.conf
 * @author imart·deng
 * @date 创建时间：2018/10/17 9:48
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.utils.conf;

import com.aifeng.ddrent.core.dao.model.conf.SystemConfigDO;

/**
 * @ClassName: TradingAreaConfig
 * @Description: 商圈配置
 * @author: imart·deng
 * @date: 2018/10/17 9:48 
 *
 */
public class TradingAreaConfig {
    /** 商圈配置嗨住前缀 */
    private static final String PREFIX = "trading.are.";

    /** 嗨住配置 */
    private static final String HIZHU = "hizhu";
    public static final String HIZHU_DOMAIN = HIZHU +".domain";
    public static final String HIZHU_REGIONS = HIZHU +".regions";

    /**
     * 获取商圈配置对象
     * @param key
     * @return
     */
    public static SystemConfigDO getConfig(String key) {
        return SystemConfigManager.getConfig(PREFIX, key);
    }

    /**
     * 获取用户配置值
     * @param key
     * @return
     */
    public static String getConfigValue(String key) {
        return SystemConfigManager.getConfigValue(PREFIX, key);
    }

    /**
     * 获取微信小程序id
     * @return
     */
    public static String getHizhuDomain() {
        return SystemConfigManager.getConfigValue(PREFIX, HIZHU_DOMAIN);
    }

    /**
     * 获取微信小程序密钥
     * @return
     */
    public static String getHizhuRegions() {
        return SystemConfigManager.getConfigValue(PREFIX, HIZHU_REGIONS);
    }

}
