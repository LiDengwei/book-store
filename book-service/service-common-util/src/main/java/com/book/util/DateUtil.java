package com.book.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	public static final int SECOND = 1000;

	public static final int MINUTE = SECOND * 60;

	public static final int HOUR = MINUTE * 60;

	public static final int DAY = HOUR * 24;

	public static final int WEEK = DAY * 7;

	public static final int YEAR = DAY * 365; // or 366 ???

	public static long millionSecondsOfDay = 86400000;

	public static Long getCurrentTime(){
		return System.currentTimeMillis();
	}

	public static Date getCurrentDate(){
		return new Date();
	}


	/*
         * java中对日期的加减操作 gc.add(1,-1)表示年份减一. gc.add(2,-1)表示月份减一. gc.add(3.-1)表示周减一.
         * gc.add(5,-1)表示天减一. gc.add(10,-1)表示减一个小时. GregorianCalendar类的add(int
         * field,int amount)方法表示年月日加减. field参数表示年,月.日等. amount参数表示要加减的数量.
         */
	public static Date changeTime(Date date, int filed, int amount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(filed, amount);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc
				.get(Calendar.DATE), gc.get(Calendar.HOUR_OF_DAY), gc
				.get(Calendar.MINUTE), gc.get(Calendar.SECOND));

		return gc.getTime();
	}

	public static String getXmlTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyyMMddHHmmssSSSS");
		return sdf.format(new Date());

	}
	/**
	 * 对天进行加减
	 *
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date changeDay(Date date, int amount) {
		return changeTime(date, Calendar.DATE, amount);
	}

	/**
	 * 对月进行加减
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date changeMonth(Date date, int amount) {
		return changeTime(date, Calendar.MONTH, amount);
	}

	public static Date changeHour(Date date, int amount) {
		return changeTime(date, Calendar.HOUR, amount);
	}

	public static Date changeYear(Date date, int amount) {
		return changeTime(date, Calendar.YEAR, amount);
	}

	/**
	 * 对秒进行加减
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date changeSecond(Date date, int amount) {
		return changeTime(date, Calendar.SECOND, amount);
	}

	/**
	 * 对分进行加减
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date changeMinute(Date date, int amount) {
		return changeTime(date, Calendar.MINUTE, amount);
	}

	/**
	 * 对查询条件时候进行处理
	 * 如2009-09-01 变成 2009-09-01 23:59:59
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date)
	{
		if( date != null )
		{
			return changeSecond(changeDay(date, 1), -1);
		}
		return null;
	}


	/**
	 * 将日期转化为字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date).toString();
	}


	public static String getCurrenyDateStr(){
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将字符串转化为日期
	 *
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatString(String dateStr, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}



	public static Date getUTCDateFromLocal(Date date){
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);

		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		//之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
		//System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
		return new Date(cal.getTimeInMillis());
	}

	public static Date getLocalTimeFromUTC(Date date){
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);

		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, (zoneOffset + dstOffset));

		//之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
		//System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
		return new Date(cal.getTimeInMillis());
	}




	public static Date newDate(int month, int date, int year) {
		Calendar inst = Calendar.getInstance();
		inst.clear();
		inst.set(year, month - 1, date);
		return inst.getTime();
	}

	public static boolean between(Date date, Date start, Date end) {
		return getDay(start, date) >= 0 && getDay(end, date) <= 0;
	}

	public static Date getDate(Date date, int i) {

		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);

		return calendar.getTime();

	}

	/**
	 * 得到本月的第一天
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));

		return  calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 *
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return  calendar.getTime();
	}

	public static Date getDateYear(Date date, int i) {

		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);

		return calendar.getTime();

	}
	public static Date getDateMonth(Date date, int i) {

		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);

		return calendar.getTime();

	}

	public static int compare(Date date1, Date date2) {
		return getDay(date1, date2);
	}

	/**
	 * 时间比较
	 * @param date1
	 * @param date2
     * @return 如果date2 > data1 为正数，否则为负数
     */
	public static double compareDouble(Date date1, Date date2) {
		try{
			if (date1 == null || date2 == null) {
				return 0;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date1 = sdf.parse(sdf.format(date1));
			date2 =  sdf.parse(sdf.format(date2));
			return (date2.getTime() - date1.getTime()) /  Double.valueOf(millionSecondsOfDay);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Date getDateByHour(Date date, int hour) {

		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);

		return calendar.getTime();

	}

	public static int getDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		date1 = getDate(date1);
		date2 = getDate(date2);

		return (int) ((date2.getTime() - date1.getTime()) / millionSecondsOfDay);
	}

	public static long getTimes(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		long time1s = date1.getTime();
		long time2s = date2.getTime();

		return (time1s-time2s);
	}

	public static int getMonth(Date date2, Date date1) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		int c =
				(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
						- cal2.get(Calendar.MONTH);
//		  System.out.println("(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12="+(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12);
//		  System.out.println("cal1.get(Calendar.MONTH)- cal2.get(Calendar.MONTH)="+(cal1.get(Calendar.MONTH)- cal2.get(Calendar.MONTH)));
		if(c>=0)c++;
		else c--;
		//不满一月按一月算
		return c;
	}

	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 日期是否符合某一星期
	 *
	 * @param date
	 * @param week
	 * @return
	 */
	public static boolean isMatchWeek(Date date, int week) {
		return getWeekOfDate(date) == week;
	}


	public static java.sql.Date getSqlDate(Date date) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	public static Date getDate(Date date) {
		if (date == null)
			return null;
		return getDate(DateUtil.dateToString(date));

	}

	public static Date getDefaultDateTime(Date date) {
		if (date == null)
			return null;
		String str = dateToString(date) + " 12:00";

		return stringToDatetime(str);
	}

	/**
	 * 获取当天零点时间
	 * @return
	 */
	public static Date getTodayStartTime() {
		String str = dateToString(new Date()) + " 00:00:00";
		return stringToDatetime(str);
	}

	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static Date getTodayEndTime() {
		String str = dateToString(new Date()) + " 23:59:59";
		return stringToDateTime(str);
	}

	public static List getDates(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return new ArrayList();

		int day = getDay(date1, date2);

		List dates = new ArrayList();

		for (int i = 0; i <= day; i++) {
			Date date = getDate(date1, i);
			dates.add(date);
		}
		return dates;
	}

	public static String dateToString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String datetimeToString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	public static String datetimesToString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取传入时间的 小时数
	 *
	 * @param date 日期参数
	 * @return
	 */
	public static Integer getHourByDate(Date date) {
		try {
			String dateTimesToString = datetimesToString(date);
			System.out.println(dateTimesToString);
			String[] split = dateTimesToString.split(" ");
			if (split.length == 2) {
				String[] strings = split[1].split(":");
				if (strings.length == 3) {
					return Integer.valueOf(strings[0]);
				}
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}

	/**
	 * 获取传入时间的 分钟数
	 *
	 * @param date 日期参数
	 * @return
	 */
	public static Integer getMinuteByDate(Date date) {
		try {
			String dateTimesToString = datetimesToString(date);
			System.out.println(dateTimesToString);
			String[] split = dateTimesToString.split(" ");
			if (split.length == 2) {
				String[] strings = split[1].split(":");
				if (strings.length == 3) {
					return Integer.valueOf(strings[1]);
				}
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}

	public static String toStringByFormat(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return "";
	}

	public static Date toDateByFormat(String str, String format) {
		if (str == null || str.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDate(String str) {
		if (str == null || str.equals(""))
			return null;
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDateTime(String str) {
		if (str == null || str.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDatetime(String str) {
		if (str == null || str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getDate(String str) {
		if (str == null || str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getTimeString(int duration) {
		int hours = duration / DateUtil.HOUR;
		int remain = duration - (hours * DateUtil.HOUR);
		int minutes = remain / DateUtil.MINUTE;
		StringBuffer time = new StringBuffer(64);
		if (hours != 0) {
			if (hours == 1) {
				time.append("1 hour and ");
			} else {
				time.append(hours).append(" hours and ");
			}
		}
		if (minutes == 1) {
			time.append("1 minute");
		} else {
			// what if minutes == 0 ???
			time.append(minutes).append(" minute(s)");
		}
		return time.toString();
	}

	public static int getYearOfSysTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthOfSysTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}



	public static Date getSystemDate() {
		return new Date();
	}

	public static Date getNowByShort() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		try {
			return sdf.parse(dateToString(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String formatDateToSQLString(Date srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(srcDate);
	}

	public static String formatDateToMMDD(Date srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		return sdf.format(srcDate);
	}

	public static String formatDateToYYMMDD(Date srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		return sdf.format(srcDate);
	}

	public static String formatTimeToString(Date srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(srcDate);
	}

	public static String formatTimeHToString(Date srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(srcDate);
	}

	public static String weeksToString(String[] week) {
		if (week == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String result = "";
		for (int i = 0; i < week.length; i++) {
			if (i == week.length - 1) {
				sb.append(week[i]);
			} else {
				sb.append(week[i]).append(",");
			}
			result = sb.toString();
		}
		return result;
	}

	public static String formatDateToString(Date srcDate){
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
		return sdf.format(srcDate);
	}

	public static String formatAllDateToString(Date srcDate){
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		return sdf.format(srcDate);
	}


	/**
	 * 判断是否为当前年，是返回 true 不是返回 false
	 * @param year
	 * @return
	 */
	public static boolean isNowYear(String year){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return year.equals(sdf.format(new Date()));
	}

	public static Integer getNowYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return Integer.parseInt(sdf.format(new Date()));
	}

	public static Integer getDateIntByType(String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return Integer.parseInt(sdf.format(new Date()));
	}

	public static Integer getDateToInt(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sdf.format(new Date()));
	}

	public static Date getYearByNos(String str) {
		if (str == null || str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINESE);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean isNowMonth(String year,String month){
		boolean result = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyM");
		String nowDates= sdf.format(new Date());
		if((year+month).equals(nowDates)){
			result = true;
		}
		return result;
	}

	public static Date getYearMonthByNos(String year,String month) {
		if (year == null || year.length()==0 || month == null || month.length()==0)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM", Locale.CHINESE);
		try {
			return sdf.parse(year+month);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	public static boolean isNowDay(Date nowDay){
		return DateUtil.formatDateToSQLString(nowDay).equals(DateUtil.formatDateToSQLString(new Date()).toString());
	}

	/**
	 * 获取该年的前几年 如 2010    结果返回 2010,2009,2008,2007,2006
	 * @param year
	 * @return
	 */
	public static String getYearsByYear(int year){
		String result ="";
		int i=0;
		for(int y=year;y>1980;y--){
			result = result+","+y;
			i++;
			if(i==6)break;
		}
		if(result.length()>0){
			result= result.substring(1);
		}
		return result;
	}

	public static String getYMsByYM(int year,int month){
		String result ="";
		int flat=0;
		for(int i=month;i>0;i--){
			result=result+","+year+i;
			if(i==1){
				i=13;
				year--;
			}
			flat++;
			if(flat==6)break;
		}
		if(result.length()>0){
			result = result.substring(1);
		}
		return result;
	}

	/**
	 * 返回日期是否在该时间段内
	 * @param strDate 欲比较的日期
	 * @param strDateBegin 开始日期
	 * @param strDateEnd 结束日期
	 * @return
	 */
	public static boolean isInDates(Date strDate,Date strDateBegin,Date strDateEnd){
		long myTime=strDate.getTime();
		long beginTime=strDateBegin.getTime();
		long endTime=strDateEnd.getTime();
		boolean result=false;

		//System.out.println(myTime+"++"+DateUtil.datetimeToString(strDate));
		//System.out.println(beginTime+"++"+DateUtil.datetimeToString(strDateBegin));
		//System.out.println(endTime+"++"+DateUtil.datetimeToString(strDateEnd));
		/*
		System.out.println(beginTime<=myTime);
		System.out.println(String.valueOf(myTime)+"<="+String.valueOf(endTime)+"="+(myTime<=endTime));

		System.out.println();*/

		if(beginTime<=myTime && myTime<=endTime){
			result=true;
		}
		return result;
	}

	public static int getHours(Date inDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inDate);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	public static int getIntervalDays(Date fDate, Date oDate) {

		if (null == fDate || null == oDate) {

			return -1;

		}

		long intervalMilli = oDate.getTime() - fDate.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}


	public static long getDistanceMinute(Date one, Date two) {
		long min = 0;
		try {
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			min = diff / (60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return min;
	}

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 *比较两个时间大小
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param num 分钟
	 * @return
	 */
	public static boolean compareMin (Date startDate,Date endDate,int num){
		long l=startDate.getTime()-endDate.getTime();
		long mins=((l/(60*1000)));
		if (mins >= num) {
			return true;
		}
		return false;
	}

	/**
	 *比较两个时间大小
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param num 小时
	 * @return
	 */
	public static boolean compareHour (Date startDate,Date endDate,int num){
		long l=startDate.getTime()-endDate.getTime();
		long hour=(l/(60*60*1000));
		if (hour >= num) {
			return true;
		}
		return false;
	}

	/**
	 *比较两个时间大小
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param num 月
	 * @return
	 */
	public static boolean compareMonth (Date startDate,Date endDate,int num){
		long l=startDate.getTime()-endDate.getTime();
		long month=(int)(l/(24*60*60*30*1000));
		if (month >= num) {
			return true;
		}
		return false;
	}

	/**
	 * 时间戳转为日期
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
     */
	public static Date LongToDate (Long time,String format) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat(format);
		String d = dateFormat.format(time);
		Date date = null;
		try {
			date = dateFormat.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取日期字符串
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrenyDateStrByFormat(String dateFormat){
		return formatDate(new Date(),dateFormat);
	}

	// 获得下周星期一的日期
	public static Date getNextMonday(Date gmtCreate) {
		int mondayPlus = getMondayPlus(gmtCreate);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		return monday;
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus(Date gmtCreate) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(gmtCreate);
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 0) {
			return -6;
		} else {
			return 1-dayOfWeek;
		}
	}

	public static long getTimesmorning(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return  zero.getTime();
	}

	/**
	 * 获取指定时期开始时间
	 * @return
	 */
	public static Date getDateStartTime(Date stDate) {
		String str = dateToString(stDate) + " 00:00:00";
		return stringToDateTime(str);
	}
	/**
	 * 获取指定日期结束时间
	 * @return
	 */
	public static Date getDateEndTime(Date stDate) {
		String str = dateToString(stDate) + " 23:59:59";
		return stringToDateTime(str);
	}

	/**
	 * 获取随机日期
	 * @param beginDate 起始日期，格式为：yyyy-MM-dd
	 * @param endDate 结束日期，格式为：yyyy-MM-dd
	 * @return
	 */
	public static Date randomDate(String beginDate,String endDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);

			if(start.getTime() >= end.getTime()){
				return null;
			}

			long date = random(start.getTime(),end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin,long end){
		long rtn = begin + (long)(Math.random() * (end - begin));
		if(rtn == begin || rtn == end){
			return random(begin,end);
		}
		return rtn;
	}

	/**
	 * 获取两个日期的时间差 小时分秒
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getDefDate(Date date1,Date date2) {
		long def = date1.getTime() - date2.getTime();
		int hours = (int) ((def) / (1000 * 60 * 60));
		int minute = (int) ((def - (hours * 60 * 60 * 1000)) / (1000 * 60));
		int second = (int) ((def - (hours * 60 * 60 * 1000) - (minute * 60 * 1000)) / 1000);
		String minuteStr = minute+"";
		if(minute<10){
			minuteStr = "0"+minute;
		}
		String secondStr = second+"";
		if(second<10){
			secondStr = "0"+second;
		}
		return hours + ":" + minuteStr + ":" + secondStr;
	}


	public static void main(String[] args) {
//		Date date = randomDate("2018-05-16","2018-05-17");
//		System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));

		Date date1 = stringToDateTime("2018-09-27 19:02:00");
		Date date2 = stringToDateTime("2018-09-27 8:00:55");
		System.out.println(getDefDate(date1,date2));
	}
}
