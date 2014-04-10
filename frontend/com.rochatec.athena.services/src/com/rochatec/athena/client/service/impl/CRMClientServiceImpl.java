package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.CRMClientService;
import com.rochatec.athena.facade.remote.CRMFacadeRemote;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

public class CRMClientServiceImpl implements CRMClientService{
	
	private CRMFacadeRemote serviceRemote = ServiceLocator.getInstance().getCrmFacadeRemote();	
	
	@Override
	public Customer persist(Customer customer) {
		return serviceRemote.persist(customer);
	}

	@Override
	public void remove(Customer customer) {
		serviceRemote.remove(customer);
	}

	@Override
	public Customer findCustomerById(Long id) {
		return serviceRemote.findCustomerById(id);
	}

	@Override
	public Customer findCustomerBySocialSecurity(String socialSecurity) {
		return serviceRemote.findCustomerBySocialSecurity(socialSecurity);
	}


	@Override
	public Customer findCustomerByIndex(Long id, String socialSecurity) {
		return serviceRemote.findCustomerByIndex(id, socialSecurity);
	}


	@Override
	public List<Customer> findCustomersByName(String name, Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findCustomersByName(name, begin, end, status);
	}


	@Override
	public List<Customer> findCustomersByDateRegister(Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findCustomersByDateRegister(begin, end, status);
	}

}
