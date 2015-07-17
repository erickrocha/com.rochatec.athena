package com.rochatec.pos.athena.utils;

import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.IResourceService;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.service.ISecurityService;
import com.rochatec.pos.athena.service.impl.ResourceServiceImpl;
import com.rochatec.pos.athena.service.impl.SecurityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by epr on 16/07/15.
 */
public class ServiceFactory {

    ApplicationContext context;

    public ServiceFactory(ApplicationContext context) {
        this.context = context;
    }

    public ServiceFactory(String springFile) {
        this(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public ISaleService getSaleService() {
        return (ISaleService) context.getBean("salesServiceImpl");
    }

    public IResourceService getResourceService() {
        return context.getBean(ResourceServiceImpl.class);
    }

    public ISecurityService getSecurityService() {
        return context.getBean(SecurityServiceImpl.class);
    }

    public IExecuteService getExecuteService() {
        return (IExecuteService) context.getBean("executeServiceImpl");
    }
}
