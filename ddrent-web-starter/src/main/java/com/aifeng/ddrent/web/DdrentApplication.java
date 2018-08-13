package com.aifeng.ddrent.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aifeng.ddrent.core.CoreConfig;

@SpringBootApplication(scanBasePackageClasses= {DdrentApplication.class, CoreConfig.class})
public class DdrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdrentApplication.class, args);
	}
}
