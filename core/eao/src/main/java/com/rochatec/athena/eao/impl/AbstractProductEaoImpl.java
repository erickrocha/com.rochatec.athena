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

import com.rochatec.athena.eao.local.AbstractProductEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class AbstractProductEaoImpl extends GenericEao<AbstractProduct,Serializable> implements AbstractProductEaoLocal{
	
	private static final Logger LOGGER = Logger.getLogger(AbstractProductEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	@Override
	public AbstractProduct findById(Long id) {
		AbstractProduct abstractProduct = super.findById(id);
		return abstractProduct;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractProduct> findByName(String name,Calendar begin,Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT ap FROM AbstractProduct ap WHERE  ");
			params = new HashMap<String, Object>();
			builder.append("p.name like :name OR p.shortName like :shortName");
			params.put("name",name+"%");
			params.put("shortName",name+"%");
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),AbstractProduct.class);
			fillParams(query);
			List<AbstractProduct> products = query.getResultList();
			return products;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<AbstractProduct>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractProduct> findByCategory(String category,Calendar begin,Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT ap FROM AbstractProduct ap WHERE  ");
			params = new HashMap<String, Object>();
			builder.append("p.category.name = :name ");
			params.put("name",category);
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),AbstractProduct.class);
			fillParams(query);
			List<AbstractProduct> products = query.getResultList();
			return products;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<AbstractProduct>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND ap.active = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and ap.dateRegister BETWEEN :begin AND :end ");
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
