package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.NatureOfOperation;

@Local
public interface NatureOfOperationEaoLocal {
	
	public NatureOfOperation persist(NatureOfOperation natureOperation);
	
	public void remove(NatureOfOperation natureOperation);
	
	public NatureOfOperation findByCode(String value);
	
	public List<NatureOfOperation> findAll();
	
	public List<NatureOfOperation> findRoots();
	
	public List<NatureOfOperation> findChilds(NatureOfOperation parent);
	
}
