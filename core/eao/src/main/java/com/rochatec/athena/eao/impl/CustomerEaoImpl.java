package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.rochatec.athena.eao.local.CustomerEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;
import com.rochatec.metallurgical.util.CalendarUtil;

@Stateless
public class CustomerEaoImpl extends GenericEao<Customer,Serializable> implements CustomerEaoLocal{
			
	private static final Logger LOGGER = Logger.getLogger(CustomerEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();
	
	public Customer findById(Long id) {
		Customer customer = super.findById(id);
		return customer;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Customer> findByName(String name) {
		Query query = getEntityManager().createNamedQuery("Customer.findByName");
		query.setParameter("name",name+"%");
		List<Customer> customers = query.getResultList();
		return customers;
	}
	
	
	public Customer findBySocialSecurity(String socialSecurity) {
		Query query = getEntityManager().createNamedQuery("Customer.findBySocialSecurity");
		query.setParameter("socialSecurity",socialSecurity);
		Customer customer = (Customer)query.getSingleResult();
		return customer;
	}

	@Override
	public Customer findByIndex(Long id, String socialSecurity) {
		String sql = "SELECT c FROM Customer c WHERE c.id = :id OR c.socialSecurity = :socialSecurity";
		Query query = getEntityManager().createQuery(sql,Customer.class);
		query.setParameter("id",id);
		query.setParameter("socialSecurity",socialSecurity);
		Customer customer = (Customer)query.getSingleResult();
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByName(String name, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT c FROM Customer c WHERE ");
			params = new HashMap<String, Object>();
			builder.append("c.name like :name ");
			params.put("name",name+"%");
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Customer.class);
			fillParams(query);
			List<Customer> customers = query.getResultList();
			return customers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Customer>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByDateRegister(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT c FROM Customer c WHERE c.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Customer.class);
			fillParams(query);
			List<Customer> customers = query.getResultList();
			return customers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Customer>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND c.active = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and c.dateRegister BETWEEN :begin AND :end ");
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
