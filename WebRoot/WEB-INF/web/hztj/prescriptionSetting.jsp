<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>处方药非处方药权重设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_prescriptionSetting.action" name="form" method="post" id="chooseForm" target="_self">
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
									权重
								</th>
								<%--<s:if test="null != pageSet.data && pageSet.data.size()>0">
									<th>操作</th>
								</s:if>--%>
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
									<%--<td style="text-align: center;">
										<a href="#"  onclick="weigthUpd('','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">权重修改</a>
									</td>--%>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td style="text-align: center;">处方药</td>
								<td>
									<input type="text" id="cf" name="condition.qzz" value=""  onkeyup="updateQz(this)" onafterpaste="updateQz(this)"/>%
								</td>
							</tr>
							<tr>
								<td style="text-align: center;">非处方药</td>
								<td>
									<input type="text" id="fcf" name="condition.qzz" value="" onkeyup="updateQz(this)" onafterpaste="updateQz(this)"/>%
								</td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<input type="button" value="权重修改" onclick="changeWeigth();"/>
						</s:if>
						<s:else>
							<input type="button" value="保存" onclick="saveData();"/>
						</s:else>
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//$("#aaa027").val(window.dialogArguments);
		$("#aaa027").val($("#tcdm").val());
		//修改权重值
		function updateQz(e){
			if(e.value > 100 || e.value<0){
				alert("权重值在0--100之间。");
				return false;
			}
			if(e.id == 'cf'){
				$("#fcf").val(100-parseFloat(e.value));
			}else{
				$("#cf").val(100-parseFloat(e.value));
			}
		}
		
		//保存
		function saveData(){
			var tempqxm = "";
			var tempqxz = "";
			var temptcdm = $("#tcdm").val();
			var qzdm = $("#qzdm").val();
			var gzbz = $("#gzbz").val();
			$('#trId tr:not(:first)').each(function(){
				tempqxm += $(this).children("td").eq(0).html() + ",";
				tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
				if($(this).children("td").eq(1).find("input")[0].value==""){
					alert("请输入权重值。");
					return false;
				}
			});
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
				url:"hztj_saveParameterPrescription.action",
				cache:false,
				type:"post",
				data:{
					tempqxm:tempqxm,
					tempqxz:tempqxz,
					temptcdm:temptcdm,
					qzdm:qzdm,
					gzbz:gzbz
				},
				success:function(data){
					alert("保存成功。");
					$("#aaa027").val(temptcdm);
					window.close();
					//query();
				}
			})
		}
		
		//权重值修改
		function weigthUpd(idx,value,id){
			var json = {idx:idx,value:value,id:id};
			var wndFeatures = "dialogWidth=300px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_weigthUpd.action',json,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		function query(){
			form.action = "hztj_prescriptionSetting.action";
			form.target = "_self";
			form.submit();
		}
		
		//权重值修改
		function changeWeigth(){
			var gzbz = $("#gzbz").val();
			var temptcdm = $("#tcdm").val();
			var qzdm = $("#qzdm").val();
			var wndFeatures = "dialogWidth=500px;dialogHeight=400px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_changeWeigth.action?condition.gzbz='+gzbz+"&condition.qzjb=4"+"&condition.tcdm="+temptcdm+"&condition.qzdm="+qzdm,null,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
	</script>
</body>
</html>