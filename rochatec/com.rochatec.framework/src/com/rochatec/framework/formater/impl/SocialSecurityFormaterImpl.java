package com.rochatec.framework.formater.impl;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.Message;
import com.rochatec.framework.util.TextFormat;

public class SocialSecurityFormaterImpl implements IFormarter{
	
	private TextFormat tf;
	
	public SocialSecurityFormaterImpl() {
		tf = new TextFormat();
	}
	
	@Override
	public String mask(String value) throws BadFormatException {
		switch (value.length()) {
		case 11:
			tf.setPattern(Message.getMessage("mask.socialsecurity"));
			break;
		case 14:
			tf.setPattern(Message.getMessage("mask.cnpj"));
			break;
		default:
			throw new BadFormatException();
		}
		return tf.mask(value);
	}

	@Override
	public String unMask(String value) {
		return tf.unMask(value);
	}

	@Override
	public String getMessage() {
		return Message.getMessage("mask.message.socialsecurity");
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
