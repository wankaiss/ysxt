package com.wondersgroup.qyws.tjfx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.pdcdc.common.pagination.PaginationRequest;
import com.wonders.pdcdc.common.pagination.PaginationResult;
import com.wondersgroup.qyws.tjfx.common.BaseDao;
import com.wondersgroup.qyws.tjfx.service.Service;

@Transactional
@Scope("prototype")
public class ServiceImpl implements Service{
	
	@Autowired
    @Qualifier("baseDao")
	public BaseDao baseDao;
	
	/**
	 * 组合查询
	 * 
	 * @param detachedCriteria
	 *            组合查询对象
	 * @return List 查询结果列表
	 */
	public List findAllByCriteria(final DetachedCriteria detachedCriteria){
		 try{
			 return baseDao.findAllByCriteria(detachedCriteria);
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
			 return null;
		 }
		
	}
	
	/**
	 * 分页查询
	 * @param  PaginationRequest 分页对象
	 * @return 返回所有未授权机构列表
	 * @throws Exception
	 */
	public PaginationResult findPageListByCriteria(PaginationRequest pr,final DetachedCriteria detachedCriteria) throws Exception{
		
		int size=this.baseDao.findCountByCriteria(detachedCriteria);
		List list=this.baseDao.findPageListByCriteria(pr.getStartRow(), pr.getPageSize(), detachedCriteria);	
		return new PaginationResult(size,list,pr);
	}
	
	public Object getObjectByCriteria(final DetachedCriteria detachedCriteria){
		List list=baseDao.findAllByCriteria(detachedCriteria);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 保存或更新对象
	 */
	public void saveObject(Object object){
		
		this.baseDao.saveObject(object);
	}
	
	/**
	 * 查找某一个对象
	 */
   public Object getObjectById(Class entityClass,Object id){
	   if(id==null){
		   return null;
	   }
	   return this.baseDao.getObjectById(entityClass, id);
   }
   
   /*
    * 删除对象
    */
   public void deleteObject(Object object){
	   
	   this.baseDao.deleteEntity(object);
   }
	
}
