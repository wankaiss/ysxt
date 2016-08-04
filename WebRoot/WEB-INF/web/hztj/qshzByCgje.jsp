<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上海市医药采购服务与监管信息系统</title>
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
		
		<script type="text/javascript" src="${path}js/hztj/qshzByCgje.js"></script>
			
		<!-- pageset -->
		<script type="text/javascript" src="${path}js/jquery.papper.js"></script>
		<link href="${path}css/pageset.css" rel="stylesheet" type="text/css" />
		<!-- pageset -->	
		<!-- 弹出窗口 -->
		<link rel="stylesheet" href="${path}css/hztj/colorbox.css" />
		<script src="${path}js/jquery.colorbox.js"></script>
		<!-- 弹出窗口 -->  
		<style>
		html,body {
			height: 100%;
			background: #fff;
		}
		</style>

		<script type="text/javascript" src="${path}js/jssearch/j.suggest.js"></script>
		<script type="text/javascript" src="${path}js/hztj/fxk.js"></script>
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	

	</head>
	<body>
	        <input type="hidden" value="<s:property value="#session.USERMESSAGE.userSsQx" escape="false"/>" id="yhqx"/>
			<div class="gray_k">
				<span class="up_donw" ><i>深入选择展开</i> </span>
				<span class="fL" style="padding-left:20px;">
					<input id="btnyear" type="button" value="年度"  onclick="WdatePickerSjLoad('4');changeClass();" class="btnGray"/>
					<input id="btnseason" type="button" onclick="WdatePickerSjLoad('3');changeClass();"  value="季度" class="btnGray" />
					<input id="btnmonth" type="button"  onclick="WdatePickerSjLoad('2');changeClass();"  value="月度" class="btnGray" />
					<em class="pL20">
					    <input type="text" name="startDate" id="startDate" class="iconDate" onchange=""  style="width:128px;"/> 
						<select id="dateStart_jd" style="display:none;width:128px;height:25px;margin-top:6px;" onchange="selectDateOnChange();"></select>
					</em>
				</span>
				<input name="" value="选定＝>展示" class="blue_bt" type="button" onclick="show();" style="float:left;margin-top:5px;"/>
			</div>
			<form action="hztj_gotoQshzByCgje.action" method="post" id="chooseForm" target="qshzMain">
					<div class="dateBox winscroll"  style="clear: both;">
						<div class="tab_nr">
	                            <input type="hidden" id="showSumbit" name="showSumbit" value="<s:property value="showSumbit"/>"/>
								<input type="hidden" id="sjkj" name="whereModel.byYear" value=""/>
								<input type="hidden" id="sjkj_type" name="whereModel.type" value="4"/>
							<s:if test="null!=whereModel">
								<s:if test="whereModel.type!='' and whereModel.type==4">
									<input type="hidden" id="sjkjReturn" value="${whereModel.byYear }"/>
									<input type="hidden" id="sjkjTypeReturn" value="${whereModel.type }"/>
								</s:if>
								<s:elseif test="whereModel.type!='' and whereModel.type==3">
									<input type="hidden" id="sjkjReturn" value="${whereModel.byQuarter }"/>
									<input type="hidden" id="sjkjTypeReturn" value="${whereModel.type }"/>
								</s:elseif>
								<s:elseif test="whereModel.type!='' and whereModel.type==2">
									<input type="hidden" id="sjkjReturn" value="${whereModel.byMonth }"/>
									<input type="hidden" id="sjkjTypeReturn" value="${whereModel.type }"/>
								</s:elseif>
							</s:if>
							<s:else>
								<input type="hidden" id="sjkjReturn" value=""/>
								<input type="hidden" id="sjkjTypeReturn" value=""/>
							</s:else>
							<div class="tab_search_box">
							<table class="tab_search tab_hide" width="100%" border="0"
								cellspacing="0" cellpadding="0" style="text-align: left;">
								<tr>
									<td width="100">
										<strong id="qxmc_strong">区县名称：</strong>
									</td>
									<td colspan="6">
										<div class="count_Top" style="width:300px;" id="qx">
											<select name="whereModel.byQxdm"  id="select_qxxx" style="width:100%;">
												<option value="" selected="selected" class="optionstyle">
													全部
												</option>
											</select>
										
										</div>
										<input type="hidden" value="${whereModel.byQxdm }" id="qxmcSelectValue"/>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="jgjb_strong">机构级别：</strong>
									</td>
									<td colspan="6">
										<div class="de-check-box">
										<div class="check_box">
											<s:if test="null==whereModel">
												<input type="checkbox" name="whereModel.byJgjb" value="" checked="checked"/>
												<span>全部</span>
												<input type="hidden" id="jgjbCheckData" value=""/>
											</s:if>
											<s:else>
												<input type="checkbox" name="whereModel.byJgjb" value="" <s:if test="whereModel.byJgjb==''"> checked </s:if>/>
												<span>全部</span>
												<input type="hidden" id="jgjbCheckData" value="${whereModel.byJgjb }"/>
											</s:else>
										</div>
									
									<s:if test="null != jgjbxxList && jgjbxxList.size()>0">
										<s:iterator value="jgjbxxList" var="jgjbData" status="index">
												<div class="check_box">
													<input type="checkbox" name="whereModel.byJgjb"  value="${jgjbData.AAA102}"/>
													<span>${jgjbData.AAA103}</span>
												</div>
										</s:iterator>
									</s:if>
									</div>
									</td>
								</tr>
								
								<tr>
									<td>
										<strong id="jgxz_strong">机构性质：</strong>
									</td>
									<td colspan="6">
									<div class="de-check-box">
										<div class="check_box">
											<s:if test="null==whereModel">
												<input type="checkbox" name="whereModel.byJgxz" value="" checked="checked"/>
												<span>全部</span>
												<input type="hidden" id="jgxzCheckData" value=""/>
											</s:if>
											<s:else>
												<input type="checkbox" name="whereModel.byJgxz" value="" <s:if test="whereModel.byJgxz==''"> checked </s:if>/>
												<span>全部</span>
												<input type="hidden" id="jgxzCheckData" value="${whereModel.byJgxz }"/>
											</s:else>
										</div>
									
									<s:if test="null != jgxzxxList && jgxzxxList.size()>0">
										<s:iterator value="jgxzxxList" var="jgxzData" status="index">
											<!-- <td> -->
												<div class="check_box">
													<input type="checkbox" name="whereModel.byJgxz" value="${jgxzData.AAA102}"/>
													<span>${jgxzData.AAA103}</span>
												</div>
											<!-- </td> -->
										</s:iterator>
									</s:if>
									</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="jglx_strong">机构类型：</strong>
									</td>
									<td colspan="6">
									<div class="de-check-box">
										<div class="check_box">
										<s:if test="null==whereModel">
												<input type="checkbox" name="whereModel.byJglx" value="" checked="checked"/>
												<span>全部</span>
												<input type="hidden" id="jglxCheckData" value=""/>
											</s:if>
											<s:else>
												<input type="checkbox" name="whereModel.byJglx" value="" <s:if test="whereModel.byJglx==''"> checked </s:if>/>
												<span>全部</span>
												<input type="hidden" id="jglxCheckData" value="${whereModel.byJglx }"/>
											</s:else>
											</div>
									
									<s:if test="null != jglxxxList && jglxxxList.size()>0">
										<s:iterator value="jglxxxList" var="jglxData" status="index">
											<!-- <td> -->
												<div class="check_box">
													<input type="checkbox" name="whereModel.byJglx" value="${jglxData.AAA102}" />
													<span >${jglxData.AAA103}</span>
												</div>
											<!-- </td> -->
										</s:iterator>
									</s:if>
									</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="sfyb_strong">是否医保：</strong>
									</td>
									
									<td colspan="6">
										<div class="de-check-box">
										<div class="check_box">
											<s:if test="null==whereModel">
												<input type="checkbox" name="whereModel.bySfyb" value="" checked="checked"/>
												<span>全部</span>
												<input type="hidden" id="sfybCheckData" value=""/>
											</s:if>
											<s:else>
												<input type="checkbox" name="whereModel.bySfyb" value="" <s:if test="whereModel.bySfyb==''"> checked </s:if>/>
									  			<span>全部</span>
									  			<input type="hidden" id="sfybCheckData" value="${whereModel.bySfyb }"/>
											</s:else>
										</div>
									
											<div class="check_box">
												<input type="checkbox" name="whereModel.bySfyb" value="1"/>
												<span>医保定点</span>
											</div>
											<div class="check_box">
												<input type="checkbox" name="whereModel.bySfyb" value="0"/>
												<span>非医保定点</span>
											</div>
									   </div>		
									</td>
								</tr>
								<tr>
									<td>
										<strong id="cglx_strong">采购类型：</strong>
									</td>
									<td colspan="6">
										<div style="width:300px;" id="cgms">
										<select name="whereModel.byCgms" id="select_cglx" style="width:100%;">
												<option value="" selected="selected" class="optionstyle">
													全部
												</option>
											</select>
									    </div>
									    <input type="hidden" value="${whereModel.byCgms }" id="cglxSelectValue"/>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="jgmc_strong">机构名称：</strong>
									</td>
									<td colspan="6">
										<div class="count_Top2 z_d8 fl" style="width:300px;" id="jgmc">

											<select name="whereModel.byJgdm" id="jgmc_select" style="width:100%;">
												<option value="" selected="selected" class="optionstyle">
													全部
												</option>
											</select>
											
										</div>
										<input type="hidden" value="${whereModel.byJgdm }" id="jgmcSelectValue"/>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="yqmc_strong">药企名称：</strong>
									</td>
									<td colspan="6">
										<div class="count_Top3 z_d7 fl" style="width:300px;" id="yqmc">
											<select name="whereModel.byScqydm" id="yqmc_select"  style="width:100%;">
												<option value="" selected="selected" class="optionstyle">
													全部
												</option>
											</select>
										</div>
										<input type="hidden" value="${whereModel.byScqydm }" id="yqmcSelectValue"/>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="yplx_strong">药品类型：</strong>
									</td>
									<td colspan="6">
										<div class="count_Top4 z_d6 fl" style="width:300px;" id="yplx">
											<select name="whereModel.byYplx" id="yplx_select"  style="width:100%;">
												<option value="" selected="selected" class="optionstyle">
													全部
												</option>
											</select>
										</div>
										<input type="hidden" value="${whereModel.byYplx }" id="yplxSelectValue"/>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="ypfl_strong">药品分类：</strong>
										
									</td>
									<td colspan="6" style="text-align: left;">
									<s:if test="#request.fltype==''">
										<input type="radio" name="whereModel.byYpfl" value="ybypfl" onclick="ypflInit();" checked="checked" />医保药品分类
										<input type="radio" name="whereModel.byYpfl" value="rsypfl" onclick="ypflInit();"/>人社药品分类 <br/>
									</s:if>
									<s:else>
										<input type="radio" name="whereModel.byYpfl" value="ybypfl" onclick="ypflInit();" <s:if test="#request.fltype=='ybypfl'">checked="checked"</s:if> />医保药品分类
										<input type="radio" name="whereModel.byYpfl" value="rsypfl" onclick="ypflInit();" <s:if test="#request.fltype=='rsypfl'">checked="checked"</s:if> />人社药品分类 <br/>
									</s:else>
									<input type="hidden" name="fltype" value="${fltype }" id="flType_hidden"/>
										<div id="ybDiv" class="tab_show">
											<div class="count_Top5 z_d5 fL" id="ypfxOption1">
												<select id="ybypfl_1" name="whereModel.ypflmodel.fl1" style="width:200px;">
													<option value="" selected="selected" class="optionstyle">
															全部
													</option>
												</select>
										</div>
										<s:if test="#request.fltype=='ybypfl'">
													<input type="hidden" value="${whereModel.ypflmodel.fl1 }" id="ybfl1_hidden"/>
												</s:if>
												<s:else>
													<input type="hidden" value="" id="ybfl1_hidden"/>
												</s:else>
										<span class="jt_icon"></span>
										<div class="count_Top6 fL" id="ypfxOption2">
											<select id="ybypfl_2" name="whereModel.ypflmodel.fl2" style="width:200px;">
												<option value="" selected="selected" class="optionstyle">
															全部
												</option>
											</select>
												
										</div>
										<s:if test="#request.fltype=='ybypfl'">
											<input type="hidden" value="${whereModel.ypflmodel.fl2 }" id="ybfl2_hidden"/>
										</s:if>
										<s:else>
											<input type="hidden" value="" id="ybfl2_hidden"/>
										</s:else>
										<span class="jt_icon"></span>

										<div class="count_Top7 fL" id="ypfxOption3">
											<select id="ybypfl_3" name="whereModel.ypflmodel.fl3" style="width:200px;">
												<option value="" selected="selected" class="optionstyle">
															全部
												</option>
											</select>
										</div>	
											<s:if test="#request.fltype=='ybypfl'">
												<input type="hidden" value="${whereModel.ypflmodel.fl3 }" id="ybfl3_hidden"/>
											</s:if>
											<s:else>
												<input type="hidden" value="" id="ybfl3_hidden"/>
											</s:else>
										</div>
										<div id="rsDiv" class="tab_hide">
										<div class="count_Top5 z_d5 fL" id="ypfxOption4">
												<select id="rsypfl_1" style="width:200px;">
													<option value="" selected="selected" class="optionstyle">
															全部
													</option>
												</select>
												<s:if test="#request.fltype=='rsypfl'">
													<input type="hidden" value="${whereModel.ypflmodel.fl1 }" id="rsfl1_hidden"/>
												</s:if>
												<s:else>
													<input type="hidden" value="" id="rsfl1_hidden"/>
												</s:else>
										</div>
										<span class="jt_icon"></span>
										<div class="count_Top6 fL" id="ypfxOption5">
											<select id="rsypfl_2" style="width:200px;">
												<option value="" selected="selected" class="optionstyle">
															全部
												</option>
											</select>
												<s:if test="#request.fltype=='rsypfl'">
													<input type="hidden" value="${whereModel.ypflmodel.fl2 }" id="rsfl2_hidden"/>
												</s:if>
												<s:else>
													<input type="hidden" value="" id="rsfl2_hidden"/>
												</s:else>
										</div>
										<span class="jt_icon"></span>

										<div class="count_Top7 fL" id="ypfxOption6">
											<select id="rsypfl_3" style="width:200px;">
												<option value="" selected="selected" class="optionstyle">
															全部
												</option>
											</select>
											<s:if test="#request.fltype=='rsypfl'">
												<input type="hidden" value="${whereModel.ypflmodel.fl3 }" id="rsfl3_hidden"/>
											</s:if>
											<s:else>
												<input type="hidden" value="" id="rsfl3_hidden"/>
											</s:else>											
											
										</div>
											<span class="jt_icon"></span>
											<div class="count_Top7 fL" id="ypfxOption7">
												<select id="rsypfl_4" style="width:200px;">
													<option value="" selected="selected" class="optionstyle">
															全部
													</option>
												</select>
												<s:if test="#request.fltype=='rsypfl'">
													<input type="hidden" value="${whereModel.ypflmodel.fl4 }" id="rsfl4_hidden"/>
												</s:if>
												<s:else>
													<input type="hidden" value="" id="rsfl4_hidden"/>
												</s:else>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<strong id="yppg_strong">药品品规：</strong>
									</td>
									<td style="text-align: left;">
										<a class="iframe" href="yppg_open.jsp">
											<s:if test="null==whereModel">
												<input value="" class="yppgInput" type="text" name="yppg" id="dbIp" readonly="readonly" style="width:300px;"/>
												<input type="hidden" value="" name="yppgbm" id="yppgbm" readonly="readonly"/>
											</s:if>
											<s:else>
												<input value="${yppg }" class="yppgInput" type="text" name="yppg" id="dbIp" readonly="readonly" style="width:300px;"/>
												<input type="hidden" value="${yppgbm }" name="yppgbm" id="yppgbm"/>
											</s:else>
										</a>
									</td>
								</tr>
							
							</table>
							</div>
						
							<div class="search-contation-text ellipsis_a" style="display:none;" id="cxtjdiv"><label>查询条件：</label><span id="cxtj"></span></div><a style="display:none;" id="cxtj_a" href="javascript:void(0);" onclick="showCxtj();">》》</a>
							<input type="hidden" value="" id="cxtj_hidden"/>
							<div class="chart_nr">
							<!-- 报表显示 -->
								<table width="100%" class="chart_tb" border="0" cellspacing="0"
										cellpadding="0">
										<tr>
											<td class="bckRbg">
												<div id="main" style="width:100%;">
													<div class="wd-chart-box" style="width:49.9%; float: left;">
														<div id="div_left_qshzByCgje" style="height:360px;display:block;">报表1</div>
													</div>
													<div class="wd-chart-box" style="width:49.9%; float: left;">
														<div id="div_right_qshzByCgje" style="height:360px;display:block;">报表2</div>
													</div>
												</div>
											</td>
										</tr>
									</table>

									<div class="wd-content-box">
									
									<table class="date_tab" width="100%" border="0"
									cellspacing="0" cellpadding="0">
										<thead>
											<tr>
												<th>
													时间
												</th>
												<th>
													订单金额(元)
												</th>
												<th>
													配送金额(元)
												</th>
												<th>
													入账金额(元)
												</th>
												<th>
													入库金额(元)
												</th>
												<th>
													完成支付金额(元)
												</th>
												<th>
													退货金额(元)  <s:property value="#attr.page.size"/>1
												</th>

											</tr>
										</thead>
										<s:if test="null != pageSet.data && pageSet.data.size()>0">
											<s:iterator value="pageSet.data" var="cons" status="index">
												<tr>
													<td>
														${cons.TTT }
													</td>
													<td>
														<fmt:formatNumber value="${cons.DDJE }" pattern="#,###.##" minFractionDigits="2"  />
														</td>
													<td>
														<fmt:formatNumber value="${cons.PSJ }" pattern="#,###.##" minFractionDigits="2"  />
														</td>
													<td>
														<fmt:formatNumber value="${cons.RZJE }" pattern="#,###.##" minFractionDigits="2"  />
														</td>
													<td>
														<fmt:formatNumber value="${cons.RKJE }" pattern="#,###.##" minFractionDigits="2"  />
														</td>
													<td>
														<fmt:formatNumber value="${cons.WCZFJE }" pattern="#,###.##" minFractionDigits="2"  />
														</td>
													<td>
														<fmt:formatNumber value="${cons.THJE }" pattern="#,###.##" minFractionDigits="2"  />
													</td>
												</tr>

											</s:iterator>
										</s:if>
										<s:else>
											<tr>
												<td colspan="7">
													查无数据
												</td>
											</tr>
										</s:else>

										
							<tfoot>
								<tr>
								   <td colspan="7">
						           <div class="papper" id="papper"></div>
										<script type="text/javascript">
											$(document).ready(function(){
												$("#papper").papper({
													count:"${pageSet.count}",
													start:"${pageSet.start}",
													limit:"${pageSet.limit}",
													form:"chooseForm"
												});
											});
										</script>
							</td>
						
							
								</tr>
							</tfoot>
									
									</table>
								
									</div>

							</div>
						</div>
						
					</div>
</form>
	
	<script type="text/javascript" src="${ctx}/common/js/echarts-all.js"></script>
</body>
</html>
