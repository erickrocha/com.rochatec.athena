package com.rochatec.pos.athena.persistence.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rochatec.pos.athena.persistence.model.Operator;
import com.rochatec.pos.athena.persistence.repository.IOperatorRepository;
import com.rochatec.pos.athena.persistence.service.ISecurityService;

@Service
public class SecurityServiceImpl implements ISecurityService{
	
	@Autowired
	private IOperatorRepository operatorRepository; 

	@Override
	public Operator findOperatorByKey(String key) {
		Operator operator = operatorRepository.findByKey(key);
		return operator;
	}

	@Override
	public Set<Operator> findAllOperators() {
		Set<Operator> operators = operatorRepository.findAll();
		return operators;
	}

	@Override
	public Operator persist(Operator operator) {
		operator = operatorRepository.persist(operator);
		return operator;
	}

	@Override
	public void remove(Operator operator) {
		operatorRepository.remove(operator);		
	}

	
}
