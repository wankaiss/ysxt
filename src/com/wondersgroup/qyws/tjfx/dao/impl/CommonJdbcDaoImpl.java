/** 
 * @(#)CommonDAOImpl.java Jun 2, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */
package com.wondersgroup.qyws.tjfx.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wondersgroup.qyws.tjfx.dao.CommonJdbcDao;

/**
 * @author xieguojun
 * @version $Revision$ Jun 2, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class CommonJdbcDaoImpl extends JdbcDaoSupport implements CommonJdbcDao{
	
	/** 
	 * @see com.wondersgroup.wssip.commons.dao.CommonDAO#update(java.lang.String, java.lang.Object[])
	 */
	public int update(String sql,  Object... arguments) {
		
		int result =getJdbcTemplate().update(sql,arguments); 
		
		return result;
	}
	
	/**
	 * 用于查询基本类型数据
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> R queryObject(String sql, Class<R> clazz, Object... arguments){
		try{
			return (R)getJdbcTemplate().queryForObject(sql, arguments, clazz);
		}
	    catch(Exception ex){
	    	return null;
	    }		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryForList(String sql,Object... arguments){
		return getJdbcTemplate().queryForList(sql, arguments);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryForMap(String sql,Object... arguments){
		return getJdbcTemplate().queryForMap(sql, arguments);
	}
	
	
	public void insert(String sql){
		getJdbcTemplate().execute(sql);
	}
	public void delete(String sql){
		getJdbcTemplate().execute(sql);
	}

}
