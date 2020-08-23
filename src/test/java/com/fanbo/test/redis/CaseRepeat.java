package com.fanbo.test.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CaseRepeat {

	private Jedis jedis_line;
	private Jedis jedis_test;
	private Jedis jedis_localhost;

	@Before
	public void setup() {
		// 连接redis服务器（正式服）
		jedis_line = new Jedis("121.196.236.82", 18880);
		// 连接redis服务器（测式服）
		jedis_test = new Jedis("192.168.10.176", 6389);
		//  本地
		jedis_localhost = new Jedis("localhost", 6379);
		// 权限认证
		jedis_line.auth("aHNnZW5lLmNvbQ==");
		jedis_test.auth("aHNnZW5lLmNvbQ==");
	}

	@Test  //线上服将病历添加进队列
	public void caseRepeatOfLine() throws InterruptedException{
		
		String key = "queue_exclude";		
		
		jedis_line.lpush(key, "347f9cfa40a944f3b16b078bcee02b43");	
		jedis_line.lpush(key, "5677f99f9d2945d69fbeba279f6074b3");	
		jedis_line.lpush(key, "5dad365eed5d4c409fb21b2f92133fea");	

	}
	
	@Test  //测试服将病历添加进队列
	public void caseRepeatOfTest() throws InterruptedException{
		
		String key = "queue_exclude";		

		jedis_test.lpush(key, "8cbc604f977a4c6480dbb1cc4e16b8d8");
		jedis_test.lpush(key, "7b6c782a306642f0a394fc43c84defc3");
		jedis_test.lpush(key, "5d278af3716b4972840cb85538c4e534");
	

	}
	
	@Test  //查看队列里有没有数据
	public void getCaseRepeat() throws InterruptedException{
		
		String key = "queue_exclude";
		
		List<String> line = jedis_line.lrange(key, 0, 100);
		List<String> test = jedis_test.lrange(key, 0, 100);
		
		for(String str : line){
			System.out.println("线上服队列里的数据：" + str);
		}
		for(String str : test){
			System.out.println("测试服队列里的数据：" + str);
		}
			
	}
	
	@Test
	public void xxx(){
		
		Jedis jedis_test = new Jedis("192.168.10.176", 6379);
		jedis_test.auth("aHNnZW5lLmNvbQ==");
		//jedis_test.del("activity_queue");
		
		List<String> size = jedis_test.lrange("activity_queue", 0, 100000);
		System.out.print(size.size());
		
		for(int i = 0; i< 200; i++){
		
			jedis_test.lpush("activity_queue", "oyQp8s_KtxwRAmXperjjD9sePO_8");
			
			
			
		}
		
		//jedis_test.lpush("activity_queue", "oyQp8s8N3JMWfeo6BF5_Gueq80UE");
	}
}
