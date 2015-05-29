package com.athena.resource;

import com.rochatec.athena.facade.local.SecurityFacadeLocal;
import com.rochatec.athena.model.Profile;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        List<Profile> profiles = securityFacadeLocal.findAllProfiles();
        return Response.ok(profiles).build();
    }
}
