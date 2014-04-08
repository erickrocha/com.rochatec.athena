package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Province;

@Local
public interface AddressEaoLocal {

	public Address persist(Address address);
	
	public void remove(Address address);
	
	public Address findById(Long id);
	
	public List<Address> findByStreet(String street);
	
	public List<Address> findByNeighborhood(String neighborhood);
	
	public List<Address> findByCity(String city);
	
	public List<Address> findAddress(String neighborhood, String city, Province province);
}
