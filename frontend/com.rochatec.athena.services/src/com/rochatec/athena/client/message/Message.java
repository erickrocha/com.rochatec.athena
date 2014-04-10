package com.rochatec.athena.client.message;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

	private static final String BUNDLE_NAME = "com.rochatec.metallurgical.resource..i18n";
	private static ResourceBundle rb = null;

	public static void setLocale(Locale locale) {
		try {
			rb = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		} catch (Exception e) {
			rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
		}
	}

	public static String getString(String key) {
		try {
			String keyValue = new String(rb.getString(key).getBytes("ISO-8859-1"), "ISO-8859-1");
			return keyValue;
		} catch (Exception e) {
			return key;
		}
	}
}
