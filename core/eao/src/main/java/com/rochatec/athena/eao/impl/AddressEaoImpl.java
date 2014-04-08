package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.AddressEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Province;

@Stateless
public class AddressEaoImpl extends GenericEao<Address,Serializable> implements AddressEaoLocal{

	public Address findById(Long id) {
		Address address = super.findById(id);
		return address;
	}

	@SuppressWarnings("unchecked")
	public List<Address> findByStreet(String street) {
		Query query = getEntityManager().createNamedQuery("Address.findByStreet");
		query.setParameter("street",street);
		List<Address> addresses = query.getResultList();
		return addresses;
	}

	@SuppressWarnings("unchecked")
	public List<Address> findByNeighborhood(String neighborhood) {
		Query query = getEntityManager().createNamedQuery("Address.findByNeighborhood");
		query.setParameter("neighborhood",neighborhood);
		List<Address> addresses = query.getResultList();
		return addresses;
	}

	@SuppressWarnings("unchecked")
	public List<Address> findByCity(String city) {
		Query query = getEntityManager().createNamedQuery("Address.findByCity");
		query.setParameter("city",city);
		List<Address> addresses = query.getResultList();
		return addresses;
	}

	@SuppressWarnings("unchecked")
	public List<Address> findAddress(String neighborhood, String city,
			Province province) {
		Query query = getEntityManager().createNamedQuery("Address.findAddress");
		query.setParameter("neighborhood",neighborhood);
		query.setParameter("city",city);
		query.setParameter("province",province);
		List<Address> addresses = query.getResultList();
		return addresses;
	}

}
