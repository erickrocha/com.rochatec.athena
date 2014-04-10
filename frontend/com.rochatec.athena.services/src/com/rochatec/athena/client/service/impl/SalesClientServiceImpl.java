package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.SalesClientService;
import com.rochatec.athena.facade.remote.SalesFacadeRemote;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;

public class SalesClientServiceImpl implements SalesClientService{
	
	private SalesFacadeRemote remote = ServiceLocator.getInstance().getSalesFacadeRemote();
	
	
	public SaleOrder persist(SaleOrder saleOrder) {
		return remote.persist(saleOrder);
	}

	
	public void remove(SaleOrder saleOrder) {
		remote.remove(saleOrder);
	}

	
	public SaleOrder findSaleOrderById(Long id) {
		return remote.findSaleOrderById(id);
	}

	
	public List<SaleOrder> findSaleOrdersByCustomer(String customerName,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByCustomer(customerName,startDate,endDate,status);
	}

	
	public List<SaleOrder> findSaleOrdersByEmployee(String employeeName,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByEmployee(employeeName,startDate,endDate,status);
	}

	
	public List<SaleOrder> findSaleOrdersByCustomer(Customer customer,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByCustomer(customer.getName(),startDate,endDate,status);
	}

	
	public List<SaleOrder> findSaleOrdersByEmployee(Employee employee,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByEmployee(employee.getName(),startDate,endDate,status);
	}


	@Override
	public List<SaleOrder> findSaleOrdersByDateRegister(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByDateRegister(startDate, endDate, status);
	}


	@Override
	public List<SaleOrder> findSaleOrdersByDateDelivery(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByDateDelivery(startDate, endDate, status);
	}


	@Override
	public List<SaleOrder> findSaleOrdersByDateValidity(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		return remote.findSaleOrdersByDateValidity(startDate, endDate, status);
	}


	@Override
	public SaleOrder save(SaleOrder saleOrder) {
		return remote.save(saleOrder);
	}


	@Override
	public SaleOrder generate(SaleOrder saleOrder) {
		return remote.generate(saleOrder);
	}


	@Override
	public SaleOrder finish(SaleOrder saleOrder) {
		return remote.finish(saleOrder);
	}
	
}
