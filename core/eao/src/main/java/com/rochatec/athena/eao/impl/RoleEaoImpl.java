package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.RoleEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Role;

@Stateless
public class RoleEaoImpl extends GenericEao<Role,Serializable> implements RoleEaoLocal {

	@SuppressWarnings("unchecked")
	
	public List<Role> findAll() {
		Query query = getEntityManager().createNamedQuery("Role.findAll");
		List<Role> roles = query.getResultList();
		return roles;
	}

	
	public Role findByKey(String key) {
		Role role = super.findById(key);
		return role;
	}

}
