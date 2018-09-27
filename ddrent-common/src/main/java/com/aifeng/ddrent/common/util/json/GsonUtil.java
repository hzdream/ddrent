/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.json 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午2:23:02
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.json;

import com.aifeng.ddrent.common.util.json.adapter.EmptyStringToNumberTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * @ClassName: GsonUtil 
 * @Description: json 工具
 * @author: imart·deng
 * @date: 2018年9月18日 下午2:23:02  
 */
public class GsonUtil {

	static final Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
			.registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(long.class, new EmptyStringToNumberTypeAdapter())
			.registerTypeAdapter(Long.class, new EmptyStringToNumberTypeAdapter())
			.create();

	/**
	 * <strong>获取gson工具</strong>
	 * <ul>
	 * <li>null --> ""</li>
	 * <li>Date pattern --> "yyyy-MM-dd HH:mm:ss"</li>
	 * </ul>
	 * @return
	 */
	public static Gson gson() {
		return gson;
	}
	
	public static class Demo{
		private int intv;
		private Integer iint;
		private long longv;
		private Long llong;
		private double doublev;
		private Double ddouble;
		private String name;
		public int getIntv() {
			return intv;
		}
		public void setIntv(int intv) {
			this.intv = intv;
		}
		public Integer getIint() {
			return iint;
		}
		public void setIint(Integer iint) {
			this.iint = iint;
		}
		public long getLongv() {
			return longv;
		}
		public void setLongv(long longv) {
			this.longv = longv;
		}
		public Long getLlong() {
			return llong;
		}
		public void setLlong(Long llong) {
			this.llong = llong;
		}
		public double getDoublev() {
			return doublev;
		}
		public void setDoublev(double doublev) {
			this.doublev = doublev;
		}
		public Double getDdouble() {
			return ddouble;
		}
		public void setDdouble(Double ddouble) {
			this.ddouble = ddouble;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Demo [intv=" + intv + ", iint=" + iint + ", longv=" + longv + ", llong=" + llong + ", doublev="
					+ doublev + ", ddouble=" + ddouble + ", name=" + name + "]";
		}
	}
	
	public static void main(String[] args) {
		String json = "{'intv':'abc','iint':'dsdf','longv':'dsdf','llong':'dsdf','doublev':'dsdf','ddouble':'dsdf','name':'dsdf'}";
		
		Demo demo = gson().fromJson(json, Demo.class);
		
		System.out.println(demo.toString());
	}
}
