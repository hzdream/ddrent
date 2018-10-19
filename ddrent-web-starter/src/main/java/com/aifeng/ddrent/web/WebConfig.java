/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:40:20
 * @version 1.0
 */
package com.aifeng.ddrent.web;

import java.util.Arrays;

import javax.validation.Validation;
import javax.validation.Validator;

import com.aifeng.ddrent.api.auth.AuthRPCService;
import com.aifeng.ddrent.web.filter.AuthenticationFilter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 
 * @ClassName: Webconfig
 * @Description: SpringMvc 配置
 * @author: imart·deng
 * @date: 2018年8月13日 下午2:40:20  
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//设置默认首页
		registry.addViewController("/").setViewName("/welcome");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registry.addViewController("").setViewName("/welcome");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	/**
	 * 添加hibernate 校验工具
	 * 
	 * @return
	 */
	@Bean
	public Validator validator() {
		return Validation.byProvider(HibernateValidator.class).configure()
				// 快速失败模式
				.addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory().getValidator();
	}
	
	/**
	 * 跨域过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL); // 1
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL); // 2
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL); // 3
		corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "Cookie"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
		return corsConfiguration;
	}

	/**
	 * 授权过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean authFilter(@Value("#{filter.exclude.pattrens}") String excludePatterns, @Autowired AuthRPCService authRPCService) {
		FilterRegistrationBean registration = new FilterRegistrationBean();

		//添加过滤器
		registration.setFilter(new AuthenticationFilter(authRPCService));

		//设置过滤路径，/*所有路径
		registration.addUrlPatterns("/**");
		registration.addInitParameter("excludePatterns", excludePatterns);
		registration.setName("authentication");
		registration.setOrder(1);//设置优先级

		return registration;
	}
	
//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//		return new Jackson2ObjectMapperBuilderCustomizer() {
//			@Override
//			public void customize(Jackson2ObjectMapperBuilder builder) {
//				builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//			}
//		};
//	}
}
