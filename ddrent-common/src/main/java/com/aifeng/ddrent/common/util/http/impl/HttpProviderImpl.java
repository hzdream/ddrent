package com.aifeng.ddrent.common.util.http.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aifeng.ddrent.common.util.http.HttpProvider;

/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2017年9月17日 下午1:41:09
* @version 1.0
* @since
* 
*/
public class HttpProviderImpl implements HttpProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpProviderImpl.class);
	
	private static HttpProviderImpl httpProvider;
	
	protected HttpProviderImpl() {
		super();
	}
	
	protected synchronized static void init() {
		if(null == httpProvider) {
			httpProvider = new HttpProviderImpl();
		}
	}
	
	public static HttpProvider getInstance() {
		if(null == httpProvider) {
			init();
		}
		return httpProvider;
	}

	@Override
	public String sendGet(String url) throws Exception {
		return sendGet(url, 10000);
	}

	@Override
	public String sendGet(String url, Map<String, Object> params) throws Exception {
		int timeout = 10000;
		if(null != params) {
			url += "?";
			boolean flag = false;
			for (Map.Entry<String, Object> entry: params.entrySet()) {
				url += entry.getKey() + "=" + entry.getValue() + "&";
				flag = true;
			}
			if(flag) {
				url = url.substring(0, url.length()-1);
			}
		}
		return sendGet(url, timeout);
	}

	@Override
	public String sendGet(String url, int timeout) throws Exception {
		StringBuffer result = new StringBuffer("");
		logger.info("send post method with url: [" + url + "]");
		long startTime = System.currentTimeMillis();
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(timeout);
			int responseCode = con.getResponseCode();
			logger.info("Get return ["+responseCode+"] from "+url);
			
			if(con.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
			logger.info("查询耗时:{}毫秒, 返回结果:{}", (System.currentTimeMillis() - startTime), result.toString());
			
			return result.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			if(in != null)
				in.close();
			try{
				con.getInputStream().close();
			} catch (Throwable e){
				
			}
			try{
				con.getOutputStream().close();
			} catch (Throwable e){
				
			}
			con.disconnect();
		}
	}

	@Override
	public String sendPost(String url, byte[] data) throws IOException {
		HttpURLConnection con = null;
		BufferedReader in = null;
		long startTime = System.currentTimeMillis();
		logger.info("send post method with url: ["+url + "] and the request params is:[" + new String(data) + "]");
		try {
			StringBuffer result = new StringBuffer("");
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			logger.info("Get return ["+responseCode+"] from "+url);
			
			if(con.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
			logger.info("查询耗时:{}毫秒, 返回结果:{}", (System.currentTimeMillis() - startTime), result.toString());
			
			return result.toString();

		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}
			
			if (con != null) {
				try{
					con.getInputStream().close();
				} catch (Throwable e){
					
				}
				try{
					con.getOutputStream().close();
				} catch (Throwable e){
					
				}
				con.disconnect();
			}
		}
	}
	
	@Override
	public String sendPost(String url, Map<String, Object> params) throws IOException {
		StringBuilder sb = new StringBuilder();
		if(null != params) {
			boolean tag = false;
			for (Map.Entry<String, Object> entry: params.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				tag = true;
			}
			if(tag) {
				System.out.println("请求url内容为：" + url);
				System.out.println("请求内容为：" + sb.toString());
				return sendPost(url, sb.substring(0, sb.length()).getBytes());
			}
		}
		return sendPost(url, sb.toString().getBytes());
	}
	
	@Override
	public String sendPostByEncode(String url, Map<String, Object> params) throws IOException {
		StringBuilder sb = new StringBuilder();
		if(null != params) {
			boolean tag = false;
			for (Map.Entry<String, Object> entry: params.entrySet()) {
				sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),"UTF-8")).append("&");
				tag = true;
			}
			if(tag) {
				System.out.println("请求url内容为：" + url);
				System.out.println("请求内容为：" + sb.toString());
				return sendPost(url, sb.substring(0, sb.length()).getBytes());
			}
		}
		return sendPost(url, sb.toString().getBytes());
	}

	@Override
	public String sendFile(String url, String filename) throws Exception {
		File file = new File(filename);
		if(!file.exists())
			throw new Exception("File ["+filename+"] not found");
		HttpURLConnection con = null;
		BufferedReader in = null;
		BufferedOutputStream out = null;
		InputStream fileIn = null;
		StringBuilder result = new StringBuilder();
		try{
			
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setReadTimeout(3600000);
			out = new BufferedOutputStream(con.getOutputStream());
			logger.info("sendFile return from "+url);
			
			fileIn = new BufferedInputStream(new FileInputStream(file));
			byte[] data = new byte[1024];
			int count = -1;
			while((count = fileIn.read(data)) != -1){
				out.write(data, 0, count);
			}
			out.flush();
			out.close();
			fileIn.close();
			
			if(con.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
			return result.toString();
			
		} catch (Exception e){
			throw e;
		} finally {
			try{
				if(in != null)
					in.close();
			}catch (Exception e){}
			try{
				if(in != null)
					out.close();
			}catch (Exception e){}
			try{
				con.getInputStream().close();
			} catch (Throwable e){
				
			}
			try{
				con.getOutputStream().close();
			} catch (Throwable e){
				
			}
			con.disconnect();
		}
	}

	@Override
	public String sendFormFile(String url, String filePath, Map<String, String> params) throws Exception {
		String result = null;
		final String BOUNDARY = "---------------------------7da2137580612";
		final String endline = "--" + BOUNDARY + "--\r\n";

		URL u = new URL(url);
		int port = u.getPort() == -1 ? 80 : u.getPort();
		Socket socket = new Socket(InetAddress.getByName(u.getHost()), port);
		OutputStream outStream = socket.getOutputStream();
		StringBuilder send = new StringBuilder();
		send.append("POST " + u.getPath() + " HTTP/1.1\r\n");
		send.append("Accept: image/*, application/x-shockwave-flash, "
				+ "application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, "
				+ "application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n");
		send.append("Accept-Language: zh-CN\r\n");
		send.append("Connection: Keep-Alive\r\n");
		send.append("Host: " + u.getHost() + ":" + port + "\r\n");
		send.append("Content-Type: multipart/form-data; boundary=" + BOUNDARY
				+ "\r\n");
		logger.info("sendFormFile return from "+url);

		StringBuilder textEntity = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			textEntity.append("--");
			textEntity.append(BOUNDARY);
			textEntity.append("\r\n");
			textEntity.append("Content-Disposition: form-data; name=\""
					+ entry.getKey() + "\""+"\r\n\r\n");
			textEntity.append(entry.getValue());
			textEntity.append("\r\n");
		}
		File file = new File(filePath);
		StringBuilder fileEntity = new StringBuilder();
		fileEntity.append("--");
		fileEntity.append(BOUNDARY);
		fileEntity.append("\r\n");
//		fileEntity.append("Content-Disposition: form-data;name=\""
//				+ "upload" + "\";filename=\"" + file.getAbsolutePath()
//				+ "/" + file.getName() + "\"\r\n");
		fileEntity.append("Content-Disposition: form-data;name=\""
				+ "upload" + "\";filename=\"" + file.getAbsolutePath()
				+ "\"\r\n");
		fileEntity
				.append("Content-Type: application/octet-stream" + "\r\n\r\n");

		String end = "\r\n" + endline;
		send.append("Content-Length: "
				+ (textEntity.toString().getBytes().length
						+ fileEntity.toString().getBytes().length
						+ file.length() + end.getBytes().length) + "\r\n");
		send.append("\r\n");
		outStream.write(send.toString().getBytes());
		outStream.write(textEntity.toString().getBytes());
		outStream.write(fileEntity.toString().getBytes());
		byte[] buffer = new byte[1024];
		int len = 0;
		FileInputStream stream = new FileInputStream(file);
		while ((len = stream.read(buffer, 0, 1024)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.write(end.getBytes());
		stream.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String line = "";
		outStream.flush();
		int resLen = 0;
		System.out.println(reader.toString());
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("Content-Length")) {
				resLen = Integer.valueOf(line.split(":")[1].trim());
			} else if (line.equals("")) {
				break;
			}
			System.out.println(line);
		}
		char[] c = new char[resLen];
		reader.read(c);
		outStream.close();
		reader.close();
		socket.close();
		result = new String(c);
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		HttpProviderImpl http = new HttpProviderImpl();
		Map<String, Object> params = new HashMap<>();
		params.put("name", "testU");
		params.put("userid", "asdff");
		params.put("position", "asdfsadf");
		params.put("mobile", "12314151");
		params.put("access_token", "access_tk");
//		params.put("params", "{test=123}");
//		try {
//			http.sendPost("http://10.20.37.97/user/test", params);
//			
//			System.out.println("");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		WeixinUser user = new WeixinUser();
		
//		user.setParams(params);
//		user.setUserid("dengxf");
//		user.setEmail("dengxf@yintong.com");
//		user.setMobile("15669960039");
//		user.setName("dengxf");
//		String json = user.getJson();
		
//		String tt = "params={test=abc}";
		try {
			http.sendPost("http://10.20.37.97/user/test", params);
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
