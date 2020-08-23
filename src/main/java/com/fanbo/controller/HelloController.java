package com.fanbo.controller;

import com.fanbo.model.basemodel.ObjectResponse;
import com.fanbo.model.basemodel.Response;
import com.fanbo.service.HelloService;
import com.fanbo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello, my websvr.";
    }

    @RequestMapping(value = "/hello/nginx", method = RequestMethod.GET)
    public String helloNginx(){
        return request.getLocalAddr();
    }

    @RequestMapping(value = "/upstream", method = RequestMethod.GET)
    public ObjectResponse upstream(){
        ObjectResponse response = new ObjectResponse();
        if(request.getHeader("SERVER_ADDR")!=null) {
            response.setData(request.getHeader("SERVER_ADDR"));
        }else {
            response.setData(request.getLocalAddr());
        }
        return response;
    }

    @GetMapping("/error/test")
    public Response errorTest(){
        Response response = new Response();

        int a = 1 / 0;  //全局捕获异常测试接口

        return response;
    }
}
