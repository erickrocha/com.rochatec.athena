package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.rochatec.athena.eao.local.InvoiceInputEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class InvoiceInputEaoImpl extends GenericEao<InvoiceInput, Serializable> implements InvoiceInputEaoLocal{

	private static final Logger LOGGER = Logger.getLogger(InvoiceInputEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	@Override
	public InvoiceInput findByNumber(Long number, Calendar begin, Calendar end,
			InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceInput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.number = :number ");
			params.put("number",number);
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceInput.class);
			fillParams(query);
			InvoiceInput invoiceInput = (InvoiceInput)query.getSingleResult();
			return invoiceInput;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceInput> findByIssuer(String issuer, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceInput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.issuer.name = :issuerName ");
			params.put("issuerName",issuer+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceInput.class);
			fillParams(query);
			List<InvoiceInput> invoices = query.getResultList();
			return clear(invoices);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceInput> findByReceiver(String receiver, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceInput i WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.receiver.name = :receiverName ");
			params.put("receiverName",receiver+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceInput.class);
			fillParams(query);
			List<InvoiceInput> invoices = query.getResultList();
			return invoices;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceInput> findByShipper(String shipper, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceInput i WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.shipper.name = :shipperName ");
			params.put("shipperName",shipper+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceInput.class);
			fillParams(query);
			List<InvoiceInput> invoices = query.getResultList();
			return invoices;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	private void addStatusWhere(InvoiceStatus status){
		if (!status.equals(InvoiceStatus.ALL)){
			builder.append("AND i.status = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(String field,Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and i."+field+" BETWEEN :begin AND :end ");
			params.put("begin",begin);
			params.put("end",end);
		}
	}
	
	private void fillParams(Query query){
		for (String param : params.keySet()){
			query.setParameter(param,params.get(param));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceInput> findAllByIssuer(Supplier supplier) {
		String hql = "SELECT i FROM InvoiceInput i LEFT JOIN FETCH i.items WHERE i.issuer = :supplier ";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("supplier",supplier);
		List<InvoiceInput> invoices = query.getResultList();
		return clear(invoices);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InvoiceInput findById(Long id) {
		String hql = "SELECT i FROM InvoiceInput i LEFT JOIN FETCH i.items WHERE i.id = :id";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("id",id);
		List<InvoiceInput> invoices = query.getResultList();
		return clear(invoices).get(0);
	}
	
}
