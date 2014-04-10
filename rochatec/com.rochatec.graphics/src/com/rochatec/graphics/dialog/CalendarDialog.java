package com.rochatec.graphics.dialog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.nebula.widgets.datechooser.DateChooser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.graphics.util.Message;

public class CalendarDialog extends ApplicationWindow{

	private DateChooser dateChooser;
	private Calendar calendar;
	private boolean currentDate;
	private Point location;
	
	public CalendarDialog(Shell owner,Point pos) {
		this(owner,true,pos);
	}
	
	
	public CalendarDialog(Shell owner,boolean currentDate,Point pos) {
		super(owner);
		this.currentDate = currentDate;
		this.location = pos;
		setBlockOnOpen(true);
		setShellStyle(SWT.CLOSE|SWT.BORDER);
	}
	
	public void setCurrentDate(boolean currentDate){
		this.currentDate = currentDate;
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setLocation(location);
		shell.setLayout(new FillLayout());
		shell.setText(Message.getMessage("calendar.dialog.title"));
	}
	
	@Override
	protected Control createContents(Composite parent) {
		parent.setLayout(new GridLayout(1,false));
		dateChooser = new DateChooser(parent,SWT.NONE);
		dateChooser.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		dateChooser.addSelectionListener(new DateSelectionListener());
		return parent;
	}
	
	public Calendar show(){
		open();
		return calendar;
	}

	
	class DateSelectionListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			DateChooser dt = (DateChooser) e.widget;
			calendar = GregorianCalendar.getInstance();
			calendar.setTime(dt.getSelectedDate());
			close();
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			if (currentDate){
				DateChooser dt = (DateChooser) e.widget;
				calendar = GregorianCalendar.getInstance();
				calendar.setTime(new Date());
				dt.setSelectedDate(calendar.getTime());
			}
		}
	}
}
