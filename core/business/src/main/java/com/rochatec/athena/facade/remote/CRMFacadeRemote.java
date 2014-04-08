package com.rochatec.athena.facade.remote;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

@Remote
public interface CRMFacadeRemote {

public Customer persist(Customer customer);
	
	public void remove(Customer customer);
	
	public Customer findCustomerById(Long id);
	
	public Customer findCustomerBySocialSecurity(String socialSecurity);
	
	public List<Customer> findCustomersByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Customer> findCustomersByDateRegister(Calendar begin, Calendar end, Status status);
	
	public Customer findCustomerByIndex(Long id, String socialSecurity);
	
}
