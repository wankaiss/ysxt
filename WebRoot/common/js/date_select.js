/**
 * 系统默认日期格式
 */
var VIEW_WEEK_FORMAT="yyyy年第MM季度";		//显示格式
var VIEW_YEAR_FORMAT="yyyy年";		//显示格式
var VIEW_MONTH_FORMAT="yyyy年MM月";		//显示格式
var VIEW_DAY_FORMAT="yyyy年MM月dd日";		//显示格式
var VIEW_TIME_FORMAT="hh:mm:ss";		//显示格式

var real_day_format="yyyyMMdd";			//实际格式
var REAL_MONTH_FORMAT="yyyyMM";			//实际格式
var REAL_WEEK_FORMAT="yyyyMM";			//实际格式
var REAL_YEAR_FORMAT="yyyy";			//实际格式
var MONT_SUB_AMOUNT=0;				//默认初始化显示上一个月的数据;

/**
 * 日期对象
 * @returns {SelectDate}
 */
SelectDate=function(){
	this.startDate;	//起始时间
	this.endDate;	//结束时间
	this.type; //展示方式
};


var date=new SelectDate();	//日期结果

var month=new Date().addMonth(MONT_SUB_AMOUNT);


//页面加载时初始化
function init_system_date(displayType){

	date.type=displayType;
	//默认选中
	if (displayType == "4"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		WdatePickerSjLoad('4');
	} else if (displayType == "3"){
		
		hideDiv("startDate");
		showDiv("dateStart_jd");
		
		WdatePickerSjLoad('3');

	} else if (displayType == "2"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		
		WdatePickerSjLoad('2');
	} else if (displayType == "1"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		
		WdatePickerSjLoad('1');
	}
}
//重新加载
function init_system_date_overload(displayType){
	date.type=displayType;
	if (displayType == "4"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		WdatePickerSjLoad('4');
		select_View_Date_OverLoad();
	} else if (displayType == "3"){
		
		hideDiv("startDate");
		showDiv("dateStart_jd");
		
		WdatePickerSjLoad('3');
		select_View_Date_OverLoad();
	} else if (displayType == "2"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		
		WdatePickerSjLoad('2');
		select_View_Date_OverLoad();
	} else if (displayType == "1"){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		WdatePickerSjLoad('1');
		select_View_Date_OverLoad();
	} 
}
function select_View_Date_OverLoad(){
		var startDate00=$("#sjkjReturn").val();
		date.startDate=startDate00;
}

function WdatePickerSjLoad(type){
	
	date.type=type;
	$('#startDate').unbind("click");
	if (type == '2'){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		setSjDate(month,month,"month");
		$("#startDate").click(function() {
			WdatePicker({dateFmt:'yyyy年MM月',isShowClear:false,readOnly:true});
		});
		$("#endDate").click(function() {
			WdatePicker({dateFmt:'yyyy年MM月',isShowClear:false,readOnly:true});
		});
	}
	if (type == '3'){
		hideDiv("startDate");
		showDiv("dateStart_jd");
		setSjDate(month,month,"week");
	} 
	if (type == '4'){
		hideDiv("dateStart_jd");
		showDiv("startDate");
		setSjDate(month,month,"year");
		$("#startDate").click(function() {
			WdatePicker({dateFmt:'yyyy年',isShowClear:false,readOnly:true});
		});
	}
	if (type == '1'){

		hideDiv("dateStart_jd");
		showDiv("startDate");
		setSjDate(month,month,"ybyear");
		$("#startDate").click(function() {
			WdatePicker({dateFmt:'yyyy年',isShowClear:false,readOnly:true});
		});
	}
	try {
		load();
	} catch (e) {
	}
	
	init_select_date();
}

//时间选择,按月份
function init_select_date(){
	select_View_Date();
	//data_query();
}

