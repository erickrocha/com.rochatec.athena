package com.rochatec.pos.athena.persistence.service;

import java.util.Set;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Product;

public interface ISaleService {

public Product findProductById(Long id);
	
	public Product findProductByBarcode(BarCode barCode);
	
	public Product findProductByBarcode(String barcode);
	
	public Set<Product> findProductsByName(String name);
	
}
