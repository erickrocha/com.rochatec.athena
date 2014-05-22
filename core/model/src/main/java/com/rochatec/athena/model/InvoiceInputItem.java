package com.rochatec.athena.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "INPUT")
public class InvoiceInputItem extends AbstractInvoiceItem implements
		IProductItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566096047283877780L;

	@ManyToOne
	@JoinColumn(name = "INVOICE", nullable = false, updatable = false)
	private InvoiceInput invoice;

	@Column(name = "COST_PRICE", precision = 10, scale = 2)
	private BigDecimal costPrice = BigDecimal.ZERO;

	@OneToOne
	@JoinColumn(name = "ICMS", referencedColumnName = "ID")
	private Icms icms;

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public void setIcms(Icms icms) {
		this.icms = icms;
	}

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
		return icms;
	}

	@Override
	public BigDecimal getIpi() {
		return getProduct().getIpi();
	}

	@Override
	public BigDecimal getTotalItem() {
		return getSellPrice().multiply(getQuantity());
	}

	@Override
	public Long getProductId() {		
		return getProduct().getId();
	}
	
	public BigDecimal getTotalItems(){
		BigDecimal value = getQuantity().multiply(costPrice);
		value.setScale(2,RoundingMode.HALF_EVEN);
		return value; 				
	}
	
	public BigDecimal getTotalIcms(){
		try {
			BigDecimal total = getTotalItems();
			BigDecimal value = total.divide(new BigDecimal(100).multiply(icms != null ? icms.getPercentage() : BigDecimal.ONE));
			value.setScale(2,RoundingMode.HALF_EVEN);
			return value;
		}catch (ArithmeticException ex){
			return BigDecimal.ZERO;
		}
	}
	
}
