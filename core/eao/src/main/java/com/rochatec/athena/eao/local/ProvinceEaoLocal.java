package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Province;

@Local
public interface ProvinceEaoLocal {
	
	public List<Province> findAll();
}
