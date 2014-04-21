package com.rochatec.graphics.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.Activator;
import com.rochatec.graphics.event.CalendarEvent;
import com.rochatec.graphics.listener.ICalendarChangedListener;
import com.rochatec.graphics.util.IResources;
import com.rochatec.graphics.util.Message;

public class DateField {

	private Text input;
	private DateTime dateTime;
	private Composite composite;
	private Button button;
	private Calendar calendar;	
	private ListenerList listenerList = new ListenerList();
	private String value;
	
	public DateField(Composite parent) {
		createContents(parent);
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValueString(){
		return value;
	}

	public void setValue(Date date){
		if (date != null){
			calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			fillValue(calendar);
		}
	}
	
	public Text getInput(){
		return this.input;
	}	

	public void setValue(Calendar calendar){
		if (calendar != null){
			this.calendar = calendar;
			fillValue(calendar);
		}
	}
	
	public void setEnabled(boolean value){
		this.input.setEnabled(value);
		this.button.setEnabled(value);
	}
	
	public void clear(){
		this.input.setText("");
		this.calendar = null;
	}
	
	public Calendar getValue(){
		return this.calendar;
	}	
	
	private void fillValue(Calendar calendar){
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		input.setText(df.format(calendar.getTime()));
		firedEvent();
	}
	
	private void createContents(Composite parent){
		composite = new Composite(parent,SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		input = new Text(composite,SWT.BORDER);
		input.addVerifyListener(new OnlyNumber());
		button = new Button(composite, SWT.PUSH);
		button.setImage(Activator.getImageDescriptor(IResources.ICON_CALENDAR_16).createImage());
		button.addSelectionListener(new ButtonClick());
		bindValues();
	}
	
	public void setLayoutData(GridData gridData){
		composite.setLayoutData(gridData);
		input.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	public void addICalendarChangedListener(ICalendarChangedListener listener){
		this.listenerList.add(listener);
	}
	
	public void removeICalendarChangedListener(ICalendarChangedListener listener){
		this.listenerList.remove(listener);
	}
	
	private void firedEvent(){
		Event event = new Event();
		event.data = calendar;
		event.display = dateTime.getDisplay();
		event.item = dateTime;
		event.text = input.getText();		
		CalendarEvent calendarEvent = new CalendarEvent(event);
		calendarEvent.calendar = calendar;
		calendarEvent.date = calendar.getTime();
		
		for (Object listener : listenerList.getListeners()){
			((ICalendarChangedListener)listener).changed(calendarEvent);
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
	
	private void bindValues(){
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(input);
		IObservableValue modelValue = PojoProperties.value(DateField.class,"value").observe(this);
		
		IValidator validator = new  IValidator() {
			
			@Override
			public IStatus validate(Object value) {
				if (value instanceof String){					
					DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
					try {
						calendar = getCalendar(df.parse(value.toString()));
					} catch (ParseException e) {
						return ValidationStatus.error(Message.getMessage("date.error"));
					} 
				}
				return ValidationStatus.ok();
			}
		};
		UpdateValueStrategy strategy = new UpdateValueStrategy();
	    strategy.setBeforeSetValidator(validator);
	    Binding bindValue = bindingContext.bindValue(widgetValue, modelValue,strategy,null);
		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
	}
	
	private Calendar getCalendar(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	
	private void makeCalendar(Shell parent){
		final Shell shell = new Shell(parent,SWT.DIALOG_TRIM);
		shell.setLayout(new FillLayout());
		shell.setText(Message.getMessage("calendar.dialog.title"));
		shell.setLocation(button.getLocation().x + button.getSize().x,button.getLocation().y+button.getSize().y);
		
		dateTime = new DateTime(shell, SWT.CALENDAR|SWT.BORDER);		
		dateTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				calendar = GregorianCalendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH,dateTime.getDay());
				calendar.set(Calendar.MONTH,dateTime.getMonth());
				calendar.set(Calendar.YEAR,dateTime.getYear());
				fillValue(calendar);
				shell.close();
			}
		});
		shell.pack();
		shell.open();		
	}
	
	class ButtonClick extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {			
			makeCalendar(button.getShell());
		}
	}
}
