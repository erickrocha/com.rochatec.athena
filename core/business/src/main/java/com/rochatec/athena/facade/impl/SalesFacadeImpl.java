package com.rochatec.athena.facade.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.dispatcher.SaleOrderDispatcher;
import com.rochatec.athena.eao.local.SalesOrderEaoLocal;
import com.rochatec.athena.facade.local.SalesFacadeLocal;
import com.rochatec.athena.facade.remote.SalesFacadeRemote;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class SalesFacadeImpl implements SalesFacadeLocal,SalesFacadeRemote{
	
	@EJB
	SaleOrderDispatcher saleOrderDispatcher;
	
	@EJB
	private SalesOrderEaoLocal salesOrderEaoLocal;
	
	@Override
	public SaleOrder persist(SaleOrder saleOrder) {
		return salesOrderEaoLocal.persist(saleOrder);
	}

	@Override
	public void remove(SaleOrder saleOrder) {
		salesOrderEaoLocal.remove(saleOrder);		
	}

	@Override
	public SaleOrder findSaleOrderById(Long id) {
		SaleOrder saleOrder = salesOrderEaoLocal.findById(id);
		return saleOrder;
	}

	@Override
	public List<SaleOrder> findSaleOrdersByCustomer(String customerName,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		List<SaleOrder> saleOrders = salesOrderEaoLocal.findByCustomer(customerName,startDate,endDate,status);
		return saleOrders;
	}

	@Override
	public List<SaleOrder> findSaleOrdersByEmployee(String employeeName,Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		List<SaleOrder> saleOrders = salesOrderEaoLocal.findByEmployee(employeeName,startDate,endDate,status);
		return saleOrders;
	}

	@Override
	public List<SaleOrder> findSaleOrdersByDateRegister(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		List<SaleOrder> saleOrders = salesOrderEaoLocal.findByDateRegister(startDate,endDate,status);
		return saleOrders;
	}
	
	@Override
	public List<SaleOrder> findSaleOrdersByDateDelivery(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		List<SaleOrder> saleOrders = salesOrderEaoLocal.findByDateRegister(startDate,endDate,status);
		return saleOrders;
	}
	
	@Override
	public List<SaleOrder> findSaleOrdersByDateValidity(Calendar startDate,Calendar endDate,SaleOrderStatus status) {
		List<SaleOrder> saleOrders = salesOrderEaoLocal.findByDateRegister(startDate,endDate,status);
		return saleOrders;
	}

	@Override
	public SaleOrder save(SaleOrder saleOrder) {
		saleOrder.setStatus(SaleOrderStatus.SAVE);
		saleOrder = persist(saleOrder);
		saleOrderDispatcher.save(saleOrder);
		return saleOrder;
	}

	
	@Override
	public SaleOrder generate(SaleOrder saleOrder) {
		saleOrder.setStatus(SaleOrderStatus.GENERATED);
		saleOrder.setDateGenerate(CalendarUtil.getToday());
		saleOrder = persist(saleOrder);
		saleOrderDispatcher.generate(saleOrder);
		return saleOrder;
	}

	
	@Override
	public SaleOrder finish(SaleOrder saleOrder) {	
		saleOrder.setStatus(SaleOrderStatus.FINISHED);
		saleOrder = persist(saleOrder);
		saleOrderDispatcher.finish(saleOrder);
		return saleOrder;
	}

}
