package com.rochatec.athena.facade.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.CustomerEaoLocal;
import com.rochatec.athena.facade.local.CRMFacadeLocal;
import com.rochatec.athena.facade.remote.CRMFacadeRemote;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

@Stateless
public class CRMFacadeImpl implements CRMFacadeLocal, CRMFacadeRemote {

	@EJB
	private CustomerEaoLocal customerEaoLocal;

	@Override
	public Customer persist(Customer customer) {
		return customerEaoLocal.persist(customer);
	}

	@Override
	public void remove(Customer customer) {
		customerEaoLocal.remove(customer);
	}

	@Override
	public Customer findCustomerById(Long id) {
		Customer customer = customerEaoLocal.findById(id);
		return customer;
	}
	
	@Override
	public Customer findCustomerBySocialSecurity(String socialSecurity) {
		Customer customer = customerEaoLocal
				.findBySocialSecurity(socialSecurity);
		return customer;
	}

	

	@Override
	public Customer findCustomerByIndex(Long id, String socialSecurity) {
		Customer customer = customerEaoLocal.findByIndex(id,socialSecurity);
		return customer;
	}

	@Override
	public List<Customer> findCustomersByName(String name, Calendar begin,
			Calendar end, Status status) {
		List<Customer> customers = customerEaoLocal.findByName(name, begin, end, status);
		return customers;
	}

	@Override
	public List<Customer> findCustomersByDateRegister(Calendar begin,
			Calendar end, Status status) {
		List<Customer> customers = customerEaoLocal.findByDateRegister(begin, end, status);
		return customers;
	}

}
