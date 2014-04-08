package com.rochatec.athena.eao.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.athena.model.Status;

@Local
public interface AbstractProductEaoLocal {

	public AbstractProduct findById(Long id);
	
	public List<AbstractProduct> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<AbstractProduct> findByCategory(String category, Calendar begin, Calendar end, Status status);
	
}
