package com.wondersgroup.qyws.tjfx.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wondersgroup.qyws.tjfx.module.DbConfig;
import com.wondersgroup.qyws.tjfx.util.CommonUtil;
import com.wondersgroup.qyws.tjfx.util.DBUtil;
import com.wondersgroup.util.papper.PageSet;

/**
 * BaseDao 针对多条件搜索，封装的Dao
 * 查询条件where 其格式  条件&语句判断符
 * 对应参数values 与where条件1 === 1对应
 * */
@Repository("baseDao")
public class BaseDao extends HibernateDaoSupport{

	//针对单个表查询，orderby 单张表
	private static final String ORDERBY_SINGLE = "single";
	
	//针对多个表查询，orderby 不同表
	private static final String ORDERBY_MULTI = "multi";
	
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public PageSet queryBySql(PageSet ps, String sql,String countSql)
	{
		//int index = sql.indexOf(" from ");
		Query query=this.getSession().createSQLQuery(sql);
		Query queryCount = this.getSession().createSQLQuery(countSql);
		query.setMaxResults(ps.getLimit());
		query.setFirstResult(ps.getStart());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List ctList=queryCount.list();
		if(ctList!=null && ctList.size()>0){
			ps.setCount(Integer.parseInt(ctList.get(0).toString()));
		}
		else{
			ps.setCount(0);
		}
		ps.setData(query.list());
		return ps;
	}
	
	public PageSet queryBySqlSpecial(PageSet ps,String sqlTotal,String sqlDetail,String countSql,String key){
		Query queryTotal=this.getSession().createSQLQuery(sqlTotal);
		Query queryCount = this.getSession().createSQLQuery(countSql);
		Query queryDetail=this.getSession().createSQLQuery(sqlDetail);
		queryTotal.setMaxResults(ps.getLimit());
		queryTotal.setFirstResult(ps.getStart());
		queryTotal.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		queryDetail.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List listTotal=queryTotal.list();
		List listDetail=queryDetail.list();
		List<Map<String, String>> returnData=new ArrayList<Map<String,String>>();
			
		for(int i=0;i<listTotal.size();i++){
			Map<String, String> mapTotal=(Map<String, String>) listTotal.get(i);
			boolean point=false;
			boolean inner=false;
			Map<String, String> newMap=new HashMap<String, String>();
			for(int j=0;j<listDetail.size();j++){
				Map<String, String> mapDetail=(Map<String, String>) listDetail.get(j);
				if(mapTotal.get(key).toString().trim().equals(mapDetail.get(key).toString().trim())){				
					if(point==false){                                                 						
						Iterator iterator=mapDetail.entrySet().iterator();
						while(iterator.hasNext()){
							Map.Entry entry=(Entry)iterator.next();
							Object myKey=entry.getKey();
							Object value=entry.getValue();
							if(value==null){
								value="";
							}
							newMap.put(myKey.toString()+'1', value.toString());
						}
						point=true;
					}else{
						Iterator iterator=mapDetail.entrySet().iterator();
						if(inner==false){
							while(iterator.hasNext()){
								Map.Entry entry=(Entry)iterator.next();
								Object myKey=entry.getKey();
								Object value=entry.getValue();
								if(value==null){
									value="";
								}
								newMap.put(myKey.toString(), value.toString());
								inner=true;
							}
						}else{
							while(iterator.hasNext()){
								Map.Entry entry=(Entry)iterator.next();
								Object myKey=entry.getKey();
								Object value=entry.getValue();
								if(value==null){
									value="";
								}
								Object valueOld=newMap.get(myKey.toString());
								String valueNew=valueOld.toString()+"</br>"+value.toString();
								newMap.put(myKey.toString(), valueNew);
							}
						}
						}				
						}
					}
			returnData.add(newMap);
				}
			
		if(queryCount.list().size()>0){
			ps.setCount(Integer.parseInt(queryCount.list().get(0).toString()));
		}
		ps.setData(returnData);
		return ps;	
	}
	
	/**
	 * 根据hibernate查询hql
	 * @param sql
	 * @return
	 */
	public List getListByHql(String sql){
		return this.getHibernateTemplate().find(sql);
	}
	
