package com.aifeng.ddrent.common.util.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;


/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2017年12月29日 下午3:52:37
* @version 1.0
* @since
* 
*/
public class KeyPairUtil {

	public static void generateKeyPair(String basePath) {
	
		/** 基于RSA算法生成公私钥对*/
		KeyPairGenerator keyPairGen = null;
		
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		/** 初始化密钥对*/
		keyPairGen.initialize(1024, new SecureRandom());
		
		//生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		//私钥
		PrivateKey privateKey = keyPair.getPrivate();
		
		//公钥
		PublicKey pubKey = keyPair.getPublic();
		
		FileWriter pubfw = null;
		FileWriter prifw = null;
		BufferedWriter pubbw = null;
		BufferedWriter pribw = null;
		try {
			//公钥base64
			String pubKeyStr = new String(Base64.encode(pubKey.getEncoded()));
			//私钥base64
			String priKeyStr = new String(Base64.encode(privateKey.getEncoded()));
			
			// 将密钥对写入到文件
			pubfw = new FileWriter(basePath + "/publicKey.keystore");
			
			prifw = new FileWriter(basePath + "/privateKey.keystore");
			
			pubbw = new BufferedWriter(pubfw);
			
			pribw = new BufferedWriter(prifw);
			
			pubbw.write(pubKeyStr);
			
			pribw.write(priKeyStr);
			
			pubbw.flush();
			
			pubfw.flush();
			
			pribw.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != pubbw) {
					pubbw.close();
				}
				if(null != pubfw ) {
					pubfw.close();
				}
				if(null != pribw) {
					pribw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		generateKeyPair("d:/test/key");
	}
	
}
