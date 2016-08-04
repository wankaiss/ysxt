<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看药店费用等级详细设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_medLevelSetting.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left: 10px;width: 100px;">当前区县：</td>
							<td style="text-align: left;">
								<s:select id="aaa027" name="condition.tcdm" list="#request.aaa027Map" listKey="key" listValue="value"
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" disabled="true">  
								</s:select>
								<input type="hidden" value="${condition.qzdm}" id="qzdm" name="condition.qzdm"/>
								<input type="hidden" value="${condition.id}" id="id" name="condition.id"/>
								<input type="hidden" value="${condition.gzbz}" id="gzbz" name="condition.gzbz"/>
								<input type="hidden" value="${condition.tcdm}" id="tcdm"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									等级
								</th>
								<th>
									非处方药分类
								</th>
								<th>
									非处方药费用
								</th>
								<th>
									处方药分类
								</th>
								<th>
									处方药费用
								</th>
								<s:if test="null != pageSet.data && pageSet.data.size()>0">
									<th>
										操作
									</th>
								</s:if>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<s:if test="#index.index < 4">
									<tr>
										<td style="text-align: center;">
											${cons.JBDM }
										</td>
										<td style="text-align: center;">
											${cons.JBM }
										</td>
										<td style="text-align: center;">
											<s:if test="#cons.JBDM==4">
												&gt;=${cons.XXZ }万
											</s:if>
											<s:elseif test="#cons.JBDM==1">
												&lt;${cons.SXZ }万
											</s:elseif>
											<s:else>
												&gt;=${cons.XXZ }万and&lt;${cons.SXZ }万
											</s:else>
										</td>
										<td style="text-align: center;">
											${cons.TJBM }
										</td>
										<td style="text-align: center;">
											<s:if test="#cons.JBDM==4">
												&gt;=${cons.TXXZ }万
											</s:if>
											<s:elseif test="#cons.JBDM==1">
												&lt;${cons.TSXZ }万
											</s:elseif>
											<s:else>
												&gt;=${cons.TXXZ }万and&lt;${cons.TSXZ }万
											</s:else>
										</td>
										<td style="text-align: center;">
											<%--<a href="#" onclick="queryMedLevel('<s:property value="#cons.TCDM"/>');">查看药店等级设置</a>&nbsp;&nbsp;&nbsp;
											--%><a href="#" onclick="levelScoreSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.JBDM"/>')">查看等级差值分值设置</a>
										</td>
									</tr>
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="6" style="text-align: center;">药店费用等级未设置</td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$("#aaa027").val($("#tcdm").val());

		
		//药店等级差值设置
		function levelScoreSetting(tcdm,jbdm){
			var gzbz = $("#gzbz").val();
			var wndFeatures = "dialogWidth=400px;dialogHeight=200px;center:true;scroll:no;status:no;";
			window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.jbdm="+jbdm+"&condition.divId=1"+"&condition.gzbz="+gzbz+"&condition.qzjb=9"+"&condition.type=5",null,wndFeatures);
		}
		
		//药店等级设置查询
		function queryMedLevel(tcdm){
			var wndFeatures = "width=1000px;height=600px;center:true;scroll:no;status:no;";
			window.open('hztj_queryMedLevel.action?condition.tcdm='+tcdm+"&condition.xxz="+<s:property value="condition.xxz"/>+"&condition.sxz="+<s:property value="condition.sxz"/>+"&condition.txxz="+<s:property value="condition.txxz"/>+"&condition.tsxz="+<s:property value="condition.tsxz"/>,"药店等级设置",wndFeatures);
		}
	</script>
</body>
</html>