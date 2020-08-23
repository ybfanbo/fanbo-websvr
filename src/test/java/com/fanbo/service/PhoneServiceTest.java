package com.fanbo.service;

import com.fanbo.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PhoneServiceTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    public void resultTypeTest(){

        List<Map<String, Object>> allPhones = phoneService.getAllPhones();
        List<Map<String, Object>> allPhones2 = phoneService.getAllPhones2();
        System.out.println("allPhones: " + StringUtil.getJson(allPhones));
        System.out.println("allPhones2: " + StringUtil.getJson(allPhones2));

    }

}
