package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;

public interface IExecuteService {

    public Box openBox(Operator operator,Operator authorizedBy,String printerSerialNumber,String ecf,String initialCounter);

}
