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

import com.rochatec.athena.eao.local.CompanyEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.util.CalendarUtil;

@Stateless
public class CompanyEaoLocalImpl extends GenericEao<Company,Serializable> implements CompanyEaoLocal{
	
	private static final Logger LOGGER = Logger.getLogger(CompanyEaoLocalImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();
	
	@Override
	public Company findById(Long id) {
		Company company = super.findById(id);
		return company;
	}

	@Override
	public Company findByIndex(Long id, String socialSecurity) {
		String hql = "SELECT c FROM Company c WHERE c.socialSecurity = :socialSecurity OR id = :id";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("socialSecurity",socialSecurity);
		query.setParameter("id",id);
		Company company = (Company)query.getSingleResult();
		return company;
	}

	@Override
	public Company findBySocialSecurity(String socialSecurity) {
		String hql = "SELECT c FROM Company c WHERE c.socialSecurity = :socialSecurity";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("socialSecurity",socialSecurity);
		Company company = (Company)query.getSingleResult();
		return company;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findByName(String name, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT c FROM Company c WHERE ");
			params = new HashMap<String, Object>();
			builder.append("c.name like :name ");
			params.put("name",name+"%");
			addStatusWhere(status);
			addPeriodwhere(begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Company.class);
			fillParams(query);
			List<Company> companies = query.getResultList();
			return companies;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Company>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findByDateRegister(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT c FROM Company c WHERE c.dateRegister BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Company.class);
			fillParams(query);
			List<Company> companies = query.getResultList();
			return companies;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Company>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND c.status = :status ");
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
