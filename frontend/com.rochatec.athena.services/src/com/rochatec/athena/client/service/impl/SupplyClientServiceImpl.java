package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.facade.remote.SupplyFacadeRemote;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;

public class SupplyClientServiceImpl implements SupplyClientService {

	private SupplyFacadeRemote serviceRemote = ServiceLocator.getInstance().getSupplyFacadeRemote();

	
	public Supplier persist(Supplier supplier) {
		return serviceRemote.persist(supplier);
	}

	
	public void remove(Supplier supplier) {
		serviceRemote.remove(supplier);
	}

	
	public Supplier findSupplierById(Long id) {
		return serviceRemote.findSupplierById(id);
	}
	
	public Supplier findSupplierBySocialSecurity(String socialSecurity) {
		return serviceRemote.findSupplierBySocialSecurity(socialSecurity);
	}

	
	public Supplier findSupplierByIndex(Long id, String socialSecurity) {
		return serviceRemote.findSupplierByIndex(id, socialSecurity);
	}

	
	public Shipper persist(Shipper shipper) {
		return serviceRemote.persist(shipper);
	}

	
	public void remove(Shipper shipper) {
		serviceRemote.remove(shipper);
	}

	
	public Shipper findShipperById(Long id) {
		return serviceRemote.findShipperById(id);
	}

	
	public Shipper findShipperBySocialSecurity(String socialSecurity) {
		return serviceRemote.findShipperBySocialSecurity(socialSecurity);
	}


	@Override
	public List<Supplier> findSuppliersByName(String name, Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findSuppliersByName(name, begin, end, status);
	}


	@Override
	public List<Supplier> findBySuppliersDateRegister(Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findBySuppliersDateRegister(begin, end, status);
	}


	@Override
	public Shipper findShipperByIdOrSocialSecurity(Long id,String socialSecurity) {
		return serviceRemote.findShipperByIdOrSocialSecurity(id, socialSecurity);
	}


	@Override
	public List<Shipper> findShippersByName(String name, Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findShippersByName(name, begin, end, status);
	}


	@Override
	public List<Shipper> findByShippersDateRegister(Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findByShippersDateRegister(begin, end, status);
	}

}
