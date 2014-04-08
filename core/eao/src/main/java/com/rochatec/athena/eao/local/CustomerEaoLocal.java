package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

@Local
public interface CustomerEaoLocal {
	
	public Customer persist(Customer customer);
	
	public void remove(Customer customer);
	
	public Customer findById(Long id);
	
	public Customer findByIndex(Long id, String socialSecurity);
	
	public Customer findBySocialSecurity(String socialSecurity);
	
	public List<Customer> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Customer> findByDateRegister(Calendar begin, Calendar end, Status status);
	
}
