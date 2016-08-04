package com.wondersgroup.qyws.tjfx.util;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ExportExcelUtil {

	/**
	 * @param rs
	 *            数据List<Object[]>
	 * @param top
	 *            表头数组
	 * @param xlsName
	 *            文件名
	 * @param sheetName
	 *            sheet名
	 * @throws Exception
	 */
	public static void resultSetToExcel(List<Object[]> rs, String[] top,String xlsName, String sheetName) throws Exception {
		System.out.println("----resultSetToExcel-----------------");
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(xlsName, "utf-8")+".xls");
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
				cell.setCellValue(new HSSFRichTextString(top[i - 1].toString()));// 给单元格赋值
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
