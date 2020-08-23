package com.fanbo.authentication;

import com.fanbo.model.basemodel.Response;
import com.fanbo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisService redisService;


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String authToken = request.getHeader("x-auth-token");

        if (authToken == null) {
            authToken = request.getParameter("token");
        }

        if (authToken != null) {
            String key = "spring:session:sessions:" + authToken;
            redisService.delete(key);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{\"message\":\"logout success!\"}");
        response.getWriter().flush();
    }

}
