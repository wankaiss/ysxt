$(document).ready(function(){
	if($("#jgjbHidden").val()!=""){
		optionsOfJgjb();
	}else if($("#jgjbHidden").val()==""){
		$("#jgjb_select").one('click',function (){optionsOfJgjb();});
	}
});
function optionsOfJgjb(){
	var htmlText = "";
	$.ajax({
		type: "POST",
		url: "ywjc_getJgjbOptions.action",
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#jgjbHidden").val()!=""&&data[i].jgjbbm==$("#jgjbHidden").val()){
						htmlText = htmlText + '<option value="'+data[i].jgjbbm+'" selected="selected">'+data[i].jgjbmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].jgjbbm+'">'+data[i].jgjbmc+'</option>';
					}
				});
				$("#jgjb_select").append(htmlText);
		  }
	});	
}