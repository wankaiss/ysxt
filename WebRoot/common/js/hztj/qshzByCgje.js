
$(document).ready(function(){
	leftChart();
	rightChart('rzje');
	$("#div_left_qshzByCgje").empty();
	$("#div_right_qshzByCgje").empty();
	$("#div_left_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
	$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");

});
function leftChart(){
	$.ajax({
		url:"hztj_ajaxGetSortOfQSHZByCGJELeftChart.action",
		type:"POST",
		dataType:"json",
		data:{
			selectedDate:'2014',//date.startDate,
			selectedType:'4'//date.type,
		},
		success:function(data){
			$("#div_left_qshzByCgje").empty();
			var xAxis=eval(data[0]);
			var series=eval(data[1]);
			var myChart = echarts.init(document.getElementById('div_left_qshzByCgje'));
			
		    var option={
		    	grid:{
                y:60,
		    	x2:10,
    	 		height:260
    		 },
    		    legend: {
    		    	orient: 'horizontal',
    		    	x: 'center', 
    				y: 'top', 
    				selectedMode :'multiple',
    				data:['订单金额','配送金额','入库金额','入账金额','完成支付金额','退货金额']
    		    },
    		    color:['#FF7755','#65BD65','#DAAD69','#31A4F0','#F1A7D3','#D366EE'],
				title : {
						text: '',
						subtext: '',
						x:'left'
						},
		        tooltip:{
					    trigger: 'axis'
		                },
		        xAxis : xAxis,
		        yAxis : [
		                 {
		                   type : 'value',
		                   name:'单位：元' 
		                  }
		                ],
		        series : series
		    };
		    myChart.setOption(option);
		    function eConsole(param) {
		    	if(param.seriesName=='入账金额'||param.target=='入账金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('rzje');
		    	}else if(param.seriesName=='订单金额'||param.target=='订单金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('ddje');
		    	}else if(param.seriesName=='配送金额'||param.target=='配送金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('psje');
		    	}else if(param.seriesName=='入库金额'||param.target=='入库金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('rkje');
		    	}else if(param.seriesName=='完成支付金额'||param.target=='完成支付金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('wczfje');
		    	}else if(param.seriesName=='退货金额'||param.target=='退货金额'){
		    		$("#div_right_qshzByCgje").empty();
		    		$("#div_right_qshzByCgje").append("<img src=\"common/images/loading2.gif\" style=\"border=0\">");
		    		rightChart('thje');
		    	}
		    }

		myChart.on(echarts.config.EVENT.CLICK, eConsole);
		myChart.on(echarts.config.EVENT.LEGEND_SELECTED, eConsole);
		window.onresize = myChart.resize;
		}
	});
}
function rightChart(type){
	$.ajax({
		url:"hztj_ajaxGetSortOfQSHZByCGJERightChart.action",
		type:"POST",
		dataType:"json",
		data:{
			selectedDate:'2014',//date.startDate,
			selectedType:'4',//date.type,
			type:type
		},
		success:function(data){
			$("#div_right_qshzByCgje").empty();
			var xAxis=eval(data[0]);
			var series=eval(data[1]);
			var legend_data=eval(data[2]);
			var myChart1 = echarts.init(document.getElementById('div_right_qshzByCgje'));
			
		    myChart1.setOption({
		    	grid:{
		    	y:60,
		    	x2:10,
    	 		height:260
    		 },
    		    legend: {
    		    	orient: 'horizontal',
    		    	x: 'center', 
    				y: 'top', 
		        	data:legend_data
    		    },
				title : {
						text: '',
						subtext: '',
						x:'left'
						},
		        tooltip:{
					    trigger: 'axis'
		                },
		        xAxis : xAxis,
		        yAxis : [
		                 {
		                   type : 'value',
		                   name:'单位：元' 
		                  }
		                ],
		        series : series
		    });
		    window.onresize = myChart1.resize;
		}
	});	
	
   }