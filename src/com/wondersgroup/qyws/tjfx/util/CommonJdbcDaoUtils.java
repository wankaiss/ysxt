package com.wondersgroup.qyws.tjfx.util;

/** 
 * @(#)CommonDAOUtils.java Jun 2, 2010
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


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.wondersgroup.qyws.tjfx.dao.CommonJdbcDao;
import com.wondersgroup.qyws.tjfx.dao.impl.CommonJdbcDaoImpl;

/**
 * @author xieguojun
 * @version $Revision$ Jun 2, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class CommonJdbcDaoUtils {
	
	public CommonJdbcDaoUtils(CommonJdbcDao commonJdbcDAO){
		
		CommonJdbcDaoUtils.commonJdbcDAO = commonJdbcDAO;
	}
	 
	
	/**
	 * 用于查询基本类型数据
	 * @param <R>
	 * @param sql
	 * @param clazz
	 * @param arguments
	 * @return
	 */
	public static <R> R queryObject(String sql,Class<R> clazz,Object ...arguments){
		
		return getCommonJdbcDAO().queryObject(sql, clazz, arguments);
	}
	/**
	 * 查询数据集合，不封装为对象，是一个Map结构
	 * 
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public static List<Map<String,Object>> queryForList(String sql,Object ...arguments){
		return getCommonJdbcDAO().queryForList(sql, arguments);
	}
	
	/**
	 * 
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public static Map<String,Object> queryForMap(String sql,Object ...arguments){
		return getCommonJdbcDAO().queryForMap(sql, arguments);
	}
	
	/**
	 * 执行SQL更新语句，返回影响的记录数 
	 * @param <R>
	 * @param sql	 
	 * @param arguments
	 * @return
	 */
	public static <R> int update(String sql,Object ...arguments){
		
		return getCommonJdbcDAO().update(sql, arguments);
	}
	public static void delete(String sql){
		getCommonJdbcDAO().delete(sql);
	}
	/**
	 * 执行SQL更新语句
	 * @author wuchunying
	 * @return
	 */
	public static void insert(String sql){
		
		getCommonJdbcDAO().insert(sql);
	}
	
	private static CommonJdbcDao commonJdbcDAO;

	public static CommonJdbcDao getCommonJdbcDAO() {
		return commonJdbcDAO;
	}

	public static void setCommonJdbcDAO(CommonJdbcDao commonJdbcDAO) {
		CommonJdbcDaoUtils.commonJdbcDAO = commonJdbcDAO;
	}
	

	public static Connection getConnection() {
		try {
			return DataSourceUtils
					.getConnection(((CommonJdbcDaoImpl) getCommonJdbcDAO())
							.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
}
