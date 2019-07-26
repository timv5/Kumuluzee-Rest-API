package com.management.restapi.resource;

import com.management.entities.Actor;
import com.management.service.ActorServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActorResource {

    @Inject
    private ActorServiceImpl actorServiceImpl;

    @GET
    public Response getAllActors(){
        return Response.ok(actorServiceImpl.getAllActors()).build();
    }

    @GET
    @Path("/{id}")
    public Response getActorById(@PathParam("id") final Integer id){
        Actor actor = this.actorServiceImpl.getActorById(id);
        if(actor != null){
            return Response.ok(actor).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    public Response addActor(final Actor actor){
        Actor createdActor = this.actorServiceImpl.createActor(actor);
        if(createdActor != null){
            return Response.status(Response.Status.CREATED).entity(createdActor).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateActor(@PathParam("id") final Integer id, final Actor actor){
        Actor foundActor = this.actorServiceImpl.updateActor(id, actor);
        if (foundActor != null){
            return Response.status(Response.Status.CREATED).entity(foundActor).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteActor(@PathParam("id") final Integer id){
        this.actorServiceImpl.deleteActor(id);
        return Response.status(Response.Status.OK).build();
    }


}
