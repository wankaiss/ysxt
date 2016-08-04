<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>宁波市综合分析系统</title>
     <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="${path}css/reset.min.css" rel="stylesheet" type="text/css" />
    <link href="${path}css/public.css" rel="stylesheet" type="text/css" />
    <link href="${path}css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${path}js/jquery-1.10.0.min.js"></script>
    <!--<script type="text/javascript" src="../js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="../js/js.js"></script>
    <script type="text/javascript" src="../js/jquery.nicescroll.min.js"></script>-->
    <!-- 弹出框 -->
    <script type="text/javascript" src="${path}js/dialog/script.js"></script>
    <script type="text/javascript">
			
			
			function optionView(){
				var dialog = $.dialog({
					id: 'favourID',
					title:"我的收藏夹",
					fixed:true,
					width: 900,
				    height: 580,
					min:false,
					max:false,
					drag: false,
					resize:false,
					content:'url:favour_gotoMaintainFavourList.action'
				});
			}
			
			
			
			$(document).ready(function(){
				
				$("#Nav").find('a').get(0).click();
		
			});
			

			

		</script>
		<style type="text/css">
			.infobar_menu{height:32px;margin-right:64px; background-color:#233441;position:absolute;right:5px;display:none;border:1px solid #2A4053;border-top:2px;}
			.infobar_menu div{font-size:12px;color:#97a6b2;}
		</style>
		
	
</head>
<body>
<div class="headwarp">
    <h1 class="logotext fL">宁波市综合分析系统</h1>
    <div class="fR headR">
    <!-- 
    <span>上次登录时间：2013-03-12</span>
    	你好，${sessionScope.USERMESSAGE.uapUser.name }！
     -->
    <span id="userinfo" class="ico_welcome" style="cursor: pointer;">你好，<%=session.getAttribute("login_user")%>！</span>
    	
    <span class="ico_exit"><a href="index_logout.action">退出</a></span>
    </div>
</div>
<div class="menuwarp">
    <div class="sublogo"><img src="${path}images/public/sublogo.gif" alt="" /></div>
    <ul id="Nav" class="menuNav">
        <li class="curSub"><a href="hztj_gotoHztjIndex.action" target="indexContent">汇总统计</a></li>
    	<%--
        <li><a href="structure_indexContent.action" target="indexContent">首页</a></li>
        <li><a href="ywjc_gotoYwjcIndex.action" target="indexContent">业务监测</a></li>
        --%>
    </ul>
</div>
<script language="javascript">

    $("#Nav").find('a').click(function(e){
        var hrefs=$(this).attr('href');
        if(hrefs!=""&&hrefs!=undefined&&hrefs!="javascript:void(0);"&&hrefs.charAt(1)!='#'){
            $($(this)).parents('').addClass('curSub').siblings().removeClass('curSub');
        };
    });

</script>


</body>
</html>