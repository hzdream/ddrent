///**
// * Copyright © 2018 aifeng club All rights reserved.
// * @Package: com.aifeng.ddrent.web.response 
// * @author imart·deng
// * @date 创建时间：2018年8月13日 下午2:58:39
// * @version 1.0
// */
//package com.aifeng.ddrent.common.model;
//
//import java.util.List;
//
///** 
// * @ClassName: DataContainer 
// * @Description: BaseResult 返回数据容器
// * @author: imart·deng
// * @date: 2018年8月13日 下午2:58:39  
// */
//public class DataContainer<T> {
//
//	private T obj;
//	
//	private List<T> rows;
//	
//	private int total;
//
//	private Object other;
//	
//	/**
//	 * @param obj
//	 */
//	public DataContainer(T obj) {
//		super();
//		this.obj = obj;
//		this.total = null == obj ? 0 : 1;
//	}
//
//	/**
//	 * @param rows
//	 * @param total
//	 */
//	public DataContainer(List<T> rows, int total) {
//		super();
//		this.rows = rows;
//		this.total = total;
//	}
//
//	/**
//	 * @return the obj
//	 */
//	public T getObj() {
//		return obj;
//	}
//
//	/**
//	 * @param obj the obj to set
//	 */
//	public void setObj(T obj) {
//		this.obj = obj;
//	}
//
//	/**
//	 * @return the rows
//	 */
//	public List<T> getRows() {
//		return rows;
//	}
//
//	/**
//	 * @param rows the rows to set
//	 */
//	public void setRows(List<T> rows) {
//		this.rows = rows;
//	}
//
//	/**
//	 * @return the total
//	 */
//	public int getTotal() {
//		return total;
//	}
//
//	/**
//	 * @param total the total to set
//	 */
//	public void setTotal(int total) {
//		this.total = total;
//	}
//
//	/**
//	 * @return the other
//	 */
//	public Object getOther() {
//		return other;
//	}
//
//	/**
//	 * @param other the other to set
//	 */
//	public void setOther(Object other) {
//		this.other = other;
//	}
//	
//}
