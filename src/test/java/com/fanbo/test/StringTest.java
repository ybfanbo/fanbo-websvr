package com.fanbo.test;

import org.junit.Test;

public class StringTest {

    @Test
    public void replaceAllTest(){
        String str = "abcdefgh";
        String result = str.replaceAll("%", "o");
        System.out.println("result: " + result);
    }

    @Test
    public void assertTest(){
        int a = 100;
        assert a == 200 : "不匹配";
    }

    @Test
    public void insertStrTest(){
        String result = new StringBuilder("201809").insert(4, "年").insert(7, "月").toString();
        System.out.println(result);
    }
}
