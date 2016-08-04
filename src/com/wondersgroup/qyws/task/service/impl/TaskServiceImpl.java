package com.wondersgroup.qyws.task.service.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.qyws.task.dao.IDaoSupport;
import com.wondersgroup.qyws.task.module.TbTaskDetail;
import com.wondersgroup.qyws.task.module.TbTaskDetailDic;
import com.wondersgroup.qyws.task.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private IDaoSupport daoSupport;
	
	public List<?> queryAll() {
		return this.daoSupport.queryAllBySQL("SELECT T.*,I.*,to_char(I.TASK_CONFIG) as TCONFIG,to_char(I.TASK_CONFIG_VALUE) as TCONFIGVALUE FROM QRTZ_TRIGGERS T LEFT JOIN TB_TASK_DETAIL I ON T.TRIGGER_NAME = I.DATA_ID");
	}
	
	public void delTaskByList(List delList)
	{
		//delTask
		for(int i=0;i<delList.size();i++)
		{
			this.delTask(delList.get(i).toString());
		}
	}
	
	
	//write by wyh.根据字典数据增加task.
	public List addTaskByDicList(List dics)
	{
		List insertTaskIds=new ArrayList();
		try
		{
			for(int i=0;i<dics.size();i++)
			{
				insertTaskIds.add(this.addTaskReturnId(this.getDicTaskObj(dics.get(i).toString())));
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return insertTaskIds;
		
	}
	
	//write by wyh.获取字典表task信息.
	private TbTaskDetail getDicTaskObj(String taskId)
	{
		
		TbTaskDetail detail = new TbTaskDetail();
		TbTaskDetailDic tbtaskdetaildic = new TbTaskDetailDic();
		tbtaskdetaildic=this.daoSupport.queryDicById(taskId);
		detail.setGroupName(tbtaskdetaildic.getGroupName());
		detail.setLevelIndex(tbtaskdetaildic.getLevelIndex());
		detail.setStartTime(new Date());
		detail.setTaskDesc(tbtaskdetaildic.getTaskDesc());
		detail.setTaskExpression(tbtaskdetaildic.getTaskExpression());
		detail.setTaskMean(tbtaskdetaildic.getTaskMean());
		detail.setTaskPath("com.wondersgroup.qyws.task.impl.ListenerJob");
		detail.setMethod(tbtaskdetaildic.getMethod());
		detail.setTaskConfig(tbtaskdetaildic.getTaskConfig());
		detail.setTaskConfigValue(tbtaskdetaildic.getTaskConfigValue());
		
//		TB_TASK_DETAIL_DIC tb_task_detail_dic=new TB_TASK_DETAIL_DIC();
//		tb_task_detail_dic=this.getTaskDetailDic(taskId);
//		detail.setGroupName(tb_task_detail_dic.getGroup_name());
//		detail.setLevelIndex(tb_task_detail_dic.getLevel_index());
//		detail.setStartTime(new Date());
//		detail.setTaskDesc(tb_task_detail_dic.getTask_desc());
//		detail.setTaskExpression(tb_task_detail_dic.getTask_expression());
//		detail.setTaskMean(tb_task_detail_dic.getTask_mean());
//		detail.setTaskPath("com.wondersgroup.qyws.task.impl.ListenerJob");
//		detail.setMethod(tb_task_detail_dic.getTask_method());
//		detail.setTaskConfig(tb_task_detail_dic.getTask_config().toString());
//		detail.setTaskConfigValue(tb_task_detail_dic.getTask_config_value().toString());
		return detail;
	}
	
	//write by wyh.插入task并返回ID.
	public String addTaskReturnId(TbTaskDetail detail)
	{
		String id = UUID.randomUUID().toString().replace("-","");
		try{
			
			detail.setDataId(id);  
			detail.setTriggerId(id);
			Class<?> cls = Class.forName(detail.getTaskPath());
			JobDetail jobDetail = new JobDetail(id,detail.getGroupName(),cls);
			CronTrigger cronTrigger = new CronTrigger(id,detail.getGroupName(),jobDetail.getName(),jobDetail.getGroup(),detail.getTaskExpression());
			if(null != detail.getStartTime()){
				cronTrigger.setStartTime(detail.getStartTime());
			}
			this.scheduler.scheduleJob(jobDetail,cronTrigger);
			this.daoSupport.insert(detail);
			
		}
		catch(UndeclaredThrowableException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return id;
	}
	
	
	public void addTask(TbTaskDetail detail) throws ClassNotFoundException, ParseException, SchedulerException {
		String id = UUID.randomUUID().toString().replace("-","");
		detail.setDataId(id);
		detail.setTriggerId(id);
		Class<?> cls = Class.forName(detail.getTaskPath());
		JobDetail jobDetail = new JobDetail(id,detail.getGroupName(),cls);
		CronTrigger cronTrigger = new CronTrigger(id,detail.getGroupName(),jobDetail.getName(),jobDetail.getGroup(),detail.getTaskExpression());
		if(null != detail.getStartTime()){
			cronTrigger.setStartTime(detail.getStartTime());
		}
		this.scheduler.scheduleJob(jobDetail,cronTrigger);
		this.daoSupport.insert(detail);
	}

	public void editTask(TbTaskDetail detail) {
		this.daoSupport.update(detail);
	}

	public void pauseTrigger(String id, String group) {
		try {
			this.scheduler.pauseTrigger(id, group);// 停止触发器
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void resumeTrigger(String id, String group) {
		try {
			this.scheduler.resumeTrigger(id,group);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void saveConfig(String id, String config) throws Exception {
		try {
			TbTaskDetail detail = this.daoSupport.queryById(id);
			detail.setTaskConfigValue(config);
			JSONObject obj = JSONObject.fromObject(config);
			if(obj.containsKey("JCDJ")){
				String value = obj.getString("JCDJ");
				detail.setLevelIndex(value);
			}
			if(obj.containsKey("JCSDSZ")){
				String value = obj.getString("JCSDSZ");
				detail.setTaskMean(value);
			}
			if(obj.containsKey("YXPL")){
				String value = obj.getString("YXPL");
				
				detail.setTaskExpression(value);
				detail.setStartTime(new Date());
				
				this.scheduler.pauseTrigger(detail.getDataId(),detail.getGroupName());
				this.scheduler.unscheduleJob(detail.getDataId(),detail.getGroupName());
				this.scheduler.deleteJob(detail.getDataId(),detail.getGroupName());
				
				Class<?> cls = Class.forName(detail.getTaskPath());
				JobDetail jobDetail = new JobDetail(id,detail.getGroupName(),cls);
				CronTrigger cronTrigger = new CronTrigger(id,detail.getGroupName(),jobDetail.getName(),jobDetail.getGroup(),value);
				if(null != detail.getStartTime()){
					cronTrigger.setStartTime(detail.getStartTime());
				}
				this.scheduler.scheduleJob(jobDetail,cronTrigger);
				
			}
			this.daoSupport.update(detail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public void delTask(String id) {
		try {
			TbTaskDetail detail = this.daoSupport.queryById(id);
			this.scheduler.pauseTrigger(detail.getDataId(),detail.getGroupName());
			this.scheduler.unscheduleJob(detail.getDataId(),detail.getGroupName());
			this.scheduler.deleteJob(detail.getDataId(),detail.getGroupName());
			this.daoSupport.delete(detail);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}