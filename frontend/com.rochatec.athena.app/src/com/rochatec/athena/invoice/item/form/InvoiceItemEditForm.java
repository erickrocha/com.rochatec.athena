package com.rochatec.athena.invoice.item.form;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.graphics.dialog.AbstractEditDialog;

public class InvoiceItemEditForm extends AbstractEditDialog<InvoiceInputItem> {

	private ItemBox itemBox;
	
	public InvoiceItemEditForm(Shell owner, InvoiceInputItem selected) {
		super(owner, selected);
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("invoiceItem.edit.dialog.title");
	}
	
	@Override
	protected Point getInitialSize() {		
		return new Point(800,350);
	}

	@Override
	public void createBody(Composite parent) {		
		itemBox = new ItemBox(parent,selected);	
	}
	
	public void addInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.addInvoiceItemListener(listener);
	}
	
	public void removeInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.removeInvoiceItemListener(listener);
	}

}
