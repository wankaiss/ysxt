$(document).ready(function(){
	if($("#ghyqHidden").val()!=""){
		optionsOfGhyq();
	}else if($("#ghyqHidden").val()==""){
		$("#ghyq_select").one('click',function (){optionsOfGhyq();});
	}
});
function optionsOfGhyq(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getGhyqOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#ghyqHidden").val()!=""&&data[i].yqbm==$("#ghyqHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].yqbm+'" selected="selected">'+data[i].yqmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].yqbm+'">'+data[i].yqmc+'</option>';
					}
				});
				$("#ghyq_select").append(htmlText);
		  }
	});	
}