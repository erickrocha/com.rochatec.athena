package com.rochatec.athena.pdv.service.model;

import java.util.HashSet;
import java.util.Set;

public class TicketIssuer {

	private String serialNumber;
	private String number;

	private Set<Ticket> tickets;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public void addTicket(Ticket ticket){
		if (this.tickets == null){
			this.tickets  =new HashSet<Ticket>(); 
		}
		this.tickets.add(ticket);
	}

}
