$(document).ready(function(){
	if($("#jgxzHidden").val()!=""){
		optionsOfJgxz();
	}else if($("#jgxzHidden").val()==""){
		$("#jgxz_select").one('click',function (){optionsOfJgxz();});
	}
});
function optionsOfJgxz(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getJgxzOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#jgxzHidden").val()!=""&&data[i].jgxzbm==$("#jgxzHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].jgxzbm+'" selected="selected">'+data[i].jgxzmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].jgxzbm+'">'+data[i].jgxzmc+'</option>';
					}
				});
				$("#jgxz_select").append(htmlText);
		  }
	});	
}