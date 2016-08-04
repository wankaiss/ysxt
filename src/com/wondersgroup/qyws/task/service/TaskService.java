package com.wondersgroup.qyws.task.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import com.wondersgroup.qyws.task.module.TbTaskDetail;

public interface TaskService {
	
//	public List<?> queryAllByUserJS(List<TB_YSXT_USER_RWJS> userJs);
	
	public String addTaskReturnId(TbTaskDetail detail);
	
	public List<?> queryAll();
	
	public void addTask(TbTaskDetail detail) throws ClassNotFoundException, ParseException, SchedulerException;
	
	public void editTask(TbTaskDetail detail);
	
	public void saveConfig(String id,String config) throws Exception ;
	
	public void pauseTrigger(String id,String group);

	public void resumeTrigger(String id,String group);
	
	public void delTask(String id);
	
	public List addTaskByDicList(List dics);
	
	public void delTaskByList(List delList);
}