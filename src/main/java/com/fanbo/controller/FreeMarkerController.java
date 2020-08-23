package com.fanbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller  //这里不能用RestController
public class FreeMarkerController {

    @RequestMapping(value = "/freemarker/index")
    public String index(ModelMap map){
        Map<String, String> resource = new HashMap<>();
        resource.put("name", "zhang3");
        resource.put("ip", "192.168.1.1");
        resource.put("address", "云南昆明");
        map.addAttribute("resource",resource);   //model
        return "index";                         //view
    }


    @RequestMapping(value = "/freemarker/index2")
    public ModelAndView index2(){
        ModelAndView mav = new ModelAndView();
        Map<String, String> resource = new HashMap<>();
        resource.put("name", "fanbo");
        resource.put("ip", "192.168.1.2");
        resource.put("address", "中国四川");
        mav.addObject("resource", resource);  //model
        mav.setViewName("index");             //view
        return mav;
    }

}
