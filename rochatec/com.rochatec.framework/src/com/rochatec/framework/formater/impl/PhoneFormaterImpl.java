package com.rochatec.framework.formater.impl;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.Message;
import com.rochatec.framework.util.TextFormat;

public class PhoneFormaterImpl implements IFormarter{
	
	private TextFormat tf;
	
	public PhoneFormaterImpl() {
		tf = new TextFormat();
	}
	
	@Override
	public String mask(String value) throws BadFormatException {
		switch (value.length()) {
		case 10:
			tf.setPattern(Message.getMessage("mask.phone.8"));
			return tf.mask(value);
		case 11:
			tf.setPattern(Message.getMessage("mask.phone.9"));
			return tf.mask(value);
		default:
			throw new BadFormatException();
		}
	}

	@Override
	public String unMask(String value) {
		return tf.unMask(value);
	}

	@Override
	public String getMessage() {
		return Message.getMessage("mask.message.phone");
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
