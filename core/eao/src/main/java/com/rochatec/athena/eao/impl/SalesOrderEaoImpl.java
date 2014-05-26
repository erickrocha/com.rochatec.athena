package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.rochatec.athena.eao.local.SalesOrderEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderItem;
import com.rochatec.athena.model.SaleOrderStatus;
import com.rochatec.metallurgical.util.CalendarUtil;

/**
 * @author erick.rocha
 * Session Bean implementation class SalesOrderLocalImpl
 */
@Stateless
public class SalesOrderEaoImpl extends GenericEao<SaleOrder,Serializable> implements SalesOrderEaoLocal {
	
	private static final Logger LOGGER = Logger.getLogger(SalesOrderEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();
	
    /**
     * Default constructor. 
     */
    public SalesOrderEaoImpl() {
    }

	
	@SuppressWarnings("unchecked")
	@Override
	public SaleOrder findById(Long id) {
		SaleOrder saleOrder = super.findById(id);
		Query query = getEntityManager().createQuery("SELECT soi FROM SaleOrderItem soi where soi.saleOrder = :saleOrder");
		query.setParameter("saleOrder", saleOrder);
		List<SaleOrderItem> items = query.getResultList();
		saleOrder.setItems(new HashSet<SaleOrderItem>(items));
		return saleOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> findByCustomer(String customerName,Calendar begin,Calendar end,SaleOrderStatus status) {
		try{
			builder = new StringBuilder("SELECT so FROM SaleOrder so LEFT JOIN FETCH so.items WHERE "); 
			params = new HashMap<String, Object>();
			builder.append("so.customer.name like :name ");
			params.put("name",customerName+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister",begin, end);
			Query query = getEntityManager().createQuery(builder.toString());
			fillParams(query);
			List<SaleOrder> saleOrders = query.getResultList();
			return clear(saleOrders);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<SaleOrder>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> findByDateRegister(Calendar begin,Calendar end,SaleOrderStatus status) {
		try{
			builder = new StringBuilder("SELECT so FROM SaleOrder so JOIN FETCH so.items WHERE "); 
			params = new HashMap<String, Object>();
			addStatusWhere(status);
			addPeriodwhere("dateRegister",begin, end);
			Query query = getEntityManager().createQuery(builder.toString());
			fillParams(query);
			List<SaleOrder> saleOrders = query.getResultList();
			return clear(saleOrders);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<SaleOrder>();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> findByEmployee(String employeeName,Calendar begin,Calendar end,SaleOrderStatus status) {
		try{
			builder = new StringBuilder("SELECT so FROM SaleOrder so JOIN FETCH so.items WHERE "); 
			params = new HashMap<String, Object>();
			builder.append("so.employee.name like :name ");
			params.put("name",employeeName+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister",begin, end);
			Query query = getEntityManager().createQuery(builder.toString());
			fillParams(query);
			List<SaleOrder> saleOrders = query.getResultList();
			return clear(saleOrders);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<SaleOrder>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> findByDateValidity(Calendar begin,Calendar end,SaleOrderStatus status) {
		try{
			builder = new StringBuilder("SELECT so FROM SaleOrder so JOIN FETCH so.items WHERE "); 
			params = new HashMap<String, Object>();
			addStatusWhere(status);
			addPeriodwhere("dateValidity",begin, end);
			Query query = getEntityManager().createQuery(builder.toString());
			fillParams(query);
			List<SaleOrder> saleOrders = query.getResultList();
			return clear(saleOrders);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<SaleOrder>();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> findByDateDelivery(Calendar begin,Calendar end,SaleOrderStatus status) {
		try{
			builder = new StringBuilder("SELECT so FROM SaleOrder so JOIN FETCH so.items WHERE "); 
			params = new HashMap<String, Object>();
			addStatusWhere(status);
			addPeriodwhere("dateDelivery",begin, end);
			Query query = getEntityManager().createQuery(builder.toString());
			fillParams(query);
			List<SaleOrder> saleOrders = query.getResultList();
			return clear(saleOrders);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<SaleOrder>();
	}
	
	private void addStatusWhere(SaleOrderStatus status){
		if (!status.equals(SaleOrderStatus.ALL)){
			builder.append("AND so.status = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(String column,Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and so."+column+" BETWEEN :begin AND :end ");
			params.put("begin",begin);
			params.put("end",end);
		}
	}
	
	private void fillParams(Query query){
		for (String param : params.keySet()){
			query.setParameter(param,params.get(param));
		}
	}

}
