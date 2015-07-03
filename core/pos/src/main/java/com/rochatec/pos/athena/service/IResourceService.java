package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.exception.FunctionNotExistException;
import com.rochatec.pos.athena.model.Function;

import java.util.List;

/**
 * Created by epr on 02/07/15.
 */
public interface IResourceService {

    public Function persist(Function function);

    public void remove(Function function);

    public Function findFunctionById(Integer id)throws FunctionNotExistException;

    public List<Function> findAllFunctions();
}
