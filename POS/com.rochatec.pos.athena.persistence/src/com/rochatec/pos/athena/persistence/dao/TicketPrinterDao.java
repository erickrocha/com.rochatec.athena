package com.rochatec.pos.athena.persistence.dao;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.TicketPrinter;

public interface TicketPrinterDao {

	public void persist(TicketPrinter ticketPrinter);
	
	public void remove(TicketPrinter ticketPrinter);
	
	public TicketPrinter findBySerialNumber(String serialNumber);
	
	public List<TicketPrinter> findAll();
}
