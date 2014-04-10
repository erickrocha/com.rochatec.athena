package com.rochatec.graphics.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.util.Colors;

public class DateFormatedText extends Text{

	private DateFormat format;
	private Calendar calendar;
	protected int FLAG_ERROR_PARSE = 0;
	
	public DateFormatedText(Composite parent, int style) {
		super(parent, style);
		format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		addFocusListener(new FocusListenerImpl());
	}
	
	public DateFormatedText(Composite parent){
		this(parent,SWT.BORDER);
	}

	public Calendar getCalendar(){
		return calendar;
	}
	
	public void setCalendar(Calendar calendar){
		if (calendar != null){
			this.calendar = calendar;
			setText(format.format(calendar.getTime()));
		}
		
	}
	
	@Override
	protected void checkSubclass() {
	}
	
	public Calendar getValue() {
		if (this.calendar != null){
			return calendar;
		}else{
			try {
				Date value = format.parse(getText());
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(value);
				return calendar;
			} catch (ParseException e) {
				setBackground(Colors.getColorRed());
			}
		}
		return null;
	}
	
	class FocusListenerImpl extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent e) {
			((Text)e.widget).setBackground(Colors.getInfoBackGround());
			
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			switch (FLAG_ERROR_PARSE) {
			case 0:
				((Text)e.widget).setSelection(0,getText().length()-1);
				((Text)e.widget).setBackground(Colors.getColorWhite());
				break;
			case 1:
				((Text)e.widget).setBackground(Colors.getColorRed());
				break;
			}
						
		}
	}
}
