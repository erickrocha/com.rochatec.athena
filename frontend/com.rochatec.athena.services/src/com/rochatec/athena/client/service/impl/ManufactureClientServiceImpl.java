package com.rochatec.athena.client.service.impl;

import java.util.Calendar;
import java.util.List;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.facade.remote.ManufactureFacadeRemote;
import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.Ncm;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;
import com.rochatec.athena.model.Status;

public class ManufactureClientServiceImpl implements ManufactureClientService{
	
	private ManufactureFacadeRemote serviceRemote = ServiceLocator.getInstance().getManufactureFacadeRemote();
	
	@Override
	public Category persist(Category category) {
		return serviceRemote.persist(category);
	}

	@Override
	public void remove(Category category) {
		serviceRemote.remove(category);		
	}

	@Override
	public Category findCategoryById(Long id) {
		return serviceRemote.findCategoryById(id);
	}

	@Override
	public List<Category> findCategoriesByName(String name) {
		return serviceRemote.findCategoriesByName(name);
	}

	@Override
	public List<Category> findAllCategories() {
		return serviceRemote.findAllCategories();
	}

	@Override
	public List<Icms> findAllIcms() {
		return serviceRemote.findAllIcms();
	}

	@Override
	public Ncm persist(Ncm ncm) {
		return serviceRemote.persist(ncm);
	}

	@Override
	public void remove(Ncm ncm) {
		serviceRemote.remove(ncm);
	}

	@Override
	public Ncm findNcmById(String id) {
		return serviceRemote.findNcmById(id);
	}

	@Override
	public List<Ncm> findAllNcms() {
		return serviceRemote.findAllNcms();
	}

	@Override
	public List<Ncm> findNcmsByName(String name) {
		return serviceRemote.findNcmsByName(name);
	}

	@Override
	public Product persist(Product product) {
		return serviceRemote.persist(product);
	}

	@Override
	public void remove(Product product) {
		serviceRemote.remove(product);
	}

	@Override
	public Product findProductById(Long id) {
		return serviceRemote.findProductById(id);
	}
	
	@Override
	public List<Product> findProductsByName(String name,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductsByName(name,begin,end,status);
	}

	@Override
	public List<Product> findProductsByCategory(String category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductsByCategory(category,begin,end,status);
	}
	
	@Override
	public List<Product> findProductsByCategory(Category category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductsByCategory(category.getName(),begin,end,status);
	}
	
	@Override
	public List<Product> findProductsByDateRegister(Calendar begin,	Calendar end, Status status) {
		return serviceRemote.findProductsByDateRegister(begin, end, status);
	}

	@Override
	public ProductSet persist(ProductSet productSet) {
		return serviceRemote.persist(productSet);
	}

	@Override
	public void remove(ProductSet productSet) {
		serviceRemote.remove(productSet);
	}

	@Override
	public ProductSet findProductSetById(Long id) {
		return serviceRemote.findProductSetById(id);
	}

	@Override
	public List<ProductSet> findProductSetsByName(String name, Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductSetsByName(name, begin,end,status);
	}

	@Override
	public List<ProductSet> findProductSetsByCategory(Category category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductSetsByCategory(category.getName(), begin,end,status);
	}
	
	@Override
	public List<ProductSet> findProductSetsByCategory(String category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findProductSetsByCategory(category, begin,end,status);
	}
	
	@Override
	public List<ProductSet> findProductSetsByDateRegister(Calendar begin,Calendar end, Status status) {
		return serviceRemote.findProductSetsByDateRegister(begin, end, status);
	}
	
	@Override
	public List<ProductSetItem> findProductSetItems(ProductSet productSet) {
		return serviceRemote.findProductSetItems(productSet);
	}

	@Override
	public AbstractProduct findById(Long id) {
		return serviceRemote.findById(id);
	}

	@Override
	public List<AbstractProduct> findByName(String name,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findByName(name,begin,end,status);
	}

	@Override
	public List<AbstractProduct> findByCategory(Category category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findByCategory(category.getName(),begin,end,status);
	}
	
	@Override
	public List<AbstractProduct> findByCategory(String category,Calendar begin,Calendar end,Status status) {
		return serviceRemote.findByCategory(category,begin,end,status);
	}

	@Override
	public List<AbstractProduct> findByDateregister(Calendar begin,Calendar end, Status status) {
		return serviceRemote.findByName("%",begin, end, status);
	}

}
