<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上海市医药采购服务与监管信息系统</title>
			<script src="${path}js/jquery-1.11.0.min.js"></script>
		 	<!-- 弹出窗口 -->
			<link rel="stylesheet" type="text/css" href="${path}css/hztj/default.css" />
			<link rel="stylesheet" href="${path}css/hztj/colorbox.css" />
			<link rel="stylesheet" href="${path}css/ywjc/chuli.css" />
			<script src="${path}js/jquery.colorbox.js"></script>
			<!-- 弹出窗口 -->  
				<style>
					html,body {
						overflow:hidden;
						background: #fff;
					}
			</style>
		
	
	<script type="text/javascript">
	$(document).ready(function($){
		
		var d=new Date();
		document.getElementById('clsj').value=d.getUTCFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' ' + d.getHours()+':'+d.getMinutes()+':'+d.getSeconds();
		$('input#save').bind('click',function(){
			var textContent=$('#textContent').val().replace(/\r\n|\n/g,"").replace(/\s+/g, "");
			var time=$('#clsj').val().trim();
			var id=$('#id_hidden',window.parent.document).val();
			returnData(textContent,time,id);
		});
	
	});
	
	function returnData(content,time,id){
		var text='{cljl:"'+content+'",clsj:"'+time+'"}';
		var code=encodeURI(encodeURI(text));
		//$('#listConstant',window.parent.document).attr('action','ywjc_cgdjjcOfgotoCgjgjc.action?ResultContent='+code+'&id='+id+'');
		//$('#listConstant',window.parent.document).submit();
		$("#cljgContentHidden",window.parent.document).val(code);
		$("#cljgIdHidden",window.parent.document).val(id);
	
		window.parent.formClose();
	}
</script>
  </head>
  
  <body>
 
   	<!--处理弹出框 --> 

  <div class="contentDiv">
    <table width="50%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th>处理情况记录</th>
        <td>
          <input type="button" value="保存" id="save" name="" style="float:right;width:80px;margin-right:10px;"/>
        </td>
      </tr>
      <tr></tr>
      <tr>
       <td colspan="2">
       <textarea id="textContent" rows="5" cols="50" ></textarea>
       </td>
      </tr>
      <tr></tr>
      <tr>
        <th>处理时间</th>
        <td>
        	<input type="text" name="" id="clsj" value="" style="float:right;margin-right:10px;"/>
        </td>
      </tr>
    </table>
    <input type="hidden" id="detailId"></input>
  </div>

<!--处理弹出框结束 --> 

  </body>
</html>
