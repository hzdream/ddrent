package com.aifeng.ddrent.common.util.data;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2017年12月28日 下午1:46:13
* @version 1.0
* @since
* 
*/
public class MD5Utils {

	private static final int    STREAM_BUFFER_LENGTH = 1024;

    public static MessageDigest getDigest(final String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    public static byte[] md5(String txt) {
        return md5(txt.getBytes());
    }

    public static byte[] md5(byte[] bytes) {
        try {
            MessageDigest digest = getDigest("MD5");
            digest.update(bytes);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5(InputStream is) throws NoSuchAlgorithmException, IOException {
        return updateDigest(getDigest("MD5"), is).digest();
    }

    public static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }

        return digest;
    }
    
    /**
     * string to md5
     * @param plainText
     * @return
     */
    public static String crypt(String plainText) {
    	assert null != plainText : "plianText can't be null.";
    	
    	StringBuffer sb = new StringBuffer();
    	try {
			MessageDigest digest = getDigest("MD5");
			
			digest.update(plainText.getBytes());
			
			byte[] hash = digest.digest();
			
			for(int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					sb.append("0" + Integer.toHexString((0xFF & hash[i])));
				}else {
					sb.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
}
