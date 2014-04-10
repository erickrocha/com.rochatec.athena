package com.rochatec.graphics.gui;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.Activator;
import com.rochatec.graphics.util.IResources;
import com.rochatec.graphics.util.Message;

public class DateField {

	private Text input;
	private DateTime dateTime;
	private Composite composite;
	private Button button;
	private Calendar calendar;
	
	public DateField(Composite parent) {
		createContents(parent);
	}
	
	public void setValue(Date date){
		if (date != null){
			calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			fillValue(calendar);
		}
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
	}
	
	public void setLayoutData(GridData gridData){
		composite.setLayoutData(gridData);
		input.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
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
	
	
	private void makeCalendar(Shell parent){
		final Shell shell = new Shell(parent,SWT.DIALOG_TRIM);
		shell.setLayout(new FillLayout());
		shell.setText(Message.getMessage("calendar.dialog.title"));
		
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
