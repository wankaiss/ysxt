package com.wondersgroup.qyws.tjfx.action.hztj;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.service.CommonService;
import com.wondersgroup.qyws.tjfx.service.ZyypService;
import com.wondersgroup.qyws.tjfx.util.ExportExcelUtil;
import com.wondersgroup.util.papper.PageSet;

@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class ZyypAction extends BaseAction{

	@Autowired
	private ZyypService zyypService;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 中药饮片总量统计
	 * @return
	 */
	public String totalStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			String month = this.getParameter("lastMonth");
			if(StringUtils.isNotEmpty(month)){
				Calendar c = Calendar.getInstance();
				c.add(c.MONTH,-1);//得到上个月的月份  
				Date d = c.getTime(); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				condition.setYearMonth(sdf.format(d));
				condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
				condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			}
			this.pageSet = zyypService.totalStatistics(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/zyyp.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 共用下拉框选择加载方法
	 */
	private void select() {
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		//人员类别Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//机构类别Map
		Map akb022Map = commonService.selectDicInfo("AKB022");
		//医院等级Map
		Map aka101Map = commonService.selectDicInfo("AKA101");
		this.setAttribute("aka130Map", aka130Map);
		this.setAttribute("akc021Map", akc021Map);
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("akb022Map", akb022Map);
		this.setAttribute("aka101Map", aka101Map);
	}

	/**
	 * 医疗机构选择
	 * @return
	 */
	public String ydHosSelect(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.queryHosInfo(pageSet,condition);
		}
		//医院等级Map
		Map aka101Map = commonService.selectDicInfo("AKA101");
		this.setAttribute("aka101Map", aka101Map);
		this.page = this.ROOR_PATH + "/zyyp/ydHosSelect.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 导出Excel
	 */
	public void exportExcel(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.totalStatistics(pageSet,condition);
		}
		//机构类别Map
		Map akb022Map = commonService.selectDicInfo("AKB022");
		//医院等级Map
		Map aka101Map = commonService.selectDicInfo("AKA101");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[20];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
			//特病非外配处方
			obj[0] = map.get("JSNY");
			obj[1] = akb022Map.get(map.get("HOSP_TYPE"));
			obj[2] = map.get("HOSP_ID");
			obj[3] = map.get("NAME");
			obj[4] = aka101Map.get(map.get("HOSP_CLASS"));
			obj[5] = map.get("ZFY");
			obj[6] = df.format(Double.parseDouble(String.valueOf(map.get("ZZZL"))));
			obj[7] = map.get("ZTS");
			obj[8] = df.format(Double.parseDouble(String.valueOf(map.get("ZJTJG"))));
			obj[9] = df.format(Double.parseDouble(String.valueOf(map.get("ZJTJGZZL"))));
			obj[10] = map.get("FWPFY");
			obj[11] = df.format(Double.parseDouble(String.valueOf(map.get("FWPZZL"))));
			obj[12] = map.get("FWPTS");
			obj[13] = df.format(Double.parseDouble(String.valueOf(map.get("FWPJTJG"))));
			obj[14] = df.format(Double.parseDouble(String.valueOf(map.get("FWPJTJGZZL"))));
			obj[15] = map.get("WPFY");
			obj[16] = df.format(Double.parseDouble(String.valueOf(map.get("WPZZL"))));
			obj[17] = map.get("WPTS");
			obj[18] = df.format(Double.parseDouble(String.valueOf(map.get("WPJTJG"))));
			obj[19] = df.format(Double.parseDouble(String.valueOf(map.get("WPJTJGZZL"))));
			rs.add(obj);
		}
		String[] top = {"结算年月","机构类别","机构代码","机构名称","医院等级","费用","增长率","贴数","贴均价格","贴均价格增长率","费用","增长率","贴数","贴均价格","贴均价格增长率","费用","增长率","贴数","贴均价格","贴均价格增长率"};
		try {
			zyypService.resultSetToExcel(rs, top,"医疗机构及药店中药饮片总量统计", "中药饮片总量统计");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单个医生的中药饮片费用及贴均价格查询
	 * @return
	 */
	public String singleDoctor(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.querySingleDoctor(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/singleDoctor.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医师选择
	 * @return
	 */
	public String docSelect(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getHosIds())){
				//单传一家医疗机构时自动去空格，把空格加上
				condition.setHosIds(condition.getHosIds()+"          ");
			}
			this.pageSet = zyypService.queryDoctorInfo(pageSet,condition);
		}
		this.page = this.ROOR_PATH + "/zyyp/docSelect.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 单个医师查询导出Excel
	 */
	public void exportExcelDoctor(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.querySingleDoctor(pageSet,condition);
		}
		//机构类别Map
		Map akb022Map = commonService.selectDicInfo("AKB022");
		//医院等级Map
		Map aka101Map = commonService.selectDicInfo("AKA101");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[27];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = map.get("JSNY");
			obj[1] = akb022Map.get(map.get("HOSP_TYPE"));
			obj[2] = map.get("DDJG_ID");
			obj[3] = map.get("NAME");
			obj[4] = aka101Map.get(map.get("HOSP_CLASS"));
			obj[5] = map.get("XM");
			obj[6] = map.get("ZGZS");
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
			//特病非外配处方
			obj[7] = map.get("TBFWPFY");
			obj[8] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPZZL"))));
			obj[9] = map.get("TBFWPTS");
			obj[10] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPJTJG"))));
			obj[11] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPJTJGZZL"))));
			//特病外配处方
			obj[12] = map.get("TBWPFY");
			obj[13] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPZZL"))));
			obj[14] = map.get("TBWPTS");
			obj[15] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPJTJG"))));
			obj[16] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPJTJGZZL"))));
			//非特病非外配处方
			obj[17] = map.get("FTBFWPFY");
			obj[18] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPZZL"))));
			obj[19] = map.get("FTBFWPTS");
			obj[20] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPJTJG"))));
			obj[21] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPJTJGZZL"))));
			//非特病外配处方
			obj[22] = map.get("FTBWPFY");        
			obj[23] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPZZL"))));       
			obj[24] = map.get("FTBWPTS");        
			obj[25] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPJTJG"))));      
			obj[26] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPJTJGZZL"))));   
			rs.add(obj);
		}
		String[] top = {"结算年月","机构类别","机构代码","机构名称","医院等级","医师姓名","医师资格证书编号","费用","增长率","贴数","贴均价格","贴均价格增长率","费用","增长率","贴数","贴均价格","贴均价格增长率","费用","增长率","贴数","贴均价格","贴均价格增长率","费用","增长率","贴数","贴均价格","贴均价格增长率"};
		try {
			zyypService.resultSetToExcelDoctor(rs, top,"单个医生的中药饮片费用及贴均价格", "单个医师查询");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单贴价格与味数查询
	 * @return
	 */
	public String singleCostQuery(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("2")){
				//显示明细
				this.pageSet = zyypService.singleCostQueryDetail(pageSet,condition);
			}else{
				//逐级下帖
				this.pageSet = zyypService.singleCostQuery(pageSet,condition);
			}
		}else{
			condition = new QueryCondition();
			condition.setDivId("1");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/singleCostQuery.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 查看费用明细
	 * @return
	 */
	public String queryCostDetail(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String akb021 = "";
		String ysxm = "";
		String aka130 = "";
		String akc050 = "";
		String aac003 = "";
		String wpddjg_id = "";
		String wpysxm = "";
		if(StringUtils.isNotEmpty(condition.getSign())){
			try {
				akb021 = URLDecoder.decode(condition.getAkb021(), "utf-8");
				ysxm = URLDecoder.decode(condition.getYsxm(), "utf-8");
				aka130 = URLDecoder.decode(condition.getAka130(), "utf-8");
				if(StringUtils.isNotEmpty(condition.getAkc050())&&!condition.getAkc050().equals("null")){
					akc050 = URLDecoder.decode(condition.getAkc050(), "utf-8");
				}
				aac003 = URLDecoder.decode(condition.getAac003(), "utf-8");
				if(StringUtils.isNotEmpty(condition.getWpysxm())&&!condition.getWpysxm().equals("null")){
					wpysxm = URLDecoder.decode(condition.getWpysxm(), "utf-8");
				}
				if(StringUtils.isNotEmpty(condition.getWpddjg_id())&&!condition.getWpddjg_id().equals("null")){
					wpddjg_id = URLDecoder.decode(condition.getWpddjg_id(), "utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(StringUtils.isNotEmpty(condition.getAkc194())){
				condition.setAkc194(condition.getAkc194());
			}
			condition.setAkb021(akb021);
			condition.setYsxm(ysxm);
			condition.setAka130(aka130);
			condition.setAkc050(akc050);
			condition.setAac003(aac003);
			condition.setWpysxm(wpysxm);
			condition.setWpddjg_id(wpddjg_id);
		}
		this.pageSet = zyypService.queryCostDetail(pageSet,condition);
		select();
		this.page = this.ROOR_PATH + "/zyyp/queryCostDetail.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 单贴价格与味数查询导出Excel
	 */
	public void exportExcelSingleCost(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("2")){
				//显示明细
				this.pageSet = zyypService.singleCostQueryDetail(pageSet,condition);
			}else if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("3")){
				this.pageSet = zyypService.queryCostDetail(pageSet,condition);
			}else{
				//逐级下帖
				this.pageSet = zyypService.singleCostQuery(pageSet,condition);
			}
		}
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		List<Object[]> rs = new ArrayList<Object[]>();
		int i = 0;
		if(condition.getDivId().equals("2")){
			i = 24;
		}else if(condition.getDivId().equals("3")){
			i = 10;
		}else{
			i = 14;
		}
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[i];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			if(condition.getDivId().equals("3")){
				obj[0] = map.get("MX_ID");
				obj[1] = map.get("MX_MC");
				obj[2] = map.get("FYZE");
				obj[3] = map.get("ZLBL");//
				obj[4] = map.get("TYM_MC");
				obj[5] = map.get("SL");
				obj[6] = map.get("YPTS");
				obj[7] = map.get("MCYL");
				obj[8] = map.get("YFBZ");//
				obj[9] = map.get("BZGG");//
			}else{
				obj[0] = map.get("XM");
				obj[1] = map.get("YBH");
				obj[2] = map.get("JZBH");
				obj[3] = aka130Map.get(map.get("JYJSLB"));
				obj[4] = map.get("ZD_NAME");
				obj[5] = map.get("TRADE_TIME");
				obj[6] = map.get("YSXM");
				obj[7] = map.get("DDJG_ID");
				obj[8] = map.get("NAME");
				obj[9] = map.get("DTJG");
				obj[10] = map.get("YPWS");
				obj[11] = map.get("WPNAME");//
				obj[12] = map.get("WPYSXM");//
				obj[13] = map.get("YPFY");
				if(condition.getDivId().equals("2")){
					obj[14] = map.get("MX_ID"); 
					obj[15] = map.get("MX_MC"); 
					obj[16] = map.get("FYZE");  
					obj[17] = map.get("ZLBL");//
					obj[18] = map.get("TYM_MC");
					obj[19] = map.get("SL");    
					obj[20] = map.get("YPTS");  
					obj[21] = map.get("MCYL");  
					obj[22] = map.get("YFBZ");//
					obj[23] = map.get("BZGG");//
				}
			}
			rs.add(obj);
		}
		String[] top = new String[i];
		String xlsName = "";
		String titleName = "";
		if(condition.getDivId().equals("3")){
			xlsName = "单贴价格与味数显示明细统计";
			titleName = "单贴价格与味数显示明细统计";
			top[0] = "明细项目编码";
			top[1] = "明细项目名称";
			top[2] = "明细金额";
			top[3] = "自付比例";
			top[4] = "通用名";
			top[5] = "药品数量";
			top[6] = "贴数";
			top[7] = "每次用量";
			top[8] = "用法标准";
			top[9] = "规格包装";
		}else{
			xlsName = "单贴价格与味数逐级下帖统计";
			titleName = "单贴价格与味数逐级下帖统计";
			top[0] = "姓名";
			top[1] = "医保号";
			top[2] = "就诊编号";
			top[3] = "就诊类型";
			top[4] = "诊断";
			top[5] = "就诊时间";
			top[6] = "医生姓名";
			top[7] = "医院代码";
			top[8] = "医院名称";
			top[9] = "单贴价格";
			top[10] = "单贴味数";
			top[11] = "外配处方医院";
			top[12] = "外配处方医生";
			top[13] = "总费用";
			if(condition.getDivId().equals("2")){
				xlsName = "单贴价格与味数逐级下帖统计（显示明细）";
				titleName = "单贴价格与味数显示明细统计（显示明细）";
				top[14] = "明细项目编码";
				top[15] = "明细项目名称";
				top[16] = "明细金额";
				top[17] = "自付比例";
				top[18] = "通用名";
				top[19] = "药品数量";
				top[20] = "贴数";
				top[21] = "每次用量";
				top[22] = "用法标准";
				top[23] = "规格包装";
			}
		}
		try {
			ExportExcelUtil.resultSetToExcel(rs, top,xlsName, titleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 就诊类型多选
	 * @return
	 */
	public String openCheckBox(){
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/zyyp/openCheckBox.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医疗机构及药店中药饮片分人员总量统计
	 * @return
	 */
	public String personTotalStatistics(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.personTotalStatistics(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/personTotalStatistics.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医疗机构及药店特定中药饮片统计数据查询
	 * @return
	 */
	public String totalStatisticsQuery(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.totalStatisticsQuery(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/totalStatisticsQuery.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医生特定中药饮片统计数据查询
	 * @return
	 */
	public String doctorTotalStatisticsQuery(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.doctorTotalStatisticsQuery(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/doctorTotalStatisticsQuery.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 单个医生人均总费用统计
	 * @return
	 */
	public String singleDoctorStatisticsQuery(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.singleDoctorStatisticsQuery(pageSet,condition);
		}else{
			condition = new QueryCondition();
			Calendar c = Calendar.getInstance();
			c.add(c.MONTH,-1);//得到上个月的月份  
			Date d = c.getTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			condition.setStartDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
			condition.setEndDate(sdf.format(d).substring(0, 4)+"年"+sdf.format(d).substring(4, 6)+"月");
		}
		select();
		this.page = this.ROOR_PATH + "/zyyp/singleDoctorStatisticsQuery.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 
	 */
	public void exportExcelSingleLine(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("1")){
				//医生特定中药饮片统计数据查询
				this.pageSet = zyypService.doctorTotalStatisticsQuery(pageSet,condition);
			}else if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("2")){
				//医疗机构及药店特定中药饮片统计数据查询
				this.pageSet = zyypService.totalStatisticsQuery(pageSet,condition);
			}else if(StringUtils.isNotEmpty(condition.getDivId())&&condition.getDivId().equals("3")){
				//单个医生人均总费用统计
				this.pageSet = zyypService.singleDoctorStatisticsQuery(pageSet,condition);
			}
		}
		//机构类别Map
		Map akb022Map = commonService.selectDicInfo("AKB022");
		List<Object[]> rs = new ArrayList<Object[]>();
		int i = 0;
		if(condition.getDivId().equals("1")){
			i = 8;
		}else if(condition.getDivId().equals("2")){
			i = 6;
		}else if(condition.getDivId().equals("3")){
			i = 7;
		}
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[i];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
			if(condition.getDivId().equals("1")){
				obj[0] = akb022Map.get(map.get("HOSP_TYPE"));
				obj[1] = map.get("HOSP_ID");
				obj[2] = map.get("NAME");
				obj[3] = map.get("XM");//
				obj[4] = map.get("ZGZS");
				obj[5] = map.get("TYMC");
				obj[6] = map.get("SL");
				obj[7] = map.get("FY");
			}else if(condition.getDivId().equals("2")){
				obj[0] = map.get("JSNY");//akb022Map.get(map.get("HOSP_TYPE"));
				obj[1] = map.get("HOSP_ID");
				obj[2] = map.get("NAME");
				obj[3] = map.get("TYMC");
				obj[4] = map.get("SL");
				obj[5] = map.get("FY");
			}else if(condition.getDivId().equals("3")){
				obj[0] = map.get("HOSP_ID");
				obj[1] = map.get("NAME");
				obj[2] = map.get("XM");
				obj[3] = map.get("ZGZS");
				obj[4] = map.get("RS");
				obj[5] = map.get("FY");
				obj[6] = df.format(Double.parseDouble(String.valueOf(map.get("RJFY"))));
			}
			rs.add(obj);
		}
		String[] top = new String[i];
		String xlsName = "";
		String titleName = "";
		if(condition.getDivId().equals("1")){
			xlsName = "医生特定中药饮片统计数据查询";
			titleName = "医生特定中药饮片统计数据查询";
			top[0] = "机构类别";
			top[1] = "机构代码";
			top[2] = "机构名称";
			top[3] = "医生姓名";
			top[4] = "医师资格证书编号";
			top[5] = "中药饮片通用名";
			top[6] = "中药饮片数量";
			top[7] = "中药饮片费用";
		}else if(condition.getDivId().equals("2")){
			xlsName = "医疗机构及药店特定中药饮片统计数据查询";
			titleName = "医疗机构及药店特定中药饮片统计数据查询";
			top[0] = "结算年月";
			top[1] = "机构代码";
			top[2] = "机构名称";
			top[3] = "中药饮片通用名";
			top[4] = "中药饮片数量";
			top[5] = "中药饮片费用";
		}else if(condition.getDivId().equals("3")){
			xlsName = "单个医生人均总费用统计";
			titleName = "单个医生人均总费用统计";
			top[0] = "机构代码";
			top[1] = "机构名称";
			top[2] = "医生姓名";
			top[3] = "医师资格证书编号";
			top[4] = "人数";
			top[5] = "费用";
			top[6] = "人均费用";
		}
		try {
			ExportExcelUtil.resultSetToExcel(rs, top,xlsName, titleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 医疗机构及药店中药饮片分人员总量统计导出EXCEL
	 */
	public void exportExcelPersonStatics(){
		this.pageSet = new PageSet(999999999, 0);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.personTotalStatistics(pageSet,condition);
		}
		//机构类别Map
		Map akb022Map = commonService.selectDicInfo("AKB022");
		//医院等级Map
		Map aka101Map = commonService.selectDicInfo("AKA101");
		List<Object[]> rs = new ArrayList<Object[]>();
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[25];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = map.get("JSNY");
			obj[1] = akb022Map.get(map.get("HOSP_TYPE"));
			obj[2] = map.get("DDJG_ID");
			obj[3] = map.get("NAME");
			obj[4] = aka101Map.get(map.get("HOSP_CLASS"));
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
			//特病非外配处方
			obj[5] = map.get("TBFWPFY");
			obj[6] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPZZL"))));
			obj[7] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPJTJG"))));
			obj[8] = df.format(Double.parseDouble(String.valueOf(map.get("TBFWPJTJGZZL"))));
			obj[9] = map.get("TBFWPJZRC");
			//特病外配处方
			obj[10] = map.get("TBWPFY");
			obj[11] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPZZL"))));
			obj[12] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPJTJG"))));
			obj[13] = df.format(Double.parseDouble(String.valueOf(map.get("TBWPJTJGZZL"))));
			obj[14] = map.get("TBWPJZRC");
			//非特病非外配处方
			obj[15] = map.get("FTBFWPFY");
			obj[16] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPZZL"))));
			obj[17] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPJTJG"))));
			obj[18] = df.format(Double.parseDouble(String.valueOf(map.get("FTBFWPJTJGZZL"))));
			obj[19] = map.get("FTBFWPJZRC");
			//非特病外配处方
			obj[20] = map.get("FTBWPFY");        
			obj[21] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPZZL"))));       
			obj[22] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPJTJG"))));      
			obj[23] = df.format(Double.parseDouble(String.valueOf(map.get("FTBWPJTJGZZL"))));   
			obj[24] = map.get("FTBWPJZRC");        
			rs.add(obj);
		}
		String[] top = {"结算年月","机构类别","机构代码","机构名称","医院等级","费用","增长率","贴均价格","贴均价格增长率","就诊人次","费用","增长率","贴均价格","贴均价格增长率","就诊人次","费用","增长率","贴均价格","贴均价格增长率","就诊人次","费用","增长率","贴均价格","贴均价格增长率","就诊人次"};
		try {
			zyypService.resultSetToExcelPersonStatics(rs, top,"医疗机构及药店中药饮片分人员总量统计", "医疗机构及药店中药饮片分人员总量统计");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单个医生人均总费用统计图形化展示
	 * @return
	 */
	public String showDemo(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String startDate = "";
		String endDate = "";
		try {
			if(StringUtils.isNotEmpty(condition.getStartDate())&&!condition.getStartDate().equals("null")){
				startDate = URLDecoder.decode(condition.getStartDate(), "utf-8");
			}
			if(StringUtils.isNotEmpty(condition.getEndDate())&&!condition.getEndDate().equals("null")){
				endDate = URLDecoder.decode(condition.getEndDate(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.singleDoctorStatisticsQuery(pageSet,condition);
		}
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数 
		ArrayList data1 = new ArrayList();
		ArrayList data2 = new ArrayList();
		ArrayList data3 = new ArrayList();
		ArrayList data4 = new ArrayList();
		if(pageSet.getCount()>0){
			for(int i = 0;i<pageSet.getData().size();i++){
				Map map=new HashMap<String,String>();
				map = (HashMap<String,String>)pageSet.getData().get(i);
				data1.add(i,"'"+map.get("XM")+"'");
				data2.add(i,map.get("RS"));
				data3.add(i,map.get("FY"));
				data4.add(i,df.format(Double.parseDouble(String.valueOf(map.get("RJFY")))));
			}
		}
		condition.setData1(data1);
		condition.setData2(data2);
		condition.setData3(data3);
		condition.setData4(data4);
		this.page = this.ROOR_PATH + "/zyyp/showDemo.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医疗机构及药店特定中药饮片统计数据查询图形化展示
	 * @return
	 */
	public String showHosYdDemo(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String startDate = "";
		String endDate = "";
		try {
			if(StringUtils.isNotEmpty(condition.getStartDate())&&!condition.getStartDate().equals("null")){
				startDate = URLDecoder.decode(condition.getStartDate(), "utf-8");
			}
			if(StringUtils.isNotEmpty(condition.getEndDate())&&!condition.getEndDate().equals("null")){
				endDate = URLDecoder.decode(condition.getEndDate(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.totalStatisticsQuery(pageSet,condition);
		}
		ArrayList data1 = new ArrayList();
		ArrayList data2 = new ArrayList();
		ArrayList data3 = new ArrayList();
		if(pageSet.getCount()>0){
			for(int i = 0;i<pageSet.getData().size();i++){
				Map map=new HashMap<String,String>();
				map = (HashMap<String,String>)pageSet.getData().get(i);
				data1.add(i,"'"+map.get("NAME")+"("+map.get("TYMC")+")"+"'");
				data2.add(i,map.get("SL"));
				data3.add(i,map.get("FY"));
			}
		}
		condition.setData1(data1);
		condition.setData2(data2);
		condition.setData3(data3);
		this.page = this.ROOR_PATH + "/zyyp/showHosYdDemo.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 医生特定中药饮片统计数据查询图形化展示
	 * @return
	 */
	public String showYsDemo(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String startDate = "";
		String endDate = "";
		try {
			if(StringUtils.isNotEmpty(condition.getStartDate())&&!condition.getStartDate().equals("null")){
				startDate = URLDecoder.decode(condition.getStartDate(), "utf-8");
			}
			if(StringUtils.isNotEmpty(condition.getEndDate())&&!condition.getEndDate().equals("null")){
				endDate = URLDecoder.decode(condition.getEndDate(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		// 查询数据库
		if(condition != null){
			this.pageSet = zyypService.doctorTotalStatisticsQuery(pageSet,condition);
		}
		ArrayList data1 = new ArrayList();
		ArrayList data2 = new ArrayList();
		ArrayList data3 = new ArrayList();
		if(pageSet.getCount()>0){
			for(int i = 0;i<pageSet.getData().size();i++){
				Map map=new HashMap<String,String>();
				map = (HashMap<String,String>)pageSet.getData().get(i);
				data1.add(i,"'"+map.get("XM")+"("+map.get("TYMC")+")"+"'");
				data2.add(i,map.get("SL"));
				data3.add(i,map.get("FY"));
			}
		}
		condition.setData1(data1);
		condition.setData2(data2);
		condition.setData3(data3);
		this.page = this.ROOR_PATH + "/zyyp/showYsDemo.jsp";
		return this.SUCCESS;
	}
}
