package com.rochatec.graphics.bind.value;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;

import com.rochatec.graphics.event.CalendarEvent;
import com.rochatec.graphics.gui.DateField;
import com.rochatec.graphics.listener.ICalendarChangedListener;

public class DataFieldObservableValue extends AbstractObservableValue{

	private DateField dateField;
	private ICalendarChangedListener listener = new ICalendarChangedListener() {
		
		@Override
		public void changed(CalendarEvent event) {
			
			
		}
	};
	
	public DataFieldObservableValue(DateField dateField) {
		this.dateField =  dateField;
		this.dateField.addICalendarChangedListener(listener);
	}
	
	
	@Override
	public Object getValueType() {		
		return Calendar.class;
	}
	
	@Override
	protected void doSetValue(Object value) {
		if (value instanceof Calendar){
			dateField.setValue((Calendar)value);
		}else if (value instanceof Date){
			dateField.setValue((Date)value);
		}else if (value instanceof String){
			
		}		
	}

	@Override
	protected Object doGetValue() {
		return dateField.getValue();
	}
	
	public synchronized void dispose(){
		this.dateField.removeICalendarChangedListener(listener);
	}

}
