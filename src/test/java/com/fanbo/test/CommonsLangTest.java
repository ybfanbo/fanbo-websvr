package com.fanbo.test;

import com.fanbo.utils.StringUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

public class CommonsLangTest {

    @Test
    public void arrayTest(){
        int[] arr = new int[]{1,2,3,4,5,6};
        int[] newArr = ArrayUtils.add(arr, 7);  //ArrayUtils
        System.out.println("arr: " + StringUtil.getJson(arr));
        System.out.println("newArr: " + StringUtil.getJson(newArr));
    }

    @Test
    public void stringTest(){
        boolean isEmpty = StringUtils.isEmpty("test");   //StringUtils
        System.out.println("isEmpty: " + isEmpty);
    }

    @Test
    public void dateFormatTest(){
        String nowDate = DateFormatUtils.format(Instant.now().toEpochMilli(), "yyyy-MM-dd HH:mm:ss");   //DateFormatUtils
        System.out.println("nowDate: " + nowDate);
    }

    @Test
    public void dateTest(){
        Date date1 = new Date();
        Date date2 = new Date();
        boolean isSameDate = DateUtils.isSameDay(date1, date2);   //DateUtils
        System.out.println("isSameDate: " + isSameDate);
    }

}
