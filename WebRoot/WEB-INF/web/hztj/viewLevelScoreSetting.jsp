<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看药店等级差值设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_viewGrowSetting.action" name="form" method="post" id="chooseForm" target="_self">
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
								<input type="hidden" value="${condition.tcdm}" id="tcdm"/>
								<input type="hidden" value="${condition.divId}" id="divId"/>
								<input type="hidden" value="${condition.gzbz}" id="gzbz" name="condition.gzbz"/>
								<input type="hidden" value="${condition.jbdm}" id="jbdm"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabBox" style="padding-top: 10px;">
					<ul class="menuTab">
				        <li id="li1"><a href="#" target="_self" onclick="changTabShow('1');">处方药</a></li>
				        <li id="li2"><a href="#" target="_self" onclick="changTabShow('2');">非处方药</a></li>
				    </ul>
				</div>
				<div id="div1">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									等级差值
								</th>
								<th>
									分值
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.QZM}</td>
									<td style="text-align: center;">${cons.QZZ}</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="2" style="text-align: center;">药店等级差值未设置</td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
				<div style="display: none;" id="div2">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									等级差值
								</th>
								<th>
									分值
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet1.data && pageSet1.data.size()>0">
							<s:iterator value="pageSet1.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.QZM}</td>
									<td style="text-align: center;">${cons.QZZ}</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="2" style="text-align: center;">药店等级差值未设置</td>
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
		var divId = $("#divId").val();
		$(document).ready(function(){
			if(divId==1){
				$("ul.menuTab li:first-child").addClass("current");
			}else{
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
			}
		});
		//更换tab页
		function changTabShow(idx){
			if(idx == 1){
				divId = 1;
				document.getElementById("div1").style.display = "";
				document.getElementById("div2").style.display = "none";
				$("#li1").addClass("current");
				$("#li1").siblings().removeClass("current");
				query();
			}else if(idx == 2){
				divId = 2;
				document.getElementById("div2").style.display = "";
				document.getElementById("div1").style.display = "none";
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
				query();
			}
		}
		//查询
		function query(){
			var jbdm = $("#jbdm").val();
			var tcdm = $("#tcdm").val();
			var qzjb = "";
			if(divId==1){
				qzjb = 9;
			}else{
				qzjb = 10;
			}
			form.action = "hztj_viewGrowSetting.action?condition.divId="+divId+"&condition.tcdm="+tcdm+"&condition.jbdm="+jbdm+"&condition.qzjb="+qzjb+"&condition.type=5";
			form.target="_self";
			form.submit();
		}
	</script>
</body>
</html>