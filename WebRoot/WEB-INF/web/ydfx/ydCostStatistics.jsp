<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店月（季、年）接诊非处方药人次、费用、次均费用</title>
		<link href="${path}css/reset.min.css" rel="stylesheet"
			type="text/css" />
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/likeCheck.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${path}js/jquery.util.js"></script>
		<script type="text/javascript" src="${path}js/jquery.blockUI.js"></script>
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
		body {
			height: 100%;
			background: #fff;
			
		}
		</style>
		<%--overflow-x: hidden;
		 	overflow-y:scroll;
		 	style="overflow-x:srolll; overflow-y:hidden; height:495px" --%>
		<script type="text/javascript" src="${path}js/jssearch/j.suggest.js"></script>
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="hztj_ydCostStatistics.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								统计周期：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
								<%-- <input type="hidden" value="${condition.divId}" id="divId" name="condition.divId"/>--%>
							</td>
							<td style="text-align: left;">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;">单次非处方额度档次查询：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.fcfValue}" id="tempFcfValue"/>
								<s:select id="fcfValue" name="condition.fcfValue" list="#request.fcfValueMap" listKey="key" listValue="value" 
									listTitle="value"  cssStyle="width:100px;">  
								</s:select>
							</td>
						</tr>
						<tr>
							<td style="padding-left: 10px;">区县选择：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select>
							</td>
							<td style="text-align: right;" colspan="2">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" id="expQz" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box" >
					<table class="date_tab" style="width:1600px;" border="0" cellspacing="0" id="trId">
						<thead>
							<tr>
								<th style="width: 200px;" rowspan="2">
									时间
								</th>
								<th style="width: 100px;" rowspan="2">
									药店代码
								</th>
								<th style="width: 300px;" rowspan="2">
									药店名称
								</th>
								<th style="width: 100px;" rowspan="2">
									区县
								</th>
								<th style="width: 300px;" colspan="3">
									当年账户非处方药
								</th>
								<th style="width: 300px;" colspan="3">
									单次非处方购药（当年账户）90元以上
								</th>
								<th style="width: 300px;" colspan="3">
									历年账户非处方药
								</th>
							</tr>
							<tr>
								<th style="width: 100px;">
									接诊人次
								</th>
								<th style="width: 100px;">
									费用
								</th>
								<th style="width: 100px;">
									次均费用
								</th>
								<th style="width: 100px;">
									人次数
								</th>
								<th style="width: 100px;">
									人次占比(%)
								</th>
								<th style="width: 100px;">
									费用占比(%)
								</th>
								<th style="width: 100px;">
									接诊人次
								</th>
								<th style="width: 100px;">
									费用
								</th>
								<th style="width: 100px;">
									次均费用
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td >
										<s:property value="#request.date"/>
									</td>
									<td>
										${cons.YD_ID }
									</td>
									<td>
										${cons.YDNAME }
									</td>
									<td>
										${aaa027Map[cons.AAA027] }
									</td>
									<td>
										${cons.FCFRC }
									</td>
									<td>
										${cons.FCFFY }
									</td>
									<td>
										${cons.FCFCJFY }
									</td>
									<td>
										${cons.TFCFRC }
									</td>
									<td>
										${cons.TRCZB }
									</td>
									<td>
										${cons.FYZB }
									</td>
									<td>
										${cons.CFRC }
									</td>
									<td>
										${cons.CFFY }
									</td>
									<td>
										${cons.CFPJRC }
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="13">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="13">
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
	$(document).ready(function(){
            var startDate = $("#tempStart").val();
			var endDate = $("#tempEnd").val();
			$("#aaa027").val($("#tempAaa027").val());
			$("#FcfValue").val($("#tempFcfValue").val());
			if(startDate==""&&endDate==""){
				var start = new Date().addMonth(MONT_SUB_AMOUNT);
				$("#startDate").val(start.format(VIEW_MONTH_FORMAT));
				$("#endDate").val(start.format(VIEW_MONTH_FORMAT));
			}else{
				$("#startDate").val(startDate);
				$("#endDate").val(endDate);
			}
			$("#startDate").click(function() {
				WdatePicker({dateFmt:'yyyy年MM月',isShowClear:false,readOnly:true});
			});
			$("#endDate").click(function() {
				WdatePicker({dateFmt:'yyyy年MM月',isShowClear:false,readOnly:true});
			});
		});
		//查询
		function query(){
			showBlock();
			checkDate();
			$("#start").val(0);
			form.action="hztj_ydCostStatistics.action";
			form.target='_self';
			form.submit();
		}
		
		//日期校验
		function checkDate(){
			var start = $("#startDate").val();
			var end = $("#endDate").val();
			if(start==""){
				alert("请选择开始时间。");
				return false;
			}
			if(end==""){
				alert("请选择结束时间。");
				return false;
			}
			if(start>end){
				alert("开始时间不能大于结束时间。");
				return false;
			}
		}
		
		//导出Excel
		function exportExcel(){
			alert("正在导出中......请稍后!");
			form.action = "ydfx_exportExcelYdCostStatistics.action";
			form.target='_self';
			form.submit();
		}
	</script>
</body>
</html>
