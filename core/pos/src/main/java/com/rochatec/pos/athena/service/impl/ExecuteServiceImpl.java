package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.BoxStatus;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.utils.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by epr on 02/07/15.
 */
@Service
public class ExecuteServiceImpl implements IExecuteService {

    @Autowired
    private ISaleService saleService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
    public Box openBox(Operator operator, Operator authorizedBy,String printerSerialNumber,String ecf,String initialCounter) {
        Box box = new Box();
        box.setOperator(operator);
        box.setAutorizedBy(operator);
        box.setStatus(BoxStatus.OPEN);
        box.setOpenDate(CalendarTools.getNow());
        box.setEcf(ecf);
        box.setInitialCounter(initialCounter);
        box.setPrinterSerialNumber(printerSerialNumber);
        box = saleService.persist(box);
        return box;
    }
}
