package com.fanbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {


    //页面请求
    @GetMapping("/socket")
    public ModelAndView socket() {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("data", "test");
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{sid}")
    public Map<String, Object> pushToWeb(String message, @PathVariable("sid") String sid) {
        Map<String, Object> result = new HashMap<>();
        try {
            WebSocketServer.sendInfo("server pushed...", sid);
            result.put("result", "success");
        } catch (IOException e) {
            e.printStackTrace();
            result.put("result", "failed");
        }
        return result;
    }


}
