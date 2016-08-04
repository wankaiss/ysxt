/*********************************************************************************************/
/*********************************全局工具方法*************************************************/
/*********************************************************************************************/
/**
 * 请求fusionchart报表,统一控制AJAX参数.
 * @param chartName		报表flash名称
 * @param chartData		xml数据
 * @param chartId		报表id
 * @param renderDiv		显示报表的div
 */
function retrieveData(url, param,callback) {
	$.post(url, param, callback, 'json');
}

/**
 * 生成fusionchart报表
 * @param chartName		报表flash名称
 * @param chartData		xml数据
 * @param chartId		报表id
 * 		在处理的时候增加了一段随机数,防止快速点击后Fusionchart内存中生成重复id的报表.
 * @param renderDiv		显示报表的div
 */
function drawChart(chartName, chartData, chartId, renderDiv) {
	chartData = chartData.replaceAll("JavaScript:check_excel_date(this)","JavaScript:basecheck_excel_date(\""+renderDiv+"\")");
	if(chartData!=null){
		var chart = new FusionCharts(resourceCharts + "/resource/Charts/"
				+ chartName + ".swf", chartId+getRandomNumber(), "100%", "100%", "0", "1");
		chart.setXMLData(chartData);
		chart.render(renderDiv);
	}
}

//没有数据时出发此函数
function FC_NoDataToDisplay(DOMId){
	var chart=FusionCharts(DOMId);
	var divname=$("#"+DOMId).parent().attr("id");
	$("#"+divname).html("<img src='"+resourceCharts+"/imgaes/no_data_display.jpg'" +
			" width='"+chart.width+"px' height='"+chart.height+"px'/>");
}

/*****************************************************************************/
/************************************String方法start*****************************/
/**
 * 字符串替换,过滤了一些特殊字符
 * @param s1	原始字符串
 * @param s2	需要替换的字符串
 * @returns		{String}
 */
String.prototype.replaceAll = function(s1, s2) {
	var r = new RegExp(s1.replace(/([\(\)\[\]\{\}\^\$\+\-\*\?\.\"\'\|\/\\])/g,
			"\\$1"), "ig");
	return this.replace(r, s2);
};

/**
 * 去掉字符串两端的空格
 * @returns
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 去掉字符串左端的空格
 * @returns
 */
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};

/**
 * 去掉字符串右端的空格
 * @returns
 */
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};
/************************************String方法end******************************/
/*****************************************************************************/



/*****************************************************************************/
/************************************css方法start*****************************/
/**
 * 隐藏div块
 * @param id	div的id
 */
function hideDiv(id) {
	if ($("#" + id) != null) {
		$("#" + id).css('display', 'none');
	}
}
/**
 * 显示div块
 * @param id	div的id
 */
function showDiv(id) {
	if ($("#" + id) != null) {
		$("#" + id).css('display', 'block');
	}
}
/**
 * 添加css样式表
 * @param id		对象id
 * @param value		css样式表
 */
function addCss(id, value) {
	if ($("#" + id) != null) {
		$("#" + id).attr("class", value);
	}
}
/**
 * 禁用对象
 * @param id	对象id
 */
function disableView(id) {
	if ($("#" + id) != null) {
		$("#" + id).attr("disabled", "disabled");
	}
}
/**
 * 恢复对象
 * @param id	对象id
 */
function eisableView(id) {
	if ($("#" + id) != null) {
		$("#" + id).attr("disabled", "");
	}
}
/************************************css方法end******************************/
/*****************************************************************************/




/*****************************************************************************/
/************************************日期方法start*****************************/
/**
 * 格式化日期
 * @param format 日期格式,如 yyyy-MM-dd
 * @returns	{String}
 */
Date.prototype.format = function(format) // author: meizz
{ 
  var o = { 
    "M+" : this.getMonth()+1, // month
    "d+" : this.getDate(),    // day
    "h+" : this.getHours(),   // hour
    "m+" : this.getMinutes(), // minute
    "s+" : this.getSeconds(), // second
    "q+" : Math.floor((this.getMonth()+3)/3),  // quarter
    "S" : this.getMilliseconds() // millisecond
  }; 
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
    format = format.replace(RegExp.$1, 
      RegExp.$1.length==1 ? o[k] : 
        ("00"+ o[k]).substr((""+ o[k]).length)); 
  return format; 
}; 

