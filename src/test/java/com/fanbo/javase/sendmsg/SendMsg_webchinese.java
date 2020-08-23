package com.fanbo.javase.sendmsg;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;


//中国网建的短信平台  官网：http://sms.webchinese.cn/default.shtml
public class SendMsg_webchinese {

	public static void main(String[] args) throws HttpException, IOException {  
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");  
        post.addRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=gbk");		// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("Uid", "ybfanbo"),	// 注册的用户名  
                new NameValuePair("Key", "fe4593f9358a626a94ca"),		// 注册成功后，登录网站后得到的密钥  
                new NameValuePair("smsMob", "17090073082"),				// 手机号码  
                new NameValuePair("smsText", "SMS短信发送测试！") };			// 短信内容  
        post.setRequestBody(data);  
  
        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:" + statusCode);  
        for (Header h : headers) {  
            System.out.println("---" + h.toString());  
        }  
        String result = new String(post.getResponseBodyAsString().getBytes(  
                "gbk"));  
        System.out.println(result);  
  
    }  

}
