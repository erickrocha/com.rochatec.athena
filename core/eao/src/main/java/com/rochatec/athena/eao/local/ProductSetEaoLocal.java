package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;
import com.rochatec.athena.model.Status;

@Local
public interface ProductSetEaoLocal {

	public ProductSet persist(ProductSet productSet);

	public void remove(ProductSet productSet);

	public ProductSet findById(Long id);

	public List<ProductSet> findByName(String name, Calendar begin, Calendar end, Status status);

	public List<ProductSet> findByCategory(String name, Calendar begin, Calendar end, Status status);
	
	public List<ProductSet> findByDateRegister(Calendar begin, Calendar end, Status status);
	
	public List<ProductSetItem> findItems(ProductSet productSet);
}
