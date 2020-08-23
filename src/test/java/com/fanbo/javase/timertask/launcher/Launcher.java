package com.fanbo.javase.timertask.launcher;


import com.fanbo.javase.timertask.constant.Constant;
import com.fanbo.javase.timertask.task.ExcludeTask;
import com.fanbo.javase.timertask.utils.DateUtils;

import java.util.Timer;


public class Launcher {

	public static void main(String[] args) {

		Timer timer = new Timer();

		//每天临晨1点定时执行排重
		timer.scheduleAtFixedRate(new ExcludeTask(), DateUtils.getTimerDate(
				Constant.EXCLUDE_TIMER_HOUR, Constant.EXCLUDE_TIMER_MINUTE,
				Constant.EXCLUDE_TIMER_SECOND), Constant.DAY_OF_MILLISECONDS); 
	}

}
