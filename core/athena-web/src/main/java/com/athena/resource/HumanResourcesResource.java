package com.athena.resource;

import com.rochatec.athena.facade.local.HumanResourceFacadeLocal;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.model.Status;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by epr on 01/06/15.
 */
@Path("/humanResource")
@RequestScoped
@Named
public class HumanResourcesResource {

    @Inject
    private HumanResourceFacadeLocal humanResourceFacadeLocal;

    @GET
    @Path("/jobs")
    @Produces("application/json")
    public Response getJobs(){
        List<Job> jobs = humanResourceFacadeLocal.findAllJobs();
        return Response.ok(jobs).build();
    }

    @GET
    @Path("/job/{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") Integer id){
        Job job = humanResourceFacadeLocal.findJobById(id);
        return Response.ok(job).build();
    }

    @GET
    @Path("/job/{name}")
    @Produces("application/json")
    public Response getByName(@PathParam("name") String name){
        List<Job> jobs = humanResourceFacadeLocal.findJobsByName(name);
        return Response.ok(jobs).build();
    }

    @GET
    @Path("/employee/{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") Long id){
        Employee employee = humanResourceFacadeLocal.findEmployeeById(id);
        return Response.ok(employee).build();
    }

    @GET
    @Path("/employee/{socialSecurity}")
    @Produces("application/json")
    public Response get(@PathParam("socialSecurity") String socialSecurity){
        Employee employee = humanResourceFacadeLocal.findEmployeeBySocialSecurity(socialSecurity);
        return Response.ok(employee).build();
    }

    @GET
    @Path("/employee/find")
    @Produces("application/json")
    public Response getByName(@QueryParam("name") String name,@QueryParam("begin") Date begin,@QueryParam("end") Date end){
        List<Employee> employees = humanResourceFacadeLocal.findEmployeesByName(name,begin,end, Status.ACTIVE);
        return Response.ok(employees).build();
    }


}
