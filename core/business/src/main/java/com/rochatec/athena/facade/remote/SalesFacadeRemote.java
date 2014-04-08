package com.rochatec.athena.facade.remote;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;

@Remote
public interface SalesFacadeRemote {

	public SaleOrder persist(SaleOrder saleOrder);

	public void remove(SaleOrder saleOrder);

	public SaleOrder findSaleOrderById(Long id);

	public List<SaleOrder> findSaleOrdersByCustomer(String customerName, Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByEmployee(String employeeName, Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByDateRegister(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByDateDelivery(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByDateValidity(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public SaleOrder save(SaleOrder saleOrder);
	
	public SaleOrder generate(SaleOrder saleOrder);
	
	public SaleOrder finish(SaleOrder saleOrder);
}
