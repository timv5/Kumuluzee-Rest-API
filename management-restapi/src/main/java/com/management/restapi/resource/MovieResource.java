package com.management.restapi.resource;

import com.management.entities.Actor;
import com.management.entities.Movie;
import com.management.service.MovieServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
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

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") final Integer id) {
        Movie movie = this.movieService.getMovieById(id);
        if(movie != null){
            return Response.ok(movie).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    public Response addMovie(final Movie movie) {
        Movie createdMovie = this.movieService.createMovie(movie);
        if(createdMovie != null){
            return Response.status(Response.Status.CREATED).entity(createdMovie).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateMovie(@PathParam("id") final Integer id, final Movie movie) {
        Movie foundMovie = this.movieService.updateMovie(id, movie);
        if (foundMovie != null){
            return Response.status(Response.Status.CREATED).entity(foundMovie).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMovie(@PathParam("id") final Integer id){
        this.movieService.deleteMovie(id);
        return Response.status(Response.Status.OK).build();
    }

}
