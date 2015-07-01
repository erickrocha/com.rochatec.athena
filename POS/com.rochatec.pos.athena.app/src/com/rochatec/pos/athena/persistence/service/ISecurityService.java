package com.rochatec.pos.athena.persistence.service;

import java.util.Set;

import com.rochatec.pos.athena.persistence.model.Operator;

public interface ISecurityService {

	public Operator findOperatorByKey(String key);

	public Set<Operator> findAllOperators();

	public Operator persist(Operator operator);

	public void remove(Operator operator);
}
