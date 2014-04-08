package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.model.Status;

@Local
public interface ShipperEaoLocal {
	
	public Shipper persist(Shipper carrier);
	
	public void remove(Shipper carrier);
	
	public Shipper findById(Long id);
	
	public Shipper findBySocialSecurity(String socialSecurity);
	
	public Shipper findByIndex(Long id, String socialSecurity);
	
	public List<Shipper> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Shipper> findbyDateRegister(Calendar begin, Calendar end, Status status);

}
