package com.athena.resource;

import com.rochatec.athena.facade.local.SecurityFacadeLocal;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by epr on 27/05/15.
 */
@Path("/security")
@RequestScoped
@Named
public class SecurityResource {

    @Inject
    private SecurityFacadeLocal securityFacadeLocal;

    @GET
    @Path("/profiles")
    @Produces("application/json")
    public Response getProfiles(){
        List<Profile> profiles = securityFacadeLocal.findAllProfiles();
        return Response.status(200).entity(profiles).build();
    }

    @GET
    @Path("/profiles/{id}")
    @Produces("application/json")
    public Response getProfile(@PathParam("id") Long id){
        Profile profile = securityFacadeLocal.findProfileById(id);
        return  Response.status(200).entity(profile).build();
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getUsers(){
        List<User> users = securityFacadeLocal.findAll();
        return  Response.status(200).entity(users).build();
    }

    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") Long id){
        User user = securityFacadeLocal.findUserById(id);
        return  Response.status(200).entity(user).build();
    }

    @GET
    @Path("/users/{email}")
    @Produces("application/json")
    public Response getUser(@PathParam("email") String email){
        User user = securityFacadeLocal.findUserByEmail(email);
        return  Response.status(200).entity(user).build();
    }

    @GET
    @Path("/roles")
    @Produces("application/json")
    public Response getRoles(){
        List<Role> roles = securityFacadeLocal.findAllRoles();
        return Response.ok(roles).build();
    }
}
