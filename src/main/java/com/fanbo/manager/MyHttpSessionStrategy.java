package com.fanbo.manager;

import com.fanbo.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class MyHttpSessionStrategy implements HttpSessionStrategy {

    private Logger logger = LoggerFactory.getLogger(MyHttpSessionStrategy.class);

    private String tokenHeaderName = "x-auth-token";

    @Override
    public String getRequestedSessionId(HttpServletRequest httpServletRequest) {

        //请求头带了token，则作为sessionId返回
        String headerToken = httpServletRequest.getHeader(tokenHeaderName);
        if (StringUtil.isNotNull(headerToken)){
            logger.info("sessionId is from header...");
            return headerToken;
        }

        //cookie中带了token，则作为sessionId返回
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null){
            String cookieToken = Arrays.stream(cookies).filter(cookie -> tokenHeaderName.equals(cookie.getName())).findFirst().map(c -> c.getName()).orElse(null);
            if (StringUtil.isNotNull(cookieToken)){
                logger.info("sessionId is from cookie...");
                return cookieToken;
            }
        }

        //参数中带了token，则作为sessionId返回
        String paramToken = httpServletRequest.getParameter("token");
        if (StringUtil.isNotNull(paramToken)){
            logger.info("sessionId is from param...");
            return paramToken;
        }

        //请求头、cookie、param都没有token
        return null;
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader(tokenHeaderName, session.getId());
    }

    @Override
    public void onInvalidateSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader(tokenHeaderName, "");
    }

    public void setTokenHeaderName(String tokenHeaderName) {
        this.tokenHeaderName = tokenHeaderName;
    }
}
