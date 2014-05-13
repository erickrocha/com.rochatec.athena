package com.rochatec.framework.model;

import org.eclipse.swt.graphics.Image;

public class Property implements Comparable<Property>{
	
	private String key;
	private String value;
	private int index;
	private Image image;
	
	public Property(String key,String value,int index,Image image) {
		this.key = key;
		this.value = value;
		this.index = index;
		this.image = image;
	}
	
	public Property(String key,String value,int index) {
		this(key,value,index,null);		
	}
	
	public Property() {
		
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public Image getImage() {
		if (image != null)
			return image;
		return null;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int compareTo(Property o) {
		return key.compareTo(o.key);
	}				
	
}