package com.rochatec.athena.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "INPUT")
public class InvoiceInputItem extends AbstractInvoiceItem {

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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceInputItem other = (InvoiceInputItem) obj;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		return true;
	}

}
