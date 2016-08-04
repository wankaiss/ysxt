<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上海市医药采购服务与监管信息系统</title>
		<link href="${path}/css/ywjc/reset.min.css" rel="stylesheet"
			type="text/css" />
		<link href="${path}/css/ywjc/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/ywjc/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tab.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.11.0.min.js"></script>

		
		<script type="text/javascript">

	  $(document).ready(function() {

		window.parent.frames["cgdjjcMain"].location="ywjc_cgdjjc.action";

        $("#tabs a").bind("click",function(e) {
            e.preventDefault();
            if ($(this).attr("id") == "current"){ //detection for current tab
              return false;  
            }
            else{             
	            $(this).parent().siblings().find("a").attr("id","");
	            $(this).attr("id","current"); // Activate this
            }
        });

    });

   function changeAction(str1,str2)
   {
	   window.parent.frames["cgdjjcMain"].location.href=str2;
   }
	  
    
  </script>
	</head>
    
	<body>
		<div class="mainBox">
			  <ul id="tabs">
			      <li><a href="javascript:void(0);" id="current" name="#tab1"  onclick="changeAction('1','ywjc_cgdjjc.action')">采购单价监测</a></li>
			  </ul>
		</div>
	</body>
	
</html>
