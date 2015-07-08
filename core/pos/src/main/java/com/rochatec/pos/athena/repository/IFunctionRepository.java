package com.rochatec.pos.athena.repository;

import com.rochatec.pos.athena.exception.FunctionNotExistNException;
import com.rochatec.pos.athena.model.Function;

import java.util.List;

/**
 * Created by epr on 02/07/15.
 */
public interface IFunctionRepository {

    public Function persist(Function function);

    public void remove(Function function);

    public Function findById(Integer id)throws FunctionNotExistNException;

    public List<Function> findAll();
}
