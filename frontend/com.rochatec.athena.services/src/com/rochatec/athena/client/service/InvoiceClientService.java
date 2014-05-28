package com.rochatec.athena.client.service;

import java.util.List;

import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

public interface InvoiceClientService {

	public InvoiceInput persist(InvoiceInput invoiceInput);

	public void remove(InvoiceInput invoiceInput);
	
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier);

	public InvoiceOutput persist(InvoiceOutput invoiceOutput);

	public void remove(InvoiceOutput invoiceOutput);
	
	public InvoiceInput findInvoiceInputById(Long id);
}
