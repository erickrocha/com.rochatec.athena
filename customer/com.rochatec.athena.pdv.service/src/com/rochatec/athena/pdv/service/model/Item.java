package com.rochatec.athena.pdv.service.model;

import java.math.BigDecimal;

public class Item {

	private Product product;
	private BigDecimal quantity;
	private Boolean canceled;
	
	public Item() {
		super();	
	}

	public Item(Product product, BigDecimal quantity,Boolean canceled) {
		super();
		this.product = product;
		this.quantity = quantity;		
		this.canceled = canceled;
	}
	
	public Item(Product product, BigDecimal quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.canceled = Boolean.FALSE;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return quantity.multiply(product.getPrice());
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}
	
	

}
