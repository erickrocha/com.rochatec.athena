package com.rochatec.pos.athena.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ticket {

	private Date dateCreated;
	private String counter;
	private Customer customer;
	private List<TicketItem> items;
	private Set<Payment> payments;
	
	public Ticket() {
		
	}
	
	public Ticket(Date dateCreated, String counter, Customer customer) {
		super();
		this.dateCreated = dateCreated;
		this.counter = counter;
		this.customer = customer;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<TicketItem> getItems() {
		return items;
	}
	public void setItems(List<TicketItem> items) {
		this.items = items;
	}
	
	public void addItem(TicketItem item){
		if (this.items == null){
			this.items = new ArrayList<TicketItem>();
		}
		this.items.add(item);
	}
	
	public void addPayment(Payment payment){
		if (this.payments == null){
			this.payments = new HashSet<Payment>();
		}
		this.payments.add(payment);
	}
	
	
}
