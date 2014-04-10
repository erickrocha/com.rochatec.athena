package com.rochatec.graphics.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import com.rochatec.graphics.util.LayoutFactory;

public abstract class AbstractView extends ViewPart{
	
	protected ScrolledForm form;
	
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(getPartName());
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(1,0));
		form.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createContents(form.getBody());
		addListeners();
	}
	
	public void setTitle(String message){
		this.form.setText(message);
	}
	
	protected abstract void createContents(Composite parent);
	
	protected void activateContext(){
		IContextService contextService = (IContextService) getSite().getService(IContextService.class);
		contextService.activateContext("com.rochatec.metallurgical.app.crud");		
	}
	
	protected abstract void addListeners();
	
	@Override
	public String getTitle() {
		return getPartName();
	}
	
	@Override
	public String getTitleToolTip() {
		return getPartName();
	}
	
	
	
}
