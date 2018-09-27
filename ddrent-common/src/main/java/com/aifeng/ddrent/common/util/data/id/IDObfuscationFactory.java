/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.data.id 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午11:49:56
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.data.id;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.aifeng.ddrent.common.util.data.RandomUtil;

/** 
 * @ClassName: IDObfuscationFactory 
 * @Description: id混淆器工厂类
 * @author: imart·deng
 * @date: 2018年9月18日 下午11:49:56  
 */
public class IDObfuscationFactory {
	
	private static final Map<Integer, IDObfuscationUtils> instanceMap = new HashMap<>(16);
	
	/**
	 * 默认实例
	 */
	private static IDObfuscationUtils curInstance;
	
	private static volatile int key;
	
	private static final Object mutex = new Object();
	
	/**
	 * 创建或者获取id混淆工具
	 * @return
	 */
	public static IDObfuscationUtils create() {
		IDObfuscationUtils newInstance = null;
		int newKey = 0;
		if(key == 0) {
			synchronized(mutex) {
				if(key == 0) {
					try {
						newKey = key = SecureRandom.getInstanceStrong().nextInt();
					} catch (NoSuchAlgorithmException e) {
						newKey = key = Integer.valueOf(RandomUtil.getRandomNum(6));
					};
					newInstance = curInstance = new IDObfuscationUtils(key);
				}
			}
		}
		if(null == newInstance) {
			try {
				newKey = SecureRandom.getInstanceStrong().nextInt();
			} catch (NoSuchAlgorithmException e) {
				newKey = Integer.valueOf(RandomUtil.getRandomNum(6));
			};
			
			newInstance = new IDObfuscationUtils(newKey);
		}
		
		instanceMap.put(newKey, newInstance);
		return newInstance;
	}
	
	/**
	 * [推荐使用] 创建或者获取id混淆工具
	 * @return
	 */
	public static IDObfuscationUtils get(int key) {
		assert 0 != key : "key can't be 0";
		
		if(null != instanceMap.get(key)) 
			return instanceMap.get(key);
		
		IDObfuscationUtils newInstance = null;
		if(null == curInstance) {
			synchronized(mutex) {
				if(null == curInstance) {
					IDObfuscationFactory.key = key;
					newInstance = curInstance = new IDObfuscationUtils(key);
					instanceMap.put(key, newInstance);
				}
			}
		}
		if(null == newInstance)
			newInstance = new IDObfuscationUtils(key);
		
		return newInstance;
	}
	
	/**
	 * 获取默认id混淆器
	 * @return
	 */
	public static IDObfuscationUtils getDefault() {
		
		if(null != curInstance) {
			return curInstance;
		}else {
			synchronized(mutex) {
				if(key == 0) {
					try {
						key = SecureRandom.getInstanceStrong().nextInt();
					} catch (NoSuchAlgorithmException e) {
						key = Integer.valueOf(RandomUtil.getRandomNum(6));
					};
					curInstance = new IDObfuscationUtils(key);
					instanceMap.put(key, curInstance);
				}
			}
		}
		
		return curInstance;
	}

}
