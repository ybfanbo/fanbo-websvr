package com.fanbo.controller;

import com.fanbo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoubleDbTestController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/double/db/test", method = RequestMethod.GET)
    public List<String> getPhones(){
        return phoneService.getPhones();  //双数据源测试
    }

}
