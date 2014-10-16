package com.rochatec.pos.athena.persistence.model;

import java.math.BigDecimal;

public class TicketItem {

	private Long id;
	private Product product;
	private BigDecimal quantity;
	private Boolean canceled;
	
	public TicketItem() {

	}

	public TicketItem(Long id, Product product, BigDecimal quantity,
			Boolean canceled) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.canceled = canceled;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}
	
	public BigDecimal getTotalItem(){
		return product.getPrice().multiply(quantity);
	}

}
