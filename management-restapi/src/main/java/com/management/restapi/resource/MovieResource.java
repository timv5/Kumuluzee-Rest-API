package com.management.restapi.resource;

import com.management.service.MovieServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MovieResource {

    @Inject
    private MovieServiceImpl movieService;

    @GET
    public Response getAllMovies(){
        return Response.ok(movieService.getAllMovies()).build();
    }


}
