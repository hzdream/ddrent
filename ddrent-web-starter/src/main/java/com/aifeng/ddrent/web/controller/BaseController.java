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

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aifeng.ddrent.common.constant.session.SessionConstant;
import com.aifeng.ddrent.web.filter.sessionManager.SessionStatusManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.CoreException;
import com.aifeng.ddrent.common.exception.auth.OAuth2Exception;
import com.aifeng.ddrent.common.exception.auth.RequestFailedException;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.web.response.commons.SessionInfo;

/** 
 * @ClassName: BaseController 
 * @Description: 基础controller服务
 * @author: imart·deng
 * @date: 2018年8月13日 下午2:42:51  
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;

	// 用户会话状态信息
	private SessionStatusManager sessionStatusManager = SessionStatusManager.getInstance();
	
	/**
	 * 异常统一处理
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public @ResponseBody Object exceptionHandler(HttpServletRequest request, Exception ex) {
		
		BaseResult<Object> result = null;
		
		if(ex instanceof CoreException) {
			result = new BaseResult<>(((CoreException)ex).getCode(), ex.getMessage());
			logger.error("[{}]服务遇到一个基础服务异常, 异常状态码{}, 异常原因{}", request.getRequestURI(),
					((CoreException)ex).getCode().code(), ex.getMessage());
			
			//AuthException 需要设置状态码
			if(ex instanceof OAuth2Exception) {
				response.setStatus(((OAuth2Exception)ex).getHttpCode());
			}
		}else {
			int status = response.getStatus();
			if(0 == status)
			response.setStatus(500);
			
			result = new BaseResult<>(ErrorCodeEnum.UNKNOW_SYSTEM_ERROR);
			logger.error("[{}]服务遇到一个基础服务异常, 异常状态码{}, 异常原因{}", request.getRequestURI(), ex.getMessage());
		}
		
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
	 * @ClassName: NumberEditor 
	 * @Description: number 编辑器
	 * @author: imart·deng
	 * @date: 2018年8月13日 下午5:18:13
	 */
	public class NumberEditor extends PropertiesEditor{
		@Override    
		public void setAsText(String text) throws IllegalArgumentException {    
			if (text == null || text.equals("")) {    
				text = null;    
			}else {
				try {
					setValue(NumberUtils.createNumber(text));
				} catch (NumberFormatException e) {
					logger.debug("[json 反序列化] 失败，json原值{}", text);
				}
			}
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
	            text = null;    
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
		binder.registerCustomEditor(int.class, new NumberEditor());
		binder.registerCustomEditor(Integer.class, new NumberEditor());
		binder.registerCustomEditor(long.class, new NumberEditor());
		binder.registerCustomEditor(Long.class, new NumberEditor());
		binder.registerCustomEditor(double.class, new NumberEditor());
		binder.registerCustomEditor(Double.class, new NumberEditor());
		binder.registerCustomEditor(float.class, new NumberEditor());
		binder.registerCustomEditor(Float.class, new NumberEditor());
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
	 * @return			全url
	 */
	public String getFullUrl() {
		
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
		String accessToken = getToken(request);
		return (SessionInfo) sessionStatusManager.get(accessToken).getData();
	}
	
	protected void validate(BindingResult bdResult) throws RequestFailedException {
		for (ObjectError error : bdResult.getAllErrors()) {
			throw new RequestFailedException(ErrorCodeEnum.PARAMS_ERROR, error.getDefaultMessage());
		}
	}

	/**
	 * 生成token 的 cookie
	 * @param token
	 * @return
	 */
	public void addTokenCooike(String token) {
		StringBuilder sb = new StringBuilder();
		sb.append(SessionConstant.ACCESS_TOKEN + "=" + token)
				.append(";Max-Age=" + 7200)
				.append(";Path=/")
				.append(";HttpOnly;");
		response.addHeader("Set-Cookie", sb.toString());
	}

	/**
	 * 获取token
	 * @param request
	 * @return
	 */
	private String getToken(ServletRequest request){
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		for (Cookie cookie: cookies) {
			if(SessionConstant.ACCESS_TOKEN.equals(cookie.getName())) return cookie.getValue();
		}
		return null;
	}
	
}
