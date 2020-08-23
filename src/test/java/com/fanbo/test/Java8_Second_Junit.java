package com.fanbo.test;

import com.fanbo.javase.domain.Phone;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by fanbo on 2017/2/2.
 */
public class Java8_Second_Junit {

    List<Phone> phones;
    @Before
    public void befort(){
        phones = new ArrayList<>();
        phones.add(new Phone(1,"iphone", "5.5", new BigDecimal(5288.00)));
        phones.add(new Phone(2,"iphone", "4.7", new BigDecimal(4288.00)));
        phones.add(new Phone(3,"snmsung", "5.5", new BigDecimal(3288)));
        phones.add(new Phone(4,"nokia", "4.0", new BigDecimal(1689.00)));
    }

    @Test
    public void maxAndMinTest(){
        Phone phone;
        phone = phones.parallelStream().min(Comparator.comparing(Phone::getSize)).get();
        System.out.println(phone);
        phone = phones.parallelStream().max(Comparator.comparing(Phone::getPrice)).get();
        System.out.println(phone);
    }

    @Test
    public void toMap(){
        Map<Integer, Phone> map = phones.stream().collect(Collectors.toMap(Phone::getId, Function.identity()));
        Map<Integer, String> map1 = phones.stream().collect(Collectors.toMap(Phone::getId, Phone::getName));
        System.out.println(map1.keySet().stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    @Test
    public void sortTest(){
        phones = phones.stream().sorted(Comparator.comparing(Phone::getSize).reversed()).collect(Collectors.toList());
        phones.forEach(o -> {
            System.out.println(o);
        });
    }

    @Test
    public void range(){
        IntStream.range(1, 10).forEach(System.out::println);
    }

    @Test
    public void toArray(){
        String[] names = phones.stream().map(o -> o.getName()).toArray(String[]::new);
        System.out.println(StringUtils.join(names, ","));
    }

    @Test
    public void reduce(){
        int sum = phones.stream().map(o -> o.getId()).reduce(5, (a,b) -> a+b);
        System.out.println(sum);
    }

    @Test
    public void limit(){
        phones.clear();
        for (int i = 10; i < 200; i++) {
            phones.add(new Phone(i, "name"+ i, "5."+i, new BigDecimal(i)));
        }

        phones = phones.stream().limit(20).skip(5).collect(Collectors.toList());

        phones.stream().forEach(o -> {
            System.out.println(o.toString());
        });
    }

    @Test
    public void foreach(){
        phones.clear();
        for (int i = 1; i < 2000000; i++) {
            phones.add(new Phone(i, "name"+ i, "5."+i, new BigDecimal(i)));
        }
        final int[] sum = {0};
        long startTime = System.currentTimeMillis();
        phones.forEach(o -> {
            sum[0] = sum[0] + o.getId();
        });
        long endTime = System.currentTimeMillis();
        System.out.println(sum + "耗时：" + (endTime - startTime));
    }

}
