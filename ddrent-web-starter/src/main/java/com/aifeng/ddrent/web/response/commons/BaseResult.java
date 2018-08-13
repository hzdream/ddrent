/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.response 
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:49:53
 * @version 1.0
 */
package com.aifeng.ddrent.web.response.commons;

import com.aifeng.ddrent.core.common.enums.system.ErrorCodeEnum;

/** 
 * @ClassName: BaseResult 
 * @Description: 基础返回结果，应该以该结果作为标准结果集
 * @author: imart·deng
 * @param <T>
 * @date: 2018年8月13日 下午2:49:53  
 */
public class BaseResult<T> {
	
	/** 请求是否成功 */
	private boolean success; 

	/** 请求错误编号 */
	private String errorCode;
	
	/** 请求结果信息 */
	private String msg;

	/** 结果数据 */
	private DataContainer<T> data;
	
	/**
	 * 
	 */
	public BaseResult() {
		super();
	}

	/**
	 * 设置错误参数
	 * @param errorCode
	 */
	public BaseResult(ErrorCodeEnum errorCode) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.errorCode = errorCode.code();
			this.msg = errorCode.msg();
		}
		
	}

	/**
	 * @param errorCode
	 * @param msg
	 */
	public BaseResult(ErrorCodeEnum errorCode, String msg) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.errorCode = errorCode.code();
		}
		this.msg = msg;
	}
	
	/**
	 * @param errorCode
	 * @param data
	 */
	public BaseResult(ErrorCodeEnum errorCode, DataContainer<T> data) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.errorCode = errorCode.code();
			this.msg = errorCode.msg();
		}
		this.data = data;
	}

	/**
	 * @param errorCode
	 * @param msg
	 * @param data
	 */
	public BaseResult(ErrorCodeEnum errorCode, String msg, DataContainer<T> data) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.errorCode = errorCode.code();
		}
		this.msg = msg;
		this.data = data;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCodeEnum errorCode) {
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.errorCode = errorCode.code();
			this.msg = errorCode.msg();
		}
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public DataContainer<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(DataContainer<T> data) {
		this.data = data;
	}
	
}
