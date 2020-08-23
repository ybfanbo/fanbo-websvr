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

public class HttpClientTest {
	private static final Log log = LogFactory.getLog(HttpClientTest.class);

	public static String postHttp(String username, String password) {

		String responseMsg = "提交成功";
		
		
		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		String url = "http://www.genekang.com/gene/index.php/Api/login";

		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);

		// 3.把参数值放入到PostMethod对象中
		postMethod.addParameter("username", username);
		postMethod.addParameter("password", password);

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
		String username = "ybfanbo";
		String password = "111111";

		// post
		System.out.println("post方式调用http接口\n"
				+ postHttp(username, password));
	}

}
