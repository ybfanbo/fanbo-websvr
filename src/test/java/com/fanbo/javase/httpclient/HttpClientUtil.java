package com.fanbo.javase.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpClientUtil {
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	public static String postHttp(String uid, String userName, String phone,
			String measureTime, String highPressure, String lowPressure,
			String heartRate, String pressureLevel, String suggestion, String sn) {

		String responseMsg = "提交成功";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		String url = "http://www.genekang.com:8888/genekang/kkData.action";

		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);

		// 3.把参数值放入到PostMethod对象中
		postMethod.addParameter("uid", uid);
		postMethod.addParameter("userName", userName);
		postMethod.addParameter("phone", phone);
		postMethod.addParameter("measureTime", measureTime);
		postMethod.addParameter("highPressure", highPressure);
		postMethod.addParameter("lowPressure", lowPressure);
		postMethod.addParameter("heartRate", heartRate);
		postMethod.addParameter("pressureLevel", pressureLevel);
		postMethod.addParameter("suggestion", suggestion);
		postMethod.addParameter("sn", sn);

		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200
			// 5.读取内容
			String result = "";
			BufferedReader in = new BufferedReader(
                    new InputStreamReader(postMethod.getResponseBodyAsStream(),Charset.forName("UTF-8")));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            responseMsg = result;
            //System.out.println(result);
			//responseMsg = responseBodyAsString.trim();
            //log.info(responseMsg);           
			// 6.处理返回的内容
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	public static void main(String[] args) {
		String uid = "888899";
		String userName = "fd";
		String phone = "66";
		String measureTime = "1423623679425";
		String highPressure = "88";
		String lowPressure = "55";
		String heartRate = "33";
		String pressureLevel = "fd";
		String suggestion = "";
		String sn = "gfdg";

		// post
		System.out.println("post方式调用http接口\n"
				+ postHttp(uid, userName, phone, measureTime, highPressure,
						lowPressure, heartRate, pressureLevel, suggestion, sn));
	}

}
