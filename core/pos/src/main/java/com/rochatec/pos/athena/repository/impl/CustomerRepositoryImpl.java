package com.rochatec.pos.athena.repository.impl;

import com.rochatec.pos.athena.model.Customer;
import com.rochatec.pos.athena.repository.ICustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by epr on 23/07/15.
 */
@Repository
public class CustomerRepositoryImpl extends  GenericRepository<Customer,Serializable> implements ICustomerRepository {

    @Override
    public Customer findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Customer> findAllByName(String name) {
        String hql = "SELECT c FROM Customer c WHERE c.name like :name";
        Query query = getEntityManager().createQuery(hql,Customer.class);
        query.setParameter("name",name+"%");
        List<Customer> customers = query.getResultList();
        return new HashSet<>(customers);
    }
}
