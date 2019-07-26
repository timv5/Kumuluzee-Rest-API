package com.management.service;

import com.management.entities.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    private Logger logger = Logger.getLogger(MovieServiceImpl.class.getName());

    @PersistenceContext(unitName = "management-jpa")
    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        logger.info("MovieServiceImpl initialized");
    }

    @Override
    public List<Movie> getAllMovies() {
        TypedQuery<Movie> query = this.entityManager.createNamedQuery("Movie.getAll", Movie.class);
        return query.getResultList();
    }
}
