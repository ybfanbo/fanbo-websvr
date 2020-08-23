package com.fanbo.test;

import com.fanbo.manager.MyClassLoader;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    @Test
    public void bootstrapClassLoader(){
        String path = System.getProperty("sun.boot.class.path");
        System.out.println("result: " + path);
    }

    @Test
    public void extentionClassLoader(){
        String path = System.getProperty("java.ext.dirs");
        System.out.println("result: " + path);
    }

    @Test
    public void classLoaderTest(){
        String currentClassLoader = ClassLoaderTest.class.getClassLoader().toString();
        String currentClassLoaderParent = ClassLoaderTest.class.getClassLoader().getParent().toString();
//        String currentClassLoaderParentParent = ClassLoaderTest.class.getClassLoader().getParent().getParent().toString();

        System.out.println("currentClassLoader: " + currentClassLoader); //appClassLoader
        System.out.println("currentClassLoaderParent: " + currentClassLoaderParent);
//        System.out.println("currentClassLoaderParentParent: " + currentClassLoaderParentParent);
    }

    @Test
    public void myClassLoaderTest(){
        //自定义类加载器的加载路径
        MyClassLoader myClassLoader = new MyClassLoader("D:\\project-my\\java-test-websvr\\src\\main\\java\\com\\fanbo\\domain");
        try {
            Class clazz = myClassLoader.loadClass("com.fanbo.domain.ClassLoaderBean");

            if (clazz != null){
                Object object = clazz.newInstance();
                Method method = clazz.getMethod("run", null);
                method.invoke(object, null);  //通过反射执行ClassLoaderBean中的run方法
//                System.out.println("classLoader name is: " + clazz.getClassLoader().getName());
                System.out.println("classLoader toString is: " + clazz.getClassLoader().toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException ie){
            ie.printStackTrace();
        } catch (IllegalAccessException iae){
            iae.printStackTrace();
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
        } catch (InvocationTargetException iie){
            iie.printStackTrace();
        }
    }


}
