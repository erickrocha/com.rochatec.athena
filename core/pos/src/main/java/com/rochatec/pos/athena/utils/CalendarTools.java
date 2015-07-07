package com.rochatec.pos.athena.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by epr on 07/07/15.
 */
public class CalendarTools {

    public static Calendar getNow(){
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar getFirstHour(){
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,01);
        calendar.set(Calendar.MILLISECOND,00);
        return calendar;
    }
}
