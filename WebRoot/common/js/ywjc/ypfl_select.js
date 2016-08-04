$(document).ready(function(){
	if($("#rsypfl_1_hidden").val()!=""){
		optionsOfYpfl();
	}else if($("#rsypfl_1_hidden").val()==""){
		$("#rsypfl_1").one('click',function (){optionsOfYpfl();});
	}
});
function optionsOfYpfl(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getYpflOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#rsypfl_1_hidden").val()!=""&&data[i].flbm==$("#rsypfl_1_hidden").val()){
						htmlText = htmlText + '<option value="'+data[i].flbm+'" selected="selected">'+data[i].flmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].flbm+'">'+data[i].flmc+'</option>';
					}
				});
				$("#rsypfl_1").append(htmlText);
		  }
	});	
}