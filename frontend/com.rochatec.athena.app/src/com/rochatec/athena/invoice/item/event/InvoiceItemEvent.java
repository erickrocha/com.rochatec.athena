package com.rochatec.athena.invoice.item.event;

import java.math.BigDecimal;
import java.util.EventObject;

import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.Product;

public class InvoiceItemEvent extends EventObject{

	public BigDecimal quantity;
	public Product product;
	public BigDecimal costPrice;
	public Icms icms;
	public BigDecimal ipi;
	public BigDecimal ipiBase;
	public BigDecimal lastCostPrice;
	public BigDecimal totalIcms;
		
	public InvoiceInputItem newItem;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2404577897613247843L;

	
	public InvoiceItemEvent(Object source) {
		super(source);
		this.product = (Product)source;
	}	
	
}
