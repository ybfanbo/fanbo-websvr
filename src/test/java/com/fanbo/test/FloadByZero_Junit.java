package com.fanbo.test;

import org.junit.Test;

public class FloadByZero_Junit {

	@Test
	public void floadBy0Test(){
		
		float a = 0.12f;
		int b = 0;
		
		float c = a / b;
		
		System.out.println(c);   //分子为单精度，分母可以为0
		
	}
	
	@Test
	public void floadBy0Test2(){
		
		int a = 3;
		float b = 0;
		
		float c = a / b;
		
		System.out.println(c);   //分母为单精度，分母可以为0
		
	}
	
	
}
