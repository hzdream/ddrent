/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service 
 * @author imart·deng
 * @date 创建时间：2018年8月22日 上午2:07:08
 * @version 1.0
 */
package com.aifeng.ddrent.core.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.model.response.DataContainer;
import com.aifeng.ddrent.common.util.data.GsonUtil;
import com.aifeng.ddrent.common.util.data.id.SequenceGeneratorUtil;
import com.aifeng.ddrent.core.common.model.PageBean;
import com.aifeng.ddrent.core.dao.model.BaseDOI;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/** 
 * @ClassName: BaseService 
 * @Description: 基础服务
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:07:08  
 */
public abstract class BaseService<T extends BaseDOI,K extends Mapper<T>> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 手动事务管理
	 */
	protected DataSourceTransactionManager txManager;
	
	/**
	 * 默认分页起始位
	 */
	protected final static int DEFAULT_PAGE = 1;

	/**
	 * 1
	 */
	protected final static int ONE = 1;
	
	/**
	 * 默认分页大小
	 */
	protected final static int DEFAULT_ROWS = 10;
	
	/** 日志参数和消息划分 */
	protected final static String lOGGER_DIVIDE = "_";
	
	@Autowired
	protected Mapper<T> mapper;

	public DataSourceTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(DataSourceTransactionManager txManager) {
		this.txManager = txManager;
	}
	
	public TransactionStatus getNewTransaction(){
		if(null != this.txManager) {
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级别，开启新事务，这样会比较安全些。
			return txManager.getTransaction(def); // 获得事务状态
		}
		return null;
	}
	
	public TransactionStatus getTransaction(int propagationType) {
		if(null != this.txManager) {
			propagationType =( propagationType < TransactionDefinition.PROPAGATION_REQUIRED ? TransactionDefinition.PROPAGATION_REQUIRED : propagationType);
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(propagationType); // 事物隔离级别，开启新事务，这样会比较安全些。
			return txManager.getTransaction(def); // 获得事务状态
		}
		return null;
	}
	
	public RowBounds preparePage(PageBean page, Integer... args) {
		RowBounds rowBounds = null;
		if(null == page) {
			int offset = DEFAULT_PAGE;
			int limit = DEFAULT_ROWS;
			if(null != args && args.length>0) {
				offset = args[0];
				if(args.length>1) {
					limit = args[1];
				}
			}
			rowBounds = new RowBounds(offset, limit);
		}else {
			rowBounds = new RowBounds(page.getOffset(), page.getRows());
		}
		return rowBounds;
	}
	
	/**
	 * 异常日志统一处理
	 * @param params
	 * @param e
	 */
	public void errorLog(Object params, Exception e) {
		this.logger.error(params.toString() + lOGGER_DIVIDE + e.getMessage(), e);
	}
	
	/**
	 * 异常日志统一处理
	 * @param params
	 * @param e
	 */
	public void errorLogWhitException(Object params, Exception e) throws RuntimeException {
		this.logger.error(params.toString() + lOGGER_DIVIDE + e.getMessage(), e);
		throw new RuntimeException();
	}
	
	/**
	 * 消息类日志统一记录 
	 * @param message
	 * @param e
	 */
	public void messageLog(String message, Exception e) {
		this.logger.debug("Processing trade with symbol: " + message);
	}
	
	/**
	 * 根据id获取单个对象
	 * @param id
	 * @return
	 */
	public T getById(Long id) {
		if(null != id) {
			return getMapper().selectByPrimaryKey(id);
		}
		return null;
	}
	
	/**
	 * 根据查询条件获取 
	 * <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;&nbsp;params 对象不能为空，否则丢出异常</li>
	 * @param params	查询参数
	 * @param page		分页对象
	 */
	public BaseResult<T> findByParams(T params, PageBean page){
		assert null != params : "params can't be null.";
		
		// 如果分页对象不存在，设置默认分页
		if(null == page) page = new PageBean(DEFAULT_PAGE, DEFAULT_ROWS);
		
		List<T> list = getMapper().selectByRowBounds(params, page.toRowBounds());
		
		return new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(list, getMapper().selectCount(params)));
	}
	
	/**
	 * 根据查询条件获取 单个对象
	 * <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;&nbsp;params 对象不能为空，否则丢出异常</li>
	 * @param params	查询参数
	 * @param page		分页对象
	 */
	public T getByParams(T params){
		assert null != params : "params can't be null.";
		
		// 如果分页对象不存在，设置默认分页
		PageBean page = new PageBean(DEFAULT_PAGE, ONE);
		
		List<T> list = getMapper().selectByRowBounds(params, page.toRowBounds());
		
		if(null != list && !list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * 根据查询条件获取对象
	 * <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;&nbsp;example 对象不能为空，否则丢出异常</li>
	 * @param example	查询参数
	 * @param page		分页对象
	 */
	public BaseResult<T> findByExample(Example example, PageBean page){
		assert null != example : "params can't be null.";
		
		// 如果分页对象不存在，设置默认分页
		if(null == page) page = new PageBean(DEFAULT_PAGE, DEFAULT_ROWS);
		
		List<T> list = getMapper().selectByExampleAndRowBounds(example, page.toRowBounds());
		
		return new BaseResult<>(ErrorCodeEnum.SUCCESS, new DataContainer<>(list, getMapper().selectCountByExample(example)));
	}
	
	/**
	 * 根据查询条件获取单个对象
	 * <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;&nbsp;example 对象不能为空，否则丢出异常</li>
	 * @param example	查询参数
	 * @param page		分页对象
	 */
	public T getByExample(Example example){
		assert null != example : "params can't be null.";
		
		// 如果分页对象不存在，设置默认分页
		PageBean page = new PageBean(DEFAULT_PAGE, ONE);
		
		List<T> list = getMapper().selectByExampleAndRowBounds(example, page.toRowBounds());
		
		if(null != list && !list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
		
	}
	
	/**
	 * 根据id更新所有字段
	 *   <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;更新所有字段</li>
	 *   <li>&nbsp;&nbsp;成功返回record 对象， 失败返回null对象。</li>
	 * @param record	需要更新的记录
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public T updateById(T record) {
		if(null == record) return null;
		
		if(null != record.getId()) {
			try {
				int num = getMapper().updateByPrimaryKey(record);
				
				logger.info("[{}] 根据id硬更新成功, 请求参数为[{}], 本次影响记录数[{}]条", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), num);
				return record;
			} catch (Exception e) {
				logger.error("[{}] 根据id硬更新失败, 请求参数为[{}], 失败原因[{}]", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), e);
				throw e;
			}
		}
		return null;
	}
	
	/**
	 * 根据id更新部分字段
	 *   <li>&nbsp;&nbsp;<strong>PS:</strong>&nbsp;更新所有非null的字段</li>
	 *   <li>&nbsp;&nbsp;成功返回record 对象， 失败返回null对象。</li>
	 * @param record	需要更新的记录
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public T updateByIdSelective(T record) {
		if(null == record) return null;
		
		if(null != record.getId()) {
			try {
				int num = getMapper().updateByPrimaryKeySelective(record);
				
				logger.info("[{}] 根据id可选更新成功, 请求参数为[{}], 本次影响记录数[{}]条", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), num);
				return record;
			} catch (Exception e) {
				logger.error("[{}] 根据id硬更新失败, 请求参数为[{}], 失败原因[{}]", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), e);
				throw e;
			}
		}
		return null;
	}
	
	/**
	 * 添加一条记录
	 * <li>保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * <li>&nbsp;&nbsp;返回更新对象，并赋予id，如果record为null，则返回也为null
	 * @param record	需要添加的记录
	 * @return
	 * @see tk.mybatis.mapper.common.base.insert#insert()
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public T add(T record) {
		
		if(null != record) {
			
			//设置唯一id
			if(null == record.getId()) {
				record.setId(SequenceGeneratorUtil.nextId());;
			}
			
			try {
				int num = getMapper().insert(record);
				logger.info("[{}] 硬新增记录成功, 请求参数为[{}], 本次影响记录数[{}]条", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), num);
			}catch(Exception e) {
				logger.error("[{}] 可选新增记录失败, 请求参数为[{}], 失败原因[{}]", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), e);
				throw e;
			}
		}
		
		return record;
	}
	
	/**
	 * 添加一条记录
	 * <li>保存一个实体，null的属性不会保存，会使用数据库默认值
	 * <li>&nbsp;&nbsp;返回更新对象，并赋予id，如果record为null，则返回也为null
	 * @param record	需要添加的记录
	 * @return
	 * @see tk.mybatis.mapper.common.base.insert#insertSelective()
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public T addSelective(T record) {
		
		if(null != record) {
			
			//设置唯一id
			if(null == record.getId()) {
				record.setId(SequenceGeneratorUtil.nextId());;
			}
			try {
				int num = getMapper().insertSelective(record);
				logger.info("[{}] 新增记录成功, 请求参数为[{}], 本次影响记录数[{}]条", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), num);
			}catch(Exception e) {
				logger.error("[{}] 可选新增记录失败, 请求参数为[{}], 失败原因[{}]", this.getClass().getSimpleName(),
						GsonUtil.gson().toJson(record), e);
				throw e;
			}
		}
		
		return record;
	}
	
	/**
	 * 根据id删除记录（为了保证系统的完整性、项目稳定性等, 不建议直接使用删除方法）
	 * <li>&nbsp;&nbsp; 成功返回影响记录数0 或者 1, 失败返回 -1
	 * @param id
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public int removeById(Long id) {
		if(null != id) {
			try {
				int num = getMapper().deleteByPrimaryKey(id);
				logger.info("[{}] 新增记录成功, 请求参数为[{}], 本次影响记录数[{}]条", this.getClass().getSimpleName(),
						id, num);
			}catch(Exception e) {
				logger.error("[{}] 可选新增记录失败, 请求参数为[{}], 失败原因[{}]", this.getClass().getSimpleName(),
						id, e);
				throw e;
			}
			return getMapper().deleteByPrimaryKey(id);
		}
		return -1;
	}
	
	/**
	 * 获取当前mapper
	 * @return
	 */
	public Mapper<T> getMapper(){
		return this.mapper;
	};
	
}
