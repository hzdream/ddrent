package com.aifeng.ddrent.common.model.weixin;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @ClassName: WeixinResult 
 * @Description: 封装微信返回对象
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:47:27
 */
public class WeixinResult implements Serializable {
	private static final long serialVersionUID = -3116895032733837838L;
	
	private String errcode;
	
	private String errmsg;
	
	private Map<String, Object> res;
	
	//错误代码
	public final static String ERROR_CODE = "errcode";
	
	//错误信息
	public final static String ERROR_MSG = "errmsg";
	
	//缺少access_token参数
	public static final String NO_ACCESS_TOKEN = "41001";
	
	//access_token已过期
	public static final String ACCESS_TOKEN_INVALID = "42001";
	
	//suite access token 已过期
	public static final String SUITE_ACCESS_TOKEN_INVALID = "42009";
	
	//请求成功
	public static final String SUCCESS = "0";
	
	//请求繁忙
	public static final String BUSY = "-1";

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Map<String, Object> getRes() {
		return res;
	}

	public void setRes(Map<String, Object> res) {
		this.res = res;
	}

	public WeixinResult() {
		super();
	}

	public WeixinResult(String errcode) {
		super();
		this.errcode = errcode;
	}

	/**
	 * 请求是否成功
	 * @return
	 */
	public boolean isSuccess() {
		if(null != errcode) {
			return SUCCESS.equals(errcode);
		}else {
			return false;
		}
	}

	/**
	 * 请求是否繁忙
	 * @return
	 */
	public boolean isBussy() {
		if(null != errcode) {
			return BUSY.equals(errcode);
		}else {
			return false;
		}
	}

	/**
	 * token失效
	 * @return
	 */
	public boolean isTokenInvalid() {
		if(null != errcode) {
			return ACCESS_TOKEN_INVALID.equals(errcode) || SUITE_ACCESS_TOKEN_INVALID.equals(errcode);
		}else {
			return false;
		}
	}
	
}
