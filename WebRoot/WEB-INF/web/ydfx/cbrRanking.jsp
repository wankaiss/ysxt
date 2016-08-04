<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>参保人购药排名</title>
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
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="hztj_cbrRanking.action" method="post" name="form" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<input type="hidden" id="sjkj" name="condition.byYear" value=""/>
				<input type="hidden" id="sjkj_type" name="condition.type" value="4"/>
				<s:if test="null!=condition">
					<s:if test="condition.type!='' and condition.type==3">
						<input type="hidden" id="sjkjReturn" value="${condition.byQuarter }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:if>
					<s:elseif test="condition.type!='' and condition.type==2">
						<input type="hidden" id="sjkjReturn" value="${condition.startDate }"/>
						<input type="hidden" id="endReturn" value="${condition.endDate }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:elseif>
					<s:elseif test="condition.type!='' and condition.type==4">
						<input type="hidden" id="sjkjReturn" value="${condition.startDate }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:elseif>
					<s:elseif test="condition.type!='' and condition.type==1">
						<input type="hidden" id="sjkjReturn" value="${condition.startDate }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:elseif>
				</s:if>
				<s:else>
					<input type="hidden" id="sjkjReturn" value=""/>
					<input type="hidden" id="sjkjTypeReturn" value=""/>
				</s:else>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div class="gray_k">
									<span class="fL" style="padding-left:0px;">
										<input id="btnmonth" type="button"  value="月度"     onclick="changeClass();" class="btnGray" />
										<input id="btnseason" type="button" value="季度"     onclick="changeClass();" class="btnGray" />
										<input id="btnzryear" type="button" value="自然年度" onclick="changeClass();" class="btnGray"/>
										<input id="btnybyear" type="button" value="医保年度" onclick="changeClass();" class="btnGray"/>
										<select id="dateStart_jd" style="display:none;width:128px;height:25px;margin-top:6px;" onchange="selectDateOnChange();"></select>
										<input type="text" name="condition.startDate" id="startDate" class="iconDate" style="width:128px;"/> 
										<em id ="zhi">至</em>
										<input type="text" name="condition.endDate" id="endDate" class="iconDate" style="width:128px;"/> 
									   
										<em style="font-size: 16;padding-left: 20px;">区县：</em>
										<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
										<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
											listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
										</s:select>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
										<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 80px;"/>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" style="width:100%;" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 180px;">
									时间
								</th>
								<th style="width: 100px;">
									医保号
								</th>
								<th style="width: 100px;">
									姓名
								</th>
								<th style="width: 100px;">
									区县
								</th>
								<th style="width: 100px;">
									总费用
								</th>
								<th  style="width: 240px;">
									费用最高药店名称
								</th>
								<th style="width: 140px;">
									费用最高药店所属区县
								</th>
								<th style="width: 100px;">
									总费用排名
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										<s:property value="#request.UIDate"/>
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${aaa027Map[cons.CBXZQH] }
									</td>
									<td>
										${cons.FYZE }
									</td>
									<td>
										${cons.MC }
									</td>
									<td>
										${aaa027Map[cons.XZQH] }
									</td>
									<td>
										${cons.rankLevel }
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="13">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="13">
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
		//初始化页面向输入框中赋值
		$(document).ready(function(){
			$("#aaa027").val($("#tempAaa027").val());
			if($("#sjkjReturn").val()==""){
				$("#sjkjTypeReturn").val(2);
				$("#btnmonth").attr("class","btnBlue");
				init_system_date_overload('2');
			}
			if ($("#sjkjTypeReturn").val() != 2) {
				document.getElementById("endDate").style.display= "none"; 
			    document.getElementById("zhi").style.display= "none"; 
			}
			var value =$("#sjkjReturn").val();
			if($("#sjkjTypeReturn").val()==3){//季度 
				$("#btnseason").attr("class","btnBlue");
				var year = value.substring(0,4);
				var jd = value.substring(4,6);
				init_system_date_overload('3');
				$("#dateStart_jd option[value='"+year+"/"+jd+"']").attr('selected',true);
				$("#sjkj").attr("value",date.startDate);
				
			}else if($("#sjkjTypeReturn").val()==2){//按月 
				var value1 =$("#endReturn").val();
				init_system_date_overload('2');
				$("#btnmonth").attr("class","btnBlue");
				var startYear = value.substring(0,4);
				var startMonth = value.substring(5,7);
				var endYear = value1.substring(0,4);
				var endMonth = value1.substring(5,7);
				$("#startDate").val(startYear+"年"+startMonth+"月");
				$("#endDate").val(endYear+"年"+endMonth+"月");
				$("#sjkj").attr("value",date.startDate);
			}else if($("#sjkjTypeReturn").val()==4){
				init_system_date_overload('4');
				$("#btnzryear").attr("class","btnBlue");
				var year = value.substring(0,4);
				$("#startDate").val(year+"年");
				$("#sjkj").attr("value",date.startDate);
			}else if($("#sjkjTypeReturn").val()==1){//医保年度 
				init_system_date_overload('1');
				$("#btnybyear").attr("class","btnBlue");
				var year = value.substring(0,4);
				$("#startDate").val(year+"年");
				$("#sjkj").attr("value",date.startDate);
			}
			changeClass();
		});
		
		//选中项变色
		function changeClass(){
			$('input#btnseason').bind('click',function(){
				if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnzryear').hasClass('btnBlue')){
						$('#btnzryear').removeClass('btnBlue').addClass('btnGray');
					 }
					if($('#btnybyear').hasClass('btnBlue')){
						$('#btnybyear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnmonth').hasClass('btnBlue')){
						$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
						
					}
						document.getElementById("endDate").style.display= "none"; 
					    document.getElementById("zhi").style.display= "none"; 
					WdatePickerSjLoad('3');
				}
			});
			
			$('input#btnmonth').bind('click',function(){
				if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnzryear').hasClass('btnBlue')){
						$('#btnzryear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnybyear').hasClass('btnBlue')){
						$('#btnybyear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
					document.getElementById("endDate").style.display= ""; 
					document.getElementById("zhi").style.display= ""; 
					WdatePickerSjLoad('2');
				}
			});
			
			$('input#btnzryear').bind('click',function(){
				if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnmonth').hasClass('btnBlue')){
						$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnybyear').hasClass('btnBlue')){
						$('#btnybyear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
					document.getElementById("endDate").style.display= "none"; 
				    document.getElementById("zhi").style.display= "none"; 
					WdatePickerSjLoad('4');
				}
			});
			
			$('input#btnybyear').bind('click',function(){
				if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnzryear').hasClass('btnBlue')){
						$('#btnzryear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnmonth').hasClass('btnBlue')){
						$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
					document.getElementById("endDate").style.display= "none"; 
					document.getElementById("zhi").style.display= "none"; 
					WdatePickerSjLoad('1');
				}
			});
		}
		
		//查询
		function query(){
			showBlock();
			$("#start").val(0);
			form.action = "hztj_cbrRanking.action";
			form.target='_self';
			form.submit();
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
			if ($("#sjkjTypeReturn").val == 2) {
				if(start>end){
					alert("开始时间不能大于结束时间。");
					return false;
				}
			}
		}
		
		//导出Excel
		function exportExcel(){
			alert("正在导出中......请稍后!");
			form.action = "ydfx_exportExcelCbr.action";
			form.target='_self';
			form.submit();
		}
	</script>
</body>
</html>
