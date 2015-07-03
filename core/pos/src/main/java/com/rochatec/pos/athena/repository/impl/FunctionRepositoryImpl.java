package com.rochatec.pos.athena.repository.impl;

import com.rochatec.pos.athena.exception.FunctionNotExistException;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.repository.IFunctionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by epr on 02/07/15.
 */
@Repository
public class FunctionRepositoryImpl extends GenericRepository<Function,Serializable> implements IFunctionRepository{

    @Override
    public Function findById(Integer id) throws FunctionNotExistException{
        try{
            return super.findById(id);
        }catch (NoResultException ex){
            throw  new FunctionNotExistException();
        }
    }

    @Override
    public List<Function> findAll() {
        String jpql = "SELECT f FROM Function f";
        Query query = getEntityManager().createQuery(jpql,Function.class);
        List<Function> functions = query.getResultList();
        return functions;
    }
}
