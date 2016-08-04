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
		<title>异常就医人员筛选</title>
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
	<form action="cbr_queryChooseExceptionPerson.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								统计周期：<s:property value="condition.yearMonth"/> 
								<input type="hidden" id="yearMonth" name="condition.yearMonth" value="${condition.yearMonth}"/>
								<input type="hidden" id="aaa027" name="condition.aaa027" value="${condition.aaa027}"/>
								<input type="hidden" id="aka130" name="condition.aka130" value="${condition.aka130}"/>
								<input type="hidden" id="pjfy" name="condition.pjfy" value="${condition.pjfy}"/>
								<input type="hidden" id="pjcs" name="condition.pjcs" value="${condition.pjcs}"/>
								<input type="hidden" name="condition.id" value="${condition.id}"/>
							</td>
							<td>区县：${aaa027Map[condition.aaa027]}</td>
							<td>就诊类型：${aka130Map[condition.aka130]}</td>
							<td>平均就诊费用：<fmt:formatNumber value="${condition.pjfy}" pattern="#,###.##" minFractionDigits="2"/></td>
							<td>平均就诊次数：<fmt:formatNumber value="${condition.pjcs}" pattern="#,###" minFractionDigits="0"/></td>
						</tr>
					</table>
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="width: 120px;" rowspan="7">参数类型：
								<select id="sign" name="condition.sign" onchange="changeValue(this.value);">
									<option value="日常" <s:if test="'日常'==condition.sign"> selected="selected"</s:if>>日常</option>
									<option value="临时" <s:if test="'临时'==condition.sign"> selected="selected"</s:if>>临时</option>
								</select>
							</td>
							<td style="width: 600px;">就医人员平均就诊费用大于等于该类型所有就医人员平均就诊费用<input id="rc_pjfy" name="condition.rc_pjfy" value="${condition.rc_pjfy }" style="width: 50px;"/>倍</td>
						</tr>
						<tr>
							<td style="width: 600px;">
								<span style="color: blue;font-size: 16px;">或</span>
							</td>
						</tr>
						<tr>
							<td style="width: 600px;">就医人员平均就诊次数大于等于该类型所有就医人员平均就诊次数<input id="rc_pjcs" name="condition.rc_pjcs" value="${condition.rc_pjcs }" style="width: 50px;"/>倍</td>
						</tr>
						<tr>
							<td style="width: 600px;">
								<span style="color: blue;font-size: 16px;">或</span>
							</td>
						</tr>
						<tr>
							<td style="width: 600px;">就医人员就诊费用总额大于等于<input id="rc_fyze" name="condition.rc_fyze" value="${condition.rc_fyze }" style="width: 50px;"/>元</td>
						</tr>
						<tr>
							<td style="width: 600px;">
								<span style="color: blue;font-size: 16px;">或</span>
							</td>
						</tr>
						<tr>
							<td style="width: 600px;">就医人员就诊次数大于等于<input id="rc_cszs" name="condition.rc_cszs" value="${condition.rc_cszs }" style="width: 50px;"/>次</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: right;">
								<s:if test="condition.rc_pjfy==null&&rc_pjcs==null&&condition.rc_fyze==null&&condition.rc_cszs==null">
									<input type="button" id="submitSaveBtn" onclick="saveData();" value="保存并查询"/>
								</s:if>
								<input type="button" onclick="query();" value="查询"/>
								<input type="button" onclick="queryPerson();" value="获取待查人员"/>
								<input type="button" onclick="exportExcel('2');" value="导出Excel"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									医保号
								</th>
								<th>
									姓名
								</th>
								<th>
									年龄
								</th>
								<th>
									区县
								</th>
								<th>
									身份证号
								</th>
								<%--<th>
									单位名称
								</th>--%>
								<th>
									费用总额
								</th>
								<th>
									就诊次数
								</th>
								<th>
									历年账户支付费用
								</th>
								<th>
									平均就诊费用
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.NL }
									</td>
									<td>
										${aaa027Map[cons.TCDM] }
									</td>
									<td>
										${cons.SFZHM }
									</td>
									<%--<td>
										
									</td>--%>
									<td>
										<fmt:formatNumber value="${cons.FYZE}" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										${cons.JZCS }
									</td>
									<td>
										<fmt:formatNumber value="${cons.LNZHZFFY}" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.PJFY}" pattern="#,###.##" minFractionDigits="2"/>
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
		//查询
		function query(){
			showBlock();
			$("#start").val(0);
			form.action="cbr_queryChooseExceptionPerson.action";
			form.target='_self';
			form.submit();
		}
		
		//选择框变更
		function changeValue(val){
			var aaa027 = $("#aaa027").val(); 
			if(val=='日常'){
				//document.getElementById("submitSaveBtn").style.display = "";
				$.ajax({
					url:"cbr_queryRc.action",
					type:"POST",
					dataType:"json",
					data:{
						aaa027:aaa027
					},
					success:function(data){
						$("#rc_pjfy").val(data[0].QZM);   
						$("#rc_pjcs").val(data[0].QZDM);
						$("#rc_fyze").val(data[0].SJQZID);  
						$("#rc_cszs").val(data[0].QZZ);
					}
				});
			}else{
				//document.getElementById("submitSaveBtn").style.display = "none";
				$("#rc_pjfy").val("");   
				$("#rc_pjcs").val("");
				$("#rc_fyze").val("");  
				$("#rc_cszs").val("");
			}
		}
		
		//保存日常查询条件
		function saveData(){
			var rc_pjfy = $("#rc_pjfy").val();
			var rc_pjcs = $("#rc_pjcs").val();
			var rc_fyze = $("#rc_fyze").val();
			var rc_cszs = $("#rc_cszs").val();
			var aaa027 = $("#aaa027").val(); 
			$.ajax({
				url:"cbr_saveRcQueryCondition.action",
				cache:false,
				type:"post",
				data:{
					rc_pjfy:rc_pjfy,
					rc_pjcs:rc_pjcs,
					rc_fyze:rc_fyze,
					rc_cszs:rc_cszs,
					aaa027:aaa027
				},
				success:function(data){
					query();
				}
			})
		}
		
		//导出Excel
		function exportExcel(divId){
			$("#start").val(0);
			form.action="cbr_exportExcel.action?condition.divId="+divId;
			form.target='_self';
			form.submit();
		}
		
		//获取待查人员
		function queryPerson(){
			var rc_pjfy = $("#rc_pjfy").val();
			var rc_pjcs = $("#rc_pjcs").val();
			var rc_fyze = $("#rc_fyze").val();
			var rc_cszs = $("#rc_cszs").val();
			var yearMonth = $("#yearMonth").val();
			var aaa027 = $("#aaa027").val();
			var aka130 = $("#aka130").val();
			var pjfy = $("#pjfy").val();
			var pjcs = $("#pjcs").val();
			var params = "&condition.aka130="+aka130+"&condition.aaa027="+aaa027+"&condition.rc_pjfy="+rc_pjfy
						+"&condition.rc_pjcs="+rc_pjcs+"&condition.rc_fyze="+rc_fyze+"&condition.rc_cszs="+rc_cszs
						+"&condition.yearMonth="+encodeURI(encodeURI(yearMonth))+"&condition.pjfy="+pjfy+"&condition.pjcs="+pjcs;
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:no;status:no;";
			window.open('cbr_queryPerson.action?params='+params,"待查人员信息",wndFeatures);
		}
	</script>
</body>
</html>
