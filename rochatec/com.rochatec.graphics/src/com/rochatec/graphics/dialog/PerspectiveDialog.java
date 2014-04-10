package com.rochatec.graphics.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.framework.model.Property;
import com.rochatec.graphics.provider.PropertyContentProvider;
import com.rochatec.graphics.provider.PropertyLabelProvider;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.Message;

public class PerspectiveDialog extends ApplicationWindow{
	
	private List<Property> perspectives = new ArrayList<Property>();
	private ListViewer viewer;
	private Property selected;
	
	

	public PerspectiveDialog(Shell owner,Map<String,Property> perspectives) {
		super(owner);
		this.perspectives = new ArrayList<>(perspectives.values());
		setShellStyle(SWT.CLOSE|SWT.BORDER);
		setBlockOnOpen(true);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(300,450);
	}
	
	public PerspectiveDialog(Shell owner) {
		this(owner, new HashMap<String,Property>());
	}
	
	public void setPerspectives(Map<String,Property> perspectives){
		this.perspectives = new ArrayList<>(perspectives.values());
	}
	
	@Override
	protected void configureShell(Shell shell) {		
		super.configureShell(shell);
		shell.setLayout(LayoutFactory.getInstance().getFillLayout());
		shell.setText(Message.getMessage("perspective.dialog.title"));
	}
	
	@Override
	protected Control createContents(Composite parent) {		
		parent.setLayout(LayoutFactory.getInstance().getGridLayout(1,3));
		viewer = new ListViewer(parent,SWT.V_SCROLL);
		viewer.getList().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		viewer.setLabelProvider(new PropertyLabelProvider());
		viewer.setContentProvider(new PropertyContentProvider());
		viewer.setInput(perspectives);
		createButtons(parent);
		viewer.addSelectionChangedListener(new PerspectiveSelection());
		viewer.addDoubleClickListener(new DoubleClickListener());
		return parent;
	}
	
	public Property show(){
		open();
		return selected;
	}
	
	private void createButtons(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(new GridLayout(2,false));
		composite.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,true,false));		
		
		Button btCancel = new Button(composite,SWT.PUSH);		
		btCancel.setText(Message.getMessage("perspective.dialog.button.cancel"));
		btCancel.addSelectionListener(new CloseListener());
		
		Button btConfirm = new Button(composite,SWT.PUSH);		
		btConfirm.setText(Message.getMessage("perspective.dialog.button.confirm"));
		btConfirm.addSelectionListener(new ConfirmListener());
	}		
	
	class ConfirmListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {			
			selected = (Property)viewer.getElementAt(viewer.getList().getSelectionIndex());
			close();
		}
	}
	
	class CloseListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			close();
		}
	}
	
	class PerspectiveSelection implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			selected = (Property)((StructuredSelection)event.getSelection()).getFirstElement();
		}
	}
	
	class DoubleClickListener implements IDoubleClickListener{
		@Override
		public void doubleClick(DoubleClickEvent event) {
			selected = (Property)((StructuredSelection)event.getSelection()).getFirstElement();
			close();
		}
	}

}
