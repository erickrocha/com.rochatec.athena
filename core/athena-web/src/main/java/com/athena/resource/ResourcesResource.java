package com.athena.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.rochatec.athena.facade.local.ResourcefacadeLocal;
import com.rochatec.athena.model.Province;

/**
 * Created by epr on 01/06/15.
 */
@Path("/resources")
@RequestScoped
@Named
public class ResourcesResource {

    @Inject
    private ResourcefacadeLocal resourcefacadeLocal;

    @GET
    @Path("/provinces")
    @Produces("application/json")
    public Response getAll(){
        List<Province> provinces = resourcefacadeLocal.findAllProvinces();
        return Response.ok(provinces).build();
    }


}
