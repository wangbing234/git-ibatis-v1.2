package com.block.core.ibatis.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final long m = 60 * 1000L;// 分
	private static final long hour = 3600 * 1000L;// 小时
	private static final long day = 24 * hour;// 天
	/**
	 * 把字符串转换成Date对象
	 * 
	 * @param dateStr
	 * @param pattern
	 *            日期模式，默认为Constants.DEFAULT_DATE_FORMAT
	 * @return Date对象
	 */
	public static Date parseDate(String dateStr) {
		if (-1 == dateStr.indexOf("-")) {
			return parseDate(dateStr, "yyyyMMdd");
		} else {
			return parseDate(dateStr, "yyyy-MM-dd");
		}
	}

	public static Date parseDate(String dateStr, String pattern) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		if (pattern == null || "".equals(pattern)) {
			return null;
		}
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            Date对象或者是String对象
	 * @param pattern
	 *            日期格式，默认为Constants.DEFAULT_DATE_FORMAT
	 * @return 日期字符串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}
	
	public static String formatDateMinute(Date time) {
		return formatDate(time, "yyyy-MM-dd HH:mm");
	}

	public static String formatDatetime(Date time) {
		return formatDate(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null || "".equals(pattern)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 日期+偏移量
	 * 
	 * @param date
	 *            Date对象
	 * @param offset
	 *            偏移量
	 * @return 日期
	 */
	public static Date offsetDate(Date date, int offset) {
		return offsetDate(date, offset, Calendar.DAY_OF_YEAR);
	}

	/**
	 * 日期+偏移量
	 * 
	 * @param date
	 *            Date对象
	 * @param offset
	 *            偏移量
	 * @return 日期
	 */
	public static Date offsetDate(Date date, int offset, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	/**
	 * 日期间隔天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long betweenDays = Math.abs(time2 - time1) / (1000 * 3600 * 24) + 1;
		return (int) betweenDays;
	}

	/**
	 * 清除“秒”域
	 * 
	 * @param date
	 *            时间
	 */
	public static Date clearSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 清除某时间域
	 * 
	 * @param date
	 *            时间
	 * @param field
	 *            域
	 */
	public static Date clearField(Date date, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(field, 0);
		return cal.getTime();
	}
	
	/**
	 * 计算 h个小时，m分钟后的时间 "以当前时间为基准"
	 * 
	 * @param h
	 * @param m
	 * @return
	 */
	public static Date calculateDate(int h, int m) {

		return calculateDate(new Date(), h, m);
	}
	
	/**
	 * 计算 h个小时，m分钟后的时间 "以fromdate为基准"
	 * 
	 * @param fromdate
	 * @param h
	 * @param m
	 * @return
	 */
	public static Date calculateDate(Date fromdate, int h, int m) {

		Date date = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromdate);
		cal.add(Calendar.HOUR_OF_DAY, h);
		cal.add(Calendar.MINUTE, m);
		date = cal.getTime();
		return date;
	}
	
	/**
	 * 两个时间相差的分钟数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dateDiff(Date d1, Date d2) {

		return dateCompare(d1, d2) / 1000 / 60;
	}
	
	
	/**
	 * 时间大小的比较，返回相差的毫秒数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dateCompare(Date d1, Date d2) {
		Calendar cal = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		cal.setTime(d1);
		ca2.setTime(d2);
		long l1 = cal.getTimeInMillis();
		long l2 = ca2.getTimeInMillis();
		return l1 - l2;
	}
	
	
	/**
	 * 日期时间转换成文字
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		Date currentDate = new Date();
		long cha = Math.abs(date.getTime() - currentDate.getTime());
		long hours = cha / hour;
		if (hours < 1) {
			if (cha / m <= 0) {
				return "刚刚";
			}
			return cha / m + "分钟前";
		}
		else if (hours < 24) {
			return cha / hour + "小时前";
		}
		else if (hours <= 72) {
			int nn = Integer.valueOf(cha / day + "");
			if (cha % day > 0) {
				nn++;
			}
			return nn + "天前";
		}
		return formatDateMinute(date);
	}

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		System.out.println(DateUtil.clearField(new Date(), Calendar.SECOND).getTime());
		System.out.println(DateUtil.clearSecond(new Date()).getTime());
		System.out.println(DateUtil.formatDate(new Date()));
		System.out.println(DateUtil.formatDate(DateUtil.clearSecond(new Date())));
		System.out.println(DateUtil.formatDate(DateUtil.offsetDate(new Date(), -5)));
		System.out.println(DateUtil.formatDate(DateUtil.offsetDate(DateUtil.parseDate("2015-12-28"), 5)));
	}

}
