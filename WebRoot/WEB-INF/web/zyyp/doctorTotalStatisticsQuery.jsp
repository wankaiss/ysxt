<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医生特定中药饮片统计数据查询</title>
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
	<form action="zyyp_doctorTotalStatisticsQuery.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%">
								统计月份：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
							</td>
							<td style="text-align: left;" width="35%">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td width="15%">人员类别：</td>
							<td style="text-align: left;" width="15%">
								<input type="hidden" value="${condition.akc021}" id="tempAkc021"/>
								<s:select id="akc021" name="condition.akc021" list="#request.akc021Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:120px;">  
								</s:select> 
							</td>
							<td width="10%">通用名：</td>
							<td width="15%">
								
							</td>
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
							<td>医生姓名：</td>
							<td style="text-align: left;">
								<input type="hidden" id="docId" name="condition.docId" value="${condition.docId}"/>
								<input name="condition.docName" id="docName" value="${condition.docName}" onclick="docSelect();" readonly="readonly" style="width:120px;"/>
							</td>
							<td>医师资格证书编号：</td>
							<td style="text-align: left;">
								<input type="text" id="zgzs" name="condition.zgzs" value="${condition.zgzs}" readonly="readonly"/>
							</td>
							<td style="text-align: right;" colspan="2">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
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
								<%--
								<th style="width: 200px;">
									结算年月
								</th>
								--%>
								<th style="width: 100px;">
									机构类别
								</th>
								<th style="width: 100px;">
									机构代码
								</th>
								<th style="width: 300px;">
									机构名称
								</th>
								<th style="width: 100px;">
									医生姓名
								</th>
								<th style="width: 200px;">
									医师资格证书编号
								</th>
								<th style="width: 150px;">
									中药饮片通用名
								</th>
								<th style="width: 100px;">
									中药饮片数量
								</th>
								<th style="width: 100px;">
									中药饮片费用
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<%--
									<td>
										${cons.JSNY }
									</td>
									--%>
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
										${cons.XM }
									</td>
									<td>
										${cons.ZGZS }
									</td>
									<td>
										${cons.TYMC }
									</td>
									<td>
										${cons.SL }
									</td>
									<td>
										${cons.FY }
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="8">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="8">
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
			var e=$("#akb022").val();
			changeShowSelect(e);
		});
		//查询
		function query(){
			showBlock();
			checkDate();
			/*
			var aaa027 = $("#aaa027").val();
			var hosIds = $("#hosIds").val();
			var docId = $("#docId").val();
			if(aaa027 == ""){
				alert("请选择区县。");
				return false;
			}
			if(hosIds == ""){
				alert("请选择医疗机构。");
				return false;
			}
			if(docId == ""){
				alert("请选择医师。");
				return false;
			}
			*/
			$("#start").val(0);
			form.action = "zyyp_doctorTotalStatisticsQuery.action";
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
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_ydHosSelect.action?condition.akb022='+e,"医疗机构查询",wndFeatures);
		}
		
		//医师选择
		function docSelect(){
			var hosIds = $("#hosIds").val();
			var ydIds = $("#ydIds").val();
			var ids = "";
			if(hosIds!=""&&ydIds!=""){
				ids = hosIds + "," + ydIds;
			}else if(hosIds!=""){
				ids = hosIds;
			}else if(ydIds!=""){
				ids = ydIds;
			}
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_docSelect.action?condition.hosIds='+ids+"&condition.sign=1","医师查询",wndFeatures);
		}
		
		//导出Excel 
		function exportExcel(){
			alert("EXCEL导出中请耐心等待...");
			form.action = "zyyp_exportExcelSingleLine.action?condition.divId=1";
			form.target = '_self';
			form.submit();
		}
		
		
		//机构类别选择时禁用不相应的选择框
		function changeShowSelect(value){
			if(value==""){
				document.getElementById("hosNames").removeAttribute("disabled");
				document.getElementById("ydNames").removeAttribute("disabled");
			}else if(value==1){
				$("#ydIds").val("");
				$("#ydNames").val("");
				document.getElementById("ydNames").disabled = "disabled";
				document.getElementById("hosNames").removeAttribute("disabled");
			}else{
				$("#hosIds").val("");
				$("#hosNames").val("");
				document.getElementById("hosNames").disabled = "disabled";
				document.getElementById("ydNames").removeAttribute("disabled");
			}
		}
		
		//图形化展示
		function showDemo(){
			var params = "&condition.startDate="+encodeURI(encodeURI($("#tempStart").val()))+"&condition.endDate="+encodeURI(encodeURI($("#tempEnd").val()))
				+"&condition.akc021="+$("#akc021").val()+"&condition.hosIds="+$("#hosIds").val()
				+"&condition.akb022="+$("#akb022").val()+"&condition.docId="+$("#docId").val()
				+"&condiiton.ydIds="+$("#ydIds").val()
				+"&pageSet.limit="+'${pageSet.limit}'+"&pageSet.start="+'${pageSet.start}';
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_showYsDemo.action?params='+params,"医生特定中药饮片统计数据查询图形化展示",wndFeatures);
		}
	</script>
</body>
</html>
