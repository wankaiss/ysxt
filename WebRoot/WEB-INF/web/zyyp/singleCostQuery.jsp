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
		<title>单贴价格与味数查询</title>
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
		 	 style="overflow-x:srolll; overflow-y:hidden; height:425px"--%>
		<script type="text/javascript" src="${path}js/jssearch/j.suggest.js"></script>
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="zyyp_singleCostQuery.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="10%">
								统计月份：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
								<input type="hidden" value="${condition.divId}" id="divId" name="condition.divId"/>
							</td>
							<td style="text-align: left;" width="35%">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;" width="10%">区县选择：</td>
							<td style="text-align: left;" width="15%">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select> 
							</td>
							<td width="10%">人员类别：</td>
							<td style="text-align: left;" width="20%">
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
							<%--<td>机构类别：</td>
							<td style="text-align: left;">
								<select name="condition.akb022" id="akb022" style="width:100px;" onchange="changeShowSelect(this.value);">
									<option value="" class="optionstyle">
										全部
									</option>
									<option value="1" <s:if test="1==condition.akb022"> selected="selected"</s:if>>医疗机构</option>
									<option value="2" <s:if test="2==condition.akb022"> selected="selected"</s:if>>零售药店</option>
								</select>
							</td>
							--%>
							<td>
								单贴价格区间：
							</td>
							<td style="text-align: left;">
								<input id="dtjgStart" name="condition.dtjgStart" value="${condition.dtjgStart}" style="width:128px;"/>元
								~<input id="dtjgEnd" name="condition.dtjgEnd" value="${condition.dtjgEnd}" style="width:128px;"/>元
							</td>
							<td>
								味数：
							</td>
							<td colspan="3" style="text-align: left;">
								<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
									id="wsStart" name="condition.wsStart" value="${condition.wsStart}" style="width:128px;"/>味
								~<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
									id="wsEnd" name="condition.wsEnd" value="${condition.wsEnd}" style="width:128px;"/>味
							</td>
						</tr>
						<tr>
							<td>医院名称：</td>
							<td style="text-align: left;"s>	
								<input type="hidden" id="hosIds" name="condition.hosIds" value="${condition.hosIds}"/>
								<input name="condition.hosNames" id="hosNames" value="${condition.hosNames}" onclick="openSelectWindow('1');" readonly="readonly" style="width:100px;"/>
							</td>
							<%--<td>药店名称：</td>
							<td>
								<input type="hidden" id="ydIds" name="condition.ydIds" value="${condition.ydIds}"/>
								<input name="condition.ydNames" id="ydNames" value="${condition.ydNames}" onclick="openSelectWindow('2');" readonly="readonly" style="width:100px;"/>
							</td>
							--%>
							<td>医生名称：</td>
							<td style="text-align: left;">
								<input type="hidden" id="docId" name="condition.docId" value="${condition.docId}"/>
								<input name="condition.docName" id="docName" value="${condition.docName}" onclick="docSelect();" readonly="readonly" style="width:100px;"/>
							</td>
							<td style="text-align: right;" colspan="2">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabBox" style="padding-top: 10px;padding-left: 15px;">
					<ul class="menuTab">
				        <li id="li1"><a href="#" target="_self" onclick="changTabShow('1');">逐级下帖</a></li>
				        <li id="li2"><a href="#" target="_self" onclick="changTabShow('2');">显示明细</a></li>
				    </ul>
				</div>
				<div class="wd-content-box"  id="div1" >
					<table class="date_tab" style="width : 1800px;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;">姓名</th>
								<th style="width: 100px;">医保号</th>
								<th style="width: 150px;">就诊编号</th>
								<th style="width: 100px;">就诊类型</th>
								<th style="width: 200px;">诊断</th>
								<th style="width: 150px;">就诊时间</th>
								<th style="width: 100px;">医生姓名</th>
								<th style="width: 100px;">医院代码</th>
								<th style="width: 200px;">医院名称</th>
								<th style="width: 100px;">单贴价格</th>
								<th style="width: 100px;">单贴味数</th>
								<th style="width: 100px;">外配处方医院</th>
								<th style="width: 100px;">外配处方医生</th>
								<th style="width: 100px;">状态</th>
								<th style="width: 100px;">总费用</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.JZBH }
									</td>
									<td>
										${aka130Map[cons.JYJSLB] }
									</td>
									<td>
										${cons.ZD_NAME }
									</td>
									<td>
										<s:date name="#cons.TRADE_TIME" format="yyyyMMdd"/>
									</td>
									<td>
										${cons.YSXM }
									</td>
									<td>
										${cons.DDJG_ID }
									</td>
									<td>
										${cons.NAME }
									</td>
									<td>
										<fmt:formatNumber value="${cons.DTJG }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.YPWS }" pattern="#,###" minFractionDigits="0"/>
									</td>
									<td>
										${cons.WPNAME }
									</td>
									<td>
										${cons.WPYSXM }
									</td>
									<td>
										${cons.ZT }
									</td>
									<td>
										<fmt:formatNumber value="${cons.YPFY }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<a href="#" onclick="queryDetail('<s:property value="#cons"/>','<s:property value="#cons.JZBH"/>');">查看明细</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="15">
									查无数据
								</td>
							</tr>
						</s:else>
					</table>
				</div>
				<div class="wd-content-box"  id="div2" style="overflow-x:srolll; overflow-y:hidden; height:425px; display: none">
					<table class="date_tab" style="width : 3100px;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;">姓名</th>
								<th style="width: 100px;">医保号</th>
								<th style="width: 150px;">就诊编号</th>
								<th style="width: 100px;">就诊类型</th>
								<th style="width: 200px;">诊断</th>
								<th style="width: 150px;">就诊时间</th>
								<th style="width: 100px;">医生姓名</th>
								<th style="width: 100px;">医院代码</th>
								<th style="width: 300px;">医院名称</th>
								<th style="width: 100px;">单贴价格</th>
								<th style="width: 100px;">单贴味数</th>
								<th style="width: 100px;">外配处方医院</th>
								<th style="width: 100px;">外配处方医生</th>
								<th style="width: 100px;">总费用</th>
								<th style="width: 100px;">明细项目编码</th>
								<th style="width: 100px;">明细项目名称</th>
								<th style="width: 100px;">明细金额</th>
								<th style="width: 100px;">自付比例</th>
								<th style="width: 300px;">通用名</th>
								<th style="width: 100px;">药品数量</th>
								<th style="width: 100px;">贴数</th>
								<th style="width: 100px;">每次用量</th>
								<th style="width: 100px;">用法标准</th>
								<th style="width: 100px;">规格包装</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.JZBH }
									</td>
									<td>
										${aka130Map[cons.JYJSLB] }
									</td>
									<td>
										${cons.ZD_NAME }
									</td>
									<td>
										<s:date name="#cons.TRADE_TIME" format="yyyyMMdd"/>
									</td>
									<td>
										${cons.YSXM }
									</td>
									<td>
										${cons.DDJG_ID }
									</td>
									<td>
										${cons.NAME }
									</td>
									<td>
										<fmt:formatNumber value="${cons.DTJG }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.YPWS }" pattern="#,###" minFractionDigits="0"/>
									</td>
									<td>
										${cons.WPNAME }
									</td>
									<td>
										${cons.WPYSXM }
									</td>
									<td>
										<fmt:formatNumber value="${cons.YPFY }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										${cons.MX_ID }
									</td>
									<td>
										${cons.MX_MC }
									</td>
									<td>
										<fmt:formatNumber value="${cons.FYZE }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										${cons.ZLBL }
									</td>
									<td>
										${cons.TYM_MC }
									</td>
									<td>
										${cons.SL }
									</td>
									<td>
										${cons.YPTS }
									</td>
									<td>
										${cons.MCYL }
									</td>
									<td>
										${cons.YFBZ }
									</td>
									<td>
										${cons.BZGG }
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="24">
									查无数据
								</td>
							</tr>
						</s:else>
					</table>
				</div>
				<tfoot>
					<tr>
					   <td colspan="24">
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
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var divId = $("#divId").val();
		$(document).ready(function(){
			if(divId==1){
				$("ul.menuTab li:first-child").addClass("current");
			}else{
				document.getElementById("div2").style.display = "";
				document.getElementById("div1").style.display = "none";
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
			}
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
		});
		
		//更换tab页
		function changTabShow(idx){
			if(idx == 1){
				$("#divId").val("1");
				document.getElementById("div1").style.display = "";
				document.getElementById("div2").style.display = "none";
				$("#li1").addClass("current");
				$("#li1").siblings().removeClass("current");
				query();
			}else if(idx == 2){
				$("#divId").val("2");
				document.getElementById("div2").style.display = "";
				document.getElementById("div1").style.display = "none";
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
				query();
			}
		}
		
		//查询
		function query(){
			showBlock();
			checkDate();
			$("#start").val(0);
			form.action = "zyyp_singleCostQuery.action";
			form.target = '_self';
			form.submit();
		}
		//日期校验
		function checkDate(){
			var start = $("#startDate").val();
			var end = $("#endDate").val();
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
		
		//医师选择
		function docSelect(){
			var hosIds = $("#hosIds").val();
			if(hosIds == ""){
				alert("请先选择医院再选择医师。");
				return false;
			}
			var wndFeatures = "width=850px;height=450px;scrollbars=yes;resizable=no;status=no;toolbar=no;";
			window.open('zyyp_docSelect.action?condition.hosIds='+hosIds,"医师查询",wndFeatures);
		}
		
		
		//导出Excel 
		function exportExcel(){
			alert("数据导出中，请耐心等待...");
			form.action = "zyyp_exportExcelSingleCost.action?condition.divId="+divId;
			form.target = '_self';
			form.submit();
		}
		
		//查看明细
		function queryDetail(e,jzbh){
			var temp = e.substring(1,e.length-1).split(",");
			var map = {};
			for(var m =0;m<temp.length;m++){
				map[temp[m].split('=')[0].trim()] = temp[m].split('=')[1];
			}
			var params = "&condition.aac003="+encodeURI(encodeURI(map['XM']))+"&condition.aac001="+map['YBH']
				+"&condition.aaz370="+map['JZBH']+"&condition.aka130="+encodeURI(encodeURI(map['JYJSLB']))
				+"&condition.akc050="+encodeURI(encodeURI(map['ZD_NAME']))+"&condition.akc194="+map['TRADE_TIME']
				+"&condition.ysxm="+encodeURI(encodeURI(map['YSXM']))+"&condition.akb020="+map['DDJG_ID']
				+"&condition.akb021="+encodeURI(encodeURI(map['NAME']))+"&condition.wpddjg_id="+encodeURI(encodeURI(map['WPNAME']))
				+"&condition.wpysxm="+encodeURI(encodeURI(map['WPYSXM']))+"&condition.zfy="+map['YPFY']
				+"&condition.jylsh="+encodeURI(encodeURI(map['JYLSH']))+"&condition.mxzdh="+encodeURI(encodeURI(map['MXZDH']));
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:yes;status:no;";
			window.showModalDialog("zyyp_queryCostDetail.action?condition.aaz217="+jzbh+"&params="+params+"&condition.sign=1","费用明细",wndFeatures);
		}
		
		//就诊类型多选
		function openCheckBox(){
			var tempAka130 = $("#tempAka130").val();
			var json = {tempAka130:tempAka130}
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:yes;status:no;";
			var address=window.showModalDialog("zyyp_openCheckBox.action",json,wndFeatures);
			if(address != null){
				var tt = address.split("|");
				document.getElementById("tempAka130").value = tt[0];
				document.getElementById("tempAka130Names").value = tt[1];
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
				document.getElementById("ydNames").disabled = "disabled";
				document.getElementById("hosNames").removeAttribute("disabled");
			}else{
				$("#hosIds").val("");
				$("#hosNames").val("");
				document.getElementById("hosNames").disabled = "disabled";
				document.getElementById("ydNames").removeAttribute("disabled");
			}
		}
		
	</script>
</body>
</html>
