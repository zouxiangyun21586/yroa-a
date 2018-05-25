package com.yr.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @作者 林水桥
 * 2018年5月22日上午10:38:38
 */
public final class DateUtils {
	static DateFormat dfymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static DateFormat dfymd = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat dfhms = new SimpleDateFormat("HH:mm:ss");

	private static final Integer T1000 = 1000;
	private static final Integer T60 = 60;
	private static final Integer T24 = 24;
	private static final Integer T18 = 18;
	private static final Integer T23 = 23;
	private static final Integer T13 = 13;
	private static final Integer T1 = -1;
	private static final Integer T365 = 365;
	
	
	private DateUtils() {
		
	}
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:13:23
	 * 
	 * @param d ..
	 * @return String
	 */
	public static String dataToStringYMD(Date d) {
		return dfymd.format(d);
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:13:29
	 * 
	 * @param d ..
	 * @return String
	 */
	public static String dataToStringYMDHMS(Date d) {
		return dfymdhms.format(d);
	}

	/**
	 * 得到当前日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDate() {
		return dfymd.format(new Date());
	}

	/**
	 * 得到当前日期和时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		return dfymdhms.format(new Date());
	}
	
	/**
	 * 获得Date类型的当前日期和时间
	 * @return yyyy-MM-dd HH:mm:ss
	 * Date
	 * @作者 林水桥2018年5月25日上午9:05:08
	 */
	public static Date getCurretDateTimeA() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(getCurrentDateTime(), pos);
		return strtodate;
	}

	/**
	 * 得到当前时间
	 * 
	 * @return HH:mm:ss
	 */
	public static String getCurrentTime() {
		return dfhms.format(new Date());
	}

	/**
	 * 得到当前小时数
	 * 
	 * @return HH
	 */
	public static int getCurrentHour() {
		int y, m, d, h, mi, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH);
		d = cal.get(Calendar.DATE);
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		return h;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:13:49
	 * 
	 * @return int
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		return y;
	}

	/**
	 * 获取当前是上午,下午还是晚上 AM上午 PM下午 NT晚上 -1不正确
	 * 
	 * @return String AM/PM/NT
	 */
	public static String getAmPmNt() {
		GregorianCalendar ca = new GregorianCalendar();
		String time = "-1";
		if (ca.get(GregorianCalendar.AM_PM) == 0) {
			time = "AM";
		} else {
			int hour = getCurrentHour();
			if (hour >= T13 && hour < T18) {
				time = "PM";
			} else if (hour >= T18 && hour <= T23) {
				time = "NT";
			}
		}
		return time;
	}

	/**
	 * 两个时间相差距离多少分
	 * 
	 * @param baseTime
	 *            时间参数 1 格式：yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 *            时间参数 2 格式：yyyy-MM-dd HH:mm:ss
	 * @return 正数表示迟到多少分钟,-数表示早到多少分也就是没迟到 long[] 返回值为：{天, 时, 分, 秒}
	 * 
	 */
	public static long getDistanceTimes(String baseTime, String nowTime) {

		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = dfymdhms.parse(baseTime);
			two = dfymdhms.parse(nowTime);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			diff = time2 - time1;
			return (diff / (T1000 * T60));
			/*
			 * if(time1<time2) { diff = time2 - time1; } else { diff = time1 - time2; }
			 */
			/*
			 * day = diff / (24 * 60 * 60 * 1000); hour = (diff / (60 * 60 * 1000) - day *
			 * 24); min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			 */
			// sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// long[] times = {day, hour, min, sec};
		// return day+" "+" "+hour +" "+min+" "+sec;
		return 0;
	}

	/**
	 * 两个时间相差距离多少年
	 * 
	 * @param baseTime
	 *            时间参数 1 格式：yyyy-MM-dd HH:mm:ss
	 * @param nowTime
	 *            时间参数 2 格式：yyyy-MM-dd HH:mm:ss
	 * @return 正数表示迟到多少分钟,-数表示早到多少分也就是没迟到 long[] 返回值为：{天, 时, 分, 秒}
	 * 
	 */
	public static long getDistanceYear(String baseTime, String nowTime) {
		try {
			Date one = dfymdhms.parse(baseTime);
			Date two = dfymdhms.parse(nowTime);
			return getDistanceYear(one, two);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return T1;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:14:13
	 * 
	 * @param baseTime 最后时间
	 * @param nowTime 开始时间
	 * @return long类型
	 */
	public static long getDistanceYear(Date baseTime, Date nowTime) {
			long time1 = baseTime.getTime();
			long time2 = nowTime.getTime();
			long diff;
			diff = time2 - time1;
			return (diff / (T1000 * T60 * T60 * T24) / T365);
			/*
			 * if(time1<time2) { diff = time2 - time1; } else { diff = time1 - time2; }
			 */
			/*
			 * day = diff / (24 * 60 * 60 * 1000); hour = (diff / (60 * 60 * 1000) - day *
			 * 24); min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			 */
			// sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
		// long[] times = {day, hour, min, sec};
		// return day+" "+" "+hour +" "+min+" "+sec;
	}

}
