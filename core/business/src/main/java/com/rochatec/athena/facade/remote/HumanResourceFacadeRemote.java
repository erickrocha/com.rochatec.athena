package com.rochatec.athena.facade.remote;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.model.Status;

@Remote
public interface HumanResourceFacadeRemote {

	public Employee persist(Employee employee);

	public void remove(Employee employee);

	public Employee findEmployeeById(Long id);
	
	public List<Employee> findEmployeesByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Employee> findEmployeesByJob(Job job, Calendar begin, Calendar end, Status status);
	
	public List<Employee> findEmployeesByJob(String name, Calendar begin, Calendar end, Status status);
	
	public List<Employee> findEmployeesByHireDate(Calendar begin, Calendar end, Status status);
	
	public List<Employee> findEmployeesByResignationDate(Calendar begin, Calendar end, Status status);

	public Employee findEmployeeBySocialSecurity(String socialSecurity);
	
	public Job persist(Job job);
	
	public void remove(Job job);
	
	public Job findJobById(Integer id);
	
	public List<Job> findJobsByName(String name);
	
	public List<Job> findAllJobs();
	
	public List<Employee> findEmployeesWithoutlogin(String name);
	
}
