/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: org.ddrent.web 
 * @author imart·deng
 * @date 创建时间：2018年9月17日 下午8:10:23
 * @version 1.0
 */
package org.ddrent.web;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aifeng.ddrent.core.CoreConfig;

/** 
 * @ClassName: BaseTest 
 * @Description: 基础测试
 * @author: imart·deng
 * @date: 2018年9月17日 下午8:10:23  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CoreConfig.class)
public class BaseTest {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
