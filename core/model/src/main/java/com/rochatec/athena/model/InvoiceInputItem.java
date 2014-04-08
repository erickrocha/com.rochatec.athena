package com.rochatec.athena.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="INPUT")
public class InvoiceInputItem extends AbstractInvoiceItem implements IProductItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566096047283877780L;
	
	@ManyToOne
	@JoinColumn(name = "INVOICE", nullable = false, updatable = false)	
	private InvoiceInput invoice;

	public InvoiceInput getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceInput invoice) {
		this.invoice = invoice;
	}
	
	@Override
	public String toString() {		
		return getProduct().getName();
	}

	@Override
	public String getLabel() {
		return getProduct().getName();
	}

	@Override
	public BigDecimal getSellPrice() {
		return getProduct().getSellprice();
	}

	@Override
	public Object getParent() {
		return getInvoice();
	}

	@Override
	public Icms getIcms() {
		return getProduct().getIcms();
	}

	@Override
	public BigDecimal getIpi() {
		return new BigDecimal(getProduct().getIpi());
	}

	@Override
	public BigDecimal getTotalItem() {		
		return getSellPrice().multiply(getQuantity());
	}
}
