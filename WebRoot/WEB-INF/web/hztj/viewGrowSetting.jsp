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
		<title>查看增长率设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<base target="_self"></base>
<body>
	<form action="hztj_viewGrowSetting.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left: 10px;width: 100px;">当前区县：</td>
							<td style="text-align: left;">
								<s:select id="aaa027" name="condition.tcdm" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" disabled="true">  
								</s:select>
								<input type="hidden" value="${condition.qzdm}" id="qzdm" name="condition.qzdm"/>
								<input type="hidden" value="${condition.gzbz}" id="gzbz" name="condition.gzbz"/>
								<input type="hidden" value="${condition.tcdm}" id="tcdm"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									参数名称
								</th>
								<th>
									权重值
								</th>
								<s:if test="null != pageSet.data && pageSet.data.size()>0">
									<th>
										操作
									</th>
								</s:if>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">
										${cons.QZM }
									</td>
									<td style="text-align: center;">
										${cons.QZZ }%
									</td>
									<td style="text-align: center;">
										<a href="#" onclick="scoreSetting('<s:property value="#index.index+1"/>','<s:property value="#cons.QZDM"/>');">查看分值设置</a>&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="3" style="text-align: center;">参数权重未设置</td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$("#aaa027").val($("#tcdm").val());
		//分数设置
		function scoreSetting(idx,qzdm){
			var tcdm = $("#tcdm").val();
			var gzbz = $("#gzbz").val();
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.showModalDialog('hztj_viewGrowSetting.action?condition.qzdm='+qzdm+"&condition.tcdm="+tcdm+"&condition.gzbz="+gzbz+"&condition.type=4"+"&condition.qzjb=3",tcdm,wndFeatures);
		}
		
	</script>
</body>
</html>