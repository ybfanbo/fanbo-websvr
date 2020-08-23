package com.fanbo.test.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class SmS {
	
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
	
	@Test  //正式服-查询
	public void getGlobalOfLine(){
		
		String key = "queue_sms";
		
		List<String> result = jedis_line.lrange(key, 0, 100);
		
		System.out.println("长度:" + result.size());

	}
	
	@Test  //测式服-查询
	public void getGlobalOfTest(){
		
		String key = "queue_sms";
		
		List<String> result = jedis_test.lrange(key, 0, 100);
		
		System.out.println("长度:" + result.size());

	}
}
