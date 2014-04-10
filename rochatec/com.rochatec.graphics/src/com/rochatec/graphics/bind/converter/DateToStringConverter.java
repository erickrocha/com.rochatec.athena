package com.rochatec.graphics.bind.converter;

import java.text.DateFormat;
import java.util.Calendar;

import org.eclipse.core.databinding.conversion.IConverter;

public class DateToStringConverter implements IConverter{

	@Override
	public Object getFromType() {		
		return Calendar.class;
	}

	@Override
	public Object getToType() {
		return String.class;
	}

	@Override
	public Object convert(Object fromObject) {
		Calendar calendar = (Calendar)fromObject;
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(calendar.getTime());
	}

}
