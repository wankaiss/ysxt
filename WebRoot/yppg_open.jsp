<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${path}js/jquery-1.11.0.min.js"></script>
 	<!-- 弹出窗口 -->
	<link rel="stylesheet" type="text/css" href="${path}css/hztj/default.css" />
	<link rel="stylesheet" href="${path}css/hztj/colorbox.css" />
	<script src="${path}js/jquery.colorbox.js"></script>
	<!-- 弹出窗口 -->  
	<link rel="stylesheet" type="text/css" href="${path}css/likeCheck.css" />
	<!-- <script type="text/javascript" src="${path}js/jssearch/jquery.js"></script> -->
	<script type="text/javascript" src="${path}js/jssearch/j.suggest.yppg.js"></script>
		<style>
			html,body {
				overflow:hidden;
				background: #fff;
			}
		</style>
  <script type="text/javascript">
  $(function(){  
      inputTipText();  //初始化Input的灰色提示信息  
  });
    var tymArray;
	var tym_inputRandom;
	var tym_divRandom;
	var tym_hiddenRandom;
	
	var jx_inputRandom;
	var jx_divRandom;
	var jx_hiddenRandom;

	var scqy_inputRandom;
	var scqy_divRandom;
	var scqy_hiddenRandom;

	var ggbz_inputRandom;
	var ggbz_divRandom;
	var ggbz_hiddenRandom;

	var bzdw_inputRandom;
	var bzdw_divRandom;
	var bzdw_hiddenRandom;
	
	$(document).ready(function(){
		var randomTemp=getRandom();
		tym_inputRandom="tym_input"+randomTemp;
		tym_divRandom="tym_div"+randomTemp;
		tym_hiddenRandom="tym_hidden"+randomTemp;

		jx_inputRandom="jx_input"+randomTemp;
		jx_divRandom="jx_div"+randomTemp;
		jx_hiddenRandom="jx_hidden"+randomTemp;

		scqy_inputRandom="scqy_input"+randomTemp;
		scqy_divRandom="scqy_div"+randomTemp;
		scqy_hiddenRandom="scqy_hidden"+randomTemp;

		ggbz_inputRandom="ggbz_input"+randomTemp;
		ggbz_divRandom="ggbz_div"+randomTemp;
		ggbz_hiddenRandom="ggbz_hidden"+randomTemp;

		bzdw_inputRandom="bzdw_input"+randomTemp;
		bzdw_divRandom="bzdw_div"+randomTemp;
		bzdw_hiddenRandom="bzdw_hidden"+randomTemp;
		if($("#yppgbm",window.parent.document).val()!=''){
			tempbm=eval("("+$("#yppgbm",window.parent.document).val()+")");
			$.each(tempbm,function(index,content){
					var randomTemp=getRandom();
						if(tempbm==""){
					$("#area1").append("<tr id=\"cluster"+randomTemp+"\">"
							+"<td><a href=\"javascript:void(0)\" class=\"del\" onclick=\"del('"+randomTemp+"')\"></a></td>"
							+"<td><input type=\"text\" value=\"\" name=\"tym_input\" id=\""+tym_inputRandom+"\"><div id=\""+tym_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+tym_hiddenRandom+"\" value=\"\" name=\"tym_hidden\"/></td>"
							+"<td><input type=\"text\" value=\"\" name=\"jx_input\" id=\""+jx_inputRandom+"\"><div id=\""+jx_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+jx_hiddenRandom+"\" value=\"\" name=\"jx_hidden\"/></td>"
							+"<td><input type=\"text\" value=\"\" name=\"scqy_input\" id=\""+scqy_inputRandom+"\"><div id=\""+scqy_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+scqy_hiddenRandom+"\" value=\"\" name=\"scqy_hidden\"/></td>"
							+"<td><input type=\"text\" value=\"\" name=\"bzdw_input\" id=\""+bzdw_inputRandom+"\"><div id=\""+bzdw_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+bzdw_hiddenRandom+"\" value=\"\" name=\"bzdw_hidden\"/></td>"
							+"<td><input type=\"text\" value=\"\" name=\"ggbz_input\" id=\""+ggbz_inputRandom+"\"><div id=\""+ggbz_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+ggbz_hiddenRandom+"\" value=\"\" name=\"ggbz_hidden\"/></td></tr>"
							+"<script language=\"javascript\">$.ajax({type:\"POST\",url:\"hztj_getYppgAjax.action\",data:{\"status\":\"init\"},dataType:\"json\",success: function(data){var tymArr=new Array();var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();$.each(data,function(i){if(null!=data[i].tymmc){tymArr[i]=new Array(data[i].tymbm,data[i].tymmc,data[i].tympy,data[i].tymszm);}"
							+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
							+"$(\"#"+tym_inputRandom+"\").suggest(tymArr,{hot_list:tymArr,dataContainer:\"#"+tym_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+tym_divRandom+"\"});"
							+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
							+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+scqy_divRandom+"\"});"
							+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+ggbz_divRandom+"\"});"
							+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+bzdw_divRandom+"\"});}});<\/script>"
							+"<script type=\"text/javascript\">function yppgChange(num){$.ajax({"
							+"type:\"POST\",url:\"hztj_getYppgAjax.action\",dataType:\"json\",data:{\"status\":\"change\",\"listNum\":num,\"tymbm\":$(\"#"+tym_hiddenRandom+"\").val(),\"jxbm\":$(\"#"+jx_hiddenRandom+"\").val(),\"scqybm\":$(\"#"+scqy_hiddenRandom+"\").val(),\"ggbz\":$(\"#"+ggbz_hiddenRandom+"\").val()},"
							+"success:function(data){var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();"
							+"$.each(data,function(i){"
							+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}"
							+"if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}"
							+"if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}"
							+"if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
							+"if(num==\"1\"){"
							+"$(\"#"+jx_inputRandom+"\").val(\"剂型名称\").css(\"color\",\"#aaa\");$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
							+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
							+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
							+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
							+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
							+"}"
							+"else if(num==\"2\"){"
							+"$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
							+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
							+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
							+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
							+"}"
							+"else if(num==\"3\"){"
							+"$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
							+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
							+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
							+"}"
							+"else if(num==\"4\"){"
							+"$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
							+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
							+"}"
							+"}});}"
							+"<\/script>");
					}else{
						$("#area1").append("<tr id=\"cluster"+randomTemp+"\">"
								+"<td><a href=\"javascript:void(0)\" class=\"del\" onclick=\"del('"+randomTemp+"')\"></a></td>"
								+"<td><input type=\"text\" value=\""+content.tym+"\" name=\"tym_input\" id=\""+tym_inputRandom+"\"><div id=\""+tym_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+tym_hiddenRandom+"\" value=\""+content.tymbm+"\" name=\"tym_hidden\"/></td>"
								+"<td><input type=\"text\" value=\""+content.jx+"\" name=\"jx_input\" id=\""+jx_inputRandom+"\"><div id=\""+jx_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+jx_hiddenRandom+"\" value=\""+content.jxbm+"\" name=\"jx_hidden\"/></td>"
								+"<td><input type=\"text\" value=\""+content.scqy+"\" name=\"scqy_input\" id=\""+scqy_inputRandom+"\"><div id=\""+scqy_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+scqy_hiddenRandom+"\" value=\""+content.scqybm+"\" name=\"scqy_hidden\"/></td>"
								+"<td><input type=\"text\" value=\""+content.bzdw+"\" name=\"bzdw_input\" id=\""+bzdw_inputRandom+"\"><div id=\""+bzdw_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+bzdw_hiddenRandom+"\" value=\""+content.bzdw+"\" name=\"bzdw_hidden\"/></td>"
								+"<td><input type=\"text\" value=\""+content.ggbz+"\" name=\"ggbz_input\" id=\""+ggbz_inputRandom+"\"><div id=\""+ggbz_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+ggbz_hiddenRandom+"\" value=\""+content.ggbz+"\" name=\"ggbz_hidden\"/></td></tr>"
								+"<script language=\"javascript\">$.ajax({type:\"POST\",url:\"hztj_getYppgAjax.action\",data:{\"status\":\"init\"},dataType:\"json\",success: function(data){var tymArr=new Array();var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();$.each(data,function(i){if(null!=data[i].tymmc){tymArr[i]=new Array(data[i].tymbm,data[i].tymmc,data[i].tympy,data[i].tymszm);}"
								+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
								+"$(\"#"+tym_inputRandom+"\").suggest(tymArr,{hot_list:tymArr,dataContainer:\"#"+tym_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+tym_divRandom+"\"});"
								+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
								+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+scqy_divRandom+"\"});"
								+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+ggbz_divRandom+"\"});"
								+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+bzdw_divRandom+"\"});}});<\/script>"
								+"<script type=\"text/javascript\">function yppgChange(num){$.ajax({"
								+"type:\"POST\",url:\"hztj_getYppgAjax.action\",dataType:\"json\",data:{\"status\":\"change\",\"listNum\":num,\"tymbm\":$(\"#"+tym_hiddenRandom+"\").val(),\"jxbm\":$(\"#"+jx_hiddenRandom+"\").val(),\"scqybm\":$(\"#"+scqy_hiddenRandom+"\").val(),\"ggbz\":$(\"#"+ggbz_hiddenRandom+"\").val()},"
								+"success:function(data){var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();"
								+"$.each(data,function(i){"
								+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}"
								+"if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}"
								+"if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}"
								+"if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
								+"if(num==\"1\"){"
								+"$(\"#"+jx_inputRandom+"\").val(\"剂型名称\").css(\"color\",\"#aaa\");$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
								+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
								+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
								+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
								+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
								+"}"
								+"else if(num==\"2\"){"
								+"$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
								+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
								+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
								+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
								+"}"
								+"else if(num==\"3\"){"
								+"$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
								+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
								+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
								+"}"
								+"else if(num==\"4\"){"
								+"$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
								+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
								+"}"
								+"}});}"
								+"<\/script>");
					}	  				
			});	
		}
		

				
		});
	function inputTipText(){   
	    $("input[tipMsg]").each(function(){  
	        if($(this).val() == ""){  
	            var oldVal=$(this).attr("tipMsg");  
	        if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}  
	        $(this)  
	           .css({"color":"#888"})     //灰色  
	           .focus(function(){  
	            if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}  
	           })  
	           .blur(function(){  
	            if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}  
	           })  
	           .keydown(function(){$(this).css({"color":"#000"})});  
	        }  
	    });  
	}  
	function del(str){
		$("#cluster"+str+"").remove();
	}
	
	function done()
	{
		
		var tyms=document.getElementsByName("tym_input");
		var tymbms=document.getElementsByName("tym_hidden");
		var jxs=document.getElementsByName("jx_input");
		var jxbms=document.getElementsByName("jx_hidden");
		var scqys=document.getElementsByName("scqy_input");
		var scqybms=document.getElementsByName("scqy_hidden");
		var ggbzs=document.getElementsByName("ggbz_input");
		var bzdws=document.getElementsByName("bzdw_input");
		var jsonBmMessage="[";
		var jsonMessage="[";
		
		for(var i=0;i<tyms.length;i++)
			{
				if(i==tyms.length-1)
					{
					jsonMessage+="{"+tyms[i].value+","+jxs[i].value+","+scqys[i].value+","+ggbzs[i].value+","+bzdws[i].value+"}";
					jsonBmMessage+="{tym:\""+tyms[i].value+"\",tymbm:\""+tymbms[i].value+"\",jx:\""+jxs[i].value+"\",jxbm:\""+jxbms[i].value+"\",scqy:\""+scqys[i].value+"\",scqybm:\""+scqybms[i].value+"\",ggbz:\""+ggbzs[i].value+"\",bzdw:\""+bzdws[i].value+"\"}";
					}
				else
					{
					jsonMessage+="{"+tyms[i].value+","+jxs[i].value+","+scqys[i].value+","+ggbzs[i].value+","+bzdws[i].value+"},";
					jsonBmMessage+="{tym:\""+tyms[i].value+"\",tymbm:\""+tymbms[i].value+"\",jx:\""+jxs[i].value+"\",jxbm:\""+jxbms[i].value+"\",scqy:\""+scqys[i].value+"\",scqybm:\""+scqybms[i].value+"\",ggbz:\""+ggbzs[i].value+"\",bzdw:\""+bzdws[i].value+"\"},";
					}
			}
		jsonMessage+="]";
		jsonBmMessage+="]";
		if(tyms.length==0){
			$("#dbIp", window.parent.document).val("");
			$("#yppgbm", window.parent.document).val("");
		}else{
			$("#dbIp", window.parent.document).val(jsonMessage);
			$("#yppgbm", window.parent.document).val(jsonBmMessage);
		}
		window.parent.formClose();
	}
	
	function getRandom(){
        return Math.floor(Math.random()*999+1);
    }

	function add()
	{
		var randomTemp=getRandom();
		tym_inputRandom="tym_input"+randomTemp;
		tym_divRandom="tym_div"+randomTemp;
		tym_hiddenRandom="tym_hidden"+randomTemp;
		
		jx_inputRandom="jx_input"+randomTemp;
		jx_divRandom="jx_div"+randomTemp;
		jx_hiddenRandom="jx_hidden"+randomTemp;

		scqy_inputRandom="scqy_input"+randomTemp;
		scqy_divRandom="scqy_div"+randomTemp;
		scqy_hiddenRandom="scqy_hidden"+randomTemp;

		ggbz_inputRandom="ggbz_input"+randomTemp;
		ggbz_divRandom="ggbz_div"+randomTemp;
		ggbz_hiddenRandom="ggbz_hidden"+randomTemp;

		bzdw_inputRandom="bzdw_input"+randomTemp;
		bzdw_divRandom="bzdw_div"+randomTemp;
		bzdw_hiddenRandom="bzdw_hidden"+randomTemp;
		
		$("#area1").append("<tr id=\"cluster"+randomTemp+"\">"
				+"<td><a href=\"javascript:void(0)\" class=\"del\" onclick=\"del('"+randomTemp+"')\"></a></td>"
				+"<td><input type=\"text\" value=\"\" name=\"tym_input\" id=\""+tym_inputRandom+"\"><div id=\""+tym_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+tym_hiddenRandom+"\" value=\"\" name=\"tym_hidden\"/></td>"
				+"<td><input type=\"text\" value=\"\" name=\"jx_input\" id=\""+jx_inputRandom+"\"><div id=\""+jx_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+jx_hiddenRandom+"\" value=\"\" name=\"jx_hidden\"/></td>"
				+"<td><input type=\"text\" value=\"\" name=\"scqy_input\" id=\""+scqy_inputRandom+"\"><div id=\""+scqy_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+scqy_hiddenRandom+"\" value=\"\" name=\"scqy_hidden\"/></td>"
				+"<td><input type=\"text\" value=\"\" name=\"bzdw_input\" id=\""+bzdw_inputRandom+"\"><div id=\""+bzdw_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+bzdw_hiddenRandom+"\" value=\"\" name=\"bzdw_hidden\"/></td>"
				+"<td><input type=\"text\" value=\"\" name=\"ggbz_input\" id=\""+ggbz_inputRandom+"\"><div id=\""+ggbz_divRandom+"\" class=\"ac_results\"></div><input type=\"hidden\" id=\""+ggbz_hiddenRandom+"\" value=\"\" name=\"ggbz_hidden\"/></td></tr>");
				/*+"<script language=\"javascript\">$.ajax({type:\"POST\",url:\"hztj_getYppgAjax.action\",data:{\"status\":\"init\"},dataType:\"json\",success: function(data){var tymArr=new Array();var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();$.each(data,function(i){if(null!=data[i].tymmc){tymArr[i]=new Array(data[i].tymbm,data[i].tymmc,data[i].tympy,data[i].tymszm);}"
				+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
				+"$(\"#"+tym_inputRandom+"\").suggest(tymArr,{hot_list:tymArr,dataContainer:\"#"+tym_hiddenRandom+"\",onSelect:function(){yppgChange('1');},attachObject:\"#"+tym_divRandom+"\"});"
				+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
				+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
				+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
				+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",onSelect:function(){},attachObject:\"#"+bzdw_divRandom+"\"});}});<\/script>"
				+"<script type=\"text/javascript\">function yppgChange(num){$.ajax({"
				+"type:\"POST\",url:\"hztj_getYppgAjax.action\",dataType:\"json\",data:{\"status\":\"change\",\"listNum\":num,\"tymbm\":$(\"#"+tym_hiddenRandom+"\").val(),\"jxbm\":$(\"#"+jx_hiddenRandom+"\").val(),\"scqybm\":$(\"#"+scqy_hiddenRandom+"\").val(),\"ggbz\":$(\"#"+ggbz_hiddenRandom+"\").val()},"
				+"success:function(data){var jxArr=new Array();var scqyArr=new Array();var ggbzArr=new Array();var bzdwArr=new Array();"
				+"$.each(data,function(i){"
				+"if(null!=data[i].jxmc){jxArr[i]=new Array(data[i].jxbm,data[i].jxmc,data[i].jxpy,data[i].jxszm);}"
				+"if(null!=data[i].scqymc){scqyArr[i]=new Array(data[i].scqybm,data[i].scqymc,data[i].scqypy,data[i].scqyszm);}"
				+"if(null!=data[i].ggbzmc){ggbzArr[i]=new Array(data[i].ggbzbm,data[i].ggbzmc,data[i].ggbzpy,data[i].ggbzszm);}"
				+"if(null!=data[i].bzdwmc){bzdwArr[i]=new Array(data[i].bzdwbm,data[i].bzdwmc,data[i].bzdwpy,data[i].bzdwszm);}});"
				+"if(num==\"1\"){"
				+"$(\"#"+jx_inputRandom+"\").val(\"剂型名称\").css(\"color\",\"#aaa\");$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
				+"$(\"#"+jx_inputRandom+"\").suggest(jxArr,{hot_list:jxArr,dataContainer:\"#"+jx_hiddenRandom+"\",onSelect:function(){yppgChange('2');},attachObject:\"#"+jx_divRandom+"\"});"
				+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
				+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
				+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
				+"}"
				+"else if(num==\"2\"){"
				+"$(\"#"+scqy_inputRandom+"\").val(\"生产企业名称\").css(\"color\",\"#aaa\");$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
				+"$(\"#"+scqy_inputRandom+"\").suggest(scqyArr,{hot_list:scqyArr,dataContainer:\"#"+scqy_hiddenRandom+"\",onSelect:function(){yppgChange('3');},attachObject:\"#"+scqy_divRandom+"\"});"
				+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
				+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
				+"}"
				+"else if(num==\"3\"){"
				+"$(\"#"+ggbz_inputRandom+"\").val(\"规格包装\").css(\"color\",\"#aaa\");$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
				+"$(\"#"+ggbz_inputRandom+"\").suggest(ggbzArr,{hot_list:ggbzArr,dataContainer:\"#"+ggbz_hiddenRandom+"\",onSelect:function(){yppgChange('4');},attachObject:\"#"+ggbz_divRandom+"\"});"
				+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
				+"}"
				+"else if(num==\"4\"){"
				+"$(\"#"+bzdw_inputRandom+"\").val(\"包装单位名称\").css(\"color\",\"#aaa\");"
				+"$(\"#"+bzdw_inputRandom+"\").suggest(bzdwArr,{hot_list:bzdwArr,dataContainer:\"#"+bzdw_hiddenRandom+"\",attachObject:\"#"+bzdw_divRandom+"\"});"
				+"}"
				+"}});}"
				+"<\/script>");*/
				
		getTymmcListInitial();	
	}
	function getTymmcListInitial(){
		if(tymArray!=null){
			$("#"+tym_inputRandom+"").suggest(tymArray,{
				hot_list:tymArray,
				dataContainer:"#"+tym_hiddenRandom+"",
				onSelect:function(){
					var tymbm=$('#'+tym_hiddenRandom+'').val();
					getJxmcListFilterByTymbm(tymbm);
					},
				attachObject:"#"+tym_divRandom+""
				});
		}else{
			$.ajax({
				type:"POST",
				url:"hztj_getTymmcInitialAjax.action",
				dataType:"json",
				success: function(data){
					var tymArr=new Array();
					$.each(data,function(i){
						if(null!=data[i].BM){
							tymArr[i]=new Array(data[i].BM,data[i].MC,data[i].PY,data[i].SZM);
							tymArray=tymArr;
							}
						});
					$("#"+tym_inputRandom+"").suggest(tymArr,{
						hot_list:tymArr,
						dataContainer:"#"+tym_hiddenRandom+"",
						onSelect:function(){
							var tymbm=$('#'+tym_hiddenRandom+'').val();
							getJxmcListFilterByTymbm(tymbm);
							},
						attachObject:"#"+tym_divRandom+""
						});
					}
				});
		}		
	}
	function getJxmcListFilterByTymbm(tymbm){
		$.ajax({
			type:"POST",
			url:"hztj_getJxmcListFilterByTymbmAjax.action",
			data:{
				tymbm:tymbm
				},
			dataType:"text",
			success: function(data){
				if(data!="null"){
				var myData=eval('('+data+')');
				var jxArr=new Array();
				$.each(myData,function(i){
					if(null!=myData[i].BM){
						jxArr[i]=new Array(myData[i].BM,myData[i].MC,myData[i].PY,myData[i].SZM);
						}
					});
				$("#"+jx_inputRandom+"").suggest(jxArr,{
					hot_list:jxArr,
					dataContainer:"#"+jx_hiddenRandom+"",
					onSelect:function(){
						var jxbm=$('#'+jx_hiddenRandom+'').val();
						getScqymcListFilterByJxbm(tymbm,jxbm);
						},
					attachObject:"#"+jx_divRandom+""
					});
				}else{                                    //没有jxbm的情况
					$('#'+jx_inputRandom+'').val('无剂型');
					getScqymcListFilterByJxbm(tymbm,'');
				}
				}
			});
	}

	function getScqymcListFilterByJxbm(tymbm,jxbm){
		$.ajax({
			type:"POST",
			url:"hztj_getScqymcListFilterByJxbmAjax.action",
			data:{
				tymbm:tymbm,
				jxbm:jxbm
				},
			dataType:"json",
			success: function(data){
				var scqyArr=new Array();
				$.each(data,function(i){
					if(null!=data[i].BM){
						scqyArr[i]=new Array(data[i].BM,data[i].MC,data[i].PY,data[i].SZM);
						}
					});
				$("#"+scqy_inputRandom+"").suggest(scqyArr,{
					hot_list:scqyArr,
					dataContainer:"#"+scqy_hiddenRandom+"",
					onSelect:function(){
						var scqybm=$('#'+scqy_hiddenRandom+'').val();
						getBzdwmcListFilterByTymbmJxbmScqybm(tymbm,jxbm,scqybm);
						},
					attachObject:"#"+scqy_divRandom+""
					});
				}
			});
	}

	function getBzdwmcListFilterByTymbmJxbmScqybm(tymbm,jxbm,scqybm){
		$.ajax({
			type:"POST",
			url:"hztj_getBzdwmcListFilterByTymbmJxbmScqybmAjax.action",
			data:{
				tymbm:tymbm,
				jxbm:jxbm,
				scqybm:scqybm
				},
			dataType:"json",
			success: function(data){
				var bzdwmcArr=new Array();
				$.each(data,function(i){
					if(null!=data[i].BM){
						bzdwmcArr[i]=new Array(data[i].BM,data[i].MC,data[i].PY,data[i].SZM);
						}
					});
				$("#"+bzdw_inputRandom+"").suggest(bzdwmcArr,{
					hot_list:bzdwmcArr,
					dataContainer:"#"+bzdw_hiddenRandom+"",
					onSelect:function(){
						var bzdwmc=$('#'+bzdw_hiddenRandom+'').val();
						getGgbzListFilterByTymbmJxbmScqybmBzdwmc(tymbm,jxbm,scqybm,bzdwmc);
						},
					attachObject:"#"+bzdw_divRandom+""
					});
				}
			});
	}
		function getGgbzListFilterByTymbmJxbmScqybmBzdwmc(tymbm,jxbm,scqybm,bzdwmc){
			$.ajax({
				type:"POST",
				url:"hztj_getGgbzListFilterByTymbmJxbmScqybmBzdwmcAjax.action",
				data:{
					tymbm:tymbm,
					jxbm:jxbm,
					scqybm:scqybm,
					bzdwmc:bzdwmc					
					},
				dataType:"json",
				success: function(data){
					var ggbzArr=new Array();
					$.each(data,function(i){
						if(null!=data[i].BM){
							ggbzArr[i]=new Array(data[i].BM,data[i].MC,data[i].PY,data[i].SZM);
							}
						});
					$("#"+ggbz_inputRandom+"").suggest(ggbzArr,{
						hot_list:ggbzArr,
						dataContainer:"#"+ggbz_hiddenRandom+"",
						onSelect:function(){
							
							},
						attachObject:"#"+ggbz_divRandom+""
						});
					}
				});
	}
	</script>

	</head>

	<body>

		<div class="container">
		
		<!-- content -->
			<table class="list" id="area1">
	<tr>
	<td colspan="6">
	
	<a class="add2" href="javascript:void(0)" onclick="add()"></a>
	</td>	
	</tr>
	
	<tr>
	<td>
	操作
	</td>
	<td>
	通用名名称
	</td>	
	<td>
	剂型
	</td>
	<td>
	生产企业名称
	</td>
	<td>
	包装单位名称
	</td>
	<td>
	规格包装
	</td>
		
	</tr>
	
	

	</table>

	<table class="list">
	<tr>
	<td>
	<input type="button" value="确认" onclick="done()">
	</td>
	</tr>
	</table>



		
		
		<!-- content -->
		</div>

<script type="text/javascript">
function getRandom(){
    return Math.floor(Math.random()*999+1);
}

		
		</script>
  </body>
</html>
