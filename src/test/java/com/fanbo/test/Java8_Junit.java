package com.fanbo.test;

import com.fanbo.javase.domain.Person;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by fanbo on 2016/10/1.
 */
public class Java8_Junit {

    private List<String> strs;
    private List<Person> ps;

    @Before
    public void before(){

        strs = Arrays.asList("111","222","333");
        ps = Lists.newArrayList();
        ps.add(new Person("fanbo", 20));
        ps.add(new Person("fanyin", 25));
        ps.add(new Person("fanbin", 35));
        ps.add(new Person("fankun", 33));
        ps.add(new Person("fanchen", 12));
        ps.add(new Person("fanhong", 99));
        ps.add(new Person("fanbo", 88));
        ps.add(new Person("fanbo", 10));
        ps.add(new Person("fanfan", 11));
    }

    @Test
    public void hashTest(){
        ps.stream().forEach(p -> {
            System.out.println(p.hashCode());
        });
    }

    @Test
    public void distinctTest(){
        ps.stream().distinct().forEach(p -> {
            System.out.println(p.hashCode());
        });
    }

    @Test
    public void t1(){

        //过滤
        List<Object> ss = strs.stream().filter(o -> o.equals("111")).collect(Collectors.toList());
        //取最大值
        String max = strs.stream().max(Comparator.comparing(o -> o.toString())).get();
        //取最小值
        Person min = ps.stream().min(Comparator.comparing(Person::getAge)).get();
        //将list转成map
        Map<String, Person> map = ps.stream().collect(Collectors.toMap(Person::getName, Function.identity()));

        String bs = StringUtils.join(strs, ",");
        String es = StringUtils.join(ss, ",");

        System.out.println(bs);
        System.out.println(es);
        System.out.println("max:" + max);
        System.out.println("min:" + min.toString());
        System.out.println("map-size:" + map.size());
        System.out.println("map-value:" + map.get("fanyin"));


    }


    //Set集合
    @Test
    public void setTest(){
        Set<String> set = new HashSet<String>();
        set.add("111");
        set.add("111");
        set.add("222");
        set.add("333");
        set.add("444");
        set.add("555");
        set.add("666");
        set.add(null);

        System.out.println(set.size());
        for (String str : set) {
            if (StringUtils.isNotEmpty(str)) {
                System.out.println(str.hashCode());
            }
        }

        Set<Person> ps = new HashSet<Person>();
        ps.add(new Person("fanbo", 22));
        ps.add(new Person("fanyin", 22));
        ps.add(new Person("fanbo", 22));
        ps.add(new Person("fanbo", 22));
        System.out.println(ps.size());
        for (Person p : ps) {
            System.out.println(p.getName() + p.getAge());
        }
    }

    //过滤、排序、取ID  （java8排序、java7排序）
    @Test
    public void filterTest(){

        List<Person> persons = new ArrayList<Person>();
        for(Person p : ps){
            if(!"fanbo".equals(p.getName())){
                persons.add(p);
            }
        }

        Collections.sort(persons, new Comparator<Person>(){
            public int compare(Person o1, Person o2) {
                return o2.getAge().compareTo(o1.getAge());
            }});

        List<Integer> result = new ArrayList<Integer>();
        for(Person p : persons){
            result.add(p.getAge());
        }

        //过滤、排序、取值
        List<Integer> result2 = ps.stream().filter(o -> !"fanbo".equals(o.getName())).sorted(Comparator.comparing(Person::getAge).reversed()).map(Person::getAge).collect(Collectors.toList());
        List<Integer> result3 = ps.parallelStream().filter(o -> !"fanbo".equals(o.getName())).sorted(Comparator.comparing(Person::getAge)).map(Person::getAge).collect(Collectors.toList());
        //过滤、取值、取年龄的和
        Integer sum = ps.stream().filter(o -> !"fanbo".equals(o.getName())).mapToInt(Person::getAge).sum();
        //取最大值
        Person maxAge = ps.stream().filter(o -> !"fanbo".equals(o.getName())).max(Comparator.comparing(Person::getAge)).get();

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println("年龄和:" + sum);
        System.out.println("年龄最大的人:" + maxAge.getName() + maxAge.getAge());

    }

