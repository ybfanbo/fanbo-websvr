package com.fanbo.test;

import org.junit.Test;

import java.text.MessageFormat;

public class MessageFormat_Junit {
	
	@Test
	public void messageFormate1(){
		
		String message = "{0}{1}{2}{3}";
		
		Object[] array = new Object[]{"A","B","C","D","E"};
		
		
		String value = MessageFormat.format(message, array);
		
		System.out.println(value);
	}
	
	@Test
	public void messageFormate2(){
		
		String message = "I am not 'a' {0}. ";
		
		Object[] array = new Object[]{"teacher"};
		
		String value = MessageFormat.format(message, array);
		
		System.out.println(value);
	}
	
	@Test
	public void messageFormate3(){
		
		String message = "I have {0,number,#.#} dollers.";
		
		Object[] array = new Object[]{new Double(30.258)};
		
		String value = MessageFormat.format(message, array);
		
		System.out.println(value);
		
	}
	
	@Test
	public void messageFormate4(){
		
		String message = "He is a {0}, and I have {1, number, #.##} dollers.";
		
		String value = MessageFormat.format(message, "pig", 55.23);
		
		System.out.println(value);
		
		
		
		
	}
 	
	
	
}
