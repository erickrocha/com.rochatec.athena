package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.model.Supplier;

@Local
public interface InvoiceInputEaoLocal {

	public InvoiceInput persist(InvoiceInput invoiceInput);
	
	public void remove(InvoiceInput invoiceInput);
	
	public InvoiceInput findByNumber(Long number, Calendar begin, Calendar end, InvoiceStatus status);
	
	public List<InvoiceInput> findByIssuer(String issuer, Calendar begin, Calendar end, InvoiceStatus status);
	
	public List<InvoiceInput> findByReceiver(String receiver, Calendar begin, Calendar end, InvoiceStatus status);
	
	public List<InvoiceInput> findByShipper(String shipper, Calendar begin, Calendar end, InvoiceStatus status);
	
	public List<InvoiceInput> findAllByIssuer(Supplier supplier);
	
	public InvoiceInput findById(Long id);
	
}
