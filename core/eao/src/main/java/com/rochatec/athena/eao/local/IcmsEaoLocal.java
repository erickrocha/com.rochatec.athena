package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Icms;

@Local
public interface IcmsEaoLocal {
	
	public List<Icms> findAll();
	
}
