<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Map aka130Map = (Map)request.getAttribute("aka130Map");
	Map akc021Map = (Map)request.getAttribute("akc021Map");
	Map aaa027Map = (Map)request.getAttribute("aaa027Map");
	request.setAttribute("aka130Map", aka130Map); 
	request.setAttribute("akc021Map", akc021Map); 
    request.setAttribute("aaa027Map", aaa027Map);  
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店刷卡频次统计计划查询</title>
		<link href="${path}css/reset.min.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/likeCheck.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${path}js/js.js"></script>
		<script type="text/javascript" src="${path}js/jquery.nicescroll.min.js"></script>
		<script type="text/javascript" src="${path}My97DatePicker/WdatePicker.js"></script>
		<script src="${path}js/common.js"  type="text/javascript"></script>
		<script src="${path}js/date_select.js"  type="text/javascript"></script>
		
		<!-- pageset -->
		<script type="text/javascript" src="${path}js/jquery.papper.js"></script>
		<link href="${path}css/pageset.css" rel="stylesheet" type="text/css" />
		<!-- pageset -->	
		<!-- 弹出窗口 -->
		<link rel="stylesheet" href="${path}css/hztj/colorbox.css" />
		<script src="${path}js/jquery.colorbox.js"></script>
		<!-- 弹出窗口 -->  
		<style>
		html,body {
			height: 100%;
			background: #fff;
		}
		</style>

		<script type="text/javascript" src="${path}js/jssearch/j.suggest.js"></script>
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="hztj_queryGenerateDetail.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								日期	：
							</td>
							<td style="text-align: left;">
								<input type="hidden" name="condition.startDate" id="startDate" value="${condition.startDate }" readonly="readonly" style="width:128px;"/>
								<s:property value="condition.startDate"/> 
								至
								<s:property value="condition.endDate"/>
								<input type="hidden" name="condition.endDate" id="endDate" value="${condition.endDate }" readonly="readonly" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;width: 100px;">区县：</td>
							<td style="text-align: left;width: 100px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" disabled="true">  
								</s:select> 
							</td>
							<td>药店名称：</td>
							<td title="<s:property value="condition.ydNames"/>">	
								<input type="hidden" id="ydIds" name="condition.ydIds" value="${condition.ydIds}"/>
								<input name="condition.ydNames" type="hidden" id="ydNames" value="${condition.ydNames}" readonly="readonly" style="width:100px;"/>
								<s:if test="condition.ydNames.length()>10">
									<s:property value="condition.ydNames.substring(0, 10)"/>...
								</s:if>
								<s:else>
									<s:property value="condition.ydNames"/>
								</s:else>
							</td>
							<td>就诊类型：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.aka130}" id="tempAka130"/>
								<s:select id="aka130" name="condition.aka130" list="#request.aka130Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" disabled="true">  
								</s:select> 
							</td>
						</tr>
						<tr>
							<td>
								时间	：
							</td>
							<td style="text-align: left;">
								<input type="hidden" name="condition.startTime" id="startTime" value="${condition.startTime }" readonly="readonly" style="width:128px;"/>
								<s:property value="condition.startTime"/> 
								至
								<s:property value="condition.endTime"/>
								<input type="hidden" name="condition.endTime" id="endTime" value="${condition.endTime }" readonly="readonly" style="width:128px;"/> 
							</td>
							<td>时间间隔：</td>
							<td style="text-align: left;">	
								<s:property value="condition.sjjg"/>
								<input type="hidden" name="condition.sjjg" id="sjjg" value="${condition.sjjg}" style="width: 50px;" readonly="readonly"/>
								<select id="sjlb" name="condition.sjlb" disabled="disabled">
									<option value="分钟" <s:if test="'分钟'==condition.sjlb"> selected="selected"</s:if>>分钟</option>
									<%--<option value="秒钟" <s:if test="'秒钟'==condition.sjlb"> selected="selected"</s:if>>秒钟</option>
									<option value="小时" <s:if test="'小时'==condition.sjlb"> selected="selected"</s:if>>小时</option>--%>
								</select>
							</td>
							<td>频次：</td>
							<td style="text-align: left;">
								<s:property value="condition.pc"/>
								<input type="hidden" name="condition.pc" id="pc" value="${condition.pc}" style="width:100px;" readonly="readonly"/>
							</td>
							<td>排名数量：</td>
							<td style="text-align: left;">
								<s:property value="condition.pmsl"/>
								<input type="hidden" name="condition.pmsl" id="pmsl" value="${condition.pmsl}" style="width:100px;" readonly="readonly"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;text-align: center;">药店代码</th>
								<th style="width: 350px;text-align: center;">药店名称</th>
								<th style="width: 100px;text-align: center;">接诊人次</th>
								<th style="width: 100px;text-align: center;">接诊费用</th>
								<th style="width: 150px;text-align: center;">日期</th>
								<th style="width: 400px;text-align: center;">时间段</th>
								<th style="width: 100px;text-align: center;">操作</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.JGID }
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${cons.JZRC }
									</td>
									<td>
										${cons.JZFY }
									</td>
									<td>
										<s:date name="#cons.KSSJ" format="yyyy年MM月dd日"/>
									</td>
									<td>
										<s:date name="#cons.KSSJ" format="HH:mm:ss"/>
										~
										<s:date name="#cons.JSSJ" format="HH:mm:ss"/>
									</td>
									<td>
										<a href="hztj_queryPlanDetail.action?condition.id='<s:property value="#cons.ID"/>'&condition.kssj=<s:property value="#cons.KSSJ"/>&condition.jssj=<s:property value="#cons.JSSJ"/>" target="newWindow");">显示明细</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="7">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="7">
							   <div class="papper" id="papper"></div>
									<script type="text/javascript">
										$(document).ready(function(){
											$("#papper").papper({
												count:"${pageSet.count}",
												start:"${pageSet.start}",
												limit:"${pageSet.limit}",
												form:"chooseForm"
											});
										});
									</script>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//查询
		function queryDetail(id){
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:no;status:no;";
			window.open("hztj_queryPlanDetail.action?condition.id="+id,"计划查询",wndFeatures);
		}
	</script>
</body>
</html>
