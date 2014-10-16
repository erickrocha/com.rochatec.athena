package com.rochatec.pos.athena.persistence.model;

import java.util.HashSet;
import java.util.Set;

public class TicketPrinter {

	private String serialNumber;
	private Long id;
	private String fabricante;
	private Set<Ticket> tickets;

	public TicketPrinter() {

	}

	public TicketPrinter(String serialNumber, Long id, String fabricante) {
		super();
		this.serialNumber = serialNumber;
		this.id = id;
		this.fabricante = fabricante;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public void addTicket(Ticket ticket){
		if (this.tickets == null){
			this.tickets = new HashSet<Ticket>();
		}
		this.tickets.add(ticket);
	}

}
