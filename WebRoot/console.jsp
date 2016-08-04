<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
	<!--- CSS --->
	<link rel="stylesheet" href="common/css/console/style.css" type="text/css" />
	<script src="common/js/jquery-1.10.0.min.js"></script>
	
	 	<!-- 弹出窗口 -->
	<link rel="stylesheet" href="${path}css/colorbox.css" />
	<script src="${path}js/jquery.colorbox.js"></script>
	<!-- 弹出窗口 --> 

	<!--- Javascript libraries (jQuery and Selectivizr) used for the custom checkbox --->

	<!--[if (gte IE 6)&(lte IE 8)]>
		<script type="text/javascript" src="common/js/console/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="common/js/console/selectivizr.js"></script>
		<noscript><link rel="stylesheet" href="common/css/console/fallback.css" /></noscript>
	<![endif]-->



<script language="javascript">

$(document).ready(function() {
	$(".iframe").colorbox({iframe:true, width:"95%", height:"95%"});
	//iframe
	$(".iframe").colorbox({
		onClosed:function(){}
	});
	
});

function loginConsole()
{
	var consoleAccName;
	var consoleAccPass;
	
	
	consoleAccName=$("#consoleAccName").val().replace(/%/g,"%");
	consoleAccName=$("#consoleAccName").val().replace(/\&/g,"&");
	consoleAccName=$("#consoleAccName").val().replace(/\+/g,"+");
	consoleAccName=$("#consoleAccName").val().replace(/\#/g,"#");
	
	
	consoleAccPass=$("#consoleAccPass").val().replace(/%/g,"%");
	consoleAccPass=$("#consoleAccPass").val().replace(/\&/g,"&");
	consoleAccPass=$("#consoleAccPass").val().replace(/\+/g,"+");
	consoleAccPass=$("#consoleAccPass").val().replace(/\#/g,"#");
	
	
	if(""!=encodeURI(encodeURI(consoleAccName)) && ""!=encodeURI(encodeURI(consoleAccPass)))
		{
			var text=$.ajax(
					{
						type:"post",
						url:"index_checkIfLoginConsole.action?consoleAccName="+encodeURI(encodeURI(consoleAccName))+"&consoleAccPass="+encodeURI(encodeURI(consoleAccPass)),
						success:function(text){
							if(text=="yes")
								{
									window.location.href="loginYsxtGl.action";
								}
							else
								{
								window.location.href="";
								}
						}
					});
		   
		}

	}

	
</script>
	</head>

	<body>
		<div id="container">
		<form action="#">
				<div class="login">统计分析与检测后台管理</div>
				<div class="username-text">用户名:</div>
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input readonly type="text" id="consoleAccName"  value="admin" />
				</div>
				<div class="password-field">
					<input type="password" id="consoleAccPass" value="" />
				</div>
	<div class="forgot-usr-pwd"><a class="iframe" href="index_gotoAccPass.action">修改密码</a></div>
				<input type="button" onclick="loginConsole()" value="进入" />
				</form>
		</div>
		<div id="footer">
			
		</div>

</body>
</html>