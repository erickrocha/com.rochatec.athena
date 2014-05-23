package com.rochatec.graphics.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class CompositeText {

	private Composite container;
	private Text text;
	
	public CompositeText(Composite parent) {
		this(parent,SWT.NONE);
	}
	
	public CompositeText(Composite parent,int style) {
		createContents(parent,style);
	}
	
	private void createContents(Composite parent,int style){
		container = new Composite(parent,SWT.NONE);
		FillLayout layout = new  FillLayout();
		layout.marginHeight =0;
		layout.marginWidth = 0;
		layout.spacing = 0;
		container.setLayout(layout);
		if (style == SWT.READ_ONLY){
			container.setEnabled(false);
		}
		text = new Text(container,SWT.BORDER);
	}
	
	public void setFocus(){
		this.text.setFocus();
	}
	
	public Composite getContainer(){
		return this.container;
	}
	
	public Text getComponent(){
		return this.text;
	}
	
	public void setText(String string){
		this.text.setText(string);
	}
	
	public String getText(){
		return this.text.getText();
	}
	
	public void clear(){
		this.text.setText("");
	}
	
	public void setLayoutData(Object layoutData){
		this.container.setLayoutData(layoutData);
	}
	
	public void addKeyListener(KeyListener listener){
		this.text.addKeyListener(listener);
	}
	
	public void removeKeyListener(KeyListener listener){
		this.text.removeKeyListener(listener);
	}
	
	public void setEnabled(boolean enabled){
		this.container.setEnabled(enabled);
	}
}
