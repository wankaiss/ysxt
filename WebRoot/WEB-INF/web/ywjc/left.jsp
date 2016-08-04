<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ taglib uri="/finder-tags" prefix="tjjc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海市医药采购服务与监管信息系统</title>
<link href="${path}/css/reset.min.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/public.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.10.0.min.js"></script>
<!-- leftnavgiate -->
<script type="text/javascript" src="${path}/js/leftnavgiate/jquery.min.js"></script>
<!--[if lte IE 10]><script src="${path}/js/leftnavgiate/selectivizr.js"></script><![endif]-->
<link href="${path}/css/leftnavgiate/leftnav.css" rel="stylesheet" type="text/css" />
<!-- leftnavgiate -->

<script type="text/javascript" src="${path}/js/jquery.nicescroll.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#menuDiv").find('a').get(0).click();
});

</script>

</head>
<body>

	<div id="menuDiv" class="leftwarp mainHi winscroll">
	<dl class="lanmu-list">
      <dd>
      	<a href="ywjc_gotoCgdjjc.action" target="ywjcMain"><tjjc:substring length="6" value="采购单价监测" append="..."></tjjc:substring></a>
      </dd>
      <dd class="current"><a href="task_list.action" target="ywjcMain">监测指标预设</a></dd>
				
	</dl>
	</div>

<script type="text/javascript">
	$("#menuDiv").find('a').click(function(e){
        var hrefs=$(this).attr('href');
        if(hrefs!=""&&hrefs!=undefined&&hrefs!="javascript:void(0);"&&hrefs.charAt(1)!='#'){
            $($(this)).parents('').addClass('current').siblings().removeClass('current');
        };
    });
</script>
</body>
</html>
