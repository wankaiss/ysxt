package com.wondersgroup.qyws.tjfx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wonders.pdcdc.common.pagination.PaginationRequest;
import com.wonders.pdcdc.common.pagination.PaginationResult;

public interface Service {

	/**
	 * 组合查询
	 * 
	 * @param detachedCriteria
	 *            组合查询对象
	 * @return List 查询结果列表
	 */
	public List findAllByCriteria(final DetachedCriteria detachedCriteria);
	
	/**
	 * 分页查询
	 * @param  PaginationRequest 分页对象
	 * @return 返回所有未授权机构列表
	 * @throws Exception
	 */
	public PaginationResult findPageListByCriteria(PaginationRequest pr,final DetachedCriteria detachedCriteria) throws Exception;
	
	/**
	 * 查询某一个对象
	 * @param detachedCriteria
	 */
	public Object getObjectByCriteria(final DetachedCriteria detachedCriteria);
	
	/**
	 * 保存或更新对象
	 */
	public void saveObject(Object object);
	
	/**
	 * 查找某一个对象
	 */
   public Object getObjectById(Class entityClass,Object id);
   
   /*
    * 删除对象
    */
   public void deleteObject(Object object);
}
