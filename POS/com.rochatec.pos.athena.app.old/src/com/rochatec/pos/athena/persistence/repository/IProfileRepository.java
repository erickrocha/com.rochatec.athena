package com.rochatec.pos.athena.persistence.repository;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.Profile;

public interface IProfileRepository {

	public Profile findById(Long id);

	public List<Profile> findByName(String name);
	
	public Profile findByExactName(String name);

	public List<Profile> findAll();
	
	public Profile persist(Profile profile);
	
	public void remove(Profile profile);
	
	public boolean isExists(String name);
}
