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

import com.rochatec.athena.eao.local.ShipperEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.model.Status;
import com.rochatec.metallurgical.util.CalendarUtil;

@Stateless
public class ShipperEaoImpl extends GenericEao<Shipper, Serializable> implements ShipperEaoLocal {
	
	private static final Logger LOGGER = Logger.getLogger(ShipperEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	public Shipper findById(Long id) {
		Shipper carrier = super.findById(id);
		return carrier;
	}

	public Shipper findBySocialSecurity(String socialSecurity) {
		Query query = getEntityManager().createNamedQuery(
				"Shipper.findBySocialSecurity");
		query.setParameter("socialSecurity", socialSecurity);
		Shipper carrier = (Shipper) query.getSingleResult();
		return carrier;
	}

	@Override
	public Shipper findByIndex(Long id, String socialSecurity) {
		Query query = getEntityManager().createQuery("SELECT s FROM Shipper s WHERE s.id = :id OR s.socialSecurity = :socialSecurity",Shipper.class);
		query.setParameter("id",id);
		query.setParameter("socialSecurity", socialSecurity);
		Shipper shipper =  (Shipper) query.getSingleResult();
		return shipper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shipper> findByName(String name, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT s FROM Shipper s WHERE ");
			params = new HashMap<String, Object>();
			builder.append("s.businessName like :businessName ");
			params.put("businessName",name);
			builder.append("OR s.comercialName like :comercialName ");
			params.put("comercialName",name);
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Shipper.class);
			fillParams(query);
			List<Shipper> shippers = query.getResultList();
			return shippers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Shipper>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shipper> findbyDateRegister(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT s FROM Shipper s WHERE s.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Shipper.class);
			fillParams(query);
			List<Shipper> shippers = query.getResultList();
			return shippers;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Shipper>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND s.active = :status ");
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
