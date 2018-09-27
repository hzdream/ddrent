package com.aifeng.ddrent.common.util.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aifeng.ddrent.common.util.security.RSASigner;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class OkHttpOpenApiUtil {
private static final Logger logger = LoggerFactory.getLogger(OkHttpOpenApiUtil.class);
	
	private static final String CONNECT_CHAR = ":";
	
	private Gson gson = new Gson();
	
	private String userToken;
	
	private String accessToken;
	
	/** 连连给的公钥，用于以后做数据验签 */
	public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdKt6vghmmA3PAjCusu3K7dbaTq\n" +
			"fwLj6gkPMCG+s7Fe9Sfrp4o5rkaJq7dTDH3+tWBQ3hlZFwxDCSZENQpOhwiMMiPv\n" +
			"vm/g+unJbkYRGt2KfkEVvNUP/CXtXeyrfT5Gng6LzDfl8Q4W2UAsk2aD+iwlQ2kA\n" +
			"CccbpsTNldc5IGkHQIDAQAB";
	
	/** 电商平台自己的私钥 */
	public static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ0q3q+CGaYDc8CM\n" +
			"K6y7crt1tpOp/AuPqCQ8wIb6zsV71J+unijmuRomrt1MMff61YFDeGVkXDEMJJkQ\n" + 
			"1Ck6HCIwyI+++b+D66cluRhEa3Yp+QRW81Q/8Je1d7Kt9PkaeDovMN+XxDhbZQCy\n" + 
			"TZoP6LCVDaQAJxxumxM2V1zkgaQdAgMBAAECgYBJQjZScL4r1+gYlD23Yhh0sMXN\n" + 
			"xqopaWjimz7SrA29l57gX7BxODqHqghQNrgD8vOaMSzpdgJTCmn0vxdlsCR0QFAi\n" + 
			"PSdYckRKVuTmCI0TcO1dc/7MXhN02UqQ8QNBjiUEmCbVsI24M0hHct6RSvmyNrWC\n" + 
			"61osVwB0C4gHLCmggQJBANBAmqFsuFuTHRAhfxvQjv4cWqM8Neu9CD/yTsg//tRR\n" + 
			"SK9TCrUYiQJQcnLNRNF2lMVIq9ENlzz3F5xsBkFATXUCQQDBM9T1Ps9deHJB+vr1\n" + 
			"9lkxa3FGVYknLyUGCPmbDdIA6N99J/byqkVQBo30GHvHuQd2AJ+ME+zDlZw77P3n\n" + 
			"7t8JAkBrbRgXM206l/4DBzR5lbFdtiSvVez+yUmLKySmuhRrU+7pui2o05pBnlwb\n" + 
			"inX4k3IU/vLa6Wbd5RzB7Rug51nNAkB6yuud14bKPIQ6BT891o3HyCbdcf3Sxrb8\n" + 
			"R/YEo1sGsvtgO2dLwgZ9nzzXmDwLq0DfsfO1fvBrOopq1+xkXcCpAkA0GATDVvFq\n" + 
			"MH+1KtgZkDbxstpBwKFEkZmYrUZwH9wpxVmyVfU4HLU0+Vfe12rTQ9/2wwoRDNpa\n" + 
			"86jvO47ItsfD";

	private static OkHttpOpenApiUtil util;
	
	OkHttpClient client = new OkHttpClient();
	
	private static Object mutex = new Object();
	
	private OkHttpOpenApiUtil() {
		super();
	}
	
	/**
	 * 根据用户名 + 主密钥 实例化 httpUtil工具
	 * @param username 		用户名
	 * @param password		用户主token
	 * @return httpUtil 	工具
	 */
	public static OkHttpOpenApiUtil getInstanceByUserAndToken(String username, String password) {
		if(null == util) {
			if(StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(password)) {
				synchronized(mutex) {
					if(null == util) {
						util = new OkHttpOpenApiUtil();
					}
				}
			}
		}
		
		/**
		 * 更新user userToken
		 */
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			byte[] bytes = (username + CONNECT_CHAR + password).getBytes();
			util.userToken = new String(Base64.getEncoder().encode(bytes));
		}
		
		return util;
	}
	
	/**
	 * 根据accessToken 实例化httputil 工具
	 * @param accessToken	第一次实例化必须
	 * @return	httpUtil工具，注意：如果第一次实例化没有传accessToken, 会得到null
	 */
	public static OkHttpOpenApiUtil getInstanceByAccessToken(String accessToken) {
		if(null == util && StringUtils.isNoneBlank(accessToken)) {
			synchronized(mutex) {
				if(null == util) {
					util = new OkHttpOpenApiUtil();
					util.accessToken = accessToken;
				}
			}
		}
		
		//更新accessToken
		if(null != accessToken) {
			util.accessToken = accessToken;
		}
		
		return util;
	}
	
	/**
	 * 在util 实例化之后快速得到util对象
	 * @return
	 */
	public static OkHttpOpenApiUtil getInstance() {
		return getInstanceByAccessToken(null);
	}

	private static final String BASICAUTH_NAME = "Authorization";
	private static final String LIANLIAN_AUTH = "AIFENG-Signature";
	private static final String BASICAUTH_BEARER = "Bearer";
	private static final String BASICAUTH_BASIC = "Basic";
	private static final String POST = "POST";
	private static final String GET = "GET";
	private static final String AND_CHARACTER = "&";
	
	/**
	 * 获取token
	 * @param url		请求地址
	 * @param params	请求参数，通过Gson.toJson(Object) 转化为json，可以为空
	 * @return
	 */
	public String getToken(String host, String uri, Map<String, String> params) {
		
		long startTime = System.currentTimeMillis();
		logger.info("=====Begin send get to {}, start at {}=====", uri, startTime);
		
		String basicStr = (BASICAUTH_BASIC + " ");
		
		String token = userToken;
		
		String url = host+uri;
		
		try {
			//获取请求包体
			String data = getHtmlParams(params);
			
			Request request = new Request.Builder()
					 .addHeader(BASICAUTH_NAME, basicStr + token)
					 .method("POST", new RequestBody() {
						
						@Override
						public void writeTo(BufferedSink bufferedSink) throws IOException {
							bufferedSink.write(data.getBytes());
						}
						
						@Override
						public MediaType contentType() {
							return MediaType.parse("application/x-www-form-urlencoded");
						}
					}).url(url).build();
			
			return client.newCall(request).execute().body().toString();
		} catch (Exception e) {
			logger.info("请求{}失败----{}", url, System.currentTimeMillis() - startTime);
		}
		return "";
		
	}

	private String getHtmlParams(Map<String, String> params) {
		String data = null;
		if(null != params) {
			StringBuilder sb = new StringBuilder();
			for (Entry<String, String> entry: params.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			data = sb.substring(0, sb.length());
		}
		return data;
	}
	
	/**
	 * app 通过access_token 调用open api 接口
	 * @param url		请求地址
	 * @param params	请求参数，通过Gson.toJson(Object) 转化为json，可以为空
	 * @return
	 */
	public String sendPostByApp(String host,String uri, Object params) {
		return sendPost(host,uri, params, true);
	}
	
	/**
	 * app 通过access_token 调用open api 接口
	 * @param url		请求地址
	 * @param params	请求参数，通过Gson.toJson(Object) 转化为json，可以为空
	 * @param isApp		是否为app调用，app调用为true
	 * @return
	 */
	public String sendPost(String host,String uri, Object params, boolean isApp) {
		long startTime = System.currentTimeMillis();
		logger.info("=====Begin send get to {}, start at {}=====", uri, startTime);
		
		String basicStr = isApp ? (BASICAUTH_BEARER + " ") : (BASICAUTH_BASIC + " ");
		
		String token = isApp ?  accessToken: userToken;
		String url = host+uri;
		try {
			//获取请求包体
			String data = getJsonParams(params);

			URL httpUrl = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) httpUrl.openConnection();
			urlConnection.setRequestMethod(POST);
			urlConnection.setConnectTimeout(10000);
			
			//设置basic auth token
			urlConnection.setRequestProperty(BASICAUTH_NAME, basicStr + token);
			
			Builder builder = new Request.Builder()
					.addHeader(BASICAUTH_NAME, basicStr + token)
					.method("POST", new RequestBody() {
						
						@Override
						public void writeTo(BufferedSink bufferedSink) throws IOException {
							bufferedSink.write(data.getBytes());
						}
						
						@Override
						public MediaType contentType() {
							return MediaType.parse("application/json");
						}
					})
					.url(url);
			if(isApp) {
				//设置签名信息
				String str= POST + AND_CHARACTER + uri + AND_CHARACTER;
				long now= System.currentTimeMillis()/1000;
				str += now + AND_CHARACTER;
				if(null != data) {
					str += data;
				}
				System.out.println("[发送post请求]签名字符串 " + str);
				str = RSASigner.sign("SHA256WithRSA", str, privateKey);
				System.out.println("[RSA签名]签名字符串 " + str);
				urlConnection.setRequestProperty("AIFENG-Signature", "t="+now+",v="+str);
				
				builder.addHeader(LIANLIAN_AUTH, "t="+now+",v="+str);
				
			}

			return client.newCall(builder.build()).execute().body().toString();
		} catch (Exception e) {
			logger.info("请求{}失败----{}", url, System.currentTimeMillis() - startTime);
		}
		
		return "";
	}
	
	private String getJsonParams(Object params) {
		return gson.toJson(params);
	}

	/**
	 * 
	 * @param host
	 * @param uri
	 * @param params
	 * @param isApp
	 * @return
	 */
	public String sendGet(String host, String uri, Object params) {
		long startTime = System.currentTimeMillis();
		logger.info("=====Begin send get to {}, start at {}=====", uri, startTime);
		
		String basicStr = BASICAUTH_BEARER;
		
		String token = accessToken;
		String url = host+uri;
		try {
			//获取请求包体
			String data = getJsonParams(params);

			
			//设置签名信息
			String str= GET + AND_CHARACTER + uri + AND_CHARACTER;
			long now= System.currentTimeMillis()/1000;
			str += now + AND_CHARACTER;
			if(null != data) {
				str += data;
			}
			str = RSASigner.sign("SHA256WithRSA", str, privateKey);
			
			Request request = new Request.Builder()
					 .addHeader(BASICAUTH_NAME, basicStr + token)
					 .addHeader(LIANLIAN_AUTH, "t="+now+",v="+str)
					 .method("POST", new RequestBody() {
						
						@Override
						public void writeTo(BufferedSink bufferedSink) throws IOException {
							bufferedSink.write(data.getBytes());
						}
						
						@Override
						public MediaType contentType() {
							return MediaType.parse("application/x-www-form-urlencoded");
						}
					}).url(url).build();


			return client.newCall(request).execute().body().toString();
		} catch (Exception e) {
			logger.info("请求{}失败----{}", url, System.currentTimeMillis() - startTime);
		}
		
		return "";
	}
}
