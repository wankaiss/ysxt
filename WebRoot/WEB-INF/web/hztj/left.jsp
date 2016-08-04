<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ taglib uri="/finder-tags" prefix="tjjc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宁波市综合分析系统</title>
<link href="${path}/css/reset.min.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/public.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.10.0.min.js"></script>

<!-- leftnavgiate -->
<script type="text/javascript" src="${path}/js/leftnavgiate/jquery.min.js"></script>
<!--[if lte IE 10]><script src="${path}/js/leftnavgiate/selectivizr.js"></script><![endif]-->
<link href="${path}/css/leftnavgiate/leftnav.css" rel="stylesheet" type="text/css" />
<!-- leftnavgiate -->


<script type="text/javascript">
	$(document).ready(function(){
		$("#menuDiv").find('span').get(0).click();
		$("#menuDiv").find('a').get(0).click();
	});
	var temp;
	var tempId = "";
	var idx = 0;
	function clickCGBB(obj,id){
		if(idx != 0){
			if($("#"+id).hasClass("shwo")){
				$(obj).removeClass("open");
				$("#"+id).removeClass("shwo");
			}else{
				$(temp).removeClass("open");
				$("#"+tempId).removeClass("shwo");
				$(obj).addClass("open");
				$("#"+id).addClass("shwo");
			}
			/*
			if($("#"+obj.value).hasClass("shwo")){
				$(obj).removeClass("open");
				$("#"+obj.value).removeClass("shwo");
			}else{
				$(temp).removeClass("open");
				$("#"+temp.value).removeClass("shwo");
				$(obj).addClass("open");
				$("#"+obj.value).addClass("shwo");
			}
			*/
		}
		idx++;
		temp = obj;
		tempId = id;
	}
	
</script>
</head>
<body>

	<div id="menuDiv" class="leftwarp mainHi winscroll">
			<%--<ul>
				<li class="cur">
					<a href="hztj_gotoQshz.action" target="hztjMain">全市汇总</a>
				</li>
				<li>
					<span onclick="clickCGBB(this)">常规报表</span>
					<ol class="middle_list shwo setBackgroundColor" id="cgbb_ol">
						<li class="setWidth">
							<a class="ellipsis_a" href="#" target="hztjMain" title="区采购情况汇总">区采购情况汇总</a>
						</li>
					</ol>
				</li>
			</ul>
			--%>
			<ul>
				<li>
					<span value="cgbb_ol" onclick="clickCGBB(this,'cgbb_ol')">药店综合分析</span>
					<ol class="middle_list shwo setBackgroundColor" id="cgbb_ol">
						
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_hosAnalyseParamOperate.action" target="hztjMain" title="药店综合分析参数维护">参数维护</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_hosAggregateAnalysis.action" target="hztjMain" title="药店综合分析">药店综合分析</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_cbrRanking.action" target="hztjMain" title="参保人药店购药排名(非处方药)">参保人购药排名</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_swipeFrequencyStatistics.action" target="hztjMain" title="药店刷卡频次统计">刷卡频次</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydHosEdit.action" target="hztjMain" title="药店与区县对应关系维护">对应关系维护</a>
						</li>
						 
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydTotalStatistics.action" target="hztjMain" title="药店总数据统计">总数据统计</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydMonthMove.action" target="hztjMain" title="药店月接诊人次、费用移动平均">费用移动平均</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydCostStatistics.action" target="hztjMain" title="药店月（季、年）接诊非处方药人次、费用、次均费用">药店月费用</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydCostRanking.action" target="hztjMain" title="参保人药店购药按参保地与就医地相分离要素排名">分离要素排名</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydMonthCostRanking.action" target="hztjMain" title="参保地与就医地相分离人员月平均费用排名">月平均费用排名</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_docWpcfRanking.action" target="hztjMain" title="医生开外配处方次数、费用排名（总的排名）">外配处方排名（总）</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_docYdWpcfRanking.action" target="hztjMain" title="医生开外配处方次数、费用排名（按药店查询）">外配处方排名</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydYpsyRankingYp.action" target="hztjMain" title="药店药品使用排名（按药品查询）">药品使用排名（按药品查询）</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="ydfx_ydYpsyRankingYd.action" target="hztjMain" title="药店药品使用排名（按药店查询）">药店药品使用排名（按药店查询）</a>
						</li>
						<%--<li class="setWidth">
							<a class="ellipsis_a" href="hztj_queryYdFs.action" target="hztjMain" title="历史参数查询">历史参数查询</a>
						</li> --%>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span value="cgbb_ol1" onclick="clickCGBB(this,'cgbb_ol1')">中药饮片分析</span>
					<ol class="middle_list setBackgroundColor" id="cgbb_ol1">
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_totalStatistics.action" target="hztjMain" title="医疗机构及药店中药饮片总量统计">总量统计</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_singleDoctor.action" target="hztjMain" title="单个医生的中药饮片费用及贴均价格查询">单个医生查询</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_singleCostQuery.action" target="hztjMain" title="单贴价格与味数查询">单贴价格查询</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_personTotalStatistics.action" target="hztjMain" title="医疗机构及药店中药饮片分人员总量统计">人员总量统计</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_totalStatisticsQuery.action" target="hztjMain" title="医疗机构及药店特定中药饮片统计数据查询">统计数据查询</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_doctorTotalStatisticsQuery.action" target="hztjMain" title="医生特定中药饮片统计数据查询">医生统计数据查询</a>
						</li>
						<li class="setWidth">
							<a class="ellipsis_a" href="zyyp_singleDoctorStatisticsQuery.action" target="hztjMain" title="单个医生人均总费用统计">人均总费用统计</a>
						</li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span value="cgbb_ol2" onclick="clickCGBB(this,'cgbb_ol2')">参保人员分析</span>
					<ol class="middle_list setBackgroundColor" id="cgbb_ol2">
						<li class="setWidth">
							<a class="ellipsis_a" href="cbr_averageCostQuery.action" target="hztjMain" title="平均就诊费用和就诊次数查询">就诊费用查询</a>
						</li>
					</ol>
				</li>
			</ul>
		</div>
 
<script type="text/javascript">

    $("#menuDiv").find('a').click(function(e){
        var hrefs=$(this).attr('href');
        if(hrefs!=""&&hrefs!=undefined&&hrefs!="javascript:void(0);"&&hrefs.charAt(1)!='#'){
            $($(this)).parents('').addClass('current').siblings().removeClass('current');
        };
    });
    
</script>
</body>
</html>
