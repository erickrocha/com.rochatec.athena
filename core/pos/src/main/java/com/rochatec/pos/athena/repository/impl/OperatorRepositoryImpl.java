package com.rochatec.pos.athena.repository.impl;

import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.exception.UserLoginException;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.repository.IOperatorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Operator login(String username, String password)throws UserException{
		try {
            String jpql = "SELECT o FROM Operator o WHERE o.key = :key AND o.password = :password";
            Query query = getEntityManager().createQuery(jpql, Operator.class);
            query.setParameter("key", username);
            query.setParameter("password", password);
            Operator operator = (Operator) query.getSingleResult();
            return operator;
        }catch (NoResultException ex){
            throw  new UserLoginException();
        }
	}
}
