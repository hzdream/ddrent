/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.model.dto 
 * @author imart·deng
 * @date 创建时间：2018年9月26日 上午12:54:53
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.model.dto;

/** 
 * @ClassName: WeixinDTO 
 * @Description: 微信session dto
 * @author: imart·deng
 * @date: 2018年9月26日 上午12:54:53  
 */
public class WeixinSessionDTO {
	
	//微信open id
	private String openid;
	
	//微信用户登陆session key
	private String session_key;
	
	//微信应用唯一id（所有应用id 一致）
	private String unionid;
	
	private String errcode;
	
	private String errmsg;

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the session_key
	 */
	public String getSession_key() {
		return session_key;
	}

	/**
	 * @param session_key the session_key to set
	 */
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	/**
	 * @return the unionid
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid the unionid to set
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	/**
	 * @return the errcode
	 */
	public String getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
