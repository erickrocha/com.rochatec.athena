package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.repository.IOperatorRepository;
import com.rochatec.pos.athena.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

	@Override
	public Operator login(String username, String password)throws UserException {
		return operatorRepository.login(username,password);
	}
}
