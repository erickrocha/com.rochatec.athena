package com.rochatec.pos.athena.controller;

import com.rochatec.pos.athena.app.ApplicationManager;
import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.service.PreferenceService;
import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.BoxStatus;
import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.IResourceService;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.service.ISecurityService;
import com.rochatec.pos.athena.service.impl.ExecuteServiceImpl;
import com.rochatec.pos.athena.service.impl.ResourceServiceImpl;
import com.rochatec.pos.athena.service.impl.SalesServiceImpl;
import com.rochatec.pos.athena.service.impl.SecurityServiceImpl;
import org.eclipse.swt.widgets.Composite;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by epr on 01/07/15.
 */
public class ApplicationController {

    private ApplicationContext context;

    private ApplicationManager manager;
    private PreferenceService preferenceService;

    public ApplicationController(ApplicationContext context){
        this.context =  context;
        this.manager = new ApplicationManager();
        this.preferenceService = new PreferenceService();
    }

    public void load(){
        preferenceService.init();
    }

    public ISaleService getSaleService(){
        return (ISaleService)context.getBean("salesServiceImpl");
    }

    public IResourceService getResourceService(){
        return context.getBean(ResourceServiceImpl.class);
    }

    public ISecurityService getSecurityService(){
        return context.getBean(SecurityServiceImpl.class);
    }

    public IExecuteService getExecuteService(){
        return (IExecuteService)context.getBean("executeServiceImpl");
    }

    public ApplicationManager getManager(){
        return manager;
    }

    public PreferenceService getPreferenceService(){
        return this.preferenceService;
    }

    public void executeOpenDay(){
        preferenceService.setValue(IAppConfig.BOX_STATUS,BoxStatus.PARTIAL_CLOSED.name());
        preferenceService.save();
    }

    public void executeEnterOperator(String operatorId){
        preferenceService.setValue(IAppConfig.BOX_STATUS,BoxStatus.OPEN.name());
        preferenceService.setValue(IAppConfig.BOX_OPERATOR,operatorId);
        preferenceService.save();
    }

    public boolean isOpen(){
        if (preferenceService.getString(IAppConfig.BOX_STATUS) == null){
            return false;
        }
        BoxStatus boxStatus = BoxStatus.valueOf(preferenceService.getString(IAppConfig.BOX_STATUS));
        if (BoxStatus.OPEN.equals(boxStatus)){
            return  true;
        }
        return false;
    }

}
