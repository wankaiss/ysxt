package com.wondersgroup.qyws.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wondersgroup.qyws.task.module.TbTaskDetail;
import com.wondersgroup.qyws.task.service.TaskService;
import com.wondersgroup.qyws.tjfx.common.BaseAction;

@Controller
@Scope("prototype") 
public class TaskAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	 
	@Autowired
	private TaskService taskService;
	
	public String list(){
		List<?> temp = this.taskService.queryAll();
		Map<String,List<Map<?,?>>> details = new HashMap<String,List<Map<?,?>>>();
		if(null != temp && temp.size()>0){
			for(int i=0;i<temp.size();i++){
				Map<?,?> map = (Map<?,?>)temp.get(i);
				List<Map<?,?>> list = null;
				if(details.containsKey(map.get("GROUP_NAME"))){       
					list = details.get(map.get("GROUP_NAME"));
				}else{
					list = new ArrayList<Map<?,?>>();
				}
				list.add(map);
				details.put(map.get("GROUP_NAME").toString(),list);
			}
		}  
		this.getRequest().setAttribute("tasks",details);
		this.page = this.ROOR_PATH + "/ywjc/task.jsp";
		return "success";
	}
	
	public String del(){
		String id = this.getRequest().getParameter("id");
		this.taskService.delTask(id);
		this.page = "task_toadd.action";
		return "redirect";
	}
	
	public String toadd(){
		this.getRequest().setAttribute("tasks",this.taskService.queryAll());
		this.page = this.ROOR_PATH + "/ywjc/add.jsp";
		return "success";
	}
	
	public String add(){
		
		TbTaskDetail detail = new TbTaskDetail();
		detail.setGroupName(this.getRequest().getParameter("groupName"));
		detail.setLevelIndex(this.getRequest().getParameter("levelIndex"));
		detail.setStartTime(new Date());
		detail.setTaskDesc(this.getRequest().getParameter("taskDesc"));
		detail.setTaskExpression(this.getRequest().getParameter("expression"));
		detail.setTaskMean(this.getRequest().getParameter("taskMean"));
		detail.setTaskPath("com.wondersgroup.qyws.task.impl.ListenerJob");
		detail.setMethod(this.getRequest().getParameter("method"));
		detail.setTaskConfig(this.getRequest().getParameter("config"));
		detail.setTaskConfigValue(this.getRequest().getParameter("configValue"));
		try {
			this.taskService.addTask(detail);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		this.page = "task_toadd.action";
		return "redirect";
	}
	
	public void pauseTrigger() {
		String triggerName = this.getRequest().getParameter("triggerName");
		String group = this.getRequest().getParameter("group");
		this.taskService.pauseTrigger(triggerName, group);
	}

	public void resumeTrigger() {
		String triggerName = this.getRequest().getParameter("triggerName");
		String group = this.getRequest().getParameter("group");
		this.taskService.resumeTrigger(triggerName, group);
	}
	
	public void pauseAll() {
//		List<?> temp = this.taskService.queryAll();
		ActionContext act=ActionContext.getContext();
		List<?> temp = this.taskService.queryAll();
		//List<?> temp = this.taskService.queryAllByUserJS( ((LoginUserMessage)act.getSession().get(PublicStatic.userMessage)).getVirGroup() );
		if(null != temp && temp.size()>0){
			for(int i=0;i<temp.size();i++){
				Map<?,?> map = (Map<?,?>)temp.get(i);
				String id = map.get("TRIGGER_NAME").toString();
				String group = map.get("TRIGGER_GROUP").toString();
				this.taskService.pauseTrigger(id,group);
			}
		}
	}
	

	public void resumeAll() {
		
//		List<?> temp = this.taskService.queryAll();
		ActionContext act=ActionContext.getContext();
		List<?> temp = this.taskService.queryAll();
		//List<?> temp = this.taskService.queryAllByUserJS( ((LoginUserMessage)act.getSession().get(PublicStatic.userMessage)).getVirGroup() );
		if(null != temp && temp.size()>0){
			for(int i=0;i<temp.size();i++){
				Map<?,?> map = (Map<?,?>)temp.get(i);
				String id = map.get("TRIGGER_NAME").toString();
				String group = map.get("TRIGGER_GROUP").toString();
				this.taskService.resumeTrigger(id,group);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public String saveInfo() {
		String id = this.getRequest().getParameter("id");
		String value = this.getRequest().getParameter("value");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			this.taskService.saveConfig(id,value);
			map.put("success",true);
			map.put("message","保存成功!");
		} catch (Exception e) {
			map.put("success",false);
			map.put("message","保存失败.请确定运行频率设置无误!");
		}
		this.object = map;
		return this.OBJECT;
	}
	

	
}
