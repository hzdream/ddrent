/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.web.request 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午7:20:36
 * @version 1.0
 */
package com.aifeng.ddrent.web.request;

import org.apache.ibatis.session.RowBounds;

/** 
 * @ClassName: PageBaseRequest 
 * @Description: 分页查询
 * @author: imart·deng
 * @date: 2018年9月25日 下午7:20:36  
 */
public class PageBaseRequest extends BaseRequest {

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
	
	public PageBaseRequest() {
	}

	public PageBaseRequest(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public PageBaseRequest setPage(Integer page) {
		this.page = page;
		return this;
	}

	public Integer getRows() {
		return rows;
	}

	public PageBaseRequest setRows(Integer rows) {
		this.rows = rows;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public PageBaseRequest setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public String getOrder() {
		return order;
	}

	public PageBaseRequest setOrder(String order) {
		this.order = order;
		return this;
	}

	public String getSortName() {
		return sortName;
	}

	public PageBaseRequest setSortName(String sortName) {
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
