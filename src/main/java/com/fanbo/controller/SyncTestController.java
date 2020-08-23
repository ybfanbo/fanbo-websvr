package com.fanbo.controller;

import com.fanbo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncTestController {

    @Autowired
    private HelloService helloService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String syncTest(){
        System.out.println("sync test started...");
        helloService.forMethod();
        System.out.println("sync test finished...");
        return "over";
    }

    //异步方法测试
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String syncTest2(){
        System.out.println("sync test started...");
        helloService.forMethod();
//        helloService.syncMethod();
        System.out.println("sync test finished...");
        return "over";
    }


}
