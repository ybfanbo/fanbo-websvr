package com.fanbo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ForwardAndRedirectController {

    //请求转发
    @RequestMapping(value = "/forwardTest", method = RequestMethod.GET)
    public void forwardTest(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getRequestDispatcher("/hello/hello").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //重定向
    @RequestMapping(value = "/redirectTest", method = RequestMethod.GET)
    public void redirectTest(HttpServletResponse response) {

        try {
            response.sendRedirect("/hello/hello/nginx");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
