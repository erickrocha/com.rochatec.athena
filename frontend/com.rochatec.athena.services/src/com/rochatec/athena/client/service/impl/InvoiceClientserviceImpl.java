package com.rochatec.athena.client.service.impl;

import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.facade.remote.InvoiceFacadeRemote;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

public class InvoiceClientserviceImpl implements InvoiceClientService{
	
	private InvoiceFacadeRemote invoiceFacadeRemote = ServiceLocator.getInstance().getInvoiceFacadeRemote();
	
	@Override
	public InvoiceInput persist(InvoiceInput invoiceInput) {
		return invoiceFacadeRemote.persist(invoiceInput);
	}

	@Override
	public void remove(InvoiceInput invoiceInput) {
		invoiceFacadeRemote.remove(invoiceInput);		
	}

	
	@Override
	public InvoiceOutput persist(InvoiceOutput invoiceOutput) {
		return invoiceFacadeRemote.persist(invoiceOutput);
	}

	@Override
	public void remove(InvoiceOutput invoiceOutput) {
		invoiceFacadeRemote.remove(invoiceOutput);
	}

	@Override
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier) {		
		return invoiceFacadeRemote.findAllInvoiceInputByIssuer(supplier);
	}

	

}