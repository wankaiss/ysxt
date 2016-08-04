 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ taglib prefix="ysxt" uri="/ysxt-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="${path}css/ywjc/reset.min.css" rel="stylesheet" type="text/css" />
<link href="${path}css/ywjc/public.css" rel="stylesheet" type="text/css" />
<link href="${path}css/ywjc/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}js/jquery-1.10.0.min.js"></script>
<script type="text/javascript" src="${path}js/js.js"></script>
<script type="text/javascript" src="${path}js/ywjc.js"></script>
<link rel="stylesheet" href="${path}css/pagescroller/css.css" type="text/css" media="all" />
<script type="text/javascript" src="${path}js/pagescroller.min.js"></script>

 	<!-- 弹出窗口 -->
	<link rel="stylesheet" href="${path}css/colorbox.css" />
	<script src="${path}js/jquery.colorbox.js"></script>
	<!-- 弹出窗口 --> 
	
<script type="text/javascript">


	$(document).ready(function($) {
		
		$(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});


		//iframe
		$(".iframe").colorbox({
			onClosed:function(){}
		});

		$('.theme-poptit .close').click(function() {
			$(".contentformdiv").empty();
			$('.theme-popover-mask').hide();
			$('.theme-popover').slideUp(100);
		});
		
		var navLabel = new Array();
		<s:if test="null != #request.tasks">
			<s:iterator value="#request.tasks" var="task">
			navLabel.push("${task.key}");
			</s:iterator>
		</s:if>
		$('#main').pageScroller({ navigation: navLabel});
		
	});
	var doAjax = function(method,id,group){
		$.ajax({
			url:"task_"+method+".action",
			cache:false,
			type:"post",
			data:{
				triggerName:id,
				group:group
			}
		}).done(function(){
			window.location.reload();
		});
	};
	var setValue = function(target,id){
		var tempTop=$(target).offset().top;
		$('.theme-popover').css("top",tempTop);
		var config = $(target).attr("_CONFIG");
		var value = $(target).attr("_VALUE");
		var c = new Config(".contentformdiv",eval("("+(config && config != ""?config:"{}")+")"));
		c.setValue(value);
		$('.theme-popover-mask').show();
		$('.theme-popover-mask').height($(document).height());
		$('.theme-popover').slideDown(120);
		$(".more_search_sure").unbind("click").bind("click",function(){
			$(".contentformdiv").empty();
			$('.theme-popover-mask').hide();
			$('.theme-popover').slideUp(100);
			var values = c.getValue();
			$.ajax({
				url:"task_saveInfo.action",
				cache:false,
				type:"post",
				data:{
					id:id,
					value:values
				},
				success:function(data){
					alert(data.message);
					window.location.reload();
				}
			});
		});
	};
	var doAll = function(method){
		$.ajax({
			cache:false,
			url:"${path}task_"+method+".action"
		}).done(function(){
			alert("成功!");
			window.location.reload();
		});
	};

   function add(){
      window.location.href="task_toadd.action";
   }
