package com.fanbo.javase.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpClientWeiXin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getHttp());
	}
	
	public static String getHttp() {

		String responseMsg = "提交成功";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		String url = "http://developer.bmwm.cn/glaze-wx/api/v1/ticket/oyQp8s6OGLN1Qa7Y5yXEj92la0x8";

		// 2.构造PostMethod的实例
		GetMethod getMethod = new GetMethod(url);

		// 3.把参数值放入到PostMethod对象中
		//postMethod.addParameter("username", username);

		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(getMethod);// 200
			// 5.读取内容
			String result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					getMethod.getResponseBodyAsStream(),
					Charset.forName("UTF-8")));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			responseMsg = result;
			// 6.处理返回的内容
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}


}
