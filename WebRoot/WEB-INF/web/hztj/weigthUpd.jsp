<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>增长率设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<base target="_self"></base>
<body>
	<form action="hztj_gotoQshzByCgje.action" method="post" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<tr>
							<td style="padding-left: 10px;width: 100px;">
								<span id="showOrNot2">权重值：</span>
								<span id="showOrNot3" style="display: none;">费用额度：</span>
								<span id="showOrNot5" style="display: none;">分值：</span>
							</td>
							<td style="text-align: left;">
								<input type="hidden" name="condition.id" id="id" style="width: 100px;"/>
								<input type="text" name="condition.qzz" id="qzz" style="width: 100px;"/><span id="showOrNot">%</span><span id="showOrNot1" style="display: none;">元</span>
							</td>
						</tr>
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
		var idx = window.dialogArguments.idx;
		if(idx=="true"){
			document.getElementById("showOrNot").style.display = "none";
		}
		if(idx=="je"){
			document.getElementById("showOrNot").style.display = "none";
			document.getElementById("showOrNot2").style.display = "none";
			document.getElementById("showOrNot1").style.display = "";
			document.getElementById("showOrNot3").style.display = "";
		}
		if(idx=="dn"){
			document.getElementById("showOrNot").style.display = "none";
			document.getElementById("showOrNot2").style.display = "none";
			document.getElementById("showOrNot5").style.display = "";
		}
		$("#qzz").val(window.dialogArguments.value);
		$("#id").val(window.dialogArguments.id);
		
		//保存修改的权重值
		function saveData(){
			var qzz = $("#qzz").val();
			var id = $("#id").val();
			if(idx=="je"||idx=="dn"){
				var reg = /^\d+(.\d+)?$/;
				if(qzz.match(reg) == null){
					if(idx=="je"){
						alert("请输入正确的费用金额。");
					}else{
						alert("请输入正确的分值。");
					}
					return false;
				}
			}else{
				var reg = /^((\d{1,3})(\.\d{1,2})?|(\.\d{1,2}))$/;
				if(qzz.match(reg) == null){
					alert("请输入正确的权重值。");
					return false;
				}
				if(qzz>100||qzz<0){
					alert("权重值范围为0%至100%");
					return false;
				}
			}
			$.ajax({
				url:"hztj_updateParameter.action",
				cache:false,
				type:"post",
				data:{
					id:id,
					qzz:qzz
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