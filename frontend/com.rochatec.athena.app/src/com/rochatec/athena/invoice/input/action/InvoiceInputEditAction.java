package com.rochatec.athena.invoice.input.action;

import org.eclipse.jface.action.Action;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.util.IPathIcons;

public class InvoiceInputEditAction extends Action{
	
	private InvoiceInput invoice;
	private InvoiceInputSupplierHistoryView view;
	
	public InvoiceInputEditAction(InvoiceInputSupplierHistoryView view,InvoiceInput invoice) {
		setId("com.rochatec.athena.invoice.input.action.InvoiceInputEditAction");
		setImageDescriptor(Activator.getImageDescriptor(IPathIcons.INFRA_EDIT_16));
		this.view = view;
		this.invoice = invoice;
	}
}
