package com.fanbo.utils;


public class PhoneUtils {

    //隐藏手机号中间4位，如：138****2988
    public static String hiddenPhone(String phone){

        return phone != null && phone.length() == 11 ? phone.substring(0, 3) + "****" + phone.substring(7, phone.length()) : "****";

    }


}
