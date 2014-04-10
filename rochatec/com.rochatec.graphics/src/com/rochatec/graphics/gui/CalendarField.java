package com.rochatec.graphics.gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.event.CalendarEvent;
import com.rochatec.graphics.listener.ICalendarChangedListener;
import com.rochatec.graphics.util.Message;

public class CalendarField {

	private Text field;
	private Calendar value;
	private ListenerList listeners = new ListenerList();
	private boolean error = false;
	private ControlDecoration decoration;
	
	
	public CalendarField(Composite parent) {
		createContents(parent);
	}
	
	private void createContents(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		field = new Text(composite,SWT.BORDER);
		field.setLayoutData(new GridData(100,17));
		Button button = new Button(composite, SWT.PUSH);		 
		try {
			ImageData imageData = new ImageData(getClass().getResource("../resource/calendar_16.png").openStream());
			Image image = new Image(parent.getDisplay(),imageData);
			button.setImage(image);
		} catch (IOException e) {
			button.setText(Message.getMessage("calendar.open"));
		}
		field.addFocusListener(new TextFocused());
		field.addVerifyListener(new OnlyNumber());
		button.addSelectionListener(new ButtonClick());
	}
	
	private void fireEvent(Calendar calendar,Event e){
		if (calendar != null){
			CalendarEvent event = new CalendarEvent(e);
			event.calendar = calendar;
			event.date = calendar.getTime();
			for (Object listener : listeners.getListeners()){
				((ICalendarChangedListener)listener).changed(event);
			}
			field.setText(formatDate(calendar.getTime()));
		}
	}
	
	private String formatDate(Date date){
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(date);
	}
	public void addICalendarChangedListener(ICalendarChangedListener listener){
		this.listeners.add(listener);
	}
	
	public void removeICalendarChangedListener(ICalendarChangedListener listener){
		this.listeners.remove(listener);
	}
	
	public Calendar getValue() {
		return value;
	}

	public void setValue(Calendar value) {
		this.value = value;
	}
	
	private void validate(String dateString,TypedEvent e){		
		try{
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			Date date = df.parse(dateString);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR,0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);
			setValue(calendar);
			Event event = new Event();
			event.display = e.display;
			event.data = e.data;
			event.widget = field;
			fireEvent(calendar,event);
			decoration = new ControlDecoration(field,SWT.TOP|SWT.LEFT);
			decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
			decoration.setDescriptionText(Message.getMessage("calendar.ok.parse"));
			error = false;
		}catch (ParseException ex){
			decoration = new ControlDecoration(field,SWT.TOP|SWT.LEFT);
			decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
			decoration.setDescriptionText(Message.getMessage("calendar.error.parse"));
			error = true;
		}
	}
	
	class OnlyNumber implements VerifyListener{
		@Override
		public void verifyText(VerifyEvent e) {
			String string = e.text;
	        char[] chars = new char[string.length()];
	        string.getChars(0, chars.length, chars, 0);
	        for (int i = 0; i < chars.length; i++) {
	          if (!('0' <= chars[i] && chars[i] <= '9'  || chars[i] == '/')  ) {
	            e.doit = false;
	            return;
	          }
	        }			
		}
	}

	class ButtonClick extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			CalendarDialog dialog = new CalendarDialog(e.display.getActiveShell());
			Calendar calendar = dialog.dialog();
			setValue(calendar);
			Event event = new Event();
			event.display = e.display;
			event.data = e.data;
			event.widget = field;
			fireEvent(calendar,event);
		}
	}
	
	class TextFocused extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent e) {
			if (error){
				error = false;
				decoration = null;
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			Text text = (Text)e.widget;	
			validate(text.getText(),e);
		}
	}
	
	
}
