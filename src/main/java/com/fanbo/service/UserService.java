package com.fanbo.service;

import com.fanbo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<String> getUserNames(){
        return userDao.getUserNames();
    }

}
