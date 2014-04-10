package com.rochatec.athena.manufacture.product.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.graphics.util.LayoutFactory;

public class ProductComposite extends Composite{

	private Text txtId;
	private Text txtQuantity;	
	
	public ProductComposite(Composite parent, int style) {
		super(parent, style);
		createContents(this);
	}
	
	private void createContents(Composite parent){
		parent.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		createFields(parent);
		createButton(parent);
		createOthers(parent);
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2,false));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.id"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.name"));
		txtId = new Text(composite,SWT.BORDER);
		txtId.setData(0);
		CLabel lblName = new CLabel(composite,SWT.LEFT|SWT.SHADOW_OUT|SWT.BORDER);
		lblName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		lblName.setData(2); 
	}
	
	private void createButton(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true,1,2));
		new Label(composite, SWT.NONE);
		ImageHyperlink hyperlink = new ImageHyperlink(composite, SWT.NONE);
		hyperlink.setImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_48).createImage());
	}
	
	private void createOthers(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(4,true));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.sellPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.icms"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.ipi"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productItem.field.label.quantity"));
		CLabel lblSellPrice = new CLabel(composite, SWT.CENTER|SWT.SHADOW_OUT|SWT.BORDER);
		lblSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		lblSellPrice.setData(6);
		CLabel lblIcms = new CLabel(composite, SWT.CENTER|SWT.SHADOW_OUT|SWT.BORDER);
		lblIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		lblIcms.setData(7);
		CLabel lblipi = new CLabel(composite,SWT.CENTER|SWT.SHADOW_OUT|SWT.BORDER);
		lblipi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		lblipi.setData(8);
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		txtQuantity.setData(-1);
	}
	
	
	public Long getLong(){
		return Long.parseLong(txtId.getText());
	}
	
	public Integer getInt(){
		return Integer.parseInt(txtId.getText());
	}
	
	public void addKeyListener(KeyListener listener){
		this.txtId.addKeyListener(listener);
	}
	
	public void removeKeyListener(KeyListener listener){
		this.txtId.removeKeyListener(listener);
	}
}
