package com.rochatec.graphics.bind.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.databinding.conversion.IConverter;

public class StringToDateConverter implements IConverter{

	@Override
	public Object getFromType() {
		return String.class;
	}

	@Override
	public Object getToType() {
		return Calendar.class;
	}

	@Override
	public Object convert(Object fromObject) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		try {
			Date date = df.parse(fromObject.toString());
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
