package com.rochatec.graphics.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class TextViewer extends AbstractViewer {

	private Text text;
	
	public TextViewer(Composite parent,int style){
		Composite composite = new Composite(parent,style);
		this.text = new Text(composite, SWT.BORDER);
	}
	
	public TextViewer(Text text) {
		this.text = text;
	}

	public void setLayoutData(Object layoutData) {
		this.text.setLayoutData(layoutData);
	}

	public void addKeyListener(KeyListener listener) {
		this.text.addKeyListener(listener);
	}

	public void removeKeyListener(KeyListener listener) {
		this.text.removeKeyListener(listener);
	}

	@Override
	public Control getControl() {
		return text;
	}

}
