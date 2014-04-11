package com.rochatec.athena.listeners.impl;

import javax.ejb.Stateless;

import com.rochatec.athena.events.SaleOrderEvent;
import com.rochatec.athena.listeners.SaleOrderListener;

@Stateless
public class SaleOrderListenerimpl implements SaleOrderListener{

	@Override
	public void generate(SaleOrderEvent event) {
	}

	@Override
	public void save(SaleOrderEvent event) {
	}

	@Override
	public void finish(SaleOrderEvent event) {
	}

}
