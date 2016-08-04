<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>单个医生人均总费用统计</title>
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
	<form action="zyyp_singleDoctorStatisticsQuery.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								统计月份：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
							</td>
							<td style="text-align: left;">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<%--<td style="padding-left: 10px;width: 100px;">区县选择：</td>
							<td style="text-align: left;width: 100px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select> 
							</td>
							--%>
							<td>人员类别：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.akc021}" id="tempAkc021"/>
								<s:select id="akc021" name="condition.akc021" list="#request.akc021Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:120px;">  
								</s:select> 
							</td>
							<td>医院名称：</td>
							<td style="text-align: left;">	
								<input type="hidden" id="hosIds" name="condition.hosIds" value="${condition.hosIds}"/>
								<input name="condition.hosNames" id="hosNames" value="${condition.hosNames}" onclick="openSelectWindow('1');" readonly="readonly" style="width:100px;"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;" colspan="6">
								<input type="button" id="se" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
								<input type="button" title="图形化展示" onclick="showDemo();" value="图形化展示" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;">
									医院代码
								</th>
								<th style="width: 300px;">
									医院名称
								</th>
								<th style="width: 100px;">
									医生姓名
								</th>
								<th style="width: 300px;">
									医师资格证书编号
								</th>
								<th style="width: 150px;">
									人数
								</th>
								<th style="width: 100px;">
									总费用
								</th>
								<th style="width: 100px;">
									人均费用
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.HOSP_ID }
									</td>
									<td>
										${cons.NAME }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.ZGZS }
									</td>
									<td>
										${cons.RS }
									</td>
									<td>
										${cons.FY }
									</td>
									<td>
										<fmt:formatNumber value="${cons.RJFY }" pattern="#,###.##" minFractionDigits="2"/>
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
		$(document).ready(function(){
			var startDate = $("#tempStart").val();
			var endDate = $("#tempEnd").val();
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
			form.action = "zyyp_singleDoctorStatisticsQuery.action";
			form.target = '_self';
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
		
		//医院或药店选择
		function openSelectWindow(){
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_ydHosSelect.action?condition.akb022=1',"医疗机构查询",wndFeatures);
		}
		
		//导出Excel 
		function exportExcel(){
			alert("EXCEL导出中请耐心等待...");
			form.action = "zyyp_exportExcelSingleLine.action?condition.divId=3";
			form.target = '_self';
			form.submit();
		}
		
		//图形化展示
		function showDemo(){
			var params = "&condition.startDate="+encodeURI(encodeURI($("#tempStart").val()))+"&condition.endDate="+encodeURI(encodeURI($("#tempEnd").val()))
				+"&condition.akc021="+$("#akc021").val()+"&condition.hosIds="+$("#hosIds").val()
				+"&pageSet.limit="+'${pageSet.limit}'+"&pageSet.start="+'${pageSet.start}';
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_showDemo.action?params='+params,"单个医生人均总费用统计图形化展示",wndFeatures);
		}
	</script>
</body>
</html>
