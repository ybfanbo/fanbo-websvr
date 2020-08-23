package com.fanbo.javase.timertask.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	//获取所定时的时间
	public static Date getTimerDate(Integer hour, Integer minute, Integer second){
		
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, hour); // 控制时
	    calendar.set(Calendar.MINUTE, minute);    // 控制分
	    calendar.set(Calendar.SECOND, second);    // 控制秒
	 
	    return calendar.getTime();     
		
	}
	
}
