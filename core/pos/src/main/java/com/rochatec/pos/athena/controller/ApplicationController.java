package com.rochatec.pos.athena.controller;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.service.PreferenceService;
import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.BoxStatus;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.utils.ServiceFactory;

/**
 * Created by epr on 01/07/15.
 */
public class ApplicationController {

    private ServiceFactory factory;
    private PreferenceService preferenceService;

    private Operator operator;
    private Box box;
    private BoxStatus status;

    public ApplicationController(ServiceFactory factory) {
        this.factory = factory;
        this.preferenceService = new PreferenceService();
    }

    public void load() {
        preferenceService.init();
        String boxValue = getPreferenceService().getString(IAppConfig.BOX_STATUS);
        status = BoxStatus.valueOf(boxValue);
        if (BoxStatus.OPEN.equals(status)){
            String operatorKey = getPreferenceService().getString(IAppConfig.BOX_OPERATOR);
            operator = getFactory().getSecurityService().findOperatorByKey(operatorKey);
            box = getFactory().getSaleService().findBoxByOperatorAndOpen(operator);
        }
    }

    public ServiceFactory getFactory() {
        return factory;
    }

    public PreferenceService getPreferenceService() {
        return this.preferenceService;
    }

    public void executeOpenDay() {
        preferenceService.setValue(IAppConfig.BOX_STATUS, BoxStatus.PARTIAL_CLOSED.name());
        preferenceService.save();
    }

    public void executeEnterOperator(String operatorId) {
        preferenceService.setValue(IAppConfig.BOX_STATUS, BoxStatus.OPEN.name());
        preferenceService.setValue(IAppConfig.BOX_OPERATOR, operatorId);
        preferenceService.save();
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public BoxStatus getStatus() {
        return status;
    }

    public void setStatus(BoxStatus status) {
        this.status = status;
    }
}
