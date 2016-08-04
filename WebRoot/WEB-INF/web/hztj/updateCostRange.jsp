<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>费用范围修改</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_updateCostRange.action" name="form" method="post" id="chooseForm" target="_self">
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
								<input type="hidden" value="${condition.id}" id="id" name="condition.id"/>
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
								<th style="width: 30px;">
									等级
								</th>
								<th style="width: 120px;">
									非处方药分类
								</th>
								<th style="width: 170px;">
									非处方药费用
								</th>
								<th style="width: 120px;">
									处方药分类
								</th>
								<th style="width: 170px;">
									处方药费用
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<s:if test="#index.index < 4">
									<tr>
										<td style="text-align: center;">
											${cons.JBDM }
										</td>
										<td style="text-align: center;">
											${cons.JBM }
										</td>
										<td style="text-align: center;">
											<s:if test="#cons.JBDM==4">
												&gt;=<input value="${cons.XXZ }" style="width: 40px;"/>万
											</s:if>
											<s:elseif test="#cons.JBDM==1">
												&lt;<input value="${cons.SXZ }" style="width: 40px;"/>万
											</s:elseif>
											<s:else>
												&gt;=<input value="${cons.XXZ }" style="width: 40px;"/>万and&lt;<input value="${cons.SXZ }" style="width: 40px;"/>万
											</s:else>
										</td>
										<td style="text-align: center;">
											${cons.TJBM }
										</td>
										<td style="text-align: center;">
											<s:if test="#cons.JBDM==4">
												&gt;=<input value="${cons.TXXZ }" style="width: 40px;"/>万
											</s:if>
											<s:elseif test="#cons.JBDM==1">
												&lt;<input value="${cons.TSXZ }" style="width: 40px;"/>万
											</s:elseif>
											<s:else>
												&gt;=<input value="${cons.TXXZ }" style="width: 40px;"/>万and&lt;<input value="${cons.TSXZ }" style="width: 40px;"/>万
											</s:else>
										</td>
									</tr>
								</s:if>
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
		$("#aaa027").val($("#tcdm").val());
		
		//保存数据
		function saveData(){
			var templev = "";//药店等级
			var tempname = "";//非处方药分类名称
			var tempval = "";//非处方药费用
			var tempcfname = "";//处方药分类名称
			var tempcfval = "";//处方药费用
			var temptcdm = $("#tcdm").val();//统筹代码
			var tempId = $("#id").val();//权重ID
			var gzbz = $("#gzbz").val();//规则标识
			$('#trId tr:not(:first)').each(function(){
				templev += $(this).children("td").eq(0).html() + ",";
				tempname += $(this).children("td").eq(1).html() + ",";
				tempcfname += $(this).children("td").eq(3).html() + ",";
				if($(this).children("td").eq(0).html()==1||$(this).children("td").eq(0).html()==4){
					tempval += $(this).children("td").eq(2).find("input")[0].value + ",";
					tempcfval += $(this).children("td").eq(4).find("input")[0].value + ",";
					if($(this).children("td").eq(2).find("input")[0].value==""||$(this).children("td").eq(4).find("input")[0].value==""){
						alert("请输入权重值。");
						return false;
					}
				}else{
					tempval += $(this).children("td").eq(2).find("input")[0].value + ";" + $(this).children("td").eq(2).find("input")[1].value + ",";
					tempcfval += $(this).children("td").eq(4).find("input")[0].value + ";" + $(this).children("td").eq(4).find("input")[1].value + ",";
					if($(this).children("td").eq(2).find("input")[0].value==""||$(this).children("td").eq(2).find("input")[1].value==""
						||$(this).children("td").eq(4).find("input")[0].value==""||$(this).children("td").eq(4).find("input")[1].value==""){
						alert("请输入权重值。");
						return false;
					}
				}
			});
			templev = templev.substring(0,templev.length-1);
			tempname = tempname.substring(0,tempname.length-1);
			tempval = tempval.substring(0,tempval.length-1);
			tempcfname = tempcfname.substring(0,tempcfname.length-1);
			tempcfval = tempcfval.substring(0,tempcfval.length-1);
			$.ajax({
				url:"hztj_saveUpdateCostRange.action",
				cache:false,
				type:"post",
				data:{
					templev:templev,
					tempname:tempname,
					tempval:tempval,
					tempcfname:tempcfname,
					tempcfval:tempcfval,
					temptcdm:temptcdm,
					tempId:tempId,
					gzbz:gzbz
				},
				success:function(data){
					alert("保存成功。");
					window.close();
				}
			})
		}
	</script>
</body>
</html>