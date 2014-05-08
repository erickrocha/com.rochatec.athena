package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.facade.remote.ResourceFacadeRemote;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.model.Province;
import com.rochatec.athena.model.Status;

public class ResourceClientServiceImpl implements ResourceClientService {

	private ResourceFacadeRemote serviceRemote = ServiceLocator.getInstance().getResourceFacadeRemote();

	
	public List<Province> findAllProvinces() {
		return serviceRemote.findAllProvinces();
	}

	
	public NatureOfOperation persist(NatureOfOperation natureOperation) {
		return serviceRemote.persist(natureOperation);
	}

	
	public void remove(NatureOfOperation natureOperation) {
		serviceRemote.remove(natureOperation);
	}

	
	public NatureOfOperation findNatureOperationByCode(String code) {
		return serviceRemote.findNatureOperationByCode(code);
	}

	
	public List<NatureOfOperation> findAllNatureOperations() {
		return serviceRemote.findAllNatureOperations();
	}

	
	public List<NatureOfOperation> findNatureOperationHierarchy() {
		return serviceRemote.findNatureOperationHierarchy();
	}

	
	public List<Address> findAddressesByStreet(String street) {
		return serviceRemote.findAddressesByStreet(street);
	}

	
	public List<Address> findAddressesByNeighborhood(String neighborhood) {
		return serviceRemote.findAddressesByNeighborhood(neighborhood);
	}

	
	public List<Address> findAddressesByCity(String city) {
		return serviceRemote.findAddressesByCity(city);
	}

	
	public List<Address> findAddress(String neighborhood, String city,
			Province province) {
		return serviceRemote.findAddress(neighborhood, city, province);
	}

	
	public List<Menu> getMenus() {
		return serviceRemote.getMenus();
	}


	@Override
	public Company persist(Company company) {
		return serviceRemote.persist(company);
	}


	@Override
	public void remove(Company company) {
		serviceRemote.remove(company);		
	}


	@Override
	public Company findCompanyById(Long id) {
		return serviceRemote.findCompanyById(id);
	}


	@Override
	public Company findCompanyByIndex(Long id, String socialSecurity) {		
		return serviceRemote.findCompanyByIndex(id, socialSecurity);
	}


	@Override
	public Company findCompanyBySocialSecurity(String socialSecurity) {
		return serviceRemote.findCompanyBySocialSecurity(socialSecurity);
	}


	@Override
	public List<Company> findCompaniesByName(String name, Calendar begin,
			Calendar end, Status status) {		
		return serviceRemote.findCompaniesByName(name, begin, end, status);
	}


	@Override
	public List<Company> findCompaniesByDateRegister(Calendar begin,
			Calendar end, Status status) {
		return serviceRemote.findCompaniesByDateRegister(begin, end, status);
	}

}
