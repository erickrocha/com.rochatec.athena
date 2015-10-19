package com.athena.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.athena.resource.CRMResource;
import com.athena.resource.HumanResourcesResource;
import com.athena.resource.ResourcesResource;
import com.athena.resource.SecurityResource;

/**
 * Created by epr on 27/05/15.
 */
@ApplicationPath("/athena")
public class AthenaApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();



    public AthenaApplication() {
        classes.add(SecurityResource.class);
        classes.add(CRMResource.class);
        classes.add(HumanResourcesResource.class);
        classes.add(ResourcesResource.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
