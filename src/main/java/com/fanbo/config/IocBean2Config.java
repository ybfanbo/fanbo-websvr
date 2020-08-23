package com.fanbo.config;

import com.fanbo.domain.IocInitBean;
import com.fanbo.domain.IocInitBean3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(IocBeanConfig.class)   //引入其它配置类
public class IocBean2Config {

    @Bean
    public IocInitBean3 iocInitBean3(IocInitBean iocInitBean){   //IocInitBean为其它配置类中的实例
        return new IocInitBean3(iocInitBean, "@import test");
    }

}
