package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;

@Local
public interface SupplierEaoLocal {
	
	public Supplier persist(Supplier supplier);
	
	public void remove(Supplier supplier);
	
	public Supplier findById(Long id);
	
	public List<Supplier> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Supplier> findByDateRegister(Calendar begin, Calendar end, Status status);
	
	public Supplier findBySocialSecurity(String socialSecurity);
	
	public Supplier findByIndex(Long id, String value);	
	
}
