/**************************************************************************
 * Copyright (c) 2006-2010 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 * 
 * 项目名称： 义乌国际贸易
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package cn.scdd.jxc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 功能说明：应用中相关日期通用类 <br>
 * 特殊属性：
 * 
 * @version $Revision: 1.1 $ $Date: 2008/06/08 09:50:16 $
 * @author yuxh
 */
public abstract class DateUtil {

	/** 默认的时间格式化类型 */
	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/** 时间格式化类型 */
	protected static final  String DATE_PATTERN_DETAIL = "yyyy-MM-dd hh mm ss";

	/**
	 *
	 * 方法说明：字符类型装成指定格式的时间格式
	 *
	 * @param date date
	 * @param pattern pattern
	 * @return Date
	 */
	public static java.util.Date str2Date(String date, String pattern) {
		Date _d = null;
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			_d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
		}
		return _d;
	}

	/**
	 *
	 * 方法说明：字符类型装成指定格式的时间格式
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static java.util.Date strToDate(String date, String pattern) {
		Date _d = null;
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			_d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
		}
		return _d;
	}

	/**
	 *
	 * 方法说明：字符类型装成默认的时间格式
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static java.util.Date str2Date(String date) {
		return str2Date(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 *
	 * 方法说明：字符类型装成默认的时间格式
	 *
	 * @param date
	 * @param pattern
	 * @return Date
	 */
	public static java.util.Date strToDate(String date) {
		return strToDate(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 方法说明：字符类型转成timestamp的时间格式.
	 *
	 * @param date date
	 * @return Date
	 */
	public static java.util.Date str2DateDetail(String date) {
		return str2Date(date, DATE_PATTERN_DETAIL);
	}

	/**
	 * 时间转成字符串.
	 *
	 * @param date date
	 * @return String
	 */
	public static String date2Str(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat newstr = new SimpleDateFormat(pattern);
		return newstr.format(date);
	}

	/**
	 * 
	 * 时间转换成字符串-默认格式"yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		return date2Str(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 
	 * 方法说明：某个时间点添加几天后的时间
	 * 
	 * @param date
	 *            某个时间
	 * @param days
	 *            需要添加的时间
	 * @return
	 */
	public static java.util.Date addDays(Date date, int days) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法说明：给日期加上一天
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date addDay(Date date) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 
	 * 方法说明：某个时间点添加几月后的时间
	 * 
	 * @param date
	 *            某个时间
	 * @param months
	 *            需要添加月数
	 * @return
	 */
	public static java.util.Date addMonths(Date date, int months) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 
	 * 增加年份
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static java.util.Date addYears(Date date, int years) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		cal.add(java.util.Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 
	 * 取得当前日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 
	 * 取当前日期字符串"yyyy-MM-dd"
	 * 
	 * @return
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return newstr.format(getCurrentDate());
	}

	/**
	 * 
	 * 获取当前时间往前一个月的开始日期
	 * 
	 * @return
	 */
	public static String getOneMonthStartDate() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		return newstr.format(calendar.getTime()).toString();
	}

	/**
	 *
	 * 获取当前时间往前月的开始日期
	 *
	 * @param months int
	 * @return Date
	 */
	public static Date getOneMonthStartDate(int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -months);
		return calendar.getTime();
	}

	/**
	 * 
	 * 获取当前时间往前一个月的结束日期
	 * 
	 * @return
	 */
	public static String getOneMonthEndDate() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return newstr.format(new Date()).toString();
	}

	/**
	 *
	 * 比较两个时间大小，date1<=date2返回true，反之返回false
	 *
	 * @param date1 date1
	 * @param date2 date2
	 * @return boolean
	 */
	public static boolean compareDate(Date date1, Date date2) {
		long day1 = date1.getTime();
		long day2 = date2.getTime();
		return day1 <= day2;
	}

	public static Map<String, Integer> getDateFields(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		return map;
	}

	/**
	 * 
	 * 方法说明：将指定时间重置为当天凌晨时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date moveBeginOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();
	}

	/**
	 * 
	 * 获取当前时间一年的日期
	 * 
	 * @return
	 */
	public static Date getOneYearStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 0); // 得到前一个月
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 1);
		return calendar.getTime();
	}

	/**
	 * 
	 * 获取当月初时间
	 * 
	 * @return
	 */
	public static Date getCurrentMonthBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 
	 * 获取当周初时间
	 * 
	 * @return
	 */
	public static Date getCurrentWeekBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(java.util.Calendar.DAY_OF_WEEK, 1);
		return cal.getTime();
	}
	
	/**
	 * 
	 * 获取指定年份开始时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getYearBegin(String yearStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(sdf.parse(yearStr.toString()));
		return calendar.getTime();
	}

	public static int getYearFromDate(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 方法说明：根据年月获取初始日期
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static Date getDateFromYearMonth(String yearMonth) {
		DateFormat ym = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = ym.parse(yearMonth);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 方法说明：根据年月日获取日期
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static Date getDateFromYearMonthDay(String yearMonth) {
		DateFormat ym = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = ym.parse(yearMonth);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 
	 * 获取这个月的年份
	 * 
	 * @return
	 */
	public static int getThisMonthOfYear() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 
	 * 获取毫秒的数据
	 * 
	 * @return
	 */
	public static String getMillisecondStr() {
		String result = date2Str(getCurrentDate(), "yyMMddHHmmssSSS");
		return result;
	}
	
	/** 
     * 获取某年第一天日期 
     * @return Date 
     */  
    public static Date getYearFirst() {  
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
    
    /**
     * 判断当前日期是星期几
     * 
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar c = Calendar.getInstance();
		 c.setTime(format.parse(pTime));
		 int dayForWeek = 0;
		 if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			 dayForWeek = 7;
		 } else {
			 dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		 }
		 return dayForWeek;
	}
    
    /**
	 * 
	 * 方法说明：某个时间点添加几分钟后的时间
	 * 
	 * @param date 某个时间
	 * @param minutes 需要添加的分钟数
	 * @return Date
	 */
	public static java.util.Date addMinutes(Date date, int minutes) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
    /**
	 * 
	 * 方法说明：某个时间点添加几小时后的时间
	 * 
	 * @param date 某个时间
	 * @param hours 需要添加的小时数
	 * @return Date
	 */
	public static java.util.Date addHour(Date date, int hours) {
		java.util.Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}
}