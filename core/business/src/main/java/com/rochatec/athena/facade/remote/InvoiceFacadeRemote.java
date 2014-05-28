package com.rochatec.athena.facade.remote;

import java.util.List;

import javax.ejb.Remote;

import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

@Remote
public interface InvoiceFacadeRemote {

	public InvoiceInput persist(InvoiceInput invoiceInput);

	public void remove(InvoiceInput invoiceInput);

	public InvoiceOutput persist(InvoiceOutput invoiceOutput);

	public void remove(InvoiceOutput invoiceOutput);
	
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier);
	
	public InvoiceInput findInvoiceInputById(Long id);
}
