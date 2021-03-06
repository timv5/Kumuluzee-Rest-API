package com.management.service;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.management.entities.Actor;
import com.management.entities.Movie;

import java.util.List;

public interface MovieService {

    /**
     * Get all Movies
     * @return List of Movies
     */
    List<Movie> getAllMovies(final QueryParameters query);

    /**
     * Get Movie by Movie id
     * @return Movie
     */
    Movie getMovieById(final Integer id);

    /**
     * Create new Movie
     * @param actor to be created
     * @return created Movie
     */
    Movie createMovie(final Movie actor);

    /**
     * Delete Movie
     * @param id Movie id to be deleted
     */
    Movie deleteMovie(final Integer id);

    /**
     * Update Movie
     * @param Movie
     * @return updated Movie
     */
    Movie updateMovie(final Integer id, final Movie movie);

    /**
     * Find Movie by title
     * @param title
     * @return List of Movies
     */
    List<Movie> searchMovieByTitle(final String title);

    /**
     * Number of returned entities
     * @param query QueryParameters for GET request
     * @return Number of enteties to be returned with passed query
     */
    Long getMovieCount(final QueryParameters query);

}
