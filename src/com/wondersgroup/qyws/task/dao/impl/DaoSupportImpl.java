package com.wondersgroup.qyws.task.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wondersgroup.qyws.task.dao.IDaoSupport;
import com.wondersgroup.qyws.task.module.TbTaskDetail;
import com.wondersgroup.qyws.task.module.TbTaskDetailDic;

@Repository
public class DaoSupportImpl implements IDaoSupport{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void update(Object obj) {
		
		Session session = this.sessionFactory.getCurrentSession();
		session.update(obj);
	}

	public void insert(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(obj);
	}

	public List<?> queryAll(String hql) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<?> result = query.list();
		return result;
	}

	public List<?> queryAllBySQL(String sql) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> result = query.list();
		return result;
	}

	public TbTaskDetail queryById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object obj = session.get(TbTaskDetail.class,id);
		if(null != obj){
			return (TbTaskDetail)obj; 
		}
		return null;
	}
	
	public TbTaskDetailDic queryDicById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object obj = session.get(TbTaskDetailDic.class,id);
		if(null != obj){
			return (TbTaskDetailDic)obj; 
		}
		return null;
	}

	public void delete(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(obj);
	}
	
}