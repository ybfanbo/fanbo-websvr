package com.fanbo.test;

import com.fanbo.manager.RedissonLock;
import org.junit.Test;

public class RedissonTest {

    @Test
    public void lockTest(){
        String key = "fb_lock";
        RedissonLock.lock(key);

        System.out.println("----------test------------");

        RedissonLock.unlock(key);
    }

}
