package com.rochatec.athena.invoice.input.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.util.IPathIcons;

public class InvoiceInputEditorInput implements IEditorInput {

	private InvoiceInput invoice;
	
	public InvoiceInputEditorInput() {
		
	}
	
	public InvoiceInputEditorInput(InvoiceInput invoice){
		this.invoice = invoice;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.INVOICE_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("invoice.input.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public InvoiceInput getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceInput invoice) {
		this.invoice = invoice;
	}

}
