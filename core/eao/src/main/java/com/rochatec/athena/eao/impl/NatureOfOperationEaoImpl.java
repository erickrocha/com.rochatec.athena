package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.NatureOfOperationEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.NatureOfOperation;

@Stateless
public class NatureOfOperationEaoImpl extends GenericEao<NatureOfOperation, Serializable> implements NatureOfOperationEaoLocal {

	public NatureOfOperation findByCode(String value) {
		NatureOfOperation natureOperation = super.findById(value);
		return natureOperation;
	}

	@SuppressWarnings("unchecked")
	public List<NatureOfOperation> findAll() {
		Query query = getEntityManager().createNamedQuery("NatureOfOperation.findAll");
		List<NatureOfOperation> natureOperations = query.getResultList();
		return natureOperations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NatureOfOperation> findRoots() {
		Query query = getEntityManager().createQuery("SELECT no FROM NatureOfOperation no WHERE no.parent is null",NatureOfOperation.class);
		List<NatureOfOperation> operations = query.getResultList();
		return operations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NatureOfOperation> findChilds(NatureOfOperation parent) {
		Query query = getEntityManager().createQuery("SELECT no FROM NatureOfOperation no WHERE no.parent = :parent ",NatureOfOperation.class);
		query.setParameter("parent",parent);
		List<NatureOfOperation> operations = query.getResultList();
		return operations;
	}
	
	

}
