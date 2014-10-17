package com.rochatec.pos.athena.persistence.dao;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.Product;

public interface ProductDao {

	public void persist(Product product);
	
	public void remove(Product product);
	
	public Product findById(Long id);
	
	public List<Product> findAll();
	
}
