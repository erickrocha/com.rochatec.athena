package com.rochatec.athena.facade.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.EmployeeEaoLocal;
import com.rochatec.athena.eao.local.JobEaoLocal;
import com.rochatec.athena.facade.local.HumanResourceFacadeLocal;
import com.rochatec.athena.facade.remote.HumanResourceFacadeRemote;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.model.Status;

@Stateless
public class HumanResourceFacadeImpl implements HumanResourceFacadeLocal,HumanResourceFacadeRemote {

	@EJB
	private EmployeeEaoLocal employeeEaoLocal;

	@EJB
	private JobEaoLocal jobEaoLocal;

	
	public Employee persist(Employee employee) {
		return employeeEaoLocal.persist(employee);
	}

	
	public void remove(Employee employee) {
		employeeEaoLocal.remove(employee);
	}

	
	public Employee findEmployeeById(Long id) {
		Employee employee = employeeEaoLocal.findById(id);
		return employee;
	}

	
	public Employee findEmployeeBySocialSecurity(String socialSecurity) {
		Employee employee = employeeEaoLocal.findBySocialSecurity(socialSecurity);
		return employee;
	}

	
	public Job persist(Job job) {
		return jobEaoLocal.persist(job);
	}

	
	public void remove(Job job) {
		jobEaoLocal.remove(job);
	}

	
	public Job findJobById(Integer id) {
		Job job = jobEaoLocal.findById(id);
		return job;
	}

	
	public List<Job> findJobsByName(String name) {
		List<Job> jobs = jobEaoLocal.findByName(name);
		return jobs;
	}

	
	public List<Job> findAllJobs() {
		List<Job> jobs = jobEaoLocal.findAll();
		return jobs;
	}


	@Override
	public List<Employee> findEmployeesByName(String name, Calendar begin,
			Calendar end, Status status) {
		List<Employee> employees = employeeEaoLocal.findByName(name, begin, end, status);
		return employees;
	}

	@Override
	public List<Employee> findEmployeesByHireDate(Calendar begin, Calendar end,
			Status status) {
		List<Employee> employees = employeeEaoLocal.findByHireDate(begin, end, status);
		return employees;
	}


	@Override
	public List<Employee> findEmployeesByResignationDate(Calendar begin,
			Calendar end, Status status) {
		List<Employee> employees = employeeEaoLocal.findByResignationDate(begin, end, status);
		return employees;
	}


	@Override
	public List<Employee> findEmployeesByJob(String name, Calendar begin,
			Calendar end, Status status) {
		List<Employee> employees = employeeEaoLocal.findByJob(name, begin, end, status);
		return employees;
	}


	@Override
	public List<Employee> findEmployeesByJob(Job job, Calendar begin,
			Calendar end, Status status) {
		List<Employee> employees = employeeEaoLocal.findByJob(job.getName(), begin, end, status);
		return employees;
	}


	@Override
	public List<Employee> findEmployeesWithoutlogin(String name) {
		List<Employee> employees = employeeEaoLocal.findWithoutlogin(name);
		return employees;
	}

}
