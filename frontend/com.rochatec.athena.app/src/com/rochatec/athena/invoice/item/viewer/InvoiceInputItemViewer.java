package com.rochatec.athena.invoice.item.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;

import com.rochatec.athena.i18n.Messages;
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
	private InvoiceItemListener listener;
	
	public InvoiceInputItemViewer(Composite parent,InvoiceItemListener listener) {
		this(parent,new InvoiceInputItem(),listener);
	}
	
	public InvoiceInputItemViewer(Composite parent,InvoiceInputItem item,InvoiceItemListener listener){
		base = new Composite(parent,SWT.NONE);
		base.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		base.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));		
		itemBox = new ItemBox(base,item,ATHENA.INSERT);
		this.listener = listener;
		addInvoiceItemListener(listener);
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
		tableViewer.addKeyListener(new DeleteListener());
	}
	
	public void setLayoutData(Object layoutData){
		this.base.setLayoutData(layoutData);
	}
	
	public Control getControl(){
		return base;
	}
	
	public void setInput(List<InvoiceInputItem> items){
		if (items == null)
			items = new ArrayList<InvoiceInputItem>();
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
			InvoiceItemEditForm form =  new InvoiceItemEditForm(event.getViewer().getControl().getShell(),item,listener);
			form.open();
		}
	}
	
	class DeleteListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.character == SWT.DEL){
				MessageBox msg = new MessageBox(e.display.getActiveShell(),SWT.ICON_QUESTION|SWT.YES|SWT.NO);
		   		msg.setText(Messages.getMessage("invoice.dialog.delete.item.title"));
		   		msg.setMessage(Messages.getMessage("invoice.dialog.delete.item.message"));
		   		int resp = msg.open();
		   		if (resp == SWT.YES){
		   			InvoiceInputItem item = (InvoiceInputItem)((IStructuredSelection)tableViewer.getSelection()).getFirstElement();
		   			itemBox.deleteItem(item);
		   		}
			}			
		}
	}
}
