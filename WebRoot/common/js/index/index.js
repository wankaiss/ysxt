	$(document).ready(function(){
		
		init_system_date('4');
		
		getChartData();		
		
		$('input#btnseason').bind('click',function(){
			if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnyear').hasClass('btnBlue')){
							$('#btnyear').removeClass('btnBlue').addClass('btnGray');
						}
					if($('#btnmonth').hasClass('btnBlue')){
							$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
						}
				}
			getChartData();
		});
		
		$('input#btnyear').bind('click',function(){
			if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnseason').hasClass('btnBlue')){
							$('#btnseason').removeClass('btnBlue').addClass('btnGray');
						}
					if($('#btnmonth').hasClass('btnBlue')){
							$('#btnmonth').removeClass('btnBlue').addClass('btnGray');
						}
				}
			getChartData();
		});
		
		$('input#btnmonth').bind('click',function(){
			if($(this).hasClass('btnGray')){
					$(this).removeClass('btnGray').addClass('btnBlue');
					if($('#btnyear').hasClass('btnBlue')){
							$('#btnyear').removeClass('btnBlue').addClass('btnGray');
						}
					if($('#btnseason').hasClass('btnBlue')){
							$('#btnseason').removeClass('btnBlue').addClass('btnGray');
						}
				}
			getChartData();
		});
			
		});

	
	function getLeftChart(){
		init_select_date();
		$("#leftChart").empty();
		$("#leftChart").append('<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>');
		$("#leftChart").append('<div  id="chart" class="chartArea"></div>');
		$("#leftChart").append('<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>');
		$.ajax({
			url:"index_getPercentOfCgjeAjaxData.action",
			type:"POST",
			dataType:"json",
			data:{
				startDate:date.startDate,
				selectType:date.type
			},
			success:function(data){
				var zfyje=data.zfyje;
				var jyje=data.jyje;
				var djyje=data.djyje;
				var kjyje=data.kjyje;
				var zcyje=data.zcyje;
				var totalCgje=data.totalCgje;
				
				var zfyjePercentOfCgje=0;//自费药占比
				var jyjePercentOfCgje=0;//基药占比
				var djyjePercentOfCgje=0;//低价药占比
				var kjyjePercentOfCgje=0;//抗菌药占比
				var zcyjePercentOfCgje=0;//中成药占比
				if(totalCgje!=0){
					zfyjePercentOfCgje=(zfyje/totalCgje*100).toFixed(2);
					jyjePercentOfCgje=(jyje/totalCgje*100).toFixed(2);
					djyjePercentOfCgje=(djyje/totalCgje*100).toFixed(2);
					kjyjePercentOfCgje=(kjyje/totalCgje*100).toFixed(2);
					zcyjePercentOfCgje=(zcyje/totalCgje*100).toFixed(2);
				}
				
				$('#chart').highcharts({
					chart: {
			            type: 'column',
			            height:300,
			            spacingBottom:3
			        },
			        colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', 
			                 '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
			        title: {
			            text: ''
			        },
			        subtitle: {
			            text: ''
			        },
			        xAxis: {
			            categories: [
			                '自费药占比',
			                '基药占比',
			                '低价药占比',
			                '抗菌药占比',
			                '中成药占比'
			            ]
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: '占采购金额百分比%'
			            }
			        },
			        tooltip: {
			            formatter: function () {
			                return '<b>' + this.x + '</b><br/>' +
			                    this.series.name + ': ' + this.y + '%<br/>';
			            }
			        },
			        legend:{
			        	 enabled: false,
					     align:"center",
					     verticalAlign:"bottom",
					     borderWidth: 1,
					     borderRadius: 7,
					     borderColor:'#AAAAAA',
					     symbolHeight:5,
					     symbolWidth: 13
						},
					credits: {
				            enabled: false
				        },
			        exporting: {
			            enabled: false
			        },
			        series: [{
			            name: '金额占比',
			            data: [eval(zfyjePercentOfCgje),eval(jyjePercentOfCgje),eval(djyjePercentOfCgje),eval(kjyjePercentOfCgje),eval(zcyjePercentOfCgje)],
			            dataLabels: {
			                enabled: true,
			                rotation: -90,
			                color: '#FFFFFF',
			                align: 'right',
			                x: 4,
			                y: 10,
			                formatter: function () {
			               return  this.y + '%';
			                },
			                style: {
			                    fontSize: '13px',
			                    fontFamily: 'Verdana, sans-serif',
			                    textShadow: '0 0 3px black'
			                }
			            }
			        }]
			    }); 
			}
		});
	}
	function getMiddleChart(data){
		$("#middleChart").empty();
		$("#middleChart").append('<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>');
		$("#middleChart").append('<div  id="pie" class="chartArea"></div>');
		$("#middleChart").append('<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>');
		var pie=eval(data[1][0].seriesData);
				/*中间的饼图*/
		$('#pie').highcharts({
		   chart: {
		       type:'pie' ,
		       height:300,
		       spacingBottom:3
		   },
		   title: {
		       text: '入账金额',
		       style:{ "color": "#333333", "fontSize": "15px" },
		       align: 'center',
		       verticalAlign: 'top',
		       y: 10
		   },
		   tooltip: {
		       pointFormat: '{series.name}: <b>￥{point.y}</b>'
		       	
		   },
		   credits:{
		  	 enabled:true
		  	},
		 	exporting:{
			     enabled:false
			    },
		   legend:{
			    enabled:true,
			    floating:true
				},
		   plotOptions: {
		       pie: {
		           dataLabels: {
		               enabled: true,
		               distance: 5,
		               crop:false,
		               formatter:function () {
		                       var fmt = "";
		                       fmt += '<br/>' + '<b>' + this.percentage.toFixed(1) + '%</b>';
		                       return fmt;
		                   }
		              
		           },
		           startAngle: -180,
		           endAngle: 180,
		           center: ['50%', '50%'],
		           showInLegend: true
		       }
		   },
		   legend:{
			     align:"center",
			     verticalAlign:"bottom",
			     borderWidth: 1,
			     borderRadius: 7,
			     borderColor:'#AAAAAA',
			     symbolHeight:5,
			     symbolWidth: 13
				},
		   series: [{
		       type: 'pie',
		       name: '总量',
		       innerSize: '40%',
		       data: pie
		   }]
		});
	}
	function getRightChart(data){
		$("#rightChart").empty();
		$("#rightChart").append('<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>');
		$("#rightChart").append('<div  id="chartDate" class="chartArea"></div>');
		$("#rightChart").append('<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>');
		var rightChartXcategories=eval(data[2][0].Xcategories);
		var rightChartSeries=eval(data[2][1].series);
		/*最右边的柱状图*/
		$('div#chartDate').highcharts({
			        chart: {
			            type: 'column',
			            height:300,
			            spacingBottom:3
			        },
			        title: {
			            text: ''
			        },
			        subtitle: {
			            text: ''
			        },
			        xAxis: {
			            categories: rightChartXcategories
			        },
			        credits:{
			        	 enabled:false
			        	},
			       	exporting:{
					     enabled:false
					    },
					legend:{
					     align:"center",
					     verticalAlign:"bottom",
					     borderWidth: 1,
					     borderRadius: 7,
					     borderColor:'#AAAAAA',
					     symbolHeight:5,
					     symbolWidth: 13
						},
			        yAxis: {
			            min: 0,
			            title: {
			                text: '单位(百万)',
			            	style: {		            		
			            		
			            	}
			            },
			            labels:{
			            	formatter:function(){
			            		return this.value;
			            	}
			            }
			        },
			        tooltip: {
			        	crosshairs: [true, true],
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.01,
			                borderWidth: 0
			            }
			        },
			        series: rightChartSeries
			    });  
	}
	
	function getChartData(){
		init_select_date();
		$("#leftChart").empty();
		$("#middleChart").empty();
		$("#rightChart").empty();
		$("#leftChart").append("<img id='picture' src=\"common/images/loading2.gif\" style=\"border=0\">");
		$("#middleChart").append("<img id='picture' src=\"common/images/loading2.gif\" style=\"border=0\">");
		$("#rightChart").append("<img id='picture' src=\"common/images/loading2.gif\" style=\"border=0\">");
		$.ajax({
			url:"index_getChartData.action",
			type:"POST",
			dataType:"json",
			data:{
				startDate:date.startDate,
				selectType:date.type
			},
			success:function(data){
				getLeftChart();
				getMiddleChart(data); 
				getRightChart(data);			
			}
		});
	}