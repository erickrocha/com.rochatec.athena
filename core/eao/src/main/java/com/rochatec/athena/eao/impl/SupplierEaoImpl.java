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

import com.rochatec.athena.eao.local.SupplierEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;
import com.rochatec.metallurgical.util.CalendarUtil;

@Stateless
public class SupplierEaoImpl extends GenericEao<Supplier,Serializable> implements SupplierEaoLocal{
	
	private static final Logger LOGGER = Logger.getLogger(SupplierEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();
	
	public Supplier findById(Long id) {
		Supplier supplier = super.findById(id);
		return supplier;
	}

	public Supplier findBySocialSecurity(String socialSecurity) {
		Query query = getEntityManager().createNamedQuery("Supplier.findBySocialSecurity");
		query.setParameter("socialSecurity", socialSecurity);
		Supplier supplier = (Supplier)query.getSingleResult();
		return supplier;
	}
	
	public Supplier findByIndex(Long id,String param){
		Query query = getEntityManager().createNamedQuery("Supplier.findByIdOrSocialSecurity");
		query.setParameter("id",id);
		query.setParameter("socialSecurity",param);
		Supplier supplier = (Supplier) query.getSingleResult();
		return supplier;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findByName(String name, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT s FROM Supplier s WHERE ");
			params = new HashMap<String, Object>();
			builder.append("s.companyName like :companyName ");
			params.put("companyName",name);
			builder.append("OR s.tradeName like :tradeName ");
			params.put("tradeName", name);
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Supplier.class);
			fillParams(query);
			List<Supplier> suppliers = query.getResultList();
			return suppliers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Supplier>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findByDateRegister(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT s FROM Supplier s WHERE s.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Employee.class);
			fillParams(query);
			List<Supplier> suppliers = query.getResultList();
			return suppliers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Supplier>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND s.status = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and s.dateRegister BETWEEN :begin AND :end ");
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
