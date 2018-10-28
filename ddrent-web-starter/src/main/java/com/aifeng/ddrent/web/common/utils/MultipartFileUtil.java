/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.common.utils
 * @author imart·deng
 * @date 创建时间：2018/10/28 21:05
 * @version 1.0
 */
package com.aifeng.ddrent.web.common.utils;

import com.aifeng.ddrent.common.enums.io.FileType;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.common.util.io.ImageUtils;
import com.aifeng.ddrent.core.common.utils.io.aliOSS.AliOSSUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MultipartFileUtil
 * @Description: 多附件工具
 * @author: imart·deng
 * @date: 2018/10/28 21:05 
 *
 */
public class MultipartFileUtil {
    public static final Logger logger = LoggerFactory.getLogger(MultipartFileUtil.class);

    public static String uploadImages(MultipartFile multipartFile){
        return uploadMultipartFile(multipartFile, Boolean.TRUE);
    }

    /**
     * 文件上传
     * @param multipartFile     多附件对象
     * @param isImages          true 为图片上传
     * @return 上传成功,则返回文件路径,否则返回null
     */
    public static String uploadMultipartFile (MultipartFile multipartFile, boolean isImages){

        if(null == multipartFile || multipartFile.isEmpty()) return null;
        String newFileName = getNewFileName(multipartFile, isImages);

        try {
            AliOSSUtil.uploadStream(newFileName, multipartFile.getInputStream());
            return newFileName;
        } catch (IOException e) {
            logger.error("[文件上传]失败, 失败原因{}", e.getMessage());
        }
        return  null;
    }

    /**
     * 批量多附件上传
     * @param multipartFiles
     * @return
     */
    public static List<String> uploadImagesByBatch(List<MultipartFile> multipartFiles, boolean isImages){

        if(null == multipartFiles || multipartFiles.isEmpty()) return null;

        OSS client = null;
        try {
            OSS client2 = client = AliOSSUtil.createOSSClient();
            List<String> keys = new ArrayList<>(multipartFiles.size());
            multipartFiles.stream().forEach( multipartFile -> {
                String newFileName = getNewFileName(multipartFile, isImages);
                if(null != newFileName){
                    keys.add(newFileName);
                    InputStream inputStream = null;
                    try {
                        inputStream = multipartFile.getInputStream();
                        client2.putObject(AliOSSUtil.getDefaultBucketName(), newFileName, inputStream);
                    } catch (IOException e) {
                        logger.error("[批量文件上传] 异常", e.getMessage());
                    }finally {
                        if(null != inputStream){
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                logger.error("[批量文件上传] 文件关闭异常", e.getMessage());
                            }
                        }
                    }
                }else{
                    logger.debug("[批量文件上传] 不支持文件类型, ", multipartFile.getOriginalFilename());
                }
            });
            return keys;
        } catch (Exception e){
            logger.error("[批量文件上传] 失败, 失败原因{}", e.getMessage());
        }finally {
            AliOSSUtil.shutdownOSS(client);
        }


        return null;
    }

    /**
     * 校验文件是否有效,并返回文件路径
     * @param multipartFile
     * @param isImages
     * @return
     */
    private static String getNewFileName(MultipartFile multipartFile, boolean isImages) {
        // 获取干净的绝对路径(去除相对路径)
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        //文件后缀命
        FileType[] fileTypes = null;
        if(!isImages) fileTypes = new FileType[]{FileType.PNG, FileType.JPEG, FileType.PSD, FileType.GIF
                , FileType.PDF, FileType.ZIP, FileType.RAR, FileType.XML, FileType.XLS_DOC};
        String suffix = ImageUtils.verifyImageType(fileName, fileTypes);
        return SequenceGeneratorUtil.nextId() + suffix;
    }

}
