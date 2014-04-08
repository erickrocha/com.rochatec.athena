package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.InvoiceStatus;

@Local
public interface InvoiceOutputEaoLocal {

	public InvoiceOutput persist(InvoiceOutput invoiceOutput);

	public void remove(InvoiceOutput invoiceOutput);

	public InvoiceOutput findByNumber(Long number, Calendar begin, Calendar end, InvoiceStatus status);

	public List<InvoiceOutput> findByIssuer(String issuer, Calendar begin, Calendar end, InvoiceStatus status);

	public List<InvoiceOutput> findByReceiver(String receiver, Calendar begin, Calendar end, InvoiceStatus status);

	public List<InvoiceOutput> findByShipper(String shipper, Calendar begin, Calendar end, InvoiceStatus status);

}
