package com.aifeng.ddrent.core.common.utils.io.aliOSS;

import com.aliyun.oss.OSSException;

public class BaseAliOSSAdaptor {
    protected static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    protected static String accessKeyId = "accessKeyId";
    protected static String accessKeySecret = "accessKeySecret";
    protected static String bucketName = "bucketName";

    protected static String getOssMessage(OSSException oe){
        if(null != oe){
            return System.out.format("Caught an OSSException, which means your request made it to OSS, " +
                    "but was rejected with an error response for some reason.\n" +
                    "Error Message: \t%s\n" +
                    "Error Code: \t%s\n" +
                    "Request ID: \t%s\n" +
                    "Host ID: \t%s\n", oe.getMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getRequestId(), oe.getHostId( )).toString();
        }
        return "";
    }

}
