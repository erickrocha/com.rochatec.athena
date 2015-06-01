package com.athena.resource;

import com.rochatec.athena.facade.local.ResourcefacadeLocal;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.model.Province;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by epr on 01/06/15.
 */
@Path("/resources")
@RequestScoped
@Named
public class ResourcesResource {

    @Inject
    private ResourcefacadeLocal resourcefacadeLocal;

    @Path("/provinces")
    @Produces("application/json")
    public Response getAll(){
        List<Province> provinces = resourcefacadeLocal.findAllProvinces();
        return Response.ok(provinces).build();
    }


}
