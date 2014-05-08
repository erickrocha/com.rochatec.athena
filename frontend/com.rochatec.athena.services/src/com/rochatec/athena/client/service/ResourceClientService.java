package com.rochatec.athena.client.service;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.model.Province;
import com.rochatec.athena.model.Status;

public interface ResourceClientService {

	public List<Province> findAllProvinces();

	public NatureOfOperation persist(NatureOfOperation natureOperation);

	public void remove(NatureOfOperation natureOperation);

	public NatureOfOperation findNatureOperationByCode(String code);

	public List<NatureOfOperation> findAllNatureOperations();

	public List<NatureOfOperation> findNatureOperationHierarchy();

	public List<Address> findAddressesByStreet(String street);

	public List<Address> findAddressesByNeighborhood(String neighborhood);

	public List<Address> findAddressesByCity(String city);

	public List<Address> findAddress(String neighborhood, String city,
			Province province);
	
	public List<Menu> getMenus();
	
	public Company persist(Company company);
	
	public void remove(Company company);
	
	public Company findCompanyById(Long id);
	
	public Company findCompanyByIndex(Long id, String socialSecurity);
	
	public Company findCompanyBySocialSecurity(String socialSecurity);
	
	public List<Company> findCompaniesByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Company> findCompaniesByDateRegister(Calendar begin, Calendar end, Status status);

}
