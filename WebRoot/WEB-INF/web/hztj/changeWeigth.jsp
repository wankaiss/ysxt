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
		<title>权重值修改</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<base target="_self"></base>
<body>
	<form action="hztj_growSetting.action" name="form" method="post" id="chooseForm" target="_self">
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
								<input type="hidden" value="${condition.qzjb}" id="qzjb" name="condition.qzjb"/>
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
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr id="id_<s:property value="#index.index"/>">
									<td style="text-align: center;">
										${cons.QZM}
									</td>
									<td style="text-align: center;">
										<input value="${cons.QZZ}"/>
										<s:if test="condition.qzjb==6">元</s:if>
										<s:elseif test="condition.qzjb==3"></s:elseif>
										<s:else>%</s:else>
										<s:if test="condition.qzjb==1&&#cons.QZM!='增长率'&&#cons.QZM!='药店费用等级跨越'&&#cons.QZM!='单次非处方购药费用占比'">
											<!-- qzjb为1时 权重项除了必输的三项外  其他项可以删除 -->
											<a href="#" onclick="clearRow('id_<s:property value="#index.index"/>','<s:property value="#cons.ID"/>');">删除</a>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</s:if>
					</table>
					<div style="text-align: center;">
						<s:if test="condition.qzjb==1">
							<input type="button" title="新增" onclick="toAddRow();" value="新增" style="width: 60px;"/>&nbsp;&nbsp;
						</s:if>
						<input type="button" value="保存" onclick="saveDate();"/>&nbsp;&nbsp;
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$("#aaa027").val($("#tcdm").val());
		
		var i = '${pageSet.count}';
		//新增参数
		function toAddRow(){
			var html = "<tr id='id_"+(i+1)+"'>";
			html += "<td><input type='text' name='condition.qzm' id='qzm_"+(i+1)+"'/></td>";
			html += "<td><input type='text' name='condition.qzz' id='qzz_"+(i+1)+"'/>%";
			html += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeRow("+(i+1)+")'>删除</a></td>";
			html += "</tr>";
			$("#trId").append(html);
			i++;
		}
		//删除新增行
		function removeRow(rowNum){
			$("#id_"+rowNum).remove();
		}
		var removeId = "";
		function clearRow(rowId,id){
			$("#"+rowId).remove();
			removeId = removeId+id; 
		}
		
		//权重值修改保存（修改全部参数）
		function saveDate(){
			var tempqxm = "";
			var tempqxz = "";
			var temptcdm = $("#tcdm").val();
			var qzdm = $("#qzdm").val();
			var gzbz = $("#gzbz").val();
			var qzjb = $("#qzjb").val();
			var count = '${pageSet.count}';
			var m = 0;
			$('#trId tr:not(:first)').each(function(){
				if(m<count){
					tempqxm += $(this).children("td").eq(0).html() + ",";
					tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
					if($(this).children("td").eq(1).find("input")[0].value==""){
						if(qzjb==6){
							alert("请输入权重值。");
						}else{
							alert("请输入权重值。");
						}
						return false;
					}
				}else{
					tempqxm += $(this).children("td").eq(0).find("input")[0].value + ",";
					tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
					if($(this).children("td").eq(0).find("input")[0].value==""){
						alert("请输入权重名。");
						return false;
					}
					if($(this).children("td").eq(1).find("input")[0].value==""){
						alert("请输入权重值。");
						return false;
					}
				}
				m++;
			});
			var tt = tempqxz.substring(0,tempqxz.length-1).split(",");
			tempqxm = tempqxm.substring(0,tempqxm.length-1);
			tempqxz = tempqxz.substring(0,tempqxz.length-1);
			var val=0;
			if(qzjb!=3&&qzjb!=6){
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
			}
			$.ajax({
				url:"hztj_saveParameterChangeWeigth.action",
				cache:false,
				type:"post",
				data:{
					tempqxm:tempqxm,
					tempqxz:tempqxz,
					temptcdm:temptcdm,
					qzdm:qzdm,
					gzbz:gzbz,
					qzjb:qzjb,
					removeId:removeId
				},
				success:function(data){
					alert("保存成功。");
					window.returnValue = true;
					window.close();
				}
			})
		}
	</script>
</body>
</html>