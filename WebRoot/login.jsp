<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>统计分析与业务监测登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <!-- CSS -->
        <link rel="stylesheet" href="common/css/login/reset.css">
        <link rel="stylesheet" href="common/css/login/supersized.css">
        <link rel="stylesheet" href="common/css/login/style.css">
		<script src="common/js/jquery-1.10.0.min.js"></script>
		
<script language="javascript">
function registMessage()
{
	var username;
	var password;
	
	
	username=$("#login_user").val().replace(/%/g,"%");
	username=$("#login_user").val().replace(/\&/g,"&");
	username=$("#login_user").val().replace(/\+/g,"+");
	username=$("#login_user").val().replace(/\#/g,"#");
	
	
	password=$("#login_pass").val().replace(/%/g,"%");
	password=$("#login_pass").val().replace(/\&/g,"&");
	password=$("#login_pass").val().replace(/\+/g,"+");
	password=$("#login_pass").val().replace(/\#/g,"#");
	
	
	if(""!=encodeURI(encodeURI(username)) && ""!=encodeURI(encodeURI(password)))
		{
		$("#loginForm").empty();
		$("#loginForm").append("<h1>正在验证获取登陆者信息...<img src=\"${pageContext.request.contextPath }/common/images/loading-animation-8.gif\" style=\"border=0;padding-top:5px\"></h1>");

		var text=$.ajax(
					{
						type:"post",
						url:"index_loginCheck.action?login_user="+encodeURI(encodeURI(username))+"&login_pass="+encodeURI(encodeURI(password)),
						success:function(text){
							if(text=="yes")
								{
								$("#loginForm").empty();
								$("#loginForm").append("<h1><font color=\"green\">验证完毕，即将进入系统...</font></h1>");
									window.location.href="loginYsxt.action";
								}
							else
								{
								//$("#loginForm").append("<div id=\"box\"></div><h1><font color=\"green\">验证完毕，即将进入系统...</font></h1>");
								window.location.href="";
								}
						}
				});
		   }
		

	}

	function a()
	{
		$("#loginForm").empty();
		$("#loginForm").append("<div class=\"page-container\"><font id=\"states\" color=\"green\">验证完毕，即将进入系统...</font><div id=\"box\"></div></div>");
		getoffset();
		change('0','20');
		//580-384=176
		//402-236=147
	}
	
</script>

<style type="text/css">
#box{
 	margin-top: -63px;
 	 /*margin-left: 226px;*/
	width:44px;	
	height:44px;
	line-height:44px;
	text-align:center;
	background: url(common/images/investjd.png);
}
</style>

    </head>

    <body>
    <div id='socket'></div>
<div class="container">
<i class="headClass" id="headPic"></i>
        <div class="page-container" >          
            <form action="" method="post" id="loginForm">
            <label for="login">用户登录</label>
                <input type="text" id="login_user" name="login" class="username" placeholder="用户名">
                <input type="password" id="login_pass" name='password' class="password" placeholder="密码">
                <button type="button" onclick="registMessage()">登 录</button>
                <div class="error"><span>+</span></div>
            </form>
            
        </div>

        <div class="connect">
                <p>
                    <a class="email" href=""></a>marketing_dept@wondersgroup.com
                    <a class="tel" href=""></a>021-24178888 &nbsp;&nbsp;© 万达信息股份有限公司版权所有 沪ICP备05002296号
                </p>
            </div>
</div>
        <script src="common/js/login/supersized.3.2.7.min.final.js"></script>
        <script src="common/js/login/scripts.js"></script>
        
            <script language="javascript">
    jQuery(function($){

        $.supersized({

            // Functionality
            slide_interval     : 4000,    // Length between transitions
            transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
            transition_speed   : 1000,    // Speed of transition
            performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)

            // Size & Position
            min_width          : 0,    // Min width allowed (in pixels)
            min_height         : 0,    // Min height allowed (in pixels)
            vertical_center    : 1,    // Vertically center background
            horizontal_center  : 1,    // Horizontally center background
            fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
            fit_portrait       : 1,    // Portrait images will not exceed browser height
            fit_landscape      : 0,    // Landscape images will not exceed browser width

            // Components
            slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
            slides             : [    // Slideshow Images
                                     {image : 'common/images/backgrounds/1.jpg'},
                                     {image : 'common/images/backgrounds/2.jpg'},
                                     {image : 'common/images/backgrounds/3.jpg'}
                                 ]

        });

    });
    </script>
    
    



<script language="javascript">
	function change (begin,end){
			var object = document.getElementById('box')
			var i = new Number(begin);
			var innerEnd=new Number(end);
			var intervalObj;
			intervalObj=setInterval(function(){
				i++
				if(i>=innerEnd){
					//i=data
					clearInterval(intervalObj);
				}
				var imgLeft = -(i*44+(i*10))+'px'
				object.style.backgroundPosition = imgLeft+'\t'+'0px'
				object.innerHTML = i+'%';
			},20)
	
	}
	
	function getoffset()
	{
		//176
		$("#box").css("margin-left",($("#states").offset().left-196)+"px");
	}

	
	window.onload = window.onresize = function () {
		//计算高度
		var MeHi = $(window).height();
		$("#headPic").css("margin-bottom",MeHi-300);
	}
</script>
    </body>

</html>

