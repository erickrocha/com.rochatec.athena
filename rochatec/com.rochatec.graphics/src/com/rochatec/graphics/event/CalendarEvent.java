package com.rochatec.graphics.event;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;

public class CalendarEvent extends TypedEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8158491791724436719L;
	public Calendar calendar;
	public Date date;
	
	public CalendarEvent(Event event) {
		super(event);		
	}
	
	

}
