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
		<script type="text/javascript" src="${path}js/js.js"></script>
		<script type="text/javascript" src="${path}js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${path}js/jquery.util.js"></script>
		<script type="text/javascript" src="${path}js/jquery.blockUI.js"></script>
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
						<input type="hidden" id="sjkjReturn" value="${condition.byMonth }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:elseif>
					<s:elseif test="condition.type!='' and condition.type==4">
						<input type="hidden" id="sjkjReturn" value="${condition.byZrYear }"/>
						<input type="hidden" id="sjkjTypeReturn" value="${condition.type }"/>
					</s:elseif>
					<s:elseif test="condition.type!='' and condition.type==1">
						<input type="hidden" id="sjkjReturn" value="${condition.byYbYear }"/>
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
										<input id="btnmonth" type="button"  onclick="changeClass();"  value="月度" class="btnGray" />
										<input id="btnseason" type="button" onclick="changeClass();"  value="季度" class="btnGray" />
										<input id="btnzryear" type="button" value="自然年度" onclick="changeClass();" class="btnGray"/>
										<input id="btnybyear" type="hidden" value="医保年度" onclick="changeClass();" class="btnGray"/>
										<em class="pL20">
										    <input type="text" name="startDate" id="startDate" class="iconDate" onchange="select_View_Date();"  style="width:128px;"/> 
											<select id="dateStart_jd" style="display:none;width:128px;height:25px;margin-top:6px;" onchange="selectDateOnChange();"></select>
										</em>
										<em style="font-size: 16;padding-left: 20px;">区县：</em>
										<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
										<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
											listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
										</s:select>&nbsp;&nbsp;&nbsp;&nbsp;
										<em style="font-size: 16;padding-left: 20px;">排名选择：</em>
										<select id="orderColumn" name="condition.orderColumn">
											<option value="zfy" <s:if test="'zfy'==condition.orderColumn"> selected="selected"</s:if>>总费用</option>
											<option value="fcfyfy" <s:if test="'fcfyfy'==condition.orderColumn"> selected="selected"</s:if>>非处方购药</option>
											<option value="cfyfy" <s:if test="'cfyfy'==condition.orderColumn"> selected="selected"</s:if>>处方购药</option>
										</select>&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="gray_k">
									<span class="fL" style="padding-left:0px;">
										<em style="font-size: 16;padding-left: 0px;">排名数量：</em>
										<input type="text" id="pmsl" name="condition.pmsl" value="${condition.pmsl }"
											onkeyup="this.value=this.value.replace(/\D/g,'')"  
											onafterpaste="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;
										<select id="px" name="condition.px">
											<option value="desc" <s:if test="'desc'==condition.px"> selected="selected"</s:if>>降序</option>
											<option value="asc" <s:if test="'asc'==condition.px"> selected="selected"</s:if>>升序</option>
										</select>
										<em style="font-size: 16;padding-left: 20px;">人员类别：</em>
										<input type="hidden" value="${condition.akc021}" id="tempAkc021"/>
										<s:select id="akc021" name="condition.akc021" list="#request.akc021Map" listKey="key" listValue="value" 
											listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:120px;">  
										</s:select> 
										<%--<em style="font-size: 16;padding-left: 20px;">特病标至：</em>
										<select id="tbbz" name="condition.tbbz"></select>
										--%><%--<input type="hidden" value="${condition.akc021}" id="tempAkc021"/>
										<s:select id="akc021" name="condition.akc021" list="#request.akc021Map" listKey="key" listValue="value" 
											listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
										</s:select> &nbsp;&nbsp;&nbsp;&nbsp;
										--%>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
										<input type="button" title="导出Excel" onclick="exportExcel();" value="导出Excel" style="width: 80px;"/>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" style="width:1750px;" border="0" cellspacing="0" cellpadding="0" id="trId">
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
								<th style="width: 150px;">
									人员类别
								</th>
								<th style="width: 200px;">
									特病名称
								</th>
								<th style="width: 100px;">
									非处方药费用
								</th>
								<th style="width: 100px;">
									处方药费用
								</th>
								<th  style="width: 240px;">
									费用最高药店名称
								</th>
								<th style="width: 140px;">
									费用最高药店代码
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
										<s:property value="condition.yearMonth"/>
									</td>
									<td>
										${cons.YBH }
									</td>
									<td>
										${cons.XM }
									</td>
									<td>
										${aaa027Map[cons.QXDM] }
									</td>
									<td>
										${cons.ZFY }
									</td>
									<td>
										${rylbMap[cons.RYLB] }
									</td>
									<td>
										${cons.TBMC }
									</td>
									<td>
										${cons.FCFYFY }
									</td>
									<td>
										${cons.CFYFY }
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${cons.JGID }
									</td>
									<td>
										${aaa027Map[cons.SSQX] }
									</td>
									<td>
										${cons.PM }
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
				init_system_date_overload('2');
				$("#btnmonth").attr("class","btnBlue");
			}
			
			var value =$("#sjkjReturn").val();
			if($("#sjkjTypeReturn").val()==3){
				$("#btnseason").attr("class","btnBlue");
				var year = value.substring(0,4);
				var jd = value.substring(4,6);
				init_system_date_overload('3');
				$("#dateStart_jd option[value='"+year+"/"+jd+"']").attr('selected',true);
				$("#sjkj").attr("value",date.startDate);
				
			}else if($("#sjkjTypeReturn").val()==2){
				init_system_date_overload('2');
				$("#btnmonth").attr("class","btnBlue");
				var year = value.substring(0,4);
				var month = value.substring(4,6);
				$("#startDate").val(year+"年"+month+"月");
				$("#sjkj").attr("value",date.startDate);
			}else if($("#sjkjTypeReturn").val()==4){
				init_system_date_overload('4');
				$("#btnzryear").attr("class","btnBlue");
				var year = value.substring(0,4);
				$("#startDate").val(year+"年");
				$("#sjkj").attr("value",date.startDate);
			}else if($("#sjkjTypeReturn").val()==1){
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
		
		//导出Excel
		function exportExcel(){
			form.action = "hztj_exportExcel.action?condition.divId=2";
			form.target='_self';
			form.submit();
		}
	</script>
</body>
</html>
