<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>备注</title>
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
	</head>
<base target="_self"></base>
<body>
	<form action="cbr_saveBeathAddList.action" method="post" id="chooseForm" target="qshzMain">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<tr>
							<td>备注：</td>
							<td style="padding-left: 10px;width: 200px;">
								<input type="hidden" name="condition.aaa027" id="aaa027" value="${condition.aaa027}"/>
								<input type="hidden" name="condition.aka130" id="aka130" value="${condition.aka130}"/>
								<input type="hidden" id="yearMonth" name="condition.yearMonth" value="${condition.yearMonth}"/>
								<input type="hidden" id="pjfy" name="condition.pjfy" value="${condition.pjfy}"/>
								<input type="hidden" id="pjcs" name="condition.pjcs" value="${condition.pjcs}"/>
								<input type="hidden" id="rc_pjcs" name="condition.rc_pjcs" value="${condition.rc_pjcs }"/>
								<input type="hidden" id="rc_fyze" name="condition.rc_fyze" value="${condition.rc_fyze }"/>
								<input type="hidden" id="rc_pjfy" name="condition.rc_pjfy" value="${condition.rc_pjfy }"/>
								<input type="hidden" id="rc_cszs" name="condition.rc_cszs" value="${condition.rc_cszs }"/>
								<input type="hidden" name="condition.sign" id="sign" value="${condition.sign }"/>
								<input type="hidden" name="condition.hhmdbz" id="hhmdbz" value="${condition.hhmdbz }"/>
								<input type="hidden" name="condition.aac002" id="aac002" value="${condition.aac002 }"/>
								<textarea rows="1" cols="1" style="width: 180px;height: 100px;" id="bz"></textarea>
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
		function saveData(){
			var rc_pjfy = $("#rc_pjfy").val();
			var rc_pjcs = $("#rc_pjcs").val();
			var rc_fyze = $("#rc_fyze").val();
			var rc_cszs = $("#rc_cszs").val();
			var yearMonth = $("#yearMonth").val();
			var aaa027 = $("#aaa027").val();
			var aka130 = $("#aka130").val();
			var pjfy = $("#pjfy").val();
			var pjcs = $("#pjcs").val();
			var hhmdbz = $("#hhmdbz").val();
			var sign = $("#sign").val();
			var sfzhm = $("#aac002").val();
			var bz = $("#bz").val();
			$.ajax({
				url:"cbr_saveBeathAddList.action",
				cache:false,
				type:"post",
				data:{
					hhmdbz:hhmdbz,
					sign:sign,
					aka130:aka130,
					aaa027:aaa027,
					rc_pjfy:rc_pjfy,
					rc_pjcs:rc_pjcs,
					rc_fyze:rc_fyze,
					rc_cszs:rc_cszs,
					yearMonth:yearMonth,
					pjfy:pjfy,
					pjcs:pjcs,
					bz:bz,
					sfzhm:sfzhm
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