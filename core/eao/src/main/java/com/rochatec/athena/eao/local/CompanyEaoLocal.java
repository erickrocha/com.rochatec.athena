package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Status;

@Local
public interface CompanyEaoLocal {
	
	public Company persist(Company company);
	
	public void remove(Company company);
	
	public Company findById(Long id);
	
	public Company findByIndex(Long id, String socialSecurity);
	
	public Company findBySocialSecurity(String socialSecurity);
	
	public List<Company> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Company> findByDateRegister(Calendar begin, Calendar end, Status status);
}
