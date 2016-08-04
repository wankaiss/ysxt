<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上海市医药采购服务与监管信息系统</title>
			<script src="${path}/js/jquery-1.11.0.min.js"></script>
		 	<!-- 弹出窗口 -->
			<link rel="stylesheet" type="text/css" href="${path}/css/hztj/default.css" />
			<link rel="stylesheet" href="${path}/css/hztj/colorbox.css" />
			<link rel="stylesheet" href="${path}/css/ywjc/chuli.css" />
			<script src="${path}/js/jquery.colorbox.js"></script>
			<!-- 弹出窗口 -->  
				<style>
					html,body {
						overflow:hidden;
						background: #fff;
					}
			</style>
			
			<%
				String cljgid = "";
				if(null!=request.getParameter("cljgid")&&request.getParameter("cljgid")!=""){
					cljgid = request.getParameter("cljgid");
				}
				request.setAttribute("cljgid",cljgid);
			 %>
		
	
	<script type="text/javascript">
	$(document).ready(function($){
		var id = $('#cljgid_hidden').val();
		var cljgValue=$("#"+id,window.parent.document).val();
		var content=eval('['+cljgValue+']');
		document.getElementById("cljg_textarea").innerHTML="处理结果："+content[0].cljl+"\r\n处理时间："+content[0].clsj;
	
	});
	
	
</script>
  </head>
  
  <body>
 
   
  <div class="contentDiv">
    <table width="50%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th>处理结果</th>
      </tr>
      <tr></tr>
      <tr>
       <td colspan="2">
       <textarea id="cljg_textarea" rows="5" cols="50"></textarea>
       </td>
      </tr>
    </table>
	<input type="hidden" value="${cljgid }" id="cljgid_hidden" />
  </div>





<script language="javascript">
function formClose()
{
	jQuery.colorbox.close();
}

</script>
  </body>
</html>
