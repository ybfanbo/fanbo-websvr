package com.fanbo.service;

import com.fanbo.authentication.CurrentUser;
import com.fanbo.authentication.UserRole;
import com.fanbo.dao.UserDao;
import com.fanbo.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.getUserInfoByUsername(username);

        if (userInfo == null){
            logger.info(String.format("${0} is not exists!", username));
            return null;
        }

        //用户角色
        List<GrantedAuthority> authorities = new ArrayList<>();

//        List<UserRole> userRoles = List.of(UserRole.Admin, UserRole.Standard);  //当前用户角色
//
//        userRoles.forEach(userRole -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
//        });

//        CurrentUser currentUser = new CurrentUser(username, userInfo.getPassword(), authorities, userInfo.getId(), userInfo.getEmail(), userInfo.getPhone(),
//                userInfo.getCreateDate(), userInfo.getUpdateDate(), userRoles);
//
//        return currentUser;
        return null;
    }

}
