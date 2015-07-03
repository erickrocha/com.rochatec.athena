package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.model.BarCode;
import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.model.Product;

import java.util.List;
import java.util.Set;

public interface ISaleService {

	public Product findProductById(Long id);

	public Product findProductByBarcode(BarCode barCode);

	public Product findProductByBarcode(String barcode);

	public Set<Product> findProductsByName(String name);
	
	public Box persist(Box box);
	
	public void remove(Box box);
	
	public Box findBoxById(Long id);
	
	public List<Box> findAllBoxes();
	
	public Box findBoxByOperatorAndOpen(Operator operator);

}
