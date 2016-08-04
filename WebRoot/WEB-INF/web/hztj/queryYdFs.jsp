<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>药店历史分数查询</title>
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
	<form action="hztj_queryYdFs.action" method="post" name="form" id="chooseForm" target="_self">
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
										<%--<input id="btnyear" type="button" value="年度"  onclick="WdatePickerSjLoad('4');changeClass();" class="btnGray"/>--%>
										<input id="btnmonth" type="button"  onclick="changeClass();"  value="月度" class="btnGray" />
										<input id="btnseason" type="button" onclick="changeClass();"  value="季度" class="btnGray" />
										<em class="pL20">
										    <input type="text" name="startDate" id="startDate" class="iconDate" onchange="select_View_Date();"  style="width:128px;"/> 
											<select id="dateStart_jd" style="display:none;width:128px;height:25px;margin-top:6px;" onchange="selectDateOnChange();"></select>
										</em>
										<em style="font-size: 16;padding-left: 20px;">区县：</em>
										<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
										<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
											listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
										</s:select>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th style="width: 100px;">
									药店代码
								</th>
								<th style="width: 200px;">
									药店名称
								</th>
								<th style="width: 100px;">
									所属区县
								</th>
								<th style="width: 100px;">
									分数
								</th>
								<th style="width: 150px;">
									操作
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td>
										${cons.AKB020 }
									</td>
									<td>
										${cons.AKB021 }
									</td>
									<td>
										${aaa027Map[cons.AAA027] }
									</td>
									<td>
										${cons.TJZ }
									</td>
									<td>
										<a href="#" onclick="queryHistoryStatistics('<s:property value="#cons.AAA027"/>','<s:property value="#cons.LBID"/>');">查看参数</a>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="5">
									查无数据
								</td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="5">
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
			}
			changeClass();
		});
		
		//选中项变色
		function changeClass(){
			$('input#btnseason').bind('click',function(){
				if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnyear').hasClass('btnBlue')){
						$('#btnyear').removeClass('btnBlue').addClass('btnGray');
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
					if($('#btnyear').hasClass('btnBlue')){
						$('#btnyear').removeClass('btnBlue').addClass('btnGray');
					}
					if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
					WdatePickerSjLoad('2');
				}
			});
		}
		//查询
		function query(){
			$("#start").val(0);
			form.action = "hztj_queryYdFs.action";
			form.target='_self';
			form.submit();
		}
		
		
		function queryHistoryStatistics(aaa027,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_queryHistoryStatistics.action?condition.aaa027='+aaa027+"&condition.gzbz="+gzbz,null,wndFeatures);
		}
	</script>
</body>
</html>
