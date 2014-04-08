package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.NcmEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Ncm;

@Stateless
public class NcmEaoImpl extends GenericEao<Ncm,Serializable> implements NcmEaoLocal{
	
	
	
	public Ncm findById(String id) {
		Ncm ncm = super.findById(id);
		return ncm;
	}

	@SuppressWarnings("unchecked")
	
	public List<Ncm> findAll() {
		Query query = getEntityManager().createNamedQuery("Ncm.findAll");
		List<Ncm> ncms = query.getResultList();
		return ncms;
	}

	@SuppressWarnings("unchecked")
	
	public List<Ncm> findByName(String name) {
		Query query = getEntityManager().createNamedQuery("Ncm.findByDescription");
		query.setParameter("description",name);
		List<Ncm> ncms = query.getResultList();
		return ncms;
	}

}
