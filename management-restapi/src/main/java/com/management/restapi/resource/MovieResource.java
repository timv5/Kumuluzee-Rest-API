package com.management.restapi.resource;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.management.entities.Movie;
import com.management.restapi.requestCounter.RequestCounter;
import com.management.service.MovieServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/movie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MovieResource {

    private RequestCounter requestCounter = new RequestCounter("C:\\Users\\tvalencic\\Documents\\personal\\jobs\\beenius\\beenius\\management-restapi\\src\\main\\resources\\counter.txt");

    @Context
    protected UriInfo uriInfo;

    @Inject
    private MovieServiceImpl movieService;

    @GET
    public Response getAllMovies(){
        QueryParameters query = QueryParameters.query(this.uriInfo.getRequestUri().getQuery()).build();
        List<Movie> movies = this.movieService.getAllMovies(query);
        Long moviesCount = this.movieService.getMovieCount(query);
        this.requestCounter.count();
        return Response.ok(movies).header("X-Total-Count", moviesCount).build();
    }

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") final Integer id) {
        Movie movie = this.movieService.getMovieById(id);
        this.requestCounter.count();
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
        this.requestCounter.count();
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
        this.requestCounter.count();
        if (foundMovie != null){
            return Response.status(Response.Status.CREATED).entity(foundMovie).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMovie(@PathParam("id") final Integer id){
        Movie deletedMovie = this.movieService.deleteMovie(id);
        this.requestCounter.count();
        if(deletedMovie != null){
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    public Response searchActorByName(@QueryParam("title") final String title){
        List<Movie> movies = this.movieService.searchMovieByTitle(title);
        this.requestCounter.count();
        if(!movies.isEmpty()){
            return Response.ok(movies).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
