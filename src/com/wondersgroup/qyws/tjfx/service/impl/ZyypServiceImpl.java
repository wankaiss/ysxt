package com.wondersgroup.qyws.tjfx.service.impl;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.wondersgroup.qyws.tjfx.module.QueryCondition;
import com.wondersgroup.qyws.tjfx.service.ZyypService;
import com.wondersgroup.util.papper.PageSet;

@Service("ZyypServiceImplService")
@Transactional
public class ZyypServiceImpl extends ServiceImpl implements ZyypService {

	/**
	 * 查询医疗机构信息
	 */
	public PageSet queryHosInfo(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select ddjg_id as akb020,name as akb021,hosp_type as akb022,hosp_class as aka101,addr ");
		exeSQL.append("from d_ddjg where 1=1 ");
		if(StringUtils.isNotEmpty(condition.getAkb020())){
			exeSQL.append(" and trim(ddjg_id) = trim('"+condition.getAkb020()+"') ");
		}
		if(StringUtils.isNotEmpty(condition.getAkb021())){
			exeSQL.append(" and name like '%"+condition.getAkb021()+"%' ");
		}
		/*if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and hosp_type = '"+condition.getAkb022()+"'");
		}*/
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		exeSQL.append(" order by ddjg_id asc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
	
	/**
	 * 中药饮片总量统计查询条件
	 * @param exeSQL
	 * @param condition
	 */
	public void queryForCondition(StringBuffer exeSQL,QueryCondition condition){
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
	}
	
	public void queryForCondition2(StringBuffer exeSQL,QueryCondition condition){
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and xzlb in ('01','02','03','04') ");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and yd_id in ("+strid+") ");
		}
	}

