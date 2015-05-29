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

import com.rochatec.athena.eao.local.ProductSetEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class ProductSetEaoImpl extends GenericEao<ProductSet,Serializable> implements ProductSetEaoLocal{
	
	private static final Logger LOGGER = Logger.getLogger(ProductSetEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();

	@Override
	public ProductSet findById(Long id) {
		builder = new StringBuilder("SELECT p FROM ProductSet p LEFT JOIN FETCH  p.children LEFT JOIN FETCH p.tasks WHERE p.id = :id");
		Query query = getEntityManager().createQuery(builder.toString(),ProductSet.class);
		query.setParameter("id", id);
		ProductSet productSet = (ProductSet)query.getSingleResult();
		return productSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductSet> findByName(String name, Calendar begin,Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM ProductSet p LEFT JOIN FETCH  p.children LEFT JOIN FETCH p.humanTasks WHERE ");
			params = new HashMap<String, Object>();
			builder.append("p.name like :name ");
			params.put("name",name+"%");
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),ProductSet.class);
			fillParams(query);
			List<ProductSet> productSets = query.getResultList();
			return clear(productSets);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<ProductSet>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductSet> findByCategory(String category, Calendar begin,Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM ProductSet p LEFT JOIN FETCH  p.children LEFT JOIN FETCH p.humanTasks WHERE ");
			params = new HashMap<String, Object>();
			builder.append("e.category.name = :name ");
			params.put("name",category);
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),ProductSet.class);
			fillParams(query);
			List<ProductSet> productSets = query.getResultList();
			return clear(productSets);
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<ProductSet>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductSet> findByDateRegister(Calendar begin, Calendar end,Status status) {
		try{
			builder = new StringBuilder("SELECT p FROM ProductSet p LEFT JOIN FETCH  p.children LEFT JOIN FETCH p.humanTasks WHERE p.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),ProductSet.class);
			fillParams(query);
			List<ProductSet> productSets = query.getResultList();
			return productSets;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<ProductSet>();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductSetItem> findItems(ProductSet productSet) {
		String sql = "SELECT pi FROM ProductSetItem pi where pi.parent = :parent";
		Query query = getEntityManager().createNamedQuery(sql,ProductSetItem.class);
		query.setParameter("parent",productSet);
		List<ProductSetItem> items = query.getResultList();
		return items;
	}

}
