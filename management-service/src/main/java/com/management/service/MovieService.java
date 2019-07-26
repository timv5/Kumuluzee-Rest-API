package com.management.service;

import com.management.entities.Movie;

import java.util.List;

public interface MovieService {

    /**
     * Get all Movies
     * @return List of Movies
     */
    List<Movie> getAllMovies();

}
