package com.rochatec.pos.athena.controller;

import com.rochatec.pos.athena.app.ApplicationManager;
import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.IResourceService;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.service.ISecurityService;
import com.rochatec.pos.athena.service.impl.ExecuteServiceImpl;
import com.rochatec.pos.athena.service.impl.ResourceServiceImpl;
import com.rochatec.pos.athena.service.impl.SalesServiceImpl;
import com.rochatec.pos.athena.service.impl.SecurityServiceImpl;
import org.springframework.context.ApplicationContext;

/**
 * Created by epr on 01/07/15.
 */
public class ApplicationController {

    private ApplicationContext context;
    private AthenaApplicationWindow windowApplication;
    private ApplicationManager manager;

    public ApplicationController(ApplicationContext context,AthenaApplicationWindow windowApplication){
        this.context =  context;
        this.windowApplication = windowApplication;
        this.manager = new ApplicationManager();
    }

    public ISaleService getSaleService(){
        return context.getBean(SalesServiceImpl.class);
    }

    public IResourceService getResourceService(){
        return context.getBean(ResourceServiceImpl.class);
    }

    public ISecurityService getSecurityService(){
        return context.getBean(SecurityServiceImpl.class);
    }

    public IExecuteService getExecuteService(){
        return context.getBean(ExecuteServiceImpl.class);
    }

    public AthenaApplicationWindow getWindowApplication(){
        return windowApplication;
    }

    public ApplicationManager getManager(){
        return manager;
    }
}
