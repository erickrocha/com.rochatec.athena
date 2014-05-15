package com.rochatec.graphics.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.formater.impl.DateFormaterImpl;
import com.rochatec.framework.formater.impl.TimeFormaterImpl;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.dialog.CalendarDialog;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.Message;

public class DateTimeBox {

	private String hour;
	private Calendar date;
	private ImageHyperlink linkCal;
	private Composite container;
	private MaskedText txtDate;
	private MaskedText txtHour;
	private IFormarter dateFormarter;
	private IFormarter timeFormarter;
	private Point point;

	public DateTimeBox(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(LayoutFactory.getInstance().getGridLayout(3));
		new CLabel(container, SWT.NONE).setText(Message
				.getMessage("datetimebox.label.date"));
		new CLabel(container, SWT.NONE);
		new CLabel(container, SWT.NONE).setText(Message
				.getMessage("datetimebox.label.time"));

		dateFormarter = new DateFormaterImpl();
		txtDate = new MaskedText(container, dateFormarter);
		txtDate.addFocusListener(new FocusListener());

		linkCal = new ImageHyperlink(container, SWT.NONE);
		linkCal.setUnderlined(false);
		
//		linkCal.setImage(JFaceResources.getImage(ICons.CALENDAR_16));
//		linkCal.setImage(Activator.getImageDescriptor(ICons.CALENDAR_16).createImage());
		linkCal.addMouseListener(new PositionListener());
		linkCal.addHyperlinkListener(new DialogDateListener());
		timeFormarter = new TimeFormaterImpl();
		txtHour = new MaskedText(container, timeFormarter);
		txtHour.addFocusListener(new TimeFocusListener());
	}

	public void setLayoutData(Object layoutData) {
		container.setLayoutData(layoutData);
	}

	public void setDate(Calendar calendar) {
		this.date = calendar;
		try {
			txtDate.setValue(dateFormarter.mask(calendar));
		} catch (BadFormatException e) {
			createDecorationError(txtDate.getComponent(),"app.message.error.parse.date");
		}
	}

	public void setHour(Date date) {
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		this.hour = df.format(date);
		try {
			txtDate.setValue(timeFormarter.mask(date));
		} catch (BadFormatException e) {
			createDecorationError(txtDate.getComponent(),"app.message.error.parse.time");
		}
	}

	public Date getHour() {
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		try {
			return df.parse(this.hour);
		} catch (ParseException e) {
			return null;
		}
	}

	public Calendar getDate() {
		String[] times = hour.split(":");
		date.set(Calendar.HOUR,Integer.valueOf(times[0]));
		date.set(Calendar.MINUTE,Integer.valueOf(times[1]));
		date.set(Calendar.SECOND,0);
		return date;
	}

	private void createDecorationError(Control control,String messagekey) {
		ControlDecoration deco = new ControlDecoration(control, SWT.TOP	| SWT.RIGHT);
		Image image = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
		deco.setDescriptionText(Message.getMessage(messagekey));
		deco.setImage(image);
		deco.setShowOnlyOnFocus(false);
	}
	
	class PositionListener extends MouseAdapter{
		@Override
		public void mouseDown(MouseEvent e) {
			point = new Point(e.x,e.y);
		}
	}

	class DialogDateListener extends HyperLinkAdapter {
		
		
		@Override
		public void linkActivated(HyperlinkEvent e) {
			CalendarDialog dialog = new CalendarDialog(container.getShell(),point);
			date = dialog.show();
			try {
				txtDate.setText(dateFormarter.mask(date));
			} catch (BadFormatException e1) {
				createDecorationError(txtDate.getComponent(),"app.message.error.parse.date");
			}
		}
	}

	class FocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			Text text = (Text) e.widget;
			try {
				date = (Calendar) dateFormarter.parse(text.getText());
			} catch (BadFormatException e1) {
				createDecorationError(text,"app.message.error.parse.date");
			}
		}
	}
	
	class TimeFocusListener extends FocusAdapter{
		@Override
		public void focusLost(FocusEvent e) {
			Text text = (Text) e.widget;
			try {
				hour = timeFormarter.parse(text.getText()).toString();
			} catch (BadFormatException e1) {
				createDecorationError(text,"app.message.error.parse.time");
			}
		}
	}

}
