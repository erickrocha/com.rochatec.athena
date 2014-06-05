package com.rochatec.athena.invoice.output.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.util.IPathIcons;

public class InvoiceOutputEditorInput implements IEditorInput {

	private InvoiceOutput invoice;
	
	public InvoiceOutputEditorInput() {
		
	}
	
	public InvoiceOutputEditorInput(InvoiceOutput invoice){
		this.invoice = invoice;
	}

	@SuppressWarnings("rawtypes")
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

	public InvoiceOutput getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceOutput invoice) {
		this.invoice = invoice;
	}

}
