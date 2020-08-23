package com.fanbo.authentication;

import com.fanbo.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {

            Cookie cookie = new Cookie("x-auth-token", session.getId());
            cookie.setPath("/");
            cookie.setMaxAge(15 * 60);
            response.addCookie(cookie);

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            CurrentUser user = (CurrentUser) authentication.getPrincipal();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
            response.setHeader("Access-Control-Allow-Credentials", "false");
            response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");

            response.getOutputStream()
                    .write(("{\"userInfo\":" + StringUtil.getJson(user) + "}")
                            .getBytes());
            response.flushBuffer();

        }
    }

}
