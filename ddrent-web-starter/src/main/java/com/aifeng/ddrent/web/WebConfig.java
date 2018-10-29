/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:40:20
 * @version 1.0
 */
package com.aifeng.ddrent.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.validation.Validation;
import javax.validation.Validator;

import com.aifeng.ddrent.api.auth.AuthRPCService;
import com.aifeng.ddrent.web.filter.AuthenticationFilter;
import com.google.code.kaptcha.impl.DefaultKaptcha;
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

import com.google.code.kaptcha.util.Config;

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
	public FilterRegistrationBean authFilter(@Value("${filter.exclude.pattrens}") String excludePatterns, @Autowired AuthRPCService authRPCService) {
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

	/** kaptcha 样式配置 */
	@Bean
	public Config kaptchaConfig() {
		Properties prop = new Properties();
		prop.setProperty("kaptcha.border", "no");
		prop.setProperty("kaptcha.border.color", "105,179,90");
		prop.setProperty("kaptcha.textproducer.font.color", "0,0,255");
		prop.setProperty("kaptcha.textproducer.font.color", "black");
		prop.setProperty("kaptcha.image.width", "80");
		prop.setProperty("kaptcha.textproducer.font.size", "24");
		prop.setProperty("kaptcha.image.height", "30");
		prop.setProperty("kaptcha.session.key", "code");
		prop.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		prop.setProperty("kaptcha.textproducer.char.string", "ade3457hkn");
		prop.setProperty("kaptcha.textproducer.char.length", "4");
		prop.setProperty("kaptcha.textproducer.char.space", "6");
		prop.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		//阴影样式
		prop.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");

		Config config = new Config(prop);

		return config;
	}

	/**
	 * Kaptcha 		工具
	 * @return		DefaultKaptcha
	 */
	@Bean
	public DefaultKaptcha defaultKaptcha() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();

		kaptcha.setConfig(kaptchaConfig());

		return kaptcha;
	}

//	public static void main(String[] args) throws IOException {
//		DefaultKaptcha captchaProducer = defaultKaptcha();
//
//		// 生成验证码文本
//		String captchaText = captchaProducer.createText();
//
//		// 生成校验码图片
//		BufferedImage bi = captchaProducer.createImage(captchaText);
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ImageIO.write(bi, "png", out);
//		// 设置图片校验码
//		String b64 = "data:image/png;base64," + new String(Base64.getEncoder().encode(out.toByteArray()));
//
//		System.out.println(b64);
//	}

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
