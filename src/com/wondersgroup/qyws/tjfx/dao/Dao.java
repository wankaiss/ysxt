package com.wondersgroup.qyws.tjfx.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface Dao {

	void saveObject(Object object);
	
	void deleteEntity(Object object);
	
	public List getChartListBySql(final String sql)throws Exception;
	
	public List findListOfMapBySql(final String sql)throws Exception;
	
	public List findListOfMapBySql(final String sql,HttpServletRequest request)throws Exception;
	
	public List getCommonListBySql(String sql);
	
	/**
	 * 根据hibernate查询hql
	 * @param sql
	 * @return
	 */
	public List getListByHql(String sql);
	
	public List getListBySql(final int startRow,final int endRow,final String sql,HttpServletRequest request)throws Exception;
	
	public List getListBySql(final int startRow,final int endRow,final String sql)throws Exception;

	public int getListSizeBySql(String sql)throws Exception;
	
	public int getListSizeBySql(String sql,HttpServletRequest request)throws Exception;
	
	public Object findTemplFileName(Long userId, String resId);
	
	Object getObjectById(Class entityClass,Object id);
}
