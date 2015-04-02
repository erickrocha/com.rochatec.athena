package com.rochatec.pos.athena.persistence.dao;

import java.util.Set;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Product;

public interface ProductDao {
	
	public Product findById(Long id);
	
	public Product findByBarcode(BarCode barCode);
	
	public Product findByBarcode(String barcode);
	
	public Set<Product> findByName(String name);
	
}
