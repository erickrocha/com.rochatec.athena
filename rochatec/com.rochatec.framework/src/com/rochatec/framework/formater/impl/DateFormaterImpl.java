package com.rochatec.framework.formater.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;

public class DateFormaterImpl implements IFormarter{
	
	private DateFormat format;
	
	public DateFormaterImpl() {
		format = DateFormat.getDateInstance(DateFormat.MEDIUM);
	}
	
	@Override
	public String mask(String value) throws BadFormatException {
		return format.format(value);
	}

	@Override
	public String unMask(String value) {
		return null;
	}

	@Override
	public String getMessage() {
		return "";
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		if (value instanceof Date){
			Date date = (Date)value;
			return format.format(date);
		}
		if (value instanceof Calendar){
			Calendar calendar = (Calendar)value;
			return format.format(calendar.getTime());
		}
		return value.toString();
	}

	@Override
	public Object parse(String value) throws BadFormatException {
		try{
			Date date = format.parse(value);
			return date;
		}catch (ParseException e) {
			throw new BadFormatException();
		}
	}

}
