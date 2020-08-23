package com.fanbo.test;

import org.junit.Test;

public class CompareVersion_Junit {

	@Test
	public void versionTest(){
		String v1 = "1.3.0";
		String v2 = "1.3.0";
		
		System.out.println(v1.compareTo(v2));
		//System.out.println(v2.compareTo(v1));
	}
	
	
}
