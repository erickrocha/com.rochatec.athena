package com.rochatec.athena.pdv.service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {

	private Long id;
	private String coo;
	private Boolean canceled;
	private Date dateCreated;
	
	private List<Item> items;
	private List<Payment> payments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoo() {
		return coo;
	}

	public void setCoo(String coo) {
		this.coo = coo;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void addItem(Item item) {
		if (this.items == null) {
			this.items = new ArrayList<Item>();
		}
		this.items.add(item);
	}

	public void addPayment(Payment payment) {
		if (this.payments == null) {
			this.payments = new ArrayList<Payment>();
		}
		this.payments.add(payment);

	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : this.items){
			total = total.add(item.getTotal());
		}
		return total;
	}

}
