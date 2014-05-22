package com.rochatec.graphics.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.util.LayoutFactory;

public class IdAndTextViewer extends AbstractViewer {
	
	private Text id;
	private Text name;
	private Composite container;
	
	public IdAndTextViewer(Composite parent) {
		createContents(parent);
	}

	@Override
	public void setLayoutData(Object layoutData) {
		this.container.setLayoutData(layoutData);
	}

	public void addKeyListener(KeyListener listener) {
		this.id.addKeyListener(listener);
	}

	public void removeKeyListener(KeyListener listener) {
		this.id.removeKeyListener(listener);
	}
	
	private void createContents(Composite parent){
		container = new Composite(parent,SWT.NONE);
		container.setLayout(LayoutFactory.getInstance().getGridLayout(2,0,5));
		id = new Text(container, SWT.BORDER);
		id.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		Composite composite = new Composite(container,SWT.READ_ONLY);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		name = new Text(composite, SWT.BORDER);		
	}
	
	public String getText(){
		return name.getText();
	}
	
	public String getId(){
		return id.getText();
	}
	
	public Long getIdToLong(){
		return Long.parseLong(id.getText());
	}

	@Override
	public Control getControl() {
		return container;
	}

}
