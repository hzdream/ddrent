/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.common.model 
 * @author imart·deng
 * @date 创建时间：2018年8月22日 上午2:21:09
 * @version 1.0
 */
package com.aifeng.ddrent.core.common.model;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;


/** 
 * @ClassName: PageParaBean 
 * @Description: 基础分页对象
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:21:09  
 */
public class PageBean implements Serializable{
	private static final long serialVersionUID = -1988400349801901570L;
	
	public static final int MIN_ROWS = 1;
	public static final int MAX_ROWS = 9999;
	
	public static final int MIN_PAGE = 1;
	public static final int MAX_PAGE = 9999;
	
	/**
	 * 页数
	 */
	private Integer page = 1;
	/**
	 * 行数
	 */
	private Integer rows = 10;
	/**
	 * 分类
	 */
	private String sort;
	
	/**
	 * 分类名称
	 */
	private String sortName;
	
	/**
	 * 排序规则  asc desc
	 */
	private String order;
	
	/**
	 * 
	 */
	private Integer total = 0;
	
	/**
	 *
	 */
	private boolean countCompute = true;
	
	public PageBean() {
	}

	public PageBean(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public PageBean setPage(Integer page) {
		this.page = page;
		return this;
	}

	public Integer getRows() {
		return rows;
	}

	public PageBean setRows(Integer rows) {
		this.rows = rows;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public PageBean setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public String getOrder() {
		return order;
	}

	public PageBean setOrder(String order) {
		this.order = order;
		return this;
	}

	public String getSortName() {
		return sortName;
	}

	public PageBean setSortName(String sortName) {
		this.sortName = sortName;
		return this;
	}
	
	public int getOffset() {
		if(page == null || rows == null || page <= 1 || rows <= 0) {
			return 0;
		}
		return (page - 1) * rows;
	}
	
	public int getMaxResults() {
		if(rows != null) {
			return rows;
		}
		return 0;
	}
	
	public boolean enablePaging() {
		if(rows == null) return false;
		return rows >= 0;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isCountCompute() {
		return countCompute;
	}

	public void setCountCompute(boolean countCompute) {
		this.countCompute = countCompute;
	}

	/**
	 * @return
	 */
	public RowBounds toRowBounds() {
		return new RowBounds(this.getOffset(), this.getRows());
	}
	
}
