package com.rochatec.graphics.dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.rochatec.graphics.Activator;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.IResources;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.Message;
import com.rochatec.graphics.util.WidgetUtils;

public abstract class AbstractDialog<T> extends ApplicationWindow implements IDialog<T>{

	private FormToolkit toolkit;
	private ScrolledForm form;
	private ImageHyperlink lkCancel;
	private ImageHyperlink lkConfirm;
	protected T selected;
	protected AbstractTable table;
	
	public AbstractDialog(Shell owner) {
		super(owner);
		setBlockOnOpen(true);		
	}
	
	public abstract void createSearchArea(Composite parent);
	
	public void createTableArea(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createTable(composite);
		table.addDoubleClickListener(new DoubleClickListener());
		table.addKeyListener(new EnterListener());
		table.addSelectionChangedListener(new SelectedListener());
	};
	
	public abstract void createTable(Composite parent);
	
	public void createButtonBar(Composite parent){
		Composite  composite = new Composite(parent,SWT.BORDER);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2,5));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lkCancel = toolkit.createImageHyperlink(composite, SWT.NONE);
		lkCancel.setLayoutData(new GridData(SWT.LEFT,SWT.FILL,true,false));
		lkCancel.setImage(Activator.getImageDescriptor(IResources.ICON_DIALOG_CANCEL_32).createImage());
		lkCancel.setUnderlined(false);
		lkCancel.addHyperlinkListener(new CancelListener());
		
		lkConfirm = toolkit.createImageHyperlink(composite, SWT.NONE);
		lkConfirm.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,true,false));
		lkConfirm.setImage(Activator.getImageDescriptor(IResources.ICON_DIALOG_CONFIRM_32).createImage());
		lkConfirm.setUnderlined(false);
		lkConfirm.addHyperlinkListener(new ConfirmListener());
		
	}
	
	@Override
	protected void configureShell(Shell shell) {		
		super.configureShell(shell);
		shell.setText(getTitle());
	}
	
	public T dialog(){
		open();
		return selected;
	}
	
	public T getObject(){
		return selected;
	}
	
	public void addCancelListener(IHyperlinkListener listener){
		lkCancel.addHyperlinkListener(listener);
	}
	
	public void addConfirmListener(IHyperlinkListener listener){
		lkConfirm.addHyperlinkListener(listener);
	}
	
	public abstract String getTitle();
		
	@Override
	protected Control createContents(Composite parent) {		
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(getTitle());
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(1,0));
		createSearchArea(form.getBody());
		createTableArea(form.getBody());
		createButtonBar(form.getBody());
		WidgetUtils.backgroundEquals(form.getBody());
		return parent;
	}
	
	class SelectedListener implements ISelectionChangedListener{

		@SuppressWarnings("unchecked")
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			selected = (T) ((IStructuredSelection)event.getSelection()).getFirstElement();			
		}
		
	}
	
	class DoubleClickListener implements IDoubleClickListener{
		@SuppressWarnings("unchecked")
		@Override
		public void doubleClick(DoubleClickEvent event) {
			selected = ((T) ((IStructuredSelection)table.getSelection()).getFirstElement());
			close();
		}
	}
	
	class EnterListener extends KeyAdapter{
		@SuppressWarnings("unchecked")
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == IKeyPadConstants.KEY_ENTER || e.keyCode == IKeyPadConstants.KEY_ENTER_NUMERICO){
				selected = (T) ((IStructuredSelection)table.getSelection()).getFirstElement();
				close();
			}
		}
	}
	
	class CancelListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			close();
		}
	}
	
	class ConfirmListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			if (selected == null){
				if (MessageDialog.openConfirm(getShell(),
						Message.getMessage("dialog.empty.selected.title"),
						Message.getMessage("dialog.empty.selected.message"))){
					close();
				};
			}else{
				close();
			}
		}
	}
	

}
