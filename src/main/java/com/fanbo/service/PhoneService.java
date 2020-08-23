package com.fanbo.service;

import com.fanbo.dao.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PhoneService {

    @Autowired
    private PhoneDao phoneDao;

    public List<String> getPhones(){
        return phoneDao.getPhones();
    }

    public List<Map<String, Object>> getAllPhones(){
        return phoneDao.getAllPhones();
    }

    public List<Map<String, Object>> getAllPhones2(){
        return phoneDao.getAllPhones2();
    }
}
