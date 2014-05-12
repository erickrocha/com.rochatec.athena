package com.rochatec.athena.invoice.input.action;

import org.eclipse.jface.action.Action;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.util.IPathIcons;

public class InvoiceInputEditAction extends Action{
	
	public static final String ID = "com.rochatec.athena.invoice.input.action.InvoiceInputEditAction";
	
	private InvoiceInput invoice;
	private InvoiceInputSupplierHistoryView view;
	
	
	
	public InvoiceInputEditAction(InvoiceInputSupplierHistoryView view,InvoiceInput invoice) {
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor(IPathIcons.INFRA_EDIT_16));
		this.view = view;
		this.invoice = invoice;
	}
}
