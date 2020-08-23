package com.fanbo.test.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class Global {
	
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
	
	@Test  //测试服-查询      hgetall global_config
	public void getGlobalOfTest(){
		
		String key = "global_config";
		
		Map<String, String> result = jedis_test.hgetAll(key);
		
		System.out.println(result.get("data"));
		System.out.println(result.get("release"));
	}
	
	@Test  //测试服-修改   hset global_config data "{\"region\": 2, \"dictionary\": 30, \"genelocus\": 2}"
	public void setGlobalOfTest(){
		
		String key = "global_config";
		
		String value = "{\"region\": 2, \"dictionary\": 33, \"genelocus\": 2}";
		
		jedis_test.hset(key, "data", value);
	}
	
	//-----------------------
	@Test  //正式服-查询
	public void getGlobalOfLine(){
		
		String key = "global_config";
		
		Map<String, String> result = jedis_line.hgetAll(key);
		
		System.out.println(result.get("data"));
		System.out.println(result.get("release"));
	}
	
	@Test  //正式服-修改
	public void setGlobalOfLine(){
		
		String key = "global_config";
		
		String value = "{\"region\": 2, \"dictionary\": 33, \"genelocus\": 2}";
		
		jedis_line.hset(key, "data", value);
	}
	
}
