package com.aifeng.ddrent.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.aifeng.ddrent.core.CoreConfig;

import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages="com.aifeng.ddrent.core.dao.mapper")
@SpringBootApplication(scanBasePackageClasses= {DdrentApplication.class, CoreConfig.class})
@ImportResource({"classpath:/dubbo/dubbo-reference.xml","classpath:/dubbo/dubbo-init.xml"})
@EnableScheduling
public class DdrentApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DdrentApplication.class, args);
	}
}
