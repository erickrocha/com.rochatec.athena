package com.rochatec.graphics.gui;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;

public class TextField {
	
	private Text text;
	private ListenerList listeners;	
	private int typePattern = 0;
	private IFormarter formarter;
	
	public TextField(Composite parent,int style,int typePatttern) {
		createContents(parent,style);
		this.typePattern = typePatttern;
	}	
	
	public TextField(Composite parent,int typePatttern) {
		this(parent,SWT.BORDER,typePatttern);
	}
	
	public TextField(Composite parent) {
		this(parent,SWT.BORDER,0);
	}
	
	public void setFormatter(IFormarter formarter){
		this.formarter = formarter;
	}
	
	public IFormarter getFormarter(){
		return formarter;
	}
		
	private void createContents(Composite parent,int style){				
		text = new Text(parent, style);
		text.addFocusListener(new MaskListener());
	}
	
	public Widget getWidget(){
		return text;
	}
	
	public void setLayoutData(Object layoutData){
		this.text.setLayoutData(layoutData);
	}
	
	public void setText(String string){
		this.text.setText(string);		
	}
	
	public String getText(){
		return this.text.getText();
	}
	
	public void addModifyListener(ModifyListener listener){
		this.listeners.add(listener);
	}
	
	public void removeModifyListener(ModifyListener listener){
		this.listeners.remove(listener);
	}
	
	public void setEnabled(boolean enabled){
		this.text.setEnabled(enabled);
	}
	
	private void fireEvent(ModifyEvent e){
		for (Object listener : listeners.getListeners()){
			((ModifyListener)listener).modifyText(e);
		}
	}
	
	public void addKeyListener(KeyListener listener){
		this.text.addKeyListener(listener);
	}
	
	public void removeKeyListener(KeyListener listener){
		this.text.removeKeyListener(listener);
	}
	
	public Display getDisplay(){
		return text.getDisplay();
	}
	
	public void setTypePattern(int typePattern) {
		this.typePattern = typePattern;
	}

	public int getTypePattern(){
		return typePattern;
	}
	
	class TextModified implements ModifyListener{
		@Override
		public void modifyText(ModifyEvent e) {
			fireEvent(e);			
		}
	}
	
	class MaskListener implements FocusListener{
		@Override
		public void focusGained(FocusEvent e) {
			if (formarter != null){
				Text text = (Text)e.widget;
				text.setText(formarter.unMask(text.getText()));
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {			
			if (formarter != null){
				try {
					Text text = (Text)e.widget;
					text.setText(formarter.mask(text.getText()));
				} catch (BadFormatException e1) {
				}			
			}
		}
	}
}
