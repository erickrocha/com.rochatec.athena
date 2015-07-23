package com.rochatec.pos.athena.repository.impl;

import com.rochatec.pos.athena.model.Sale;
import com.rochatec.pos.athena.repository.ISaleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by epr on 23/07/15.
 */
@Repository
public class SaleRepositoryImpl extends GenericRepository<Sale,Serializable> implements ISaleRepository{

    @Override
    public Sale findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Sale> findAllByDay(Date begin, Date end) {
        String hql = "SELECT s FROM Sale s LEFT JOIN FETCH i.items WHERE s.dateRegister BETWEEN :begin and :end ";
        Query query = getEntityManager().createQuery(hql,Sale.class);
        query.setParameter("begin",begin);
        query.setParameter("end",end);
        List<Sale> sales = query.getResultList();
        return new HashSet<>(sales);
    }
}
