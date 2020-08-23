package com.fanbo.authentication;

import com.fanbo.exception.UserDisabledException;
import com.fanbo.exception.UserNoRoleException;
import com.fanbo.service.CurrentUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CurrentUserDetailsService currentUserDetailsService;

    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();

        logger.info("login userName:" + userName);
        logger.info("login password:" + password);

        CurrentUser currentUser = (CurrentUser) currentUserDetailsService.loadUserByUsername(userName);

        if (currentUser == null){
            throw new UsernameNotFoundException(userName + " is not exist!");  //用户不存在
        }
        if (!currentUser.getPassword().equals(password)){
            throw new BadCredentialsException("Incorrect password!");          //密码不正确
        }
        if (CollectionUtils.isEmpty(currentUser.getUserRoles())){
            throw new UserNoRoleException(userName + " has no roles!");         //用户无角色
        }
        if (!currentUser.isEnabled()){
            throw new UserDisabledException(userName + " is not enabled!");     //用户失效
        }

        //credentials证书
        return new UsernamePasswordAuthenticationToken(currentUser, "credentials", currentUser.getAuthorities());
    }


    //先走这个supports，返回true之后再走authenticate方法
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }


}
