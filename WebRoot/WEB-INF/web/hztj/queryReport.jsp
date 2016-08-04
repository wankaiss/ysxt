<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>药店综合分析分析报告</title>
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
		<script type="text/javascript" src="common/js/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/common/js/echarts-all.js"></script>
		
		<!-- pageset -->
		<script type="text/javascript" src="${path}js/jquery.papper.js"></script>
		<link href="${path}css/pageset.css" rel="stylesheet" type="text/css" />
	</head>
<body>
</br>
	<form action="hztj_queryReport.action" name="form" method="post" id="chooseForm" target="_self">
		<div class="dateBox winscroll"  style="clear: both;">
			<div class="tab_nr">
				<div class="wd-content-box" style="text-align: left;">
					<span style="font-size: 18px;text-align: left;">
						<strong>
						<s:property value="condition.akb021"/>，代码为：<s:property value="condition.akb020"/>
						，所属区县为：${aaa027Map[condition.aaa027] }，法人代表为：<s:property value="condition.aab013"/>
						，地址为：<s:property value="condition.aae006"/>，<s:property value="condition.byYear"/>内药店得分为：<s:property value="condition.fs"/>
						，排名：<s:property value="condition.pm"/>
						<!-- 
						，药店非处方药费用等级为：
						，处方药分类等级为：
						，<s:property value="condition.dnzh"/>元以上非处方药当年账户购药人次占比<fmt:formatNumber value="${condition.dnzb }" pattern="#,###.##" minFractionDigits="2"/>%
						，<s:property value="condition.lnzh"/>元以上非处方药历年账户购药人次占比<fmt:formatNumber value="${condition.lnzb }" pattern="#,###.##" minFractionDigits="2"/>%。
						 -->
						增长率具体指标如下：
						</strong>
					</span>
				</div>
				<div class="wd-content-box">
					<table class="date_tab" width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
						<thead>
							<tr>
								<th></th>
								<th>本期值</th>
								<th>上期值</th>
								<th>去年同期值</th>
								<th>上年度平均值</th>
								<th>同比增长率</th>
								<th>环比增长率</th>
								<th>与同类药店平均增长率差值</th>
								<th>与自己去年平均值比较的增长率</th>
							</tr>
						</thead>
						<s:if test="null != pageSet.data && pageSet.data.size()>0">
							<s:iterator value="pageSet.data" var="cons" status="index">
								<tr>
									<th>
										${cons.MC }
									</th>
									<td>
										<fmt:formatNumber value="${cons.BQZ }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.SQZ }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.QNTQZ }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<fmt:formatNumber value="${cons.SNDPJZ }" pattern="#,###.##" minFractionDigits="2"/>
									</td>
									<td>
										<s:if test="#cons.TBZZL != null">
											<fmt:formatNumber value="${cons.TBZZL }" pattern="#,###.##" minFractionDigits="2"/>%
										</s:if>
									</td>
									<td>
										<s:if test="#cons.HBZZL != null">
											<fmt:formatNumber value="${cons.HBZZL }" pattern="#,###.##" minFractionDigits="2"/>%
										</s:if>
									</td>
									<td>
										<s:if test="#cons.TLYDCZ != null">
											<fmt:formatNumber value="${cons.TLYDCZ }" pattern="#,###.##" minFractionDigits="2"/>%
										</s:if>
									</td>
									<td>
										<s:if test="#cons.ZJQNBJ != null">
											<fmt:formatNumber value="${cons.ZJQNBJ }" pattern="#,###.##" minFractionDigits="2"/>%
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</s:if>
					</table>
				</div>
				<div style="text-align: center;">
					<input type="button" value="关闭" onclick="javascript:window.close();"/>
				</div>
			</div>
			<s:if test="null != pageSet.data && pageSet.data.size()>0">
				<div class="chart_nr">
				<!-- 报表显示 -->
					<table width="100%" class="chart_tb" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="bckRbg">
								<div id="main" style="width:100%;">
									<div class="wd-chart-box" style="width:49.9%; float: left;">
										<div id="div_left_jzrcbj" style="height:360px;display:block;">接诊人次比较</div>
									</div>
									<div class="wd-chart-box" style="width:49.9%; float: left;">
										<div id="div_right_jzfybj" style="height:360px;display:block;">接诊费用比较</div>
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
				rightChart();
			}
		});
		
		function rightChart(){
			$("#div_right_jzfybj").empty();
			var myChart1 = echarts.init(document.getElementById('div_right_jzfybj'));
			var data3 = <s:property value="condition.data3"/>;
			var data4 = <s:property value="condition.data4"/>;
			
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
		        	data:['处方药','非处方药']
    		    },
				title : {
						text: '接诊费用比较',
						subtext: '',
						x:'left'
						},
		        tooltip:{
					    trigger: 'axis'
		                },
		        xAxis : {
	                	type : 'category',
	                	data : ['本期值','上期值','去年同期值','上年度平均值']
		        },
		        yAxis : [
		                 {
		                   type : 'value',
		                   name:'单位：元' 
		                  }
		                ],
		        series : [{
		        	name:'处方药',
		        	type:'bar',
		        	data:data3
		        },{
		        	name:'非处方药',
		        	type:'bar',
		        	data:data4
		        }]
		    });
		    window.onresize = myChart1.resize;
		}
		
		function leftChart(){
			$("#div_left_jzrcbj").empty();
			var myChart = echarts.init(document.getElementById('div_left_jzrcbj'));
			var data1 = <s:property value="condition.data1"/>;
			var data2 = <s:property value="condition.data2"/>;
			
			myChart.setOption({
		    	grid:{
		    	y:60,
		    	x2:10,
    	 		height:260
    		 },
    		    legend: {
    		    	orient: 'horizontal',
    		    	x: 'center', 
    				y: 'top', 
		        	data:['处方药','非处方药']
    		    },
				title : {
						text: '接诊人次比较',
						subtext: '',
						x:'left'
						},
		        tooltip:{
					    trigger: 'axis'
		                },
		        xAxis : {
	                	type : 'category',
	                	data : ['本期值','上期值','去年同期值','上年度平均值']
		        },
		        yAxis : [
		                 {
		                   type : 'value',
		                   name:'单位：次' 
		                  }
		                ],
		        series : [{
		        	name:'处方药',
		        	type:'bar',
		        	data:data1
		        },{
		        	name:'非处方药',
		        	type:'bar',
		        	data:data2
		        }]
		    });
		    window.onresize = myChart.resize;
		}
	</script>
</body>
</html>