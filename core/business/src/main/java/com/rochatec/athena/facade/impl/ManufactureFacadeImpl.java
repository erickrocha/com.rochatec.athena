package com.rochatec.athena.facade.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.AbstractProductEaoLocal;
import com.rochatec.athena.eao.local.CategoryEaoLocal;
import com.rochatec.athena.eao.local.IcmsEaoLocal;
import com.rochatec.athena.eao.local.NcmEaoLocal;
import com.rochatec.athena.eao.local.ProductEaoLocal;
import com.rochatec.athena.eao.local.ProductSetEaoLocal;
import com.rochatec.athena.facade.local.ManufactureFacadeLocal;
import com.rochatec.athena.facade.remote.ManufactureFacadeRemote;
import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.Ncm;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;
import com.rochatec.athena.model.Status;

@Stateless
public class ManufactureFacadeImpl implements ManufactureFacadeLocal,
		ManufactureFacadeRemote {

	@EJB
	private ProductEaoLocal productEaoLocal;

	@EJB
	private CategoryEaoLocal categoryEaoLocal;

	@EJB
	private NcmEaoLocal ncmEaoLocal;

	@EJB
	private IcmsEaoLocal icmsEaoLocal;
	
	@EJB
	private ProductSetEaoLocal productSetEaoLocal;
	
	@EJB
	private AbstractProductEaoLocal abstractProductEaoLocal;

	
	public Product persist(Product product) {
		return productEaoLocal.persist(product);
	}

	public void remove(Product product) {
		productEaoLocal.remove(product);
	}

	public Product findProductById(Long id) {
		Product product = productEaoLocal.findById(id);
		return product;
	}

	public List<Product> findProductsByName(String name,Calendar begin,Calendar end,Status status) {
		List<Product> products = productEaoLocal.findByName(name,begin,end,status);
		return products;
	}
	
	public List<Product> findProductsByCategory(String category,Calendar begin,Calendar end,Status status) {
		List<Product> products = productEaoLocal.findByCategory(category,begin,end,status);
		return products;
	}
	
	public List<Product> findProductsByDateRegister(Calendar begin,Calendar end,Status status){
		List<Product> products = productEaoLocal.findByDateRegister(begin,end,status);
		return products;
	}

	public Category persist(Category category) {
		category = categoryEaoLocal.persist(category);
		return category;
	}

	public void remove(Category category) {
		categoryEaoLocal.remove(category);
	}

	public Category findCategoryById(Long id) {
		Category category = categoryEaoLocal.findById(id);
		return category;
	}

	public List<Category> findCategoriesByName(String name) {
		List<Category> categories = categoryEaoLocal.findByName(name);
		return categories;
	}

	public List<Category> findAllCategories() {
		List<Category> categories = categoryEaoLocal.findAll();
		return categories;
	}

	public List<Icms> findAllIcms() {
		List<Icms> icms = icmsEaoLocal.findAll();
		return icms;
	}

	public Ncm persist(Ncm ncm) {
		ncm = ncmEaoLocal.persist(ncm);
		return ncm;
	}

	public void remove(Ncm ncm) {
		ncmEaoLocal.remove(ncm);
	}

	public Ncm findNcmById(String id) {
		Ncm ncm = ncmEaoLocal.findById(id);
		return ncm;
	}

	public List<Ncm> findAllNcms() {
		List<Ncm> ncms = ncmEaoLocal.findAll();
		return ncms;
	}

	public List<Ncm> findNcmsByName(String name) {
		List<Ncm> ncms = ncmEaoLocal.findByName(name);
		return ncms;
	}

	@Override
	public ProductSet persist(ProductSet productSet) {
		productSet = productSetEaoLocal.persist(productSet);
		return productSet;
	}

	@Override
	public void remove(ProductSet productSet) {
		productSetEaoLocal.remove(productSet);
	}

	@Override
	public ProductSet findProductSetById(Long id) {
		ProductSet productSet = productSetEaoLocal.findById(id);
		return productSet;
	}

	@Override
	public List<ProductSet> findProductSetsByName(String name,Calendar begin,Calendar end,Status status) {
		List<ProductSet> productSets = productSetEaoLocal.findByName(name, begin,end,status);
		return productSets;
	}

	@Override
	public List<ProductSet> findProductSetsByCategory(String category,Calendar begin,Calendar end,Status status) {
		List<ProductSet> productSets = productSetEaoLocal.findByCategory(category,begin,end,status);
		return productSets;
	}
	
	public List<ProductSet> findProductSetsByDateRegister(Calendar begin,Calendar end,Status status){
		List<ProductSet> productSets = productSetEaoLocal.findByDateRegister(begin,end,status);
		return productSets;
	}
	
	@Override
	public List<ProductSetItem> findProductSetItems(ProductSet productSet) {
		List<ProductSetItem> items = productSetEaoLocal.findItems(productSet);
		return items;
	}

	@Override
	public AbstractProduct findById(Long id) {
		AbstractProduct product = abstractProductEaoLocal.findById(id);
		return product;
	}

	@Override
	public List<AbstractProduct> findByName(String name,Calendar begin,Calendar end,Status status) {
		List<AbstractProduct> products = abstractProductEaoLocal.findByName(name,begin,end,status);
		return products;
	}

	@Override
	public List<AbstractProduct> findByCategory(String category,Calendar begin,Calendar end,Status status) {
		List<AbstractProduct> products = abstractProductEaoLocal.findByCategory(category,begin,end,status);
		return products;
	}

}
