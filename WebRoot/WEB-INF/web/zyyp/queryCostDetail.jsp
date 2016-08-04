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
		<title>单贴价格与味数查询查看费用明细</title>
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
	<form action="zyyp_queryCostDetail.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								姓名：
							</td>
							<td style="text-align: left;">
								<s:property value="condition.aac003"/>
								<input type="hidden" value="${condition.aac003 }" name="condition.aac003"/>
							</td>
							<td style="padding-left: 10px;width: 100px;">医保号：</td>
							<td style="text-align: left;width: 100px;">
								<s:property value="condition.aac001"/>
								<input type="hidden" value="${condition.aac001 }" name="condition.aac001"/>
							</td>
							<td>就诊编号：</td>
							<td style="text-align: left;">
								<s:property value="condition.aaz370"/>
								<input type="hidden" value="${condition.aaz370 }" name="condition.aaz370"/>
								<input type="hidden" value="${condition.aaz370 }" name="condition.aaz217"/>
							</td>
							<td>就诊时间：</td>
							<td style="text-align: left;">
								<s:property value="condition.akc194"/>
								<input type="hidden" value="${condition.akc194 }" name="condition.akc194"/>
							</td>
						</tr>
						<tr>
							<td>就诊类型：</td>
							<td style="text-align: left;">
								${aka130Map[condition.aka130] }
								<input type="hidden" value="${condition.aka130 }" name="condition.aka130"/>
							</td>
							<td>医院名称：</td>
							<td style="text-align: left;">	
								<s:property value="condition.akb021"/>
								<input type="hidden" value="${condition.akb020 }" name="condition.akb020"/>
							</td>
							<td>医生名称：</td>
							<td style="text-align: left;">
								<s:property value="condition.ysxm"/>
								<input type="hidden" value="${condition.ysxm }" name="condition.ysxm"/>
							</td>
							<td>外配处方医院：</td>
							<td style="text-align: left;">
								<s:property value="condition.wpddjg_id"/>
								<input type="hidden" value="${condition.wpddjg_id }" name="condition.wpddjg_id"/>
							</td>
						</tr>
							<td>外配处方医生：</td>
							<td style="text-align: left;">
								<s:property value="condition.wpysxm"/>
								<input type="hidden" value="${condition.wpysxm }" name="condition.wpysxm"/>
							</td>
							<td>总费用：</td>
							<td style="text-align: left;">
								<s:property value="condition.zfy"/>
							</td>
							<td style="text-align: right;" colspan="4">
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
							</td>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
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
								<td colspan="10">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="10">
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
				<div style="text-align: center;">
					<input type="button" value="关闭" onclick="javascript:window.close();"/>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		
		//导出Excel 
		function exportExcel(){
			form.action = "zyyp_exportExcelSingleCost.action?condition.divId=3&condition.aaz217="+'<s:property value="condition.aaz217"/>';
			form.target = '_self';
			form.submit();
		}
	</script>
</body>
</html>
