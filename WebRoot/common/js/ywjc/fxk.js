$(document).ready(function(){
	$(".check_box").checkbox();
	
	if($("#ypfl_strong").length!=0){
		rsypflInit();
	}
	$('#showButton').bind('click',function(){
		show();
	});
});


//人事药品分类初始化
function rsypflInit(){
				if($("#rsypfl_2_hidden").val()!=""){
					rsypflChange($("#rsypfl_1_hidden").val(),'1');
				}
				if($("#rsypfl_3_hidden").val()!=""){
					rsypflChange($("#rsypfl_2_hidden").val(),'2');
				}
				if($("#rsypfl_4_hidden").val()!=""){
					rsypflChange($("#rsypfl_3_hidden").val(),'3');
				}
}
//人事药品分类 change
function rsypflChange(value,num){
	var htmlText="";
	var n = parseInt(num)+1;
	for(var i=n;i<=4;i++){
		document.getElementById("rsypfl_"+i).options.length=1;
	}
	$.ajax({
		type: "POST",
		url: "ywjc_ajaxYpflData.action",
		data:{"flflbm":""+value+""},
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#rsypfl_"+n+"_hidden").val()!=""&&data[i].ypflbm==$("#rsypfl_"+n+"_hidden").val()){
						htmlText = htmlText + '<option value="'+data[i].ypflbm+'" selected="selected">'+data[i].flmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].ypflbm+'">'+data[i].flmc+'</option>';
					}
				});
				$("#rsypfl_"+n).append(htmlText);
		  }
	});	
}


//药品品规change
function yppgChange(type,num){
	var htmlTextTym="";
	var htmlTextJx="";
	var htmlTextScqy="";
	var htmlTextGgbz="";
	var htmlTextBzdw="";
	var n = parseInt(num)+1;
	for(var i=n;i<=5;i++){
		document.getElementById("yppg_"+i).options.length=1;
	}
	var tym="";
	var jx="";
	var scqy="";
	var ggbz="";
	var bzdw="";
	if(type=="0"){
		tym=$("#yppg_1").val();
		jx=$("#yppg_2").val();
		scqy=$("#yppg_3").val();
		ggbz=$("#yppg_4").val();
		bzdw=$("#yppg_5").val();
	}else if(type=="1"){
		if(num=='1'){
			tym=$("#yppg_1_hidden").val();
		}
		if(num=='2'){
			tym=$("#yppg_1_hidden").val();
			jx=$("#yppg_2_hidden").val();
		}
		if(num=='3'){
			tym=$("#yppg_1_hidden").val();
			jx=$("#yppg_2_hidden").val();
			scqy=$("#yppg_3_hidden").val();
		}
		if(num=='4'){
			tym=$("#yppg_1_hidden").val();
			jx=$("#yppg_2_hidden").val();
			scqy=$("#yppg_3_hidden").val();
			ggbz=$("#yppg_4_hidden").val();
		}
	}
	$.ajax({
		type: "POST",
		url: "ywjc_ajaxYppgData.action",
		data:{"status":"change","listNum":num,"tymbm":tym,"jxbm":jx,"scqybm":scqy,"ggbz":ggbz},
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if(null!=data[i].tymmc){
						if(data[i].tymbm==$("#yppg_1_hidden").val()){
							htmlTextTym = htmlTextTym + '<option value="'+data[i].tymbm+'" selected="selected">'+data[i].tymmc+'</option>';
						}else{
							htmlTextTym = htmlTextTym + '<option value="'+data[i].tymbm+'">'+data[i].tymmc+'</option>';
						}
					}
					if(null!=data[i].jxmc){
						if(data[i].jxbm==$("#yppg_2_hidden").val()){
							htmlTextJx = htmlTextJx + '<option value="'+data[i].jxbm+'" selected="selected">'+data[i].jxmc+'</option>';
						}else{
							htmlTextJx = htmlTextJx + '<option value="'+data[i].jxbm+'">'+data[i].jxmc+'</option>';
						}
					}
					if(null!=data[i].scqymc){
						if(data[i].scqybm==$("#yppg_3_hidden").val()){
							htmlTextScqy = htmlTextScqy + '<option value="'+data[i].scqybm+'" selected="selected">'+data[i].scqymc+'</option>';
						}else{
							htmlTextScqy = htmlTextScqy + '<option value="'+data[i].scqybm+'">'+data[i].scqymc+'</option>';
						}
					}
					if(null!=data[i].ggbzmc){
						if(data[i].ggbzbm==$("#yppg_4_hidden").val()){
							htmlTextGgbz = htmlTextGgbz + '<option value="'+data[i].ggbzbm+'" selected="selected">'+data[i].ggbzmc+'</option>';
						}else{
							htmlTextGgbz = htmlTextGgbz + '<option value="'+data[i].ggbzbm+'">'+data[i].ggbzmc+'</option>';
						}
					}
					if(null!=data[i].bzdwmc){
						if(data[i].bzdwbm==$("#yppg_5_hidden").val()){
							htmlTextBzdw = htmlTextBzdw + '<option value="'+data[i].bzdwbm+'" selected="selected">'+data[i].bzdwmc+'</option>';
						}else{
							htmlTextBzdw = htmlTextBzdw + '<option value="'+data[i].bzdwbm+'">'+data[i].bzdwmc+'</option>';
						}
					}
				});
				$("#yppg_1").append(htmlTextTym);
				$("#yppg_2").append(htmlTextJx);
				$("#yppg_3").append(htmlTextScqy);
				$("#yppg_4").append(htmlTextGgbz);
				$("#yppg_5").append(htmlTextBzdw);
		  }
	});		
}

