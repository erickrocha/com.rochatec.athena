package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.model.Operator;

import java.util.Set;

public interface ISecurityService {

	public Operator findOperatorByKey(String key);

	public Set<Operator> findAllOperators();

	public Operator persist(Operator operator);

	public void remove(Operator operator);

	public Operator login(String username,String password) throws UserException;
}
