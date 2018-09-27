/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.date 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 上午11:03:56
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.date;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/** 
 * @ClassName: DateUtil 
 * @Description: 日期工具类
 * @author: imart·deng
 * @date: 2018年9月18日 上午11:03:56  
 */
public class DateUtil {
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || "".equals(pattern) || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Calendar cal) {
		return format(cal,null);
	}
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String formatForMonth(int amount,String... arg) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, amount);
		String pattern = null;
		if(arg.length>0){
			pattern = arg[0].toString();
		}
		return format(cal,pattern);
	}
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Calendar cal, String pattern) {
		if (cal == null) {
			return "null";
		}
		if (pattern == null || "".equals(pattern) || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(cal.getTime());
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || "".equals(pattern) || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
			try {
				d = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (Exception e) {
			}
		}
		return d;
	}
	/**
	 * 获取指定月份工作日
	 * @param year 年份
	 * @param month 月份
	 * @return list<Date> 工作日数组
	 */
	public static List<Date> getDates(int year,int month){    
	        List<Date> dates = new ArrayList<Date>();    
	            
	        Calendar cal = Calendar.getInstance();    
	        cal.set(Calendar.YEAR, year);    
	        cal.set(Calendar.MONTH,  month - 1);    
	        cal.set(Calendar.DATE, 1);    
	        while(cal.get(Calendar.YEAR) == year &&     
	                cal.get(Calendar.MONTH) < month){    
	            int day = cal.get(Calendar.DAY_OF_WEEK);    
	                
	            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){    
	                dates.add((Date)cal.getTime().clone());    
	            }    
	            cal.add(Calendar.DATE, 1);    
	        }    
	        return dates;    
	    
	    } 

	/**
	 * 获取date开始结束日期，如果date==null ，那么返回的今天的开始和结束时间
	 * @param date 需要获取的开始和结束时间的日期
	 * @return Date[]数组，长度为2:
	 * <br/>date[0]  为今天00:00:00:000，
	 * <br/>date[1]  为今天今天23:59:59:999
	 */
	public static Date[] getBeginAndEnd(Date date) {
		Date[] dates = new Date[2];
		Calendar cal = Calendar.getInstance();
		if(null != date) {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		dates[0] = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		dates[1] = cal.getTime();
		return dates;
	}
	
	/**
	 * 获取今天开始时间
	 * @return Date 类型，今天00:00:00:000
	 */
	public static Date getTodayBegin() {
		return getBeginAndEnd(null)[0];
	}
	
	/**
	 * 获取今天结束时间
	 * @return Date 类型，今天23:59:59:999
	 */
	public static Date getTodayEnd() {
		return getBeginAndEnd(null)[1];
	}
	
	/**
	 * 获取今天开始结束日期
	 * @return Date[]数组，长度为2:
	 * <br/>date[0]  为今天00:00:00:000，
	 * <br/>date[1]  为今天今天23:59:59:999
	 */
	public static Date[] getTodayBeginAndEnd() {
		return getBeginAndEnd(null);
	}

	/**
	 * 获取当前时间字符串类型
	 * @return
	 */
	public static String getNowStrFormat() {
		return getNowStrFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取 date 当前时间字符串表达式
	 * @param date
	 * @param pattern
	 * @return 返回时间字符串表达式，例如：2017.08.31 16:04:32
	 */
	private static String getNowStrFormat(Date date, String pattern) {
		return format(date, pattern);
	}
	
	/**
	 * 判断date1 是否在date2之后
	 * @param date1
	 * @param date2
	 * @return boolean true 为date1在date2之后
	 */
	public static boolean ifAfter(Date date1, Date date2) {
		if(null != date1 && null != date2) {
			return date1.after(date2);
		}
		return false;
	}

	public static boolean ifSameDay(Date begin, Date end) {
		if(null!=begin && null!=end) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			cal.setTime(begin);
			cal2.setTime(end);
			int byear = cal.get(Calendar.YEAR);
			int eyear = cal.get(Calendar.YEAR);
			
			if(byear == eyear) {
				return cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
			}else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean ifSameDayIgnoreYear(Date begin, Date end) {
		if(null!=begin && null!=end) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			cal.setTime(begin);
			cal2.setTime(end);
			int bmonth = cal.get(Calendar.MONTH);
			int bday = cal.get(Calendar.DAY_OF_MONTH);
			int emonth = cal.get(Calendar.MONTH);
			int eday = cal.get(Calendar.DAY_OF_MONTH);
			return (bmonth==emonth && bday==eday);
		}
		return false;
	}
	
	public static void main(String[] args) {
//		Date day1 = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(day1);
//		System.out.println(cal.get(Calendar.DAY_OF_YEAR));
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		System.out.println(cal.get(Calendar.DAY_OF_YEAR));
//		
//		
//		long current = new Date().getTime();
//		cal.setTimeInMillis(current+2678400000l);
//		System.out.println(format(cal));
//		
////		cal.set(Calendar.DAY_OF_MONTH, 31);
//		cal.add(Calendar.DATE, 31);
//		System.out.println(format(cal));
//		long cha = cal.getTimeInMillis() - current;
//		System.out.println(cha);
//		System.out.println(cha/(1000*60*60*24));
//		
//		cal.setTime(new Date());
//		cal.add(Calendar.HOUR, 4);
//		cal.add(Calendar.MINUTE, 46);
//		System.out.println(betweenHours(new Date(), cal.getTime()));;
		
		
//		Date date1 = DateUtil.format("2017-10-01 09:00:00");
//		Date date2 = DateUtil.format("2017-10-01 23:30:00");
////		current += 1000 * 60 * 60 * 24 *14;
////		cal.setTimeInMillis(current);
////		System.out.println(format(cal));
//		float time = betweenHours(date1, date2, 0.5f);
//		
//		System.out.println(time);
//		
//		
//		Date today = new Date();
//		System.out.println(today);
//		
//		System.out.println(format(today));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		System.out.println(getDateTimeStr(cal.getTime()));
	}
	
	public static float betweenHours(Date startTime, Date endTime) {
		return betweenHours(startTime, endTime, null);
	}

	public static float betweenHours(Date startTime, Date endTime, Float limit) {
		if(null != startTime && null!=endTime) {
			BigDecimal cha = new BigDecimal((endTime.getTime() - startTime.getTime()));
			float value = cha.divide(new BigDecimal(1000*60*60),2,BigDecimal.ROUND_HALF_UP).floatValue();
			if(null != limit) {
				int i =  (int)value;
				float f = value - i;
				value = (float)i;
				if(f>=limit) {
					value += limit;
				}
			}
			return value;
		}
		return 0;
	}
	
	
	
	/**
	 * 获取时间的中文字符串
	 * @param date
	 * @return
	 */
	public static String getDateTimeStr(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return getYearStr(String.valueOf(year))+"年"+getMonthAndDateStr(month)+"月"+getMonthAndDateStr(day)+"日";
	}
	
	private static String[] dateAry = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
	
	/**
	 * 获取年的中文字符串
	 * @param year
	 * @return
	 */
	private static String getYearStr(String year){
		String yearStr = "";
		for(int i=0; i<year.length(); i++){
			yearStr += dateAry[Integer.valueOf(year.substring(i, i+1))];
		}
		return yearStr;
	}
	
	/**
	 * 获取月日的中文字符串
	 * @param time
	 * @return
	 */
	private static String getMonthAndDateStr(int time){
		String timeStr = "";
		if(time >= 10 && time < 20){
			timeStr += "十";
		}else if(time >= 20 && time < 30){
			timeStr += "二十";
		}else if(time >= 30){
			timeStr += "三十";
		}
		String timeString = String.valueOf(time);
		String singleDigits = timeString.substring(timeString.length()-1, timeString.length());
		if(!singleDigits.equals("0")){
			timeStr += dateAry[Integer.valueOf(singleDigits)];
		}
		
		return timeStr;
	}
	
}
