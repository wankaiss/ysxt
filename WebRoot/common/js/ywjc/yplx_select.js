$(document).ready(function(){
	if($("#yplxHidden").val()!=""){
		optionsOfYplx();
	}else if($("#yplxHidden").val()==""){
		$("#yplx_select").one('click',function (){optionsOfYplx();});
	}
});
function optionsOfYplx(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getYplxOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#yplxHidden").val()!=""&&data[i].yplxbm==$("#yplxHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].yplxbm+'" selected="selected">'+data[i].yplxmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].yplxbm+'">'+data[i].yplxmc+'</option>';
					}
				});
				$("#yplx_select").append(htmlText);
		  }
	});	
}