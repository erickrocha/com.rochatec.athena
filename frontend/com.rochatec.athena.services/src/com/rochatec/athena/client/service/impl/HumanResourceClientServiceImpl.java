package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.facade.remote.HumanResourceFacadeRemote;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.model.Status;

public class HumanResourceClientServiceImpl implements HumanResourceClientService{
	
	private HumanResourceFacadeRemote serviceRemote = ServiceLocator.getInstance().getHumanResourceFacadeRemote();
	
	
	public Employee persist(Employee employee) {
		return serviceRemote.persist(employee);
	}

	
	public void remove(Employee employee) {
		serviceRemote.remove(employee);		
	}

	
	public Employee findEmployeeById(Long id) {
		return serviceRemote.findEmployeeById(id);
	}
	
	public Employee findEmployeeBySocialSecurity(String socialSecurity) {
		return serviceRemote.findEmployeeBySocialSecurity(socialSecurity);
	}

	
	public Job persist(Job job) {
		return serviceRemote.persist(job);
	}

	
	public void remove(Job job) {
		serviceRemote.remove(job);
	}

	
	public Job findJobById(Integer id) {
		return serviceRemote.findJobById(id);
	}

	
	public List<Job> findJobsByName(String name) {
		return serviceRemote.findJobsByName(name);
	}

	
	public List<Job> findAllJobs() {
		return serviceRemote.findAllJobs();
	}


	@Override
	public List<Employee> findEmployeeByName(String name, Calendar startDate,
			Calendar endDate, Status status) {
		return serviceRemote.findEmployeesByName(name,startDate,endDate,status);
	}


	@Override
	public List<Employee> findEmployeeByJob(Job job, Calendar startDate,
			Calendar endDate, Status status) {
		return serviceRemote.findEmployeesByJob(job,startDate,endDate, status);
	}


	@Override
	public List<Employee> findEmployeeByHireDate(Calendar startDate,
			Calendar endDate, Status status) {
		return serviceRemote.findEmployeesByHireDate(startDate,endDate, status);
	}


	@Override
	public List<Employee> findEmployeeByResignationDate(Calendar startDate,
			Calendar endDate, Status status) {
		return serviceRemote.findEmployeesByResignationDate(startDate,endDate, status);
	}


	@Override
	public List<Employee> findEmployeeByJob(String name, Calendar startDate,
			Calendar endDate, Status status) {
		return serviceRemote.findEmployeesByJob(name,startDate,endDate, status);
	}
	
	@Override
	public List<Employee> findEmployeesWithoutlogin(String name){
		return serviceRemote.findEmployeesWithoutlogin(name);
	}
	
}
