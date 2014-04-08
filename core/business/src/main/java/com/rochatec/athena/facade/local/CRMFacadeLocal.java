package com.rochatec.athena.facade.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

@Local
public interface CRMFacadeLocal {

	public Customer persist(Customer customer);
	
	public void remove(Customer customer);
	
	public Customer findCustomerById(Long id);
	
	public Customer findCustomerBySocialSecurity(String socialSecurity);
	
	public List<Customer> findCustomersByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Customer> findCustomersByDateRegister(Calendar begin, Calendar end, Status status);
	
	public Customer findCustomerByIndex(Long id, String socialSecurity);
}
