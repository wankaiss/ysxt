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
		<style >
			body {
				height: 100%;
				background: #fff;
				overflow-x: hidden;
			 	overflow-y:scroll;
			}
		</style>
	</head>
<body >
	<form action="zyyp_ydHosSelect.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>医疗机构编号：</td>
							<td><input type="text" name="condition.akb020" id="akb020" value="${condition.akb020}"/></td>
							<td>医疗机构名称：</td>
							<td><input type="text" name="condition.akb021" id="akb021" value="${condition.akb021}"/></td>
							<td>
								<input type="hidden" name="condition.aaa027" id="aaa027" value="${condition.aaa027}"/>
								<input type="hidden" name="condition.akb022" id="akb022" value="${condition.akb022}"/>
								<input type="button" value="查询" onclick="query();"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<thead>
							<tr>
								<th><input type="checkbox"  value="checkFilelist" onclick="checkedByName(this.value)"/></th>
								<th>医疗机构名称</th>
								<th>医疗机构编号</th>
								<th>医疗机构类别</th>
								<th>医疗机构等级</th>
								<th>地址</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										<input type="checkbox" name="checkFilelist" tempCode="${cons.AKB020 }" tempValue="${cons.AKB021 }"/>
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${cons.AKB020 }
									</td>
									<td>
										<s:if test="#cons.AKB022==1">
											医疗机构
										</s:if>
										<s:elseif test="#cons.AKB022==2">
											零售药店
										</s:elseif>
									</td>
									<td>
										${aka101Map[cons.AKA101] }
									</td>
									<td>
										${cons.ADDR }
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
					<input type="button" value="确定" onclick="chooseSelect();"/>
					<input type="button" value="关闭" onclick="javascript:window.close();"/>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//查询
		function query(){
			showBlock();
			$("#start").val(0);
			form.target = '_self';
			form.submit();
		}
		
		//全选/反选
		var isCheckedAll = true;
		function checkedByName(name){
			if(isCheckedAll){
				checkedCtrl(name,true);
				isCheckedAll = false;
			}
			else{
				checkedCtrl(name,false);
				isCheckedAll = true;
			}
		}
		function checkedCtrl(name, isNeedChecked){
			if(isNeedChecked){
				$("input[name='"+name+"']").attr('checked','true');
			}
			else{
				$("input[name='"+name+"']").removeAttr('checked');
			}
		}
		
		//选中数据带回父页面
		function chooseSelect(){
			var code = "";
			var name = "";
			var idx = $("#akb022").val();
			$("input[name='checkFilelist']:checkbox:checked").each(function(){
				var tempCode = $(this).attr("tempCode");
				var tempValue = $(this).attr("tempValue");
				code += tempCode + ",";
				name += tempValue + ",";
		    });
			if(code != ""){
				code = code.substring(0,code.length-1);
				name = name.substring(0,name.length-1);
			}
			if(idx == '2'){
				window.opener.document.getElementById("ydIds").value = code;
				window.opener.document.getElementById("ydNames").value = name;
				window.opener.document.getElementById("ydNames").title = name;
			}else{
				window.opener.document.getElementById("hosIds").value = code;
				window.opener.document.getElementById("hosNames").value = name;
				window.opener.document.getElementById("hosNames").title = name;
			}
			window.close();
		}
	</script>
</body>
</html>