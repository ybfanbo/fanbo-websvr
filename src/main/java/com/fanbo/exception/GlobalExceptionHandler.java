package com.fanbo.exception;

import com.fanbo.model.basemodel.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//全局捕获异常
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response serverError(RuntimeException e){
        Response result = new Response();
        result.setStatus(0);
        result.setMessage("server error");

        logger.error("error api: " +  request.getRequestURI());  //打印出访问哪个接口报错了
        logger.error("error into:" + e.getMessage());   //打印出异常信息

        return result;
    }
}
