package com.wondersgroup.qyws.task.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;

import com.wondersgroup.qyws.task.BaseJob;
import com.wondersgroup.qyws.task.annotation.MyJob;
import com.wondersgroup.qyws.task.dao.IDaoSupport;
import com.wondersgroup.qyws.task.module.TbTaskDetail;
import com.wondersgroup.qyws.tjfx.service.YwjcService;
import com.wondersgroup.util.spring.ApplicationContextUtils;

@MyJob(name="监控任务",description="监控任务")
public class ListenerJob extends BaseJob{
	
	@Override
	protected void run(JobExecutionContext context) {
		this.log.debug("TASK RUNING");

		IDaoSupport daoSupport = (IDaoSupport)ApplicationContextUtils.getBean(IDaoSupport.class);
		YwjcService ywjcService = (YwjcService)ApplicationContextUtils.getBean(YwjcService.class);
		String name = context.getTrigger().getName();
		TbTaskDetail detail = daoSupport.queryById(name);
		if(null == detail){
			return;
		}

		Map<String,String> params = new HashMap<String,String>();
		params.put("BH",detail.getDataId());//指标ID
		params.put("JCZBSZ","3");//检测指标设置 	例:药品采购单价高出前{0}个月的最高采购单价
		params.put("JCDJ","TX");//包含  TX,JG,YC,WG  对应:提醒,警告,异常,违规
		params.put("JCSDSZ","GTGZ");//包含 GTGZ,DJGZ,ZDTX 对应:隔天告知,当即告知,主动提醒
		params.put("ZSYXS","YES");//在首页显示  对应YES/NO
		
		String method = detail.getMethod();
		if(null != method && method.trim().length()>0){
			try {
				StringBuffer tempBuffer=new StringBuffer();
				Method m = ywjcService.getClass().getMethod(method,Map.class);
				m.invoke(ywjcService,params);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
}