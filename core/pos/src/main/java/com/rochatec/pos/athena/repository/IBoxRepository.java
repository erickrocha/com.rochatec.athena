package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.Operator;

import java.util.List;

public interface IBoxRepository {

	public Box persist(Box box);
	
	public void remove(Box box);
	
	public Box findById(Long id);
	
	public List<Box> findAll();
	
	public Box findByOperatorAndOpen(Operator operator);
	
}
