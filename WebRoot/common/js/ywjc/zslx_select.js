$(document).ready(function(){
	if($("#zslxHidden").val()!=""){
		optionsOfZslx();
	}else if($("#zslxHidden").val()==""){
		$("#zslx_select").one('click',function (){optionsOfZslx();});
	}
});
function optionsOfZslx(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getZslxOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#zslxHidden").val()!=""&&data[i].zslxbm==$("#zslxHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].zslxbm+'" selected="selected">'+data[i].zslxmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].zslxbm+'">'+data[i].zslxmc+'</option>';
					}
				});
				$("#zslx_select").append(htmlText);
		  }
	});	
}