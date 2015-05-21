package com.rochatec.pos.athena.persistence.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rochatec.pos.athena.persistence.model.Profile;
import com.rochatec.pos.athena.persistence.repository.IProfileRepository;

@Repository
public class ProfileRepositoryImpl extends GenericRepository<Profile,Serializable> implements IProfileRepository{

	@Override
	public Profile findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile findByExactName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}



}
