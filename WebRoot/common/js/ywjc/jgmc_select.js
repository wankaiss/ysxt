$(document).ready(function(){
	if($("#jgmc_hidden").val()!=""){
		optionsOfJgmc();
	}else if($("#jgmc_hidden").val()==""){
		$("#jgmc_select").one('click',function (){optionsOfJgmc();});
	}
});
function optionsOfJgmc(){
	var htmlText = "";
	var jgxz=$('input#jgxzHidden').val();
	var jgjb=$('input#jgjbHidden').val();
	$.ajax({
		type: "POST",
		url: "ywjc_getJgmcOptions.action",
		data:{"jgjb":jgjb,"jgxz":jgxz},
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#jgmc_hidden").val()!=""&&data[i].jgbm==$("#jgmc_hidden").val()){
						htmlText = htmlText + '<option value="'+data[i].jgbm+'" selected="selected">'+data[i].jgmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].jgbm+'">'+data[i].jgmc+'</option>';
					}
				});
				$("#jgmc_select").append(htmlText);
		  }
	});	
}