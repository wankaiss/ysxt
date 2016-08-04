package com.wondersgroup.qyws.task.entity;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import oracle.sql.CLOB;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TB_TASK_DETAIL_DIC")
public class TB_TASK_DETAIL_DIC {
	
	@Id
	@Column(name="DATA_ID",length=32)
	private String data_id;

	@Column(name="LEVEL_INDEX")//订单明细编号
	private String level_index;
	@Column(name="TASK_MEAN")//药品配送关系本地ID
	private String task_mean;
	@Column(name="GROUP_NAME")//药品统编代码
	private String group_name;
	@Column(name="TASK_DESC")//医院编码
	private String task_desc;
	@Column(name="TRIGGER_ID")//医院名称
	private String trigger_id;
	@Column(name="TASK_PATH")//供货渠道(企业编码)
	private String task_path;
	@Column(name="TASK_EXPRESSION")//供货渠道名称(企业名称)
	private String task_expression;
	@Column(name="TASK_BEGINTIME")//既定供货渠道编码(配送企业编码)
	private String task_begintime;
	@Column(name="TASK_CONFIG")//既定供货渠道企业名称(配送企业名称)
	private Clob task_config;
	@Column(name="TASK_CONFIG_VALUE")//最近更新时间
	private Clob task_config_value;
	@Column(name="TASK_METHOD")//监测等级
	private String task_method;
	public String getData_id() {
		return data_id;
	}
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	public String getLevel_index() {
		return level_index;
	}
	public void setLevel_index(String level_index) {
		this.level_index = level_index;
	}
	public String getTask_mean() {
		return task_mean;
	}
	public void setTask_mean(String task_mean) {
		this.task_mean = task_mean;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
	public String getTrigger_id() {
		return trigger_id;
	}
	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}
	public String getTask_path() {
		return task_path;
	}
	public void setTask_path(String task_path) {
		this.task_path = task_path;
	}
	public String getTask_expression() {
		return task_expression;
	}
	public void setTask_expression(String task_expression) {
		this.task_expression = task_expression;
	}
	public String getTask_begintime() {
		return task_begintime;
	}
	public void setTask_begintime(String task_begintime) {
		this.task_begintime = task_begintime;
	}
	public Clob getTask_config() {
		return task_config;
	}
	public void setTask_config(Clob task_config) {
		this.task_config = task_config;
	}
	public Clob getTask_config_value() {
		return task_config_value;
	}
	public void setTask_config_value(Clob task_config_value) {
		this.task_config_value = task_config_value;
	}
	public String getTask_method() {
		return task_method;
	}
	public void setTask_method(String task_method) {
		this.task_method = task_method;
	}
	
	
	
}
