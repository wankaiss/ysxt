<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加任务</title>
		
	</head>
	<body>
		<table>
			<s:if test="null != #request.tasks">
				<s:iterator value="#request.tasks" var="task">
				<tr>
					<td>${task.TASK_DESC}</td>
					<td>${task.GROUP_NAME}</td>
					<td>
						<a href="task_del.action?id=${task.DATA_ID}">删除</a>
					</td>
				</tr>
				</s:iterator>
			</s:if>
		</table>
		<hr/>
		<form action="task_add.action" method="post">
			<table>
				<tr>
					<td>任务名称</td>
					<td>
						<input type="text" name="taskDesc"/>
						检测指标信息
					</td>
				</tr>
				<tr>
					<td>任务分组</td>
					<td>
						<input type="text" name="groupName"/>
						大分类
					</td>
				</tr>
				<tr>
					<td>业务方法</td>
					<td>
						<input type="text" name="method"/>
						对应调用YwjcService中的方法名
					</td>
				</tr>
				<tr>
					<td>检测等级</td>
					<td>
						<select name="levelIndex">
							<option value="TX" selected="selected">提醒</option>
							<option value="JG">警告</option>
							<option value="YC">异常</option>
							<option value="WG">违规</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>告知方式</td>
					<td>
						<select name="taskMean">
							<option value="SYTS" selected="selected">首页推送</option>
							<option value="ZCJC">正常检测</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>运行频率</td>
					<td>
						<input type="text" name="expression" value="0/59 * * * * ?"/>
					</td>
				</tr>
				<tr>
					<td>参数配置</td>
					<td>
						<textarea name="config" style="height:200px;width:100%;">{
items:[
	{name:"JCZBSZ",label:"监测指标设置",prefix:"药品采购单价高出前",suffix:"个月的最高采购单价",defaultValue:3,type:"input"},
	{name:"JCDJ",label:"监测等级",defaultValue:"TX",type:"select",items:[{key:"TX",value:"提醒"},{key:"JG",value:"警告"},{key:"YC",value:"异常"},{key:"WG",value:"违规"}]},
	{name:"JCSDSZ",label:"告知方式",defaultValue:"ZCJC",type:"select",items:[{key:"SYTS",value:"首页推送"},{key:"ZCJC",value:"正常检测"}]},
	{name:"YXPL",label:"运行频率",defaultValue:3,type:"input"}
]
}</textarea>
					</td>
				</tr>
				<tr>
					<td>默认配置</td>
					<td>
						<textarea name="configValue" style="height:100px;width:100%;">{"JCZBSZ": "3","JCDJ": "WG","JCSDSZ": "ZCJC","YXPL": "0/59 * * * * ?"}</textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="保存"/>
		</form>
	</body>
</html>