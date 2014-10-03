package com.rochatec.athena.home.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import com.rochatec.athena.pdv.plugin.Activator;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.FontToolkit;

public class CupomView extends ViewPart{
	
	public static final String ID = "com.rochatec.athena.home.views.CupomView";
	private FormToolkit toolkit;
	private ScrolledForm form;
	private StyledText styledText;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.getBody().setLayout(new GridLayout(2,true));
		createCupom(form.getBody());
		createInput(form.getBody());
		toolkit.decorateFormHeading(form.getForm());
	}
	
	
	private void createCupom(Composite parent){
		styledText = new StyledText(parent,SWT.BORDER|SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		styledText.setFont(FontToolkit.getInstance().getTahoma(20,SWT.NONE));
		styledText.setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
	}
	
	private void createInput(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(new GridLayout(2,false));
		Text text = new Text(composite, SWT.BORDER);
		text.setFont(FontToolkit.getInstance().getTahomaHeader());
		text.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		ImageHyperlink hyperlink = new ImageHyperlink(composite, SWT.NONE);
		hyperlink.setImage(Activator.getImageDescriptor(IPathIcons.BUTTON_SEARCH).createImage());
	}

	@Override
	public void setFocus() {
		form.getBody().setFocus();
		
	}

}
