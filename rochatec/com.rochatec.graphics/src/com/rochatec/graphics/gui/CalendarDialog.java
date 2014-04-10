package com.rochatec.graphics.gui;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

public class CalendarDialog extends ApplicationWindow{

	private Calendar calendar;
	
	public CalendarDialog(Shell owner) {
		super(owner);
		setBlockOnOpen(true);
		setShellStyle(SWT.DIALOG_TRIM);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		DateTime dateTime = new DateTime(parent, SWT.CALENDAR|SWT.BORDER);
		dateTime.addMouseListener(new DateSelected());
		return parent;
	}

	class DateSelected extends MouseAdapter{
		@Override
		public void mouseDoubleClick(MouseEvent e) {			
			DateTime dateTime = (DateTime)e.widget;			
			calendar = GregorianCalendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH,dateTime.getDay());
			calendar.set(Calendar.MONTH,dateTime.getMonth());
			calendar.set(Calendar.YEAR,dateTime.getYear());
			calendar.set(Calendar.HOUR,0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);
			close();
		}
	}
	
	
	public Calendar dialog(){		
		open();
		return calendar;
	}
	
}
