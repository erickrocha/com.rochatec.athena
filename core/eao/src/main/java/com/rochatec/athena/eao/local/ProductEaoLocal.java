package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.Status;

@Local
public interface ProductEaoLocal {
	
	public Product persist(Product product);
	
	public void remove(Product product);
	
	public Product findById(Long id);
	
	public List<Product> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<Product> findByCategory(String category, Calendar begin, Calendar end, Status status);
	
	public List<Product> findByDateRegister(Calendar begin, Calendar end, Status status);
	
}
