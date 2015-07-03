package com.rochatec.pos.athena.utils;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by epr on 01/07/15.
 */

public class Messages {

    public static final String MESSAGE_PATH = "com.rochatec.pos.athena.resource.message";

    private static Logger LOGGER = Logger.getLogger(Messages.class);

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
            ResourceBundle messages = messageBundles.get(locale.toString());
            if (messages == null) {
                messages = ResourceBundle.getBundle(MESSAGE_PATH, locale);
                messageBundles.put(locale.toString(), messages);
            }
            return messages.getString(key);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            return key;
        }
    }

    public static String getMessage(String key, String... strings) {
        String message = getMessage(key);
        for (int i = 0; i < strings.length; i++) {
            message = message.replace("{" + i + "}", strings[0]);
        }
        return message;
    }

    public static String getMessage(String key, Object... objects) {
        String message = getMessage(key);
        for (int i = 0; i < objects.length; i++) {
            message = message.replace("{" + i + "}", objects[0].toString());
        }
        return message;
    }
}
