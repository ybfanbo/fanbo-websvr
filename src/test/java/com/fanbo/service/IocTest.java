package com.fanbo.service;

import com.fanbo.domain.IocInitBean;
import com.fanbo.domain.IocInitBean2;
import com.fanbo.domain.IocInitBean3;
import com.fanbo.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IocTest {

    @Autowired
    private IocInitBean iocInitBean;
    @Autowired
    private IocInitBean2 iocInitBean2;
    @Autowired
    private IocInitBean3 iocInitBean3;

    //IOC注入测试
    @Test
    public void getIocInitBean(){
        String result = StringUtil.getJson(iocInitBean);
        System.out.println("result: " + result);
    }

    //IOC依赖注入测试
    @Test
    public void getIocInitBean2(){
        String result = StringUtil.getJson(iocInitBean2);
        System.out.println("result: " + result);
    }

    //IOC @Import注入其它类配置测试
    @Test
    public void getIocInitBean3(){
        String result = StringUtil.getJson(iocInitBean3);
        System.out.println("result: " + result);
    }

}
