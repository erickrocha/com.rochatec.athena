package com.rochatec.athena.pdv.lib.model;

import java.math.BigDecimal;

public class Item {

	private Long id;
	private String label;
	private BigDecimal quantity;
	private String quantityType;
	private Tax tax;
	private Discount discount;
	
	public Item(Long id, String label, BigDecimal quantity,
			String quantityType, Tax tax, Discount discount) {
		super();
		this.id = id;
		this.label = label;
		this.quantity = quantity;
		this.quantityType = quantityType;
		this.tax = tax;
		this.discount = discount;
	}
	
	public Item() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

}
