package com.wondersgroup.qyws.tjfx.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class CommonUtil {
	
	 public static String getDayOfWeek(String date){ 

	      try{

	         SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	         Calendar cal = Calendar.getInstance();

	         //这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误

	         cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一

	         cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始

	         cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天

	         cal.setTime(df.parse(date));
	         
	         String dayWeek = new DateFormatSymbols().getShortWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];	

	         return dayWeek;

	      }

	      catch(Exception e){

	         e.printStackTrace();

	      }

	      return "";

	   }
	
	//使用正则表达式实现不区分大小写替换

	public static String replaceStringP(String source, String oldstring, 

	   String newstring){ 

		Matcher m = Pattern.compile(oldstring, Pattern.CASE_INSENSITIVE).matcher(source); 

		String result=m.replaceAll(newstring); 

		return result;

	} 


	
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	} 


	/**
	 * * 根据scope保留data小数位数 * 参数:@param data 原始数据 * 参数:@scope 保留的小数点位数 *
	 * return:String
	 * */
	public static String round(Object data, Integer scope) {
		String value = "0";
		int roundingMode = 0;
		if (data != null && NumberUtils.isNumber(data.toString())) {
			value = data.toString();
		}
		if (scope != null && NumberUtils.isNumber(String.valueOf(scope))) {
			roundingMode = scope;
		}
		return new BigDecimal(value).setScale(roundingMode,
				BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 生成随机主键ID
	 * 
	 * @return 主键ID
	 */
	public static String generateNumber() {
		SimpleDateFormat sf = new SimpleDateFormat("MMddHHmmssSSS");
		return sf.format(new Date()) + RandomStringUtils.randomNumeric(5);
	}

	/**
	 * 保存XML文件
	 * */
	public static String saveFileXml(Object obj, String fileName, String content) {
		try {
			String path = obj.getClass().getClassLoader().getResource(
					"configure/mz/").getPath();
			File file = new File(path + "/" + fileName);
			if (file.exists() == false) {
				file.createNewFile();
			}
			OutputStream ots = new FileOutputStream(file);
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(
					ots, "UTF-8"));
			write.write(content);
			write.flush();
			write.close();
			ots.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		return "success";
	}

	public static String deleteXmlFile(Object obj, String fileName) {
		try {
			String path = obj.getClass().getClassLoader().getResource(
					"configure/mz/").getPath();
			File file = new File(path + "/" + fileName);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		return "success";
	}

	/**
	 * 文件流读取xml文件
	 * */
	public static String loadStreamXML(Object obj, String conf) {
		String xml = "";
		try {
			InputStream is = new FileInputStream(obj.getClass()
					.getClassLoader().getResource(conf).getPath());
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			xml = buffer.toString();
			in.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	/**
	 * 通过前台时间控件获取时间
	 * */
	public static String[] initDateParame(String selectStartDate,
			String selectEndDate, String selectType, String displayType,String timeBack) {
		String startDate = "";
		String endDate = "";
		String title = "";
		if ("date".equals(displayType)) {
			if (StringUtils.isNotBlank(selectStartDate)
					&& StringUtils.isNotBlank(selectType)) {
				if ("year".equals(selectType)) {
					endDate = selectStartDate.concat("1231");
					//Integer endYear = Integer.parseInt(selectStartDate)- Integer.parseInt(selectEndDate);
					startDate = selectStartDate.concat("0101");
					title=selectStartDate+"年";
					if(StringUtils.isNotBlank(timeBack))
					{
						int newDate = Integer.parseInt(selectStartDate)-Integer.parseInt(timeBack);
						startDate = newDate+"0101";
					}
					
				} else if ("month".equals(selectType)) {

					startDate = selectStartDate.concat("01");
					String year = selectStartDate.substring(0, 4);
					String month = selectStartDate.substring(4);
					endDate = DateUtils.convertToString(DateUtils
							.getMonthLast(Integer.parseInt(year), Integer
									.parseInt(month) - 1),
							DateUtils.ISO_DATE_NO_HYPHEN_FORMAT);
					title=year+"年"+month+"月";
					
					if(StringUtils.isNotBlank(timeBack))
					{  
						startDate=DateUtils.getAfterMonthDate(selectStartDate,-Integer.parseInt(timeBack))+"01";
					}

				} else if ("week".equals(selectType)) {
					String start = selectStartDate.substring(0, 4);
					String end = selectStartDate.substring(4);
					title=start+"年第"+end+"季度";
					if ("01".equals(end)) {
						startDate = start.concat("01").concat("01");
						endDate = start.concat("03").concat("31");
						
//						if (StringUtils.isNotBlank(selectEndDate)) {
//							startDate = DateUtil.addMenthDate(start.concat("01"),
//									Integer.parseInt(selectEndDate)*3).concat("01");
//						}
					} else if ("02".equals(end)) {
						startDate = start.concat("04").concat("01");
						endDate = start.concat("06").concat("30");
						
//						if (StringUtils.isNotBlank(selectEndDate)) {
//							startDate = DateUtil.addMenthDate(start.concat("04"),
//									Integer.parseInt(selectEndDate)*3).concat("01");
//						}
						
					} else if ("03".equals(end)) {
						startDate = start.concat("07").concat("01");
						endDate = start.concat("09").concat("30");
						
//						if (StringUtils.isNotBlank(selectEndDate)) {
//							startDate = DateUtil.addMenthDate(start.concat("07"),
//									Integer.parseInt(selectEndDate)*3).concat("01");
//						}
						
					} else if ("04".equals(end)) {
						startDate = start.concat("10").concat("01");
						endDate = start.concat("12").concat("31");
						
//						if (StringUtils.isNotBlank(selectEndDate)) {
//							startDate = DateUtil.addMenthDate(start.concat("10"),
//									Integer.parseInt(selectEndDate)*3).concat("01");
//						}
					}
					
					if(StringUtils.isNotBlank(timeBack))
					{
						startDate = DateUtils.getAfterDayDate(endDate,-Integer.parseInt(timeBack)*3);
					}
				}
			}
		} else if ("interval".equals(displayType)) {
			if (StringUtils.isNotBlank(selectStartDate)
					&& StringUtils.isNotBlank(selectEndDate)
					&& StringUtils.isNotBlank(selectType)) {
				if ("year".equals(selectType)) {
					if (selectStartDate.length() > 4) {
						selectStartDate = selectStartDate.substring(0, 4);
					}
					startDate = selectStartDate.concat("0101");
					endDate = selectEndDate.concat("1231");
					title=selectStartDate+"年  至  "+selectEndDate+"年";
					if(StringUtils.isNotBlank(timeBack))
					{
						int newDate = Integer.parseInt(selectEndDate)-Integer.parseInt(timeBack);
						startDate = newDate+"0101";
					}
					
				} else if ("month".equals(selectType)) {
					startDate = selectStartDate.concat("01");
					String year = selectEndDate.substring(0, 4);
					String month = selectEndDate.substring(4);
					endDate = DateUtils.convertToString(DateUtils
							.getMonthLast(Integer.parseInt(year), Integer
									.parseInt(month) - 1),
							DateUtils.ISO_DATE_NO_HYPHEN_FORMAT);
					
					title=selectStartDate.substring(0, 4)+"年"+selectStartDate.substring(4)+"月  至  "+selectEndDate.substring(0, 4)+"年"+selectEndDate.substring(4)+"月";
					
					if(StringUtils.isNotBlank(timeBack))
					{
						startDate = DateUtils.getAfterMonthDate(selectEndDate,-Integer.parseInt(timeBack))+"01";
					}
					
				} else if ("week".equals(selectType)) {
					startDate = selectStartDate;
					endDate = selectEndDate;
					
					title=selectStartDate.substring(0, 4)+"年"+selectStartDate.substring(4,6)+"月"+selectStartDate.substring(6)+"日  至  "+selectEndDate.substring(0, 4)+"年"+selectEndDate.substring(4,6)+"月"+selectEndDate.substring(6)+"日";
					
					if(StringUtils.isNotBlank(timeBack))
					{
						startDate = DateUtils.getAfterDayDate(selectEndDate,-Integer.parseInt(timeBack)*3);
					}
				} else if ("day".equals(selectType)) {
					startDate = selectStartDate;
					endDate = selectEndDate;
					
					title=selectStartDate.substring(0, 4)+"年"+selectStartDate.substring(4,6)+"月"+selectStartDate.substring(6)+"日  至   "+selectEndDate.substring(0, 4)+"年"+selectEndDate.substring(4,6)+"月"+selectEndDate.substring(6)+"日";
					
					if(StringUtils.isNotBlank(timeBack))
					{
						startDate = DateUtils.getAfterDayDate(selectEndDate,-Integer.parseInt(timeBack));
					}
				}
			}
		}
		return new String[] { startDate, endDate,title  };
	}

	/**
	 * 通过前台时间控件获取时间 old
	 * */
	public static String[] initParame(String selectDate, String selectType) {
		String startDate = "";
		String endDate = "";
		if (StringUtils.isNotBlank(selectDate)
				&& StringUtils.isNotBlank(selectType)) {
			if ("year".equals(selectType)) {
				startDate = selectDate;
				endDate = selectDate;
			} else if ("month".equals(selectType)) {
				startDate = selectDate;
				endDate = selectDate;
			} else if ("week".equals(selectType)) {
				String start = selectDate.substring(0, 4);
				String end = selectDate.substring(4);
				if ("01".equals(end)) {
					startDate = start.concat("01");
					endDate = start.concat("03");
				} else if ("02".equals(end)) {
					startDate = start.concat("04");
					endDate = start.concat("06");
				} else if ("03".equals(end)) {
					startDate = start.concat("07");
					endDate = start.concat("09");
				} else if ("04".equals(end)) {
					startDate = start.concat("10");
					endDate = start.concat("12");
				}
			}
		} else {
			selectType = "month";
			startDate = DateUtils.getAfterMonthDate(DateUtils.convertToString(
					new Date(), "yyyyMM"), -1);
			endDate = DateUtils.getAfterMonthDate(DateUtils.convertToString(
					new Date(), "yyyyMM"), -1);
		}
		return new String[] { selectType, startDate, endDate };
	}

	/**
	 * 对sql条件中@符号进行替代
	 * 
	 * @param str
	 * @return
	 */
	public static String getFinalSql(String str) {
		
		//针对默认排序为，为空直接替换字段
		if(StringUtils.isNotBlank(str)){
			str = str.replaceAll("DATANULL", "").replaceAll("#06#", ",").replaceAll("\\(", " \\( ").replaceAll("\\)", " \\) ");
			
			//是否开启崇明的科室排序
			if(1==2){
			    if(str.indexOf("#07#")!=-1){
			    	str = str.replaceAll("#07#", "").replaceAll("yyksmc(\\s{1,})(as|AS|As|aS)(\\s{1,})(\\w{0,}),", "yyksmc$1$2$3$4,px,").replaceAll("b.yyksmc", "b.yyksmc,px");
			    }
			}
		}
		
		

		// 因为后面要用到空格分组获取条件,首先去掉等号左右的空格，这个不会影响sql语句的执行
		str = str.replaceAll("\\s{0,}=\\s{0,}", "=");
		str = str.replaceAll("%@\\s{0,}\\S{0,}\\s{0,}%", "%%");
		StringBuffer bufferSql = new StringBuffer(" ");
		// 按一个或多个空格分组
		String[] strs = str.split("\\s{1,}");
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf("@") > -1) {
				bufferSql.append(" 1=1 ");
			} else {
				bufferSql.append(strs[i] + " ");
			}
		}
		
		

		return bufferSql.toString().replaceAll("&", "'||chr(38)||'").replaceAll("union", " union ").replaceAll("where", " where ");
	}

	/**
	 * 对图形的title进行切换
	 * 
	 * @param str
	 * @return
	 */
	public static String getFinalTitle(String str) {

		str = str.replaceAll(" ", "");

		str = str.replaceAll("null", "");

		StringBuffer bufferSql = new StringBuffer();
		// 按一个或多个空格分组
		String[] strs = str.split("##");
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf("@") > -1) {

			} else if (strs[i].indexOf("#") > -1) {

			} else {
				bufferSql.append(strs[i]);
			}
		}

		return bufferSql.toString();
	}

	/**
	 * 生成唯一ID号
	 * 
	 * @return
	 */
	public static String getUUid() {
		String s = UUID.randomUUID().toString();
		String value = s.substring(0, 8) + s.substring(9, 13)
				+ s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
		return value;
	}

	public static void main(String[] args) {
		// System.out.println("路径："
		// + (util.getClass().getClassLoader()
		// .getResource("configure/mz/mz1.xml").getPath()));
		System.out.println(CommonUtil
				.HtmlEncode(" select * from hello where 1=1 and cs >= 5"));
		System.out.println(CommonUtil.HtmlDecode(CommonUtil
				.HtmlEncode(" select * from hello where 1=1 and cs >= 5")));
	}

	public static String HtmlEncode(String encodeString) {
		encodeString = encodeString.replace("<", "&lt;");
		encodeString = encodeString.replace(">", "&gt;");
		encodeString = encodeString.replace(" ", "&nbsp;");
		encodeString = encodeString.replace("’", "'");
		encodeString = encodeString.replace(String.valueOf(13), "<br>");
		return encodeString;
	}

	public static String HtmlDecode(String encodeString) {
		encodeString = encodeString.replace("&lt;", "<");
		encodeString = encodeString.replace("&gt;", ">");
		encodeString = encodeString.replace("&nbsp;", " ");
		encodeString = encodeString.replace("'", "’");
		encodeString = encodeString.replace("<br>", String.valueOf(13));
		return encodeString;
	}

	//生成随机码
	public static char getRandomChar() {
		Random random = new Random();
		if (random.nextInt(5) < 3) {
			return (char) (65 + random.nextInt(26));
		}
		return (char) (97 + random.nextInt(26));

	}
	
	//去除数组中重复的记录  
	public static String array_unique(String[] a) {  
	    // array_unique 
		String strUnique="";
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	        	strUnique+=a[i]+",";
	        	list.add(a[i]);  
	        }  
	    }
	    if(StringUtils.isNotBlank(strUnique)){
	    	strUnique=strUnique.substring(0,strUnique.length()-1);
	    }
	    return strUnique;  
	}  


}
