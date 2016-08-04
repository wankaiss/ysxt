package com.wondersgroup.qyws.tjfx.action.hztj;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wondersgroup.qyws.tjfx.common.BaseAction;
import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.service.CbrService;
import com.wondersgroup.qyws.tjfx.service.CommonService;
import com.wondersgroup.qyws.tjfx.util.ExportExcelUtil;
import com.wondersgroup.qyws.tjfx.util.GlobalConstants;
import com.wondersgroup.util.papper.PageSet;


@SuppressWarnings("serial")
@Controller()  
@Scope("prototype")
public class CbrAction extends BaseAction{
	@Autowired
	private CommonService commonService;
	@Autowired
	private CbrService cbrService;
	
	/**
	 * 平均就诊费用和就诊次数查询
	 * @return
	 */
	public String averageCostQuery(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		if(condition != null){
			condition.setYearMonth(condition.getStartDate()+"~"+condition.getEndDate());
			this.pageSet = cbrService.averageCostQuery(pageSet,condition);
		}
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//人员类别Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("akc021Map", akc021Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/cbr/averageCostQuery.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 异常就医人员筛选
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String chooseExceptionPerson() throws Exception{
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String yearMonth = "";
		try {
			if(StringUtils.isNotEmpty(condition.getYearMonth())){
				yearMonth = URLDecoder.decode(condition.getYearMonth(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//根据区县查询日常参数
		List list = cbrService.chooseExceptionPerson(condition);
		if(list.size()>0){
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)list.get(0);
			condition.setRc_pjfy(String.valueOf(map.get("QZM")));
			condition.setRc_pjcs(String.valueOf(map.get("QZDM")));
			condition.setRc_fyze(String.valueOf(map.get("SJQZID")));
			condition.setRc_cszs(String.valueOf(map.get("QZZ")));
			condition.setId(String.valueOf(map.get("ID")));
		}
		condition.setYearMonth(yearMonth);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//人员类别Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("akc021Map", akc021Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/cbr/chooseExceptionPerson.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 异常就医人员筛选查询
	 * @return
	 */
	public String queryChooseExceptionPerson(){
		this.pageSet = cbrService.queryChooseExceptionPerson(pageSet,condition);
		//统筹区Map
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		//人员类别Map
		Map akc021Map = commonService.selectDicInfo("AKC021");
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aaa027Map", aaa027Map);
		this.setAttribute("akc021Map", akc021Map);
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/cbr/chooseExceptionPerson.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 参保人日常查询条件保存
	 */
	public void saveRcQueryCondition(){
		String rc_pjfy = this.getParameter("rc_pjfy");
		String rc_pjcs = this.getParameter("rc_pjcs");
		String rc_fyze = this.getParameter("rc_fyze");
		String rc_cszs = this.getParameter("rc_cszs");
		String aaa027 = this.getParameter("aaa027");
		condition = new QueryCondition();
		condition.setRc_pjfy(rc_pjfy);
		condition.setRc_pjcs(rc_pjcs);
		condition.setRc_fyze(rc_fyze);
		condition.setRc_cszs(rc_cszs);
		condition.setAaa027(aaa027);
		cbrService.saveRcQueryCondition(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 日常参数查询
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void queryRc() throws Exception{
		QueryCondition con = new QueryCondition();
		String aaa027 = this.getParameter("aaa027");
		con.setAaa027(aaa027);
		//根据区县查询日常参数
		List list = cbrService.chooseExceptionPerson(con);
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/json");  
	    response.setCharacterEncoding("UTF-8");  
	    PrintWriter writer = response.getWriter();
		JSONArray array = JSONArray.fromObject(list);
		writer.print(array.toString());
	}
	
	/**
	 * 导出Excel
	 */
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
			i = 4;
			this.pageSet = cbrService.averageCostQuery(pageSet,condition);
		}else if(condition.getDivId().equals("2")){
			i = 9;
			this.pageSet = cbrService.queryChooseExceptionPerson(pageSet,condition);
		}
		Map akc021Map = commonService.selectDicInfo("AKC021");
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		Map aka130Map = commonService.selectDicInfo("AKA130");
		List<Object[]> rs = new ArrayList<Object[]>();
		//格式化金钱 保留两位小数
		DecimalFormat df = new DecimalFormat("#.00"); 
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[i];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			if(condition.getDivId().equals("1")){
				obj[0] = condition.getStartDate()+"~"+condition.getEndDate();
				obj[1] = aka130Map.get(map.get("JZLX"));
				obj[2] = df.format(map.get("PJFY"));
				obj[3] = df.format(map.get("PJCS"));
			}else if(condition.getDivId().equals("2")){
				obj[0] = map.get("YBH");
				obj[1] = map.get("XM");
				obj[2] = map.get("NL");
				obj[3] = aaa027Map.get(map.get("TCDM"));
				obj[4] = map.get("SFZHM");
				obj[5] = map.get("FYZE");
				obj[6] = df.format(map.get("JZCS"));
				obj[7] = map.get("ZFFY");
				obj[8] = df.format(map.get("PJFY"));
			}
			rs.add(obj);
		}
		String[] top = new String[i];
		String xlsName = "";
		String sheetName = "";
		if(condition.getDivId().equals("1")){
			xlsName = "平均就诊费用和就诊次数查询";
			sheetName = "平均就诊费用和就诊次数查询";
			top[0] = "时间";
			top[1] = "就诊类型";
			top[2] = "平均就诊费用";
			top[3] = "平均就诊次数";
		}else if(condition.getDivId().equals("2")){
			xlsName = "异常就医人员";
			sheetName = "异常就医人员";
			top[0] = "医保号";
			top[1] = "姓名";
			top[2] = "年龄";
			top[3] = "区县";
			top[4] = "身份证号";
			top[5] = "费用总额";
			top[6] = "就诊次数";
			top[7] = "历年账户支付费用";
			top[8] = "平均就诊费用";
		}
		try {
			ExportExcelUtil.resultSetToExcel(rs, top,stime + xlsName,sheetName );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 待查人员查询
	 * @return
	 */
	public String queryPerson(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String yearMonth = "";
		try {
			if(StringUtils.isNotEmpty(condition.getYearMonth())){
				yearMonth = URLDecoder.decode(condition.getYearMonth(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setYearMonth(yearMonth);
		this.pageSet = cbrService.queryPerson(pageSet,condition);
		this.page = this.ROOR_PATH + "/cbr/queryPerson.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 批量加入红黑名单
	 * @return
	 */
	public String beathAddList(){
		String yearMonth = "";
		try {
			if(StringUtils.isNotEmpty(condition.getYearMonth())){
				yearMonth = URLDecoder.decode(condition.getYearMonth(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setYearMonth(yearMonth);
		//红名单
		if(condition.getColorList().equals("red")){
			condition.setHhmdbz(GlobalConstants.DIC_HHMDBZ_1);
		}else{
			condition.setHhmdbz(GlobalConstants.DIC_HHMDBZ_2);
		}
		this.page = this.ROOR_PATH + "/cbr/beathAddList.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 保存红黑名单
	 */
	public void saveBeathAddList(){
		if(condition == null){
			condition = new QueryCondition();
		}
		String aaa027 = this.getParameter("aaa027");
		String sfzhm = this.getParameter("sfzhm");
		String aka130 = this.getParameter("aka130");
		String sign = this.getParameter("sign");
		String hhmdbz = this.getParameter("hhmdbz");
		String rc_pjfy = this.getParameter("rc_pjfy");
		String rc_pjcs = this.getParameter("rc_pjcs");
		String rc_fyze = this.getParameter("rc_fyze");
		String rc_cszs = this.getParameter("rc_cszs");
		String yearMonth = this.getParameter("yearMonth");
		String pjfy = this.getParameter("pjfy");
		String pjcs = this.getParameter("pjcs");
		String bz = this.getParameter("bz");
		condition.setAaa027(aaa027);
		condition.setAac002(sfzhm);
		condition.setAka130(aka130);
		condition.setSign(sign);
		condition.setRc_pjfy(rc_pjfy);
		condition.setRc_pjcs(rc_pjcs);
		condition.setRc_fyze(rc_fyze);
		condition.setRc_cszs(rc_cszs);
		condition.setYearMonth(yearMonth);
		condition.setPjfy(pjfy);
		condition.setPjcs(pjcs);
		condition.setHhmdbz(hhmdbz);
		condition.setBz(bz);
		//人员全选
		if(condition.getSign().equals("all")){
			if (null == this.pageSet) {
				this.pageSet = new PageSet(99999999, 0);
			}
			this.pageSet = cbrService.queryPerson(pageSet,condition);
			String sfzhms = "";
			if(pageSet.getCount()>0){
				for(int i=0;i<pageSet.getCount();i++){
					Map map = new HashMap<String,String>();
					map = (HashMap<String,String>)pageSet.getData().get(i);
					sfzhms += map.get("SFZHM") + ",";
				}
			}
			if(StringUtils.isNotEmpty(sfzhms)){
				sfzhms = sfzhms.substring(0, sfzhms.length()-1);
			}
			condition.setAac002(sfzhms);
		}
		cbrService.beathAddList(condition);
		this.ajax("{\"success\":true,\"result\":[]}", "text/json");
	}
	
	/**
	 * 个人报告
	 * @return
	 */
	public String queryPersonReport(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String yearMonth = "";
		try {
			if(StringUtils.isNotEmpty(condition.getYearMonth())){
				yearMonth = URLDecoder.decode(condition.getYearMonth(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setYearMonth(yearMonth);
		this.pageSet = cbrService.queryPersonReport(pageSet,condition);
		this.page = this.ROOR_PATH + "/cbr/queryPersonReport.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 就诊信息查询
	 * @return
	 */
	public String queryJzxx(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String aac003 = "";
		try {
			if(StringUtils.isNotEmpty(condition.getAac003())){
				aac003 = URLDecoder.decode(condition.getAac003(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setAac003(aac003);
		this.pageSet = cbrService.queryJzxx(pageSet,condition);
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/cbr/queryJzxx.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 费用明细查询
	 * @return
	 */
	public String queryFymx(){
		if (null == this.pageSet) {
			this.pageSet = new PageSet(PublicStatic.DATA.Limit, 0);
		}
		String aac003 = "";
		try {
			if(StringUtils.isNotEmpty(condition.getAac003())){
				aac003 = URLDecoder.decode(condition.getAac003(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		condition.setAac003(aac003);
		this.pageSet = cbrService.queryFymx(pageSet,condition);
		//就诊类型Map
		Map aka130Map = commonService.selectDicInfo("AKA130");
		this.setAttribute("aka130Map", aka130Map);
		this.page = this.ROOR_PATH + "/cbr/queryFymx.jsp";
		return this.SUCCESS;
	}
	
	/**
	 * 待查人员导出
	 */
	public void exportExcelPerson(){
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
		this.pageSet = cbrService.queryPerson(pageSet,condition);
		
		Map akc021Map = commonService.selectDicInfo("AKC021");
		Map aaa027Map = commonService.selectDicInfo("AAA027");
		Map aka130Map = commonService.selectDicInfo("AKA130");
		List<Object[]> rs = new ArrayList<Object[]>();
		//格式化金钱 保留两位小数
		DecimalFormat df = new DecimalFormat("#.00"); 
		for(int j=0;j<pageSet.getCount();j++){
			Object[] obj = new Object[12];
			Map map=new HashMap<String,String>();
			map = (HashMap<String,String>)pageSet.getData().get(j);
			obj[0] = map.get("YBH");
			obj[1] = map.get("XM");
			obj[2] = map.get("NL");
			obj[3] = map.get("SFZHM");
			obj[4] = map.get("XGSJ");
			obj[5] = map.get("ZFY");
			obj[6] = df.format(map.get("JZCS"));
			obj[7] = df.format(map.get("LNZHZFFY"));
			obj[8] = df.format(map.get("PJJZFY"));
			if(map.get("HHMDBZ")!=null&&map.get("HHMDBZ").equals("1")){
				obj[9] = "红名单";
			}else if(map.get("HHMDBZ")!=null&&map.get("HHMDBZ").equals("2")){
				obj[9] = "黑名单";
			}else{
				obj[9] = "未加入红/黑名单";
			}
			if(map.get("BZ")!=null){
				obj[10] = map.get("BZ");
			}else{
				obj[10] = "";
			}
			if(map.get("DYQDDYFY")!=null){
				obj[11] = map.get("DYQDDYFY");
			}else{
				obj[11] = "";
			}
			rs.add(obj);
		}
		String[] top = new String[12];
		String xlsName = "待查人员信息";
		String sheetName = "待查人员信息";
		top[0] = "医保号";
		top[1] = "姓名";
		top[2] = "年龄";
		top[3] = "身份证号";
		top[4] = "加入待查日期";
		top[5] = "费用总额";
		top[6] = "就诊次数";
		top[7] = "历年账户支付费用";
		top[8] = "平均就诊费用";
		top[9] = "是否红黑名单成员";
		top[10] = "红/黑名单备注";
		top[11] = "待遇启动当月发生费用";
		try {
			ExportExcelUtil.resultSetToExcel(rs, top,stime + xlsName,sheetName );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
