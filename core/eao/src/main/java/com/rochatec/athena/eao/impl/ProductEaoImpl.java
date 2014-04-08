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

import com.rochatec.athena.eao.local.ProductEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.Status;
import com.rochatec.metallurgical.util.CalendarUtil;

@Stateless
public class ProductEaoImpl extends GenericEao<Product, Serializable> implements ProductEaoLocal {
	
	private static final Logger LOGGER = Logger.getLogger(ProductEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	public Product findById(Long id) {
		Product product = super.findById(id);
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByName(String name, Calendar begin, Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM Product p WHERE  ");
			params = new HashMap<String, Object>();			
			builder.append("p.name like :name OR p.shortName like :shortName ");
			params.put("name",name+"%");
			params.put("shortName",name+"%");			
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Product.class);
			fillParams(query);
			List<Product> products = query.getResultList();
			return products;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Product>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByCategory(String category, Calendar begin,Calendar end, Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM Product p WHERE ");
			params = new HashMap<String, Object>();
			builder.append("p.category.name = :name ");
			params.put("name",category);
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Product.class);
			fillParams(query);
			List<Product> products = query.getResultList();
			return products;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Product>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByDateRegister(Calendar begin, Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM Product p WHERE p.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Product.class);
			fillParams(query);
			List<Product> products = query.getResultList();
			return products;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Product>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND p.active = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and p.dateRegister BETWEEN :begin AND :end ");
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
