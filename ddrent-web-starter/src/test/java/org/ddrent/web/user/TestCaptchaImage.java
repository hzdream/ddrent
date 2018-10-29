/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: org.ddrent.web.captcha
 * @author imart·deng
 * @date 创建时间：2018/10/29 16:44
 * @version 1.0
 */
package org.ddrent.web.user;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.data.GsonUtil;
import com.aifeng.ddrent.web.DdrentApplication;
import com.aifeng.ddrent.web.controller.auth.UserController;
import com.aifeng.ddrent.web.controller.auth.response.CaptchaImageResponse;
import org.ddrent.web.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: TestCaptchaImage
 * @Description: 校验码图片
 * @author: imart·deng
 * @date: 2018/10/29 16:44 
 *
 */
@SpringBootTest(classes= DdrentApplication.class)
public class TestCaptchaImage extends BaseTest {

    @Autowired
    private UserController userController;

    /**
     * 测试校验码生成
     */
    @Test
    public void testCreateImage(){
        BaseResult<CaptchaImageResponse> result =  userController.createCaptchaImage();

        System.out.println(GsonUtil.gson().toJson(result));

        if(result.isSuccess()){
            System.out.println(String.format("校验码id为 %s", result.getData().getObj().getId()));
            System.out.println(String.format("校验码图片数据为:\n%s", result.getData().getObj().getImage()));
        }
    }
}
