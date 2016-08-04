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
		<title>药店刷卡频次统计</title>
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
	<form action="hztj_swipeFrequencyStatistics.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								日期	：
								<input type="hidden" id="tempStart" value="${condition.startDate}"/>
								<input type="hidden" id="tempEnd" value="${condition.endDate}"/>
							</td>
							<td style="text-align: left;">
								<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
							</td>
							<td style="padding-left: 10px;width: 100px;">区县：</td>
							<td style="text-align: left;width: 100px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select> 
							</td>
							<td>药店名称：</td>
							<td style="text-align: left;">	
								<input type="hidden" id="ydIds" name="condition.ydIds" value="${condition.ydIds}"/>
								<input name="condition.ydNames" id="ydNames" value="${condition.ydNames}" onclick="openSelectWindow('2');" readonly="readonly" style="width:100px;"/>
							</td>
							<td>就诊类型：</td>
							<td style="text-align: left;">
								<input type="hidden" value="${condition.aka130}" id="tempAka130"/>
								<s:select id="aka130" name="condition.aka130" list="#request.aka130Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select> 
							</td>
						</tr>
						<tr>
							<td>
								时间	：
								<input type="hidden" id="tempStartTime" value="${condition.startTime}"/>
								<input type="hidden" id="tempEndTime" value="${condition.endTime}"/>
							</td>
							<td style="text-align: left;">
								<input type="text" name="condition.startTime" id="startTime" class="iconDate" style="width:128px;"/> 
								至
								<input type="text" name="condition.endTime" id="endTime" class="iconDate" style="width:128px;"/> 
							</td>
							<td>时间间隔：</td>
							<td style="text-align: left;">	
								<input name="condition.sjjg" id="sjjg" value="${condition.sjjg}" style="width: 50px;"/>
								<select id="sjlb" name="condition.sjlb">
									<option value="分钟">分钟</option>
								</select>
							</td>
							<td>频次：</td>
							<td style="text-align: left;">
								<input name="condition.pc" id="pc" value="${condition.pc}" style="width:100px;"/>
							</td>
							<td>排名数量：</td>
							<td style="text-align: left;">
								<input name="condition.pmsl" id="pmsl" value="${condition.pmsl}" style="width:100px;"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;" colspan="8">
								<input type="button" title="生成计划" onclick="generatePlan();" value="生成计划" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" style="width : 1560px;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 300px;">日期范围</th>
								<th style="width: 200px;">时间范围</th>
								<th style="width: 100px;">区县</th>
								<th style="width: 350px;">药店</th>
								<th style="width: 120px;">就诊类型</th>
								<th style="width: 100px;">时间间隔</th>
								<th style="width: 50px;">频次</th>
								<th style="width: 80px;">排名数量</th>
								<th style="width: 100px;">计划生成时间</th>
								<th style="width: 80px;">计划状态</th>
								<th style="width: 80px;">操作</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.PARA1 }至${cons.PARA2 }
									</td>
									<td>
										${cons.PARA3 }至${cons.PARA4 }
									</td>
									<td>
										${aaa027Map[cons.PARA5] }
									</td>
									<td title="<s:property value="#cons.AKB021"/>">
										<s:if test="#cons.AKB021.length()>26">
											<s:property value="#cons.AKB021.substring(0, 26)"/>...
										</s:if>
										<s:else>
											<s:property value="#cons.AKB021"/>
										</s:else>
									</td>
									<td>
										${aka130Map[cons.PARA7] }
									</td>
									<td>
										${cons.PARA8 }分钟
									</td>
									<td>
										${cons.PARA9 }
									</td>
									<td>
										${cons.PARA10 }
									</td>
									<td>
										<s:date name="#cons.XGSJ" format="yyyy-MM-dd"/>
									</td>
									<td>
										<s:if test="#cons.ZXZT==0">
											未执行
											<%--<a href="#" onclick="executePlan('<s:property value="#cons.ID"/>');">执行计划</a>--%>
										</s:if>
										<s:elseif test="#cons.ZXZT==1">
											正在执行
										</s:elseif>
										<s:elseif test="#cons.ZXZT==2">
											执行完成
										</s:elseif>
										<s:else>
											取消
										</s:else>
									</td>
									<td>
										<s:if test="#cons.ZXZT==2">
											<a href="#" onclick="queryDetail('<s:property value="#cons"/>','<s:property value="#cons.ID"/>','<s:property value="#cons.AKB021"/>');">查询</a>
										</s:if>	
										<s:else>
											待查询
										</s:else>
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
		$(document).ready(function(){
			var startDate = $("#tempStart").val();
			var endDate = $("#tempEnd").val();
			var startTime = $("#tempStartTime").val();
			var endTime = $("#tempEndTime").val();
			$("#aka130").val($("#tempAka130").val());
			$("#aaa027").val($("#tempAaa027").val());
			if(startDate==""&&endDate==""){
				var start = new Date().addMonth(MONT_SUB_AMOUNT);
				$("#startDate").val(start.format("yyyy-MM-dd"));
				$("#endDate").val(start.format("yyyy-MM-dd"));
			}else{
				$("#startDate").val(startDate);
				$("#endDate").val(endDate);
			}
			if(startTime==""&&endTime==""){
//				var start = new Date().addMonth(MONT_SUB_AMOUNT);
//				$("#startTime").val(start.format(VIEW_TIME_FORMAT));
//				$("#endTime").val(start.format(VIEW_TIME_FORMAT));
				$("#startTime").val("07:00:00");
				$("#endTime").val("07:30:00");
			}else{
				$("#startTime").val(startTime);
				$("#endTime").val(endTime);
			}
			$("#startDate").click(function() {
				WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true});
			});
			$("#endDate").click(function() {
				WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true});
			});
			$("#startTime").click(function() {
				WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false,readOnly:true});
			});
			$("#endTime").click(function() {
				WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false,readOnly:true});
			});
		});
		
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
			if(aaa027 == ""){
				alert("请先选择区县在选择药店！");
				return false;
			}
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('zyyp_ydHosSelect.action?condition.akb022='+e+"&condition.aaa027="+aaa027,"医疗机构查询",wndFeatures);
		}
		
		//生成计划
		function generatePlan(){
			checkDate();
			var aaa027 = $("#aaa027").val();
			if(aaa027 == ""){
				alert("请选择区县！");
				return false;
			}
			var ydIds = $("#ydIds").val();
			if(ydIds.length > 50){
				alert("最多能选7家药店，请重新选择！");
				return false;
			}
			var sjjg = $("#sjjg").val();
			if(sjjg==""){
				alert("请输入时间间隔。");
				return false;
			}
			$.ajax({
					url:"hztj_queryGeneratePlan.action",
					type:"POST",
					dataType:"json",
					data:{
						aaa027:aaa027
					},
					success:function(data){
						if(data[0].ZS<4){
							form.action = "hztj_generatePlan.action";
							form.target = '_self';
							form.submit();
						}else{
							alert("该区县制定的计划未执行数量超过4条，不能再新制定计划，请等待已制定计划执行完成在新增计划。");
							return false;
						}
					}
				});
		}
		
		//查询
		function queryDetail(e,id,akb021){
			var temp = e.substring(1,e.length-1).split(",");
			var map = {};
			for(var m =0;m<temp.length;m++){
				map[temp[m].split('=')[0].trim()] = temp[m].split('=')[1];
			}
			var params = "&condition.startDate="+encodeURI(encodeURI(map['PARA1']))+"&condition.endDate="+encodeURI(encodeURI(map['PARA2']))
				+"&condition.startTime="+map['PARA3']+"&condition.endTime="+map['PARA4']
				+"&condition.aaa027="+map['PARA5']+"&condition.ydNames="+encodeURI(encodeURI(akb021))
				+"&condition.aka130="+map['PARA7']+"&condition.sjjg="+encodeURI(encodeURI(map['PARA8']))
				+"&condition.pc="+map['PARA9']+"&condition.pmsl="+map['PARA10'];
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:no;status:no;";
			window.open("hztj_queryGenerateDetail.action?condition.id="+id+"&params="+params,"计划查询",wndFeatures);
		}
		
		function executePlan(id){
			alert(id);
			form.action = "hztj_executePlan.action?condition.id="+id;
			form.target = '_self';
			form.submit();
		}
	</script>
</body>
</html>
