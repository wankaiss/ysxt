<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>参保人药店购药按参保地与就医地相分离要素排名</title>
		<link href="${path}css/reset.min.css" rel="stylesheet"
			type="text/css" />
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
	<form action="hztj_ydCostRanking.action" method="post" name="form" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								统计周期：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
								<input type="hidden" value="${condition.divId}" id="divId" name="condition.divId"/>
							</td>
							<td style="text-align: left;">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;width: 100px;">参保地区县：</td>
							<td style="text-align: left;width: 200px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select>
							</td>
							<td style="padding-left: 10px;width: 100px;">就医地区县：</td>
							<td style="text-align: left;width: 200px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select>
							</td>
						</tr>
						<tr>
							<td>排名数量：</td>
							<td style="text-align: left;">
								<input />
							</td>
							<td style="text-align: right;" colspan="4">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" id="expQz" title="导出Excel" onclick="expQzAll();" value="导出Excel" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" style="width:1600px;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									时间
								</th>
								<th>
									医保号
								</th>
								<th>
									姓名
								</th>
								<th>
									待遇类型
								</th>
								<th>
									参保地区县
								</th>
								<th>
									就医地区县
								</th>
								<th>
									购药总费用
								</th>
								<th>
									费用最高药店
								</th>
								<th>
									处方药费用
								</th>
								<th>
									非处方药费用
								</th>
								<th>
									总费用排名
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										
									</td>
									<td>
									
									</td>
									<td>
									
									</td>
									<td>
										
									</td>
									<td>
									
									</td>
									<td>
									
									</td>
									<td>
										
									</td>
									<td>
									
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="11">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="11">
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
		function query(){
			$("#start").val(0);
			form.action="hztj_ydCostRanking.action";
			form.target='_self';
			form.submit();
		}
		
	</script>
</body>
</html>
