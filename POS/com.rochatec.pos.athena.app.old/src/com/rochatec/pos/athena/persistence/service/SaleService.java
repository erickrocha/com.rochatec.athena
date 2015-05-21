package com.rochatec.pos.athena.persistence.service;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Icms;
import com.rochatec.pos.athena.persistence.model.Product;

public interface SaleService {

	public List<Icms> finAdllIcms();
	
	public Product findProductById(Long id);
	
	public Product findProdcutByBarCode(BarCode barCode);
	
	public Product findProductByBarCode(String barCode);
	
	public List<Product> findAllProductByName(String name);
	
}
