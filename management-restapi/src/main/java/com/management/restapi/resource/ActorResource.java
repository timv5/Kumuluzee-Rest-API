package com.management.restapi.resource;

import com.management.service.ActorServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ActorResource {

    @Inject
    private ActorServiceImpl actorServiceImpl;

    @GET
    public Response getAllActors(){
        return Response.ok(actorServiceImpl.getAll()).build();
    }


}
