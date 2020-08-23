package com.fanbo.test;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by fanbo on 2016/10/1.
 */
public class Date_LocalDate_Junit {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    public void dateTest(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        System.out.println(date);
    }

    @Test
    public void dateTest2(){
        Long unix = 1465891481l * 1000;

        Date date = new Date(unix);

        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String d = df.format(date);
        System.out.println(d);
    }

    @Test
    public void localDateTest(){
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

    }

    @Test
    public void localDateTimeFormatTest(){

        System.out.println("localDateTime:" + localDateTime);

        String ldt = localDateTime.toString();
        System.out.println("ldt:" + ldt);

    }

    @Test
    public void localDateTimeTest(){

        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        String date1 = localDateTime.format(DateTimeFormatter.ISO_DATE);
        String date2 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String date3 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String date4 = localDateTime.format(DateTimeFormatter.ISO_TIME);
        String date5 = localDateTime.format(DateTimeFormatter.ISO_WEEK_DATE);
        String date6 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("date:" + date);
        System.out.println("date1:" + date1);
        System.out.println("date2:" + date2);
        System.out.println("date3:" + date3);
        System.out.println("date4:" + date4);
        System.out.println("date5:" + date5);
        System.out.println("date6:" + date6);

        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        System.out.println("毫秒：" + instant.toEpochMilli());
    }

    @Test
    public void instTest(){
        Instant instant = Instant.now();
        System.out.println("毫秒：" + instant.toEpochMilli());
        System.out.println("秒：" + instant.getEpochSecond());
    }

}
