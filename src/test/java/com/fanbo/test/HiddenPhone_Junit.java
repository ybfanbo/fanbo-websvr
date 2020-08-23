package com.fanbo.test;

public class HiddenPhone_Junit {
	public static void main(String[] args) {
		String phone = "13340650367";
		int leng = phone.length();
		if(phone.length() < 7){
			System.out.println("******");
		}else{
		
			String result = phone.substring(0,  3) + "****" + phone.substring(7, phone.length());
		
			System.out.println(result);
		}
	}
}
