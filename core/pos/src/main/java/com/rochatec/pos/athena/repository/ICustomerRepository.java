package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.model.Customer;

import java.util.Set;

/**
 * Created by epr on 23/07/15.
 */
public interface ICustomerRepository {

    Customer persist(Customer customer);

    void remove(Customer customer);

    Customer findById(Long id);

    Set<Customer> findAllByName(String name);
}