/**
 * 根据指定格式解析日期
 * @param date		日期字符串
 * @param format	格式字符串
 * @returns {Date}
 */
function parseDate(date,format){// author: xukj
	var result=new Date();
	if(/(y+)/.test(format))
		result.setFullYear(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	if(/(M+)/.test(format))
		result.setMonth(parseInt(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length),10)-1);
	if(/(d+)/.test(format))
		result.setDate(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	if(/(h+)/.test(format))
		result.setHours(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	if(/(m+)/.test(format))
		result.setMinutes(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	if(/(s+)/.test(format))
		result.setSeconds(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	if(/(S+)/.test(format))
		result.setMilliseconds(date.substring(format.indexOf(RegExp.$1),format.indexOf(RegExp.$1)+RegExp.$1.length));
	return result;
}

/**
 * 日期增加月份数
 * @param date	日期,{Date}类型
 * @param amount	增加的月份数,数值类型
 * @returns {Date}
 */
Date.prototype.addMonth=function(amount){
	this.setMonth(this.getMonth()+amount);
	return this;
}

/**
 * 取得月份的最大天数
 * @param year		年份
 * @param month		月份
 * @returns	{Number}
 */
function getMaxDay(year,month){
	return new Date(year+"/"+(parseInt(month,10)+1)+"/0").getDate();
}
/************************************日期方法end******************************/
/*****************************************************************************/


/**//*
功能：修改 window.setTimeout，使之可以传递参数和对象参数
使用方法： setTimeout(回调函数,时间,参数1,,参数n)
*/
var __sto = setTimeout;
window.setTimeoutNew = function(callback,timeout,param){
	var args = Array.prototype.slice.call(arguments,2);
	var _cb = function()
	{
	    callback.apply(null,args);
	};
	__sto(_cb,timeout);
};
   


/**
 * 简单封装,使用json格式的数据填充表格.
 * @param tableid			表格id
 * @param fromRowIndex		起始行,为了保留表头
 * @param data				数据
 */ 
function filled_table_with_json(tableid,data){
	if(data==null){
		return;
	}
	var table=$("#"+tableid);
	var oldtr = $("#"+tableid+" tr").eq(2);	//第一行标题,第二行表头,第三行数据,以第三行为模板
	$("#"+tableid+" tr:gt(2)").each(function(){//删除所有的数据行
		$(this).remove();
	});
	$.each(data, function(k, v) {//迭代数据
		var newtr=oldtr.clone(),i=0;	//拷贝数据行
	    $.each(v,function(kk, vv) {
	    	if(vv==null||vv==0||vv=='0'){
	    		vv="-";
	    	}
	    	if(i==0){//第一列加粗
	    		(newtr.find("td")[i++])["innerHTML"]="<span>"+vv+"</span>";
	    	}else{
	    		(newtr.find("td")[i++])["innerHTML"]=vv;
	    	}
	    });
	    table.append(newtr);
	});
	oldtr.remove();
}

/***********************************************************************************/
/************************************cookie操作方法start*****************************/
function getCookiePath(){
	var href=document.location.href;
	return href.substring(href.indexOf(resourceCharts)+
			resourceCharts.length).replaceAll("/","").replaceAll("#","");
}

//设置cookie
function setCookie(cookieName,cvalue,expiredays,path)
{
	var expireDate=new Date();
	var expireStr="";
	if(expiredays!=null) {
		expireDate.setTime(expireDate.getTime()+(expiredays*24*3600*1000));
		expireStr="; expires="+expireDate.toGMTString();
	}
	pathStr=(path==null)?"; path=/":"; path="+path;
	document.cookie=cookieName+'='+escape(cvalue)+expireStr+pathStr;
	
}

//取得cookie	
function getCookie(cookieName)
{
	var index=-1;
 	if(document.cookie) 
 		index=document.cookie.indexOf(cookieName);
 	if(index==-1) {
 		return "";
 	} else {
 	     var iBegin = (document.cookie.indexOf("=", index) +1);
 	     if(iBegin==0)
 	    	 return "";
          var iEnd =document.cookie.indexOf(";", index);
          if (iEnd == -1)
          {
              iEnd = document.cookie.length;
          }
          return unescape(document.cookie.substring(iBegin,iEnd));
	}
} 

function delCookie(name){//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
   var date = new Date();
   date.setTime(date.getTime() - 10000);
   document.cookie = name + "=a; expires=" + date.toGMTString();
}
/************************************cookie操作方法end******************************/
/*****************************************************************************/

/*****************************************************************************/
/************************************easyui方法start*****************************/
function easyui_window(divid,title,width,height){
	showDiv(divid);
	$('#'+divid).window( {
		title : title,
		width : width,
		height : height,
		modal : true,
		shadow : false,		
		top : ($(window).height()) * 0.1,
		left : ($(window).width()) * 0.03,
		closed : false,
		closable : true,
		collapsible : false,
		minimizable : false,
		maximizable : false
	});
}

function easyui_tabs(divid,width,height,callback){
	$('#'+divid).tabs({
		width:width,
		height:height,
		border:false,
		tools:[{
			id:'btnsave',
			text:'导出',
			disabled:false,
			iconCls:'icon-save',
			handler:callback
		}]
	});
}
/************************************easyui方法end******************************/
/*****************************************************************************/
/**
 * 
 */
function convertParameterToString(parameter){
	var result="";
	for(var property in parameter){	
		if(parameter[property]!=null&&parameter[property].trim()!=""){
			result+="&"+property+"="+parameter[property];
		}else{
			result+="&"+property+"=";
		}
	}
	if(result!=""&&result.trim().length>1){
		return result.substring(1, result.length);
	}
	return result;
}

function getRandomNumber(){
	return Math.round(Math.random()*1000);
}






function basecheck_excel_date(renderDiv){
	try {
		check_excel_date(renderDiv);
	} catch (e) {
		parameterlocad.divId = renderDiv;
		var datagridDiv = renderDiv+getRandomNumber();	
		loadExcelDatagrid_data(datagridDiv);
	}
	
}

function loadExcelDatagrid_data(datagrid){
	retrieveData(resourceURL+"viewExcelDatagrid.json",parameterlocad,function(response){
		
		var datagridDataString = "";
		datagridDataString +=" <thead><tr>";
		var fields = response.baseExcelDataModel.fields;
		for ( var i = 0; i < fields.length; i++) {
			var field = fields[i].split(","); 
			datagridDataString += "<th field='"+field[0]+"' width='100'>"+field[1]+"</th>";
		}
		datagridDataString +="</tr></thead><tbody> ";
		var rows = response.baseExcelDataModel.data.rows;
	
		for ( var i = 0; i < rows.length; i++) {
			datagridDataString +=" <tr> ";
			var row = rows[i];
			for ( var j = 0; j < fields.length; j++) {
				var field = fields[j].split(","); 
				datagridDataString += "<td>"+eval("row."+field[0]);+"</td> ";
			}
			datagridDataString +=" </tr> ";
		}	
		datagridDataString +=" </tbody>";
		
		$('#excelDatagridDiv').html('<div id="'+datagrid+'"><table id="'+datagrid+'Datagrid" >'+datagridDataString+'</table></div>');
		
		$('#'+datagrid).window({
			title : response.baseExcelDataModel.excelSheetTitle,
			height:loadheight*0.5,
			width:loadwidth*0.4,
			modal : true,
			shadow : false,
			closed : false,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			height : 300		
		});	
		$('#'+datagrid+'Datagrid').datagrid({
			rownumbers:true,
			border:false,
			toolbar:[{
				id:'btnadd',
				text:'导出',
				iconCls:'icon-save',
				handler:function(){
					getExcel(response.baseExcelDataModel);
				}
			}]
		});
	});
}

function getExcel(baseExcelDataModel){
	location.href=resourceURL+'getExcel?jsonString='+JSON.stringify(baseExcelDataModel);  
	//window.open(resourceURL+'getExcel?jsonString='+JSON.stringify(baseExcelDataModel),"_blank",",","");
}

function changePlusChar(str){
	   var reg = new RegExp("//+","gi");
	   return str.replace(reg,"%2B");
}

function changeTableColor(){
	 $(".TabCx tbody tr").each(function(i) {  
         var className = ["odd", "even"][i % 2];  
         $(this).addClass(className); 	          
	});
}
