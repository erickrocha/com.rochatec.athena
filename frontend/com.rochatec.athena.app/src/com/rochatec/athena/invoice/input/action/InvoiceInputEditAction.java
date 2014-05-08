package com.rochatec.athena.invoice.input.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.util.IPathIcons;

public class InvoiceInputEditAction extends Action{
	
	private InvoiceInput invoice;
	private TreeViewer treeViewer;
	
	public InvoiceInputEditAction(TreeViewer treeViewer,InvoiceInput invoice) {
		setId("com.rochatec.athena.invoice.input.action.InvoiceInputEditAction");
		setImageDescriptor(Activator.getImageDescriptor(IPathIcons.INFRA_EDIT_16));
		this.treeViewer = treeViewer;
		this.invoice = invoice;
	}
}
