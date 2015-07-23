package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.model.Sale;

import java.util.Date;
import java.util.Set;

/**
 * Created by epr on 23/07/15.
 */
public interface ISaleRepository {

    Sale persist(Sale sale);

    void remove(Sale sale);

    Sale findById(Long id);

    Set<Sale> findAllByDay(Date begin,Date end);


}
