package com.rochatec.athena.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.AddressEaoLocal;
import com.rochatec.athena.eao.local.MenuEaoLocal;
import com.rochatec.athena.eao.local.NatureOfOperationEaoLocal;
import com.rochatec.athena.eao.local.ProvinceEaoLocal;
import com.rochatec.athena.facade.local.ResourcefacadeLocal;
import com.rochatec.athena.facade.remote.ResourceFacadeRemote;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.model.Province;

@Stateless
public class ResourceFacadeImpl implements ResourcefacadeLocal,
		ResourceFacadeRemote {

	@EJB
	private ProvinceEaoLocal provinceEaoLocal;

	@EJB
	private NatureOfOperationEaoLocal natureOperationEaoLocal;

	@EJB
	private AddressEaoLocal addressEaoLocal;
	
	@EJB
	private MenuEaoLocal menuEaoLocal;

	public List<Province> findAllProvinces() {
		List<Province> provinces = provinceEaoLocal.findAll();
		return provinces;
	}

	public NatureOfOperation findNatureOperationByCode(String code) {
		NatureOfOperation natureOperation = natureOperationEaoLocal
				.findByCode(code);
		return natureOperation;
	}

	public List<NatureOfOperation> findAllNatureOperations() {
		List<NatureOfOperation> natureOperations = natureOperationEaoLocal
				.findAll();
		return natureOperations;
	}

	public NatureOfOperation persist(NatureOfOperation natureOperation) {
		natureOperation = natureOperationEaoLocal.persist(natureOperation);
		return natureOperation;
	}

	public void remove(NatureOfOperation natureOperation) {
		natureOperationEaoLocal.remove(natureOperation);
	}

	public List<NatureOfOperation> findNatureOperationHierarchy() {;
		List<NatureOfOperation> roots = natureOperationEaoLocal.findRoots();
		return roots;
	}

	public Address persist(Address address) {
		address = addressEaoLocal.persist(address);
		return address;
	}

	public void remove(Address address) {
		addressEaoLocal.remove(address);
	}

	public Address findById(Long id) {
		Address address = addressEaoLocal.findById(id);
		return address;
	}

	public List<Address> findAddressesByStreet(String street) {
		List<Address> addresses = addressEaoLocal.findByStreet(street);
		return addresses;
	}

	public List<Address> findAddressesByNeighborhood(String neighborhood) {
		List<Address> addresses = addressEaoLocal
				.findByNeighborhood(neighborhood);
		return addresses;
	}

	public List<Address> findAddressesByCity(String city) {
		List<Address> addresses = addressEaoLocal.findByCity(city);
		return addresses;
	}

	public List<Address> findAddress(String neighborhood, String city,Province province) {
		List<Address> addresses = addressEaoLocal.findAddress(neighborhood,city, province);
		return addresses;
	}

	@Override
	public List<Menu> getMenus() {
		List<Menu> menus = menuEaoLocal.getMenus();
		return menus;
	}

}
