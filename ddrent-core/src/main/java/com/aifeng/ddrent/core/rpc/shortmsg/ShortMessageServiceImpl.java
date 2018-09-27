/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.captcha 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午11:37:11
 * @version 1.0
 */
package com.aifeng.ddrent.core.rpc.shortmsg;

import org.springframework.stereotype.Service;

import com.aifeng.ddrent.api.sms.ShortMessageService;

/** 
 * @ClassName: MobileCaptchaHttpService 
 * @Description: 手机短信工具
 * @author: imart·deng
 * @date: 2018年9月25日 下午11:37:11  
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

	/* (non-Javadoc)
	 * @see com.aifeng.ddrent.api.sms.ShortMessageService#sendSms(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sendSms(String phone, String text) {
		//TODO 调用 captcha 服务， 需要先租用短信服务
		return true;
	}

}
