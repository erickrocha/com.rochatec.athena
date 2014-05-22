package com.rochatec.athena.invoice.item.viewer;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.invoice.item.form.InvoiceItemEditForm;
import com.rochatec.athena.invoice.item.form.ItemBox;
import com.rochatec.athena.invoice.item.provider.InvoiceItemLabelProvider;
import com.rochatec.athena.invoice.item.table.InvoiceItemTable;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.util.LayoutFactory;

public class InvoiceInputItemViewer {
	
	private AbstractTable tableViewer;
	private Composite base;
	private ItemBox itemBox;
	
	public InvoiceInputItemViewer(Composite parent) {
		this(parent,new InvoiceInputItem());
	}
	
	public InvoiceInputItemViewer(Composite parent,InvoiceInputItem item){
		base = new Composite(parent,SWT.NONE);
		base.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		base.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));		
		itemBox = new ItemBox(base,item,ATHENA.INSERT);		
		createTable(base);
	}
	
	private void createTable(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		tableViewer = new InvoiceItemTable(composite);
		tableViewer.setContentProvider(new GenericContentProvider<InvoiceInputItem>());
		tableViewer.setLabelProvider(new InvoiceItemLabelProvider());
		tableViewer.addDoubleClickListener(new EditListener());
	}
	
	public void setLayoutData(Object layoutData){
		this.base.setLayoutData(layoutData);
	}
	
	public Control getControl(){
		return base;
	}
	
	public void setInput(List<InvoiceInputItem> items){
		tableViewer.setInput(items);
	}
	
	public void addInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.addInvoiceItemListener(listener);
	}
	
	public void removeInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.removeInvoiceItemListener(listener);
	}
	
	class EditListener implements IDoubleClickListener{
		@Override
		public void doubleClick(DoubleClickEvent event) {
			InvoiceInputItem item = (InvoiceInputItem)((IStructuredSelection)tableViewer.getSelection()).getFirstElement();			
			InvoiceItemEditForm form =  new InvoiceItemEditForm(event.getViewer().getControl().getShell(),item);
			form.open();
		}
	}
}
