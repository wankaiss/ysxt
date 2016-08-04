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
		<title>个人分析报告</title>
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
	<form action="cbr_queryPersonReport.action" method="post" name="form" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="text-align: right;">
								<input type="hidden" name="condition.aaa027" id="aaa027" value="${condition.aaa027}"/>
								<input type="hidden" name="condition.aka130" id="aka130" value="${condition.aka130}"/>
								<input type="hidden" name="condition.colorList" id="colorList"/>
								<input type="hidden" name="condition.sign" id="sign"/>
								<input type="hidden" name="condition.aac002" id="aac002" value="${condition.aac002}"/>
								<s:if test="condition.hhmdbz==1">
									<input type="button" title="加入黑名单" value="加入黑名单" onclick="beathAddList('black');" style="width: 140px;"/>
								</s:if>
								<s:elseif test="condition.hhmdbz==2">
								</s:elseif>
								<s:else>
									<input type="button" title="加入红名单" value="加入红名单" onclick="beathAddList('red');" style="width: 140px;"/>
									<input type="button" title="加入黑名单" value="加入黑名单" onclick="beathAddList('black');" style="width: 140px;"/>
								</s:else>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									姓名
								</th>
								<th>
									性别
								</th>
								<th>
									年龄
								</th>
								<th>
									身份证号
								</th>
								<th>
									医保号
								</th>
								<th>
									特殊病名称
								</th>
								<th>
									历年体检结果
								</th>
								<th>
									单据编号
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.AAC003 }
									</td>
									<td>
										<s:if test="#cons.AAC004==1">
											男
										</s:if>
										<s:else>
											女
										</s:else>
									</td>
									<td>
										${cons.NL }
									</td>
									<td>
										${cons.AAC002 }
									</td>
									<td>
										${cons.AAC001 }
									</td>
									<td>
										
									</td>
									<td>
										
									</td>
									<td>
										${cons.AAZ217 }
									</td>
									<td>
										<a href="#" onclick="queryJzxx('<s:property value="#cons.AAZ217"/>','<s:property value="#cons.AAC003"/>','<s:property value="#cons.AAC004"/>','<s:property value="#cons.NL"/>','<s:property value="#cons.AAC002"/>','<s:property value="#cons.AAC001"/>');">就诊信息</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="8">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="8">
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
		//批量加入红黑名单
		function beathAddList(c){
			var aac002 = document.getElementById("aac002").value;
			var params = "&condition.colorList="+c+"&condition.aac002="+aac002+"&condition.sign='some'";
			var wndFeatures = "dialogWidth=300px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('cbr_beathAddList.action?params='+params,null,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		//查询
		function query(){
			form.action="cbr_queryPerson.action";
			form.target='_self';
			form.submit();
		}
		
		//就诊信息查询
		function queryJzxx(aaz217,aac003,aac004,nl,aac002,aac001){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('cbr_queryJzxx.action?condition.aaz217='+aaz217+"&condition.aac003="+encodeURI(encodeURI(aac003))
			       +"&condition.nl="+nl+"&condition.aac004="+aac004+"&condition.aac002="+aac002+"&condition.aac001="+aac001,null,wndFeatures);
		}
		
	</script>
</body>
</html>
