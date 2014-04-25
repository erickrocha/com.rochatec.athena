package com.rochatec.athena.components.viewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.util.LayoutFactory;


public class ItemViewer {

	private TableViewer tableViewer;
	private Composite base;
	
	private Text txtProductId;
	private CLabel lblProductName;
	private CLabel lblIcms;
	private CLabel lblIpi;
	private CLabel lblPrice;
	private Text txtQuantity;	
	
	public ItemViewer(Composite parent) {
		base = new Composite(parent,SWT.NONE);
		base.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		base.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createContents(base);
	}
	
	protected void createContents(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(4));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.id.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.name.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.quantity.label"));
		new Label(composite, SWT.NONE);
		
		txtProductId = new Text(composite, SWT.BORDER);
		
		lblProductName = new CLabel(composite, SWT.BORDER);
		lblProductName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		
		new Label(composite, SWT.NONE);
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.icms.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.ipi.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.ipi.label"));
		
		lblIcms = new CLabel(composite, SWT.BORDER);
		lblIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblIcms = new CLabel(composite, SWT.BORDER);
		lblIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblPrice = new CLabel(composite, SWT.BORDER);
		lblPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	public void setLayoutData(Object layoutData){
		this.base.setLayoutData(layoutData);
	}
	
	public Control getControl(){
		return base;
	}
	
}
