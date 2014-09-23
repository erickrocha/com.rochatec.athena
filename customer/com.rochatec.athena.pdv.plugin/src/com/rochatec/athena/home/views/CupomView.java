package com.rochatec.athena.home.views;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

public class CupomView extends ViewPart{
	
	public static final String ID = "com.rochatec.athena.home.views.CupomView";
	private FormToolkit toolkit;
	private ScrolledForm form;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.getBody().setLayout(new GridLayout(1,false));
		toolkit.decorateFormHeading(form.getForm());
	}

	@Override
	public void setFocus() {
		form.getBody().setFocus();
		
	}

}
