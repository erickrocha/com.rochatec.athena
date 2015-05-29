package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.ProfileEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Profile;

@Stateless
public class ProfileEaoImpl extends GenericEao<Profile,Serializable> implements ProfileEaoLocal{

	public Profile findById(Long id) {
		Profile profile = super.findById(id);
		return profile;
	}

	@SuppressWarnings("unchecked")
	
	public List<Profile> findByName(String name) {
		Query query = getEntityManager().createQuery("SELECT p FROM Profile p WHERE p.name like :name",Profile.class);
		query.setParameter("name",name);
		List<Profile> profiles = query.getResultList();
		return profiles;
	}

	@SuppressWarnings("unchecked")
	
	public List<Profile> findAll() {
		Query query = getEntityManager().createQuery("SELECT p FROM Profile p",Profile.class);
		List<Profile> profiles = query.getResultList();
		return profiles;
	}

	@Override
	public Profile findByExactName(String name) {
		String hql = "SELECT p FROM Profile p WHERE p.name = :name";
		Query query = getEntityManager().createNamedQuery(hql,Profile.class);
		query.setParameter("name",name);
		Profile profile = (Profile)query.getSingleResult();
		return profile;
	}

	@Override
	public boolean isExists(String name) {
		Profile profile = findByExactName(name);
		if (profile != null)
			return true;
		return false;
	}
	
	

}
