/**
 */
package com.ying.tangshi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils { 
	 
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
 
    /**
     * 得到两日期的偏移量(天数)
     */
    public static int getDateBias(Date smdate, Date bdate){
        long between_days = 0L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * @desc 得到Date偏移后的日期
     */
    public static Date getBiasDate(Date date,int bias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE,bias);
        return calendar.getTime();
    }

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = format(date, pattern[0].toString());
		} else {
			formatDate = format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}
	public static String getTime(String str) {
		return formatDate(new Date(),str);
	}
	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

    public static String getHour() {
		return formatDate(new Date(), "H");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取当前月份的第一天和最后一天
	 * @param monthValue 0 表示当前月份 -1 表示上月份 1 表示下月份
	 * @return
	 */
	public static String[] getMonthStartEnd(int monthValue){
	    Calendar startCalendar = Calendar.getInstance();//获取当前日期
	    startCalendar.add(Calendar.MONTH, monthValue);
	    startCalendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = formatDate(startCalendar.getTime(),parsePatterns[0]);
        
        Calendar endCalendar = Calendar.getInstance();//获取当前日期
        endCalendar.add(Calendar.MONTH, monthValue+1);
        endCalendar.set(Calendar.DAY_OF_MONTH,0);
        String endDay = formatDate(endCalendar.getTime(),parsePatterns[0]);
        
        String[] result = {firstDay,endDay};
        return result;
    }
    

    
    /*
     * 获取时间间隔（天）
     */
    public static String getTimeIntervalByDay(String start_time, String end_time) throws Exception
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTimeDate = formatter.parse(end_time);
        Date starttimeDate = formatter.parse(start_time);
        long diff = endTimeDate.getTime() - starttimeDate.getTime();
        long diff_day = diff / (1000 * 60 * 60 * 24);
        return String.valueOf(diff_day);
    }
    
    /*
     * 获取时间间隔（秒）
     */
    public static String getTimeIntervalBySecond(String start_time, String end_time) throws Exception
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTimeDate = formatter.parse(end_time);
        Date starttimeDate = formatter.parse(start_time);
        long diff = endTimeDate.getTime() - starttimeDate.getTime();
        long diff_day = diff / (1000);
        return String.valueOf(diff_day);
    }
    
    /*
     * 获取系统当前时间
     */
    public static String getCurrentTime(String dateFormat)
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /*
     * 两个时间之间差的月份
     */
    public static int calculateMonthIn(String date1, String date2) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starttimeDate = formatter.parse(date1);
        Date endTimeDate = formatter.parse(date2);
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(starttimeDate);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(endTimeDate);
        int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        return c;
    }
    
    /*
     * 获取系统当前时间
     */
    public static String getCurrentTime()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /*
     * 获取系统当前日期
     */
    public static String getCurrentDate()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /*
     * 获取系统当前年月，如201612
     */
    public static String getCurrentYearAndMonth(int months)
    {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(2) + 1 + months;
        int year = cal.get(1);
        String timeStamp = year + (String.valueOf(month).length() == 1 ? "0" + String.valueOf(month) : String.valueOf(month));
        return timeStamp;
    }
    
    /*
     * 获取系统当前时间的后n个月时间
     */
    public static String getCurrentTime4NextNMonth(int amount)
    {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, amount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向后增加n个月时间，向后增加1天
     */
    public static String getCurrentDate4NextNMonth(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向后增加n个月
        ca.add(Calendar.MONTH, amount);
        // 向后增加1天
        ca.add(Calendar.DATE, 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向后增加n天（yyyy-MM-dd）
     */
    public static String getCurrentDate4NextNDay(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向后增加n天
        ca.add(Calendar.DATE, amount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向后增加n天（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentTime4NextNDay(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向后增加n天
        ca.add(Calendar.DATE, amount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n天（yyyy-MM-dd）
     */
    public static String getCurrentDate4PastNDay(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n天
        int pastamount = -amount;
        ca.add(Calendar.DATE, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n天（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentTime4PastNDay(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n天
        int pastamount = -amount;
        ca.add(Calendar.DATE, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n月（yyyy-MM-dd）
     */
    public static String getCurrentDate4PastNMonth(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n月
        int pastamount = -amount;
        ca.add(Calendar.MONTH, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n月（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentTime4PastNMonth(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n月
        int pastamount = -amount;
        ca.add(Calendar.MONTH, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n年（yyyy-MM-dd）
     */
    public static String getCurrentDate4PastNYear(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n年
        int pastamount = -amount;
        ca.add(Calendar.YEAR, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 系统当前时间向前追溯n年（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentTime4PastNYear(int amount)
    {
        Calendar ca = Calendar.getInstance();
        // 向前追溯n年
        int pastamount = -amount;
        ca.add(Calendar.YEAR, pastamount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date de = ca.getTime();
        String dateString = formatter.format(de);
        return dateString;
    }
    
    /*
     * 获取当前时间的年月，如20151105
     */
    public static String getCurrentTimeForYearMonth()
    {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddhhmm");
        String curMonth = dateformat.format(new Date());
        return curMonth.substring(0, 6);
    }
    
    /*
     * 查询周期，如2015年10月1日至2015年10月31日
     */
    public static String getQueryCycleTimeStr(String month)
    {
        String curMonth = getCurrentTimeForYearMonth();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat firstDayformat = new SimpleDateFormat("yyyy-MM-01");
        String mm = month.substring(month.length() - 2, month.length());
        String year = month.substring(0, month.length() - 2);
        String str = "";
        if (curMonth.equals(month))
        {
            str = firstDayformat.format(new Date()) + " 至 " + dateformat.format(new Date());
        }
        else
        {
            int last_day = lastDay(year, mm);
            String lastday = lastDay(year, mm) < 10 ? "0" + last_day : String.valueOf(last_day);
            str = year + "-" + mm + "-01 至 " + year + "-" + mm + "-" + lastday;
        }
        return str;
    }
    
    /**
     * 获取任意时间的后任一天
     * getAnyTime4NextDay
     * 
     * @param DayNum
     * @return
     * @return String返回说明
     * @Exception 异常说明
     * @author：zhangqd3@asiainfo.com
     * @create：2015年11月26日 下午1:34:48
     */
    @SuppressWarnings("static-access")
    public static String getAnyTime4NextDay(String date, int DayNum)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date Date = formatter.parse(date);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(Date);
            calendar.add(calendar.DATE, DayNum);// 把日期往后增加一天.整数往后推,负数往前移动
            Date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            return formatter.format(Date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /*
     * 查询周期，如2015-10-1至2015-10-31
     */
    public static String getQueryCycleDateStr(String month)
    {
        String curMonth = getCurrentTimeForYearMonth();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat firstDayformat = new SimpleDateFormat("yyyy-MM-01");
        String mm = month.substring(month.length() - 2, month.length());
        String year = month.substring(0, month.length() - 2);
        String str = "";
        if (curMonth.equals(month))
        {
            str = firstDayformat.format(new Date()) + " 至 " + dateformat.format(new Date());
        }
        else
        {
            int last_day = lastDay(year, mm);
            String lastday = lastDay(year, mm) < 10 ? "0" + last_day : String.valueOf(last_day);
            str = year + "-" + mm + "-01 至 " + year + "-" + mm + "-" + lastday;
        }
        return str;
    }
    
    public static int lastDay(String year, String month)
    {
        int mm = Integer.parseInt(month);
        int yy = Integer.parseInt(year);
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(yy, mm - 1, day);
        int last = cal.getActualMaximum(Calendar.DATE);
        return last;
    }
    
    /*
     * 0 ：source和traget时间相同； 1 ：source比traget时间大； -1：source比traget时间小
     */
    public static int dataCompare(String source, String traget) throws Exception
    {
        return dataCompare(source, traget, "yyyy-MM-dd HH:mm:ss");
    }
    
    /*
     * 0 ：source和traget时间相同； 1 ：source比traget时间大； -1：source比traget时间小
     */
    public static int dataCompareTo(String source, String traget) throws Exception
    {
        return dataCompare(source, traget, "yyyy-MM-dd");
    }
    
    /*
     * 比较传入时间 0 ：source和traget时间相同 1 ：source比traget时间大 -1：source比traget时间小
     */
    public static int dataCompare(String source, String traget, String type) throws Exception
    {
        int send = 2;
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date sourcedate = format.parse(source);
        Date tragetdate = format.parse(traget);
        send = sourcedate.compareTo(tragetdate);
        
        return send;
    }
    
    /*
     * 获取当月最后一天yyyy-MM-dd 23:59:59
     */
    public static String getLastDayOfMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); // 设置日期为本月最大日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String lastDay = format.format(calendar.getTime());
        return lastDay;
    }
    
    /*
     * 获取当月第一天
     */
    public static String getFirstDayOfMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号，当前日期既为本月第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        String firstDay = format.format(calendar.getTime());
        return firstDay;
    }
    
    /*
     * 当月还剩余多少天
     */
    public static long getRemainDayOfMonth()
    {
        Calendar calendar = Calendar.getInstance();
        int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 获取本月最大天数
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 获取当前天数
        return last - day + 1;
    }
    
    /*
     * 获取当月过了多少天
     */
    public static long getHasPastDayOfMonth()
    {
        long past = 0; // 当月已经过去多少天
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号，当前日期既为本月第一天
            String timeOfMonth = (new SimpleDateFormat("yyyyMMdd000000")).format(calendar.getTime());
            long diff = (new Date()).getTime() - df.parse(timeOfMonth).getTime();
            past = diff / (1000 * 60 * 60 * 24) + 1;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return past;
    }
    
    /*
     * 下月第一天yyyy-MM-dd
     */
    public final static String getNextmonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = 1;
        int year = cal.get(Calendar.YEAR);
        String timeStamp = year + (String.valueOf(month).length() == 1 ? ("0" + String.valueOf(month)) : String.valueOf(month))
                + (String.valueOf(day).length() == 1 ? ("0" + String.valueOf(day)) : String.valueOf(day));
        return timeStamp;
    }
    
    /*
     * 下月第一天 yyyy-MM-dd HH:mm:ss
     */
    public final static String getNextmonthNew()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = 1;
        int year = cal.get(Calendar.YEAR);
        String timeStamp = year + "-" + (String.valueOf(month).length() == 1 ? ("0" + String.valueOf(month)) : String.valueOf(month)) + "-"
                + (String.valueOf(day).length() == 1 ? ("0" + String.valueOf(day)) : String.valueOf(day)) + " 00:00:00";
        return timeStamp;
    }
    
    /*
     * 把yyyy-MM-dd格式转换为yyyyMMdd格式
     */
    public static String DateFormat(String datestr)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = java.sql.Date.valueOf(datestr);
        String Date = formatter.format(date);
        return Date;
    }
    
    /*
     * 把时间转换为yyyy/MM/dd格式
     */
    public static String DateFormatTo(String datestr)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = java.sql.Date.valueOf(datestr);
        String Date = formatter.format(date);
        return Date;
    }
    
    /*
     * 根据yyyyMM获取yyyyMMdd的值
     */
    public static String getLastDate(String currentMonth, String format) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(currentMonth.substring(0, 4));
        int month = Integer.parseInt(currentMonth.substring(4, currentMonth.length()));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(cal.getTime());
    }
    
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static String getYestodayStart(String format) {
    	Calendar yestodayStart = Calendar.getInstance();
		yestodayStart.add(Calendar.DATE, -1);
		yestodayStart.set(Calendar.HOUR_OF_DAY, 0);		
		yestodayStart.set(Calendar.MINUTE, 0);		
		yestodayStart.set(Calendar.SECOND, 0);		
		yestodayStart.set(Calendar.MILLISECOND, 0);
		Date date = yestodayStart.getTime();
		return new SimpleDateFormat(format).format(date);
    }
    
    public static String getLastTwoDay(String format) {
    	Calendar yestodayStart = Calendar.getInstance();
		yestodayStart.add(Calendar.DATE, -2);
		yestodayStart.set(Calendar.HOUR_OF_DAY, 0);		
		yestodayStart.set(Calendar.MINUTE, 0);		
		yestodayStart.set(Calendar.SECOND, 0);		
		yestodayStart.set(Calendar.MILLISECOND, 0);
		Date date = yestodayStart.getTime();
		return new SimpleDateFormat(format).format(date);
    }
    
	public static String getMonthStart() {
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR_OF_DAY, 0);		
		monthStart.set(Calendar.MINUTE, 0);		
		monthStart.set(Calendar.SECOND, 0);		
		monthStart.set(Calendar.MILLISECOND, 0);
		Date date = monthStart.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
	}
	
	public static String getMonthEnd() {
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.add(Calendar.MONTH, 1);
		monthEnd.set(Calendar.DAY_OF_MONTH, 0);
        return new SimpleDateFormat("yyyy-MM-dd").format(monthEnd.getTime()) + " 23:59:59";
	}

	//获取前一天的0点和当天0点
    public static Map<String,Date> getYestodayStartEnd(){
        Map<String,Date> resultMap = new HashMap<>();
        Date dNow = new Date(); //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
        dBefore = calendar.getTime(); //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前一天
        defaultStartDate = defaultStartDate+" 00:00:00";
        String defaultEndDate = sdf.format(dNow).substring(0,10)+" 00:00:00";
        resultMap.put("startTime",parseDate(defaultStartDate));
        resultMap.put("endTime",parseDate(defaultEndDate));
        return resultMap;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException
    {
    	System.out.println(getYestodayStartEnd());
    }
    
}
