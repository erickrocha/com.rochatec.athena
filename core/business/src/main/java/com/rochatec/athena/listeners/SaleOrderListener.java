package com.rochatec.athena.listeners;

import javax.ejb.Local;

import com.rochatec.athena.events.SaleOrderEvent;

@Local
public interface SaleOrderListener {

	public void generate(SaleOrderEvent event);
	
	public void save(SaleOrderEvent event);
	
	public void finish(SaleOrderEvent event);
}
