package com.rochatec.athena.util;

public class StringUtils {

	public static String onlyNumber(String text){
		String value = text != null && !text.equals("") ? text.replaceAll("[^0-9]", "") : "";
		return value.trim();
	}
	
	
}
