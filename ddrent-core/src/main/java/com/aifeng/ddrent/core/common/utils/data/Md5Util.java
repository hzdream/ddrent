/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.core.common.utils.data
 * @author imart·deng
 * @date 创建时间：2018/10/26 11:18
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.utils.data;

import com.aifeng.ddrent.core.common.utils.conf.SystemConfigManager;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @ClassName: Md5Util
 * @Description: Md5工具包
 * @author: imart·deng
 * @date: 2018/10/26 11:18 
 *
 */
public class Md5Util {

    /**
     * Md5编码
     * @param plainTxt
     * @return
     */
    public static String decode(String plainTxt){
        if(null == plainTxt) return null;
        return decode(plainTxt.getBytes());
    }

    public static String decode(byte[] bytes){
        return Md5Crypt.md5Crypt(bytes, SystemConfigManager.getMd5Key());
    }

    public static void main(String[] args) {
        String password = "123abc";

        System.out.println(decode(password));
    }

}
