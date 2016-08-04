package com.wondersgroup.qyws.task.dao;

import java.util.List;

import com.wondersgroup.qyws.task.module.TbTaskDetail;
import com.wondersgroup.qyws.task.module.TbTaskDetailDic;

public interface IDaoSupport {
	
	public void insert(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	
	public List<?> queryAll(String hql);
	
	public List<?> queryAllBySQL(String sql);
	
	public TbTaskDetail queryById(String id);
	
	public TbTaskDetailDic queryDicById(String id);
	
}