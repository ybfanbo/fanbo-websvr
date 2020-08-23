package com.fanbo.config;

import com.fanbo.manager.MyHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HttpSessionStrategy;

//打开即使用rediss存储session（用于分布式redis共享session）
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 900)  //maxInactiveIntervalInSeconds默认是1800秒，即30分钟
public class HttpSessionConfig {

    @Bean
    public HttpSessionStrategy httpSessionStrategy(){
        return new MyHttpSessionStrategy();
    }

}