	/**
	 * 中药饮片总量统计
	 */
	public PageSet totalStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		exeSQL.append("select * from( ");
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		exeSQL.append("select '"+jsny+"' as jsny,m.hosp_type,a.hosp_id,m.name,m.hosp_class, ");
		//外配+非外配
		exeSQL.append("(nvl(a.fy,0)+nvl(b.fy,0)) zfy,(nvl(a.ts,0)+nvl(b.ts,0)) zts,");//外配+非外配费用、贴数
		exeSQL.append("(case when (nvl(a.fy_p,0)+nvl(b.fy_p,0))=0 then 0 else ((nvl(a.fy,0)+nvl(b.fy,0))-(nvl(a.fy_p,0)+nvl(b.fy,0)))/(nvl(a.fy_p,0)+nvl(b.fy_p,0)) end)*100 zzzl,");//费用增长率
		exeSQL.append("(case when (nvl(a.ts,0)+nvl(b.ts,0))=0 then 0 else (nvl(a.fy,0)+nvl(b.fy,0))/(nvl(a.ts,0)+nvl(b.ts,0)) end) zjtjg,");//贴均价格
		exeSQL.append("(case when (nvl(a.ts_p,0)+nvl(b.ts_p,0))=0 then 0 else (decode((nvl(a.ts,0)+nvl(b.ts,0)),0,0,(nvl(a.fy,0)+nvl(b.fy,0))/(nvl(a.ts,0)+nvl(b.ts,0)))-(nvl(a.fy_p,0)+nvl(b.fy,0))/(nvl(a.ts_p,0)+nvl(b.ts_p,0)))/(nvl(a.fy_p,0)+nvl(b.fy,0))/(nvl(a.ts_p,0)+nvl(b.ts_p,0)) end) ZJTJGZZL,");//贴均价格增长率
		//非外配处方
		exeSQL.append("nvl(a.fy,0) as FWPFY,nvl(a.ts,0) as FWPTS,");//费用、贴数
		exeSQL.append("(case when nvl(a.fy_p,0)=0 then 0 else (a.fy-a.fy_p)/a.fy_p end)*100 as FWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(a.ts,0)=0 then 0 else a.fy/a.ts end) as FWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(a.ts_p,0)=0 then 0 else (decode(a.ts,0,0,a.fy/a.ts)-a.fy_p/a.ts_p)/a.fy_p/a.ts_p end) as FWPJTJGZZL,");//贴均价格增长率
		//外配处方
		exeSQL.append("nvl(b.fy,0) as WPFY,nvl(b.ts,0) as WPTS,");//费用、贴数
		exeSQL.append("(case when nvl(b.fy_p,0)=0 then 0 else (b.fy-b.fy_p)/b.fy_p end)*100 as WPZZL,");//费用增长率
		exeSQL.append("(case when nvl(b.ts,0)=0 then 0 else b.fy/b.ts end) as WPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(b.ts_p,0)=0 then 0 else (decode(b.ts,0,0,b.fy/b.ts)-b.fy_p/b.ts_p)/b.fy_p/b.ts_p end) as WPJTJGZZL ");//贴均价格增长率
		exeSQL.append("from ");
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())&&condition.getAkb022().equals("1")){
			exeSQL.append("(select f1.hosp_id,sum(f1.fy) fy,sum(f1.fy_p) fy_p,sum(f1.ts) ts,sum(f1.ts_p) ts_p ");
			exeSQL.append("from F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is null and d.hosp_type='1' ");//非外配处方
			queryForCondition(exeSQL,condition);
		}else if(StringUtils.isNotEmpty(condition.getAkb022())&&condition.getAkb022().equals("2")){
			exeSQL.append("(select f1.hosp_id,(sum(f1.fy)+(select sum(fy) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) fy,");
			exeSQL.append("(sum(f1.fy_p)+(select sum(fy_p) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) fy_p,");
			exeSQL.append("(sum(f1.ts)+(select sum(ts) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) ts,");
			exeSQL.append("(sum(f1.ts_p)+(select sum(ts_p) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) ts_p");
			exeSQL.append(" from F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is null and d.hosp_type='2' ");//非外配处方
			queryForCondition(exeSQL,condition);
		}else{
			exeSQL.append("(select f1.hosp_id,sum(f1.fy) fy,sum(f1.fy_p) fy_p,sum(f1.ts) ts,sum(f1.ts_p) ts_p ");
			exeSQL.append(" from F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is null and d.hosp_type='1' ");//非外配处方
			queryForCondition(exeSQL,condition);
			exeSQL.append(" group by f1.hosp_id ");
			exeSQL.append(" union ");
			exeSQL.append(" select f1.hosp_id,(sum(f1.fy)+(select sum(fy) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) fy,");
			exeSQL.append("(sum(f1.fy_p)+(select sum(fy_p) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) fy_p,");
			exeSQL.append("(sum(f1.ts)+(select sum(ts) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) ts,");
			exeSQL.append("(sum(f1.ts_p)+(select sum(ts_p) from F_ZYYP_TRADE_ORG_SUM_3 where f1.hosp_id = yd_id ");
			queryForCondition2(exeSQL,condition);
			exeSQL.append(")) ts_p ");
			exeSQL.append("from F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is null and d.hosp_type='2' ");//非外配处方
			queryForCondition(exeSQL,condition);
		}
		exeSQL.append(" group by f1.hosp_id) a left join");
		exeSQL.append("(select f1.hosp_id,sum(f1.fy) fy,sum(f1.fy_p) fy_p,sum(f1.ts) ts,sum(f1.ts_p) ts_p ");
		exeSQL.append("from F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is not null ");//外配处方
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
		exeSQL.append(" group by f1.hosp_id) b on a.hosp_id=b.hosp_id,d_ddjg m where a.hosp_id=m.ddjg_id");
		exeSQL.append(") order by zzzl desc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
	
	/**
	 * @param rs
	 *            数据List<Object[]>
	 * @param top
	 *            表头数组
	 * @param xlsName
	 *            文件名
	 * @param sheetName
	 *            sheet名
	 */
	public void resultSetToExcel(List<Object[]> rs, String[] top,String xlsName, String sheetName) {
		System.out.println("----resultSetToExcel-----------------");
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("医疗机构及药店中药饮片分析", "utf-8")+".xls");
			HSSFSheet sheet = workbook.createSheet();
			int iRow = 0;// 行下标
			workbook.setSheetName(0, sheetName);
			HSSFRow row = sheet.createRow(iRow);
			HSSFCell cell = null;
			cell = row.createCell(0);
			// 设置字体样式
			HSSFFont font = workbook.createFont();
			font.setFontName(HSSFFont.FONT_ARIAL);// 字体
			font.setFontHeightInPoints((short) 15);// 字号
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			// font.setColor(HSSFColor.BLUE.index);//颜色
			HSSFCellStyle cellStyle_1 = workbook.createCellStyle(); // 设置单元格样式
			cellStyle_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle_1.setFont(font);
			cell.setCellStyle(cellStyle_1);
			cell.setCellValue(new HSSFRichTextString(xlsName));// 设置文件名第一行展示
//			cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,top.length - 1));
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			//行合并
			sheet.addMergedRegion(new Region(1, (short) (0), 2,(short) (0)));
			sheet.addMergedRegion(new Region(1, (short) (1), 2,(short) (1)));
			sheet.addMergedRegion(new Region(1, (short) (2), 2,(short) (2)));
			sheet.addMergedRegion(new Region(1, (short) (3), 2,(short) (3)));
			sheet.addMergedRegion(new Region(1, (short) (4), 2,(short) (4)));
			//列合并
			sheet.addMergedRegion(new Region(1, (short) (5), 1,(short) (9)));
			sheet.addMergedRegion(new Region(1, (short) (10), 1,(short) (14)));
			sheet.addMergedRegion(new Region(1, (short) (15), 1,(short) (19)));
			for(int i=1;i<=20;i++){
				sheet.setColumnWidth(i-1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell(i-1);
				HSSFCellStyle cellStyle1 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setWrapText(true);
				cell.setCellStyle(cellStyle1);
				if(i==1){
					cell.setCellValue(new HSSFRichTextString("结算年月"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("机构类别"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("机构代码"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("机构名称"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("医院等级"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("外配处方+非外配处方 "));// 给单元格赋值
				}else if(i==11){
					cell.setCellValue(new HSSFRichTextString("非外配处方 "));// 给单元格赋值
				}else if(i==16){
					cell.setCellValue(new HSSFRichTextString("外配处方 "));// 给单元格赋值
				}else{
					cell.setCellValue(new HSSFRichTextString(""));
				}
			}
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			int nColumn = top.length;
			for (int i = 1; i <= nColumn; i++) {
				sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell((i - 1));
				HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
				cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle.setWrapText(true);
				cell.setCellStyle(cellStyle);
				if(i>5){
					cell.setCellValue(new HSSFRichTextString(top[i - 1].toString()));// 给单元格赋值
				}
			}
			
			iRow = iRow + 1;
			// 写入各条记录
			int rColumn = rs.toArray().length;
			for (int j = 0; j < rColumn; j++) {
				row = sheet.createRow(iRow);
				Object obj[] = new Object[] {};
				obj = (Object[]) rs.get(j);
				for (int k = 0; k < nColumn; k++) {
					cell = row.createCell(k);
					if (obj[k] != null) {
						cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(" "));
					}
					HSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
				}
				iRow = iRow + 1;
			}
			ServletOutputStream fOut = response.getOutputStream();
			
			System.out.println("--------------endend-------------");
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 医师选择
	 */
	public PageSet queryDoctorInfo(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select a.ysdm,a.xm,a.zyzs,a.zgzs,a.hosp_id,b.name from d_doctor a,d_ddjg b where a.hosp_id=b.ddjg_id ");
		if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and a.hosp_id in ("+strid+") ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and b.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and b.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		if(StringUtils.isNotEmpty(condition.getAaz263())){
			exeSQL.append(" and a.ysdm = '"+condition.getAaz263()+"' ");
		}
		if(StringUtils.isNotEmpty(condition.getAac003())){
			exeSQL.append(" and a.xm like '%"+condition.getAac003()+"%'");
		}
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	
	/**
	 * 单个医生的中药饮片费用及贴均价格查询
	 */
	public PageSet querySingleDoctor(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		exeSQL.append("select * from( ");
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		exeSQL.append("select '"+jsny+"' as jsny,hosp_type,ddjg_id,name,hosp_class,xm,zgzs,xzlb_1,xzlb_2,(case when (nvl(fwp_fy_p, 0)+nvl(fwp_ftb_fy_p,0)+nvl(wp_fy_p,0)+nvl(wp_ftb_fy_p,0))=0 then 0 ");
		exeSQL.append("else ((nvl(fwpfy,0)+nvl(fwp_ftb_fy,0)+nvl(wpfy,0)+nvl(wp_ftb_fy,0))-(nvl(fwp_fy_p,0)+nvl(fwp_ftb_fy_p,0)+nvl(wp_fy_p,0)+nvl(wp_ftb_fy_p,0)))/");
		exeSQL.append("(nvl(fwp_fy_p, 0) + nvl(fwp_ftb_fy_p, 0) + nvl(wp_fy_p, 0) + nvl(wp_ftb_fy_p, 0)) end) * 100 zzzl,");
		//特病非外配处方
		exeSQL.append("nvl(fwpfy, 0) as TBFWPFY,nvl(fwpts, 0) as TBFWPTS,");//费用、贴数
		exeSQL.append("(case when nvl(fwp_fy_p,0)=0 then 0 else (nvl(fwpfy,0)-fwp_fy_p)/fwp_fy_p end) * 100 as TBFWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(fwpts,0)=0 then 0 else nvl(fwpfy, 0) / fwpts end) as TBFWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(fwp_ts_p,0)=0 then 0 else (decode(fwpts,0,0,nvl(fwpfy,0)/fwpts)-nvl(fwp_fy_p,0)/fwp_ts_p)/fwp_fy_p/fwp_ts_p end) as TBFWPJTJGZZL,");//贴均价格增长率
		//特病外配处方
		exeSQL.append("nvl(wpfy, 0) as TBWPFY,nvl(wpts, 0) as TBWPTS,");//费用、贴数
		exeSQL.append("(case when nvl(wp_fy_p,0) = 0 then 0 else (wpfy-wp_fy_p)/wp_fy_p end) * 100 as TBWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(wpts,0) = 0 then 0 else  wpfy/wpts end) as TBWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(wp_ts_p,0) = 0 then 0 else (decode(wpts,0,0,wpfy/wpts)-wp_fy_p/wp_ts_p)/wp_fy_p/wp_ts_p end) as TBWPJTJGZZL,");//贴均价格增长率
		//非特病非外配处方
		exeSQL.append("nvl(fwp_ftb_fy, 0) as FTBFWPFY,nvl(fwp_ftb_s, 0) as FTBFWPTS,");//费用、贴数
		exeSQL.append("(case when nvl(fwp_ftb_fy_p,0) = 0 then 0 else (fwp_ftb_fy-fwp_ftb_fy_p)/fwp_ftb_fy_p end) * 100 as FTBFWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(fwp_ftb_s,0) = 0 then 0 else fwp_ftb_fy/fwp_ftb_s end) as FTBFWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(fwp_ftb_ts_p,0) = 0 then 0 else (decode(fwp_ftb_s,0,0,fwp_ftb_fy/fwp_ftb_s)-fwp_ftb_fy_p/fwp_ftb_ts_p)/fwp_ftb_fy_p/fwp_ftb_ts_p end) as FTBFWPJTJGZZL,");//贴均价格增长率
		//非特病外配处方
		exeSQL.append("nvl(wp_ftb_fy, 0) as FTBWPFY,nvl(wp_ftb_s, 0) as FTBWPTS,");//费用、贴数
		exeSQL.append("(case when nvl(wp_ftb_fy_p,0) = 0 then 0 else (wp_ftb_fy-wp_ftb_fy_p)/wp_ftb_fy_p end) * 100 as FTBWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(wp_ftb_s,0) = 0 then 0 else wp_ftb_fy/wp_ftb_s end) as FTBWPJTJG,");//贴均价格
		exeSQL.append("(case  when nvl(wp_ftb_ts_p,0) = 0 then 0 else (decode(wp_ftb_s,0,0,wp_ftb_fy/wp_ftb_s)-wp_ftb_fy_p/wp_ftb_ts_p)/wp_ftb_fy_p/wp_ftb_ts_p end) as FTBWPJTJGZZL ");//贴均价格增长率
		exeSQL.append("from(select * from(select * from ");
		exeSQL.append(" (select f1.hosp_id hosp_id_1,f1.dr_id dr_id_1,f1.xzlb xzlb_1, f1.yd_id yd_id_1,sum(f1.tb_fy) fwpfy,sum(f1.tb_ts) fwpts,sum(f1.tb_fy_p) fwp_fy_p,sum(f1.tb_ts_p) fwp_ts_p,");
		exeSQL.append("sum(f1.ftb_fy) fwp_ftb_fy,sum(f1.ftb_ts) fwp_ftb_s,sum(f1.ftb_fy_p) fwp_ftb_fy_p,sum(f1.ftb_ts_p) fwp_ftb_ts_p from ");
		exeSQL.append("f_zyyp_trade_dr_sum_2 f1,d_doctor c,d_ddjg d where f1.dr_id=c.dr_id and f1.hosp_id=d.ddjg_id and trim(yd_id) is null ");//特病（非特病）非外配处方
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医师编号（医师编号存在重复所以要医院编码和医师编号联合判断来确认）
		if(StringUtils.isNotEmpty(condition.getDocId())){
			String[] docids = condition.getDocId().split(",");
			exeSQL.append(" and (");
			for (int i = 0; i < docids.length; i++) {
				String[] code = docids[i].split(":"); 
				if (i == 0) {
					exeSQL.append("  (c.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and c.ysdm = '"+code[0]+"') ");
				}else{
					exeSQL.append(" or (c.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and c.ysdm = '"+code[0]+"') ");
				}
			}
			exeSQL.append(" ) ");
		}else{
			//医院编码
			if(StringUtils.isNotEmpty(condition.getHosIds())){
				String strid = "";
				String[] ids = condition.getHosIds().split(",");
				for (int i = 0; i < ids.length; i++) {
					if ((i + 1) == ids.length) {
						strid += "'" + ids[i] + "'";
					} else {
						strid += "'" + ids[i] + "',";
					}
				}
				exeSQL.append(" and d.ddjg_id in ("+strid+") ");
			}
		}
		exeSQL.append(" group by f1.hosp_id,f1.dr_id, f1.xzlb, f1.yd_id) a full join ");
		exeSQL.append("(select f1.hosp_id hosp_id_2,f1.dr_id dr_id_2,f1.xzlb xzlb_2,f1.yd_id yd_id_2,sum(f1.tb_fy) wpfy,sum(f1.tb_ts) wpts,sum(f1.tb_fy_p) wp_fy_p,sum(f1.tb_ts_p) wp_ts_p,");
		exeSQL.append("sum(f1.ftb_fy) wp_ftb_fy,sum(f1.ftb_ts) wp_ftb_s,sum(f1.ftb_fy_p) wp_ftb_fy_p,sum(f1.ftb_ts_p) wp_ftb_ts_p from ");
		exeSQL.append("f_zyyp_trade_dr_sum_2 f1,d_doctor c,d_ddjg d where f1.dr_id=c.dr_id and f1.hosp_id=d.ddjg_id and trim(yd_id) is not null ");//特病（非特病）外配处方
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//医师编号（医师编号存在重复所以要医院编码和医师编号联合判断来确认）
		if(StringUtils.isNotEmpty(condition.getDocId())){
			String[] docids = condition.getDocId().split(",");
			exeSQL.append(" and (");
			for (int i = 0; i < docids.length; i++) {
				String[] code = docids[i].split(":"); 
				if (i == 0) {
					exeSQL.append("  (c.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and c.ysdm = '"+code[0]+"') ");
				}else{
					exeSQL.append(" or (c.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and c.ysdm = '"+code[0]+"') ");
				}
			}
			exeSQL.append(" ) ");
		}else{
			//医院编码
			if(StringUtils.isNotEmpty(condition.getHosIds())){
				String strid = "";
				String[] ids = condition.getHosIds().split(",");
				for (int i = 0; i < ids.length; i++) {
					if ((i + 1) == ids.length) {
						strid += "'" + ids[i] + "'";
					} else {
						strid += "'" + ids[i] + "',";
					}
				}
				exeSQL.append(" and d.ddjg_id in ("+strid+") ");
			}
		}
		exeSQL.append(" group by f1.hosp_id,f1.dr_id,f1.xzlb,f1.yd_id) b on a.hosp_id_1=b.hosp_id_2 and a.dr_id_1 = b.dr_id_2 and a.xzlb_1 = b.xzlb_2 and a.yd_id_1 = b.yd_id_2) e, " +
				"d_ddjg m,d_doctor n where (e.hosp_id_1 = m.ddjg_id or e.hosp_id_2 = m.ddjg_id) ");
		exeSQL.append(" and (e.hosp_id_1 = n.hosp_id or e.hosp_id_2 = n.hosp_id) and (n.dr_id = e.dr_id_1 or n.dr_id = e.dr_id_2))");
		exeSQL.append(") order by zzzl desc ");
		//exeSQL.append(" ) group by xm, name ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
	
	/**
	 * @param rs
	 *            数据List<Object[]>
	 * @param top
	 *            表头数组
	 * @param xlsName
	 *            文件名
	 * @param sheetName
	 *            sheet名
	 */
	public void resultSetToExcelDoctor(List<Object[]> rs, String[] top,String xlsName, String sheetName) {
		System.out.println("----resultSetToExcel-----------------");
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("单个医生的中药饮片费用及贴均价格", "utf-8")+".xls");
			HSSFSheet sheet = workbook.createSheet();
			int iRow = 0;// 行下标
			workbook.setSheetName(0, sheetName);
			HSSFRow row = sheet.createRow(iRow);
			HSSFCell cell = null;
			cell = row.createCell(0);
			// 设置字体样式
			HSSFFont font = workbook.createFont();
			font.setFontName(HSSFFont.FONT_ARIAL);// 字体
			font.setFontHeightInPoints((short) 15);// 字号
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			// font.setColor(HSSFColor.BLUE.index);//颜色
			HSSFCellStyle cellStyle_1 = workbook.createCellStyle(); // 设置单元格样式
			cellStyle_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle_1.setFont(font);
			cell.setCellStyle(cellStyle_1);
			cell.setCellValue(new HSSFRichTextString(xlsName));// 设置文件名第一行展示
//			cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,top.length - 1));
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			//行合并
			sheet.addMergedRegion(new Region(1, (short) (0), 2,(short) (0)));
			sheet.addMergedRegion(new Region(1, (short) (1), 2,(short) (1)));
			sheet.addMergedRegion(new Region(1, (short) (2), 2,(short) (2)));
			sheet.addMergedRegion(new Region(1, (short) (3), 2,(short) (3)));
			sheet.addMergedRegion(new Region(1, (short) (4), 2,(short) (4)));
			sheet.addMergedRegion(new Region(1, (short) (5), 2,(short) (5)));
			sheet.addMergedRegion(new Region(1, (short) (6), 2,(short) (6)));
			//列合并
			sheet.addMergedRegion(new Region(1, (short) (7), 1,(short) (11)));
			sheet.addMergedRegion(new Region(1, (short) (12), 1,(short) (16)));
			sheet.addMergedRegion(new Region(1, (short) (17), 1,(short) (21)));
			sheet.addMergedRegion(new Region(1, (short) (22), 1,(short) (26)));
			for(int i=1;i<=27;i++){
				sheet.setColumnWidth(i-1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell(i-1);
				HSSFCellStyle cellStyle1 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setWrapText(true);
				cell.setCellStyle(cellStyle1);
				if(i==1){
					cell.setCellValue(new HSSFRichTextString("结算年月"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("机构类别"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("机构代码"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("机构名称"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("医院等级"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("医师姓名"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("医师资格证书编号 "));// 给单元格赋值
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("特病非外配处方 "));// 给单元格赋值
				}else if(i==13){
					cell.setCellValue(new HSSFRichTextString("特病外配处方 "));// 给单元格赋值
				}else if(i==18){
					cell.setCellValue(new HSSFRichTextString("非特病非外配处方 "));// 给单元格赋值
				}else if(i==23){
					cell.setCellValue(new HSSFRichTextString("非特病外配处方 "));// 给单元格赋值
				}else{
					cell.setCellValue(new HSSFRichTextString(""));
				}
			}
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			int nColumn = top.length;
			for (int i = 1; i <= nColumn; i++) {
				sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell((i - 1));
				HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
				cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle.setWrapText(true);
				cell.setCellStyle(cellStyle);
				if(i>7){
					cell.setCellValue(new HSSFRichTextString(top[i - 1].toString()));// 给单元格赋值
				}
			}
			
			iRow = iRow + 1;
			// 写入各条记录
			int rColumn = rs.toArray().length;
			for (int j = 0; j < rColumn; j++) {
				row = sheet.createRow(iRow);
				Object obj[] = new Object[] {};
				obj = (Object[]) rs.get(j);
				for (int k = 0; k < nColumn; k++) {
					cell = row.createCell(k);
					if (obj[k] != null) {
						cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(" "));
					}
					HSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
				}
				iRow = iRow + 1;
			}
			ServletOutputStream fOut = response.getOutputStream();
			
			System.out.println("--------------endend-------------");
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 单贴价格与味数查询逐级下帖
	 */
	public PageSet singleCostQuery(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select b.xm,a.ybh,a.jzbh,a.jylsh,a.mxzdh,a.jyjslb,(select zd_name from d_diagnosis where zd_id = (select zd_id from d_diagnosis_group where zd_group_id=a.zd_group_id and rownum=1)) zd_name,");
		exeSQL.append("a.trade_time,d.xm ysxm,a.ddjg_id,c.name,a.dtjg,a.ypws,");
		exeSQL.append("(select name from d_ddjg where ddjg_id=a.wpddjg_id) wpname,");
		exeSQL.append("(select xm from d_doctor where dr_id=a.wpdr_id) wpysxm,decode(a.zt,0,'购买',2,'退费',4,'被退费','其他') as zt, decode(a.zt,2,'-'||a.ypfy,3,'-'||a.ypfy,a.ypfy) as ypfy ");
		exeSQL.append("from f_fyzf_trade a,d_person b,d_ddjg c,d_doctor d where a.ybh=b.ybh and a.ddjg_id=c.ddjg_id and a.dr_id=d.dr_id ");
		//医师编号（医师编号存在重复所以要医院编码和医师编号联合判断来确认）
		if(StringUtils.isNotEmpty(condition.getDocId())){
			String[] docids = condition.getDocId().split(",");
			exeSQL.append(" and (");
			for (int i = 0; i < docids.length; i++) {
				String[] code = docids[i].split(":"); 
				if (i == 0) {
					exeSQL.append("  (d.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and d.ysdm = '"+code[0]+"') ");
				}else{
					exeSQL.append(" or (d.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and d.ysdm = '"+code[0]+"') ");
				}
			}
			exeSQL.append(" ) ");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and a.trade_time >= to_date('"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"','yyyyMM') ");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and a.trade_time < add_months(to_date('"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"','yyyyMM'),1) ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and c.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and c.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			if("01".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('1','2','6') ");
			}else if("02".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('3') ");
			}else if("03".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('4','5') ");
			}else if("04".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('7','8') ");
			}
		}
//		//就诊类型 
//		if(StringUtils.isNotEmpty(condition.getAka130())){
//			exeSQL.append(" and a.JZLX = '"+condition.getAka130()+"' ");
//		}
//		//机构类别
//		if(StringUtils.isNotEmpty(condition.getAkb022())){
//			exeSQL.append(" and d.akb022 = '"+condition.getAkb022()+"'");
//		}
		//单贴价格开始区间
		if(condition.getDtjgStart()!=null){
			exeSQL.append(" and a.dtjg >= '"+condition.getDtjgStart()+"' ");
		}
		//单贴价格结束区间
		if(condition.getDtjgEnd()!=null){
			exeSQL.append(" and a.dtjg <= '"+condition.getDtjgEnd()+"' ");
		}
		//味数开始区间
		if(condition.getWsStart()!=null){
			exeSQL.append(" and a.ypws >= '"+condition.getWsStart()+"' ");
		}
		//味数结束区间
		if(condition.getWsEnd()!=null){
			exeSQL.append(" and a.ypws <= '"+condition.getWsEnd()+"' ");
		}
		//exeSQL.append(" ) where rownum <= 100 ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 单贴价格与味数查询显示明细
	 */
	public PageSet singleCostQueryDetail(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		exeSQL.append("select distinct b.xm,a.ybh,a.jzbh,a.jyjslb,(select zd_name from d_diagnosis where zd_id = (select zd_id from d_diagnosis_group where zd_group_id=a.zd_group_id and rownum=1)) zd_name,");
		exeSQL.append("a.trade_time,d.xm ysxm,a.ddjg_id,c.name,a.dtjg,a.ypws,");
		exeSQL.append("(select name from d_ddjg where ddjg_id=a.wpddjg_id) wpname,");
		exeSQL.append("(select xm from d_doctor where dr_id=a.wpdr_id) wpysxm,a.ypfy,e.mx_id,e.mx_mc,e.fyze,e.zlbl,e.tym_mc,e.sl,e.ypts,e.mcyl,e.yfbz,e.bzgg ");
		exeSQL.append("from f_fyzf_trade a,d_person b,d_ddjg c,d_doctor d,f_fymx_trade e ");
		exeSQL.append("where a.ybh=b.ybh and a.ddjg_id=c.ddjg_id and a.dr_id=d.dr_id and a.jzbh=e.jzbh and a.jylsh = e.jylsh and a.mxzdh = e.mxzdh ");
		//医师编号（医师编号存在重复所以要医院编码和医师编号联合判断来确认）
		if(StringUtils.isNotEmpty(condition.getDocId())){
			String[] docids = condition.getDocId().split(",");
			exeSQL.append(" and (");
			for (int i = 0; i < docids.length; i++) {
				String[] code = docids[i].split(":"); 
				if (i == 0) {
					exeSQL.append("  (d.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and d.ysdm = '"+code[0]+"') ");
				}else{
					exeSQL.append(" or (d.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and d.ysdm = '"+code[0]+"') ");
				}
			}
			exeSQL.append(" ) ");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and c.ddjg_id in ("+strid+") ");
		}
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and a.trade_time >= to_date('"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"','yyyyMM') ");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and a.trade_time < add_months(to_date('"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"','yyyyMM'),1) ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and c.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and c.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			if("01".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('1','2','6') ");
			}else if("02".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('3') ");
			}else if("03".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('4','5') ");
			}else if("04".equals(condition.getAkc021())){
				exeSQL.append(" and a.xzlb in('7','8') ");
			}
		}
		//if(StringUtils.isNotEmpty(condition.getAkc021())){
		//	exeSQL.append(" and a.xzlb = '"+condition.getAkc021()+"' ");
		//}
//		//就诊类型 
//		if(StringUtils.isNotEmpty(condition.getAka130())){
//			exeSQL.append(" and a.JZLX = '"+condition.getAka130()+"' ");
//		}
//		//机构类别
//		if(StringUtils.isNotEmpty(condition.getAkb022())){
//			exeSQL.append(" and d.akb022 = '"+condition.getAkb022()+"'");
//		}
		//单贴价格开始区间
		if(condition.getDtjgStart()!=null){
			exeSQL.append(" and a.dtjg >= '"+condition.getDtjgStart()+"' ");
		}
		//单贴价格结束区间
		if(condition.getDtjgEnd()!=null){
			exeSQL.append(" and a.dtjg <= '"+condition.getDtjgEnd()+"' ");
		}
		//味数开始区间
		if(condition.getWsStart()!=null){
			exeSQL.append(" and a.ypws >= '"+condition.getWsStart()+"' ");
		}
		//味数结束区间
		if(condition.getWsEnd()!=null){
			exeSQL.append(" and a.ypws <= '"+condition.getWsEnd()+"' ");
		}
		//exeSQL.append(" ) where rownum <= 100 ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 查看费用明细
	 */
	public PageSet queryCostDetail(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
				
		exeSQL.append("select e.mx_id,e.mx_mc,e.fyze,e.zlbl,e.tym_mc,e.sl,e.ypts,e.mcyl,e.yfbz,e.bzgg ");
		exeSQL.append("from f_fymx_trade e where e.jzbh = '"+condition.getAaz370()+"' ");
		if (StringUtils.isNotEmpty(condition.getJylsh())) {
			exeSQL.append(" and e.jylsh = '"+condition.getJylsh()+"' ");
		}
		if (StringUtils.isNotEmpty(condition.getMxzdh())) {
			exeSQL.append(" and e.mxzdh = '"+condition.getMxzdh()+"' ");
		}
		//exeSQL.append("and e.jylsh = '"+condition.getJylsh()+"' and e.mxzdh = '"+condition.getMxzdh()+"' ");
		//医院ID
		if(StringUtils.isNotEmpty(condition.getAkb020())){
			exeSQL.append(" and e.ddjg_id = '"+condition.getAkb020()+"' ");
		}
		//就诊时间
		if(StringUtils.isNotEmpty(condition.getAkc194())){
			exeSQL.append(" and e.trade_time >= to_date('"+condition.getAkc194().replaceAll("-", "").substring(0, 8)+"','yyyyMMdd') ");
			exeSQL.append(" and e.trade_time < add_months(to_date('"+condition.getAkc194().replaceAll("-", "").substring(0, 8)+"','yyyyMMdd'),1) ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//就诊类型 
		if(StringUtils.isNotEmpty(condition.getAka130())){
			exeSQL.append(" and e.jyjslb = '"+condition.getAka130()+"' ");
		}
		
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 医疗机构及药店中药饮片分人员总量统计
	 */
	public PageSet personTotalStatistics(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		exeSQL.append("select * from( ");
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		exeSQL.append("select '"+jsny+"' as jsny,m.hosp_type,m.ddjg_id,m.name,m.hosp_class,(case when (a.zfy_p+nvl(b.zfy_p,0))=0 then 0 else ");
		exeSQL.append("((nvl(a.zfy,0)+nvl(b.zfy,0))-(nvl(a.zfy_p,0)+nvl(b.zfy,0)))/(nvl(a.zfy_p,0)+nvl(b.zfy_p,0)) end)*100 zzzl,");
		//特病非外配处方
		exeSQL.append("nvl(a.fwpfy,0) as TBFWPFY,nvl(a.fwp_tb_rc,0) as TBFWPJZRC,");//费用、贴数
		exeSQL.append("(case when nvl(a.fwp_fy_p,0)=0 then 0 else (a.fwpfy-a.fwp_fy_p)/a.fwp_fy_p end)*100 as TBFWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(a.fwpts,0)=0 then 0 else a.fwpfy/a.fwpts end) as TBFWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(a.fwp_ts_p,0)=0 then 0 else (decode(a.fwpts,0,0,a.fwpfy/a.fwpts)-a.fwp_fy_p/a.fwp_ts_p)/a.fwp_fy_p/a.fwp_ts_p end) as TBFWPJTJGZZL,");//贴均价格增长率
		//特病外配处方
		exeSQL.append("nvl(b.fwpfy,0) as TBWPFY,nvl(b.fwp_tb_rc,0) as TBWPJZRC,");//费用、贴数
		exeSQL.append("(case when nvl(b.fwp_fy_p,0)=0 then 0 else (b.fwpfy-b.fwp_fy_p)/b.fwp_fy_p end)*100 as TBWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(b.fwpts,0)=0 then 0 else b.fwpfy/b.fwpts end) as TBWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(b.fwp_ts_p,0)=0 then 0 else (decode(b.fwpts,0,0,b.fwpfy/b.fwpts)-b.fwp_fy_p/b.fwp_ts_p)/b.fwp_fy_p/b.fwp_ts_p end) as TBWPJTJGZZL,");//贴均价格增长率
		//非特病非外配处方
		exeSQL.append("nvl(a.fwp_ftb_fy,0) as FTBFWPFY,nvl(a.fwp_ftb_rc,0) as FTBFWPJZRC,");//费用、贴数
		exeSQL.append("(case when nvl(a.fwp_ftb_fy_p,0)=0 then 0 else (a.fwp_ftb_fy-a.fwp_ftb_fy_p)/a.fwp_ftb_fy_p end)*100 as FTBFWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(a.fwp_ftb_s,0)=0 then 0 else a.fwp_ftb_fy/a.fwp_ftb_s end) as FTBFWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(a.fwp_ftb_ts_p,0)=0 then 0 else (decode(a.fwp_ftb_s,0,0,a.fwp_ftb_fy/a.fwp_ftb_s)-a.fwp_ftb_fy_p/a.fwp_ftb_ts_p)/a.fwp_ftb_fy_p/a.fwp_ftb_ts_p end) as FTBFWPJTJGZZL,");//贴均价格增长率
		//非特病外配处方
		exeSQL.append("nvl(b.fwp_ftb_fy,0) as FTBWPFY,nvl(b.fwp_ftb_rc,0) as FTBWPJZRC,");//费用、贴数
		exeSQL.append("(case when nvl(b.fwp_ftb_fy_p,0)=0 then 0 else (b.fwp_ftb_fy-b.fwp_ftb_fy_p)/b.fwp_ftb_fy_p end)*100 as FTBWPZZL,");//费用增长率
		exeSQL.append("(case when nvl(b.fwp_ftb_s,0)=0 then 0 else b.fwp_ftb_fy/b.fwp_ftb_s end) as FTBWPJTJG,");//贴均价格
		exeSQL.append("(case when nvl(b.fwp_ftb_ts_p,0)=0 then 0 else (decode(b.fwp_ftb_s,0,0,b.fwp_ftb_fy/b.fwp_ftb_s)-b.fwp_ftb_fy_p/b.fwp_ftb_ts_p)/b.fwp_ftb_fy_p/b.fwp_ftb_ts_p end) as FTBWPJTJGZZL ");//贴均价格增长率
		exeSQL.append("from (select f1.hosp_id,sum(f1.fy) zfy,sum(f1.fy_p) zfy_p,sum(f1.tb_fy) fwpfy,sum(f1.tb_ts) fwpts,sum(f1.tb_fy_p) fwp_fy_p,sum(f1.tb_ts_p) fwp_ts_p,sum(f1.tb_rc) fwp_tb_rc,");
		exeSQL.append("sum(f1.ftb_fy) fwp_ftb_fy,sum(f1.ftb_ts) fwp_ftb_s,sum(f1.ftb_fy_p) fwp_ftb_fy_p,sum(f1.ftb_ts_p) fwp_ftb_ts_p,sum(f1.ftb_rc) fwp_ftb_rc from ");
		exeSQL.append("F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is null ");//特病（非特病）非外配处方
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
		exeSQL.append(" group by f1.hosp_id) a left join");
		exeSQL.append("(select f1.hosp_id,sum(f1.fy) zfy,sum(f1.fy_p) zfy_p,sum(f1.tb_fy) fwpfy,sum(f1.tb_ts) fwpts,sum(f1.tb_fy_p) fwp_fy_p,sum(f1.tb_ts_p) fwp_ts_p,sum(f1.tb_rc) fwp_tb_rc,");
		exeSQL.append("sum(f1.ftb_fy) fwp_ftb_fy,sum(f1.ftb_ts) fwp_ftb_s,sum(f1.ftb_fy_p) fwp_ftb_fy_p,sum(f1.ftb_ts_p) fwp_ftb_ts_p,sum(f1.ftb_rc) fwp_ftb_rc from ");
		exeSQL.append("F_ZYYP_TRADE_ORG_SUM_3 f1,d_ddjg d where f1.hosp_id=d.ddjg_id and trim(yd_id) is not null ");//特病（非特病）外配处方
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
		exeSQL.append(" group by f1.hosp_id) b on a.hosp_id=b.hosp_id,d_ddjg m where a.hosp_id=m.ddjg_id ");
		exeSQL.append(") order by zzzl desc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 医疗机构及药店特定中药饮片统计数据查询
	 */
	public PageSet totalStatisticsQuery(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		exeSQL.append("select '"+jsny+"' as jsny,a.hosp_id,a.name,a.sl,a.fy,a.tymc ");
		exeSQL.append("from (select f1.hosp_id,d.name,f1.mx_id,b.tymc,sum(f1.sl) sl,sum(f1.fy) fy from F_ZYYP_TRADE_ORG_ZYYP_SUM_3 f1,d_drug b,d_ddjg d ");
		exeSQL.append("where f1.mx_id=b.drug_id and f1.hosp_id=d.ddjg_id ");
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//通用名
		if(StringUtils.isNotEmpty(condition.getTym())){
			exeSQL.append(" and b.tymc = '"+condition.getTym()+"' ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
		exeSQL.append(" group by f1.hosp_id,f1.mx_id,b.tymc,d.name) a order by a.fy desc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 医生特定中药饮片统计数据查询
	 */
	public PageSet doctorTotalStatisticsQuery(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		exeSQL.append("select '"+jsny+"' as jsny,a.hosp_type,a.hosp_id,a.name,a.sl,a.fy,a.tymc,a.xm,a.zgzs ");
		exeSQL.append("from (select f1.hosp_id,d.hosp_type,d.name,f1.mx_id,b.tymc,e.xm,e.zgzs,sum(f1.sl) sl,sum(f1.fy) fy from f_zyyp_trade_dr_zyyp_sum_2 f1, d_drug b,d_ddjg d,d_doctor e ");
		exeSQL.append("where f1.mx_id=b.drug_id and f1.hosp_id=d.ddjg_id and f1.dr_id=e.dr_id ");
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and f1.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and f1.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//通用名
		if(StringUtils.isNotEmpty(condition.getTym())){
			exeSQL.append(" and b.tymc = '"+condition.getTym()+"' ");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and d.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and d.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and f1.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and f1.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and d.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())&&StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			String[] ydIds = condition.getYdIds().split(",");
			for (int i = 0; i < ydIds.length; i++) {
				strid += ",'" + ydIds[i] + "'";
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}else if(StringUtils.isNotEmpty(condition.getYdIds())){
			String strid = "";
			String[] ids = condition.getYdIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and d.ddjg_id in ("+strid+") ");
		}
		//医师编号（医师编号存在重复所以要医院编码和医师编号联合判断来确认）
		if(StringUtils.isNotEmpty(condition.getDocId())){
			String[] docids = condition.getDocId().split(",");
			exeSQL.append(" and (");
			for (int i = 0; i < docids.length; i++) {
				String[] code = docids[i].split(":"); 
				if (i == 0) {
					exeSQL.append("  (e.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and e.ysdm = '"+code[0]+"') ");
				}else{
					exeSQL.append(" or (e.hosp_id = '"+code[1]+"' ");
					exeSQL.append(" and e.ysdm = '"+code[0]+"') ");
				}
			}
			exeSQL.append(" ) ");
		}
		exeSQL.append(" group by f1.hosp_id,d.hosp_type,d.name,f1.mx_id,b.tymc,e.xm,e.zgzs) a order by a.fy desc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 单个医生人均总费用统计
	 */
	public PageSet singleDoctorStatisticsQuery(PageSet pageSet,QueryCondition condition) {
		StringBuffer exeSQL=new StringBuffer();
		StringBuffer countSQL=new StringBuffer();
		
		String jsny = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())&&StringUtils.isNotEmpty(condition.getEndDate())){
			if(condition.getStartDate().equals(condition.getEndDate())){
				jsny = condition.getStartDate();
			}else{
				jsny = condition.getStartDate() + "~" + condition.getEndDate();
			}
		}
		//exeSQL.append("select '"+jsny+"' as jsny,a.hosp_id,a.name,a.rs,a.fy,a.rjfy,a.xm,a.zgzs ");
		exeSQL.append("select * from (select '"+jsny+"', a.hosp_id, b.name, c.xm, c.zgzs, a.xzlb, sum(a.rs) rs, sum(a.fy) fy, " +
				"decode(sum(a.rs),0,0, sum(a.fy)/sum(a.rs)) rjfy from f_zyyp_trade_dr_rs_sum_2 a, d_ddjg b, d_doctor c " +
				"where a.hosp_id = b.ddjg_id and a.dr_id = c.dr_id");
		//exeSQL.append("from f_zyyp_trade_dr_zyyp_sum_2 f1,d_ddjg d,d_doctor e where f1.hosp_id=d.ddjg_id and f1.dr_id=e.dr_id ");
		//开始时间
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQL.append(" and a.jsny >= '"+condition.getStartDate().substring(0, 4)+condition.getStartDate().substring(5, 7)+"'");
		}
		//结束时间
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQL.append(" and a.jsny <= '"+condition.getEndDate().substring(0, 4)+condition.getEndDate().substring(5, 7)+"'");
		}
		//统筹区
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			//市本级包括11个区
			if(condition.getAaa027().equals("330299")){
				exeSQL.append(" and b.admin_region_id in ('330203','330204','330205','330206','330211','330212','330240','330241','330242','330243','330244') ");
			}else{
				exeSQL.append(" and b.admin_region_id = '"+condition.getAaa027()+"' ");
			}
		}
		//人员类别
		if(StringUtils.isNotEmpty(condition.getAkc021())){
			exeSQL.append(" and a.xzlb = '"+condition.getAkc021()+"' ");
		}else{
			exeSQL.append(" and a.xzlb in ('01','02','03','04') ");
		}
		//机构类别
		if(StringUtils.isNotEmpty(condition.getAkb022())){
			exeSQL.append(" and b.hosp_type = '"+condition.getAkb022()+"'");
		}
		//医院编码
		if(StringUtils.isNotEmpty(condition.getHosIds())){
			String strid = "";
			String[] ids = condition.getHosIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				if ((i + 1) == ids.length) {
					strid += "'" + ids[i] + "'";
				} else {
					strid += "'" + ids[i] + "',";
				}
			}
			exeSQL.append(" and b.ddjg_id in ("+strid+") ");
		}
		exeSQL.append(" group by a.hosp_id,b.name,c.xm,c.zgzs,a.xzlb) t order by t.rjfy desc ");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}

	/**
	 * 医疗机构及药店中药饮片分人员总量统计导出EXCEL
	 */
	public void resultSetToExcelPersonStatics(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		System.out.println("----resultSetToExcel-----------------");
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("单个医生的中药饮片费用及贴均价格", "utf-8")+".xls");
			HSSFSheet sheet = workbook.createSheet();
			int iRow = 0;// 行下标
			workbook.setSheetName(0, sheetName);
			HSSFRow row = sheet.createRow(iRow);
			HSSFCell cell = null;
			cell = row.createCell(0);
			// 设置字体样式
			HSSFFont font = workbook.createFont();
			font.setFontName(HSSFFont.FONT_ARIAL);// 字体
			font.setFontHeightInPoints((short) 15);// 字号
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			// font.setColor(HSSFColor.BLUE.index);//颜色
			HSSFCellStyle cellStyle_1 = workbook.createCellStyle(); // 设置单元格样式
			cellStyle_1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle_1.setFont(font);
			cell.setCellStyle(cellStyle_1);
			cell.setCellValue(new HSSFRichTextString(xlsName));// 设置文件名第一行展示
//			cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,top.length - 1));
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			//行合并
			sheet.addMergedRegion(new Region(1, (short) (0), 2,(short) (0)));
			sheet.addMergedRegion(new Region(1, (short) (1), 2,(short) (1)));
			sheet.addMergedRegion(new Region(1, (short) (2), 2,(short) (2)));
			sheet.addMergedRegion(new Region(1, (short) (3), 2,(short) (3)));
			sheet.addMergedRegion(new Region(1, (short) (4), 2,(short) (4)));
			//列合并
			sheet.addMergedRegion(new Region(1, (short) (5), 1,(short) (9)));
			sheet.addMergedRegion(new Region(1, (short) (10), 1,(short) (14)));
			sheet.addMergedRegion(new Region(1, (short) (15), 1,(short) (19)));
			sheet.addMergedRegion(new Region(1, (short) (20), 1,(short) (24)));
			for(int i=1;i<=25;i++){
				sheet.setColumnWidth(i-1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell(i-1);
				HSSFCellStyle cellStyle1 = workbook.createCellStyle(); // 设置单元格样式
				cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle1.setWrapText(true);
				cell.setCellStyle(cellStyle1);
				if(i==1){
					cell.setCellValue(new HSSFRichTextString("结算年月"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("机构类别"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("机构代码"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("机构名称"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("医院等级"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("特病非外配处方 "));// 给单元格赋值
				}else if(i==11){
					cell.setCellValue(new HSSFRichTextString("特病外配处方 "));// 给单元格赋值
				}else if(i==16){
					cell.setCellValue(new HSSFRichTextString("非特病非外配处方 "));// 给单元格赋值
				}else if(i==21){
					cell.setCellValue(new HSSFRichTextString("非特病外配处方 "));// 给单元格赋值
				}else{
					cell.setCellValue(new HSSFRichTextString(""));
				}
			}
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			int nColumn = top.length;
			for (int i = 1; i <= nColumn; i++) {
				sheet.setColumnWidth(i - 1, 20 * 256);// 设置单元格宽度：18字符
				cell = row.createCell((i - 1));
				HSSFCellStyle cellStyle = workbook.createCellStyle(); // 设置单元格样式
				cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle.setWrapText(true);
				cell.setCellStyle(cellStyle);
				if(i>7){
					cell.setCellValue(new HSSFRichTextString(top[i - 1].toString()));// 给单元格赋值
				}
			}
			
			iRow = iRow + 1;
			// 写入各条记录
			int rColumn = rs.toArray().length;
			for (int j = 0; j < rColumn; j++) {
				row = sheet.createRow(iRow);
				Object obj[] = new Object[] {};
				obj = (Object[]) rs.get(j);
				for (int k = 0; k < nColumn; k++) {
					cell = row.createCell(k);
					if (obj[k] != null) {
						cell.setCellValue(new HSSFRichTextString(obj[k].toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(" "));
					}
					HSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellStyle.setDataFormat((short) 0x31);// 设置显示格式，避免点击后变成科学计数法了
					cellStyle.setWrapText(true);
					cell.setCellStyle(cellStyle);
				}
				iRow = iRow + 1;
			}
			ServletOutputStream fOut = response.getOutputStream();
			
			System.out.println("--------------endend-------------");
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
}
