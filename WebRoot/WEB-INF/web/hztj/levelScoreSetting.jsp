<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>药店等级差值设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_levelScoreSetting.action" name="form" method="post" id="chooseForm" target="_self">
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
								<input type="hidden" value="${condition.tcdm}" id="tcdm"/>
								<input type="hidden" value="${condition.divId}" id="divId"/>
								<input type="hidden" value="${condition.gzbz}" id="gzbz" name="condition.gzbz"/>
								<input type="hidden" value="${condition.jbdm}" id="jbdm"/>
								<input type="hidden" value="${condition.qzdm}" id="qzdm"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tabBox" style="padding-top: 10px;">
					<ul class="menuTab">
				        <li id="li1"><a href="#" target="_self" onclick="changTabShow('1');">处方药</a></li>
				        <li id="li2"><a href="#" target="_self" onclick="changTabShow('2');">非处方药</a></li>
				    </ul>
				</div>
				<div id="div1">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									等级差值
								</th>
								<th>
									分值
								</th>
								<%--<s:if test="null != pageSet.data && pageSet.data.size()>0">
									<th>
										操作
									</th>
								</s:if>
								--%>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.QZM}</td>
									<td style="text-align: center;">${cons.QZZ}</td>
									<%--<td style="text-align: center;">
										<a href="#"  onclick="weigthUpd('dn','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>');">修改</a>
									</td>
									--%>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td style="text-align: center;">&lt;=0</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">1</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">2</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">3</td>
								<td><input type="text"/></td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<input type="button" value="分值修改" onclick="updateScore();"/>
						</s:if>
						<s:else>
							<input type="button" value="保存" onclick="saveData();"/>
						</s:else>
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
				<div style="display: none;" id="div2">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									等级差值
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
									<td style="text-align: center;">${cons.QZZ}</td>
									<%--<td style="text-align: center;">
										<a href="#" onclick="updateScore('dn','<s:property value="#cons.QZZ"/>','<s:property value="#cons.ID"/>')">修改</a>
									</td>
									--%>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td style="text-align: center;">&lt;=0</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">1</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">2</td>
								<td><input type="text"/></td>
							</tr>
							<tr>
								<td style="text-align: center;">3</td>
								<td><input type="text"/></td>
							</tr>
						</s:else>
					</table>
					<div style="text-align: center;">
						<s:if test="null != pageSet1.data && pageSet1.data.size()>0">
							<input type="button" value="分值修改" onclick="updateScore();"/>
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
		$("#aaa027").val($("#tcdm").val());
		var divId = $("#divId").val();
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
				query();
			}else if(idx == 2){
				divId = 2;
				document.getElementById("div2").style.display = "";
				document.getElementById("div1").style.display = "none";
				$("#li2").addClass("current");
				$("#li2").siblings().removeClass("current");
				query();
			}
		}
		//查询
		function query(){
			var jbdm = $("#jbdm").val();
			form.action = "hztj_levelScoreSetting.action?condition.divId="+divId+"&condition.tcdm="+$("#tcdm").val()+"&condition.jbdm="+jbdm;
			form.target="_self";
			form.submit();
		}
		//分数修改
		function weigthUpd(idx,value,id){
			var json = {idx:idx,value:value,id:id};
			var wndFeatures = "dialogWidth=300px;dialogHeight=200px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_weigthUpd.action',json,wndFeatures);
			if(k){
				//修改成功重新加载页面
				query();
			}
		}
		
		//保存
		function saveData(){
			var tempqxm = "";
			var tempqxz = "";
			var temptcdm = $("#tcdm").val();
			var jbdm = $("#jbdm").val();
			var m=0;
			if(divId == 1){
				$('#trId tr:not(:first)').each(function(){
					if(m<4){
						tempqxm += $(this).children("td").eq(0).html() + ",";
						tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
						if($(this).children("td").eq(1).find("input")[0].value==""){
							alert("请输入分值。");
							return false;
						}
					}
					m++;
				});
			}else{
				$('#trId tr:not(:first)').each(function(){
					if(m<4){
						tempqxm += $(this).children("td").eq(0).html() + ",";
						tempqxz += $(this).children("td").eq(1).find("input")[0].value + ",";
						if($(this).children("td").eq(1).find("input")[0].value==""){
							alert("请输入分值。");
							return false;
						}
					}
					m++;
				});
			}
			var tt = tempqxz.substring(0,tempqxz.length-1).split(",");
			tempqxm = tempqxm.substring(0,tempqxm.length-1);
			tempqxz = tempqxz.substring(0,tempqxz.length-1);
			var gzbz = $("#gzbz").val();
			for(var i=0;i<tt.length;i++){
				var reg = /^\d+(.\d+)?$/;
				if(tt[i].match(reg) == null){
					alert("请输入正确的分值。");
					return false;
				}
			}
			$.ajax({
				url:"hztj_saveLevelScore.action",
				cache:false,
				type:"post",
				data:{
					tempqxm:tempqxm,
					tempqxz:tempqxz,
					temptcdm:temptcdm,
					divId:divId,
					jbdm:jbdm,
					gzbz:gzbz
				},
				success:function(data){
					alert("保存成功。");
					query();
				}
			})
		}
		
		function updateScore(){
			var temptcdm = $("#tcdm").val();//统筹代码
			var gzbz = $("#gzbz").val();//规则标识
			var divId = $("#divId").val();
			var qzdm = $("#qzdm").val();
			var wndFeatures = "dialogWidth=600px;dialogHeight=400px;center:true;scroll:no;status:no;";
			var k = window.showModalDialog('hztj_updateScore.action?condition.gzbz='+gzbz+"&condition.tcdm="+temptcdm+"&condition.divId="+divId+"&condition.qzjb=9"+"&condition.qzdm="+qzdm,null,wndFeatures);
			var tt = k.split(",");
			$("#gzbz").val(tt[1]);
			if(tt[0]){
				//修改成功重新加载页面
				query();
			}
		}
	</script>
</body>
</html>