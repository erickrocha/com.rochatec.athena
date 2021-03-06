package com.rochatec.athena.facade.remote;

import java.util.List;

import javax.ejb.Remote;

import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

@Remote
public interface InvoiceFacadeRemote {

	public InvoiceInput persist(InvoiceInput invoiceInput);

	public void remove(InvoiceInput invoiceInput);
	
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier);
	
	public InvoiceInput findInvoiceInputById(Long id);
	
	public InvoiceOutput persist(InvoiceOutput invoiceOutput);

	public void remove(InvoiceOutput invoiceOutput);
	
	public List<InvoiceOutput> findAllInvoiceOutputstByIssuer(Company company);
	
	public List<InvoiceOutput> findAllInvoiceOutputstByReceiver(Customer customer);
	
	public InvoiceOutput findInvoiceOutputById(Long id);
	
	
}
