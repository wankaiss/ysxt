package com.wondersgroup.qyws.task.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import oracle.sql.CLOB;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TB_TASK_DETAIL")
public class TB_TASK_DETAIL {
	
	@Id
	@Column(name="DATA_ID",length=32)
	protected String data_id;

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
	private CLOB task_config;
	@Column(name="TASK_CONFIG_VALUE")//最近更新时间
	private CLOB task_config_value;
	@Column(name="TASK_METHOD")//监测等级
	private String task_method;

	
	
}
