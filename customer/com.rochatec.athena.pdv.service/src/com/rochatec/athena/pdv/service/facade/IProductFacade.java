package com.rochatec.athena.pdv.service.facade;

import java.util.List;

import com.rochatec.athena.pdv.service.model.Product;

public interface IProductFacade {

	public void persist(Product product);
	
	public void remove(Product product);
	
	public List<Product> findAll();
	
	public void execute();
}
