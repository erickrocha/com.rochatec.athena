package com.rochatec.athena.facade.local;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.model.Province;


@Local
public interface ResourcefacadeLocal {
	
	public List<Province> findAllProvinces();
	
	public NatureOfOperation persist(NatureOfOperation natureOperation);
	
	public void remove(NatureOfOperation natureOperation);
	
	public NatureOfOperation findNatureOperationByCode(String code);
	
	public List<NatureOfOperation> findAllNatureOperations();
	
	public List<NatureOfOperation> findNatureOperationHierarchy();
	
	public Address persist(Address address);
	
	public void remove(Address address);
	
	public Address findById(Long id);
	
	public List<Address> findAddressesByStreet(String street);
	
	public List<Address> findAddressesByNeighborhood(String neighborhood);
	
	public List<Address> findAddressesByCity(String city);
	
	public List<Address> findAddress(String neighborhood, String city, Province province);
	
	public List<Menu> getMenus();
	
	
}
