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
		<title>药店综合分析参数维护</title>
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
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
<body>
	<input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
	<form action="hztj_queryYdfx.action" method="post" name="form" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding-left: 10px;width: 100px;">区县选择：</td>
							<td style="text-align: left;width: 200px;">
								<input type="hidden" value="${condition.aaa027}" id="tempAaa027"/>
								<s:select id="aaa027" name="condition.aaa027" list="#request.aaa027Map" listKey="key" listValue="value" 
									listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;">  
								</s:select>
							</td>
							<td style="text-align: right;">
								<input type="button" title="查询" onclick="query();" value="查询" style="width: 60px;"/>
								<input type="button" id="toadd" title="新增" onclick="toAddRow();" value="新增" style="width: 60px;"/>
								<input type="button" id="savepar" title="保存" onclick="saveParameter();" value="保存" style="width: 60px;"/>
								<input type="button" id="weigthUpd" title="权重修改" onclick="changeWeigth();" value="权重修改" style="width: 100px;"/>
								<input type="button" id="expQz" title="导出Excel" onclick="expQzAll();" value="导出Excel" style="width: 100px;"/>
								<input type="button" id="updatepar" title="上一年度药店等级设置" onclick="updateSet();" value="上一年度药店等级设置" style="width: 150px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="100%" border="0" cellspacing="0" cellpadding="0" id="trId">
						<thead>
							<tr>
								<th>
									参数名称
								</th>
								<th>
									权重值
								</th>
								<th>
									参数适用范围
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
										${cons.QZM }
									</td>
									<td>
										<span class="span1">${cons.QZZ }%</span>
										<span class="span2" style="display: none;"><input type="text" value="${cons.QZZ }" name="" maxlength="5"/>%<span>
									</td>
									<td>
										<input type="hidden" value="${cons.TCDM }"/>
										${aaa027Map[cons.TCDM] }
										<input type="hidden" name="gzbz" value="${cons.GZBZ }"/>
									</td>
									<td>
										<s:if test="#cons.QZM == '增长率'">
											<%--<a href="#" onclick="weigthUpd('yd_1','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">权重修改</a>&nbsp;&nbsp;&nbsp;
											--%><a href="#" onclick="growSetting('1','<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">详细设置</a>
										</s:if>
										<s:elseif test="#cons.QZM == '药店费用等级跨越'">
											<%--<a href="#" onclick="weigthUpd('yd_2','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">权重修改</a>&nbsp;&nbsp;&nbsp;
											--%><a href="#" onclick="growSetting('2','<s:property value="#cons.TCDM"/>','<s:property value="#cons.ID"/>','<s:property value="#cons.GZBZ"/>');">详细设置</a>&nbsp;&nbsp;&nbsp;
											<%--<a href="#" onclick="queryHosData();">药店分类数据查询</a>&nbsp;&nbsp;&nbsp;--%>
											<a href="#" onclick="prescriptionSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">处方药非处方药权重设置</a>
										</s:elseif>
										<s:elseif test="#cons.QZM == '单次非处方购药费用占比'">
											<%--<a href="#" onclick="weigthUpd('yd_3','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">权重修改</a>&nbsp;&nbsp;&nbsp;
											--%><a href="#" onclick="growSetting('3','<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">详细设置</a>&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="amountSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">费用额设置</a>&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="oldAndNowSetting('<s:property value="#cons.TCDM"/>','<s:property value="#cons.QZDM"/>','<s:property value="#cons.GZBZ"/>');">当年账户历年账户权重设置</a>
										</s:elseif>
										<s:else>
											<%--<a href="#" onclick="weigthUpd('yd_0','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">权重修改</a>&nbsp;&nbsp;&nbsp;
											--%><a href="#" onclick="deleteWeigth('<s:property value="#cons.ID"/>');">删除</a>&nbsp;&nbsp;&nbsp;
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td>增长率</td>
								<td><input type="text" name="condition.qzz" maxlength="5"/>%</td>
								<td>
									<s:select id="aaa027save" name="condition.tcdm" list="#request.aaa027Map" listKey="key" listValue="value" 
										listTitle="value" headerKey="" headerValue="--请选择--" cssStyle="width:100px;" onchange="changeSelectValue(this);">  
									</s:select> 
								</td>
							</tr>
							<tr>
								<td>药店费用等级跨越</td>
								<td><input type="text" name="condition.qzz" maxlength="5"/>%</td>
								<td><input type="text" readonly="readonly" value="" id="djId"/></td>
							</tr>
							<tr>
								<td>单次非处方购药费用占比</td>
								<td><input type="text" name="condition.qzz" maxlength="5"/>%</td>
								<td><input type="text" readonly="readonly" value="" id="fyId"/></td>
							</tr>
						</s:else>
						<tfoot>
							<tr>
							   <td colspan="4">
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
		var sign = false;
		var bdAaa027 = "";
		$(document).ready(function(){
			$("#aaa027").val($("#tempAaa027").val());
			bdAaa027 = $("#tempAaa027").val();
			if('${pageSet.count}'>0){
				$("#savepar").hide();
				$("#toadd").hide();
				$("#weigthUpd").show();
			}else{
				$("#savepar").show();
				$("#weigthUpd").hide();
				$("#toadd").show();
			}
			sign = false;
		});
		//查询
		function query(){
			$("#start").val(0);
			form.action="hztj_queryYdfx.action";
			form.target='_self';
			form.submit();
		}
		var i = 0;
		//新增参数
		function toAddRow(){
			if(bdAaa027 == ""){
				alert("请先选择区县再增加行。");
				return false;
			}
			var html = "<tr id='id_"+i+"'>";
			html += "<td><input type='text' name='condition.qzm' id='qzm_"+i+"'/></td>";
			html += "<td><input type='text' name='condition.qzz' id='qzz_"+i+"'/>%</td>";
			var count = '${pageSet.count}';
			if(count==0){
				html += '<td><input type="text" id="tcdm_'+i+'" readonly="readonly"/></td>';
			}else{
				var temp1 = '<s:property value="#request.aaa027Map"/>';
				var temp2 = temp1.substring(1,temp1.length-1).split(",");
				var map = {};
				for(var m =0;m<temp2.length;m++){
					map[temp2[m].split('=')[0].trim()] = temp2[m].split('=')[1];
				}
				html += '<td><input type="text" id="tcdm_'+i+'" readonly="readonly" value="'+map[bdAaa027]+'"/></td>';
			}
			html += "<td><a href='#' onclick='removeRow("+i+")'>删除</a>";
			if(count>0){
				html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='saveSingleRow("+i+")'>保存</a>";
			}
			html += "</td></tr>";
			$("#trId").append(html);
			i++;
		}
		//删除新增行
		function removeRow(rowNum){
			$("#id_"+rowNum).remove();
		}
		
		//权重值修改(已弃用)
		function weigthUpd(idx,value,id){
			var json = {idx:idx,value:value,id:id};
			var wndFeatures = "dialogWidth=300px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_weigthUpd.action',json,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		//权重值修改（全体参数）
		function changeWeigth(){
			var tempCode = "";
			var i=0;
			$("input[name='gzbz']").each(function(){
				if(i==0){
					tempCode = $(this).attr("value");
				}
				i++;
		    });
			var wndFeatures = "dialogWidth=500px;dialogHeight=400px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_changeWeigth.action?condition.gzbz='+tempCode+"&condition.qzjb=1"+"&condition.tcdm="+bdAaa027,null,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		//详细设置
		function growSetting(idx,tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			if(idx == '1'){
				//增长率设置
				window.open('hztj_growSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
			}else if(idx == '2'){
				//药店费用等级跨越
				window.open('hztj_medLevelSetting.action?condition.tcdm='+tcdm+"&condition.id="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
			}else if(idx == '3'){
				//单次非处方购药费用占比
				//window.showModalDialog('hztj_singleMedPay.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.divId=1",tcdm,wndFeatures);
				window.open('hztj_singleMedPay.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.divId=1"+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
			}
		}
		
		//处方药非处方药权重设置
		function prescriptionSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_prescriptionSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
		}
		
		//药店分类数据查询
		function queryHosData(){
			
		}
		
		//费用额设置
		function amountSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_amountSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
		}
		
		//当年账户历年账户权重设置
		function oldAndNowSetting(tcdm,qzdm,gzbz){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_oldAndNewSetting.action?condition.tcdm='+tcdm+"&condition.qzdm="+qzdm+"&condition.gzbz="+gzbz,tcdm,wndFeatures);
		}
		
		//
		function changeSelectValue(e){
			sign = true;
			var temp1 = '<s:property value="#request.aaa027Map"/>';
			var temp2 = temp1.substring(1,temp1.length-1).split(",");
			var map = {};
			for(var m =0;m<temp2.length;m++){
				map[temp2[m].split('=')[0].trim()] = temp2[m].split('=')[1];
			}
			$("#djId").val(map[e.value]);
			$("#fyId").val(map[e.value]);
			for(var j=0;j<i;j++){
				document.getElementById("tcdm_"+j).value = map[e.value];
			}
		}
		
		//保存
		function saveParameter(){
			var tempqxm = "";
			var tempqxz = "";
			var temptcdm = "";
			var i=0;
			var count = '${pageSet.count}';
			if(sign){
				//第一次保存该区县权重
				$('#trId tr:not(:first)').each(function(){
					if(i<$('#trId tr:not(:first)').length-1){
						if(count == "0"){
							if(i<3){
								tempqxm += $(this).children("td").eq(0).html() + ",";
							}else{
								tempqxm += $(this).children("td").eq(0).find("input")[0].value + ",";
							}
						}else{
							if(i<count){
								tempqxm += $(this).children("td").eq(0).html() + ",";
							}else{
								tempqxm += $(this).children("td").eq(0).find("input")[0].value + ",";
							}
						}
						tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
						if($(this).children("td").eq(1).find("input")[0].value==""){
							alert("请输入权重值。");
							return false;
						}
						if(i==0){
							temptcdm = $(this).children("td").eq(2).find("select")[0].value;
						}
					}
					i++;
				});
			}else{
				alert("请先废除该区县权重数据再重新设置权重进行保存。");
				return false;
				/**
				$('#trId tr:not(:first)').each(function(){
					if(i<$('#trId tr:not(:first)').length-1){
						if(count == "0"){
							if(i<3){
								tempqxm += $(this).children("td").eq(0).html() + ",";
							}else{
								tempqxm += $(this).children("td").eq(0).find("input")[0].value + ",";
							}
						}else{
							if(i<count){
								tempqxm += $(this).children("td").eq(0).html() + ",";
							}else{
								tempqxm += $(this).children("td").eq(0).find("input")[0].value + ",";
							}
						}
						tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
						if($(this).children("td").eq(1).find("input")[0].value==""){
							alert("请输入权重值。");
							return false;
						}
						if(i==0){
							temptcdm = $(this).children("td").eq(2).find("input")[0].value;
						}
					}
					i++;
				});*/
			}
			var tt = tempqxz.substring(0,tempqxz.length-1).split(",");
			tempqxm = tempqxm.substring(0,tempqxm.length-1);
			tempqxz = tempqxz.substring(0,tempqxz.length-1);
			var val=0;
			for(var i=0;i<tt.length;i++){
				var reg = /^((\d{1,3})(\.\d{1,2})?|(\.\d{1,2}))$/;
				if(tt[i].match(reg) == null){
					alert("请输入正确的权重值。");
					return false;
				}
				if(tt[i]>100||tt[i]<0){
					alert("权重值范围为0%至100%");
					return false;
				}
				val += parseFloat(tt[i]);
			}
			if(val != 100){
				alert("权重值总和必须为100。");
				return false;
			}
			$.ajax({
				url:"hztj_saveParameter.action",
				cache:false,
				type:"post",
				data:{
					tempqxm:tempqxm,
					tempqxz:tempqxz,
					temptcdm:temptcdm
				},
				success:function(data){
					alert("保存成功。");
					$("#aaa027").val(temptcdm);
					query();
				}
			})
		}
		
		//权重删除
		function deleteWeigth(id){
			if(confirm("确认删除？")){
				$.ajax({
					url:"hztj_deleteParameter.action",
					cache:false,
					type:"post",
					data:{
						id:id
					},
					success:function(data){
						alert("删除成功。权重值总和不满100请重新设置权重值。");
						query();
					}
				});
			}
		}
		
		//上一年度药店等级设置
		function updateSet(){
			var wndFeatures = "dialogWidth=800px;dialogHeight=450px;center:true;scroll:no;status:no;";
			window.open('hztj_setYdLevel.action',null,wndFeatures);
		}
		
		function saveSingleRow(i){
			var gzbz = '${pageSet.data[0].GZBZ}';
			var qzm = $("#qzm_"+i).val();
			var qzz = $("#qzz_"+i).val();
			var aaa027 = bdAaa027;
			$.ajax({
				url:"hztj_saveSingleRow.action",
				cache:false,
				type:"post",
				data:{
					gzbz:gzbz,
					qzm:qzm,
					qzz:qzz,
					aaa027:aaa027
				},
				success:function(data){
					alert("新增权重保存成功。");
					query();
				}
			});
		}
		
		//导出区县所有权重
		function expQzAll(){
			var aaa027 = $("#aaa027").val();
			if(aaa027 == ""){
				alert("请先选择区县再进行导出。");
				return false;
			}
			form.action = "hztj_expQz.action";
			form.target = '_self';
			form.submit();
		}
	</script>
</body>
</html>
