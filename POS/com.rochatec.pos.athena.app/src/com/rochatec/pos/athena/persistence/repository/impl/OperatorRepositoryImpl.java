package com.rochatec.pos.athena.persistence.repository.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rochatec.pos.athena.persistence.model.Operator;
import com.rochatec.pos.athena.persistence.repository.IOperatorRepository;

@Repository
public class OperatorRepositoryImpl extends GenericRepository<Operator,Serializable> implements IOperatorRepository{

	@Override
	public Operator findByKey(String key) {
		return super.findById(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Operator> findAll() {
		String hql = "SELECT o FROM Operator o";
		Query query = getEntityManager().createQuery(hql,Operator.class);
		Set<Operator> operators = new HashSet<Operator>(query.getResultList());	
		return operators;
	}
	
}
