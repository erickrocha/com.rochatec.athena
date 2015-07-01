package com.rochatec.pos.athena.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.rochatec.pos.athena.persistence.repository.IBoxRepository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.rochatec.pos.athena.persistence.model.Box;
import com.rochatec.pos.athena.persistence.model.Operator;

@Repository
public class BoxRepositoryImpl extends GenericRepository<Box,Serializable> implements IBoxRepository{

	@Override
	public Box findById(Long id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> findAll() {
		String hql = "SELECT b FROM Box b";
		Query query = getEntityManager().createQuery(hql, Box.class);
		List<Box> boxes = query.getResultList(); 
		return boxes;
	}

	@Override
	public Box findByOperatorAndOpen(Operator operator) {
		String hql = "SELECT b FROM Box b WHERE b.operator = :operator and finishDate is null";
		Query query = getEntityManager().createQuery(hql,Box.class);
		query.setParameter("operator",operator);
		Box box = (Box)query.getSingleResult();
		return box;
	}

	
	
}
