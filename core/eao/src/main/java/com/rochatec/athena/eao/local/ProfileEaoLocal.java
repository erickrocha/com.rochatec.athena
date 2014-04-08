package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Profile;

@Local
public interface ProfileEaoLocal {

	public Profile findById(Long id);

	public List<Profile> findByName(String name);
	
	public Profile findByExactName(String name);

	public List<Profile> findAll();
	
	public Profile persist(Profile profile);
	
	public void remove(Profile profile);
	
	public boolean isExists(String name);
}
