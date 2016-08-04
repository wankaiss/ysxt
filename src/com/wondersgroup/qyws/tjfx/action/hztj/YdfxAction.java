package com.wondersgroup.qyws.tjfx.action.hztj;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.service.CommonService;
import com.wondersgroup.qyws.tjfx.service.YdfxService;
import com.wondersgroup.util.papper.PageSet;


@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class YdfxAction extends BaseAction{

	@Autowired
	private YdfxService ydfxService;
	
	@Autowired
	private CommonService commonService;
	
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
		this.page = this.ROOR_PATH + "/ydfx/ydfx.jsp";
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
		
		if (condition != null) {
			if(StringUtils.isNotEmpty(condition.getByYear())){
				if(condition.getType().equals("2")){
					condition.setByMonth(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年"+condition.getByYear().substring(4, 6)+"月");
				}else{
					condition.setByQuarter(condition.getByYear());
					condition.setYearMonth(condition.getByYear().substring(0, 4)+"年第"+condition.getByYear().substring(4, 6)+"季度");
				}
			}
			this.pageSet = ydfxService.hosAggregateAnalysis(pageSet,condition);
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
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		
		this.page = this.ROOR_PATH + "/ydfx/hosAggregateAnalysis.jsp";
		return this.SUCCESS;
	}	
	
	/**
	 * 参保人购药排名
	 * @return
	 */
	public String cbrRanking(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
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
//		this.pageSet = hztjService.swipeFrequencyStatistics(pageSet,condition);
//		if(pageSet.getData().size()>0){
////			for(int i=0;i<pageSet.getData().size();i++){
//				Map map = new HashMap<String,String>();
//				map = (HashMap<String,String>)pageSet.getData().get(i);
//				if(String.valueOf(map.get("PARA6")).contains(",")){
//					String[] aa = String.valueOf(map.get("PARA6")).split(",");
//					String akb021 = "";
//					for(int j=0;j<aa.length;j++){
//						String tempakb021 = ydfxService.queryAkb021(aa[j]);
//						if(j==aa.length-1){
//							akb021 += tempakb021;
//						}else{
//							akb021 += tempakb021 + ",";
//						}
//					}
//					map.put("AKB021", akb021);
//				}
//			}
	//	}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//就诊类型Map
//		Map aka130Map = commonService.selectDicInfo("AKA130");
		Map aka130Map = new LinkedHashMap();
		aka130Map.put("5000,5099", "购药（处方药）");
		aka130Map.put("5100,5199", "购药（非处方药）");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/ydfx/swipeFrequencyStatistics.jsp";
		return this.SUCCESS;
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
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
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
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
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
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/ydYpsyRankingYp.jsp";
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
//		this.pageSet = hztjService.levelScoreSetting(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/ydYpsyRankingYd.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 参保人导出Excel
	 */
	public void exportExcelCbr(){
		this.pageSet = new PageSet(999999999, 0);
		
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
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.cbrRanking(pageSet,condition);
		}
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
		String UIDate = "";
		
		if ("1".equals(condition.getType()) || "3".equals(condition.getType())) {
			UIDate = startDate;
		}else {
			UIDate = startDate + "~" + endDate;
		}
		
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[27];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("YBH");
			obj[2] = map.get("XM");
			obj[3] = aaa027Map.get(map.get("CBXZQH"));
			obj[4] = map.get("FYZE");
			obj[5] = map.get("MC");
			obj[6] = aaa027Map.get(map.get("XZQH"));
			obj[7] = map.get("rankLevel");
			rs.add(obj);
		}
		String[] top = {"时间","医保号","姓名","区县","总费用","费用最高药店名称","费用最高药店所属区县","总费用排名"};
		try {
			ydfxService.resultSetToExcelDoctor(rs, top,"医生开外配处方次数、费用排名（总的排名）", "外配处方排名（总）");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 总费用统计导出EXCEL
	 */
	public String exportExcelydTotalStatistics(){
		this.condition = new QueryCondition();
		this.pageSet = new PageSet(999999999, 0);
		String startDate = this.getParameter("startDate");
		String endDate = this.getParameter("endDate");
		String ydIds = this.getParameter("ydIds");	
		String ydNames = this.getParameter("ydNames");
		String aaa027 = this.getParameter("aaa027");
		this.condition.setStartDate(startDate);
		this.condition.setEndDate(endDate);
		this.condition.setYdIds(ydIds);
		this.condition.setYdNames(ydNames);
		this.condition.setAaa027(aaa027);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.ydTotalStatistics(pageSet,condition);
		}
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = map.get("YD_ID");
			obj[1] = map.get("NAME");
			obj[2] = aaa027Map.get(map.get("AAA027"));
			obj[3] = map.get("ZRC");
			obj[4] = map.get("FYZE");
			obj[5] = map.get("FCFRC");
			obj[6] = map.get("FCFFY");
			obj[7] = map.get("CFRC");
			obj[8] = map.get("CFFY");
			rs.add(obj);
		}
		String[] top = {"药店代码","药店名称","区县","接诊总人次","总费用","非处方接诊人次","非处方接诊费用","处方接诊人次","处方接诊费用"};
		String result = null;
		try {
			result = ydfxService.resultSetToExcelYdTotalStatistics(rs, top,"药店总数据统计", "总数据统计");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxJsonSuccessMessage(result);
	}
	
	public void exportExcelYdMonthMove(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.ydMonthMove(pageSet,condition);
		}
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = map.get("JSNY");
			obj[1] = map.get("YD_ID");
			obj[3] = map.get("NAME");
			obj[2] = aaa027Map.get(map.get("AAA027"));
			obj[4] = map.get("PJFCFRC");
			obj[5] = map.get("PJFCFFY");
			obj[6] = map.get("PJCFRC");
			obj[7] = map.get("PJCFFY");
			rs.add(obj);
		}
		String[] top = {"时间","药店代码","药店名称","区县","平均每天接诊人次","平均每天接诊费用","平均每天接诊人次","平均每天接诊费用"};
		try {
			ydfxService.exportExcelYdMonthMove(rs, top,"药店月接诊人次、费用移动平均", "费用移动平均");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 药店月（季、年）接诊非处方药人次、费用、次均费用(药店月费用)
	 */
	public void exportExcelYdCostStatistics(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.ydCostStatistics(pageSet,condition);
		}
		String UIDate = "";
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
		endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
		UIDate = startDate + "~" + endDate;
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("YD_ID");
			obj[2] = map.get("YDNAME");
			obj[3] = aaa027Map.get(map.get("AAA027"));
			obj[4] = map.get("FCFRC");
			obj[5] = map.get("FCFFY");
			obj[6] = map.get("FCFCJFY");
			obj[7] = "";
			obj[8] = "";
			obj[9] = "";
			obj[10] = map.get("CFRC");
			obj[11] = map.get("CFFY");
			obj[12] = map.get("CFPJRC");
			rs.add(obj);
		}
		String[] top = {"时间","药店代码","药店名称","区县","接诊人次","费用","次均费用","人次数","人次占比", "费用占比", "接诊人次", "费用", "次均费用"};
		try {
			ydfxService.exportExcelYdCostStatistics(rs, top,"药店月（季、年）接诊非处方药人次、费用、次均费用", "药店月费用");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 参保人药店购药按参保地与就医地相分离要素排名(分离要素排名)
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void exportExcelYdCostRanking(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.ydCostRanking(pageSet,condition);
		}
		
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
		
		String UIDate = "";
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
		endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
		UIDate = startDate + "~" + endDate;
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		Map xzlbMap = commonService.selectDicInfo("XZLB");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("YBH");
			obj[2] = map.get("XM");
			obj[3] = xzlbMap.get(map.get("XZLB"));
			obj[4] = aaa027Map.get(map.get("CBXZQH"));
			obj[5] = aaa027Map.get(map.get("JYQHDM"));
			obj[6] = map.get("FYZE");
			obj[7] = map.get("MC");
			obj[8] = map.get("CFFY");
			obj[9] = map.get("FCFFY");
			obj[10] = map.get("rankLevel");
			rs.add(obj);
		}
		String[] top = {"时间","医保号","姓名","待遇类型","参保地区县","就医地区县","购药总费用","费用最高药店","处方药费用", "非处方药费用", "总费用排名"};
		try {
			ydfxService.exportExcelYdCostRanking(rs, top,"参保人药店购药按参保地与就医地相分离要素排名", "分离要素排名");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 参保地与就医地相分离人员月平均费用排名(月平均费用排名)
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void exportExcelYdMonthCostRanking(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.ydMonthCostRanking(pageSet,condition);
		}
		
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
		
		String UIDate = "";
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
		endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
		UIDate = startDate + "~" + endDate;
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		Map xzlbMap = commonService.selectDicInfo("XZLB");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("YBH");
			obj[2] = map.get("XM");
			obj[3] = xzlbMap.get(map.get("XZLB"));
			obj[4] = aaa027Map.get(map.get("CBXZQH"));
			obj[5] = aaa027Map.get(map.get("JYQHDM"));
			obj[6] = map.get("FYZE");
			obj[7] = map.get("YPJFY");
			obj[8] = map.get("rankLevel");
			rs.add(obj);
		}
		String[] top = {"时间","医保号","姓名","待遇类型","参保地区县","就医地区县","总费用","月平均费用","月平均费用排名"};
		try {
			ydfxService.exportExcelYdMonthCostRanking(rs, top,"参保地与就医地相分离人员月平均费用排名", "月平均费用排名");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 医生开外配处方次数、费用排名（总的排名）(外配处方排名（总）)
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void exportExcelDocWpcfRanking(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.docWpcfRanking(pageSet,condition);
		}
		
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
		
		String UIDate = "";
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
		endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
		UIDate = startDate + "~" + endDate;
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("DDJG_ID");
			obj[2] = map.get("NAME");
			obj[3] = map.get("XM");
			obj[4] = map.get("WPCFCS");
			obj[5] = map.get("WPCFFY");
			obj[6] = map.get("rankLevel");
			rs.add(obj);
		}
		String[] top = {"时间","医院代码","医院名称","医生姓名","外配处方次数","外配处方费用","外配处方费用排名"};
		try {
			ydfxService.exportExcelDocWpcfRanking(rs, top,"医生开外配处方次数、费用排名（总的排名）", "外配处方排名（总）");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 医生开外配处方次数、费用排名（按药店查询）(外配处方排名)
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void exportExcelDocYdWpcfRanking(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = ydfxService.docYdWpcfRanking(pageSet,condition);
		}
		
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
		
		String UIDate = "";
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		startDate = date1.substring(0, 4) + "年" + date1.substring(5, 7) + "月";
		endDate = date2.substring(0, 4)	+ "年" + date2.substring(5, 7) + "月";
		UIDate = startDate + "~" + endDate;
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = UIDate;
			obj[1] = map.get("DDJG_ID");
			obj[2] = map.get("NAME");
			obj[3] = map.get("XM");
			obj[4] = map.get("WPCFCS");
			obj[5] = map.get("WPCFFY");
			obj[6] = map.get("rankLevel");
			rs.add(obj);
		}
		String[] top = {"时间","医院代码","医院名称","医生姓名","外配处方次数","外配处方费用","外配处方费用排名"};
		try {
			ydfxService.exportExcelDocYdWpcfRanking(rs, top,"医生开外配处方次数、费用排名（按药店查询）", "外配处方排名");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String docWpcfDetail() throws UnsupportedEncodingException {
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String date1 = URLDecoder.decode(condition.getStartDate().toString(), "UTF-8");
		String date2 = URLDecoder.decode(condition.getEndDate().toString(), "UTF-8");
		String akb021 = URLDecoder.decode(condition.getAkb021().toString(), "UTF-8");
		String ydName = URLDecoder.decode(condition.getYdNames().toString(), "UTF-8");
		//String akb020 = URLDecoder.decode(condition.getAkb020().toString(), "UTF-8");
		String UIDate = date1 + "~" + date2;
		this.pageSet = ydfxService.docWpcfDetail(pageSet, condition);
		condition.setAkb021(akb021);
		//condition.setAkb020(akb020);
		condition.setYdNames(ydName);
		condition.setStartDate(UIDate);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");	
		this.setAttribute("aaa027Map", aaa027Map);
		this.page = this.ROOR_PATH + "/ydfx/docWpcfDetail.jsp";
		return this.SUCCESS;
	}
	
}
