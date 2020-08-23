package com.fanbo.javase.timertask.utils;

import redis.clients.jedis.Jedis;


public class RedisUtil {

    public static Jedis getRedisOfLocalhost(){
    	
    	Jedis jedis = new Jedis("localhost", 6379);
    	
    	return jedis;
    }
    
    public static Jedis getRedisOfTestServer(){
    	
    	Jedis jedis = new Jedis("192.168.10.176", 6379);
    	jedis.auth("aHNnZW5lLmNvbQ==");
    	
    	return jedis;
    }
    
    public static Jedis getRedisOfOnlineServer(){
    	
    	Jedis jedis = new Jedis("10.47.72.46", 6379);
    	jedis.auth("aHNnZW5lLmNvbQ==");
    	
    	return jedis;
    }

}
