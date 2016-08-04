<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>医疗机构查询</title>
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
	</head>
<body>
	<form action="ydfx_docWpcfDetail.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>统计周期：</td>
							<td><input type="text" name="condition.akb020" id="UIDate" value="${condition.startDate}" disabled="disabled"/></td>
							<td>医院名称：</td>
							<td><input type="text" name="condition.akb021" id="akb021" value="${condition.ydNames}" disabled="disabled"/></td>
							<td>医生姓名：</td>
							<td><input type="text" name="condition.docName" id="docName" value="${condition.akb021}" disabled="disabled"/></td>
							<td>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<thead>
							<tr>
								<th>药店代码</th>
								<th>药店名称</th>
								<th>所属区县</th>
								<th>外配处方次数</th>
								<th>外配处方费用</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.DDJG_ID }
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${aaa027Map[cons.AAA027] }
									</td>
									<td>
										${cons.WPCFCS }
									</td>
									<td>
										${cons.WPCFFY }
									</td>
									
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="6">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="6">
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
					<input type="button" value="关闭" onclick="closeWindow()"/>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		
		function closeWindow(){
			window.opener.location.reload();
			window.close();
		}
	</script>
</body>
</html>