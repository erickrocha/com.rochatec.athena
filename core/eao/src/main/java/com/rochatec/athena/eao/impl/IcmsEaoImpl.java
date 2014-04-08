package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.IcmsEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Icms;

@Stateless
public class IcmsEaoImpl extends GenericEao<Icms, Serializable> implements
		IcmsEaoLocal {

	@SuppressWarnings("unchecked")
	public List<Icms> findAll() {
		Query query = getEntityManager().createNamedQuery("Icms.findAll");
		List<Icms> icms = query.getResultList();
		return icms;
	}

}
