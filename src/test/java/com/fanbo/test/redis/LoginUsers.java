package com.fanbo.test.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class LoginUsers {

	private Jedis jedis;
	private String activityId;
	private String activityId_ZI;
	private String activityId_ZA;
	private String date;

	@Before
	public void setup() {
		// 连接redis服务器（正式服）
		jedis = new Jedis("121.196.236.82", 18880);

		// 权限认证
		jedis.auth("aHNnZW5lLmNvbQ==");
		
		activityId = "activity_201604";
		activityId_ZI = "activity_201606";
		activityId_ZA = "activity_201607";
		
		date = "20160726";	
	}
	
	@Test//每日活动分享次数、人数、登录人数
	public void loginUsers(){
		
	//	Double count = jedis.zscore("news_share_record_" + activityId, date);		
	//	System.out.println(date + "拆礼盒活动分享次数：" + count);
		
	//	Set<String> set = jedis.smembers("news_share_user_" + activityId + "_" + date);		
	//	System.out.println(date + "拆礼盒活动分享人数：" + set.size());
		
	//	Double count2 = jedis.zscore("news_share_record_" + activityId_ZI, date);		
	//	System.out.println(date + "粽子活动分享次数：" + count2);
		
	//	Set<String> set2 = jedis.smembers("news_share_user_" + activityId_ZI + "_" + date);		
	//	System.out.println(date + "粽子活动分享人数：" + set2.size());

		Double count3 = jedis.zscore("news_share_record_" + activityId_ZA, date);		
		System.out.println(date + "砸蛋活动分享次数：" + count3);

		Set<String> set3 = jedis.smembers("news_share_user_" + activityId_ZA + "_" + date);		
		System.out.println(date + "砸蛋活动分享人数：" + set3.size());

		Set<String> loginUsers = jedis.smembers("launch_daily_" + date);		
		System.out.println(date + "总登录人数：" + loginUsers.size());		
		for(String str : loginUsers){
			System.out.println(str);
		}

	}
	
	@Test//每日活动分享次数、人数
	public void getActivityShareCount(){
		
		Double count = jedis.zscore("news_share_record_" + activityId, date);
		
		System.out.println(date + "活动分享次数：" + count);
		
		Set<String> set = jedis.smembers("news_share_user_" + activityId + "_" + date);
		
		System.out.println(date + "活动分享人数：" + set.size());
		
	}
	

	
	@Test//每周登录人数
	public void getLoginWeeklyUsers(){
		//201606_5总登录人数：1542
		String week = "201607_2";
		
		Set<String> loginUsers = jedis.smembers("launch_weekly_" + week);
		
		System.out.println(week + "总登录人数：" + loginUsers.size());
		
		for(String str : loginUsers){
			System.out.println(str);
		}
	}
	
	@Test//每月登录人数
	public void getLoginMonthlyUsers(){
		
		String month = "201607";
		
		Set<String> loginUsers = jedis.smembers("launch_monthly_" + month);
		
		System.out.println(month + "总登录人数:" + loginUsers.size());
	/*	
		for(String str : loginUsers){
			System.out.println(str);
		}
	*/
	}

	
	
}
