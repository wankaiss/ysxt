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
		<title>药店刷卡频次统计显示明细</title>
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
	<form action="hztj_queryPlanDetail.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;text-align: center;">医保号</th>
								<th style="width: 350px;text-align: center;">身份证号</th>
								<th style="width: 100px;text-align: center;">人员类别</th>
								<th style="width: 100px;text-align: center;">姓名</th>
								<%--<th style="width: 150px;text-align: center;">单位名称</th>
								--%><th style="width: 100px;text-align: center;">费用</th>
								<th style="width: 100px;text-align: center;">频次</th>
								<th style="width: 100px;text-align: center;">操作</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.SFZH }
									</td>
									<td>
										${akc021Map[cons.RYLB] }
									</td>
									<td>
										${cons.XM }
									</td>
									<%--<td>
										${cons.DWMC }
									</td>
									--%><td>
										${cons.JZFY }
									</td>
									<td>
										${cons.PC }
									</td>
									<td>
										<a href="hztj_queryJzmxDetail.action?condition.id=<s:property value="#cons.SFZH"/>&condition.kssj=<s:date name="condition.kssj"/>&condition.jssj=<s:date name="condition.jssj"/>" target="detailWindow");">就诊明细</a>
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
		//查询
		function queryDetail(id){
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:no;status:no;";
			window.open("hztj_queryPlanDetail.action?condition.id="+id,"计划查询",wndFeatures);
		}
	</script>
</body>
</html>
