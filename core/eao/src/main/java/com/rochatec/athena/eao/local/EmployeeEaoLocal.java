package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Status;

@Local
public interface EmployeeEaoLocal {
	
	public Employee persist(Employee employee);
	
	public void remove(Employee employee);
	
	public Employee findById(Long id);
	
	public List<Employee> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Employee> findByJob(String job, Calendar begin, Calendar end, Status status);
	
	public List<Employee> findByHireDate(Calendar begin, Calendar end, Status status);
	
	public List<Employee> findByResignationDate(Calendar begin, Calendar end, Status status);
	
	public Employee findBySocialSecurity(String socialSecurity);
	
	public List<Employee> findWithoutlogin(String name);

}
