package com.rochatec.athena.invoice.input.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceValue;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.gui.NumberFormatedText;
import com.rochatec.graphics.util.LayoutFactory;

public class InvoiceValueViewer {

	private Group composite;
	private NumberFormatedText txtBaseIcms;
	private NumberFormatedText txtTotalIcms;
	private NumberFormatedText txtBaseIcmsSub;
	private NumberFormatedText txtTotalIcmsSub;
	private NumberFormatedText txtTotalItems;
	private NumberFormatedText txtTotalFrete;
	private NumberFormatedText txtTotalInvoice;
	private NumberFormatedText txtTotalSeguro;
	private NumberFormatedText txtOutrasDespesas;
	private NumberFormatedText txtTotalIpi;
	private NumberFormatedText txtDesconto;
	
	private InvoiceValue invoiceValue;
	
	public InvoiceValueViewer(Composite parent) {
		this(parent,SWT.NONE);
	}
	
	public InvoiceValueViewer(Composite parent,int style) {
		createContents(parent,style);
	}
	
	
	private void createContents(Composite parent,int style){
		composite = new Group(parent,style);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.baseIcms.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIcms.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.baseIcmsSub.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIcmsSub.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIpi.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalProducts.field.label"));
		
		txtBaseIcms = new NumberFormatedText(composite);
		txtBaseIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIcms = new NumberFormatedText(composite);
		txtTotalIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtBaseIcmsSub = new NumberFormatedText(composite);
		txtBaseIcmsSub.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIcmsSub = new NumberFormatedText(composite);
		txtTotalIcmsSub.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIpi = new NumberFormatedText(composite);
		txtTotalIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalItems = new NumberFormatedText(composite);
		txtTotalItems.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalFrete.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalSeguro.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.outrasDespesas.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.desconto.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalInvoice.field.label"));
		new Label(composite, SWT.NONE);
		
		txtTotalFrete = new NumberFormatedText(composite);
		txtTotalFrete.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalSeguro = new NumberFormatedText(composite);
		txtTotalSeguro.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtOutrasDespesas = new NumberFormatedText(composite);
		txtOutrasDespesas.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDesconto = new NumberFormatedText(composite);
		txtDesconto.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalInvoice = new NumberFormatedText(composite);
		txtTotalInvoice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,0));		
	}
	
	public void setValue(InvoiceValue value){
		this.invoiceValue = value;
		fill(invoiceValue);
	}
	
	private void fill(InvoiceValue value){
		try{
			txtBaseIcms.setText(Formatter.getCurrency().mask(value.getBaseIcms()));
			txtTotalIcms.setText(Formatter.getCurrency().mask(value.getTotalIcms()));
			txtBaseIcmsSub.setText(Formatter.getCurrency().mask(value.getBaseIcmsSub()));
			txtTotalIcmsSub.setText(Formatter.getCurrency().mask(value.getTotalIcmsSub()));
			txtTotalIpi.setText(Formatter.getCurrency().mask(value.getTotalIpi()));
			txtTotalItems.setText(Formatter.getCurrency().mask(value.getTotalItems()));
			txtTotalFrete.setText(Formatter.getCurrency().mask(value.getTotalFrete()));
			txtTotalSeguro.setText(Formatter.getCurrency().mask(value.getTotalSeguro()));
			txtOutrasDespesas.setText(Formatter.getCurrency().mask(value.getOutrasdespesas()));
			txtDesconto.setText(Formatter.getCurrency().mask(value.getDesconto()));
			txtTotalInvoice.setText(Formatter.getCurrency().mask(value.getTotalInvoice()));
		}catch (BadFormatException ex){
			
		}
	}
	
	public void setLayoutDate(Object layoutData){
		this.composite.setLayoutData(layoutData);
	}
	
	public void setLayout(Layout layout){
		this.composite.setLayout(layout);
	}
	
	public void pack(){
		this.composite.pack();
	}
}



