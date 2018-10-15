/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.constant.aliyun.oss 
 * @author imart·deng
 * @date 创建时间：2018年9月28日 下午10:49:16
 * @version 1.0
 */
package com.aifeng.ddrent.common.constant.aliyun.oss;

import java.util.Date;

import com.aifeng.ddrent.common.util.date.DateUtil;

/** 
 * @ClassName: OSSClientConstants 
 * @Description: OSS 默认配置
 * @author: imart·deng
 * @date: 2018年9月28日 下午10:49:16  
 */
public class OSSClientConstants {
	//阿里云API的外网域名
    public static final String ENDPOINT = "xx";
    //阿里云API的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "xx";
    //阿里云API的密钥Access Key Secret
    public static final String ACCESS_KEY_SECRET = "xx";
    //阿里云API的bucket名称
    public static final String BACKET_NAME = "xx";
    //阿里云API的文件夹名称
    public static final String FOLDER = "xx/";
    public static final String FOLDER_VIDEO = "xx/";
    public static final String FORMAT = DateUtil.format(new Date(), "yyyyMMdd");
    public static final String FORMATS = DateUtil.format(new Date(), "yyyyMMddHHmmss");
}
