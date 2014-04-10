package com.rochatec.athena.client.service;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;

public interface SalesClientService {

	public SaleOrder persist(SaleOrder saleOrder);

	public void remove(SaleOrder saleOrder);

	public SaleOrder findSaleOrderById(Long id);

	public List<SaleOrder> findSaleOrdersByCustomer(String customerName,Calendar startDate,Calendar endDate,SaleOrderStatus status);

	public List<SaleOrder> findSaleOrdersByCustomer(Customer customer,Calendar startDate,Calendar endDate,SaleOrderStatus status);

	public List<SaleOrder> findSaleOrdersByEmployee(String employeeName,Calendar startDate,Calendar endDate,SaleOrderStatus status);

	public List<SaleOrder> findSaleOrdersByEmployee(Employee employee,Calendar startDate,Calendar endDate,SaleOrderStatus status);

	public List<SaleOrder> findSaleOrdersByDateRegister(Calendar startDate,Calendar endDate,SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByDateDelivery(Calendar startDate,Calendar endDate,SaleOrderStatus status);
	
	public List<SaleOrder> findSaleOrdersByDateValidity(Calendar startDate,Calendar endDate,SaleOrderStatus status);
	
	public SaleOrder save(SaleOrder saleOrder);
	
	public SaleOrder generate(SaleOrder saleOrder);
	
	public SaleOrder finish(SaleOrder saleOrder);

}
