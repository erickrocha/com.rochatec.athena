package com.rochatec.pos.athena.persistence.repository;

import java.util.Set;

import com.rochatec.pos.athena.persistence.model.Operator;

public interface IOperatorRepository {

	public Operator findByKey(String key);
	
	public Set<Operator> findAll();
	
	public Operator persist(Operator operator);
	
	public void remove(Operator operator);	
	
}
