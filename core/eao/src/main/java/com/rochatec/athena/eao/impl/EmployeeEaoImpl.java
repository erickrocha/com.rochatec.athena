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

import com.rochatec.athena.eao.local.EmployeeEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Status;
import com.rochatec.metallurgical.util.CalendarUtil;

@Stateless
public class EmployeeEaoImpl extends GenericEao<Employee, Serializable> implements EmployeeEaoLocal{	
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeEaoImpl.class);
	
	private Map<String,Object> params = new HashMap<String, Object>();
	private StringBuilder builder = new StringBuilder();
	
	public Employee findById(Long id) {
		Employee employee = super.findById(id);
		return employee;
	}
	
	public Employee findBySocialSecurity(String socialSecurity) {
		Query query = getEntityManager().createNamedQuery("Employee.findBySocialSecurity");
		query.setParameter("socialSecurity",socialSecurity);
		Employee employee = (Employee)query.getSingleResult();
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByName(String name, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT e FROM Employee e WHERE ");
			params = new HashMap<String, Object>();
			builder.append("e.name like :name ");
			params.put("name",name+"%");
			addStatusWhere(status);
			addPeriodwhere("hiredate", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Employee.class);
			fillParams(query);
			List<Employee> employees = query.getResultList();
			return employees;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Employee>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByJob(String job, Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT e FROM Employee e WHERE ");
			params = new HashMap<String, Object>();
			builder.append("e.job.name = :name ");
			params.put("name",job);
			addStatusWhere(status);
			addPeriodwhere("hiredate", begin, end);
			Query query = getEntityManager().createQuery(builder.toString(),Employee.class);
			fillParams(query);
			List<Employee> employees = query.getResultList();
			return employees;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Employee>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByHireDate(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT e FROM Employee e WHERE e.hiredate BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Employee.class);
			fillParams(query);
			List<Employee> employees = query.getResultList();
			return employees;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Employee>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByResignationDate(Calendar begin, Calendar end,
			Status status) {
		try{
			builder = new StringBuilder("SELECT e FROM Employee e WHERE e.resignationDate BETWEEN :begin AND :end ");
			params = new HashMap<String, Object>();
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			params.put("begin",begin);
			params.put("end",end);			
			addStatusWhere(status);
			Query query = getEntityManager().createQuery(builder.toString(),Employee.class);
			fillParams(query);
			List<Employee> employees = query.getResultList();
			return employees;
		}catch (Exception e) {
			LOGGER.error(builder.toString());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Employee>();
	}
	
	private void addStatusWhere(Status status){
		if (!status.equals(Status.ALL)){
			builder.append("AND e.active = :status ");
			params.put("status", status);
		}
	}
	
	private void addPeriodwhere(String field,Calendar begin,Calendar end){
		if ((begin != null && end == null) || (end != null && begin == null) || (begin != null && end != null) ){
			begin = begin == null ? CalendarUtil.getFirstHourToday() : begin;
			end = end == null ? CalendarUtil.getLastHourToday() : end;
			builder.append("and e."+field+" BETWEEN :begin AND :end ");
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
	public List<Employee> findWithoutlogin(String name) {
		String sql = "select * from employee where id not in (select nvl(employee,0) from users) and name like :name and active = 1";
		Query query = getEntityManager().createNativeQuery(sql,Employee.class);
		query.setParameter("name",name+"%");
		List<Employee> employees = query.getResultList();
		return employees;
	}

}
