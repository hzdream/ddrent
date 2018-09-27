package com.aifeng.ddrent.common.util.http;

import java.io.IOException;
import java.util.Map;

public interface HttpProvider {
	
	/**
	 * 发送get请求,默认超时时间
	 * @param url 请求地址
	 * @return String字符类型结果
	 * @throws Exception
	 */
	public String sendGet(String url) throws Exception;
	
	/**
	 * 发送get请求
	 * @param url 请求url
	 * @param params 请求参数
	 * @param timeout 超时时间
	 * @return
	 */
	public String sendGet(String url, Map<String, Object> params) throws Exception;
	
	/**
	 * 发送get请求，自定义超时时间
	 * @param url
	 * @param timeout 超时时间
	 * @return String字符类型结果
	 * @throws Exception
	 */
	public String sendGet(String url, int timeout) throws Exception;

	/**
	 * 发送post请求
	 * @param url 请求url
	 * @param data 请求参数
	 * @return String字符类型结果
	 * @throws IOException
	 */
	public String sendPost(String url, byte[] data) throws IOException;
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String sendPost(String url, Map<String, Object> params) throws IOException;
	
	/**
	 * 发送post请求，并对value值encode编码
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String sendPostByEncode(String url, Map<String, Object> params) throws IOException;
	
	
	/**
	 * 发送文件
	 * @param url 请求url
	 * @param filename 完整文件地址
	 * @return String字符类型姐u狗
	 * @throws Exception
	 */
	public String sendFile(String url, String filename) throws Exception;
	
	/**
	 * 发送表单参数的post请求
	 * @param url
	 * @param filePath
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String sendFormFile(String url, String filePath, Map<String, String> params) throws Exception;
}