function show(){
	//var actionOld = $("#chooseForm").attr("action");
	//$("#chooseForm").attr("action",actionOld+"?yppg="+$("#dbIp").val()+"&yppgbm="+ encodeURIComponent($("#yppgbm").val()));
	$("#listConstant").submit();
}

function getJgmcByJgjb(value){
	var htmlText = "";
	var jgxz=$('input#jgxzHidden').val();
	$.ajax({
		type: "POST",
		url: "ywjc_getJgmcByJgjb.action",
		data:{"jgjb":value,"jgxz":jgxz},
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#jgmc_hidden").val()==data[i].jgdm){
						htmlText = htmlText + '<option value="'+data[i].jgdm+'" selected="selected">'+data[i].jgmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].jgdm+'">'+data[i].jgmc+'</option>';
					}
				});
				$("#jgmc_select").empty();
				if(value==""){
					$("#jgmc_select").append('<option value="">全部</option>');
					$("#jgmc_select").append(htmlText);
				}else{
					$("#jgmc_select").append('<option value="">全部</option>');
					$("#jgmc_select").append(htmlText);
				}
				$('input#jgjbHidden').val(value);
		  }
	});	
}
function getJgmcByJgxz(value){
	var htmlText = "";
	var jgjb=$('input#jgjbHidden').val();
	$.ajax({
		type: "POST",
		url: "ywjc_getJgmcByJgxz.action",
		data:{"jgjb":jgjb,"jgxz":value},
		dataType:"json",
		success: function(data){
				$.each(data,function(i){
					if($("#jgmc_hidden").val()==data[i].jgdm){
						htmlText = htmlText + '<option value="'+data[i].jgdm+'" selected="selected">'+data[i].jgmc+'</option>';
					}else{
						htmlText = htmlText + '<option value="'+data[i].jgdm+'">'+data[i].jgmc+'</option>';
					}
				});
				$("#jgmc_select").empty();
				if(value==""){
					$("#jgmc_select").append('<option value="">全部</option>');
					$("#jgmc_select").append(htmlText);
				}else{
					$("#jgmc_select").append('<option value="">全部</option>');
					$("#jgmc_select").append(htmlText);
				}
				$('input#jgxzHidden').val(value);
		  }
	});	
}