</script>
<style type="text/css">
	.dyform{clear:both;margin-top:10px;}
	.dyform li{display:block;line-height:24px;font-size:13px;text-align:left;}
	.dyform li .label{width:130px;display:inline-block;text-align:right;margin-right:5px;}
	.dyform li input{width:50px;border:1px solid #cccccc;}
	.dyform li select{width:100px;border:1px solid #cccccc;padding:1px;}
</style>
</head>
<body style="overflow:auto;">
	<!--弹出框 -->
	<div class="theme-popover-mask"></div>
	<div class="theme-popover">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
			<h3 style="text-align: center;">设置</h3>
		</div>
		<div class="theme-popbod dform">
			<div class="contentformdiv" style="clear:both; text-align: center;"></div>
				<a class="more_search_sure" href="javascript:void(0);" style="margin:20px 35% 0px;">保存</a>
		</div>
	</div>
	<!--弹出框结束 -->
	
	<div class="mainBox" style="padding-right: 9px">
		<div style="border:1px solid #d2e1ed;border-bottom:0px;padding:5px;background-color:#ffffff;text-align:right;">
		    <input type="button" value="添加" id="pauseAll" onclick="add();"/>
			<input type="button" value="全部暂停" id="pauseAll" onclick="doAll('pauseAll');"/>
			<input type="button" value="全部启动" id="resumeAll" onclick="doAll('resumeAll');"/>
		</div>
		<div class="purchaseBox" style="border-right: 1px solid #d2e1ed; border-bottom: 1px solid #d2e1ed">
			<div class="money">
				<div class="dateBox winscroll">
					<div class="tab_nr" id="main">
						<s:if test="null != #request.tasks">
							<s:iterator value="#request.tasks" var="task">
								<div class="tableL_nr section">
									<div class="immedtit">${task.key}
									<s:if test="'医院库存监测'==#task.key">
									<a href="ywjc_gotoJqypMl.action" class="iframe">紧缺药品目录管理</a>
									
										
									</s:if>
									<s:if test="'药企库存监测'==#task.key">
									<a href="ywjc_gotoZdypMl.action" class="iframe">个人指定药品目录管理</a>
									</s:if>
									</div>
									<table width="100%" class="monitor_tab" border="0" cellspacing="0" cellpadding="0">
										<thead>
											<tr>
												<th class="x_l">序号</th>
												<th>监测指标</th>
												<th>监测等级</th>
												<th>监测手段</th>
												<th>状态</th>
												<th>上次执行时间</th>
												<th>下次执行时间</th>
												<th class="x_r">操作</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="#task.value" var="t" status="index">
												<tr>
													<td>${index.index+1}</td>
													<td>
														<span title="${t.TASK_DESC}" style="color:#000000;text-decoration:none;"><ysxt:substring append="..." length="15" value="${t.TASK_DESC}" /></span>
													</td>
													<td>
														<ul>
															<s:if test="#t.LEVEL_INDEX == 'TX'">
																<li>提醒</li>
																<li class="icon_yellowlight"></li>
																<!-- <li class="icon_tixinglight"></li> -->
															</s:if>
															<s:elseif test="#t.LEVEL_INDEX == 'JG'">
																<li>警告</li>
																<li class="icon_yellowlight"></li>
															</s:elseif>
															<s:elseif test="#t.LEVEL_INDEX == 'YC'">
																<li>异常</li>
																<li class="icon_redlight"></li>
															</s:elseif>
															<s:else>
																<li>违规</li>
															    <li class="icon_redlight"></li>
																<!-- <li class="icon_weiguilight"></li> -->
															</s:else>
														</ul>
													</td>
													<td>
														<s:if test="#t.TASK_MEAN == 'SYTS'">
															首页推送
														</s:if>
														<s:else>
															正常检测
														</s:else>
													</td>
													<td>
														<s:if test="#t.TRIGGER_STATE eq 'WAITING' ">
															已启动
														</s:if>
														<s:elseif test="#t.TRIGGER_STATE eq 'PAUSED' ">
															暂停
														</s:elseif>
														<s:elseif test="#t.TRIGGER_STATE eq 'ACQUIRED' ">
															正常执行
														</s:elseif>
														<s:elseif test="#t.TRIGGER_STATE eq 'BLOCKED' ">
															阻塞
														</s:elseif>
														<s:elseif test="#t.TRIGGER_STATE eq 'ERROR' ">
															错误
														</s:elseif>
													</td>
													<td><ysxt:time-format value="${t.PREV_FIRE_TIME}" format="yyyy-MM-dd HH:mm:ss" /></td>
													<td><ysxt:time-format value="${t.NEXT_FIRE_TIME}" format="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
														<s:if test="#t.TRIGGER_STATE eq 'PAUSED'">
															<a href="javascript:doAjax('resumeTrigger','${TRIGGER_NAME}','${TRIGGER_GROUP}');"><span>启动</span></a>
														</s:if>
														<s:else>
															<a href="javascript:doAjax('pauseTrigger','${TRIGGER_NAME}','${TRIGGER_GROUP}');"><span>暂停</span></a>
														</s:else>
														<a class="theme-login" href="javascript:void(0);" onclick='setValue(this,"${DATA_ID}");' _CONFIG='${TCONFIG}' _VALUE='${TCONFIGVALUE}'>设置</a>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>