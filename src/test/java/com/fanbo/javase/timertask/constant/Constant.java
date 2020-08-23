package com.fanbo.javase.timertask.constant;

public class Constant {

	public static final Integer EXCLUDE_TIMER_HOUR = 1;  	//排重定时：时（临晨1点）
	public static final Integer EXCLUDE_TIMER_MINUTE = 0;	//排重定时：分（0分）
	public static final Integer EXCLUDE_TIMER_SECOND = 0;   //排重定时：秒（0秒）
    
	public static final Integer DAY_OF_MILLISECONDS = 1000 * 60 * 60 * 24;  //1天

    public static final String QUEUE_EXCLUDE_TIMING = "queue_exclude_timing";  //redis key：待定时排重的病历ID队列

    public static final String QUEUE_EXCLUDE = "queue_exclude"; //redis key：实时排重的病历ID队列
}