	/**
	 * 根据hql执行更新语句
	 */
	public void executeUpdateByHql(String sql){
		this.getSession().createQuery(sql).executeUpdate();
	}
	
	/**
	 * 根据hql执行更新语句
	 */
	public void executeUpdateBySql(String sql){
		this.getSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * sql語句查询集合 ,返回结果是以别名,value为键值对Map的List
	 * @param sql
	 * @return boolean
	 */
	public List findListOfMapBySql(final String sql) throws Exception{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
	}

	
	/**
	 * sql語句查询集合 ,返回结果是以别名,value为键值对Map的List
	 * @param sql
	 * @return boolean
	 */
	public List findListOfMapBySql(final String sql,final HttpServletRequest request) throws Exception{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String finalSql=sql;
				
				if(request.getSession().getAttribute("db")!=null){
					Connection conn=null; 
					Statement stmt =null;
					ResultSet rs =null;
					DbConfig config = (DbConfig)request.getSession().getAttribute("db");

					try{
						conn = DBUtil.getConnectionByDb(config);
						stmt = conn.createStatement(); 
						rs = stmt.executeQuery(finalSql);
						List list = DBUtil.resultSetToList(rs);// session.getSessionFactory().openSession(connection).createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
						return list;
					}
					catch(Exception ex){
						ex.printStackTrace();
						return null;
					}
					finally{
						rs.close();
						stmt.close();
						conn.close();
					}
				}
				else{
					return session.createSQLQuery(finalSql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				}
			}
		});
	}
	
	public List getListBySql(final int startRow,final int endRow,final String sql)throws Exception{
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).setFirstResult(startRow).setMaxResults(endRow).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
	}
	
