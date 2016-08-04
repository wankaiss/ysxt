$(document).ready(function(){
	if($("#cgmsHidden").val()!=""){
		optionsOfCgms();
	}else if($("#cgmsHidden").val()==""){
		$("#cgms_select").one('click',function (){optionsOfCgms();});
	}
});
function optionsOfCgms(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getCgmsOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#cgmsHidden").val()!=""&&data[i].cgmsbm==$("#cgmsHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].cgmsbm+'" selected="selected">'+data[i].cgmsmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].cgmsbm+'">'+data[i].cgmsmc+'</option>';
					}
				});
				$("#cgms_select").append(htmlText);
		  }
	});	
}