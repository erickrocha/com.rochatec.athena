package com.rochatec.athena.invoice.input.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceValue;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.gui.NumberFormatedText;
import com.rochatec.graphics.util.LayoutFactory;

public class InvoiceValueViewer implements Bindable{

	private Group group;
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
	private Editable editable;
	
	public InvoiceValueViewer(Composite parent,InvoiceValue invoiceValue,Editable editable) {
		this(parent,SWT.NONE,invoiceValue,editable);
	}
	
	public InvoiceValueViewer(Composite parent,int style,InvoiceValue invoiceValue,Editable editable) {
		this.invoiceValue = invoiceValue;
		this.editable = editable;
		createContents(parent,style);		
	}
	
	
	private void createContents(Composite parent,int style){
		group = new Group(parent,style);
		group.setText(Messages.getMessage("invoiceValue.title.label"));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.baseIcms.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIcms.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.baseIcmsSub.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIcmsSub.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalIpi.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalProducts.field.label"));
		
		txtBaseIcms = new NumberFormatedText(group);
		txtBaseIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIcms = new NumberFormatedText(group);
		txtTotalIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtBaseIcmsSub = new NumberFormatedText(group);
		txtBaseIcmsSub.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIcmsSub = new NumberFormatedText(group);
		txtTotalIcmsSub.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalIpi = new NumberFormatedText(group);
		txtTotalIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalItems = new NumberFormatedText(group);
		txtTotalItems.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalFrete.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalSeguro.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.outrasDespesas.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.desconto.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoiceValue.totalInvoice.field.label"));
		new Label(group, SWT.NONE);
		
		txtTotalFrete = new NumberFormatedText(group);
		txtTotalFrete.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalSeguro = new NumberFormatedText(group);
		txtTotalSeguro.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtOutrasDespesas = new NumberFormatedText(group);
		txtOutrasDespesas.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDesconto = new NumberFormatedText(group);
		txtDesconto.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtTotalInvoice = new NumberFormatedText(group);
		txtTotalInvoice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,0));
		DataBindingFactory<InvoiceValue> factory = new DataBindingFactory<InvoiceValue>(invoiceValue,editable);
		factory.bind(getBinds());
	}
	
	public void setValue(InvoiceValue value){
		this.invoiceValue = value;
	}
	
	public InvoiceValue getValue(){
		return this.invoiceValue;
	}
	
	public void setLayoutDate(Object layoutData){
		this.group.setLayoutData(layoutData);
	}
	
	public void setLayout(Layout layout){
		this.group.setLayout(layout);
	}
	
	public void pack(){
		this.group.pack();
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("baseIcms",txtBaseIcms);
		map.put("totalIcms",txtTotalIcms);
		map.put("baseIcmsSub",txtBaseIcmsSub);
		map.put("totalIcmsSub",txtTotalIcmsSub);
		map.put("desconto",txtDesconto);
		map.put("outrasdespesas",txtOutrasDespesas);
		map.put("totalFrete",txtTotalFrete);
		map.put("totalInvoice",txtTotalInvoice);
		map.put("totalIpi",txtTotalIpi);
		map.put("totalItems",txtTotalItems);
		map.put("totalSeguro",txtTotalSeguro);		
		return map;
	}
	
	
}



