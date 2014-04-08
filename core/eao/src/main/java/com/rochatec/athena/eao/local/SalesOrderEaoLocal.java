package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;

@Local
public interface SalesOrderEaoLocal {
	
	public SaleOrder persist(SaleOrder saleOrder);
	
	public void remove(SaleOrder saleOrder);
	
	public SaleOrder findById(Long id);
	
	public List<SaleOrder> findByCustomer(String customerName, Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findByEmployee(String employeeName, Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findByDateRegister(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findByDateValidity(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
	public List<SaleOrder> findByDateDelivery(Calendar startDate, Calendar endDate, SaleOrderStatus status);
	
}
