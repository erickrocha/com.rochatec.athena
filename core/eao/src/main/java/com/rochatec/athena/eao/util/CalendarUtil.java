package com.rochatec.athena.eao.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {

	public static Calendar getLastHourDay(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,00);
		return calendar;
	}
	
	public static Calendar getFirstHourDay(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,00);
		return calendar;
	}
	
	public static Calendar getLastHourToday(){
		return getLastHourDay(new Date());
	}
	
	public static Calendar getFirstHourToday(){
		return getFirstHourDay(new Date());
	}
}
