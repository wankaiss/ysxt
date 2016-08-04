package com.wondersgroup.qyws.tjfx.action.hztj;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.module.TbParaGrade;
import com.wondersgroup.qyws.tjfx.module.TbParaHeavy;
import com.wondersgroup.qyws.tjfx.service.CommonService;
import com.wondersgroup.qyws.tjfx.service.HztjService;
import com.wondersgroup.qyws.tjfx.util.ExportExcelUtil;
import com.wondersgroup.qyws.tjfx.util.GlobalConstants;
import com.wondersgroup.util.papper.PageSet;

@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class HztjAction extends BaseAction {
	
	@Autowired
	private HztjService hztjService;
	
	@Autowired
	private CommonService commonService;
	
	public String gotoHztjIndex(){
		this.page = this.ROOR_PATH + "/hztj/index.jsp";
		return this.SUCCESS;
	}
	
	public String left(){
		this.page = this.ROOR_PATH + "/hztj/left.jsp";
		return this.SUCCESS;
	}
	
	//全市汇总
	public String gotoQshz()
	{
		this.page = this.ROOR_PATH + "/hztj/qshzindex.jsp";
		return this.SUCCESS;
	}
	//全市汇总(按采购金额)
	public String gotoQshzByCgje() throws UnsupportedEncodingException
	{	
		Map map=new HashMap<String,String>();
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.getTestList(pageSet,map);
	
		this.page = this.ROOR_PATH + "/hztj/qshzByCgje.jsp";
		return this.SUCCESS;
	}
	
	//采购金额左Chart
	public void ajaxGetSortOfQSHZByCGJELeftChart(){
		String rs="[[{\"type\":\"category\",\"data\":[\"2014年2月\",\"2015年1月\",\"2015年2月\"]}],[{\"name\":\"订单金额\",\"type\":\"bar\",\"data\":[0,2.438068184719E8,1.507534803535E8]},{\"name\":\"配送金额\",\"type\":\"bar\",\"data\":[0,1.444620442681E8,1.391005398256E8]},{\"name\":\"入账金额\",\"type\":\"bar\",\"data\":[0,1.143049509512E8,1.19052127798E8]},{\"name\":\"入库金额\",\"type\":\"bar\",\"data\":[0,1.248855332036E8,1.279888169387E8]},{\"name\":\"完成支付金额\",\"type\":\"bar\",\"data\":[0,9218181.5602,7977755.3]},{\"name\":\"退货金额\",\"type\":\"bar\",\"data\":[0,343226.89,292435.85]}]]";
		this.ajax(rs, "text/json");
	}
	//采购金额右Chart
	public void ajaxGetSortOfQSHZByCGJERightChart(){
		String rs="[[{\"type\":\"category\",\"data\":[\"3月\",\"4月\",\"5月\",\"6月\",\"7月\",\"8月\",\"9月\",\"10月\",\"11月\",\"12月\",\"1月\",\"2月\"]}],[{\"name\":\"2014-2015(入账金额)\",\"type\":\"line\",\"symbol\":\"emptyCircle\",\"symbolSize\":3,\"smooth\":false,\"itemStyle\":{\"normal\":{\"color\":\"#CC0000\",\"lineStyle\":{\"width\":2,\"type\":\"dashed\"}}},\"data\":[0,0,0,0,0,0,0,0,0,4.11982647923E7,1.143049509512E8,1.19052127798E8]},{\"name\":\"2013-2014(入账金额)\",\"type\":\"line\",\"symbol\":\"emptyTriangle\",\"symbolSize\":3,\"smooth\":false,\"itemStyle\":{\"normal\":{\"color\":\"#008B8B\",\"lineStyle\":{\"width\":2,\"type\":\"dashed\"}}},\"data\":[0,0,0,0,0,0,0,0,0,0,0,0]}],[\"2014-2015(入账金额)\",\"2013-2014(入账金额)\"]]";
		this.ajax(rs,"text/json");
	}
	
	/**
	 * 跳转药店综合分析参数维护页面
	 * @return
	 */
	public String hosAnalyseParamOperate(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		condition = new QueryCondition();
//		// 查询数据库
//		this.pageSet = hztjService.queryYdfx(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
	
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/ydfx.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 查询药店分析参数
	 * @return
	 */
	public String queryYdfx(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.queryYdfx(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/ydfx.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 权重修改
	 * @return
	 */
	public String weigthUpd(){
		this.page = this.ROOR_PATH + "/hztj/weigthUpd.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 增长率详细设置
	 * @return
	 */
	public String growSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.growSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/growSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 分数设置
	 * @return
	 */
	public String scoreSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.scoreSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/scoreSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店费用等级跨越详细设置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String medLevelSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		condition.setDivId("1");
		// 查询数据库
		this.pageSet = hztjService.medLevelSetting(pageSet,condition);
		//new新的map 把非处方和处方合并到一起
		Map map1 = new HashMap<String,String>();
		Map map2 = new HashMap<String,String>();
		Map map3 = new HashMap<String,String>();
		Map map4 = new HashMap<String,String>();
		if(pageSet.getCount()>0){
			map1 = (HashMap<String,String>)this.getPageSet().getData().get(0);
			map2 = (HashMap<String,String>)this.getPageSet().getData().get(1);
			map3 = (HashMap<String,String>)this.getPageSet().getData().get(2);
			map4 = (HashMap<String,String>)this.getPageSet().getData().get(3);
			condition.setSxz(String.valueOf(map1.get("XXZ")));
			condition.setXxz(String.valueOf(map4.get("SXZ")));
		}else{
			condition.setSxz("1");
			condition.setXxz("1");
		}
		condition.setDivId("2");
		this.pageSet1 = hztjService.medLevelSetting(pageSet,condition);
		Map map5 = new HashMap<String,String>();
		Map map8 = new HashMap<String,String>();
		if(pageSet1.getCount()>0){
			map5 = (HashMap<String,String>)this.getPageSet1().getData().get(4);
			map8 = (HashMap<String,String>)this.getPageSet1().getData().get(7);
			map1.putAll((HashMap<String,String>)this.getPageSet1().getData().get(4));
			map2.putAll((HashMap<String,String>)this.getPageSet1().getData().get(5));
			map3.putAll((HashMap<String,String>)this.getPageSet1().getData().get(6));
			map4.putAll((HashMap<String,String>)this.getPageSet1().getData().get(7));
			condition.setTsxz(String.valueOf(map5.get("TXXZ")));
			condition.setTxxz(String.valueOf(map8.get("TSXZ")));
			List<Map> list = new ArrayList<Map>();
			list.add(0, map1);
			list.add(1, map2);
			list.add(2, map3);
			list.add(3, map4);
			this.pageSet.setData(list);
		}else{
			condition.setTsxz("1");
			condition.setTxxz("1");
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/medLevelSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 处方药非处方药权重设置
	 * @return
	 */
	public String prescriptionSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.prescriptionSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/prescriptionSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 单次非处方购药费用占比
	 * @return
	 */
	public String singleMedPay(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition.getDivId().equals("1")){
			this.pageSet = hztjService.singleMedPay(pageSet,condition);
		}else{
			this.pageSet1 = hztjService.singleMedPay(pageSet,condition);
		}
		
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/singleMedPay.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 当年账户历年账户权重设置
	 * @return
	 */
	public String oldAndNewSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.oldAndNewSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/oldAndNewSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 费用额度设置
	 * @return
	 */
	public String amountSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.amountSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/amountSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店综合分析 
	 * @return
	 */
	public String hosAggregateAnalysis(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getByYear())){
				if(condition.getType().equals("2")){
					condition.setByMonth(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月");
				}else{
					condition.setByQuarter(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度");
				}
			}
			this.pageSet = hztjService.hosAggregateAnalysis(pageSet,condition);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		//药店分类Map
		Map akb023Map = commonService.selectDicInfo("AKB023");
		this.setAttribute("akb023Map", akb023Map);
		//医院等级
		Map aka101Map = commonService.selectDicInfo("AKA101");
		this.setAttribute("aka101Map", aka101Map);
		this.page = this.ROOR_PATH + "/hztj/hosAggregateAnalysis.jsp";
		return this.SUCCESS;
	}		
	
	/**
	 * 药店分析详细查询
	 * @return
	 */
	public String queryDetail(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			try {
				String start = URLDecoder.decode(condition.getStartDate(), "utf-8");
				if(start.endsWith("月")){
					condition.setStartTime(start.substring(0, 4)+start.substring(5, 7));
				}else{
					condition.setStartTime(start.substring(0, 4)+"S"+start.substring(7, 8));
				}
				condition.setStartDate(start);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			this.pageSet = hztjService.queryDetail(pageSet,condition);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/queryDetail.jsp";
		return this.SUCCESS;
	}	
	
	/**
	 * 药店综合分析导出Excel
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(){
		String stime = "";
		if(StringUtils.isNotEmpty(condition.getByYear())){
			if(condition.getType().equals("2")){
				condition.setByMonth(condition.getByYear());
				stime = condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月";
			}else if(condition.getType().equals("4")){
				condition.setByZrYear(condition.getByYear());
				stime = condition.getByYear()+"年";
			}else if(condition.getType().equals("1")){
				condition.setByYbYear(condition.getByYear());
				stime = condition.getByYear()+"年";
			}else{
				condition.setByQuarter(condition.getByYear());
				stime = condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度";
			}
		}
		this.pageSet = new PageSet(999999999, 0);
		int i = 0;
		if(condition.getDivId().equals("1")){
			i = 10;
			this.pageSet = hztjService.hosAggregateAnalysis(pageSet,condition);
		}else if(condition.getDivId().equals("2")){
			i = 13;
			this.pageSet = hztjService.cbrRanking(pageSet,condition);
		}else if(condition.getDivId().equals("3")){
			i = 8;
		}
		Map akc021Map = commonService.selectDicInfo("AKC021");
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[i];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			if(condition.getDivId().equals("1")){
				obj[0] = stime;
				obj[1] = map.get("AKB020");
				obj[2] = map.get("AKB021");
//				obj[3] = map.get("AKB023");
				obj[3] = aaa027Map.get(map.get("AAA027"));
				obj[4] = map.get("FS");
				obj[5] = map.get("PM");
				obj[6] = map.get("CF");
				obj[7] = map.get("CFFY");
				obj[8] = map.get("FCF");
				obj[9] = map.get("FCFFY");
			}else if(condition.getDivId().equals("2")){
				obj[0] = stime;
				obj[1] = map.get("YBH");
				obj[2] = map.get("XM");
				obj[3] = aaa027Map.get(map.get("QXDM"));
				obj[4] = map.get("ZFY");
				obj[5] = akc021Map.get(map.get("RYLB"));
				obj[6] = map.get("TBMC");
				obj[7] = map.get("FCFYFY");
				obj[8] = map.get("CFYFY");
				obj[9] = map.get("AKB021");
				obj[10] = map.get("JGID");
				obj[11] = aaa027Map.get(map.get("SSQX"));
				obj[12] = map.get("PM");
			}else if(condition.getDivId().equals("3")){
				obj[0] = stime;
				obj[1] = map.get("YBH");
				obj[2] = map.get("XM");
				obj[3] = aaa027Map.get(map.get("QXDM"));
				obj[4] = map.get("ZFY");
				obj[5] = akc021Map.get(map.get("RYLB"));
				obj[6] = map.get("TBMC");
				obj[7] = map.get("FCFYFY");
			}
			rs.add(obj);
		}
		String[] top = new String[i];
		String xlsName = "";
		String sheetName = "";
		if(condition.getDivId().equals("1")){
			xlsName = "药店分析查询";
			sheetName = "药店分析查询";
			top[0] = "时间";
			top[1] = "药店代码";
			top[2] = "药店名称";
			top[3] = "所属区县";
			top[4] = "分数";
			top[5] = "排名";
			top[6] = "处方药人次";
			top[7] = "处方药费用";
			top[8] = "非处方药人次";
			top[9] = "非处方药费用";
		}else if(condition.getDivId().equals("2")){
			xlsName = "参保人药店购药排名";
			sheetName = "参保人药店购药排名";
			top[0] = "时间";
			top[1] = "医保号";
			top[2] = "姓名";
			top[3] = "区县";
			top[4] = "总费用";
			top[5] = "人员类别";
			top[6] = "特病名称";
			top[7] = "非处方药费用";
			top[8] = "处方药费用";
			top[9] = "费用最高药店名称";
			top[10] = "费用最高药店代码";
			top[11] = "费用最高药店所属区县";
			top[12] = "总费用排名";
		}else if(condition.getDivId().equals("3")){
			xlsName = stime + "药店等级设置";
			sheetName = stime + "药店等级设置";
			top[0] = "区县";
			top[1] = "药店名称";
			top[2] = "非处方药费用范围";
			top[3] = "处方药费用范围";
			top[4] = "平均非处方药费用";
			top[5] = "平均处方药费用";
			top[6] = "建议非处方药分类等级";
			top[7] = "建议处方药分类等级";
		}
		try {
			ExportExcelUtil.resultSetToExcel(rs, top,stime + xlsName,sheetName );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存
	 * @return
	 */
	public void saveParameter(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		hztjService.saveParameter(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 权重修改
	 */
	public void updateParameter(){
		String id = this.getParameter("id");
		String qzz = this.getParameter("qzz");
		condition = new QueryCondition();
		condition.setQzz(qzz);
		condition.setId(id);
		hztjService.updateParameter(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 权重删除
	 */
	public void deleteParameter(){
		String id = this.getParameter("id");
		condition = new QueryCondition();
		condition.setId(id);
		hztjService.deleteParameter(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 增长率设置权重值保存
	 */
	public void saveParameterLevelTwo(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterLevelTwo(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 分值保存
	 */
	public void saveParameterLevelThree(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterLevelThree(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 处方药非处方药权重设置
	 */
	public void saveParameterPrescription(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterPrescription(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 当年账户历年账户权重设置保存
	 */
	public void saveParameterOldAndNew(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterOldAndNew(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 费用额设置保存
	 */
	public void saveParameterAmount(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterAmount(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 单次非处方购药费用占比保存
	 */
	public void saveParameterSingleMed(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String divId = this.getParameter("divId");
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setDivId(divId);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterSingleMed(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 药店综合分析分析报告
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryReport() throws Exception{
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			String akb021 = "";
			try {
				akb021 = URLDecoder.decode(condition.getAkb021(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			condition.setAkb021(akb021);
			if(condition.getAkb020().length()==6){
				condition.setAkb020(condition.getAkb020()+"          ");
			}
			//查询kc21,kb01信息
//			List baList = hztjService.queryKc21Kb01Info(condition);
//			Map bamap=new HashMap<String,String>();
//			if(baList.size()>0){
//				bamap = (HashMap<String,String>)baList.get(0);
//				condition.setAab013(String.valueOf(bamap.get("AAB013")));
//				condition.setAae006(String.valueOf(bamap.get("AAE006")));
//			}
			//查询TB_PARA_HEAVY权重和占比信息
//			List list = hztjService.queryBasicInfo(condition);
//			Map map=new HashMap<String,Double>();
//			if(list.size()>0){
//				map = (HashMap<String,Double>)list.get(0);
//				condition.setDnzb(Double.parseDouble(String.valueOf(map.get("DNZB"))));
//				condition.setLnzb(Double.parseDouble(String.valueOf(map.get("LNZB"))));
//				condition.setDnzh(Double.parseDouble(String.valueOf(map.get("DNZH"))));
//				condition.setLnzh(Double.parseDouble(String.valueOf(map.get("LNZH"))));
//			}
			if(StringUtils.isNotEmpty(condition.getByYear())){
				if(condition.getType().equals("2")){
					condition.setByMonth(condition.getByYear());
					condition.setByYear(condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月");
				}else{
					condition.setByQuarter(condition.getByYear());
					condition.setByYear(condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度");
				}
			}
			this.pageSet = hztjService.queryReport(pageSet,condition);
		}
		if(pageSet.getCount()>0){
			Map map = new HashMap<String,String>();
			//次数 处方药
			ArrayList data1 = new ArrayList();
			map = (HashMap<String,String>)pageSet.getData().get(0);
			data1.add(0, map.get("BQZ"));
			data1.add(1, map.get("SQZ"));
			data1.add(2, map.get("QNTQZ"));
			data1.add(3, map.get("SNDPJZ"));
			//次数 非处方药
			ArrayList data2 = new ArrayList();
			map = (HashMap<String,String>)pageSet.getData().get(2);
			data2.add(0, map.get("BQZ"));
			data2.add(1, map.get("SQZ"));
			data2.add(2, map.get("QNTQZ"));
			data2.add(3, map.get("SNDPJZ"));
			//费用 处方药
			ArrayList data3 = new ArrayList();
			map = (HashMap<String,String>)pageSet.getData().get(1);
			data3.add(0, map.get("BQZ"));
			data3.add(1, map.get("SQZ"));
			data3.add(2, map.get("QNTQZ"));
			data3.add(3, map.get("SNDPJZ"));
			//费用 非处方药
			ArrayList data4 = new ArrayList();
			map = (HashMap<String,String>)pageSet.getData().get(3);
			data4.add(0, map.get("BQZ"));
			data4.add(1, map.get("SQZ"));
			data4.add(2, map.get("QNTQZ"));
			data4.add(3, map.get("SNDPJZ"));
			condition.setData1(data1);
			condition.setData2(data2);
			condition.setData3(data3);
			condition.setData4(data4);
			this.setAttribute("data1", data1);
			this.setAttribute("data2", data2);
			this.setAttribute("data3", data3);
			this.setAttribute("data4", data4);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/queryReport.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店费用等级详情设置
	 * @return
	 */
	public void saveMedLevel(){
		String templev = this.getParameter("templev");//药店等级
		String tempname = this.getParameter("tempname");//非处方药分类名称
		String tempval = this.getParameter("tempval");//非处方药费用
		String tempcfname = this.getParameter("tempcfname");//处方药分类名称
		String tempcfval = this.getParameter("tempcfval");//处方药费用
		String temptcdm = this.getParameter("temptcdm");//统筹代码
		String tempId = this.getParameter("tempId");//权重ID
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setId(tempId);
		condition.setTcdm(temptcdm);
		condition.setMedlev(templev);
		condition.setFcfName(tempname);
		condition.setFcfValue(tempval);
		condition.setCfName(tempcfname);
		condition.setCfValue(tempcfval);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveMedLevel(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 费用范围修改
	 * @return
	 */
	public String updateCostRange(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		condition.setDivId("1");
		// 查询数据库
		this.pageSet = hztjService.medLevelSetting(pageSet,condition);
		//new新的map 把非处方和处方合并到一起
		Map map1 = new HashMap<String,String>();
		Map map2 = new HashMap<String,String>();
		Map map3 = new HashMap<String,String>();
		Map map4 = new HashMap<String,String>();
		if(pageSet.getCount()>0){
			map1 = (HashMap<String,String>)this.getPageSet().getData().get(0);
			map2 = (HashMap<String,String>)this.getPageSet().getData().get(1);
			map3 = (HashMap<String,String>)this.getPageSet().getData().get(2);
			map4 = (HashMap<String,String>)this.getPageSet().getData().get(3);
			condition.setSxz(String.valueOf(map1.get("XXZ")));
			condition.setXxz(String.valueOf(map4.get("SXZ")));
		}else{
			condition.setSxz("1");
			condition.setXxz("1");
		}
		condition.setDivId("2");
		this.pageSet1 = hztjService.medLevelSetting(pageSet,condition);
		Map map5 = new HashMap<String,String>();
		Map map8 = new HashMap<String,String>();
		if(pageSet1.getCount()>0){
			map5 = (HashMap<String,String>)this.getPageSet1().getData().get(4);
			map8 = (HashMap<String,String>)this.getPageSet1().getData().get(7);
			map1.putAll((HashMap<String,String>)this.getPageSet1().getData().get(4));
			map2.putAll((HashMap<String,String>)this.getPageSet1().getData().get(5));
			map3.putAll((HashMap<String,String>)this.getPageSet1().getData().get(6));
			map4.putAll((HashMap<String,String>)this.getPageSet1().getData().get(7));
			condition.setTsxz(String.valueOf(map5.get("TXXZ")));
			condition.setTxxz(String.valueOf(map8.get("TSXZ")));
			List<Map> list = new ArrayList<Map>();
			list.add(0, map1);
			list.add(1, map2);
			list.add(2, map3);
			list.add(3, map4);
			this.pageSet.setData(list);
		}else{
			condition.setTsxz("1");
			condition.setTxxz("1");
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
//		List tpgList = hztjService.queryTbParaGradeById(condition);
//		for(int i=0;i<tpgList.size();i++){
//			Object[] obj = (Object[])tpgList.get(i);
//			if(obj[5].toString().contains("非处方药")){
//				condition.setId(obj[0].toString());
//				condition.setSxz(obj[1].toString());
//				condition.setXxz(obj[2].toString());
//				condition.setFcfName(obj[5].toString());
//			}else{
//				condition.setTid(obj[0].toString());
//				condition.setTsxz(obj[1].toString());
//				condition.setTxxz(obj[2].toString());
//				condition.setCfName(obj[5].toString());
//			}
//			condition.setTcdm(obj[3].toString());
//			condition.setMedlev(obj[4].toString());
//		}
		this.page = this.ROOR_PATH + "/hztj/updateCostRange.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 保存修改的费用范围
	 */
	public void saveCostRange(){
		String templev = this.getParameter("medlev");//药店等级
		String id = this.getParameter("id");
		String tid = this.getParameter("tid");
		String sxz = this.getParameter("sxz");
		String xxz = this.getParameter("xxz");
		condition = new QueryCondition();
		condition.setId(id);
		condition.setTid(tid);
		condition.setSxz(sxz);
		condition.setXxz(xxz);
		condition.setMedlev(templev);
		hztjService.saveCostRange(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 药店等级差值设置
	 * @return
	 */
	public String levelScoreSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/levelScoreSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店等级差值设置分值保存
	 */
	public void saveLevelScore(){
		String tempqxm = this.getParameter("tempqxm");//权重名
		String tempqxz = this.getParameter("tempqxz");//权重值
		String temptcdm = this.getParameter("temptcdm");//统筹代码
		String divId = this.getParameter("divId");//页签
		String jbdm = this.getParameter("jbdm");//级别代码
		String gzbz = this.getParameter("gzbz");//规则标识
		condition = new QueryCondition();
		condition.setQzm(tempqxm);
		condition.setQzz(tempqxz);
		condition.setTcdm(temptcdm);
		condition.setDivId(divId);
		condition.setJbdm(jbdm);
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.saveLevelScore(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 参保人购药排名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String cbrRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		if (StringUtils.isNotEmpty(condition.getType())) {
			
			if(StringUtils.isNotEmpty(condition.getByYear())){
				if(condition.getType().equals("2")){
					condition.setByMonth(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月");
				}else if(condition.getType().equals("4")){
					condition.setByZrYear(condition.getByYear());
					condition.setYearMonth(condition.getByYear()+"年");
				}else if(condition.getType().equals("1")){
					condition.setByYbYear(condition.getByYear());
					condition.setYearMonth(condition.getByYear()+"年");
				}else{
					condition.setByQuarter(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度");
				}
			}
			if ("1".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + "年度";
			}
			if ("2".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
				endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
			}
			if ("3".equals(condition.getType())) {
				String quarter = condition.getByQuarter();
				if (condition.getByQuarter().endsWith("1")) {
					startDate = quarter.substring(0, 4) + "第一季度";
				}
				if (condition.getByQuarter().endsWith("2")) {
					startDate = quarter.substring(0, 4) + "第二季度";
				}
				if (condition.getByQuarter().endsWith("3")) {
					startDate = quarter.substring(0, 4) + "第三季度";
				}
				if (condition.getByQuarter().endsWith("4")) {
					startDate = quarter.substring(0, 4) + "第四季度";
				}
			}
			if ("4".equals(condition.getType())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				Date date = new Date();
				String format = sdf.format(date);
				startDate = format.substring(0, 4) + "01";
				endDate = format;
			}
		}
			
		this.pageSet = hztjService.cbrRanking(pageSet,condition);
		
		int i = 0;
		List data = pageSet.getData();
		int start = pageSet.getStart();
		if (start == 0) {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + 1);
				i++;
			}
		}else {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + start + 1);
				i++;
			}
		}
		
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		String UIDate = "";
		if ("1".equals(condition.getType()) || "3".equals(condition.getType())) {
			UIDate = startDate;
		}else {
			UIDate = startDate + "~" + endDate;
		}
		this.setAttribute("UIDate", UIDate);
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/cbrRanking.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店刷卡频次统计计划查询
	 * @return
	 */
	public String swipeFrequencyStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
			this.pageSet.setStart(0);
		}
		this.pageSet = hztjService.swipeFrequencyStatistics(pageSet,condition);
		if(pageSet.getData().size()>0){
			for(int i=0;i<pageSet.getData().size();i++){
				Map map = new HashMap<String,String>();
				map = (HashMap<String,String>)pageSet.getData().get(i);
				if(String.valueOf(map.get("PARA6")).contains(",")){
					String[] aa = String.valueOf(map.get("PARA6")).split(",");
					String akb021 = "";
					for(int j=0;j<aa.length;j++){
						String tempakb021 = hztjService.queryAkb021(aa[j]);
						if(j==aa.length-1){
							akb021 += tempakb021;
						}else{
							akb021 += tempakb021 + ",";
						}
					}
					map.put("AKB021", akb021);
				}
			}
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//就诊类型Map
//		Map aka130Map = commonService.selectDicInfo("AKA130");
		Map aka130Map = new LinkedHashMap();
		aka130Map.put("5000,5099", "购药（处方药）");
		aka130Map.put("5100,5199", "购药（非处方药）");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/hztj/swipeFrequencyStatistics.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店刷卡频次统计生成计划
	 */
	public String generatePlan(){
		hztjService.generatePlan(condition);
		return swipeFrequencyStatistics();
	}
	
	/**
	 * 计划查询
	 * @return
	 */
	public String queryGenerateDetail(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.queryGenerateDetail(pageSet,condition);
		String startDate = "";
		String endDate = "";
		String ydNames = "";
		String endTime = "";
		int sjjg = 0;
		String sjlb = "";
		try {
			if(StringUtils.isNotEmpty(condition.getStartDate())){
				startDate = URLDecoder.decode(condition.getStartDate(), "utf-8");
			}
			if(StringUtils.isNotEmpty(condition.getEndDate())){
				endDate = URLDecoder.decode(condition.getEndDate(), "utf-8");
			}
			if(StringUtils.isNotEmpty(condition.getYdNames())){
				ydNames = URLDecoder.decode(condition.getYdNames(), "utf-8");
			}
//			if(null != condition.getSjjg()){
//				sjjg = Integer.parseInt(URLDecoder.decode(condition.getSjlb(), "utf-8").substring(0, URLDecoder.decode(condition.getSjlb(), "utf-8").length()-2));
//				sjlb = URLDecoder.decode(condition.getSjlb(), "utf-8").substring(URLDecoder.decode(condition.getSjlb(), "utf-8").length()-2, URLDecoder.decode(condition.getSjlb(), "utf-8").length());
//			}else{
//				sjlb = condition.getSjlb();
//			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		condition.setYdNames(ydNames);
//		condition.setSjjg(sjjg);
//		condition.setSjlb(sjlb);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//就诊类型Map
//		Map aka130Map = commonService.selectDicInfo("AKA130");
		Map aka130Map = new LinkedHashMap();
		aka130Map.put("5000", "购药（处方药）");
		aka130Map.put("5099", "购药（处方药）");
		aka130Map.put("5100", "购药（非处方药）");
		aka130Map.put("5199", "购药（非处方药）");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/hztj/queryGenerateDetail.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 显示明细
	 * @return
	 */
	public String queryPlanDetail(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.queryPlanDetail(pageSet,condition);
		//人员类别Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		this.setAttribute("akc021Map", akc021Map);
		this.page = this.ROOR_PATH + "/hztj/queryPlanDetail.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 就诊明细
	 * @return
	 */
	public String queryJzmxDetail(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.queryJzmxDetail(pageSet,condition);
		//就诊类型Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		Map aka130Map = commonService.selectDicInfo("AKA130");
//		Map aka130Map = new LinkedHashMap();
//		aka130Map.put("5000", "购药（处方药）");
//		aka130Map.put("5100", "购药（非处方药）");
		this.setAttribute("aka130Map", aka130Map);
		this.setAttribute("akc021Map", akc021Map);
		this.page = this.ROOR_PATH + "/hztj/queryJzmxDetail.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店等级设置查询
	 * @return
	 */
	public String queryMedLevel(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.queryMedLevel(pageSet,condition);
		//区县Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/hztj/queryMedLevel.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 查询制定计划数量
	 * @throws Exception 
	 */
	public void queryGeneratePlan() throws Exception{
		QueryCondition con = new QueryCondition();
		String aaa027 = this.getParameter("aaa027");
		con.setAaa027(aaa027);
		//根据区县查询日常参数
		List list = hztjService.queryGeneratePlan(con);
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/json");  
	    response.setCharacterEncoding("UTF-8");  
	    PrintWriter writer = response.getWriter();
		JSONArray array = JSONArray.fromObject(list);
		writer.print(array.toString());
	}
	
	/**
	 * 根据规则标识废除权重数据
	 */
	public void updateSet(){
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setGzbz(Long.parseLong(gzbz));
		hztjService.updateSet(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 单行新增权重保存
	 */
	public void saveSingleRow(){
		String gzbz = this.getParameter("gzbz");
		String qzm = this.getParameter("qzm");
		String qzz = this.getParameter("qzz");
		String aaa027 = this.getParameter("aaa027");
		condition = new QueryCondition();
		condition.setGzbz(Long.parseLong(gzbz));
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(aaa027);
		hztjService.saveSingleRow(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 历史参数查询
	 * @return
	 */
	public String queryHistoryStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		if(condition == null){
			condition = new QueryCondition();
		}
		this.pageSet = hztjService.queryHistoryStatistics(pageSet,condition);
		//区县Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/queryHistoryStatistics.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 查看详细设置
	 * @return
	 */
	public String viewGrowSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.viewGrowSetting(pageSet,condition);
		//区县Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		String jspRoot = "";
		if(condition.getType().equals("1")){
			//增长率详细设置查询
			jspRoot = "/hztj/viewGrowSetting.jsp";
		}else if(condition.getType().equals("4")){
			//增长率详细设置查询-分值设置查询
			jspRoot = "/hztj/viewScoreSetting.jsp";
		}else if(condition.getType().equals("5")){
			//药店费用等级跨越 查看详细设置
			jspRoot = "/hztj/viewLevelScoreSetting.jsp";
		}else if(condition.getType().equals("6")){
			// 查看处方药非处方药权重设置 
			jspRoot = "/hztj/viewPrescriptionSetting.jsp";
		}else if(condition.getType().equals("7")){
			//单次非处方购药费用占比查看详细设置    
			jspRoot = "/hztj/viewSingleMedPay.jsp";
		}else if(condition.getType().equals("8")){
			//查看费用额设置
			jspRoot = "/hztj/viewAmountSetting.jsp";
		}else if(condition.getType().equals("9")){
			// 查看当年账户历年账户权重设置 
			jspRoot = "/hztj/viewOldAndNewSetting.jsp";
		}
		this.page = this.ROOR_PATH + jspRoot;
		return this.SUCCESS;
	}
	
	
	public String viewMedLevelSetting(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		condition.setDivId("1");
		// 查询数据库
		this.pageSet = hztjService.viewMedLevelSetting(pageSet,condition);
		//new新的map 把非处方和处方合并到一起
		Map map1 = new HashMap<String,String>();
		Map map2 = new HashMap<String,String>();
		Map map3 = new HashMap<String,String>();
		Map map4 = new HashMap<String,String>();
		if(pageSet.getCount()>0){
			map1 = (HashMap<String,String>)this.getPageSet().getData().get(0);
			map2 = (HashMap<String,String>)this.getPageSet().getData().get(1);
			map3 = (HashMap<String,String>)this.getPageSet().getData().get(2);
			map4 = (HashMap<String,String>)this.getPageSet().getData().get(3);
			condition.setSxz(String.valueOf(map1.get("XXZ")));
			condition.setXxz(String.valueOf(map4.get("SXZ")));
		}else{
			condition.setSxz("1");
			condition.setXxz("1");
		}
		condition.setDivId("2");
		this.pageSet1 = hztjService.viewMedLevelSetting(pageSet,condition);
		Map map5 = new HashMap<String,String>();
		Map map8 = new HashMap<String,String>();
		if(pageSet1.getCount()>0){
			map5 = (HashMap<String,String>)this.getPageSet1().getData().get(4);
			map8 = (HashMap<String,String>)this.getPageSet1().getData().get(7);
			map1.putAll((HashMap<String,String>)this.getPageSet1().getData().get(4));
			map2.putAll((HashMap<String,String>)this.getPageSet1().getData().get(5));
			map3.putAll((HashMap<String,String>)this.getPageSet1().getData().get(6));
			map4.putAll((HashMap<String,String>)this.getPageSet1().getData().get(7));
			condition.setTsxz(String.valueOf(map5.get("TXXZ")));
			condition.setTxxz(String.valueOf(map8.get("TSXZ")));
			List<Map> list = new ArrayList<Map>();
			list.add(0, map1);
			list.add(1, map2);
			list.add(2, map3);
			list.add(3, map4);
			this.pageSet.setData(list);
		}else{
			condition.setTsxz("1");
			condition.setTxxz("1");
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/viewMedLevelSetting.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 权重修改初始化页面 读取数据
	 * @return
	 */
	public String changeWeigth(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		this.pageSet = hztjService.changeWeigth(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/changeWeigth.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 权重值修改保存（根据GZBZ修改所有关联数据）
	 */
	public void saveParameterChangeWeigth(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String gzbz = this.getParameter("gzbz");
		String qzjb = this.getParameter("qzjb");
		String removeId = this.getParameter("removeId");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setGzbz(Long.parseLong(gzbz));
		condition.setQzjb(qzjb);
		//根据GZBZ查询TbParaHeavy表下的所有有效数据
		List<TbParaHeavy> tphList = hztjService.queryAllTbParaHeavyByGzbz(Long.parseLong(gzbz),removeId);
		//根据GZBZ查询TbParaGrade表下的所有有效数据
		List<TbParaGrade> tpgList = hztjService.queryAllTbParaGradeByGzbz(Long.parseLong(gzbz));
		hztjService.saveParameterChangeWeigth(condition,tphList,tpgList);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 药店费用等级费用范围修改保存
	 */
	public void saveUpdateCostRange(){
		String templev = this.getParameter("templev");//药店等级
		String tempname = this.getParameter("tempname");//非处方药分类名称
		String tempval = this.getParameter("tempval");//非处方药费用
		String tempcfname = this.getParameter("tempcfname");//处方药分类名称
		String tempcfval = this.getParameter("tempcfval");//处方药费用
		String temptcdm = this.getParameter("temptcdm");//统筹代码
		String tempId = this.getParameter("tempId");//权重ID
		String gzbz = this.getParameter("gzbz");
		condition = new QueryCondition();
		condition.setId(tempId);
		condition.setTcdm(temptcdm);
		condition.setMedlev(templev);
		condition.setFcfName(tempname);
		condition.setFcfValue(tempval);
		condition.setCfName(tempcfname);
		condition.setCfValue(tempcfval);
		condition.setGzbz(Long.parseLong(gzbz));
		//根据GZBZ查询TbParaHeavy表下的所有有效数据
		List<TbParaHeavy> tphList = hztjService.queryAllTbParaHeavyByGzbz(Long.parseLong(gzbz),"");
		//根据GZBZ查询TbParaGrade表下的所有有效数据
		List<TbParaGrade> tpgList = hztjService.queryAllTbParaGradeByGzbz(Long.parseLong(gzbz));
		hztjService.saveUpdateCostRange(condition,tphList,tpgList);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 初始化修改页面（tab页）
	 * @return
	 */
	public String updateScore(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		if (null == this.pageSet1) {
			this.pageSet1 = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库(divId=1)
		this.pageSet = hztjService.updateScore(pageSet,condition);
		// 查询数据库(divId=2)
		if(condition.getQzjb().equals("7")){
			condition.setQzjb(GlobalConstants.DIC_QZJB_8);
		}else if(condition.getQzjb().equals("9")){
			condition.setQzjb(GlobalConstants.DIC_QZJB_10);
		}
		this.pageSet1 = hztjService.updateScore(pageSet1,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/updateScore.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 分值保存
	 */
	public void saveUpdateScore(){
		String qzm = this.getParameter("tempqxm");
		String qzz = this.getParameter("tempqxz");
		String tcdm = this.getParameter("temptcdm");
		String qzdm = this.getParameter("qzdm");
		String divId = this.getParameter("divId");
		String gzbz = this.getParameter("gzbz");
		String qzjb = this.getParameter("qzjb");
		condition = new QueryCondition();
		condition.setQzm(qzm);
		condition.setQzz(qzz);
		condition.setTcdm(tcdm);
		condition.setQzdm(qzdm);
		condition.setDivId(divId);
		condition.setGzbz(Long.parseLong(gzbz));
		condition.setQzjb(qzjb);
		//根据GZBZ查询TbParaHeavy表下的所有有效数据
		List<TbParaHeavy> tphList = hztjService.queryAllTbParaHeavyByGzbz(Long.parseLong(gzbz),"");
		//根据GZBZ查询TbParaGrade表下的所有有效数据
		List<TbParaGrade> tpgList = hztjService.queryAllTbParaGradeByGzbz(Long.parseLong(gzbz));
		String tempGzbz = hztjService.saveUpdateScore(condition,tphList,tpgList);
		this.ajax("{\"success\":true,\"result\":["+tempGzbz+"]}", "text/json");
	}
	
	/**
	 * 上一年度药店等级设置（药店信息查询）
	 * @return
	 */
	public String setYdLevel(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		if(null == condition){
			condition = new QueryCondition();
		}
		this.pageSet = hztjService.setYdLevel(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/setYdLevel.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 打开保存/修改页面
	 * @return
	 */
	public String saveSetYdLevel(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.setYdLevel(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/saveSetYdLevel.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店等级保存或修改
	 */
	public void saveOrUpdYdLevel(){
		String id = this.getParameter("id");
		String yddj = this.getParameter("yddj");
		String akb020 = this.getParameter("akb020");
		String akb021 = this.getParameter("akb021");
		String aaa027 = this.getParameter("aaa027");
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.setId(id);
		queryCondition.setAaa027(aaa027);
		queryCondition.setAkb020(akb020);
		queryCondition.setAkb021(akb021);
		queryCondition.setYddj(yddj);
		hztjService.saveOrUpdYdLevel(queryCondition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 药店分数查询
	 * @return
	 */
	public String queryYdFs(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		if(null != condition){
			if(StringUtils.isNotEmpty(condition.getByYear())){
				if(condition.getType().equals("2")){
					condition.setByMonth(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月");
				}else{
					condition.setByQuarter(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度");
				}
			}
			this.pageSet = hztjService.queryYdFs(pageSet,condition);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/queryYdFs.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 导出权重
	 */
	public void expQz(){
		this.pageSet = new PageSet(999999999, 0);
		this.pageSet1 = new PageSet(999999999, 0);
		this.pageSet2 = new PageSet(999999999, 0);
		// 查询权重表
		this.pageSet = hztjService.queryAllQzHeavy(pageSet,condition);
		// 查询参数表
		condition.setDivId("1");
		// 查询数据库
		this.pageSet1 = hztjService.queryAllQzGrade(pageSet1,condition);
		//new新的map 把非处方和处方合并到一起
		Map map1 = new HashMap<String,String>();
		Map map2 = new HashMap<String,String>();
		Map map3 = new HashMap<String,String>();
		Map map4 = new HashMap<String,String>();
		if(pageSet1.getCount()>0){
			map1 = (HashMap<String,String>)this.getPageSet1().getData().get(0);
			map2 = (HashMap<String,String>)this.getPageSet1().getData().get(1);
			map3 = (HashMap<String,String>)this.getPageSet1().getData().get(2);
			map4 = (HashMap<String,String>)this.getPageSet1().getData().get(3);
			condition.setSxz(String.valueOf(map1.get("XXZ")));
			condition.setXxz(String.valueOf(map4.get("SXZ")));
		}
		condition.setDivId("2");
		this.pageSet2 = hztjService.queryAllQzGrade(pageSet2,condition);
		Map map5 = new HashMap<String,String>();
		Map map8 = new HashMap<String,String>();
		if(pageSet2.getCount()>0){
			map5 = (HashMap<String,String>)this.getPageSet2().getData().get(4);
			map8 = (HashMap<String,String>)this.getPageSet2().getData().get(7);
			map1.putAll((HashMap<String,String>)this.getPageSet2().getData().get(4));
			map2.putAll((HashMap<String,String>)this.getPageSet2().getData().get(5));
			map3.putAll((HashMap<String,String>)this.getPageSet2().getData().get(6));
			map4.putAll((HashMap<String,String>)this.getPageSet2().getData().get(7));
			condition.setTsxz(String.valueOf(map5.get("TXXZ")));
			condition.setTxxz(String.valueOf(map8.get("TSXZ")));
			List<Map> list = new ArrayList<Map>();
			list.add(0, map1);
			list.add(1, map2);
			list.add(2, map3);
			list.add(3, map4);
			this.pageSet1.setData(list);
		}
		//就诊类型Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		try {
			hztjService.resultSetToExcel(pageSet,pageSet1,aaa027Map,condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 药店与区县对应关系维护
	 * @return
	 */
	public String ydHosEdit(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.pharmacyMaintenance(pageSet, condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/ydHosEdit.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店总数据统计
	 * @return
	 */
	public String ydTotalStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.ydTotalStatistics(pageSet, condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/ydTotalStatistics.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店月接诊人次、费用移动平均
	 * @return
	 */
	public String ydMonthMove(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.ydMonthMove(pageSet, condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/ydMonthMove.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店月（季、年）接诊非处方药人次、费用、次均费用
	 * @return
	 */
	public String ydCostStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.ydCostStatistics(pageSet, condition);
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String date = startDate + "~" + endDate;
		this.setAttribute("date", date);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		Map fcfValueMap = commonService.selectDicInfo("FCFVALUE");
		this.setAttribute("fcfValueMap", fcfValueMap);
		this.page = this.ROOR_PATH + "/ydfx/ydCostStatistics.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 参保人药店购药按参保地与就医地相分离要素排名
	 * @return
	 */
	public String ydCostRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.ydCostRanking(pageSet, condition);
		int i = 0;
		List data = pageSet.getData();
		int start = pageSet.getStart();
		if (start == 0) {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + 1);
				i++;
			}
		}else {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + start + 1);
				i++;
			}
		}
		//统筹区Map
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String date = startDate + "~" + endDate;
		this.setAttribute("date", date);
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		Map xzlbMap = commonService.selectDicInfo("XZLB");
		this.setAttribute("xzlbMap", xzlbMap);
		this.page = this.ROOR_PATH + "/ydfx/ydCostRanking.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 参保地与就医地相分离人员月平均费用排名
	 * @return
	 */
	public String ydMonthCostRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.ydMonthCostRanking(pageSet, condition);
		
		int i = 0;
		List data = pageSet.getData();
		int start = pageSet.getStart();
		if (start == 0) {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + 1);
				i++;
			}
		}else {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + start + 1);
				i++;
			}
		}
		
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String date = startDate + "~" + endDate;
		this.setAttribute("date", date);
		//统筹区Map
		Map xzlbMap = commonService.selectDicInfo("XZLB");
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("xzlbMap", xzlbMap);
		this.page = this.ROOR_PATH + "/ydfx/ydMonthCostRanking.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医生开外配处方次数、费用排名（总的排名）
	 * @return
	 */
	public String docWpcfRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.docWpcfRanking(pageSet, condition);
		int i = 0;
		List data = pageSet.getData();
		int start = pageSet.getStart();
		if (start == 0) {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + 1);
				i++;
			}
		}else {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + start + 1);
				i++;
			}
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/docWpcfRanking.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医生开外配处方次数、费用排名（按药店查询）
	 * @return
	 */
	public String docYdWpcfRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		this.pageSet = hztjService.docYdWpcfRanking(pageSet, condition);
		
		int i = 0;
		List data = pageSet.getData();
		int start = pageSet.getStart();
		if (start == 0) {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + 1);
				i++;
			}
		}else {
			for (Object object : data) {
				Map hashMap = (Map) data.get(i);
				hashMap.put("rankLevel", i + start + 1);
				i++;
			}
		}
		
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/docYdWpcfRanking.jsp";
		return this.SUCCESS;
	}

	/**
	 * 药店药品使用排名（按药品查询）
	 * @return
	 */
	public String ydYpsyRankingYp(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/ydYpsyRankingYp.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 药店药品使用排名（按药店查询）
	 * @return
	 */
	public String ydYpsyRankingYd(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		this.pageSet = hztjService.ydYpsyRankingYd(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/hztj/ydYpsyRankingYd.jsp";
		return this.SUCCESS;
	}
}



