package com.rochatec.athena.pdv.service.facade.impl;

import java.math.BigDecimal;
import java.util.List;

import com.rochatec.athena.pdv.service.dao.IProductDao;
import com.rochatec.athena.pdv.service.dao.impl.ProductDaoImpl;
import com.rochatec.athena.pdv.service.facade.IProductFacade;
import com.rochatec.athena.pdv.service.model.Product;
import com.rochatec.mongo.MongoConnection;

public class ProductFacadeImpl implements IProductFacade{
	
	private IProductDao productDao;
	private MongoConnection connection;
	
	public ProductFacadeImpl() {
		connection = MongoConnection.getInstance();
		productDao = new ProductDaoImpl(connection);
	}

	@Override
	public void persist(Product product) {
		productDao.persist(product);
	}

	@Override
	public void remove(Product product) {
		productDao.remove(product);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public void execute() {
		MongoConnection connection = MongoConnection.getInstance();
		IProductDao productDao = new ProductDaoImpl(connection);
		
		Product product = new Product();
		product.setId(1L);
		product.setName("CERVEJA HEINEKEN 355ML LN");
		product.setPrice(new BigDecimal(2.99));
		product.setStock(BigDecimal.TEN);
		product.setCategory("CERVEJAS");
		product.addBarcode("7891149000012");
		
		productDao.persist(product);
		
		for (Product p : productDao.findAll()){
			System.out.println(p.toString());
		}
		
	}
	
	 

}
