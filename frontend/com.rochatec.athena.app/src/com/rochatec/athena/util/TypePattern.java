package com.rochatec.athena.util;

public enum TypePattern {

	NONE,SOCIAL_SECURITY,ZIPCODE,PHONE,DATE,BIGDECIMAL;
	
	public static TypePattern getTypePattern(int index){
		return values()[index];
	}
	
}
