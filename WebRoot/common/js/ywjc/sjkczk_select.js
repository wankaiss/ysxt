$(document).ready(function(){
	if($("#sjkczkHidden").val()!=""){
		optionsOfSjkczk();
	}else if($("#sjkczkHidden").val()==""){
		$("#sjkczk_select").one('click',function (){optionsOfSjkczk();});
	}
});
function optionsOfSjkczk(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getSjckzkOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#sjkczkHidden").val()!=""&&data[i].sjkczkbm==$("#sjkczkHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].sjkczkbm+'" selected="selected">'+data[i].sjkczkmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].sjkczkbm+'">'+data[i].sjkczkmc+'</option>';
					}
				});
				$("#sjkczk_select").append(htmlText);
		  }
	});	
}