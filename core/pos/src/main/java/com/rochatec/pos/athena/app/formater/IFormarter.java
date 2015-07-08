package com.rochatec.pos.athena.app.formater;

import com.rochatec.pos.athena.app.exception.BadFormatException;

public interface IFormarter {

	public String mask(String value) throws BadFormatException;
	
	public String mask(Object value) throws BadFormatException;
	
	public String unMask(String value);
	
	public String getMessage();
	
	public Object parse(String value) throws BadFormatException;
	
}
