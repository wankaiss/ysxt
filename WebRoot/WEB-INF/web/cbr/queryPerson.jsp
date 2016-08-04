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
		<title>待查人员信息</title>
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
	<form action="cbr_queryPerson.action" method="post" name="form" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="text-align: right;">
								<input type="hidden" name="condition.aaa027" id="aaa027" value="${condition.aaa027}"/>
								<input type="hidden" name="condition.aka130" id="aka130" value="${condition.aka130}"/>
								<input type="hidden" id="yearMonth" name="condition.yearMonth" value="${condition.yearMonth}"/>
								<input type="hidden" id="pjfy" name="condition.pjfy" value="${condition.pjfy}"/>
								<input type="hidden" id="pjcs" name="condition.pjcs" value="${condition.pjcs}"/>
								<input type="hidden" id="rc_pjcs" name="condition.rc_pjcs" value="${condition.rc_pjcs }"/>
								<input type="hidden" id="rc_fyze" name="condition.rc_fyze" value="${condition.rc_fyze }"/>
								<input type="hidden" id="rc_pjfy" name="condition.rc_pjfy" value="${condition.rc_pjfy }"/>
								<input type="hidden" id="rc_cszs" name="condition.rc_cszs" value="${condition.rc_cszs }"/>
								<input type="hidden" name="condition.colorList" id="colorList"/>
								<input type="hidden" name="condition.sign" id="sign"/>
								<input type="button" title="人员全选" value="人员全选" id="sfqx" onclick="chooseAll(this);" style="width: 100px;"/>
								<input type="button" title="批量加入红名单" value="批量加入红名单" onclick="beathAddList('red');" style="width: 140px;"/>
								<input type="button" title="批量加入黑名单" value="批量加入黑名单" onclick="beathAddList('black');" style="width: 140px;"/>
								<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 100px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									<input type="checkbox" value="checkFilelist" onclick="checkedByName(this.value);"/>
								</th>
								<th>
									医保号
								</th>
								<th>
									姓名
								</th>
								<th>
									年龄
								</th>
								<th>
									身份证号
								</th>
								<%--<th>
									单位名称
								</th>--%>
								<th>
									加入待查名单日期
								</th>
								<th>
									费用总额
								</th>
								<th>
									就诊次数
								</th>
								<th>
									历年账户支付费用
								</th>
								<th>
									平均就诊费用
								</th>
								<th>
									是否红/黑名单成员
								</th>
								<th>
									红/黑名单备注
								</th>
								<th>
									待遇启动当月发生费用
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
										<input type="checkbox" name="checkFilelist" tempCode="${cons.SFZHM }" tempName="${cons.HHMDBZ }"/>
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${cons.NL }
									</td>
									<td>
										${cons.SFZHM }
									</td>
									<%--<td>
										
									</td>--%>
									<td>
										<s:date name="#cons.XGSJ" format="yyyy-MM-dd"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.ZFY}" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										${cons.JZCS }
									</td>
									<td>
										${cons.LNZHZFFY }
									</td>
									<td>
										<fmt:formatNumber value="${cons.PJJZFY}" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<s:if test="#cons.HHMDBZ==1">
											红名单
										</s:if>
										<s:elseif test="#cons.HHMDBZ==2">
											黑名单
										</s:elseif>
										<s:else>
											未加入红/黑名单
										</s:else>
									</td>
									<td>
										${cons.BZ }
									</td>
									<td>
										${cons.DYQDDYFY }
									</td>
									<td>
										<a href="#" onclick="queryPersonReport('<s:property value="#cons.SFZHM"/>','<s:property value="#cons.HHMDBZ"/>');">分析报告</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="14">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="14">
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
		//是否选择全部人员
		var isChooseAll = false;
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
	
		//获取选中的数据
		var sfzhms = "";//选中参保人的身份证号
		var isRed = false;
		var isBlack = false;
		function check(){
			sfzhms = "";
			$("input[name='checkFilelist']:checkbox:checked").each(function(){
				var tempCode = $(this).attr("tempCode");
				var tempName = $(this).attr("tempName");
				sfzhms += tempCode + ",";
				if(tempName!=""){
					if(tempName==1){
						isRed = true;
					}else if(tempName==2){
						isBlack = true;
					}
				}
			});
			if(sfzhms != ""){
				sfzhms = sfzhms.substring(0,sfzhms.length-1);
			}
		}
		
		//人员全选或取消全选
		function chooseAll(e){
			if(e.value=='人员全选'){
				isChooseAll = true;
				document.getElementById("sfqx").removeAttribute("value");
				document.getElementById("sfqx").setAttribute("value","取消全选");
			}else{
				isChooseAll = false;
				document.getElementById("sfqx").removeAttribute("value");
				document.getElementById("sfqx").setAttribute("value","人员全选");
				sfzhms = "";
				$("input[name='checkFilelist']:checkbox:checked").each(function(){
					//去除选中的勾选项
					$(this).attr("checked",false);
				});
			}
		}
		
		//批量加入红黑名单
		function beathAddList(c){
			var rc_pjfy = $("#rc_pjfy").val();
			var rc_pjcs = $("#rc_pjcs").val();
			var rc_fyze = $("#rc_fyze").val();
			var rc_cszs = $("#rc_cszs").val();
			var yearMonth = $("#yearMonth").val();
			var aaa027 = $("#aaa027").val();
			var aka130 = $("#aka130").val();
			var pjfy = $("#pjfy").val();
			var pjcs = $("#pjcs").val();
			//全选
			if(isChooseAll){
				sign = "all";
			}else{
				//多选
				check();
				if(sfzhms==""){
					alert("请先选择要加入红名单或黑名单的数据。");
					return false;
				}
				if(c=="red"){
					if(isRed||isBlack){
						alert("选中的数据中含有已经是红名单或黑名单的成员，不能重复添加，请重新选择。");
						return false;
					}
				}else if(c=="black"){
					if(isBlack){
						alert("选中的数据中含有黑名单成员，不能重复添加，请重新选择。");
						return false;
					}
				}
				sign = "some";
			}
			var params = "&condition.colorList="+c+"&condition.sign="+sign+"&condition.aka130="+aka130
						+"&condition.aaa027="+aaa027+"&condition.rc_pjfy="+rc_pjfy+"&condition.rc_pjcs="+rc_pjcs
						+"&condition.rc_fyze="+rc_fyze+"&condition.rc_cszs="+rc_cszs+"&condition.yearMonth="+encodeURI(encodeURI(yearMonth))
						+"&condition.pjfy="+pjfy+"&condition.pjcs="+pjcs+"&condition.aac002="+sfzhms;
			var wndFeatures = "dialogWidth=300px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('cbr_beathAddList.action?params='+params,null,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		//导出Excel
		function exportExcel(divId){
			form.action="cbr_exportExcelPerson.action";
			form.target='_self';
			form.submit();
		}
		
		//查询
		function query(){
			showBlock();
			form.action="cbr_queryPerson.action";
			form.target='_self';
			form.submit();
		}
		
		//个人报告
		function queryPersonReport(e,hhmdbz){
			var yearMonth = $("#yearMonth").val();
			var wndFeatures = "dialogWidth=1000px;dialogHeight=600px;center:true;scroll:no;status:no;";
			window.open("cbr_queryPersonReport.action?condition.aac002="+e+"&condition.hhmdbz="+hhmdbz+"&condition.yearMonth="+encodeURI(encodeURI(yearMonth)),"个人报告",wndFeatures);
		}
	</script>
</body>
</html>
