/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: org.ddrent.web.user 
 * @author imart·deng
 * @date 创建时间：2018年9月28日 下午5:24:19
 * @version 1.0
 */
package org.ddrent.web.user;

import org.ddrent.web.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.json.GsonUtil;
import com.aifeng.ddrent.core.dao.model.auth.UserDO;
import com.aifeng.ddrent.core.service.user.UserService;
import com.aifeng.ddrent.web.DdrentApplication;
import com.aifeng.ddrent.web.WebConfig;

/** 
 * @ClassName: UserTest 
 * @Description: TODO
 * @author: imart·deng
 * @date: 2018年9月28日 下午5:24:19  
 */
@SpringBootTest(classes=DdrentApplication.class)
public class UserTest extends BaseTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testFind() {
		
		BaseResult<UserDO> userResult = userService.findByParams(new UserDO(), null);
		
		System.out.format("[用户查询] 返回结果 %s", GsonUtil.gson().toJson(userResult));
	}
}
