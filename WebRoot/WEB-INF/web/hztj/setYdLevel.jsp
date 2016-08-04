<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
	</head>
<body>
	<form action="hztj_setYdLevel.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left: 10px;width: 100px;">区县选择：</td>
							<td style="text-align: left;width: 200px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select>
							</td>
							<td>药店编号：</td>
							<td><input type="text" name="condition.akb020" id="akb020" value="${condition.akb020}"/></td>
							<td>药店名称：</td>
							<td><input type="text" name="condition.akb021" id="akb021" value="${condition.akb021}"/></td>
							<td>
								<input type="button" value="查询" onclick="query();"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<thead>
							<tr>
								<th>药店名称</th>
								<th>药店编号</th>
								<th>所属统筹区</th>
								<th>药店等级</th>
								<th>操作</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${cons.AKB020 }
									</td>
									<td>
										${aaa027Map[cons.AAA027] }
									</td>
									<td>
										${cons.YDDJ }
									</td>
									<td>
										<a href="#" onclick="saveSetYdLevel('<s:property value="#cons.AKB020"/>','<s:property value="#cons.ID"/>');">药店等级设置</a>
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
				<div style="text-align: center;">
					<input type="button" value="关闭" onclick="javascript:window.close();"/>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//查询
		function query(){
			$("#start").val(0);
			form.target = '_self';
			form.submit();
		}
		
		//打开设置页面
		function saveSetYdLevel(akb020,id){
			var wndFeatures = "dialogWidth=400px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_saveSetYdLevel.action?condition.id='+id+"&condition.akb020="+akb020,null,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
	</script>
</body>
</html>