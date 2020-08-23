package com.fanbo.controller;

import com.fanbo.manager.RedissonLock;
import com.fanbo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redisson")
public class RedissonController {

    @Autowired
    private RedisService redisService;

    //
    @RequestMapping(value = "lockTest", method = RequestMethod.GET)
    public boolean lockTest(@RequestParam("id") Integer id){

        try {
            String key = "fb_lock";
            RedissonLock.lock(key);   //获取锁（上锁）

            Thread.sleep(20 * 1000);
            System.out.println("--lock--:" + id);
            redisService.put("lockTest", key);

            RedissonLock.unlock(key);  //释放锁

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "lockTest2", method = RequestMethod.GET)
    public boolean lockTest2(@RequestParam("id") Integer id){

        try {
            String key = "fb_lock";
            RedissonLock.lock(key);

            System.out.println("--lock2--:" + id);
            redisService.put("lockTest2", key);

            RedissonLock.unlock(key);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "lockTest3", method = RequestMethod.GET)
    public boolean lockTest3(@RequestParam("id") Integer id){

        try {
            String key = "fb_lock_2";
            RedissonLock.lock(key);

            System.out.println("--lock3--:" + id);
            redisService.put("lockTest3", key);

            RedissonLock.unlock(key);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
