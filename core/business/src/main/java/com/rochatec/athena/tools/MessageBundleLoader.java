package com.rochatec.athena.tools;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundleLoader {
	public static final String MESSAGE_PATH = "com.rochatec.metallurgical.resources.errors";

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
			ResourceBundle messages = (ResourceBundle) messageBundles.get(locale.toString());
			if (messages == null) {
				messages = ResourceBundle.getBundle(MESSAGE_PATH, locale);
				messageBundles.put(locale.toString(), messages);
			}
			return messages.getString(key);
		}
		catch (Exception e) {
			return key;
		}
	}
}
