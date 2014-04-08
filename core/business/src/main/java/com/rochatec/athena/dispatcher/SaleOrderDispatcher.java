package com.rochatec.athena.dispatcher;

import javax.ejb.Local;

import com.rochatec.athena.listeners.SaleOrderListener;
import com.rochatec.athena.model.SaleOrder;

@Local
public interface SaleOrderDispatcher {

	public void addSaleOrderListener(SaleOrderListener listener);

	public void removeSaleOrderListener(SaleOrderListener listener);

	public void save(SaleOrder saleOrder);

	public void generate(SaleOrder saleOrder);

	public void finish(SaleOrder saleOrder);

}
