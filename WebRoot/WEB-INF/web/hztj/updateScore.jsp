<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>
			<s:if test="condition.qzjb==8">
       			单次非处方购药费用占比分值设置
       		</s:if>
       		<s:else>
       			药店费用等级差值分值设置
       		</s:else>
		</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_singleMedPay.action" name="form" method="post" id="chooseForm" target="qshzMain">
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
								<input type="hidden" value="${condition.divId}" id="divId"/>
								<input type="hidden" value="${condition.qzjb}" id="qzjb"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabBox" style="padding-top: 10px;">
					<ul class="menuTab">
				        <li id="li1">
				        	<a href="#" target="_self" onclick="changTabShow('1');">
				        		<s:if test="condition.qzjb==8">
				        			当年账户购药
				        		</s:if>
				        		<s:else>
				        			处方药
				        		</s:else>
				        	</a>
				        </li>
				        <li id="li2">
				        	<a href="#" target="_self" onclick="changTabShow('2');">
				        		<s:if test="condition.qzjb==8">
				        			历年账户购药
				        		</s:if>
				        		<s:else>
				        			非处方药
				        		</s:else>
				        	</a>
				        </li>
				    </ul>
				</div>
				<div id="div1">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									<s:if test="condition.qzjb==8">
					        			增长率
					        		</s:if>
					        		<s:else>
					        			等级差值
					        		</s:else>
								</th>
								<th>
									分值
								</th>
								<%--<s:if test="null != pageSet.data && pageSet.data.size()>0">
									<th>
										操作
									</th>
								</s:if>--%>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.QZM}</td>
									<td style="text-align: center;"><input value="${cons.QZZ}"/></td>
									<%--<td style="text-align: center;">
										<a href="#"  onclick="weigthUpd('dn','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">修改</a>
									</td>--%>
								</tr>
							</s:iterator>
						</s:if>
					</table>
					<div style="text-align: center;">
						<input type="button" value="保存" onclick="saveData();"/>
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
				<div style="display: none;" id="div2">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									<s:if test="condition.qzjb==8">
					        			增长率
					        		</s:if>
					        		<s:else>
					        			等级差值
					        		</s:else>
								</th>
								<th>
									分值
								</th>
								<%--<s:if test="null != pageSet1.data && pageSet1.data.size()>0">
									<th>
										操作
									</th>
								</s:if>
								--%>
							</tr>
						</thead>
						<s:if test="null != pageSet1.data && pageSet1.data.size()>0">
							<s:iterator value="pageSet1.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.QZM}</td>
									<td style="text-align: center;"><input value="${cons.QZZ}"/></td>
									<%--<td style="text-align: center;">
										<a href="#" onclick="updateScore('dn','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>')">修改</a>
									</td>
									--%>
								</tr>
							</s:iterator>
						</s:if>
					</table>
					<div style="text-align: center;">
						<input type="button" value="保存" onclick="saveData();"/>
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//$("#aaa027").val(window.dialogArguments);
		$("#aaa027").val($("#tcdm").val());
		var divId = $("#divId").val();
		var qzjb = $("#qzjb").val();
		$(document).ready(function(){
			if(divId==1){
				$("ul.menuTab li:first-child").addClass("current");
			}else{
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
			}
		});
		//更换tab页
		function changTabShow(idx){
			if(idx == 1){
				divId = 1;
				document.getElementById("div1").style.display = "";
				document.getElementById("div2").style.display = "none";
				$("#li1").addClass("current");
				$("#li1").siblings().removeClass("current");
			}else if(idx == 2){
				divId = 2;
				document.getElementById("div2").style.display = "";
				document.getElementById("div1").style.display = "none";
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
			}
		}
		
		//保存
		function saveData(){
			var tempqxm = "";
			var tempqxz = "";
			var temptcdm = $("#tcdm").val();
			var qzdm = $("#qzdm").val();
			var gzbz = $("#gzbz").val();
			var qzjb = $("#qzjb").val();
			var count = '${pageSet.count}';
			var m=0;
			$('#trId tr:not(:first)').each(function(){
				if(m<count){
					tempqxm += $(this).children("td").eq(0).html() + ",";
					tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
					if($(this).children("td").eq(1).find("input")[0].value==""){
						alert("请输入分值。");
						return false;
					}
				}else if(m==count){
					tempqxm = tempqxm.substring(0,tempqxm.length-1);
					tempqxz = tempqxz.substring(0,tempqxz.length-1);
					tempqxm += "-";
					tempqxz += "-";
				}else if(m>count){
					tempqxm += $(this).children("td").eq(0).html() + ",";
					tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
					if($(this).children("td").eq(1).find("input")[0].value==""){
						alert("请输入分值。");
						return false;
					}
				}
				m++;
			});
			var tt = tempqxz.substring(0,tempqxz.length-1).split("-");
			var uu = tt[0].split(",");
			var vv = tt[1].split(",");
			tempqxm = tempqxm.substring(0,tempqxm.length-1);
			tempqxz = tempqxz.substring(0,tempqxz.length-1);
			for(var i=0;i<uu.length;i++){
				var reg = /^\d+(.\d+)?$/;
				if(uu[i].match(reg) == null){
					alert("请输入正确的分值。");
					return false;
				}
			}
			for(var i=0;i<vv.length;i++){
				var reg = /^\d+(.\d+)?$/;
				if(vv[i].match(reg) == null){
					alert("请输入正确的分值。");
					return false;
				}
			}
			$.ajax({
				url:"hztj_saveUpdateScore.action",
				cache:false,
				type:"post",
				data:{
					tempqxm:tempqxm,
					tempqxz:tempqxz,
					temptcdm:temptcdm,
					qzdm:qzdm,
					divId:divId,
					gzbz:gzbz,
					qzjb:qzjb
				},
				success:function(data){
					alert("保存成功。");
					$("#aaa027").val(temptcdm);
					window.returnValue = true+","+data.result[0];
					window.close();
					//query();
				}
			})
		}
		
	</script>
</body>
</html>