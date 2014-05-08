package com.rochatec.athena.invoice.input.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;

public class InvoiceInputNewAction extends Action{
	
	private TreeViewer treeViewer;
	private Supplier supplier;
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	
	public InvoiceInputNewAction(TreeViewer treeViewer,Supplier supplier) {
		setId("com.rochatec.athena.invoice.input.action.InvoiceInputNewAction");
		setImageDescriptor(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_16));
		this.treeViewer = treeViewer;
		this.supplier = supplier;
	}
	
	@Override
	public void run() {
		InvoiceInput invoiceInput = InvoiceInput.factory(null,supplier);
		
	}
}
