package com.rochatec.athena.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.InvoiceInputEaoLocal;
import com.rochatec.athena.eao.local.InvoiceOutputEaoLocal;
import com.rochatec.athena.facade.local.InvoiceFacadeLocal;
import com.rochatec.athena.facade.remote.InvoiceFacadeRemote;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;

@Stateless
public class InvoiceFacadeImpl implements InvoiceFacadeLocal,InvoiceFacadeRemote{
	
	@EJB
	private InvoiceInputEaoLocal invoiceInputEaoLocal;
	
	@EJB
	private InvoiceOutputEaoLocal invoiceOutputEaoLocal;
	
	@Override
	public InvoiceInput persist(InvoiceInput invoiceInput) {
		invoiceInput = invoiceInputEaoLocal.persist(invoiceInput);
		return invoiceInput;
	}

	@Override
	public void remove(InvoiceInput invoiceInput) {
		invoiceInputEaoLocal.remove(invoiceInput);		
	}

	@Override
	public InvoiceOutput persist(InvoiceOutput invoiceOutput) {
		invoiceOutput = invoiceOutputEaoLocal.persist(invoiceOutput);
		return invoiceOutput;
	}

	@Override
	public void remove(InvoiceOutput invoiceOutput) {
		invoiceOutputEaoLocal.remove(invoiceOutput);
	}

	@Override
	public List<InvoiceInput> findAllInvoiceInputByIssuer(Supplier supplier) {
		List<InvoiceInput> invoiceInputs = invoiceInputEaoLocal.findAllByIssuer(supplier);
		return invoiceInputs;
	}

	@Override
	public InvoiceInput findInvoiceInputById(Long id) {
		InvoiceInput invoiceInput = invoiceInputEaoLocal.findById(id);
		return invoiceInput;
	}

}
