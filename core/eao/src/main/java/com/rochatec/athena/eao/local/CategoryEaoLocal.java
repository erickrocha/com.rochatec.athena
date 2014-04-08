package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Category;

@Local
public interface CategoryEaoLocal {
	
	public Category persist(Category category);
	
	public void remove(Category category);
	
	public Category findById(Long id);
	
	public List<Category> findByName(String name);
	
	public List<Category> findAll();
	
}
