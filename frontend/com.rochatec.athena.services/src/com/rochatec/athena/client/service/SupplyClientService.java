package com.rochatec.athena.client.service;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;

public interface SupplyClientService {

	public Supplier persist(Supplier supplier);

	public void remove(Supplier supplier);

	public Supplier findSupplierById(Long id);

	public List<Supplier> findSuppliersByName(String name,Calendar begin,Calendar end,Status status);
	
	public List<Supplier> findBySuppliersDateRegister(Calendar begin,Calendar end,Status status);
	
	public Supplier findSupplierBySocialSecurity(String socialSecurity);
	
	public Supplier findSupplierByIndex(Long id,String socialSecurity);

	public Shipper persist(Shipper carrier);

	public void remove(Shipper carrier);

	public Shipper findShipperById(Long id);

	public Shipper findShipperBySocialSecurity(String socialSecurity);

	public List<Shipper> findShippersByName(String name,Calendar begin,Calendar end,Status status);
	
	public Shipper findShipperByIdOrSocialSecurity(Long id,String socialSecurity);
	
	public List<Shipper> findByShippersDateRegister(Calendar begin,Calendar end,Status status);
}
