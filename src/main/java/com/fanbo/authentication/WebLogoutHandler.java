package com.fanbo.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;


public class WebLogoutHandler implements LogoutHandler {

    private Logger logger = LoggerFactory.getLogger(WebLogoutHandler.class);

    public WebLogoutHandler() {
    }

    @Override
    public void logout(HttpServletRequest request,HttpServletResponse response, Authentication authentication) {

        Authentication secAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;

        if (secAuthentication != null){
            principal = secAuthentication.getPrincipal();
        }

        if (principal != null && principal instanceof CurrentUser) {
            CurrentUser currentUser = (CurrentUser) principal;

            logger.info(currentUser.getUsername() + " logout success!"); //注销成功
        }

    }
}