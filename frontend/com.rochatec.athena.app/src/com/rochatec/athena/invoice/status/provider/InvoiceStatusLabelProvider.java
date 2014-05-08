package com.rochatec.athena.invoice.status.provider;

import org.eclipse.jface.viewers.LabelProvider;

import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.util.StatusTradutor;

public class InvoiceStatusLabelProvider extends LabelProvider{

	
	@Override
	public String getText(Object element) {
		InvoiceStatus invoiceStatus = (InvoiceStatus)element;
		return StatusTradutor.getLabel(invoiceStatus);
	}
}
