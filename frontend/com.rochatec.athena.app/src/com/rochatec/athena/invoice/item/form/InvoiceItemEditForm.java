package com.rochatec.athena.invoice.item.form;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.graphics.dialog.AbstractEditDialog;

public class InvoiceItemEditForm extends AbstractEditDialog<InvoiceInputItem> {

	private ItemBox itemBox;
	private InvoiceItemListener listener;
	
	public InvoiceItemEditForm(Shell owner, InvoiceInputItem selected,InvoiceItemListener listener) {
		super(owner, selected);
		this.listener = listener;
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
		itemBox = new ItemBox(parent,selected,ATHENA.EDIT);
		itemBox.addInvoiceItemListener(listener);
	}
	
	public void addInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.addInvoiceItemListener(listener);
	}
	
	public void removeInvoiceItemListener(InvoiceItemListener listener){
		this.itemBox.removeInvoiceItemListener(listener);
	}

	@Override
	public void execute() {		
		itemBox.editItem();		
	}
}
