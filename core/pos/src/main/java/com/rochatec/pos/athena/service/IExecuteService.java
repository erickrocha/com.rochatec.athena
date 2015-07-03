package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;

public interface IExecuteService {

	public void execute(Function function,Operator operator);

    public void execute(Function function,Operator operator,Operator autorizedBy);

}
