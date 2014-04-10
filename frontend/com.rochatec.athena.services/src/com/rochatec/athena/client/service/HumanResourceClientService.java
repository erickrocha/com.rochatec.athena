package com.rochatec.athena.client.service;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.model.Status;

public interface HumanResourceClientService {

	public Employee persist(Employee employee);

	public void remove(Employee employee);

	public Employee findEmployeeById(Long id);

	public List<Employee> findEmployeeByName(String name,Calendar startDate,Calendar endDate,Status status);
	
	public List<Employee> findEmployeeByJob(Job job,Calendar startDate,Calendar endDate,Status status);
	
	public List<Employee> findEmployeeByJob(String name,Calendar startDate,Calendar endDate,Status status);
	
	public List<Employee> findEmployeeByHireDate(Calendar startDate,Calendar endDate,Status status);
	
	public List<Employee> findEmployeeByResignationDate(Calendar startDate,Calendar endDate,Status status);

	public Employee findEmployeeBySocialSecurity(String socialSecurity);
	
	public Job persist(Job job);
	
	public void remove(Job job);
	
	public Job findJobById(Integer id);
	
	public List<Job> findJobsByName(String name);
	
	public List<Job> findAllJobs();
	
	public List<Employee> findEmployeesWithoutlogin(String name);
}
