package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.model.BarCode;
import com.rochatec.pos.athena.model.Product;

import java.util.Set;

public interface IProductRepository {
	
	public Product findById(Long id);
	
	public Product findByBarcode(BarCode barCode);
	
	public Product findByBarcode(String barcode);
	
	public Set<Product> findByName(String name);
	
}
