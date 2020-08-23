package com.fanbo.javase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeUtil {
	
	//获取当前时间（字符串格式：yyyy-MM-dd HH:mm:ss）
	public static String now(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(d);		
		return now;		
	}
	
	//Unix时间戳转换成Date
	public static Date getDateByUnixTime(long unixTime){
		Date d = new Date(unixTime * 1000);
		return d;
	}
	
	//Date转换成Unix时间戳
	public static long getUnixTimeByDate(Date date){
		long time = date.getTime() / 1000;
		return time;
	}
	
	//Unix时间戳转成String（格式：yyyy-MM-dd hh:mm:ss）
	public static String getStringByUnixTime(long unixTime){
		Date d = new Date(unixTime * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_str = sdf.format(d);
		return time_str;
	}
	
	//String转换成Unix时间戳
	public static long getUnixTimeByString(String date) throws ParseException{
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		long time = d.getTime() / 1000;
		return time;		
	}
	
	//Date转换成String（格式：yyyy-MM-dd hh:mm:ss）
	public static String getStringByDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_str = sdf.format(date);
		return time_str;
	}
	
	//String转换成Date
	public static Date getDateByString(String date) throws ParseException{
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		return d;
	}	
	
	
}
