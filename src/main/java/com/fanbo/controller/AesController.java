package com.fanbo.controller;

import com.fanbo.utils.AesUtil;
import com.fanbo.utils.AesUtil_Zshield;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AesController {

    //加密
    @RequestMapping(value = "/aesEncrypt", method = RequestMethod.GET)
    public String aesEncrypt(){

//        String result = AesUtil.aesDecrypt("abc");
//
//        return result;
        return null;
    }

    //解密
    @RequestMapping(value = "/aesDecrypt", method = RequestMethod.GET)
    public String aesDecrypt(){

//        String result = AesUtil.aesDecrypt("UU05vv3VPS2Com8NrNVfww==");
//
//        return result;
        return null;
    }


    //志翔加密
    @RequestMapping(value = "/zshield/aesEncrypt", method = RequestMethod.GET)
    public String zshieldEncrypt(){

        AesUtil_Zshield az = new AesUtil_Zshield(128, 10000);

//        String result = az.encrypt("abc_zshield");
//
//        return result;
        return null;
    }

    //志翔解密
    @RequestMapping(value = "/zshield/aesDecrypt", method = RequestMethod.GET)
    public String zsheldDecrypt(){

        AesUtil_Zshield az = new AesUtil_Zshield(128, 10000);

//        String result = az.decrypt("5qXZZuGSi0O6a+IM3Tm3XQ==");
//
//        return result;
        return null;
    }
}
