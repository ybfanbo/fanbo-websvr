package com.fanbo.config;

import com.fanbo.domain.IocInitBean;
import com.fanbo.domain.IocInitBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IocBeanConfig {

    //注入IOC
    @Bean
    public IocInitBean iocInitBean(){
        return new IocInitBean(1, "fanbo333", "ioc test");
    }

    //iocInitBean2依赖iocInitBean
    @Bean
    public IocInitBean2 iocInitBean2(){
        return new IocInitBean2(iocInitBean(), "IOC依赖注入，测试");
    }

}
