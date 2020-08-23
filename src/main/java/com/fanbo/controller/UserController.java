package com.fanbo.controller;

import com.fanbo.model.basemodel.ListResponse;
import com.fanbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/name/list", method = RequestMethod.GET)
    public ListResponse nameList(){
        ListResponse response = new ListResponse();
        try {
            List<String> result = userService.getUserNames();
            response.setDataList(result);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(0);
            response.setMessage("server error");
        }
        return response;
    }
}
