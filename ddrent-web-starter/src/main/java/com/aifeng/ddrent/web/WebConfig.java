/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:40:20
 * @version 1.0
 */
package com.aifeng.ddrent.web;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 
 * @ClassName: Webconfig
 * @Description: SpringMvc 配置
 * @author: imart·deng
 * @date: 2018年8月13日 下午2:40:20  
 */
@EnableCaching
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//设置默认首页
		registry.addViewController("/").setViewName("/welcome");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registry.addViewController("").setViewName("/welcome");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
