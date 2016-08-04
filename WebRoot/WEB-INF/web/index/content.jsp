<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>上海市医药采购服务与监管信息系统</title>
    <link href="common/css/reset.min.css" rel="stylesheet" type="text/css" />
	<link href="common/css/public.css" rel="stylesheet" type="text/css" />
	<link href="common/css/style.css" rel="stylesheet" type="text/css" />
	<link href="common/css/index/index.css" rel="stylesheet" type="text/css"/>
	<link href="common/css/bootstrap-3.2.0.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="common/js/jquery-1.10.0.min.js"></script>
    <script type="text/javascript" src="common/js/highcharts.js"></script>
 
    <script src="common/js/common.js"  type="text/javascript"></script>
	<script src="common/js/date_select.js"  type="text/javascript"></script>
	<script src="common/js/index/index.js"  type="text/javascript"></script>
	<script src="common/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
	
</head>

<body style="height: 100%;">
<div id='socket'></div>
<div class="mainwarp" style="height: 100%;">
	<div style="width:100%;height:100%">
		<div class="Immediate">
			<div class="immedTop">
				<div class="immedtit fL">数据最新更新时间&nbsp;&nbsp;</div>
				<div class="fL"><span class="immedtimeimg fL">&nbsp;</span>
				<div class="immedtimetext fL" id="timer"></div>
				</div>
			</div>
			<div class="immCon" style="padding-top:3px;">
				<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="68%">
						<table border="0" width="100%" cellpadding="0" cellspacing="0">
						<tr>
					    <td width="68%"> 			
							<div class="StatusR">	
								<div class="statTab greenTab">
									<div class="statL"><div class="statR"><div class="statC"></div></div></div>
									<div class="statCenter">
										<div class="immedstaTit"><span class="icon_drug">即时采配状况（单据条数）</span></div>
										<div class="immedacon">
										<a href="#" class="iframe">
											<span class="w114" id="span_dapps" >
												<em>待审批</em><i id="dsp">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yapps">
												<em>待安排配送</em><i id="dapps">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_dyyzf">
												<em>待验收入库</em><i id="dysrk">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yqwxy">
												<em>待财务入账</em><i id="dcwrz">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yqwxy">
												<em>待结算支付</em><i id="djszf">1</i>
											</span>
											</a>	
										</div>
									</div>
									<div class="stabL"><div class="stabR"><div class="stabC"></div></div></div>
								</div>
							</div>
						</td>
						
						</tr>
						
						<tr>
						
						<td width="68%"> 			
							<div class="StatusR">	
								<div class="statTab blueTab">
									<div class="statL"><div class="statR"><div class="statC"></div></div></div>
									<div class="statCenter">
										<div class="immedstaTit"><span class="icon_drug">当前累计状况（单据条数）</span></div>
										<div class="immedacon">
										<a href="#" class="iframe">
											<span class="w114" id="span_dapps">
												<em>已受理并提交</em><i id="yslbtj">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yapps">
												<em>已验收入库</em><i id="yysrk">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_dyyzf">
												<em>已财务入账</em><i id="ycwrz">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yqwxy">
												<em>已结算支付</em><i id="yjszf">1</i>
											</span>
											</a>
											<a href="#" class="iframe">
											<span class="w114" id="span_yqwxy">
												<em>未响应关闭</em><i id="wxygb">1</i>
											</span>		
											</a>								
										</div>
									</div>
									<div class="stabL"><div class="stabR"><div class="stabC"></div></div></div>
								</div>
							</div>
						</td>
						
						</tr>
						</table>
							
					    </td>

						<td width="11"></td>
						<td width="30%">
						
							<div class="Business_Mon">
							<div>
								<label class="busmonTit" style="font-weight:bold;">业务监测</label>
								<a style="float:right;padding-right:16px;font-size: 14px;line-height: 34px;" href="#" class="iframe">更多</a>
							</div>	
							
								<div class="busmondate">
									<p><em>238</em><span>采购平均价超过中标规格数</span></p>
									<p><em>147</em><span>采购平均价超过中标规格数</span></p>
									<p><em>16</em><span>采购平均价超过中标规格数</span></p>
									<p><em>44</em><span>采购平均价超过中标规格数</span></p>
									<p><em>233</em><span>采购平均价超过中标规格数</span></p>
									<p><em>354</em><span>采购平均价超过中标规格数</span></p>									
								</div>
								
							</div>
							
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="charBor mT10">
			<div class="chartTop">
				<span class="chartTit">全市采购情况汇总</span>
				<span class="fR pT6R12">
					<input id="btnyear" type="button" value="年度"  onclick="WdatePickerSjLoad('4')" class="btnBlue" />
					<input id="btnseason" type="button" onclick="WdatePickerSjLoad('3')"  value="季度" class="btnGray" />
					<input id="btnmonth" type="button"  onclick="WdatePickerSjLoad('2')"  value="月度" class="btnGray" />
					<em class="pL20">
					    <input type="text" name="startDate" id="startDate" class="iconDate"  onchange="getChartData();"  style="width:128px;"/> 
						<select id="dateStart_jd" style="display:none;width:128px;height:26px;" onchange="selectDateOnChange();getChartData();"></select>	
					</em>
				</span>
			</div>
			<div class="chartTab">		
			<div id="leftChart" style="float:left;width:32%;" class="chartBoard">
				<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>	
				<div  id="chart" class="chartArea"></div>
				<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>	
			</div>
			<div id="middleChart" style="float:left;width:33%;"  class="chartBoard">
				<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>	
				<div  id="pie" class="chartArea"></div>
				<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>	
			</div>
			<div id="rightChart" style="float:left;width:32%;"  class="chartBoard">
				<div class="charttL"><div class="charttR"><div class="charttC"></div></div></div>	
				<div  id="chartDate" class="chartArea"></div>	
				<div class="chartbL"><div class="chartbR"><div class="chartbC"></div></div></div>		
			</div>
			</div>
		</div>
	</div>
</div>

    <script type="text/javascript" src="common/js/echarts-plain-map.js"></script>
</body>

<script language="javascript">
	
	$('.greenTab .w114').bind('click',function(){
		$('.greenTab .w114').each(function(){
			$(this).removeClass('cur');
		});
		$(this).addClass('cur');
	});
	$('.blueTab .w114').bind('click',function(){
		$('.blueTab .w114').each(function(){
			$(this).removeClass('cur');
		});
		$(this).addClass('cur');
	});
</script>

</html>