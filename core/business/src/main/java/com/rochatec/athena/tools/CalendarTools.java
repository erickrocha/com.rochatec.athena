package com.rochatec.athena.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTools {
	
	public static Calendar getToday(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}
	
}
