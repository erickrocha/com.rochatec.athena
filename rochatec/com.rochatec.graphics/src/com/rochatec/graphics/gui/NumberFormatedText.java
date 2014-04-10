package com.rochatec.graphics.gui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.util.Colors;

public class NumberFormatedText extends Text{

	private DecimalFormat format;
	private BigDecimal value;
	protected int FLAG_ERROR_PARSE = 0;
	
	public NumberFormatedText(Composite parent,Locale locale,String pattern) {
		super(parent,SWT.BORDER);
		setOrientation(SWT.RIGHT_TO_LEFT);
		setText("0");
		format = new DecimalFormat(pattern,new DecimalFormatSymbols(locale));
		addFocusListener(new FocusListenerImpl());
	}
	
	public NumberFormatedText(Composite parent,String pattern){
		this(parent,Locale.getDefault(),pattern);
	}
	
	public NumberFormatedText(Composite parent){
		this(parent,"#,##0.00");
	}

	
	public String getFormatedValue(String pattern) {
		format.applyPattern(pattern);
		return format.format(getValue());
	}

	public String getFormatedValue() {
		return format.format(getValue());
	}

	
	public void setValue(BigDecimal value) {
		if (value != null){
			this.value = value;
			setText(format.format(value));	
		}
	}
	
	public void setValue(Double value){
		setValue(new BigDecimal(value));
	}
	
	public void setValue(Integer value){
		setValue(new BigDecimal(value));
	}
	
	@Override
	protected void checkSubclass() {
	}
	
	public BigDecimal getValue() {
		if (this.value != null){
			return value;
		}else{
			try {
				BigDecimal value = null;
				Number number = format.parse(getText());
				if (number instanceof Double){
					value = new BigDecimal(number.doubleValue());
				}else if (number instanceof Float){
					value = new BigDecimal(number.floatValue());
				}else if (number instanceof Long){
					value = new BigDecimal(number.longValue());
				}else if (number instanceof Integer){
					value = new BigDecimal(number.intValue());
				}else{
					value = (BigDecimal)number;
				}
				return value;
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
