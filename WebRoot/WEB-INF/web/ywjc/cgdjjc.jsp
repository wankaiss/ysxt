<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上海市医药采购服务与监管信息系统</title>
		<link href="${path}css/ywjc/reset.min.css" rel="stylesheet"
			type="text/css" />
		<link href="${path}css/ywjc/public.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/ywjc/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}css/likeCheck.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="${path}js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${path}js/js.js"></script>
		<script src="${path}js/common.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path}js/ywjc/fxk.js"></script>
		<script type="text/javascript" src="${path}js/jquery.nicescroll.min.js"></script>
		<!-- pageset -->
		<script type="text/javascript" src="${path}js/jquery.papper.js"></script>
		<link href="${path}css/pageset.css" rel="stylesheet" type="text/css" />
		<!-- pageset -->
		<!-- 弹出窗口 -->
		<link rel="stylesheet" href="${path}css/hztj/colorbox.css" />
		<script src="${path}js/jquery.colorbox.js"></script>
		<!-- 弹出窗口 -->  
		<!-- 弹出框 -->
    	<script type="text/javascript" src="${path}js/dialog/script.js"></script>	
		<style>
			html,body {
				height: 100%;
				background: #fff;
			}
			tr[name=detail]{
				cursor: pointer;
			}
			a{
			font-size: 12px;
			color: #000000;
			text-decoration: none;
			}
			a:visited {
			font-size: 12px;
			color: #000000;
			text-decoration: none;
			} 
		</style>

		
		<script type="text/javascript">
	$(document).ready(function($){

		$(".iframe").colorbox({iframe:true, width:"100%", height:"100%"});
		//iframe
		$(".iframe").colorbox({
			onClosed:function(){
			}
		});

	
			
});
	function formClose()
	{
		jQuery.colorbox.close();
	}

</script>


	</head>
	<body>
	
			<div class="gray_k">
				<!-- <span class="up_donw"><i>筛选条件</i> </span> -->
				<input id="" name="" value="筛选条件" class="blue_bt" type="button"  style="float:left;margin-top:5px;"/>
			</div>
			<div class="dateBox winscroll">
				<div class="tab_nr">
					<form action="ywjc_cgdjjc.action" method="post" id="listConstant">
						<jsp:include page="/WEB-INF/web/ywjc/include_cgdjjc.jsp"></jsp:include>	
<%--					</form>--%>

						<table class="date_tab" width="100%" border="0"	cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<th width="29%" >
										药品品规
									</th>
									<th width="25%">
										最高价医疗机构/最低价医疗机构
									</th>
									<th width="25%">
										最高价销售药企/最低价销售药企
									</th>
									<th width="8%">
										最高采购单价
									</th>
									<th width="8%">
										最低采购单价
									</th>
									<th width="5%">
										差异率
									</th>
								</tr>
							</thead>
							<tbody>
					<s:if test="null != pageSet.data && pageSet.data.size()>0">
						<s:iterator value="pageSet.data" var="cons" status="index">						
							<tr name="detail">														
								<td>
									1
								</td>
							
								<td>
									1
								</td>
								<td>
										${cons.YQMC } 
									</td>
								<td>
									2
									</td>
								<td>
									2
									</td>
								<td>
									
										忽略不计
														
								</td>									
							</tr>				
					
						</s:iterator>
						
					</s:if>
					<s:else>
						<tr>
							<td colspan="6">
								查无数据
							</td>
						</tr>
					</s:else>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="11">
								<div class="papper" id="papper">
									
								</div>
								<script type="text/javascript">
									$(document).ready(function() {
										$("#papper").papper({
											count : "${pageSet.count}",
											start : "${pageSet.start}",
											limit : "${pageSet.limit}",
											form : "listConstant"
										});
									});
								</script>
							</td>
						</tr>
					</tfoot>
					
				</table>
					</form>
				</div>
			</div>
	</body>

</html>
