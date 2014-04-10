package com.rochatec.graphics.gui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class TimeFormatedText extends Text {
	
	private BigDecimal value;
	
	public TimeFormatedText(Composite parent, int style) {
		super(parent, style);
	}
	
	public TimeFormatedText(Composite parent) {
		this(parent,SWT.BORDER);
	}

	public void setValue(BigDecimal value){
		if (value != null){
			this.value = value;
		}
	}
	
	public BigDecimal getValue(){
		return this.value;
	}

	
	
	
	@Override
	protected void checkSubclass() {
	}

}
