package com.aifeng.ddrent.common.model.weixin;

import java.io.Serializable;

/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2018年1月23日 下午4:04:36
* @version 1.0
* @since
* 
*/
public class JsAPIConfig implements Serializable{
	private static final long serialVersionUID = 1476106663621179717L;

	private String ticket;
	
	private String noncestr;
	
	private Long timestamp;
	
	private String url;
	
	private String signature;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getParamsStr() {
		return "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url ;
	}

	@Override
	public String toString() {
		return "JsAPIConfig [ticket=" + ticket + ", noncestr=" + noncestr + ", timestamp=" + timestamp + ", url=" + url
				+ ", signature=" + signature + "]";
	}
}
