package com.aifeng.ddrent.common.model.weixin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: WeixinUser 
 * @Description: 企业微信用户信息
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:47:52
 */
public class WeixinUser implements Serializable {
	private static final long serialVersionUID = -7402960815562576336L;

	/** 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节 */
	private String userid;
	
	/** 成员名称。长度为1~64个字节 */
	private String name;
	
	/** 英文名。长度为1-64个字节。 */
	private String english_name;
	
	/** 手机号码。企业内必须唯一，mobile/email二者不能同时为空 */
	private String mobile;
	
	/** 成员所属部门id列表,不超过20个 */
	private List<String> department;
	
	/** 部门内的排序值，默认为0。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32) */
	private List<String> order;
	
	/** 职位信息。长度为0~64个字节 */
	private String position;
	
	/** 性别。1表示男性，2表示女性 */
	private Integer gender;
	
	/** 邮箱。长度为0~64个字节。企业内必须唯一，mobile/email二者不能同时为空*/
	private String email;
	
	/** 座机。长度0-64个字节。*/
	private String telephone;
	
	/** 上级字段，标识是否为上级。*/
	private Integer isleader;
	
	/** 成员头像的mediaid，通过多媒体接口上传图片获得的mediaid */
	private String avatar_mediaid;
	
	/** 启用/禁用成员。1表示启用成员，0表示禁用成员 */
	private Integer enable;
	
	/** 自定义字段。自定义字段需要先在WEB管理端“我的企业” — “通讯录管理”添加，否则忽略未知属性的赋值*/
	private Map<String, List<Extattr>> extattr;
	
	//额外字段属性名
	public final static String ATTRS = "attrs";
	
	//	成员对外属性，字段详情见对外属性
	private Map<String, List<ExternalAttr>> external_profile;
	
	//对外字段属性
	public final static String EXTERNAL_ATTR = "external_attr";
	
	/**
	 * 额外字段
	 * @author dengxf
	 *
	 */
	public static class Extattr{
		String name;
		String value;
		public Extattr(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
	}
	
	/**
	 * 对外扩展属性
	 * @author dengxf
	 *
	 */
	public static class ExternalAttr{
		Integer type;
		String name;
		
		//对外扩展属性web属性
		ExternalWeb web;
		
		//对外扩展属性文本属性
		ExternalText text;
		
		//对外扩展属性小程序属性
		ExternalMiniprogram miniprogram;

		public ExternalAttr(String name, ExternalText text) {
			super();
			this.type = 0;
			this.name = name;
			this.text = text;
		}

		public ExternalAttr(String name, ExternalWeb web) {
			super();
			this.type = 1;
			this.name = name;
			this.web = web;
		}

		public ExternalAttr(String name, ExternalMiniprogram miniprogram) {
			super();
			this.type = 2;
			this.name = name;
			this.miniprogram = miniprogram;
		}
		
	}
	
	public static class ExternalWeb{
		//网页的url,必须包含http或者https头
		String url;
		//网页的展示标题,长度限制12个UTF8字符
		String title;
		public ExternalWeb(String url, String title) {
			super();
			this.url = url;
			this.title = title;
		}
	}
	
	public static class ExternalText{
		//文本属性内容,长度限制12个UTF8字符
		String value;

		public ExternalText(String value) {
			super();
			this.value = value;
		}
		
	}
	
	//对外属性app属性
	public static class ExternalMiniprogram{
		//小程序appid，必须是有在本企业安装授权的小程序，否则会被忽略
		String appid;
		//小程序的展示标题,长度限制12个UTF8字符
		String title;
		//小程序的页面路径
		String pagepath;
		public ExternalMiniprogram(String appid, String title, String pagepath) {
			super();
			this.appid = appid;
			this.title = title;
			this.pagepath = pagepath;
		}
	}
	
	public WeixinUser(String userid, String name, String mobile, List<String> department, Integer gender) {
		super();
		this.userid = userid;
		this.name = name;
		this.mobile = mobile;
		this.department = department;
		if(null != gender) {
			this.gender = gender;
		}
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglish_name() {
		return english_name;
	}

	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getDepartment() {
		return department;
	}

	public void setDepartment(List<String> department) {
		this.department = department;
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getIsleader() {
		return isleader;
	}

	public void setIsleader(Integer isleader) {
		this.isleader = isleader;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Map<String, List<Extattr>> getExtattr() {
		return extattr;
	}

	public Map<String, List<ExternalAttr>> getExternal_profile() {
		return external_profile;
	}

	public void addExtattr(String name, String value) {
		if(null == extattr) {
			extattr = new HashMap<String, List<Extattr>>(2);
			extattr.put(ATTRS, new ArrayList<Extattr>(8));
		}
		extattr.get(ATTRS).add(new Extattr(name, value));
	}
	
	public void addExternal(String name, ExternalText text) {
		if(null == external_profile) {
			external_profile = new HashMap<String, List<ExternalAttr>>(2);
			external_profile.put(EXTERNAL_ATTR, new ArrayList<ExternalAttr>(8));
		}
		external_profile.get(EXTERNAL_ATTR).add(new ExternalAttr(name, text));
	}
	
	public void addExternal(String name, ExternalWeb web) {
		if(null == external_profile) {
			external_profile = new HashMap<String, List<ExternalAttr>>(2);
			external_profile.put(EXTERNAL_ATTR, new ArrayList<ExternalAttr>(8));
		}
		external_profile.get(EXTERNAL_ATTR).add(new ExternalAttr(name, web));
	}
	
	public void addExternal(String name, ExternalMiniprogram miniprogram) {
		if(null == external_profile) {
			external_profile = new HashMap<String, List<ExternalAttr>>(2);
			external_profile.put(EXTERNAL_ATTR, new ArrayList<ExternalAttr>(8));
		}
		external_profile.get(EXTERNAL_ATTR).add(new ExternalAttr(name, miniprogram));
	}

}
