<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
	<!--- CSS --->
	<link rel="stylesheet" href="common/css/console/style.css" type="text/css" />
	<script src="common/js/jquery-1.10.0.min.js"></script>

	<!--- Javascript libraries (jQuery and Selectivizr) used for the custom checkbox --->

	<!--[if (gte IE 6)&(lte IE 8)]>
		<script type="text/javascript" src="common/js/console/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="common/js/console/selectivizr.js"></script>
		<noscript><link rel="stylesheet" href="common/css/console/fallback.css" /></noscript>
	<![endif]-->



<script language="javascript">
function accPass()
{
	var oldAccPass;
	var newAccPass;
	
	
	oldAccPass=$("#oldAccPass").val().replace(/%/g,"%");
	oldAccPass=$("#oldAccPass").val().replace(/\&/g,"&");
	oldAccPass=$("#oldAccPass").val().replace(/\+/g,"+");
	oldAccPass=$("#oldAccPass").val().replace(/\#/g,"#");
	
	
	newAccPass=$("#newAccPass").val().replace(/%/g,"%");
	newAccPass=$("#newAccPass").val().replace(/\&/g,"&");
	newAccPass=$("#newAccPass").val().replace(/\+/g,"+");
	newAccPass=$("#newAccPass").val().replace(/\#/g,"#");
	
	
	if(""!=encodeURI(encodeURI(oldAccPass)) && ""!=encodeURI(encodeURI(newAccPass)))
		{

		
			var text=$.ajax(
					{
						type:"post",
						url:"index_updateConsolePass.action?oldAccPass="+encodeURI(encodeURI(oldAccPass))+"&newAccPass="+encodeURI(encodeURI(newAccPass)),
						success:function(text){
							if(text=="yes")
								{
									$("#showMessage").empty();
									$("#showMessage").append("密码修改成功");
									$("#oldAccPass").val("");
									$("#newAccPass").val("");
								}
							else
								{
									$("#showMessage").empty();
									$("#showMessage").append("旧密码不正确");
								}
						}
					});
		   
		}

	}

	
</script>
	</head>

	<body>
		<div id="container">
			<table class="list">
			<tr>
			<td>
			<l style="float:left;line-height:48px">原先的密码:</l>
			<div class="password-field2">
			<input type="password" id="oldAccPass"  value="" />
			<i style="float:right;cursor:pointer;" id="showMessage"></i>
			</div>
			</td>
			</tr>
			
			<tr>
			<td>
			<l style="float:left;line-height:48px">新建的密码:</l>
			<div class="password-field2">
			<input type="password" id="newAccPass"  value="" />
			<i style="float:right;cursor:pointer;" onclick="accPass()">确定修改</i>
			</div>
			</td>
			</tr>	
			</table>
		</div>
		<div id="footer">
			
		</div>

</body>
</html>