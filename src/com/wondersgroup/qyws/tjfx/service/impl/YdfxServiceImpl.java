package com.wondersgroup.qyws.tjfx.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.wondersgroup.qyws.tjfx.service.YdfxService;
import com.wondersgroup.util.papper.PageSet;


@Service("YdfxServiceImplService")
@Transactional
public class YdfxServiceImpl extends ServiceImpl implements YdfxService{

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
	@SuppressWarnings("deprecation")
	public void resultSetToExcelDoctor(List<Object[]> rs, String[] top,String xlsName, String sheetName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("医生开外配处方次数、费用排名（总的排名）", "utf-8")+".xls");
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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("医保号"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("姓名"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("区县"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("总费用"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("费用最高药店名称"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("费用最高药店所属区县 "));// 给单元格赋值
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("总费用排名 "));// 给单元格赋值
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
			
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 参保人购药排名
	 */
	public PageSet cbrRanking(PageSet pageSet, QueryCondition condition) {
		String date1 = condition.getStartDate();
		String date2 = condition.getEndDate();
		String startDate = "";
		String endDate = "";
		if (StringUtils.isNotEmpty(condition.getType())) {
			if ("1".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + "01";
				endDate = date1.substring(0, 4) + "12";
			}
			if ("2".equals(condition.getType())) {
				startDate = date1.substring(0, 4) + date1.substring(5, 7);
				endDate = date2.substring(0, 4) + date2.substring(5, 7);
			}
			if ("3".equals(condition.getType())) {
				String quarter = condition.getByQuarter();
				if (condition.getByQuarter().endsWith("1")) {
					startDate = quarter.substring(0, 4) + "01";
					endDate = quarter.substring(0, 4) + "03";
				}
				if (condition.getByQuarter().endsWith("2")) {
					startDate = quarter.substring(0, 4) + "04";
					endDate = quarter.substring(0, 4) + "06";
				}
				if (condition.getByQuarter().endsWith("3")) {
					startDate = quarter.substring(0, 4) + "07";
					endDate = quarter.substring(0, 4) + "09";
				}
				if (condition.getByQuarter().endsWith("4")) {
					startDate = quarter.substring(0, 4) + "10";
					endDate = quarter.substring(0, 4) + "12";
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
		
		StringBuffer exeSQL=new StringBuffer("select t1.* from(select max(a.ybh) as ybh, max(b.xm) xm, " +
				"(select t2.name from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate + "' and ybh = a.ybh) t1, " +
				"d_ddjg t2 where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) mc, " +
				"(select t2.admin_region_id from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate + "' and ybh = a.ybh) t1, d_ddjg t2 " +
				"where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) xzqh, max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, sum(a.fyze) fyze " +
				"from F_PHARM_PAY_YDCBR_2 a, d_person b where a.ybh = b.ybh  and a.jsny >= '" + startDate + "'  and a.jsny <= '" + endDate + "' ");
		StringBuffer countSQL=new StringBuffer();
		
		if (StringUtils.isNotEmpty(condition.getAaa027())) {
			exeSQL.append(" and a.jyqhdm = '" + condition.getAaa027() + "' ");
		}
		exeSQL.append(" group by a.ybh) t1 where 1 = 1  ");
		exeSQL.append(" order by t1.fyze desc");
		countSQL.append("select count(*) from (");
		countSQL.append(exeSQL.toString());
		countSQL.append(" ) ");
		
		return this.baseDao.queryBySql(pageSet,exeSQL.toString(),countSQL.toString());
	}
	
	public PageSet ydTotalStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select a.yd_id, a.fcfrc, a.fcffy, a.cfrc, a.cffy, a.fyze, " +
				"b.name, b.admin_region_id as aaa027, (a.fcfrc + a.cfrc) as zrc " +
				"from f_pharm_pay_yd_2 a, d_ddjg b where b.ddjg_id = a.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	/**
	 * 总费用统计导出EXCEL
	 */
	public String resultSetToExcelYdTotalStatistics(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("药店总数据统计", "utf-8")+".xls");
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
			sheet.addMergedRegion(new Region(1, (short) (5), 1,(short) (6)));
			sheet.addMergedRegion(new Region(1, (short) (7), 1,(short) (8)));
			sheet.addMergedRegion(new Region(1, (short) (9), 1,(short) (24)));
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
					cell.setCellValue(new HSSFRichTextString("药店代码"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("药店名称"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("区县"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("接诊总人次"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("总费用"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("非处方药"));// 给单元格赋值
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("处方药"));// 给单元格赋值
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			
		}
		return "success";
	}
	
public PageSet ydMonthMove(PageSet pageSet, QueryCondition condition) {
		
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select a.yd_id, a.jsny, b.name, b.admin_region_id as aaa027, " +
				"round(a.cfrc/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),0) as pjcfrc, " +
				"round(a.cffy/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),4) as pjcffy, " +
				"round(a.fcffy/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),4) as pjfcffy, " +
				"round(a.fcfrc/(add_months(trunc(to_date(a.jsny,'YYYYMM'),'MM'),1)-trunc(to_date(a.jsny,'YYYYMM'),'MM')),0) as pjfcfrc " +
				"from F_PHARM_PAY_YD_2 a, d_ddjg b where b.ddjg_id = a.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}


	/**
	 * 月移动平均导出EXCEL
	 */
	public void exportExcelYdMonthMove(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("药店月接诊人次、费用移动平均", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,top.length - 1));
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			//行合并
			sheet.addMergedRegion(new Region(1, (short) (0), 2,(short) (0)));
			sheet.addMergedRegion(new Region(1, (short) (1), 2,(short) (1)));
			sheet.addMergedRegion(new Region(1, (short) (2), 2,(short) (2)));
			sheet.addMergedRegion(new Region(1, (short) (3), 2,(short) (3)));
			//列合并
			sheet.addMergedRegion(new Region(1, (short) (4), 1,(short) (5)));
			sheet.addMergedRegion(new Region(1, (short) (6), 1,(short) (7)));
			sheet.addMergedRegion(new Region(1, (short) (8), 1,(short) (24)));
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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("药店代码"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("药店名称"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("区县"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("非处方药"));
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("处方药"));// 给单元格赋值
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 药店月（季、年）接诊非处方药人次、费用、次均费用(药店月费用)
	 */
	public void exportExcelYdCostStatistics(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("药店月（季、年）接诊非处方药人次、费用、次均费用", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,top.length - 1));
			
			iRow = iRow + 1;
			row = sheet.createRow(iRow);
			//行合并
			sheet.addMergedRegion(new Region(1, (short) (0), 2,(short) (0)));
			sheet.addMergedRegion(new Region(1, (short) (1), 2,(short) (1)));
			sheet.addMergedRegion(new Region(1, (short) (2), 2,(short) (2)));
			sheet.addMergedRegion(new Region(1, (short) (3), 2,(short) (3)));
			//列合并
			sheet.addMergedRegion(new Region(1, (short) (4), 1,(short) (6)));
			sheet.addMergedRegion(new Region(1, (short) (7), 1,(short) (9)));
			sheet.addMergedRegion(new Region(1, (short) (10), 1,(short) (12)));
			sheet.addMergedRegion(new Region(1, (short) (13), 1,(short) (25)));
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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("药店代码"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("药店名称"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("区县"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("当年账户非处方药"));
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("单次非处方购药（当年账户）90元以上"));// 给单元格赋值
				}else if(i==11){
					cell.setCellValue(new HSSFRichTextString("历年账户非处方药"));
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	public PageSet ydCostStatistics(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select a.yd_id,  max(b.name) as ydName, max(b.admin_region_id) as aaa027, sum(a.fcfrc) as fcfrc, sum(a.fcffy) as fcffy, " +
				"round(decode((sum(a.fcffy)/decode(sum(a.fcfrc), 0,1, sum(a.fcfrc))),sum(a.fcffy), 0, sum(a.fcffy)/" +
				"decode(sum(a.fcfrc), 0, 1, sum(a.fcfrc))),4) as fcfcjfy, sum(a.cfrc) as cfrc, sum(a.cffy) as cffy, " +
				"round(decode((sum(a.cffy)/decode(sum(a.cfrc), 0,-1, sum(a.cfrc))),-1, 0, sum(a.cffy)/" +
				"decode(sum(a.cfrc), 0, -1, sum(a.cfrc))),4) as cfpjrc from F_PHARM_PAY_YD_2 a, d_ddjg b where b.ddjg_id = a.yd_id ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '"+startDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '"+endDate+"'");
		}
		if(StringUtils.isNotEmpty(condition.getYdIds())){
			exeSQl.append(" and a.yd_id = '"+condition.getYdIds()+"'");
		}
		exeSQl.append("group by a.yd_id ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	/**
	 * 参保人药店购药按参保地与就医地相分离要素排名(分离要素排名)
	 */
	@SuppressWarnings("deprecation")
	public void exportExcelYdCostRanking(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("参保人药店购药按参保地与就医地相分离要素排名", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
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
			sheet.addMergedRegion(new Region(1, (short) (7), 2,(short) (7)));
			sheet.addMergedRegion(new Region(1, (short) (8), 2,(short) (8)));
			sheet.addMergedRegion(new Region(1, (short) (9), 2,(short) (9)));
			sheet.addMergedRegion(new Region(1, (short) (10), 2,(short) (10)));

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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("医保号"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("姓名"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("待遇类型"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("参保地区县"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("就医地区县"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("购药总费用"));
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("费用最高药店"));
				}else if(i==9){
					cell.setCellValue(new HSSFRichTextString("处方药费用"));
				}else if(i==10){
					cell.setCellValue(new HSSFRichTextString("非处方药费用"));
				}else if(i==11){
					cell.setCellValue(new HSSFRichTextString("总费用排名"));
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	public PageSet ydCostRanking(PageSet pageSet, QueryCondition condition) {
		
		String date1 = condition.getStartDate();
		String startDate = date1.substring(0, 4) + date1.substring(5, 7);
		String date2 = condition.getEndDate();
		String endDate = date2.substring(0, 4) + date2.substring(5, 7);
		
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from(select max(a.ybh) as ybh, max(b.xm) xm, max(a.xzlb) as xzlb, " +
				"(select t2.name from (select ddjg_id,row_number() over(partition by ybh order by fyze desc) rk " +
				"from F_PHARM_PAY_YDCBR_2 where jsny >= '" + startDate + "' and jsny <= '" + endDate +"' and ybh = a.ybh) t1, d_ddjg t2 " +
				"where t1.rk = 1 and t1.ddjg_id = t2.ddjg_id) mc, " +
				"max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, sum(a.fyze) fyze, max(a.ddjg_id) as ddjg_id," +
				"sum(a.cffy) cffy, sum(a.fcffy) fcffy from F_PHARM_PAY_YDCBR_2 a, " +
				"d_person b where a.ybh = b.ybh ");
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		exeSQl.append(" group by a.ybh) t1 where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and t1.cbxzqh = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getJyaaa027())){
			exeSQl.append(" and t1.jyqhdm = '"+condition.getJyaaa027()+"'");
		}
		exeSQl.append(" order by t1.fyze desc");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	public PageSet ydMonthCostRanking(PageSet pageSet, QueryCondition condition) {
		
		
		String startDate = "";
		String start = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			startDate = condition.getStartDate();
			start = startDate.substring(0, 4) + startDate.substring(5, 7);
		}
		String endDate = "";
		String end = "";
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			endDate = condition.getEndDate();
			end = endDate.substring(0, 4) + endDate.substring(5, 7);
		}
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select max(a.ybh) as ybh, max(b.xm) xm, max(a.xzlb) as xzlb, max(a.cbxzqh) cbxzqh, max(a.jyqhdm) jyqhdm, " +
				"sum(a.fyze) fyze, sum(a.fyze)/(months_between(add_months(to_date('" + end + "','yyyyMM'),1)," +
				"to_date('" + start + "', 'yyyyMM'))) as ypjfy from F_PHARM_PAY_YDCBR_2 a, d_person b " +
				"where a.ybh = b.ybh ");
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			exeSQl.append(" and a.jsny >= '" + start + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			exeSQl.append(" and a.jsny <= '" + end + "' ");
		}
		exeSQl.append(" group by a.ybh) t1 where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and t1.cbxzqh = '"+condition.getAaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getJyaaa027())){
			exeSQl.append(" and t1.jyqhdm = '"+condition.getJyaaa027()+"'");
		}
		if(StringUtils.isNotEmpty(condition.getPjfy())){
			exeSQl.append(" and t1.ypjfy >= '"+condition.getPjfy()+"'");
		}
		exeSQl.append(" order by t1.ypjfy desc");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	/**
	 * 参保地与就医地相分离人员月平均费用排名(月平均费用排名)
	 */
	@SuppressWarnings("deprecation")
	public void exportExcelYdMonthCostRanking(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("参保地与就医地相分离人员月平均费用排名", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
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
			sheet.addMergedRegion(new Region(1, (short) (7), 2,(short) (7)));
			sheet.addMergedRegion(new Region(1, (short) (8), 2,(short) (8)));

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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("医保号"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("姓名"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("待遇类型"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("参保地区县"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("就医地区县"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("总费用"));
				}else if(i==8){
					cell.setCellValue(new HSSFRichTextString("月平均费用"));
				}else if(i==9){
					cell.setCellValue(new HSSFRichTextString("月平均费用排名"));
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	public PageSet docWpcfRanking(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select max(a.ddjg_id) as ddjg_id, max(b.name) as name, " +
				"c.xm, sum(a.wpcfcs) as wpcfcs, sum(a.wpcffy) as wpcffy " +
				"from F_PHARM_PAY_DOC_2 a, d_ddjg b, d_doctor c where a.ddjg_id = b.ddjg_id and a.dr_id = c.dr_id ");
		
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		exeSQl.append(" group by c.xm) t1 where 1 = 1 ");
		
		if(StringUtils.isNotEmpty(condition.getPm())){
			exeSQl.append(" and rownum <= " + condition.getPm());
		}
		exeSQl.append(" order by t1.wpcffy desc");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	/**
	 * 医生开外配处方次数、费用排名（总的排名）(外配处方排名（总）)
	 */
	@SuppressWarnings("deprecation")
	public void exportExcelDocWpcfRanking(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("医生开外配处方次数、费用排名（总的排名）", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("医院代码"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("医院名称"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("医生姓名"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("外配处方次数"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("外配处方费用"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("外配处方费用排名"));
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}
	
	public PageSet docYdWpcfRanking(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select max(a.ddjg_id) as ddjg_id, max(b.name) as name, " +
				"c.xm, sum(a.wpcfcs) as wpcfcs, sum(a.wpcffy) as wpcffy " +
				"from F_PHARM_PAY_DOC_2 a, d_ddjg b, d_doctor c where a.ddjg_id = b.ddjg_id and a.dr_id = c.dr_id ");
		if (StringUtils.isNotEmpty(condition.getYdIds())) {
		exeSQl.append(" and a.ddjg_id = '" + condition.getYdIds() + "' ");
			
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '" + startDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = condition.getEndDate();
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '" + endDate + "' ");
		}
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and b.admin_region_id = '"+condition.getAaa027()+"'");
		}
		exeSQl.append(" group by c.xm) t1 where 1 = 1 ");
		
		exeSQl.append(" order by t1.wpcffy desc");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
	
	/**
	 * 医生开外配处方次数、费用排名（按药店查询）(外配处方排名)
	 */
	@SuppressWarnings("deprecation")
	public void exportExcelDocYdWpcfRanking(List<Object[]> rs, String[] top,String sheetName, String xlsName) {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("医生开外配处方次数、费用排名（按药店查询）", "utf-8")+".xls");
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
	//		cell.setCellValue(new HSSFRichTextString(xlsName.substring(0,xlsName.length() - 4)));// 设置文件名第一行展示
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
					cell.setCellValue(new HSSFRichTextString("时间"));
				}else if(i==2){
					cell.setCellValue(new HSSFRichTextString("医院代码"));
				}else if(i==3){
					cell.setCellValue(new HSSFRichTextString("医院名称"));
				}else if(i==4){
					cell.setCellValue(new HSSFRichTextString("医生姓名"));
				}else if(i==5){
					cell.setCellValue(new HSSFRichTextString("外配处方次数"));
				}else if(i==6){
					cell.setCellValue(new HSSFRichTextString("外配处方费用"));// 给单元格赋值
				}else if(i==7){
					cell.setCellValue(new HSSFRichTextString("外配处方费用排名"));
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
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
		}
	}

	public PageSet docWpcfDetail(PageSet pageSet, QueryCondition condition) {
		
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select a.ddjg_id, b.name as akb021, b.admin_region_id as aaa027, a.wpcfcs, a.wpcffy, c.xm as docName from F_PHARM_PAY_DOC_2 a, d_ddjg b, d_doctor c where a.yd_id = b.ddjg_id and a.dr_id = c.dr_id ");
		
		if (StringUtils.isNotEmpty(condition.getDocId())) {
			String docId = "";
			try {
				docId = URLDecoder.decode(condition.getDocId().toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			exeSQl.append("and a.dr_id = '" + docId + "'");
		}
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = "";
			try {
				date = URLDecoder.decode(condition.getStartDate().toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny >= '" + startDate +"' ");
		}
		if(StringUtils.isNotEmpty(condition.getEndDate())){
			String date = "";
			try {
				date = URLDecoder.decode(condition.getEndDate().toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String endDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append(" and a.jsny <= '" + endDate +"' ");
		}
		
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" )");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}

	public PageSet hosAggregateAnalysis(PageSet pageSet, QueryCondition condition) {
		StringBuffer exeSQl = new StringBuffer();
		StringBuffer countSQL = new StringBuffer();
		exeSQl.append("select * from (select a.sj, a.jgdm, a.jgmc, a.xzqhdm, decode(a.fs,0,'0.00',trim(to_char(a.fs,'9999.00'))) as fs from TB_YD_ZHFX a where 1 = 1 ");
		if(StringUtils.isNotEmpty(condition.getStartDate())){
			String date = condition.getStartDate();
			String startDate = date.substring(0, 4) + date.substring(5, 7);
			exeSQl.append("  and a.sj = '" + startDate + "' ");
		}
		
		if(StringUtils.isNotEmpty(condition.getAaa027())){
			exeSQl.append(" and a.xzqhdm = '"+condition.getAaa027()+"'");
		}
		
		exeSQl.append(" order by a.fs desc) where rownum <= 100 ");
		countSQL.append(" select count(*) from (");
		countSQL.append(exeSQl.toString());
		countSQL.append(" ) ");
		
		return this.baseDao.queryBySql(pageSet, exeSQl.toString(), countSQL.toString());
	}
}
