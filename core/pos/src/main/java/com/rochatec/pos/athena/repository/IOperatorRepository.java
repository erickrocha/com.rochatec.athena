package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.model.Operator;

import java.util.Set;

public interface IOperatorRepository {

	public Operator findByKey(String key);
	
	public Set<Operator> findAll();
	
	public Operator persist(Operator operator);
	
	public void remove(Operator operator);

	public Operator login(String username,String password)throws UserException;
	
}
