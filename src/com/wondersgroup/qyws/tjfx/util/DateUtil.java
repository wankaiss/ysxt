package com.wondersgroup.qyws.tjfx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DateUtil {
	//系统默认的格式
	public static final String SYSTEM_DATE_FORMAT="yyyyMMdd";
	public static final String SYSTEM_WEEK_FORMAT="yyyyWW";
	public static final String SYSTEM_MOHTH_FORMAT="yyyyMM";
	public static final String SYSTEM_YEAR_FORMAT="yyyy";
	
	/**
	 * 根据起始时间,结束时间计算月份id ,List中包含的形如:201101,201102的字符串
	 * 注意,返回结果的格式 yyyyMM,而不是系统默认的yyyyMMdd
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param fmt			格式	
	 * @return
	 */
	public static List<String> getMonthDiffIds(String startDate,String endDate,String fmt){
		Date start=parseDate(startDate,fmt);
		Date end=parseDate(endDate,fmt);
		if(start.getTime()>end.getTime()){	//如果起始时间大于结束时间,则交换
			Date tmp=start;
			start=end;
			end=tmp;
		}
		Long monthDiff=dateDiff("mm",start,end);
		List<String> monthes=new ArrayList<String>(monthDiff.intValue()+1);
		int start_year=getFieldValue(start,Calendar.YEAR);
		int start_month=getFieldValue(start,Calendar.MONTH);
		for(int i=0;i<monthDiff+1;i++){
			if(start_month/13==1){
				start_year++;
				start_month=1;
			}
			monthes.add(new String(start_year+appendZero(start_month)));
			start_month+=1;
		}
		return monthes;
	}
	
	public static List<String> getMonthIds(String startDate,String endDate,String fmt){
		Date start=parseDate(startDate,fmt);
		Date end=parseDate(endDate,fmt);
		if(start.getTime()>end.getTime()){	//如果起始时间大于结束时间,则交换
			Date tmp=start;
			start=end;
			end=tmp;
		}
		Long monthDiff=dateDiff("mm",start,end);
		List<String> monthes=new ArrayList<String>(monthDiff.intValue()+1);
		int start_year=getFieldValue(start,Calendar.YEAR);
		int start_month=getFieldValue(start,Calendar.MONTH);
		for(int i=0;i<monthDiff+1;i++){
			if(start_month/13==1){
				start_year++;
				start_month=1;
			}
			monthes.add(new String(start_year+appendZero(start_month)));
			start_month+=1;
		}
		return monthes;
	}
	
	/**
	 * 根据起始时间,结束时间计算月份id ,List中包含的形如:201101,201102的字符串
	 * 注意,返回结果的格式 yyyyMM,而不是系统默认的yyyyMMdd
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param fmt			格式	
	 * @return
	 */
	public static List<String> getDayDiffIds(String startDate,String endDate,String fmt){
		Date start=parseDate(startDate,fmt);
		Date end=parseDate(endDate,fmt);
		if(start.getTime()>end.getTime()){	//如果起始时间大于结束时间,则交换
			Date tmp=start;
			start=end;
			end=tmp;
		}
		Long monthDiff=dateDiff("dd",start,end);
		List<String> days=new ArrayList<String>(monthDiff.intValue()+1);
		for(int i=0;i<monthDiff+1;i++){
			days.add(formatDate(addDay(start,i)));
		}
		return days;
	}
	
	public static List<String> getWeekDiffIds(String startDate,String endDate){
		/*int startDateYear = Integer.parseInt(startDate.substring(0,4));
		int endDateYear = Integer.parseInt(endDate.substring(0,4));
		
		int startDateWeek = Integer.parseInt(startDate.substring(4,6));
		int endDateWeek = Integer.parseInt(endDate.substring(4,6));
		
		int count = 0;
		if (startDateYear == endDateYear){
			count = endDateWeek-startDateWeek;
		}
		if (startDateYear < endDateYear){
			count = endDateWeek+52-startDateWeek;
		}
		
		List<String> weekList = new ArrayList<String>();
		int countTemp = 0;
		for (int i = 1; i < count+1; i++) {
			String weekString = "";
			if ((startDateWeek+i) < 53){
				if(startDateWeek < 10){
					weekString = startDateYear+"0"+(startDateWeek+i);
				} else {
					weekString = startDateYear+""+(startDateWeek+i);
				}
				
				countTemp = i;
			} else {				
				weekString = endDateYear+""; 
				int numberTemp = i-countTemp;
				if (numberTemp < 10){
					weekString += "0"+numberTemp;					
				} else {
					weekString += numberTemp;
				}
			}
			weekList.add(weekString);
		}		
		return weekList;*/	
		
		//修改,若有冲突,打开上面,注掉下面  +_+  -T
		int startDateYear = Integer.parseInt(startDate.substring(0,4));
		int endDateYear = Integer.parseInt(endDate.substring(0,4));
		int startDateWeek = Integer.parseInt(startDate.substring(4,6));//08
		int endDateWeek = Integer.parseInt(endDate.substring(4,6));//13
		int loopTimes=0;
		if(startDateYear==endDateYear)
			loopTimes=endDateWeek-startDateWeek;
		else
			loopTimes=endDateWeek+52-startDateWeek;
		List<String> weekList = new ArrayList<String>();
		for(int i=0;i<=loopTimes;i++){
			String weekString = "";
			if(startDateYear==endDateYear)
				weekString=startDateYear+""+(startDateWeek+i<10?"0"+(startDateWeek+i):(startDateWeek+i));
			else{
				if(startDateWeek+i>52)
					weekString=(startDateYear+1)+""+(startDateWeek+i-52<10?"0"+(startDateWeek+i-52):(startDateWeek+i-52));
				else
					weekString=startDateYear+""+(startDateWeek+i<10?"0"+(startDateWeek+i):(startDateWeek+i));
			}
			weekList.add(weekString);
		}
		return weekList;
	}
	
	
	public static List<String> getMonthDiffIds(String startDate,String endDate){
		return getMonthDiffIds(startDate,endDate,SYSTEM_DATE_FORMAT);
	}
	
	public static List<String> getMonthList(String startDate,String endDate){
		return getMonthIds(startDate,endDate,SYSTEM_MOHTH_FORMAT);
	}
	
    /**
     * 取得两个日期之间的差值
     * @param String 差值的类型
     * @param date 第一个日期
     * @param date 第二个日期
     * @return long 两个日期之间的差值，类型由第一个String参数指定
     */
    public static long dateDiff(String style, Date fromdate, Date todate)
    {
        byte byte0;
        byte byte1 = 0;
        int i = 1;
        Date date2;
        Date date3;
        if(fromdate.getTime() > todate.getTime()) {
            i = -1;
            date2 = todate;
            date3 = fromdate;
        } else {
            date2 = fromdate;
            date3 = todate;
        }
        if(style.equals("yyyy"))
            byte0 = 1;
        else if(style.toLowerCase().equals("mm"))
            byte0 = 2;
        else if(style.equals("dd")){
        	date2 = new Date((date2.getTime()/86400000)*86400000);
        	date3 = new Date((date3.getTime()/86400000)*86400000);
            byte0 = 5;
        }
        else if(style.equals("y"))
            byte0 = 5;
        else if(style.equals("w"))
            byte0 = 4;
        else if(style.equals("ww"))
            byte0 = 3;
        else if(style.equals("h")) {
            byte0 = 5;
            byte1 = 11;
        } else if(style.equals("n")) {
            byte0 = 5;
            byte1 = 12;
        } else if(style.equals("s")) {
            byte0 = 5;
            byte1 = 13;
        } else {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        long l = 0;
        calendar.add(byte0, 1);
        for (Date date4 = calendar.getTime(); date4.getTime() <= date3.getTime();) {
            calendar.add(byte0, 1);
            date4 = calendar.getTime();
            l++;
        }
        if(byte1 == 11 || byte1 == 12 || byte1 == 13) {
            calendar.setTime(date2);
            calendar.add(byte0, (int) l);
            Date date5 = calendar.getTime();
            switch (byte1) {
            case 11:
                l *= 24;
                break;
            case 12:
                l = l * 24 * 60;
                break;
            case 13:
                l = l * 24 * 60 * 60;
                break;
            }
            calendar.add(byte1, 1);
            for (Date date6 = calendar.getTime(); date6.getTime() <= date3.getTime();) {
                calendar.add(byte1, 1);
                date6 = calendar.getTime();
                l++;
            }
        }
        return l * i;
    }
	
    
	/**
	 * 根据指定格式增加月份
	 * @param date		日期字符串
	 * @param fmt		格式
	 * @param amount	增加的数量
	 * @return
	 */
	public static String addMonth(String date,String fmt,int amount){
		Date d=parseDate(date,fmt);	//
		d=addMonth(d,amount);
		return formatDate(d,fmt);
	}
	
	public static String addMonth(String date,int amount){
		return addMonth(date,SYSTEM_DATE_FORMAT,amount);
	}

	
	public static Date addMonth(Date date,int amount){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}
	
	public static Date addDay(Date date,int amount){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}
	/**
	 * 根据指定格式增加日数
	 * @param date		日期字符串
	 * @param fmt		格式
	 * @param amount	增加的数量
	 * @return
	 */
	public static String addDate(String date,String fmt,int amount){
		Date d=parseDate(date,fmt);	//
		d=addDate(d,amount);
		return formatDate(d,fmt);
	}
	
	public static String addDate(String date,int amount){
		return addDate(date,SYSTEM_DATE_FORMAT,amount);
	}
	public static String addMenthDate(String date,int amount){
		return formatDate(addMenth(parseDate(date,SYSTEM_MOHTH_FORMAT),amount),SYSTEM_MOHTH_FORMAT);
	}
	
	public static String addYearDate(String date,int amount){
		return formatDate(addYear(parseDate(date,SYSTEM_YEAR_FORMAT),amount),SYSTEM_YEAR_FORMAT);
	}
	
	public static String addWeek(String week,int count){
		if (week != null && !week.equals("")){
			/*int weekYear =  Integer.parseInt(week.substring(0,4));
			int weekInt =  Integer.parseInt(week.substring(4,6));
			if (weekInt > count){
				int weekTemp = weekInt-count;
				if (weekTemp < 10){
					return weekYear+"0"+weekTemp;
				} else {
					return weekYear+""+weekTemp;
				}
			} else {
				weekYear -=1;
				int weekTemp = weekInt+52-count;
				return weekYear+""+weekTemp;
			}*/
			//修改,若有冲突,打开上面,注掉下面  +_+  -T
			int weekYear =  Integer.parseInt(week.substring(0,4));
			int weekInt =  Integer.parseInt(week.substring(4,6));
			if(weekInt>count)
				return weekInt-count<10?(weekYear+"0"+(weekInt-count)):(weekYear+""+(weekInt-count));
			else
				return weekInt-count<10?((weekYear-1)+"0"+(weekInt-count)):((weekYear-1)+""+(weekInt-count));
		} else {
			return week;
		}
	}

	
	public static Date addDate(Date date,int amount){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, amount);
		return c.getTime();
	}
	public static Date addMenth(Date date,int amount){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -amount);
		return c.getTime();
	}
	
	public static Date addYear(Date date,int amount){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, -amount);
		return c.getTime();
	}
	
	
	/**
	 * 根据指定格式解析日期
	 * @param date	日期字符串
	 * @param fmt	格式
	 * @return
	 */
	public static Date parseDate(String date,String fmt){
		try {
			Date d=new SimpleDateFormat(fmt).parse(date);
			Calendar c=Calendar.getInstance();
			c.setTime(d);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseDate(String date){
		return parseDate(date,SYSTEM_DATE_FORMAT);
	}
	
	/**
	 * 根据指定格式格式化日期
	 * @param date	日期值
	 * @param fmt	格式
	 * @return
	 */
	public static String formatDate(Date date,String fmt){
		return new SimpleDateFormat(fmt).format(date);
	}
	
	public static String formatDate(Date date){
		return formatDate(date,SYSTEM_DATE_FORMAT);
	}
	
	/**
	 * 日期格式转换
	 * @param date			字符串日期
	 * @param fromFormat	源日期格式
	 * @param toFormat		目标日期格式
	 * @return
	 */
	public static String convertFormat(String date,String fromFormat,String toFormat){
		return formatDate(parseDate(date,fromFormat), toFormat);
	}
	
	/**
	 * 根据日期相应part的值.
	 * @param date
	 * @param fmt
	 * @param field
	 * @return
	 */
	public static Integer getFieldValue(String date,String fmt,int field){
		return getFieldValue(parseDate(date,fmt),field);
	}
	
	public static Integer getFieldValue(String date,int field){
		return getFieldValue(date,SYSTEM_DATE_FORMAT,field);
	}
	
	/**
	 * 取得日期指定域的值
	 * @param date		
	 * @param field		域 Calendar.Month
	 * @return
	 */
	public static Integer getFieldValue(Date date,int field){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		if(field==Calendar.MONTH){
			return c.get(field)+1;
		}
		return c.get(field);
	}
	
	public static String appendZero(int amount){
		if(amount<10){
			return "0"+amount;
		}
		return ""+amount;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Date date1=parseDate("20100101","yyyyMMdd");
//		Date date2=parseDate("20111231","yyyyMMdd");
//		log.debug(dateDiff("mm",date1,date2));
		Logger log=Logger.getLogger(DateUtil.class);

		List<String> ids=getDayDiffIds("20120201","20120209","yyyyMMdd");
		for(String id:ids){
			log.debug(id+" ");
		}
//		log.debug(addMonth("20100101","yyyyMMdd",-1));
//		log.debug(getFieldValue("20100101","yyyyMMdd",Calendar.MONTH));
		//log.debug(1.0019*100-100);
	}
	
	/**
	 * 计算两日期间的天数
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param tabType 传入的日期类型(天-dw，周-ww，月-mw)
	 * @return
	 */
	public static int getMidFromDoubleDate(String startDate,String endDate,String tabType){
		Date date1=null;
		Date date2=null;
		if("ww".equals(tabType)){
			date1=parseDate(startDate,SYSTEM_WEEK_FORMAT);
			date2=parseDate(endDate,SYSTEM_WEEK_FORMAT);
		}else if("mw".equals(tabType)){
			date1=parseDate(startDate,SYSTEM_MOHTH_FORMAT);
			date2=parseDate(endDate,SYSTEM_MOHTH_FORMAT);
		}else{
			date1=parseDate(startDate);
			date2=parseDate(endDate);
		}
		return (int)(Math.abs((date1.getTime()-date2.getTime())/(1000*60*60*24))+1);
	}

}
