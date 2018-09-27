package com.aifeng.ddrent.common.util.data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2018年1月2日 下午4:37:33
* @version 1.0
* @since
* 
*/
public class EncryUtil {
	private static final Logger log = LoggerFactory.getLogger(EncryUtil.class);
	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(TreeMap<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}

	/**
	 * 对易宝支付返回的结果进行验签
	 * 
	 * @param data
	 *            易宝支付返回的业务数据密文
	 * @param encrypt_key
	 *            易宝支付返回的对ybAesKey加密后的密文
	 * @param yibaoPublickKey
	 *            易宝支付提供的公钥
	 * @param merchantPrivateKey
	 *            商户自己的私钥
	 * @return 验签是否通过
	 * @throws Exception
	 */
	public static boolean checkDecryptAndSign(String data, String encrypt_key,
			String yibaoPublickKey, String merchantPrivateKey) throws Exception {

		return checkSign(data, encrypt_key, yibaoPublickKey, merchantPrivateKey).isSuccess();
	}

	public static BaseResult<Map<String, String>> checkSign(String data, String encryptKey, String clientPubKey, String serverPrivateKey)
			throws IOException {
		
		BaseResult<Map<String, String>> result = new BaseResult<>();
		
		/** 1.使用YBprivatekey解开aesEncrypt。 */
		String AESKey = "";
		try {
			AESKey = RSA.decrypt(encryptKey, serverPrivateKey);
		} catch (Exception e) {
			e.printStackTrace();
			/** AES密钥解密失败 */
			log.error(e.getMessage(), e);
			return result;
		}

		/** 2.用aeskey解开data。取得data明文 */
		String realData = AES.decryptFromBase64(data, AESKey);
		
		/** json to map */
		Gson gson = new Gson();
		Type type = new TypeToken<TreeMap<String, Object>>(){}.getType();
		TreeMap<String, String> map = gson.fromJson(realData, type);
				
		/** 3.取得data明文sign。 */
		String sign = StringUtils.trimToEmpty(map.get("sign"));

		/** 4.对map中的值进行验证 */
		StringBuffer signData = new StringBuffer();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();

			/** 把sign参数隔过去 */
			if ("sign".equals((String) entry.getKey())) {
				continue;
			}
			signData.append(entry.getValue() == null ? "" : entry.getValue());
		}
		
		/** 移除sign签名字段*/
		map.remove("sign");
		result.setData(new DataContainer<>(map));
		
		/** 5. result为true时表明验签通过 */
		boolean res = RSA.checkSign(signData.toString(), sign,
				clientPubKey);
		result.setSuccess(res);

		return result;
	}

	/**
	 * 生成hmac
	 */
	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String hmacTemp = sbuffer.toString();

		String hmac = "";
		if (StringUtils.isNotEmpty(hmacKey)) {
			hmac = Digest.hmacSHASign(hmacTemp, hmacKey, Digest.ENCODE);
		}
		return hmac;
	}
}
