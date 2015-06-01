package com.athena.resource;

import com.rochatec.athena.facade.local.CRMFacadeLocal;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by epr on 01/06/15.
 */
@Path("/crm")
@RequestScoped
@Named
public class CRMResource {

    @Inject
    private CRMFacadeLocal crmFacadeLocal;

    @Path("/customer/{id}")
    @Produces("application/json")
    private Response get(@PathParam("id") Long id){
        Customer customer = crmFacadeLocal.findCustomerById(id);
        return Response.ok(customer).build();
    }

    @Path("/customers")
    @Produces("application/json")
    @Consumes("application/json")
    private Response get(Map<String,Object> params){
        String name = params.get("name").toString();
        Calendar begin = (Calendar)params.get("begin");
        Calendar end = (Calendar)params.get("end");
        List<Customer> customers = crmFacadeLocal.findCustomersByName(name,begin,end, Status.ACTIVE);
        return Response.ok(customers).build();
    }
}
