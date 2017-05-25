package com.haoxw.terminal.business.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * 
 * @author xuewuhao
 * 
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final String DEFAULT_PATTERN = "yyyyMMddHHmmss";

	/**
	 * 获取相隔interval天的时间 并返回pattern类型的string
	 * 
	 * @param interval
	 * @param starttime
	 * @param pattern
	 * @return
	 */
	public static String getDate(String interval, Date starttime, String pattern) {
		Calendar temp = Calendar.getInstance(TimeZone.getDefault());
		temp.setTime(starttime);
		temp.add(temp.DATE, Integer.parseInt(interval));
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(temp.getTime());
	}

	/**
	 * 将字符串类型转换为时间类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			d = sdf.parse(str);
		} catch (Exception e) {
			logger.error("",e);
		}
		return d;
	}

	/**
	 * 将字符串按照pattern类型转换为时间类型
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String str, String pattern) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(str);
		} catch (Exception e) {
			logger.error("",e);
		}
		return d;
	}

	/**
	 * 将时间格式化 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static Date DatePattern(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			String dd = sdf.format(date);
			date = str2Date(dd);
		} catch (Exception e) {
			logger.error("",e);
		}
		return date;
	}

	/**
	 * 将long转成时间格式
	 * 
	 * @param datelong
	 * @param format
	 * @return
	 */
	public static String long2date(long datelong, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		date.setTime(datelong);
		return sdf.format(date);
	}

	/**
	 * 将时间格式化
	 */
	public static Date DatePattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			String dd = sdf.format(date);
			date = str2Date(dd, pattern);
		} catch (Exception e) {
			logger.error("",e);
		}
		return date;
	}

	/**
	 * 将date转换成默认时间格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		return sdf.format(date);
	}

	/**
	 * 将date转换成format格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取昨天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastDate(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 获取昨天
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String getLastDate(Date date, String pattern) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		return date2Str(calendar.getTime(), pattern);
	}

	/**
	 * 获取上周第一天（周一）
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekStart(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		int i = calendar.get(calendar.DAY_OF_WEEK) - 1;
		int startnum = 0;
		if (i == 0) {
			startnum = 7 + 6;
		} else {
			startnum = 7 + i - 1;
		}
		calendar.add(calendar.DATE, -startnum);

		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 返回指定月的最后一天 比如 201002 获取2月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.MONTH, month);//

		calendar.set(Calendar.DATE, 1);

		calendar.add(Calendar.DATE, -1);

		int end = calendar.get(Calendar.DATE);
		return end;
	}

	/**
	 * 获取上周最后一天（周末）
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekEnd(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		int i = calendar.get(calendar.DAY_OF_WEEK) - 1;
		int endnum = 0;
		if (i == 0) {
			endnum = 7;
		} else {
			endnum = i;
		}
		calendar.add(calendar.DATE, -(endnum - 1));

		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 改更现在时间
	 */
	public static Date changeDate(String type, int value) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		if (type.equals("month")) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
		} else if (type.equals("date")) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
		}
		return calendar.getTime();
	}

	/**
	 * 更改时间
	 */
	public static Date changeDate(Date date, String type, int value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (type.equals("month")) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
		} else if (type.equals("date")) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
		} else if (type.endsWith("year")) {
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);
		}
		return calendar.getTime();
	}

	/**
	 * 比较时间是否在这两个时间点之间
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean checkTime(String time1, String time2) {
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();
		Date date11 = DateUtil.str2Date(DateUtil.date2Str(date1, "yyyy-MM-dd")
				+ " " + time1);// 起始时间

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date date2 = c.getTime();
		Date date22 = DateUtil.str2Date(DateUtil.date2Str(date2, "yyyy-MM-dd")
				+ " " + time2);// 终止时间

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date11);// 起始时间

		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(date22);// 终止时间

		Calendar calendarnow = Calendar.getInstance();

		if (calendarnow.after(scalendar) && calendarnow.before(ecalendar)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 检查输入日期是否是interval月之内的日期
	 * 
	 * @param time
	 * @param interval
	 * @return
	 */
	public static boolean checkOnly6Month(String time, int interval) {
		boolean t = true;
		Calendar calendarnow = Calendar.getInstance();
		Date datetmp = DateUtil.str2Date(time + " 00:00:01");
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(datetmp);// 要判断的时间点
		calendarnow.add(Calendar.MONTH, interval); // 将当前日期前翻interval个月
		calendarnow.set(Calendar.DAY_OF_MONTH, 1);// 将当前日期前翻interval个月之后
		// 将日期翻到该月第一天
		calendarnow.set(Calendar.HOUR_OF_DAY, 0);
		calendarnow.set(Calendar.MINUTE, 0);
		calendarnow.set(Calendar.SECOND, 0);

		if (!scalendar.after(calendarnow)) {
			t = false;
		}
		return t;
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstString
	 * @param secondString
	 * @return
	 */
	public static int nDaysBetweenTwoDate(String starttime, String endtime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(starttime);
			secondDate = df.parse(endtime);
		} catch (Exception e) {
			logger.error("",e);
			// 日期型字符串格式错误
		}
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	/**
	 * 获取当月第一天和下月第一天
	 * 
	 * @return
	 */
	public static List<Date> getFirstAndLastDayOfMonth() {
		List<Date> listDate = new ArrayList<Date>();
		// 获取当前日期 并设置0时0分0秒
		Calendar cal_now = Calendar.getInstance();
		cal_now.set(Calendar.HOUR_OF_DAY, 0);
		cal_now.set(Calendar.MINUTE, 0);
		cal_now.set(Calendar.SECOND, 0);
		// 设置为1号,当前日期既为本月第一天
		cal_now.set(Calendar.DAY_OF_MONTH, 1);
		listDate.add(cal_now.getTime());
		// 设置为下月1号
		cal_now.add(Calendar.MONTH, 1);
		listDate.add(cal_now.getTime());
		return listDate;
	}

	/**
	 * 获取今天和昨天开始时间
	 * 
	 * @return
	 */
	public static List<Date> getLastDayAndToday() {
		List<Date> listDate = new ArrayList<Date>();
		// 获取当前日期 并设置0时0分0秒
		Calendar cal_now = Calendar.getInstance();
		cal_now.set(Calendar.HOUR_OF_DAY, 0);
		cal_now.set(Calendar.MINUTE, 0);
		cal_now.set(Calendar.SECOND, 0);
		listDate.add(cal_now.getTime());
		// 设置到昨天
		cal_now.add(Calendar.DATE, -1);
		listDate.add(cal_now.getTime());
		return listDate;
	}

	/**
	 * 获取今天和明天开始时间
	 * 
	 * @return
	 */
	public static List<Date> getCurDayAndTomorrowDay() {
		List<Date> listDate = new ArrayList<Date>();
		// 获取当前日期 并设置0时0分0秒
		Calendar cal_now = Calendar.getInstance();
		cal_now.set(Calendar.HOUR_OF_DAY, 0);
		cal_now.set(Calendar.MINUTE, 0);
		cal_now.set(Calendar.SECOND, 0);
		listDate.add(cal_now.getTime());
		// 设置第二天
		cal_now.add(Calendar.DAY_OF_MONTH, 1);
		listDate.add(cal_now.getTime());
		return listDate;
	}

	/**
	 * 获取当前时间小时和下个小时
	 * 
	 * @return
	 */
	public static List<Date> getCurHourAndNextHour() {
		List<Date> listDate = new ArrayList<Date>();
		// 获取当前小时并设置0分0秒
		Calendar cal_now = Calendar.getInstance();
		cal_now.set(Calendar.MINUTE, 0);
		cal_now.set(Calendar.SECOND, 0);
		listDate.add(cal_now.getTime());
		// 设置下个小时
		cal_now.add(Calendar.HOUR_OF_DAY, 1);
		listDate.add(cal_now.getTime());
		return listDate;
	}

	/**
	 * 获取当前时间距离结束时间毫秒数
	 * 
	 * @param toDate
	 * @return
	 */
	public static long getNowInteval(Date toDate) {
		return toDate.getTime() - System.currentTimeMillis();
	}
	/**
	 * 获取上个月所有日期  几月几号
	 * @return
	 */
	public static List<String> getLastMonthDays() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		int month = calendar.get(Calendar.MONTH);
		int maxDate = calendar.getActualMaximum(Calendar.DATE);
		for (int i = 1; i <= maxDate; i++) {
			list.add((month + 1) + "月" + i + "号");
		}
		return list;
	}
 
	public static void main(String[] args) {
		Date d = new Date();
		DateUtil dateUtil = new DateUtil();

		List<Date> listDate = dateUtil.getFirstAndLastDayOfMonth();
		for (Date date1 : listDate)
			System.out.println(dateUtil.date2Str(date1));

		List<Date> listDate2 = dateUtil.getCurDayAndTomorrowDay();
		for (Date date2 : listDate2)
			System.out.println(dateUtil.date2Str(date2));

		List<Date> listDate3 = dateUtil.getCurHourAndNextHour();
		for (Date date3 : listDate3)
			System.out.println(dateUtil.date2Str(date3));
		
		dateUtil.getLastMonthDays();
		
	}
	
	
	 //判断闰年  
	public static boolean isLeap(int year)  
    {  
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))  
            return true;  
        else  
            return false;  
    }  
    
    
    //返回当月天数  
    public static int getDays(int year, int month)  
    {  
        int days;  
        int FebDay = 28;  
        if (isLeap(year))  
            FebDay = 29;  
        switch (month)  
        {  
            case 1:  
            case 3:  
            case 5:  
            case 7:  
            case 8:  
            case 10:  
            case 12:  
                days = 31;  
                break;  
            case 4:  
            case 6:  
            case 9:  
            case 11:  
                days = 30;  
                break;  
            case 2:  
                days = FebDay;  
                break;  
            default:  
                days = 0;  
                break;  
        }  
        return days;  
    }  
    
    
    public static String getBeforeMonth(Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, 0);
    	Date beforeMonth = cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    	String str = sdf.format(beforeMonth);
    	return str;
    }
    

}
