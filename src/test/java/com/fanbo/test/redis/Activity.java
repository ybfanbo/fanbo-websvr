package com.fanbo.test.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

public class Activity {
	
	private Jedis jedis_test;
	private Jedis jedis_line;

	@Before
	public void setup() {
		
		// （测试服）
		jedis_test = new Jedis("192.168.10.176", 6379);
		// （线上服）
		jedis_line = new Jedis("121.196.236.82", 18880);

		// 权限认证
		jedis_test.auth("aHNnZW5lLmNvbQ==");
		jedis_line.auth("aHNnZW5lLmNvbQ==");
	}
	
	@Test  //测试服-查询      share_activity
	public void getGlobalOfTest(){
		
		String key = "share_activity";
		
		Map<String, String> result = jedis_test.hgetAll(key);
		
		System.out.println(result.get("activity_201607"));
	}
	
	@Test  //测试服-修改   hset global_config data "{\"region\": 2, \"dictionary\": 30, \"genelocus\": 2}"
	public void setGlobalOfTest(){
		
		String key = "global_config";
		
		String value = "{\"region\": 2, \"dictionary\": 33, \"genelocus\": 2}";
		
		jedis_test.hset(key, "data", value);
	}
	
	@Test  //测试服-查询      share_activity
	public void activity_queue(){
		
		String key = "activity_queue";
		
		List<String> lists = jedis_test.lrange(key, 0, 10000000);
		
		System.out.println(lists.size());
	}
	
	//-----------------------
	@Test  //正式服-查询
	public void getGlobalOfLine(){
		
		String key = "share_activity";
		
		Map<String, String> result = jedis_line.hgetAll(key);
		
		System.out.println(result.get("activity_201607"));
	}
	
	@Test  //正式服-修改
	public void setGlobalOfLine(){
		
		String key = "share_activity";
		
		String value = "{\"infoId\":\"activity_201607\",\"requireLogin\":\"true\", \"previewUrl\":\"http://tbd.api.hsgene.cn/activity/201607/index.html\",\"shareUrl\":\"http://tbd.api.hsgene.cn/activity/201607/fen.html?memberId={0}\"}";
		
		jedis_line.hset(key, "activity_201607", value);
	}
	
	@Test  //正试服-查询      share_activity
	public void activity_queue_online(){
		
		String key = "activity_queue";
		
		List<String> lists = jedis_line.lrange(key, 0, 10000000);
		/*
		for(String str : lists){
			
			System.out.println(str);
			
		}
		*/
		System.out.println(lists.size());
	}
	
	@Test //正式服-清空活动数据
	public void activity_queue_del_online(){
		jedis_test.del("activity_queue");
	}
	
}
