package com.wondersgroup.qyws.task.module;

import java.io.Serializable;
import java.util.Date;

import org.quartz.JobDetail;

public class TbTaskDetailDic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dataId;
	private String levelIndex;//1:提醒,2:警告
	private String taskMean;//隔天告知[GTGZ]
	private String groupName;
	private String taskDesc;
	private String triggerId;
	private String taskPath;
	private String taskExpression;
	
	private JobDetail jobDetail;
	
	private String taskConfig;
	private String taskConfigValue;
	private String method;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTaskConfigValue() {
		return taskConfigValue;
	}
	public void setTaskConfigValue(String taskConfigValue) {
		this.taskConfigValue = taskConfigValue;
	}
	public String getTaskConfig() {
		return taskConfig;
	}
	public void setTaskConfig(String taskConfig) {
		this.taskConfig = taskConfig;
	}
	public JobDetail getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
	private Date startTime;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getTaskExpression() {
		return taskExpression;
	}
	public void setTaskExpression(String taskExpression) {
		this.taskExpression = taskExpression;
	}
	public String getDataId() {
		return dataId;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public String getTaskMean() {
		return taskMean;
	}
	public String getTaskPath() {
		return taskPath;
	}
	public String getTriggerId() {
		return triggerId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public void setTaskMean(String taskMean) {
		this.taskMean = taskMean;
	}
	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
	}
	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}
	public String getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(String levelIndex) {
		this.levelIndex = levelIndex;
	}
	
}