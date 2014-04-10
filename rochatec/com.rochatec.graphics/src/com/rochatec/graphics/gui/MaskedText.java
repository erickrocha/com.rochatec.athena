package com.rochatec.graphics.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.graphics.util.Colors;

public class MaskedText {

	private Text text;
	private IFormarter formarter;

	public MaskedText(Composite parent, int style, IFormarter formarter) {
		text = new Text(parent, style);
		this.formarter = formarter;
		text.addFocusListener(new FocusListeenr());
		text.setMessage(this.formarter.getMessage());
	}

	public MaskedText(Composite parent, IFormarter formarter) {
		this(parent, SWT.BORDER, formarter);
	}

	public String getText() {
		return text.getText();
	}
	
	public String getValue(){
		return formarter.unMask(text.getText());
	}
	
	public void setValue(String value){
		try{
			this.text.setText(formarter.mask(value));
		}catch (BadFormatException e) {
			this.text.setMessage(e.getMessage());
		}	
	}

	public void setText(String value) {
		this.text.setText(value);
	}

	public void setEditable(boolean editable) {
		text.setEditable(editable);
	}

	public void setEnabled(boolean enabled) {
		text.setEnabled(enabled);
	}

	public void setLayoutData(GridData layoutData) {
		this.text.setLayoutData(layoutData);
	}

	public void setFocus() {
		this.text.setFocus();
	}
	
	public void addKeyListener(KeyListener listener){
		this.text.addKeyListener(listener);
	}
	
	public void removeKeyListener(KeyListener listener){
		this.text.removeKeyListener(listener);
	}
	
	public IFormarter getFormarter(){
		return this.formarter;
	}
	
	public void setMessage(String message){
		this.text.setMessage(message);
	}
	
	public Text getComponent(){
		return this.text;
	}
	
	public void addFocusListener(FocusListener listener){
		text.addFocusListener(listener);
	}
	
	public void removeFocusListener(FocusListener listener){
		text.removeFocusListener(listener);
	}
	

	class FocusListeenr extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			Text text = (Text) e.widget;
			text.setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
			if (text.getText() != null && !text.getText().equalsIgnoreCase("")) {
				text.setText(formarter.unMask(text.getText()));
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			Text text = (Text) e.widget;
			text.setBackground(Colors.getColor(SWT.COLOR_WHITE));
			if (text.getText() != null && !text.getText().equalsIgnoreCase("")) {
				try{
					text.setText(formarter.mask(text.getText()));
				}catch (BadFormatException ex) {
					text.setBackground(Colors.getColor(SWT.COLOR_RED));
				}
			}
		}
	}

}
