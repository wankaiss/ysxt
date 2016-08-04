<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店与区县对应关系维护</title>
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
	<form action="hztj_ydHosEdit.action" method="post" name="form" id="chooseForm" target="_self">
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
							<td style="text-align: right;">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" id="toadd" title="新增" onclick="toAdd();" value="新增" style="width: 60px;"/>
								<input type="button" id="toupdate" title="修改" onclick="toupdate();" value="修改" style="width: 60px;"/>
								<input type="button" id="toinsert" title="保存" onclick="toinsert();" value="保存" style="width: 60px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									新增日期
								</th>
								<th>
									药店代码
								</th>
								<th>
									药店名称
								</th>
								<th>
									所属区县
								</th>
								<th>
									暂停标志
								</th>
								<th>
									有效标志
								</th>
								 <%--<th>操作</th>
							--%></tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
						
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.STARTTIME }
									</td>
									<td>
										${cons.YDIDS }
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${aaa027Map[cons.ZIPCODE] }
									</td>
									<td>
										
									</td>
									<td>
										${cons.VALID_FLAG }
									</td>
									<%--<td>
										<a href="#">修改</a>
									</td>
								--%></tr>
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
					</table>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//查询
		function query(){
			showBlock();
			checkDate();
			$("#start").val(0);
			form.action="hztj_ydHosEdit.action";
			form.target='_self';
			form.submit();
		}
		
		function toAdd(){
			
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
	</script>
</body>
</html>
