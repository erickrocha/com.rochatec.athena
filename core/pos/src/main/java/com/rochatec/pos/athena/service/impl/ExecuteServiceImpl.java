package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.IExecuteService;
import org.springframework.stereotype.Service;

/**
 * Created by epr on 02/07/15.
 */
@Service
public class ExecuteServiceImpl implements IExecuteService {

    @Override
    public void execute(Function function, Operator operator) {

    }

    @Override
    public void execute(Function function, Operator operator, Operator autorizedBy) {

    }
}
