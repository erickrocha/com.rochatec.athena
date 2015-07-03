package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.exception.FunctionNotExistException;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.repository.IFunctionRepository;
import com.rochatec.pos.athena.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by epr on 02/07/15.
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private IFunctionRepository functionRepository;

    @Override
    public Function persist(Function function) {
        return functionRepository.persist(function);
    }

    @Override
    public void remove(Function function) {
        functionRepository.remove(function);
    }

    @Override
    public Function findFunctionById(Integer id) throws FunctionNotExistException {
        Function function = functionRepository.findById(id);
        return function;
    }

    @Override
    public List<Function> findAllFunctions() {
        List<Function> functions = functionRepository.findAll();
        return functions;
    }
}
