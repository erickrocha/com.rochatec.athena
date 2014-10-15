package com.rochatec.pos.athena.contribution;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.rochatec.pos.athena.util.GridLayoutBuilder;

public class ProductMenuContribution extends ControlContribution{
	
	public ProductMenuContribution() {
		super(ID);
	}

	public static final String ID = "com.rochatec.pos.athena.contribution.ProductMenuContribution";

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(GridLayoutBuilder.getInstance().build());
		Text text = new Text(composite, SWT.BORDER|SWT.ICON_SEARCH);
		
		return composite;
	}



}
