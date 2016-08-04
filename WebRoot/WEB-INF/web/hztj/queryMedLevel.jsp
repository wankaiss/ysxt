<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店等级设置</title>
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
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="hztj_queryMedLevel.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left: 10px;width: 100px;">当前区县：</td>
							<td style="text-align: left;">
								<s:select id="aaa027" name="condition.tcdm" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" disabled="true">  
								</s:select>
								<input type="hidden" value="${condition.tcdm}" id="tempAaa027"/>
							</td>
							<td>药店名称：</td>
							<td style="text-align: left;">
								<input type="hidden" id="ydIds" name="condition.ydIds" value="${condition.ydIds}"/>
								<input name="condition.ydNames" id="ydNames" value="${condition.ydNames}" onclick="openSelectWindow('2');" readonly="readonly" style="width:100px;"/>
							</td>
							<td>统计年度：</td>
							<td style="text-align: left;">
								<input type="hidden" id="sjkjReturn" value="${condition.byZrYear }"/>
								<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
								<input type="hidden" id="sjkj" name="condition.byYear" value=""/>
								<input type="hidden" id="sjkj_type" name="condition.type" value="4"/>
								<input type="hidden" name="condition.sxz" value="${condition.sxz }"/>
								<input type="hidden" name="condition.xxz" value="${condition.xxz }"/>
								<input type="hidden" name="condition.tsxz" value="${condition.tsxz }"/>
								<input type="hidden" name="condition.txxz" value="${condition.txxz }"/>
								<input type="text" name="startDate" id="startDate" class="iconDate" onchange="select_View_Date();"  style="width:128px;"/>
							</td>
							<td>
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 80px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									区县
								</th>
								<th>
									药店名称
								</th>
								<th>
									非处方药费用范围
								</th>
								<th>
									处方药费用范围
								</th>
								<th>
									平均非处方药费用
								</th>
								<th>
									平均处方药费用
								</th>
								<th>
									建议非处方药分类等级
								</th>
								<th>
									建议处方药分类等级
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										<s:property value="condition.yearMonth"/>
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										<s:property value="condition.xxz"/>万~<s:property value="condition.sxz"/>万
									</td>
									<td>
										<s:property value="condition.txxz"/>万~<s:property value="condition.tsxz"/>万
									</td>
									<td>
										${cons.ZFY }
									</td>
									<td>
										${akc021Map[cons.RYLB] }
									</td>
									<td>
										${cons.TBMC }
									</td>
									<td>
										${cons.FCFYFY }
									</td>
									<td>
										<a href="#" onclick="">等级设置</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="9">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="9">
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
		//初始化页面向输入框中赋值
		$(document).ready(function(){
			$("#aaa027").val($("#tempAaa027").val());
			if($("#sjkjReturn").val()==""){
				init_system_date_overload('4');
				var year = new Date().getFullYear();
				$("#startDate").val(year+"年");
				$("#sjkj").attr("value",date.startDate);
			}
			
			var value =$("#sjkjReturn").val();
			if($("#sjkjTypeReturn").val()==4){
				init_system_date_overload('4');
				$("#btnzryear").attr("class","btnBlue");
				var year = value.substring(0,4);
				$("#startDate").val(year+"年");
				$("#sjkj").attr("value",date.startDate);
			}
		});
		
		//查询
		function query(){
			$("#start").val(0);
			form.action = "hztj_queryMedLevel.action";
			form.target='_self';
			form.submit();
		}
		
		//导出Excel
		function exportExcel(){
			form.action = "hztj_exportExcel.action?condition.divId=3";
			form.target='_self';
			form.submit();
		}
		
		//医院或药店选择
		function openSelectWindow(e){
			var aaa027 = $("#aaa027").val();
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('zyyp_ydHosSelect.action?condition.akb022='+e+"&condition.aaa027="+aaa027,"医疗机构查询",wndFeatures);
		}
	</script>
</body>
</html>
