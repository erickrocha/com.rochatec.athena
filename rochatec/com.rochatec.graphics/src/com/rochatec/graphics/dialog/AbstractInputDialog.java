package com.rochatec.graphics.dialog;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import com.rochatec.graphics.util.IResources;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public abstract class AbstractInputDialog<T> extends ApplicationWindow implements IDialog<T>{
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	private ImageHyperlink lkCancel;
	private ImageHyperlink lkConfirm;
	protected T selected;
	protected AbstractTable table;
	
	public AbstractInputDialog(Shell owner) {
		super(owner);
		setBlockOnOpen(true);		
	}
	
	protected abstract void createComponents(Composite parent);
	
	public void createButtonBar(Composite parent){
		Composite  composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
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
	
	@Override
	protected void configureShell(Shell shell) {		
		super.configureShell(shell);
		shell.setText(getTitle());
	}
	
	public abstract String getTitle();
	
	public abstract void save();
		
	@Override
	protected Control createContents(Composite parent) {		
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(getTitle());
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(1,0));
		createComponents(form.getBody());
		createButtonBar(form.getBody());
		WidgetUtils.backgroundEquals(form.getBody());
		return parent;
	}	
	
	public void setFormImage(Image image){
		form.setImage(image);
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
			save();
			close();			
		}
	}
}
