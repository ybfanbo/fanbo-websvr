package com.fanbo.test;

import com.fanbo.domain.UserInfo;
import com.fanbo.utils.SerializationUtil;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

public class SerializableTest {

    @Test
    public void seriTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("aaa");
        userInfo.setEmail("123@qq.com");

        SerializationUtils.serialize(userInfo);
        UserInfo u = (UserInfo)SerializationUtils.deserialize(SerializationUtils.serialize(userInfo));

        System.out.println("id: " + u.getId());
        System.out.println("email: " + u.getEmail());


        userInfo.setEmail("456@qq.com");
        SerializationUtil.serialize(userInfo);

    }

    @Test
    public void deseriTest(){
        UserInfo userInfo = (UserInfo) SerializationUtil.deserialize();
        System.out.println("new email: " + userInfo.getEmail());
    }


}
