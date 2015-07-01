package com.rochatec.pos.athena.persistence.repository;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.Box;
import com.rochatec.pos.athena.persistence.model.Operator;

public interface IBoxRepository {

	public Box persist(Box box);
	
	public void remove(Box box);
	
	public Box findById(Long id);
	
	public List<Box> findAll();
	
	public Box findByOperatorAndOpen(Operator operator);
	
}
