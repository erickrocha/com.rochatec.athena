package com.rochatec.athena.util;

import java.io.IOException;

import org.eclipse.jface.preference.PreferenceStore;

public class Enviroment {
	
private static PreferenceStore preferenceStore;
	
	public static void load(String path) throws IOException{
		preferenceStore = new PreferenceStore(path);
		preferenceStore.load();
	}
	
	public static void setDefaults(){
		
	}
	
	public static String getString(String key){
		return preferenceStore.getString(key);
	}
	
	public static int getInt(String key){
		return preferenceStore.getInt(key);
	}
	
	public static boolean getBoolean(String key){
		return preferenceStore.getBoolean(key);
	}
	
	public static float getFloat(String key){
		return preferenceStore.getFloat(key);
	}
	
	public static void setValue(String key,String value){
		preferenceStore.setValue(key, value);
	}
	
	public static void setValue(String key,boolean value){
		preferenceStore.setValue(key, value);
	}
	
	public static void setValue(String key,int value){
		preferenceStore.setValue(key, value);
	}
	
	public static void setValue(String key,float value){
		preferenceStore.setValue(key, value);
	}
	
	public static void save() throws IOException{
		preferenceStore.save();
	}
	
	public static String getAppPath(){
		return System.getProperty("user.dir");
	}
	
}
