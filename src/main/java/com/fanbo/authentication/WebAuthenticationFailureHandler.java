package com.fanbo.authentication;

import com.fanbo.exception.UserDisabledException;
import com.fanbo.exception.UserNoRoleException;
import com.fanbo.exception.WhiteListAuthException;
import com.fanbo.utils.StringUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        Map<String, Object> responseMap = new HashMap();
        responseMap.put("hasMessage", true);
        responseMap.put("status", -1);

        String message;
        if (exception instanceof UsernameNotFoundException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            message = exception.getMessage();       //用户不存在
        }else if (exception instanceof UserDisabledException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            message = exception.getMessage();     //用户无效
        }else if (exception instanceof WhiteListAuthException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            message = exception.getMessage();     //白名单认证失败
        }else if(exception instanceof UserNoRoleException){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            message = exception.getMessage();   //用户无角色
        }else if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            message = exception.getMessage();  //密码错误
        }else if (exception instanceof AccountStatusException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            if (exception instanceof AccountExpiredException) {
                message = "账号已过期";
            } else if (exception instanceof CredentialsExpiredException) {
                message = "凭证已过期";
            } else if (exception instanceof DisabledException) {
                message = "账号已关闭";
            } else if (exception instanceof LockedException) {
                message = "账号被锁定";
            } else {
                message = "账号状态存在问题";
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            message = "禁止访问";
        }

        responseMap.put("message", message);

        response.setContentType("application/json");
        response.getWriter().println(StringUtil.getJson(responseMap));

        response.flushBuffer();

    }


}

