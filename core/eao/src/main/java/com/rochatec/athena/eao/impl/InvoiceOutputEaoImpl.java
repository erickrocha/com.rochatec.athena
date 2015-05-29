package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.rochatec.athena.eao.local.InvoiceOutputEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class InvoiceOutputEaoImpl extends GenericEao<InvoiceOutput,Serializable> implements InvoiceOutputEaoLocal{
	
	private static final Logger LOGGER = Logger.getLogger(InvoiceOutputEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	@Override
	public InvoiceOutput findByNumber(Long number, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.number = :number ");
			params.put("number",number);
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			InvoiceOutput invoiceOutput = (InvoiceOutput)query.getSingleResult();
			return invoiceOutput;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceOutput> findByIssuer(String issuer, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.issuer.name = :issuerName ");
			params.put("issuerName",issuer+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			List<InvoiceOutput> invoices = query.getResultList();
			return clear(invoices);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceOutput> findByReceiver(String receiver, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.receiver.name = :receiverName ");
			params.put("receiverName",receiver+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			List<InvoiceOutput> invoices = query.getResultList();
			return clear(invoices);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceOutput> findByShipper(String shipper, Calendar begin,
			Calendar end, InvoiceStatus status) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.shipper.name = :shipperName ");
			params.put("shipperName",shipper+"%");
			addStatusWhere(status);
			addPeriodwhere("dateRegister", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			List<InvoiceOutput> invoices = query.getResultList();
			return clear(invoices);
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

	@Override
	public InvoiceOutput findById(Long id) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.id = :id ");
			params.put("id",id);			
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			InvoiceOutput invoice = (InvoiceOutput) query.getSingleResult();
			return invoice;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceOutput> findAllByReceiver(Customer customer) {
		try{
			builder = new StringBuilder("SELECT i FROM InvoiceOutput i LEFT JOIN FETCH i.items WHERE ");
			params = new HashMap<String, Object>();
			builder.append("i.receiver = :receiver ");
			params.put("receiver",customer);			
			Query query = getEntityManager().createQuery(builder.toString(),InvoiceOutput.class);
			fillParams(query);
			List<InvoiceOutput> invoices = query.getResultList();
			return clear(invoices);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
