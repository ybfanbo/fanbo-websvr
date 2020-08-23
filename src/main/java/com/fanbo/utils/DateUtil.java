package com.fanbo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {


    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }


    //ruleEngine只能接收的格式
    public static String getRuleTime(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(time);
    }

    public static Date parseRuleTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date result;
        try {
            result = StringUtil.isNull(time) ? null : sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    //解析东八区时间格式的日期
    public static Long parseBjTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
        Date parse = null;
        try {
            parse = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse == null ? null : parse.getTime();
    }


    //获取昨天的日期，格式为yyyyMMdd
    public static String getYesterDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);    //1天前

        return format(calendar.getTime());
    }

    //获取上周的日期，格式为yyyyMMdd的字符串
    public static String getLastWeekFormatDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);    //7天前

        return format(calendar.getTime());
    }


    //获取上个月的日期，格式为yyyyMMdd
    public static String getLastMonthDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);    //1个月以前

        return format(calendar.getTime());
    }

    //获取上周的日期
    public static Date getLastWeekDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);    //7天前

        return calendar.getTime();
    }

    //获取上周一的起始日期
    public static Date getLastMondayDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1 * 7);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime(); //上周一
    }

    //获取上上周一的起始日期，格式为yyyyMMdd 00:00:00
    public static Date getLastLastMondayDate() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2 * 7);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return formatToYyyyMmDd(calendar.getTime());//上上周一

    }

    //yyyyMMdd HH:mm:ss格式转yyyyMMdd 00:00:00格式
    public static Date formatToYyyyMmDd(Date date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.parse(dateFormat.format(date));
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {

            SimpleDateFormat sdf = new SimpleDateFormat(pattern[0].toString());
            formatDate = sdf.format(date);

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = sdf.format(date);

        }
        return formatDate;
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
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


    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
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
     * 获取日期之间的天数
     * @param d1
     * @param d2
     * @return
     */
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR)
                - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

}
