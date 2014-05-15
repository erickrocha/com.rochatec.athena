package com.rochatec.framework.math;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;

public class None implements IFormarter{

	@Override
	public String mask(String value) throws BadFormatException {		
		return value;
	}

	@Override
	public String mask(Object value) throws BadFormatException {		
		return value.toString();
	}

	@Override
	public String unMask(String value) {		
		return value;
	}

	@Override
	public String getMessage() {
		return "";
	}

	@Override
	public Object parse(String value) throws BadFormatException {
		return null;
	}

}
