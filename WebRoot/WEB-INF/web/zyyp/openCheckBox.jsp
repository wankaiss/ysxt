<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Map aka130Map = (Map)request.getAttribute("aka130Map");
	request.setAttribute("aka130Map", aka130Map); 
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>就诊类型选择</title>
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
	<form action="zyyp_openCheckBox.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">就诊类型</td>
						</tr>
						<%
							int i=0;
						%>
						<s:iterator value="#request.aka130Map" id="skey">
						<%if(i%4==0){ %>
						<tr>
						<%} %>
							<td style="text-align: left;">
								<input type="checkbox" tempValue="<s:property value="#skey.value"/>" name="checkFilelist" 
									id="<s:property value="#skey.key"/>" tempCode="<s:property value="#skey.key"/>"/>
								<s:property value="#skey.value"/>
							</td>
						<%if(i%4==3){ %>
						</tr>
						<%} %>
						<%i++; %>
						</s:iterator>
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
		var tempAka130 = window.dialogArguments.tempAka130;
		check();
		function check(){
			if(tempAka130!=""){
				var kk = tempAka130.split(",");
				for(var i = 0;i<kk.length;i++){
					document.getElementById(kk[i]).setAttribute("checked","checked");
				}
			}
		}
		//选中数据带回父页面
		function chooseSelect(){
			var code = "";
			var name = "";
			$("input[name='checkFilelist']:checkbox:checked").each(function(){
				var tempCode = $(this).attr("tempCode");
				var tempValue = $(this).attr("tempValue");
				code += tempCode + ",";
				name += tempValue + ",";
		    });
			if(code != ""){
				code = code.substring(0,code.length-1);
				name = name.substring(0,name.length-1);
			}else{
				alert("请先选择就诊类型在确定。");
				return false;
			}
			window.returnValue=code+"|"+name;  
			window.close();
		}
	</script>
</body>
</html>