public List getPageListBySql(final int startRow,final int endRow,final String sql)throws Exception{
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).setFirstResult(startRow).setMaxResults(endRow).setResultTransformer(Transformers.TO_LIST).list();
			}
		});
	}
	
	public List getListBySql(final int startRow,final int endRow,final String sql,final HttpServletRequest request)throws Exception
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String finalSql=sql;
				if(request.getSession().getAttribute("db")!=null){
					Connection conn=null; 
					Statement stmt =null;
					ResultSet rs =null;
					DbConfig config = (DbConfig)request.getSession().getAttribute("db");

					try{
						conn = DBUtil.getConnectionByDb(config);
						stmt = conn.createStatement(); 
						stmt.setMaxRows(endRow);
						rs = stmt.executeQuery(finalSql);
						rs.absolute(startRow * endRow);
						List list = DBUtil.resultSetToList(rs);// session.getSessionFactory().openSession(connection).createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
						return list;
					}
					catch(Exception ex){
						ex.printStackTrace();
						return null;
					}
					finally{
						rs.close();
						stmt.close();
						conn.close();
					}
				}
				else{
					return session.createSQLQuery(finalSql).setFirstResult(startRow).setMaxResults(endRow).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
					//return session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				}
			}
		});
	}
	
	public List getCommonListBySql(String sql){
		Query query = this.getSession().createSQLQuery(sql);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	public List getListBySql(String sql){
		Query query = this.getSession().createSQLQuery(sql);
		return query.list();
	}
	
	public List getListBySql(String sql,Class cl){
		Query query = this.getSession().createSQLQuery(sql).addEntity(cl);
		return query.list();
	}
//	public Map<String, String> getMapBySql(String sql,String key){
//		Query query=this.getSession().createSQLQuery(sql);
//		Map<String,String> map=new HashMap<String, String>();
//		map.put(key, query.list().toString());
//		return map;
//	}

	public int getListSizeBySql(String sql)throws Exception{
		Query query = this.getSession().createSQLQuery(sql);
		List list =query.list();
		if(list!=null && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	public int getListSizeBySql(String sql,HttpServletRequest request)throws Exception{
		
		String finalSql=sql;
	
		if(request.getSession().getAttribute("db")!=null){
			Connection conn=null; 
			Statement stmt =null;
			ResultSet rs =null;
			DbConfig config = (DbConfig)request.getSession().getAttribute("db");

			try{
				conn = DBUtil.getConnectionByDb(config);
				stmt = conn.createStatement(); 
				rs = stmt.executeQuery(finalSql);
				while (rs.next()){
					return Integer.parseInt(rs.getString("0"));
				}
				return 0;
			}
			catch(Exception ex){
				ex.printStackTrace();
				return 0;
			}
			finally{
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		else{
			Query query = this.getSession().createSQLQuery(finalSql);
			List list =query.list();
			if(list!=null && list.size()>0){
				return Integer.parseInt(list.get(0).toString());
			}
			return 0;
		}
	}
	
	/*
	 * HQL语句
	 * ====================================================================
	 * =============================
	 */
	/**
	 * 获取指定表的集合
	 * */
	@SuppressWarnings("rawtypes")
	public List getObjAllList(Class entity, String[] orderby) {
		StringBuffer hql = new StringBuffer(" from " + entity.getName() + " s");
		hql.append(disposeOrderBy(orderby, ORDERBY_SINGLE));
		return this.getHibernateTemplate().find(hql.toString());
	}

	/**
	 * 指定hql和参数查询列表
	 * */
	@SuppressWarnings("rawtypes")
	public List getHqlList(String hql, Object... values) {
		return this.getHibernateTemplate().find(hql, values);
	}

	/**
	 * 根据条件及条件值获得指定表对象
	 * */
	@SuppressWarnings("rawtypes")
	public Object getObjByParam(Class entity, String where, Object... values) {
		String hql = " from " + entity.getName() + " s where 1=1 ";
		hql.concat(disposeWhereAndParam(where, values));
		Query query = getSession().createQuery(hql);
		return query.uniqueResult();
	}

	/**
	 * 根据条件及条件值获得指定表集合
	 * */
	@SuppressWarnings("rawtypes")
	public List getObjHqlPagerListByParam(Class entity, String where,
			int firstResult, int maxResult, String[] orderby, Object... values) {
		String hql = " from " + entity.getName() + " s where 1=1 ";
		hql.concat(disposeWhereAndParam(where, values));
		hql.concat(disposeOrderBy(orderby, ORDERBY_SINGLE));
		Query query = getSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	/**
	 * 处理hql，返回唯一对象
	 * */
	public Object getHqlObj(final String hql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = createHqlQuery(s, hql, values);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * 分页，执行hql语句
	 * */
	@SuppressWarnings("rawtypes")
	public List getHqlListByPager(String hql, int firstResult,
			int maxResult, String[] orderby, String where, Object... values) {
		hql.concat(disposeWhereAndParam(where, values));
		hql.concat(disposeOrderBy(orderby, ORDERBY_MULTI));
		Query query = getSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	/*
	 * SQL语句
	 * ====================================================================
	 * =============================
	 */

	/**
	 * 处理sql，返回唯一对象
	 * */
	public Object getSqlObj(final String sql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = createSqlQuery(s, sql, values);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * 分页，执行sql语句
	 * */
	@SuppressWarnings("rawtypes")
	public List getSqlListByPager(String sql, String where, int firstResult,
			int maxResult, String[] orderby, Object... values) {
		sql.concat(disposeWhereAndParam(where, values));
		sql.concat(disposeOrderBy(orderby, ORDERBY_MULTI));
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}
	
	

	/*
	 * 工具
	 * ========================================================================
	 * =========================
	 */

	/**
	 * 工具 对参数进行处理
	 * */
	public String disposeWhereAndParam(String where, Object... values) {
		StringBuffer wherehql = new StringBuffer();
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (StringUtils.isNotBlank(where)) {
			if (where.endsWith("}")){
				String end = where.substring(where.lastIndexOf("{") + 1, where.lastIndexOf("}"));
				where = where.substring(0, where.indexOf("{") - 1);
				String[] en = end.split(",");
				for (String str : en){
					Integer tjStart = new Integer(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
					String tjEnd = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
					map.put(tjStart, tjEnd);
				}
			}
			
			String[] sp = where.split(",");
			if (sp.length != values.length) {
				System.out.println("条件和参数个数不一致！！");
				return "error";
			}
			boolean con = false;
			for (int i = 0; i < sp.length; i++) {
				if (StringUtils.isNotEmpty(sp[i]) && values[i] != null) {
					if (sp[i].contains("&")) {
						String propertyPrefix = StringUtils.substringBefore(
								sp[i], "&");
						String propertySuffix = StringUtils.substringAfter(
								sp[i], "&");
						if (StringUtils.isBlank(propertySuffix)) {
							System.out.println("条件格式不正确！！ &后面缺少条件！");
							return "error";
						}
						if (!"=".equals(propertySuffix)
								&& !"=".equals(propertySuffix)
								&& !">".equals(propertySuffix)
								&& !"<".equals(propertySuffix)
								&& !"<=".equals(propertySuffix)
								&& !">=".equals(propertySuffix)
								&& !"<>".equals(propertySuffix)
								&& !"!=".equals(propertySuffix)
								&& !"like".equals(propertySuffix)) {
							System.out.println("语句错误，值比较符号只包含'=','>','<','<=','>=','<>','!=','like'");
							return "error";
						}
						if (values[i] instanceof String
								|| values[i] instanceof Integer
								|| values[i] instanceof Float
								|| values[i] instanceof Boolean
								|| values[i] instanceof Short
								|| values[i] instanceof Double
								|| values[i] instanceof Long
								|| values[i] instanceof BigDecimal
								|| values[i] instanceof BigInteger
								|| values[i] instanceof Byte
								|| values[i] instanceof Date) {
							if (map != null && map.size() != 0){
								if (StringUtils.isNotBlank(map.get(i))){
									String pd = "values[i] != null && !''.equals(values[i]) " + map.get(i);
									Boolean bool = new Boolean(pd);
									System.out.println("bool  " + bool);
									if (bool) {
										con = true;
									}
								}
							} else {
								if (values[i] != null && !"".equals(values[i])) {
										con = true;
								}
							}
						}
						if (con) {
							wherehql.append(" and " + propertyPrefix + " "
									+ propertySuffix);
							if ("like".equals(propertySuffix)
									|| values[i] instanceof String) {
								wherehql.append(" '" + values[i] + "'");
							} else {
								wherehql.append(" " + values[i]);
							}
						}

					} else {
						System.out.println("条件格式不正确！！");
						return "error";
					}

				}
			}
			System.out.println("当前语句： " + wherehql.toString());
		}
		return wherehql.toString();
	}

	/**
	 * 工具 对排序参数处理
	 * sign 标识
	 * */
	public String disposeOrderBy(String[] orderby, String sign) {
		StringBuffer hql = new StringBuffer();
		if (orderby != null && orderby.length != 0) {
			hql = hql.append(" Order by ");
			if (ORDERBY_MULTI.equals(sign)){
				for (String str : orderby) {
					hql.append(str + ",");
				}
			} else if (ORDERBY_SINGLE.equals(sign)) {
				for (String str : orderby) {
					hql.append(" s." + str + ",");
				}
			}
			hql.deleteCharAt(hql.length() - 1);
		}
		return hql.toString();
	}

	/**
	 * 工具 根据查询条件与参数列表创建Query对象
	 */
	public Query createHqlQuery(Session session, String hql, Object... objects) {
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	/**
	 * 工具 根据查询条件与参数列表创建Query对象
	 */
	public Query createSqlQuery(Session session, String sql, Object... objects) {
		Query query = session.createSQLQuery(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	/**
	 * 调用存储过程
	 * */
	@SuppressWarnings("deprecation")
	public Object execProc(String procName) {
		Object ret = null;
		String procedure = "{call dbo." + procName + "() }";
		CallableStatement cstmt;
		try {
			Connection con = this.getSession().connection();
			cstmt = con.prepareCall(procedure);
			ret = cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	/**
	 * sql語句查询集合 ,返回结果是className对象的List
	 * @param sql
	 * @return boolean
	 */
	public List findListOfObjectBySql(final String sql, final Class className) throws ServiceException{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(className)).list();
			}
		});
	}
	
	/**
	 * 根据userId,resId 取得模板文件名(含.xml)
	 * @param userId 用户id
	 * @param resId 资源id
	 * @return String
	 */
	public Object findTemplFileName(Long userId, String resId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select new TbTjfxResTemplate(b.id) from TbTjfxRoleRes a, TbTjfxResTemplate b, TbTjfxUserRole c ")
		   .append("where a.roletableid = c.roleid and a.restableid = b.id and c.userid = ? and b.resid = ?");
		Object template = getHqlObj(hql.toString(), new Object[]{userId, Long.parseLong(resId)});
		return template;
	}
	
	/**
	 * 获取总条数
	 * 
	 * @param detachedCriteria
	 *            组合查询对象
	 * @return int 总条数
	 */
	public int findCountByCriteria(final DetachedCriteria detachedCriteria){
		
		return this.getHibernateTemplate().findByCriteria(detachedCriteria).size();
	}
	
	/**
	 * 组合查询
	 * 
	 * @param detachedCriteria
	 *            组合查询对象
	 * @return List 查询结果列表
	 */
	@SuppressWarnings("unchecked")
	public List findAllByCriteria(final DetachedCriteria detachedCriteria){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
	
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				List list = criteria.list();
				return criteria.list();
				}
				
			}, true);
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List findPageListByCriteria(final int startRow,final int endRow,final DetachedCriteria detachedCriteria)throws Exception{
		return (List) getHibernateTemplate().execute(new HibernateCallback() {   
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
			
			    Criteria criteria = detachedCriteria.getExecutableCriteria(session); 
			
	            return criteria.setFirstResult(startRow).setMaxResults(endRow).list(); 
		    }
        });
	}
	
	/**
	 * 保存或更新对象
	 */
	public void saveObject(Object object){
		
		this.getHibernateTemplate().saveOrUpdate(object);
	}
	/**
	 * 根据主键查找实体对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object getObjectById(Class entityClass,Object id){
		
		
		return this.getHibernateTemplate().get(entityClass, (Serializable) id);
	}
	
	public List<?> queryBySQL(String sql,Map<String,Object> params){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(null != params && !params.isEmpty() && params.size()>0){
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Object value = params.get(key);
				query.setParameter(key,value);
			}
		}
		return query.list();
	}
	
	public List<?> queryByHQL(String hql,Map<String,Object> params) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		if(null != params && !params.isEmpty()){
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Object value = params.get(key);
				query.setParameter(key,value);
			}
		}
		return query.list();
	}
	
	public PageSet queryByHQL(PageSet ps, String hql, Map<String, Object> params) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		Query queryCount = session.createQuery(hql);
		query.setMaxResults(ps.getLimit());
		query.setFirstResult(ps.getStart());
		if(null != params && !params.isEmpty()){
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Object value = params.get(key);
				query.setParameter(key,value);
			}
		}
		if(null != params && !params.isEmpty()){
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				Object value = params.get(key);
				queryCount.setParameter(key,value);
			}
		}
		ps.setCount(queryCount.list().size());
		ps.setData(query.list());
		return ps;
	}
	
	
	
	/**
	 * 删除对象
	 * @param object 对象
	 * @return
	 */
	public void deleteEntity(Object object){
		this.getHibernateTemplate().delete(object);
	}
	
	/**
	 * test
	 * */
	public static void main(String[] args) {
		BaseDao base = new BaseDao();
		String tj1 = "hello";
		int tj2 = 22;
		Date tj3 = new Date();
		Date tj4 = new Date();
		String tj5 = "world";
		String tj6 = "end";
		System.out.println("一般情况-------------------");
		base.disposeWhereAndParam(
				"a.pid&like,b.age&<,c.startsj&>=,d.startsj&<=,e.xm&=",
				new Object[] { tj1, tj2, tj3, tj4, tj5 });
		System.out.println("针对1个参数，带多个条件判断- 目前占未实现------------------");
		base.disposeWhereAndParam(
				"a.pid&like,b.age&>,e.xm&=,f.xb&=,{[0]- && values[i] != 'all' - ,[1]- && values[i] > 15 -}",
				new Object[] { tj1, tj2, tj3, tj6 });
	}

	/**
	 * 获取序列值
	 * @param sequenceName
	 * @return
	 */
	public String getSequenceValue(String sequenceName) {
		Dialect dialect = ((SessionImpl) getSession()).getFactory().getDialect();
		String queryString = dialect.getSequenceNextValString(sequenceName);
		SQLQuery query = getSession().createSQLQuery(queryString);
		return String.valueOf(query.uniqueResult());
	}
}