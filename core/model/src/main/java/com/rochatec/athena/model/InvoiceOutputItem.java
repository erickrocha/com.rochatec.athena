package com.rochatec.athena.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "OUTPUT")
public class InvoiceOutputItem extends AbstractInvoiceItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586938092582794719L;
	
	@ManyToOne
	@JoinColumn(name = "INVOICE", nullable = false, updatable = false)
	private InvoiceOutput invoice;	

	public InvoiceOutput getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceOutput invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return getProduct().getName();
	}
	
	public BigDecimal getTotalIcms(){
		try {
			BigDecimal total = getTotalItems();
			BigDecimal value = total.divide(new BigDecimal(100).multiply(getIcms() != null ? getIcms().getPercentage() : BigDecimal.ONE));
			value.setScale(2,RoundingMode.HALF_EVEN);
			return value;
		}catch (ArithmeticException ex){
			return BigDecimal.ZERO;
		}
	}
	
	public BigDecimal getTotalItems(){
		BigDecimal value = getQuantity().multiply(getCostPrice());
		value.setScale(2,RoundingMode.HALF_EVEN);
		return value; 				
	}

}
