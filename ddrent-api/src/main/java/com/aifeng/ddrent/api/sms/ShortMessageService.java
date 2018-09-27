/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.api.sms 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午11:49:28
 * @version 1.0
 */
package com.aifeng.ddrent.api.sms;

/** 
 * @ClassName: ShortMessageService 
 * @Description: 短信服务
 * @author: imart·deng
 * @date: 2018年9月25日 下午11:49:28  
 */
public interface ShortMessageService {

	/**
	 * 发送短信服务
	 * @param phone		手机号码
	 * @param text		短信内容
	 * @return
	 */
	public boolean sendSms(String phone, String text);
}
