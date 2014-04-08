package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Ncm;

@Local
public interface NcmEaoLocal {
	
	public Ncm persist(Ncm ncm);
	
	public void remove(Ncm ncm);
	
	public Ncm findById(String id);
	
	public List<Ncm> findAll();
	
	public List<Ncm> findByName(String name);
	
}
