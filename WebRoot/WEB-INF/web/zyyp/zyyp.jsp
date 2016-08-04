<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<meta http-equiv=“X-UA-Compatible” content=“IE=8″>
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
		<title>医疗机构及药店中药饮片总量统计</title>
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
		<%-- overflow-x: hidden;
		 	overflow-y:scroll;
		 	style="overflow-x:scroll; overflow-y:scroll; height:400px"
		 	--%>

		<script type="text/javascript" src="${path}js/jssearch/j.suggest.js"></script>
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="zyyp_totalStatistics.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box" overflow-x:auto>
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%">
								统计月份：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
							</td>
							<td style="text-align: left;"  width="35%">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;" width="10%">区县选择：</td>
							<td style="text-align: left;" width="15%">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;"
									onchange="setEmptyValue(this.value)">  
								</s:select> 
							</td>
							<td width="10%">人员类别：</td>
							<td width="20%" style="text-align: left;">
								<input type="hidden" value="${condition.akc021}" id="tempAkc021"/>
								<s:select id="akc021" name="condition.akc021" list="#request.akc021Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:120px;">  
								</s:select> 
							</td>
							<%--<td>就诊类型：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.aka130}" id="tempAka130"/>
								<s:select id="aka130" name="condition.aka130" list="#request.aka130Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select> 
								<input type="hidden" id="tempAka130" name="condition.aka130" value="${condition.aka130}"/>
								<input name="condition.aka130Names" id="tempAka130Names" value="${condition.aka130Names}" onclick="openCheckBox();" readonly="readonly" style="width:100px;"/>
							</td>
						--%>
						</tr>
						<tr>
							<td>机构类别：</td>
							<td style="text-align: left;">
								<select name="condition.akb022" id="akb022" style="width:100px;" onchange="changeShowSelect(this.value);">
									<option value="" class="optionstyle">
										全部
									</option>
									<option value="1" <s:if test="1==condition.akb022"> selected="selected"</s:if>>医疗机构</option>
									<option value="2" <s:if test="2==condition.akb022"> selected="selected"</s:if>>零售药店</option>
								</select>
							</td>
							<td>医院名称：</td>
							<td style="text-align: left;">	
								<input type="hidden" id="hosIds" name="condition.hosIds" value="${condition.hosIds}"/>
								<input name="condition.hosNames" id="hosNames" value="${condition.hosNames}" onclick="openSelectWindow('1');" readonly="readonly" style="width:100px;"/>
							</td>
							<td>药店名称：</td>
							<td style="text-align: left;">
								<input type="hidden" id="ydIds" name="condition.ydIds" value="${condition.ydIds}"/>
								<input name="condition.ydNames" id="ydNames" value="${condition.ydNames}" onclick="openSelectWindow('2');" readonly="readonly" style="width:100px;"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;" colspan="6">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" title="显示上月数据" onclick="queryLastMonth();" value="显示上月数据" style="width: 100px;"/>
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box" >
					<table class="date_tab" style="width : 2256px;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th rowspan="2" style="width: 200px;">
									结算年月
								</th>
								<th rowspan="2" style="width: 100px;">
									机构类别
								</th>
								<th rowspan="2" style="width: 100px;">
									机构代码
								</th>
								<th rowspan="2" style="width: 240px;">
									机构名称
								</th>
								<th rowspan="2" style="width: 150px;">
									医院等级
								</th>
								<th colspan="5" style="width: 500px;">
									外配处方+非外配处方
								</th>
								<th colspan="5" style="width: 500px;">
									非外配处方
								</th>
								<th colspan="5" style="width: 500px;">
									外配处方
								</th>
							</tr>
							<tr>
								<th style="width: 100px;">费用</th>
								<th style="width: 100px;">增长率</th>
								<th style="width: 100px;">贴数</th>
								<th style="width: 100px;">贴均价格</th>
								<th style="width: 100px;">贴均价格增长率</th>
								<th style="width: 100px;">费用</th>
								<th style="width: 100px;">增长率</th>
								<th style="width: 100px;">贴数</th>
								<th style="width: 100px;">贴均价格</th>
								<th style="width: 100px;">贴均价格增长率</th>
								<th style="width: 100px;">费用</th>
								<th style="width: 100px;">增长率</th>
								<th style="width: 100px;">贴数</th>
								<th style="width: 100px;">贴均价格</th>
								<th style="width: 100px;">贴均价格增长率</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.JSNY }
									</td>
									<td>
										${akb022Map[cons.HOSP_TYPE] }
									</td>
									<td>
										${cons.HOSP_ID }
									</td>
									<td>
										${cons.NAME }
									</td>
									<td>
										${aka101Map[cons.HOSP_CLASS] }
									</td>
									<!-- 外配处方+非外配处方 -->
									<td>
										<fmt:formatNumber value="${cons.ZFY }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.ZZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
									<td>
										<fmt:formatNumber value="${cons.ZTS }" pattern="#,###"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.ZJTJG }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.ZJTJGZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
									<!-- 非外配处方 -->
									<td>
										<fmt:formatNumber value="${cons.FWPFY }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.FWPZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
									<td>
										<fmt:formatNumber value="${cons.FWPTS }" pattern="#,###"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.FWPJTJG }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.FWPJTJGZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
									<!-- 外配处方 -->
									<td>
										<fmt:formatNumber value="${cons.WPFY }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.WPZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
									<td>
										<fmt:formatNumber value="${cons.WPTS }" pattern="#,###"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.WPJTJG }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.WPJTJGZZL }" pattern="#,###.##" minFractionDigits="2"/>%
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="20">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="20">
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
			$("#aka130").val($("#tempAka130").val());
			$("#akc021").val($("#tempAkc021").val());
			$("#aaa027").val($("#tempAaa027").val());
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
			var e=$("#akb022").val();
			changeShowSelect(e);
		});
		//查询
		function query(){
			showBlock();
			checkDate();
			$("#start").val(0);
			form.action = "zyyp_totalStatistics.action";
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
		function openSelectWindow(e){
			var aaa027 = $("#aaa027").val();
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_ydHosSelect.action?condition.akb022='+e+"&condition.aaa027="+aaa027,"医疗机构查询",wndFeatures);
		}
		
		//显示上月数据
		function queryLastMonth(){
			showBlock();
			$("#start").val(0);
			form.action = "zyyp_totalStatistics.action?lastMonth=1";
			form.target = '_self';
			form.submit();
		}
		
		//导出Excel 
		function exportExcel(){
			form.action = "zyyp_exportExcel.action";
			form.target = '_self';
			form.submit();
		}
		
		//就诊类型多选
		function openCheckBox(){
			var tempAka130 = $("#tempAka130").val();
			var json = {tempAka130:tempAka130}
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			var address=window.open("zyyp_openCheckBox.action",json,wndFeatures);
			if(address != null){
				var tt = address.split("|");
				document.getElementById("tempAka130").value = tt[0];
				document.getElementById("tempAka130Names").value = tt[1];
			}
		}
		
		//区划重新选择时清空医院已选的信息
		function setEmptyValue(e){
			//如果选中请选择则不变否则清空医院已选的信息
			if(e!=""){
				$("#hosIds").val("");
				$("#hosNames").val("");
				$("#ydIds").val("");
				$("#ydNames").val("");
				$("#ydNames").attr("title","");
				$("#hosNames").attr("title","");
			}
		}
		
		//机构类别选择时禁用不相应的选择框
		function changeShowSelect(value){
			if(value==""){
				document.getElementById("hosNames").removeAttribute("disabled");
				document.getElementById("ydNames").removeAttribute("disabled");
			}else if(value==1){
				$("#ydIds").val("");
				$("#ydNames").val("");
				$("#ydNames").attr("title","");
				document.getElementById("ydNames").disabled = "disabled";
				document.getElementById("hosNames").removeAttribute("disabled");
			}else{
				$("#hosIds").val("");
				$("#hosNames").val("");
				$("#hosNames").attr("title","");
				document.getElementById("hosNames").disabled = "disabled";
				document.getElementById("ydNames").removeAttribute("disabled");
			}
		}
		
		
	</script>
</body>
</html>
