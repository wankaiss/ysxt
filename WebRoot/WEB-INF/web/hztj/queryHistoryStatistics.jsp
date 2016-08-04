<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Map aaa027Map = (Map)request.getAttribute("aaa027Map");
    request.setAttribute("aaa027Map", aaa027Map);  
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店综合分析参数维护</title>
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
	<form action="hztj_queryHistoryStatistics.action" method="post" name="form" id="chooseForm" target="qshzMain">
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
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									参数名称
								</th>
								<th>
									权重值
								</th>
								<th>
									参数适用范围
								</th>
								<th>
									权重识别标志
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
										${cons.QZM }
									</td>
									<td>
										<span class="span1">${cons.QZZ }%</span>
										<span class="span2" style="display: none;"><input type="text" value="${cons.QZZ }" name="" maxlength="5"/>%<span>
									</td>
									<td>
										${aaa027Map[cons.TCDM] }
									</td>
									<td>
										${cons.GZBZ}
									</td>
									<td>
										<s:if test="#cons.QZM == '增长率'">
											<a href="#" onclick="viewGrowSetting('1','<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">查看详细设置</a>
										</s:if>
										<s:elseif test="#cons.QZM == '药店费用等级跨越'">
											<a href="#" onclick="viewGrowSetting('2','<s:property value="#cons.TCDM"/>','<s:property value="#cons.ID"/>','<s:property value="#cons.GZBZ"/>');">查看详细设置</a>&nbsp;&nbsp;&nbsp;
											<%--<a href="#" onclick="queryHosData();">药店分类数据查询</a>&nbsp;&nbsp;&nbsp;--%>
											<a href="#" onclick="prescriptionSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">查看处方药非处方药权重设置</a>
										</s:elseif>
										<s:elseif test="#cons.QZM == '单次非处方购药费用占比'">
											<a href="#" onclick="viewGrowSetting('3','<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">查看详细设置</a>&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="amountSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">查看费用额设置</a>&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="oldAndNowSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">查看当年账户历年账户权重设置</a>
										</s:elseif>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="5">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="5">
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
		var bdAaa027 = "";
		$(document).ready(function(){
			$("#aaa027").val($("#tempAaa027").val());
			bdAaa027 = $("#tempAaa027").val();
		});
		//查询
		function query(){
			showBlock();
			$("#start").val(0);
			form.action="hztj_queryHistoryStatistics.action";
			form.target='_self';
			form.submit();
		}
		
		//查看详细设置
		function viewGrowSetting(idx,tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			if(idx == '1'){
				//增长率设置
				window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz+"&condition.type=1"+"&condition.qzjb=2",tcdm,wndFeatures);
			}else if(idx == '2'){
				//药店费用等级跨越
				window.open('hztj_viewMedLevelSetting.action?condition.tcdm='+tcdm+"&condition.id="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
			}else if(idx == '3'){
				//单次非处方购药费用占比
				//window.showModalDialog('hztj_singleMedPay.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.divId=1",tcdm,wndFeatures);
				window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.divId=1"+"&condition.gzbz="+gzbz+"&condition.qzjb=7"+"&condition.type=7",tcdm,wndFeatures);
			}
		}
		
		//查看处方药非处方药权重设置
		function prescriptionSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz+"&condition.type=6"+"&condition.qzjb=4",tcdm,wndFeatures);
		}
		
		//费用额设置
		function amountSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz+"&condition.type=8"+"&condition.qzjb=6",tcdm,wndFeatures);
		}
		
		//当年账户历年账户权重设置
		function oldAndNowSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_viewGrowSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz+"&condition.type=9"+"&condition.qzjb=5",tcdm,wndFeatures);
		}
	</script>
</body>
</html>
