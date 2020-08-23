package com.fanbo.utils;

import java.io.*;

public class SerializationUtil {

    private static String OBJECT_FILE = "c:/object.bin";

    //序列化
    public static void serialize(Serializable s){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OBJECT_FILE));
            oos.writeObject(s);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //反序列化
    public static Object deserialize(){
        Object object = null;
        try {
            ObjectInput oi = new ObjectInputStream(new FileInputStream(OBJECT_FILE));
            object = oi.readObject();
            oi.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
