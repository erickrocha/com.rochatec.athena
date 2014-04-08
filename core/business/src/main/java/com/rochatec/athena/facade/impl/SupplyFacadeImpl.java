package com.rochatec.athena.facade.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.ShipperEaoLocal;
import com.rochatec.athena.eao.local.SupplierEaoLocal;
import com.rochatec.athena.facade.local.SupplyFacadeLocal;
import com.rochatec.athena.facade.remote.SupplyFacadeRemote;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;

@Stateless
public class SupplyFacadeImpl implements SupplyFacadeLocal,SupplyFacadeRemote {
	
	@EJB
	private SupplierEaoLocal supplierEaoLocal;
	
	@EJB
	private ShipperEaoLocal shipperEaoLocal;
	
	public Supplier persist(Supplier supplier) {
		supplier = supplierEaoLocal.persist(supplier);
		return supplier;
	}	
	
	public void remove(Supplier supplier) {
		supplierEaoLocal.remove(supplier);
	}

	public Supplier findSupplierById(Long id) {
		Supplier supplier = supplierEaoLocal.findById(id);
		return supplier;
	}
	
	public Supplier findSupplierBySocialSecurity(String socialSecurity) {
		Supplier supplier = supplierEaoLocal.findBySocialSecurity(socialSecurity);
		return supplier;
	}

	public Supplier findSupplierByIndex(Long id, String socialSecurity) {
		Supplier supplier = supplierEaoLocal.findByIndex(id,socialSecurity);
		return supplier;
	}
	
	public Shipper persist(Shipper shipper) {
		shipper  = shipperEaoLocal.persist(shipper);
		return shipper;
	}

	public void remove(Shipper shipper) {
		shipperEaoLocal.remove(shipper);
	}
	
	public Shipper findShipperById(Long id) {
		Shipper shipper = shipperEaoLocal.findById(id);
		return shipper;
	}

	public List<Shipper> findShippersByName(String name,Calendar begin,Calendar end,Status status) {
		List<Shipper> shippers = shipperEaoLocal.findByName(name,begin,end,status);
		return shippers;
	}

	public Shipper findShipperBySocialSecurity(String socialSecurity) {
		Shipper carrier = shipperEaoLocal.findBySocialSecurity(socialSecurity);
		return carrier;
	}

	@Override
	public List<Supplier> findSuppliersByName(String name, Calendar begin,Calendar end, Status status) {
		List<Supplier> suppliers = supplierEaoLocal.findByName(name,begin, end, status);
		return suppliers;
	}

	@Override
	public List<Supplier> findBySuppliersDateRegister(Calendar begin,Calendar end, Status status) {
		List<Supplier> suppliers = supplierEaoLocal.findByDateRegister(begin, end, status);
		return suppliers;
	}

	@Override
	public Shipper findShipperByIdOrSocialSecurity(Long id,String socialSecurity) {
		Shipper shipper = shipperEaoLocal.findByIndex(id,socialSecurity);
		return shipper;
	}

	@Override
	public List<Shipper> findByShippersDateRegister(Calendar begin,
			Calendar end, Status status) {
		List<Shipper> shippers = shipperEaoLocal.findbyDateRegister(begin, end, status);
		return shippers;
	}
	
}
