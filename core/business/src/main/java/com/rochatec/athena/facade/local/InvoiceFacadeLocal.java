package com.rochatec.athena.facade.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

@Local
public interface InvoiceFacadeLocal {

	public InvoiceInput persist(InvoiceInput invoiceInput);

	public void remove(InvoiceInput invoiceInput);

	public InvoiceOutput persist(InvoiceOutput invoiceOutput);

	public void remove(InvoiceOutput invoiceOutput);	
	
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier);

}
