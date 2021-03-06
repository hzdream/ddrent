/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.response
 * @author imart·deng
 * @date 创建时间：2018年8月13日 下午2:49:53
 * @version 1.0
 */
package com.aifeng.ddrent.common.model.response;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;

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
	private String code;
	
	/** 请求结果信息 */
	private String message;

	/** 结果数据 */
	private DataContainer<T> data;
	
	public BaseResult() {
		super();
	}

	/**
	 * 设置错误参数
	 * @param errorCode		错误编码
	 */
	public BaseResult(ErrorCodeEnum errorCode) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
			this.message = errorCode.msg();
		}
		
	}

	/**
	 * @param errorCode		错误编码
	 * @param msg			信息
	 */
	public BaseResult(ErrorCodeEnum errorCode, String msg) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
		}
		this.message = msg;
	}
	
	/**
	 * @param errorCode		错误编码
	 * @param data			信息
	 */
	public BaseResult(ErrorCodeEnum errorCode, DataContainer<T> data) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
			this.message = errorCode.msg();
		}
		this.data = data;
	}

	/**
	 * @param errorCode		错误信息
	 * @param msg			信息
	 * @param data			数据内容
	 */
	public BaseResult(ErrorCodeEnum errorCode, String msg, DataContainer<T> data) {
		super();
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
		}
		this.message = msg;
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
	public String getCode() {
		return code;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public BaseResult<T> setCode(ErrorCodeEnum errorCode) {
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
			this.message = errorCode.msg();
		}
		return this;
	}

	/**
	 *
	 * @param baseResult
	 * @param <R>
	 * @return
	 */
	public <R> BaseResult<T> setCode(BaseResult<R> baseResult) {
		if(null != baseResult){
			this.success = baseResult.isSuccess();
			this.code = baseResult.getCode();
			this.message = baseResult.getMessage();
		}
		return this;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param msg the msg to set
	 */
	public BaseResult<T> setMessage(String msg) {
		this.message = msg;
		return this;
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
	public BaseResult<T> setData(DataContainer<T> data) {
		this.data = data;
		return this;
	}

	/**
	 * @param errorCode		错误代码
	 * @param message		错误信息
	 */
	public BaseResult<T> setCode(ErrorCodeEnum errorCode, String message) {
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
			this.message = message;
		}
		return this;
	}

	/**
	 * @param errorCode		错误信息
	 * @param data			数据内容
	 */
	public BaseResult<T> setCode(ErrorCodeEnum errorCode, DataContainer<T> data) {
		this.data = data;
		if(null != errorCode) {
			success = errorCode.isSuccess();
			this.code = errorCode.code();
			this.message = errorCode.msg();
		}
		return this;
	}
	
}
