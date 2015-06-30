package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.ProvinceEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Province;

@Stateless
public class ProvinceEaoImpl extends GenericEao<Province,Serializable> implements ProvinceEaoLocal{

	@SuppressWarnings("unchecked")
	public List<Province> findAll() {
		Query query = getEntityManager().createQuery("SELECT p FROM Province p",Province.class);
		List<Province> provinces = query.getResultList();
		return provinces;
	}

}
