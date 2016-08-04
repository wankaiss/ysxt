<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>医生特定中药饮片统计数据查询图形化展示</title>
		<link href="${path}css/reset.min.css" rel="stylesheet"
			type="text/css" />
		<link href="${path}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/likeCheck.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${path}js/js.js"></script>
		<script type="text/javascript" src="${path}js/jquery.nicescroll.min.js"></script>
		<script type="text/javascript" src="${path}My97DatePicker/WdatePicker.js"></script>
		<script src="${path}js/common.js"  type="text/javascript"></script>
		<script src="${path}js/date_select.js"  type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/common/js/echarts.js"></script>
		
		<!-- pageset -->
		<script type="text/javascript" src="${path}js/jquery.papper.js"></script>
		<link href="${path}css/pageset.css" rel="stylesheet" type="text/css" />
	</head>
<body>
</br>
	<form action="hztj_showDemo.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<s:if test="null != pageSet.data && pageSet.data.size()>0">
				<div class="chart_nr">
				<!-- 报表显示 -->
					<table width="100%" class="chart_tb" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="bckRbg">
								<div id="main" style="width:100%;">
									<div class="wd-chart-box" style="float: left;">
										<div id="div_left_jzrcbj" style="height:560px;display:block;">
											<h1>
												<s:if test="condition.startDate==condition.endDate">
													<s:property value="condition.startDate"/>
												</s:if>
												<s:else>
													<s:property value="condition.startDate"/>~
													<s:property value="condition.endDate"/>
												</s:else>
												医生特定中药饮片统计数据查询图形化展示
											</h1>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</s:if>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			if('<s:property value="pageSet.data"/>'!=null){
				leftChart();
			}
		});
		
		function leftChart(){
			$("#div_left_jzrcbj").empty();
			var myChart = echarts.init(document.getElementById('div_left_jzrcbj'));
			var data1 = <s:property value="condition.data1"/>;
			var data2 = <s:property value="condition.data2"/>;
			var data3 = <s:property value="condition.data3"/>;
			
			myChart.setOption({
		    	grid:{
		    	y:70,
		    	x2:60,
    	 		height:360
    		 },
    		 toolbox: {
	            show : true,
	            feature : {
	                magicType: {show: true, type: ['line', 'bar']},
	                restore : {show: true}
	            }
	        },
	        dataZoom: [
		        {
		        	type: 'slider',
            		show: true,
		            start: 0,
		            end: 100
		        }
		    ],
	        calculable : true,
    		    legend: {
    		    	orient: 'horizontal',
    		    	x: 'center', 
    				y: 'top', 
		        	data:['数量（个）','费用（元）']
    		    },
		        tooltip:{
					    trigger: 'axis'
		                },
		        xAxis : {
	                	type : 'category',
	                	data : data1,
	                	axisLabel : {
		                	rotate : 60
	                	}
		        },
		        yAxis : [{type : 'value',
		                  name:'单位：元'
		                 },
		                 {
		                	type : 'value',
		                    name:'单位：个'
		                  }
		                ],
		        series : [{
		        	name:'数量（个）',
		        	type:'bar',
		        	yAxisIndex: 1,
		        	data:data2
		        },{
		        	name:'费用（元）',
		        	type:'bar',
		        	data:data3
		        }]
		    });
		    window.onresize = myChart.resize;
		}
	</script>
</body>
</html>