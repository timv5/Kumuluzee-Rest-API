package com.management.restapi.resource;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.management.entities.Actor;
import com.management.restapi.requestCounter.RequestCounter;
import com.management.service.ActorServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/actor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActorResource {

    private RequestCounter requestCounter = new RequestCounter("C:\\Users\\tvalencic\\Documents\\personal\\jobs\\beenius\\beenius\\management-restapi\\src\\main\\resources\\counter.txt");

    @Context
    protected UriInfo uriInfo;

    @Inject
    private ActorServiceImpl actorServiceImpl;

    @GET
    public Response getAllActors(){
        QueryParameters query = QueryParameters.query(this.uriInfo.getRequestUri().getQuery()).build();
        List<Actor> actors = this.actorServiceImpl.getAllActors(query);
        Long actorsCount = this.actorServiceImpl.getActorCount(query);

        this.requestCounter.count();
        return Response.ok(actors).header("X-Total-Count", actorsCount).build();
    }

    @GET
    @Path("/{id}")
    public Response getActorById(@PathParam("id") final Integer id){
        Actor actor = this.actorServiceImpl.getActorById(id);
        this.requestCounter.count();
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
        this.requestCounter.count();
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
        this.requestCounter.count();
        if (foundActor != null){
            return Response.status(Response.Status.CREATED).entity(foundActor).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteActor(@PathParam("id") final Integer id){
        Actor actor = this.actorServiceImpl.deleteActor(id);
        this.requestCounter.count();
        if(actor != null){
            return Response.status(Status.NO_CONTENT).build();
        }else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search")
    public Response searchActorByName(@QueryParam("firstname") final String firstname){
        List<Actor> actors = this.actorServiceImpl.searchActorsByName(firstname);
        this.requestCounter.count();
        if(!actors.isEmpty()){
            return Response.ok(actors).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
