package com.rochatec.pos.athena.persistence.repository;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.Role;


public interface IRoleRepository {
	
	public List<Role> findAll();
	
	public Role findByKey(String key);
	
}	
