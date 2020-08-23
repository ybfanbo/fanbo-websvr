package com.fanbo.manager;

import com.fanbo.constant.Constants;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;

public class RedissonManager {

    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson = null;
    private static final String RAtomicName = "genId_";


    //实例化redisson  （redis单机情况）
    static {
        config.useSingleServer().setAddress("redis://192.168.0.104:6379").setPassword("028911");
        //得到redisson对象
        redisson = (Redisson) Redisson.create(config);
    }

    //实例化redisson  （redis集群情况）
//    static {
//        config.useClusterServers() //这是用的集群server
//                .setScanInterval(2000) //设置集群状态扫描时间
//                .setMasterConnectionPoolSize(10000) //设置连接数
//                .setSlaveConnectionPoolSize(10000)
//                .addNodeAddress("redis://192.168.0.104:6379","redis://192.168.0.107:6379").setPassword("028911");
//        redisson = (Redisson) Redisson.create(config);
//        //清空自增的ID数字
//        RAtomicLong atomicLong = redisson.getAtomicLong(RAtomicName);
//        atomicLong.set(1);
//    }

    //获取redisson对象的方法
    public static Redisson getRedisson() {
        return redisson;
    }

}
