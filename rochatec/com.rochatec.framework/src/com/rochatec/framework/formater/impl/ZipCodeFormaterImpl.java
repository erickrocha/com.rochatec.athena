package com.rochatec.framework.formater.impl;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.Message;
import com.rochatec.framework.util.TextFormat;

public class ZipCodeFormaterImpl implements IFormarter{
	
	private TextFormat tf;
	
	public ZipCodeFormaterImpl() {
		tf = new TextFormat(Message.getMessage("mask.zipcode"));
	}
	
	@Override
	public String mask(String value) throws BadFormatException {
		if (value.length() == 8){
			return tf.mask(value);
		}else{
			throw new BadFormatException();
		}
	}

	@Override
	public String unMask(String value) {
		return tf.unMask(value);
	}

	@Override
	public String getMessage() {
		return Message.getMessage("mask.message.zipcode");
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		return tf.mask(value.toString());
	}

	@Override
	public Object parse(String value) throws BadFormatException {
		return value;
	}

}