    //forEach
    @Test
    public void forEachTest(){

        List<String> names = new ArrayList<String>();
        ps.stream().forEach(o -> names.add(o.getName()));
        names.stream().forEach(System.out::println);

    }

    //toArray
    @Test
    public void lambda(){

        List<String> strs = Lists.newArrayList();

        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");

        //集合转字符串的新旧形式
        String[] ss = strs.toArray(new String[strs.size()]);
        String[] sss = strs.stream().toArray(String[]::new);

        System.out.println(StringUtils.join(ss));
        System.out.println(StringUtils.join(sss));

        String[] result = ps.stream().map(o -> o.getName() + o.getAge()).toArray(String[]::new);

        System.out.println(StringUtils.join(result, "\r\n"));
    }

    //toMap
    @Test
    public void toMap(){

        Map<String, Person> map = ps.stream().filter(o -> !"fanbo".equals(o.getName())).collect(Collectors.toMap(Person::getName, Function.identity()));

        Set<String> names = map.keySet();

        names.stream().forEach(o -> System.out.println("key:" + o + "-- value:" + map.get(o)));
    }

    //toArrayList
    @Test
    public void toArrayList(){

        ArrayList<Person> al = ps.stream().filter(o -> !"fanbo".equals(o.getName())).collect(Collectors.toCollection(ArrayList::new));

        al.stream().forEach(System.out::println);

    }

    //distinct
    @Test
    public void distinct(){

        //List<String> strs = Arrays.asList(new String[]{"111", "222", "333", "222"});
        List<String> strs = Arrays.asList("111", "222", "333", "222");

        strs.stream().distinct().forEach(System.out::println);

    }

    //joining
    @Test
    public void joingTest(){

        String result = ps.stream().map(o -> o.getName() + o.getAge()).collect(Collectors.joining(";")).toString();

        System.out.println(result);
    }

    //大小写转换
    @Test
    public void toUpperCase(){

        List<String> strs = Arrays.asList("fdsfa", "fdsfd", "12edee");

        strs = strs.stream().map(String::toUpperCase).collect(Collectors.toList());

        strs.stream().forEach(System.out::println);
    }


    //peek  操作了元素，再将操作后的集合重新生成stream
    @Test
    public void peekTest(){

        List<Integer> ages = new ArrayList<Integer>();

        Integer max = ps.stream().filter(o -> !"fanbo".equals(o.getName()))
                .peek(System.out::println).mapToInt(Person::getAge)
                .peek(ages::add).max().getAsInt();

        System.out.println(ages);
        System.out.println(max);
    }

    //数据转成流，再操作
    @Test
    public void arrStreamTest(){
        String[] strs = new String[]{"a","b","c"};
        List<String> ss = Stream.of(strs).filter(o -> !"b".equals(o)).collect(Collectors.toList());

        ss.stream().forEach(System.out::println);
    }

    @Test
    public void numStreamTest(){
        IntStream stream = IntStream.of(1,2,3,4,5,7,8).filter(o -> o % 2 == 0);
        stream.forEach(System.out::println);

        IntStream.range(1, 100).filter(o -> o % 3 == 0).forEach(System.out::println);

        IntStream.rangeClosed(0, 100).filter(o -> o%4 == 0).forEach(System.out::println);
    }

    @Test
    public void toOtherData(){
        Stream<String> stream = Stream.of("a", "b", "c", "d", "e");
        //String[] ss = stream.toArray(String[]::new);
        //List<String> list = stream.collect(Collectors.toList());
        //ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        Stack stack = stream.collect(Collectors.toCollection(Stack::new));
        //String string = stream.collect(Collectors.joining());

        stack.add("f");
        stack.remove("b");
        stack.stream().forEach(System.out::println);

    }

    @Test
    public void toUpperTest2(){
        Stream.of("fan", "bo", "java").map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void flatMapTest(){
        Stream<List<String>> stream = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d", "e", "f"), Arrays.asList("gg", "hh", "ii"));

        List<String> lists = stream.flatMap(o -> o.stream()).collect(Collectors.toList());

        lists.forEach(o -> System.out.println(o));
    }

    @Test
    public void parallelStreamTest(){
        List<String> ss = Arrays.asList("a", "b", "c", "d", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u");
        //ss.parallelStream().forEach(System.out::println);
        ss.stream().forEach(System.out::println);
    }





}
