<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>医师查询</title>
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
<body >
	<form action="zyyp_docSelect.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>医师编号：</td>
							<td>
								<input type="hidden" name="condition.aaa027" id="aaa027" value="${condition.aaa027}"/>
								<input type="text" name="condition.aaz263" id="aaz263" value="${condition.aaz263}"/>
								<input type="hidden" name="condition.hosIds" id="hosIds" value="${condition.hosIds}"/>
								<input type="hidden" name="condition.sign" id="sign" value="${condition.sign}"/>
							</td>
							<td>医师姓名：</td>
							<td><input type="text" name="condition.aac003" id="aac003" value="${condition.aac003}"/></td>
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
								<th><input type="checkbox"  value="checkFilelist" onclick="checkedByName(this.value)"/></th>
								<th>医护人员编号</th>
								<th>姓名</th>
								<th>医师执业证编码</th>
								<th>医师资格证编码</th>
								<th>医疗机构编码</th>
								<th>医疗机构名称</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										<input type="checkbox" name="checkFilelist" tempCode="${cons.YSDM }" tempValue="${cons.XM }" tempAkb020="${cons.HOSP_ID }" tempZgzs="${cons.ZGZS }"/>
									</td>
									<td>
										${cons.YSDM }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.ZYZS }
									</td>
									<td>
										${cons.ZGZS }
									</td>
									<td>
										${cons.HOSP_ID }
									</td>
									<td>
										${cons.NAME }
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
		
		//选中数据带回父页面
		function chooseSelect(){
			var code = "";
			var name = "";
			var akb020 = "";
			var zgzs = "";
			$("input[name='checkFilelist']:checkbox:checked").each(function(){
				var tempCode = $(this).attr("tempCode");
				var tempValue = $(this).attr("tempValue");
				var tempAkb020 = $(this).attr("tempAkb020");
				var tempZgzs = $(this).attr("tempZgzs");
				code += tempCode + ":" + tempAkb020 + ",";
				name += tempValue + ",";
				akb020 += tempAkb020 + ",";
				zgzs += tempZgzs + ",";
		    });
			if(code != ""){
				code = code.substring(0,code.length-1);
				name = name.substring(0,name.length-1);
				akb020 = akb020.substring(0,akb020.length-1);
				zgzs = zgzs.substring(0,zgzs.length-1);
			}
			window.opener.document.getElementById("docId").value = code;
			window.opener.document.getElementById("docName").value = name;
			window.opener.document.getElementById("docName").title = name;
			window.opener.document.getElementById("hosIds").value = akb020;
			var sign = $("#sign").val();
			if(sign==1){
				window.opener.document.getElementById("zgzs").value = zgzs;
			}
			window.close();
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
	</script>
</body>
</html>