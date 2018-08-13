/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.controller 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:42:51
 * @version 1.0
 */
package com.aifeng.ddrent.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aifeng.ddrent.core.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.web.response.commons.BaseResult;
import com.aifeng.ddrent.web.response.commons.SessionInfo;

/** 
 * @ClassName: BaseController 
 * @Description: 基础controller服务
 * @author: imart·deng
 * @date: 2018年8月13日 下午2:42:51  
 */
public class BaseController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	/**
	 * 异常统一处理
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public @ResponseBody Object exceptionHandler(HttpServletRequest request, Exception ex) {
		logger.error("遇到一个异常", ex);
		BaseResult<Object> result = new BaseResult<>(ErrorCodeEnum.SYSTEM_ERROR, ex.getMessage());
		if(ex instanceof RuntimeException) {
			
		}
		request.setAttribute("ex", ex.getMessage());
		return result;
	}
	
	/**
	 * 
	 * @ClassName: DoubleEditor 
	 * @Description: dubbo 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:18:13
	 */
	public class DoubleEditor extends PropertiesEditor{
		@Override    
	    public void setAsText(String text) throws IllegalArgumentException {    
	        if (text == null || text.equals("")) {    
	            text = "0";    
	        }    
	        setValue(Double.parseDouble(text));    
	    }
		
		@Override    
	    public String getAsText() {    
	        return getValue().toString();    
	    }
	}
	
	/**
	 * 
	 * @ClassName: IntegerEditor 
	 * @Description: int 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:18:29
	 */
	public class IntegerEditor extends PropertiesEditor{
		@Override    
	    public void setAsText(String text) throws IllegalArgumentException {    
	        if (text == null || text.equals("")) {    
	            text = "0";    
	        }    
	        setValue(Integer.parseInt(text));    
	    } 
		@Override    
	    public String getAsText() {    
	        return getValue().toString();    
	    }  
	}
	
	/**
	 * 
	 * @ClassName: FloatEditor 
	 * @Description: float 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:18:40
	 */
	public class FloatEditor extends PropertiesEditor{
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null || text.equals("")) {
				text = "0";
			}
			super.setValue(Float.parseFloat(text));
		}
		
		@Override
		public String getAsText() {
			return getValue().toString();
		}
		
	}
	
	/**
	 * 
	 * @ClassName: LongEditor 
	 * @Description: long 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:18:52
	 */
	public class LongEditor extends PropertiesEditor{
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null || text.equals("")) {
				text = "0";
			}
			super.setValue(Long.parseLong(text));
		}
		
		@Override
		public String getAsText() {
			return getValue().toString();
		}
		
	}
	
	/**
	 * 
	 * @ClassName: MyDateEditor 
	 * @Description: date 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:19:07
	 */
	public static class MyDateEditor extends CustomDateEditor{
		
		private static final List<DateFormat> formarts = new ArrayList<DateFormat>(4);
		
		static {
			formarts.add(new SimpleDateFormat("yyyy-MM"));
			formarts.add(new SimpleDateFormat("yyyy-MM-dd"));
			formarts.add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
			formarts.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}
	
		private DateFormat dateFormat;
		
		private DateFormat defaultDateFormat = formarts.get(3);

		private final boolean allowEmpty;

		private final int exactDateLength;
		
		public MyDateEditor(DateFormat dateFormat, boolean allowEmpty) {
			super(dateFormat, allowEmpty);
			this.dateFormat = dateFormat;
			this.allowEmpty = allowEmpty;
			this.exactDateLength = -1;
		}

		public MyDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
			super(dateFormat, allowEmpty, exactDateLength);
			this.dateFormat = dateFormat;
			this.allowEmpty = allowEmpty;
			this.exactDateLength = exactDateLength;
		}
		
		public void convert(String source) {
			if(source.matches("^\\d{4}-\\d{1,2}$")){
				this.dateFormat = formarts.get(0);
			}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
				this.dateFormat = formarts.get(1);
			}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
				this.dateFormat = formarts.get(2);
			}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
				this.dateFormat = formarts.get(3);
			}else {
				
			}
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (this.allowEmpty && !StringUtils.hasText(text)) {
				// Treat empty String as null value.
				setValue(null);
			}
			else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
				throw new IllegalArgumentException(
						"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
			}
			else {
				text.trim();
				convert(text);
				try {
					setValue(this.dateFormat.parse(text));
				}
				catch (ParseException ex) {
					throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
				}
			}
		}

		@Override
		public String getAsText() {
			Date value = (Date) getValue();
			return (value != null ? this.defaultDateFormat.format(value) : "");
		}
		
	}
	
	/**
	 * 重写spring 默认编辑器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new MyDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
		
	}
	
	/**
	 * 获取客户端ip
	 * @return
	 */
	public String getClientIp() { 
	    String ip = request.getHeader("X-Forwarded-For");  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_VIA");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("REMOTE_ADDR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getRemoteAddr();  
	    }
	    if("0:0:0:0:0:0:0:1".equals(ip)) {
	    	ip = "localhost";
	    }
	    return ip;  
	}
	
	/**
	 * 获取uri完整信息
	 * @param request
	 * @return
	 */
	public String getFullUri() {
		
		//用于兼容代理https
		String scheme = request.getHeader("X-Forwarded-Proto");
		if(null == scheme) {
			scheme = request.getHeader("X-Forwarded-For");
		}
		if(null == scheme || !scheme.equals("htpps")) {
			scheme = request.getScheme();
		}
		
		String uri = scheme + "://" +
	             request.getServerName() + 
	             ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
	             request.getRequestURI() +
	            (request.getQueryString() != null ? "?" + request.getQueryString() : "");
		
		return uri;
	}
	
	/**
	 * 获取请求uri
	 * @param request
	 * @return
	 */
	public String getUrl() {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		return  url;
	}
	
	/**
	 * 获取登陆信息
	 * @return
	 */
	public SessionInfo getSessionInfo() {
		return (SessionInfo) request.getSession().getAttribute(SessionInfo.SESSION_NAME);
	}
}
