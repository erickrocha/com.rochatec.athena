package com.rochatec.pos.athena.views;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.pos.athena.app.Activator;

public class WelcomeView extends ViewPart{

	public static final String ID = "com.rochatec.pos.athena.views.WelcomeView";
	
	private FormToolkit toolkit;
	private Form form;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createForm(parent);
		GridLayout layout = new GridLayout(1,false);
		layout.marginTop = 50;
		layout.marginLeft = 130;
		layout.marginRight = 40;
		form.getBody().setLayout(layout);
		
		Form header = new Form(form.getBody(),SWT.NONE);
		header.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		header.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();		
		header.setText(preferenceStore.getString("welcome.text"));
		toolkit.decorateFormHeading(header);
	}

	@Override
	public void setFocus() {
		
	}

}
