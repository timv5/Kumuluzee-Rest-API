package com.management.service;

import com.management.entities.Movie;

import java.util.List;

public interface MovieService {

    /**
     * Get all Movies
     * @return List of Movies
     */
    List<Movie> getAllMovies();

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
    void deleteMovie(final Integer id);

    /**
     * Update Movie
     * @param Movie
     * @return updated Movie
     */
    Movie updateMovie(final Integer id, final Movie movie);

}
