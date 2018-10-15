package com.aifeng.ddrent.core.common.utils.io.aliOSS;

import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class AliOSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliOSSUtil.class);

    // 文件夹连接路径
    private static final String FOLDER_CONCAT = "/";

    //匹配是否以 / 结尾
    private static final String FOLDER_CHAR_REGEXP = ".*/$";

    // 访问域名
    private static String ENDPOINT;

    // 身份id
    private static String ACCESS_KEY_ID;

    // 身份密钥
    private static String ACCESS_KEY_SECRET;

    // 空间名称
    private static String BUCKET_NAME;

    /**
     * 初始化OSS配置，为了账号密码安全性， 配置统一由配置中心注入
     * @param endpoint          访问域名
     * @param accessKeyId       身份id
     * @param accessKeySecret   身份密钥
     */
    public static void init(String endpoint, String accessKeyId, String accessKeySecret, String defaultBucketName){
        assert StringUtils.isNotBlank(endpoint)
                && StringUtils.isNotBlank(accessKeyId)
                && StringUtils.isNotBlank(accessKeySecret)
                && StringUtils.isNotBlank(defaultBucketName)
                : "\"endpoint、accessKeyId、accessKeySecret、defaultBucketName\" can't be empty, please check it!";
        ENDPOINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = defaultBucketName;
    }

    /**
     *  create the OSS
     * @return OSS object or throws.
     */
    public static OSS createOSSClient() {
        assert StringUtils.isNotBlank(ENDPOINT)
                && StringUtils.isNotBlank(ACCESS_KEY_ID)
                && StringUtils.isNotBlank(ACCESS_KEY_SECRET)
                && StringUtils.isNotBlank(BUCKET_NAME)
                : "\"ENDPOINT、ACCESS_KEY_ID、ACCESS_KEY_SECRET、BUCKET_NAME\" can't be empty, please invoke init method first!";

        return new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建OSS ，并检测bucketName是否存在，如果不存在则先创建bucket
     * @param bucketName
     * @return OSS object or throws.
     */
    public static OSS createOSSClientWithBucket(String bucketName) {
        OSS client = createOSSClient();

        if(!client.doesBucketExist(bucketName)) client.createBucket(bucketName);

        return client;
    }

    /**
     * 创建空间
     * @param bucketName        空间名称
     * @return Bucket or null
     */
    public static Bucket createBucket(String bucketName){
        if(StringUtils.isBlank(bucketName)) return null;

        OSS client = null;
        try{
            client = createOSSClientWithBucket(bucketName);

            return client.getBucketInfo(bucketName).getBucket();
        } catch (OSSException oe) {
            ossExceptionLog(oe, "创建空间", bucketName);
        } catch (ClientException ce) {
            clientExceptionLog(ce, "创建空间", bucketName);
        }catch (Exception e){
            logger.error("[创建空间] 异常， 参数：[{}], 异常原因{}", bucketName, e);
        }finally {
            shutdownOSS(client);
        }
        return null;
    }

    /**
     * bucket 默认
     * @param folderName    文件夹名称
     * @see AliOSSUtil#createFolder(String, String)
     * @return true or false
     */
    public static boolean createFolder(String folderName){
        return createFolder(folderName, BUCKET_NAME);
    }

    /**
     * 创建虚拟文件夹，以“/”结尾
     * @param folderName        文件夹名称
     * @param bucketName        文件夹所属空间
     * @return true or false
     */
    public static boolean createFolder(String folderName, String bucketName){
        if(StringUtils.isBlank(folderName)) return false;

        OSS client = null;
        try {
            folderName = folderName.matches(FOLDER_CHAR_REGEXP) ? folderName : folderName + FOLDER_CONCAT;
            client = createOSSClientWithBucket(bucketName);

            client.putObject(bucketName, folderName, new ByteArrayInputStream(new byte[0]));

            return true;
        } catch (OSSException oe) {
            ossExceptionLog(oe, "创建文件夹", folderName);
        } catch (ClientException ce) {
            clientExceptionLog(ce, "创建文件夹", folderName);
        }catch (Exception e){
            logger.error("[创建文件夹] 未知异常， 参数：[{}], 异常原因{}", folderName, e);
        }finally {
            shutdownOSS(client);
        }

        return false;
    }

    /**
     * 上传文件
     * @param key           文件远端路径名
     * @param filePath      文件本地路径
     */
    public static boolean uploadFile(String key, String filePath) {
        return uploadFile(BUCKET_NAME, key, filePath);
    }

    /**
     * 上传文件
     * @param bucketName        文件空间名
     * @param key               文件远端路径名
     * @param filePath          文件本地路径
     */
    public static boolean uploadFile(String bucketName, String key, String filePath) {
        if(StringUtils.isBlank(filePath)) return false;

        File file = new File(filePath);

        return uploadFile(bucketName, key, file);
    }

    /**
     * 上传文件
     * @param key       文件路径名
     * @param file      文件对象
     */
    public static boolean uploadFile(String key, File file) {
        return uploadFile(BUCKET_NAME, key, file);
    }

    /**
     * 上传文件
     * @param bucketName    空间名称
     * @param key           文件路径
     * @param file          文件对象
     * @see AliOSSUtil#uploadStream(String, String, InputStream)
     */
    public static boolean uploadFile(String bucketName, String key, File file) {
        if(null == file || !file.isFile()) return false;
        try {
            return uploadStream(bucketName, key, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("[上传文件] 异常， 请求参数[{}], 错误原因", "{\"bucketName\":\"" +
                    bucketName +
                    "\",\"key\":\"" +
                    key +
                    "\"}", e.getMessage());
        }
        return false;
    }

    /**
     * 上传文件流
     * @param key       文件路径
     * @param is        文件流对象
     * @return true 请求发送成功，false 请求异常
     */
    public static boolean uploadStream (String key, InputStream is) {
        return uploadStream(BUCKET_NAME, key, is);
    }

    /**
     * 上传文件流
     * @param bucketName    空间名
     * @param key           文件路径
     * @param is            文件流对象
     * @return  true 请求发送成功，false 请求异常
     */
    public static boolean uploadStream (String bucketName, String key, InputStream is) {

        if(StringUtils.isBlank(key) || null == is) return false;
        bucketName = StringUtils.isBlank(bucketName) ? BUCKET_NAME : bucketName;

        OSS client = null;
        try {
            client = createOSSClientWithBucket(bucketName);

            client.putObject(bucketName, key, is);

            return true;
        } catch (OSSException oe) {
            ossExceptionLog(oe, "上传文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\",\"key\":\"" +
                    key +
                    "\"}");
        } catch (ClientException ce) {
            clientExceptionLog(ce, "上传文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\",\"key\":\"" +
                    key +
                    "\"}");
        }catch (Exception e){
            logger.error("[上传文件] 异常， 参数：[{}], 异常原因{}", "", e);
        }finally {
            shutdownOSS(client);
        }
        return false;
    }

    /**
     * 下载文件
     * @param key           文件路径
     * @return  InputStream instance or null
     */
    public static InputStream download(String key){
        return download(BUCKET_NAME, key);
    }

    /**
     * 下载文件
     * @param bucketName    空间名
     * @param key           文件路径
     * @return  InputStream instance or null
     */
    public static InputStream download(String bucketName, String key){
        if(StringUtils.isBlank(key)) return null;
        bucketName = StringUtils.isBlank(bucketName) ? BUCKET_NAME : bucketName;

        OSS client = null;
        try {
            client = createOSSClientWithBucket(bucketName);

            OSSObject ossObject = client.getObject(bucketName, key);

            InputStream inputStream = ossObject.getObjectContent();
            if(null != inputStream){
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;
                        System.out.println("\n" + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return ossObject.getObjectContent();
        } catch (OSSException oe) {
            ossExceptionLog(oe, "下载文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\",\"key\":\"" +
                    key +
                    "\"}");
        } catch (ClientException ce) {
            clientExceptionLog(ce, "下载文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\",\"key\":\"" +
                    key +
                    "\"}");
        }catch (Exception e){
            logger.error("[下载文件] 异常， 参数：[{}], 异常原因{}", "", e);
        }finally {
            shutdownOSS(client);
        }

        return null;
    }

    /**
     * 列举文件
     * @param bucketName
     * @return
     */
    public static ObjectListing listBucket(String bucketName){
        bucketName = StringUtils.isBlank(bucketName) ? BUCKET_NAME : bucketName;
        OSS client = null;
        try {
            client = createOSSClientWithBucket(bucketName);

            return client.listObjects(bucketName);
        } catch (OSSException oe) {
            ossExceptionLog(oe, "列举文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\"}");
        } catch (ClientException ce) {
            clientExceptionLog(ce, "列举文件", "{\"bucketName\":\"" +
                    bucketName +
                    "\"}");
        }catch (Exception e){
            logger.error("[列举文件] 异常， 参数：[{}], 异常原因{}", "", e);
        }finally {
            shutdownOSS(client);
        }
        return null;
    }

    public static void main(String[] args) {
//        String regexp = ".*/$";
//        regexp = "(.|\\n)*";
//        String str = "jsal;df/";
//
//        System.out.println(str.matches(regexp));

        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "ddrentimages1";

        // file content
        String objectName = "test.txt";
        String content = "Hello OSS";

        File file = new File("D:\\test\\images\\ddrent.jpg");

        init(endpoint, accessKeyId, accessKeySecret, bucketName);

//        uploadFile("hangzhou2/xixi/ddrent.jpg", file);

//        uploadStream(objectName, new ByteArrayInputStream(content.getBytes()));

        download(objectName);

//        if(null != inputStream){
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            try {
//                while (true) {
//                    String line = reader.readLine();
//                    if (line == null) break;
//                    System.out.println("\n" + line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 获取bucket 列表
     * @return
     */
    public static List<String> getList(){

        return null;
    }


    /**
     * 关闭oss
     * @param oss
     */
    private static void shutdownOSS(OSS oss) {
        if(null != oss){
            oss.shutdown();
        }
    }

    /**
     * oss 客户端异常日志
     * @param ce    OSS Client exception
     */
    private static void clientExceptionLog(ClientException ce, String name, Object params) {
        logger.error("[{}] 异常, 请求参数[{}], 异常信息：{}", name, params, ce.getMessage());
    }

    /**
     * oss 异常日志
     * @param oe    OSS exception
     */
    private static void ossExceptionLog(OSSException oe, String name, Object params) {
        logger.error("[{}] 异常, 请求参数[{}], 异常信息：{}", name, params, System.out.format(
               " Error Message: \t%s\n" +
                "Error Code: \t%s\n" +
                "Request ID: \t%s\n" +
                "Host ID: \t%s\n", oe.getMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getRequestId(), oe.getHostId( )).toString());
    }
}
