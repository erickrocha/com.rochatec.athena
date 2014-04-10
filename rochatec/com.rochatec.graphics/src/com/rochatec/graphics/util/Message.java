package com.rochatec.graphics.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Message {
	
	public static final String URL_PPOPERTIES = "com.rochatec.graphics.resource.message";
	
	private static HashMap<String,ResourceBundle> messageBundles = new HashMap<String, ResourceBundle>();
	
	public static String getMessage(String key) {
		if (key == null) {
			return null;
		}
		try {
			Locale locale = Locale.getDefault();
			if (locale == null) {
				locale = Locale.ENGLISH;
			}
			ResourceBundle messages = (ResourceBundle) messageBundles
					.get(locale.toString());
			if (messages == null) {
				messages = ResourceBundle.getBundle(URL_PPOPERTIES, locale);
				messageBundles.put(locale.toString(), messages);
			}
			return messages.getString(key);
		}
		catch (Exception e) {
			return key;
		}
	}
	
	public static String getMessageWithParams(String key,String... strings){
		String message = getMessage(key);
		for (int i = 0; i < strings.length;i++){			
			message = message.replace("{"+i+"}",strings[0]);
		}
		return message;
	}
	
	public static String getMessageWithParams(String key,Object... objects){
		String message = getMessage(key);
		for (int i = 0; i < objects.length;i++){			
			message = message.replace("{"+i+"}",objects[0].toString());
		}
		return message;
	}
}
