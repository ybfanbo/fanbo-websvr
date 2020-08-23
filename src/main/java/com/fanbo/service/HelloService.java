package com.fanbo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Async
    public void forMethod(){
        for (int i=0; i< 900000; i++){
            System.out.println("index: " + i);
        }
    }

    public void syncMethod(){
        System.out.println("task 1");
        forMethod();
        System.out.println("task 2");
    }

}
