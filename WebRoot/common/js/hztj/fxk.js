$(document).ready(function(){
	
	if($("#sjkjReturn").val()==""){
		init_system_date('4');
		$("#btnyear").attr("class","btnBlue");
	}
	
	var value =$("#sjkjReturn").val();
	if($("#sjkjTypeReturn").val()==4){
		init_system_date_overload('4');
		$("#btnyear").attr("class","btnBlue");
		$("#startDate").val(value+"年");
	}else if($("#sjkjTypeReturn").val()==3){
		$("#btnseason").attr("class","btnBlue");
		var year = value.substring(0,4);
		var jd = value.substring(4,6);
		init_system_date_overload('3');
		$("#dateStart_jd option[value='"+year+"/"+jd+"']").attr('selected',true);
		
	}else if($("#sjkjTypeReturn").val()==2){
		init_system_date_overload('2');
		$("#btnmonth").attr("class","btnBlue");
		var year = value.substring(0,4);
		var month = value.substring(4,6);
		$("#startDate").val(year+"年"+month+"月");
		
	}
	
	$("#sjkj").attr("value",date.startDate);
		
	changeClass();
	
});


function changeClass(){
	$('input#btnseason').bind('click',function(){
		if($(this).hasClass('btnGray')){
				$(this).removeClass('btnGray').addClass('btnBlue');
				if($('#btnyear').hasClass('btnBlue')){
						$('#btnyear').removeClass('btnBlue').addClass('btnGray');
					}
				if($('#btnmonth').hasClass('btnBlue')){
						$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
					}
			}
	});
	
	$('input#btnyear').bind('click',function(){
		if($(this).hasClass('btnGray')){
				$(this).removeClass('btnGray').addClass('btnBlue');
				if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
				if($('#btnmonth').hasClass('btnBlue')){
						$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
					}
			}
	});
	
	$('input#btnmonth').bind('click',function(){
		if($(this).hasClass('btnGray')){
				$(this).removeClass('btnGray').addClass('btnBlue');
				if($('#btnyear').hasClass('btnBlue')){
						$('#btnyear').removeClass('btnBlue').addClass('btnGray');
					}
				if($('#btnseason').hasClass('btnBlue')){
						$('#btnseason').removeClass('btnBlue').addClass('btnGray');
					}
			}
	});
}