function select_View_Date(){
	if ($("#btnzryear").hasClass("btnBlue")){
		var startDate00 = $("#startDate").val();
		date.startDate = startDate00.replace("年","");
		date.type="4";
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
	
	if ($("#btnybyear").hasClass("btnBlue")){
		var startDate00 = $("#startDate").val();
		date.startDate = startDate00.replace("年","");
		date.type="1";
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
	
	if ($("#btnmonth").hasClass("btnBlue")){
		var startDate00 = $("#startDate").val();
		date.startDate = startDate00.replace("年","").replace("月","");
		date.type="2";
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
	
	if ($("#btnseason").hasClass("btnBlue")){
		selectDateOnChange();
		date.type="3";
	}
}

/**
 * set方法,更新日期
 * @param startDate	起始时间,Date类型
 * @param endDate	结束时间,Date类型
 * @param type		日期展示方式
 */
function setSjDate(start,end,type){
	if (type == 'year'){
		$("#startDate").val(start.format(VIEW_YEAR_FORMAT));
		date.startDate = start.format(REAL_YEAR_FORMAT);
		date.type = '4';	
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
	if (type == 'ybyear'){
		$("#startDate").val(start.format(VIEW_YEAR_FORMAT));
		date.startDate = start.format(REAL_YEAR_FORMAT);
		date.type = '1';	
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
	if (type == 'week'){		
		date.type = '3';
		initSelectQuarterDate(2008, getInitNYYear(1), 01, "dateStart_jd");
		selectDateOnChange();
	}
	if (type == 'month'){
		$("#startDate").val(start.format(VIEW_MONTH_FORMAT));
		$("#endDate").val(start.format(VIEW_MONTH_FORMAT));
		date.startDate = start.format(REAL_MONTH_FORMAT);
		date.type = '2';
		$("#sjkj").val(date.startDate);
		$("#sjkj_type").val(date.type);
	}
}

function getloadWeek(){
	var d1 = new Date();
	var d2 = new Date();
	d2.setMonth(0);
	d2.setDate(1);
	var rq = d1-d2;
	var s1 = Math.ceil(rq/(24*60*60*1000));
	var s2 = Math.ceil(s1/7);
	return s2;
}

function selectDateOnChange(){
	var valString = $('#dateStart_jd').find("option:selected").text();
	valString = replace_jd(valString);
	date.startDate = valString;
	$("#sjkj").val(date.startDate);
	$("#sjkj_type").val("3");
}

function initSelectQuarterDate(stratYear, endYear, month, selectId) {
	var lastYear=endYear;
	var sj=new Date();
	var jd=0;

	if(sj.getMonth()<3){//modify by xukaijin
		//endYear=parseInt(endYear,10)-1;
		jd=1;
	}
	else if(sj.getMonth()>=3&&sj.getMonth()<6){
		jd=2;
	}
	else if(sj.getMonth()>=6&&sj.getMonth()<9){
		jd=3;
	}
	else if(sj.getMonth()>=9&&sj.getMonth()<12){
		jd=4;
	}
	
	
	$("#" + selectId).empty(); //清空省份SELECT控件
	for ( var i = stratYear; i <= endYear; i++) {
		$("<optgroup id=" + i + selectId + "></optgroup>").attr("label",
				i + "年").appendTo($("#" + selectId));

		for ( var j = 1; j <= 4; j++) {
			var dateDay = j;
			if (j < 10) {
				dateDay = "0" + j
			}
			
		    if(i==endYear&&j>jd&&jd!=0){
		    	
		    }
		    else{
		    	if((i==endYear)&&(j==jd)){
		    		$("<option></option>").val(i + "/" + dateDay).text(
							i + "年第" + dateDay + "季度").attr("selected", "selected").appendTo(
							$("#" + i + selectId));
		    	}
		    	else{
				$("<option></option>").val(i + "/" + dateDay).text(
						i + "年第" + dateDay + "季度").appendTo(
						$("#" + i + selectId));
		    	}
		    }
		}

	}
}

function replace_jd(dateValue) {
	dateValue = dateValue.replace("年第01季度", "01");
	dateValue = dateValue.replace("年第02季度", "02");
	dateValue = dateValue.replace("年第03季度", "03");
	dateValue = dateValue.replace("年第04季度", "04");
	dateValue = dateValue.replace("/", "");
	return dateValue;
}

function getInitNYYear(amount) {
	//默认为1
	if(arguments.length == 0){
		amount=1;
	}
	var d = new Date();
	return String(d.getFullYear());
}

function hideDiv(id) {
	if ($("#" + id) != null) {
		$("#" + id).css('display', 'none');
	}
}
function showDiv(id) {
	if ($("#" + id) != null) {
		$("#" + id).css('display', 'inline');
	}
}
