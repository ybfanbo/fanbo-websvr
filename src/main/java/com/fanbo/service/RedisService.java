package com.fanbo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //有效时间3600秒
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, 3600, TimeUnit.SECONDS);
    }

    //自定义有效时间
    public void put(String key, String value, int expireSecond) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
    }

    //未验证
    public void putMap(String key, Map<String, Object> map) {
        redisTemplate.opsForValue().set(key, map);
    }

    public void putMap(String key, Map<String, Object> map,int expireSecond) {
        redisTemplate.opsForValue().set(key, map);
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
    }



    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void deleteKeys(Set<String> keys){
        redisTemplate.delete(keys);
    }

    public void delPrefixKeys(String prefix){
        Set<String> keys = redisTemplate.keys(prefix + "*"); //如: cache_*
        redisTemplate.delete(keys);
    }

    //设置有效期
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

}
