package com.wondersgroup.qyws.tjfx.common;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class StringUtils {
	public static String filterString(String temp) {
		String htmlStr = temp;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}
	
	public static String formatScore(double score){
		if(Double.isNaN(score)){
			return "0";
		}
		DecimalFormat df = new DecimalFormat("###,###.###");
		return df.format(score);
	}
	/**
     * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br> 
     * @return String
     * @author pure
     */
    public static String thisMonth(String str ){
    	String x,y;
        String strY = null;
        x = str.substring(0, 4);
        y = str.substring(5, 7);
        strY = Integer.parseInt(y) >= 10 ? String.valueOf(Integer.parseInt(y)) : ("0" + Integer.parseInt(y));
        return x + "-" + strY + "-01";
    }

    /**
     * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br> 
     * @return String
     * @author pure
     */
    public static  String thisMonthEnd(String str){
    	int x,y;
        String strY = null;
        String strZ = null;
        boolean leap = false;
        x = Integer.parseInt(str.substring(0, 4));
        y = Integer.parseInt(str.substring(5, 7));
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            }
            else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }
    /**
     * 功能：判断输入年份是否为闰年<br>
     * 
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year){
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            }
            else leap = true;
        }
        else leap = false;
        return leap;
    }
}
