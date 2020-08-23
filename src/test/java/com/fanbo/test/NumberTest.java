package com.fanbo.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class NumberTest {

    @Test
    public void numberFormatTest(){
        NumberFormat nf = new DecimalFormat("#.##");
        String result = nf.format(154.5487852);
        String result2 = nf.format(154.5);
        System.out.println("result: " + result);
        System.out.println("result2: " + result2);
    }

    @Test
    public void bigDecimalTest(){
        BigDecimal bd = new BigDecimal(45.24);
        System.out.println(bd.toString());
    }

    @Test
    public void jdk7Test(){
        int money = 1_000_000_000;
        System.out.println("money: " + money);
    }

    @Test
    public void randomOfJdk8Test(){
        Random random = new Random();
        random.ints().limit(8).forEach(System.out::println);
        random.ints(5).forEach(o -> System.out.println("---:" + o));
        System.out.println("random boolean: " + random.nextBoolean());
    }
}
