<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<table class="tab_search" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<strong class="floatStyle"  id="yqmc_strong">供货药企：</strong>
			<div class="divStyle">
			<select name="whereModel_Ywjc.byGhyqYwjc" id="ghyq_select">
				<s:if test="null==whereModel_Ywjc">
					<option value="" selected="selected" class="optionstyle">
						全部
					</option>
				</s:if>
				<s:else>
					<option value="" class="optionstyle" <s:if test="whereModel_Ywjc.byGhyqYwjc==''"> selected </s:if>>
						全部
					</option>
				</s:else>
			</select>
			 <input type="hidden" value="${whereModel_Ywjc.byGhyqYwjc }" id="ghyqHidden"/>
			 </div>
			 <strong class="floatStyle" style="margin-left:50px;" id="yljg_strong">医疗机构：</strong>
			<div class="divStyle">
			<select name="whereModel_Ywjc.byJgjbYwjc" id="jgjb_select" class="floatStyle" onchange="getJgmcByJgjb(this.value);">
				<s:if test="null==whereModel_Ywjc">
					<option value="" selected="selected" class="optionstyle">
						全部
					</option>
				</s:if>
				<s:else>
					<option value="" class="optionstyle" <s:if test="whereModel_Ywjc.byJgjbYwjc==''"> selected </s:if>>
						全部
					</option>
				</s:else>
			</select>
			<input type="hidden" value="${whereModel_Ywjc.byJgjbYwjc }" id="jgjbHidden"/>
			
			<span class="tj_icon" ></span>
			<select name="whereModel_Ywjc.byJgmcYwjc" id="jgmc_select">
				<s:if test="null==whereModel_Ywjc">
					<option value="" selected="selected" class="optionstyle">
						全部
					</option>
				</s:if>
				<s:else>
					<option value="" class="optionstyle" <s:if test="whereModel_Ywjc.byJgmcYwjc==''"> selected </s:if>>
						全部
					</option>
				</s:else>
			</select>
			<input type="hidden" value="${whereModel_Ywjc.byJgmcYwjc }" id="jgmc_hidden">
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="3">
			<strong id="yppg_strong">药品品规：</strong>
			<a class="iframe" href="yppg_open_ywjc.jsp">
				<s:if test="null==whereModel_Ywjc">
					<input value="" class="yppgInput" type="text" name="yppg" id="dbIp" readonly="readonly" style="width:300px;"/>
					<input type="hidden" value="" name="yppgbm" id="yppgbm" readonly="readonly"/>
				</s:if>
				<s:else>
					<input value="${yppg }" class="yppgInput" type="text" name="yppg" id="dbIp" readonly="readonly" style="width:300px;"/>
					<input type="hidden" value="${yppgbm}" name="yppgbm" id="yppgbm"/>
				</s:else>
		  </a>
		</td>		
	</tr>
	<tr>
		<td colspan="3">
			<input id="showButton" name="" value="选定＝>展示" class="blue_bt" type="button"  style="float:right;"/>
		</td>
	</tr>
</table>
