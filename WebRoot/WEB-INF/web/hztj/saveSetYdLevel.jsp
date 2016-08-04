<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>上年度药店等级设置</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<body>
	<form action="hztj_saveSetYdLevel.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" id="trId" width="95%" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>
									药店名称
								</th>
								<th>
									药店编码
								</th>
								<th>
									药店等级
								</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<td style="text-align: center;">${cons.AKB021}</td>
									<td style="text-align: center;">${cons.AKB020}</td>
									<td style="text-align: center;">
										<input type="text" id="yddj" value="${cons.YDDJ}" maxlength="1" onkeyup="this.value=this.value.replace(/\D/g,'')"  
											onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
										<input type="hidden" id="akb020" value="${cons.AKB020}"/>
										<input type="hidden" id="akb021" value="${cons.AKB021}"/>
										<input type="hidden" id="aaa027" value="${cons.AAA027}"/>
										<input type="hidden" id="id" value="${cons.ID}"/>
									</td>
								</tr>
							</s:iterator>
						</s:if>
					</table>
					</br>
					<div style="text-align: center;">
						<input type="button" value="保存" onclick="saveDate();"/>
						<input type="button" value="关闭" onclick="javascript:window.close();"/>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//保存分值
		function saveDate(){
			var yddj = $("#yddj").val();
			var akb020 = $("#akb020").val();
			var akb021 = $("#akb021").val();
			var aaa027 = $("#aaa027").val();
			var id = $("#id").val();
			$.ajax({
				url:"hztj_saveOrUpdYdLevel.action",
				cache:false,
				type:"post",
				data:{
					yddj:yddj,
					akb020:akb020,
					akb021:akb021,
					aaa027:aaa027,
					id:id
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