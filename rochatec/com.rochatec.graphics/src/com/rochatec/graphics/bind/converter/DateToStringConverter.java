package com.rochatec.graphics.bind.converter;

import java.text.DateFormat;
import java.util.Calendar;

import org.eclipse.core.databinding.conversion.Converter;

public class DateToStringConverter extends Converter{

	public DateToStringConverter() {
		super(Calendar.class,String.class);		
	}

	@Override
	public Object convert(Object fromObject) {
		Calendar calendar = (Calendar)fromObject;
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(calendar.getTime());
	}

}
