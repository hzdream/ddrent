/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.controller.auth.response
 * @author imart·deng
 * @date 创建时间：2018/10/29 13:48
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.auth.response;

/**
 * @ClassName: CaptchaImageResponse
 * @Description: 图片校验码返回
 * @author: imart·deng
 * @date: 2018/10/29 13:48 
 *
 */
public class CaptchaImageResponse {

    // 校验码id
    private Long id;

    // 图片Base64编码
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
