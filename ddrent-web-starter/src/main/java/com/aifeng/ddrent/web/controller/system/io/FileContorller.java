/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.controller.system.io
 * @author imart·deng
 * @date 创建时间：2018/10/28 16:37
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller.system.io;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.web.common.utils.MultipartFileUtil;
import com.aifeng.ddrent.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @ClassName: FileContorller
 * @Description: 文件控制器
 * @author: imart·deng
 * @date: 2018/10/28 16:37 
 *
 */
@RestController
@RequestMapping("/files")
public class FileContorller extends BaseController {

    /**
     * 单图片上传
     * @param multipartFile     图片副本
     * @return      true 返回文件路径, false 文件上传失败
     */
    @RequestMapping("/image/upload")
    public BaseResult<String> uploadImage(MultipartFile multipartFile){
        // 上传图片
        String path = MultipartFileUtil.uploadImages(multipartFile);

        // 上传成功
        if(null != path) return new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(path));

        // 上传失败
        return new BaseResult<>(ErrorCodeEnum.FILE_UPLOAD_FAILDED);
    }

    /**
     * 多图片文件上传
     * @return
     */
    @RequestMapping("/images/upload")
    public BaseResult<String> uploadImages(){
        List<MultipartFile> multipartFiles = ((MultipartHttpServletRequest) request) .getFiles("file");

        // 上传图片
        List<String> path = MultipartFileUtil.uploadImagesByBatch(multipartFiles, Boolean.TRUE);

        return null;
    }
}
