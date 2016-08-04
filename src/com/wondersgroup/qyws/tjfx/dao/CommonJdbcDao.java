/** 
 * @(#)CommonDAO.java Jun 2, 2010
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

package com.wondersgroup.qyws.tjfx.dao;

import java.util.List;
import java.util.Map;

/**
 * @author xieguojun
 * @version $Revision$ Jun 2, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public interface CommonJdbcDao{	
	
	/**
	 * 执行SQL更新语句，返回影响的记录数 
	 * @param <R>
	 * @param sql	 
	 * @param arguments
	 * @return
	 */
	public int update(String sql, Object... arguments);
	
	/**
	 * 用于查询基本类型数据
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 */
	public <R> R queryObject(String sql, Class<R> clazz, Object... arguments);
	
	public void insert(String sql);
	public void delete(String sql);
	
	
	List<Map<String,Object>> queryForList(String sql,Object... arguments);
	
	Map<String,Object> queryForMap(String sql,Object... arguments);

 
}
