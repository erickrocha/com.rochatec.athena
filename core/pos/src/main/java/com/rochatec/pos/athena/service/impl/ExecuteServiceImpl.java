package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.BoxStatus;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.utils.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by epr on 02/07/15.
 */
@Service
public class ExecuteServiceImpl implements IExecuteService {

    @Autowired
    private ISaleService saleService;

    @Override
    public void execute(Function function, Operator operator) {

    }

    @Override
    public void execute(Function function, Operator operator, Operator autorizedBy) {
        switch (function.getId()){
            case 102:
                Box box = new Box();
                box.setOperator(operator);
                box.setAutorizedBy(operator);
                box.setStatus(BoxStatus.OPEN);
                box.setOpenDate(CalendarTools.getNow());
                saleService.persist(box);
                break;
        }
    }
}
