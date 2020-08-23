package com.fanbo.test;

import org.junit.Test;

import java.util.Base64;


//结论：java.util.Base64、org.apache.commons.codec.binary.Base64两种工具的转码和解码都一样的结果
public class Base64_Junit {
	
	@Test //java.util.base64转码  、    apache.commons.base64转码
	public void java_util_Base64_encode(){
		
		String str = "飞翔的企鹅";
		
		String javaUtil = Base64.getEncoder().encodeToString(str.getBytes());		
		System.out.println("javaUtil转码：" + javaUtil);
				
		String apacheCommons = org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes()).toString();
		System.out.println("apacheCommons转码：" + apacheCommons);
	}


	@Test //java.util.base64解码    、   apache.commons.base64解码
	public void java_util_Base64_decode(){
		
		String base64 = "6YeR55CJ55KD5b6u5L+h5rWL6K+V";
		
		String javaUtil = new String(Base64.getDecoder().decode(base64));		
		System.out.println("javaUtil解码：" + javaUtil);
		
		String apacheCommons = new String(org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes()));
		System.out.println("apacheCommons解码：" + apacheCommons);
	}	
	

	
	

}
