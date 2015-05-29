package com.athena.application;

import com.athena.resource.SecurityResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by epr on 27/05/15.
 */
@ApplicationPath("/athena")
public class AthenaApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();



    public AthenaApplication() {
        singletons.add(new SecurityResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
