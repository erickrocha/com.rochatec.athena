package com.rochatec.pos.athena.repository.impl;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.repository.IBoxRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by epr on 01/07/15.
 */
@Repository
public class BoxRepositoryImpl extends GenericRepository<Box,Serializable> implements IBoxRepository{

    @Override
    public Box findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Box> findAll() {
        String jpql = "SELECT b FROM Box b";
        Query query = getEntityManager().createQuery(jpql,Box.class);
        List<Box> boxes = query.getResultList();
        return boxes;
    }

    @Override
    public Box findByOperatorAndOpen(Operator operator) {
        String jpql = "SELECT b FROM Box b WHERE b.operator = :operator and b.finishDate is null";
        Query query = getEntityManager().createQuery(jpql,Box.class);
        query.setParameter("operator",operator);
        Box box = (Box)query.getSingleResult();
        return box;
    }
}
