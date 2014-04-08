package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Role;

@Local
public interface RoleEaoLocal {
	
	public List<Role> findAll();
	
	public Role findByKey(String key);
	
}	
