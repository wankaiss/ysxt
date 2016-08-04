<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>就诊信息查询</title>
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
	<form action="cbr_queryJzxx.action" method="post" name="form" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<input type="hidden" id="aac003" value="${condition.aac003 }"/>
								<input type="hidden" id="aac004" value="${condition.aac004 }"/>
								<input type="hidden" id="nl" value="${condition.nl }"/>
								<input type="hidden" id="aac002" value="${condition.aac002 }"/>
								<input type="hidden" id="aac001" value="${condition.aac001 }"/>
								姓名：<s:property value="condition.aac003"/>
							</td>
							<td>
								性别：<s:if test="condition.aac004=='1'">
									                  男	  
									  </s:if>
									  <s:else>
									  	       女
									  </s:else>
							</td>
							<td>
								年龄：<s:property value="condition.nl"/>
							</td>
							<td>
								身份证号：<s:property value="condition.aac002"/>
							</td>
							<td>
								医保号：<s:property value="condition.aac001"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									医疗服务机构
								</th>
								<th>
									医师
								</th>
								<th>
									医疗类别
								</th>
								<th>
									诊断
								</th>
								<th>
									就诊时间
								</th>
								<th>
									入院日期
								</th>
								<th>
									出院日期
								</th>
								<th>
									总费用
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
										${cons.AKB021 }
									</td>
									<td>
										${cons.YSXM }
									</td>
									<td>
										${aka130Map[cons.AKA130] }
									</td>
									<td>
										${cons.AKA120 }
									</td>
									<td>
										<s:date name="#cons.AKC194" format="yyyy-MM-dd"/>
									</td>
									<td>
										${cons.AAE030 }
									</td>
									<td>
										${cons.AAE031 }
									</td>
									<td>
										${cons.ZFY }
									</td>
									<td>
										<a href="#" onclick="queryFymx('<s:property value="#cons.AAZ217"/>','<s:date name="#cons.AKC194" format="yyyy-MM-dd"/>','<s:property value="#cons.AKA130"/>');">费用明细</a>
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
		function queryFymx(aaz217,akc194,aka130){
			var aac003 = $("#aac003").val();
			var nl = $("#nl").val();
			var aac004 = $("#aac004").val();
			var aac002 = $("#aac002").val();
			var aac001 = $("#aac001").val();
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('cbr_queryFymx.action?condition.aaz217='+aaz217+"&condition.aac003="+encodeURI(encodeURI(aac003))
			       +"&condition.nl="+nl+"&condition.aac004="+aac004+"&condition.aac002="+aac002+"&condition.aac001="+aac001
			       +"&condition.akc194="+akc194+"&condition.aka130="+aka130,"new",wndFeatures);
		}
		
	</script>
</body>
</html>
