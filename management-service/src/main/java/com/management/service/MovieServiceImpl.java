package com.management.service;

import com.management.entities.Actor;
import com.management.entities.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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

    @Transactional
    @Override
    public Movie createMovie(final Movie movie) {
        if(movie != null){
            try {
                this.entityManager.persist(movie);
                return movie;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void deleteMovie(final Integer id) {
        Movie movie = this.entityManager.find(Movie.class, id);
        if(movie != null){
            this.entityManager.remove(movie);
        }
    }

    @Transactional
    @Override
    public Movie updateMovie(final Integer id, final Movie movie) {
        Movie currentMovie = this.entityManager.find(Movie.class, id);
        if(currentMovie == null){
            return null;
        }else {
            currentMovie.setDescription(movie.getDescription());
            currentMovie.setReleaseYear(movie.getReleaseYear());
            currentMovie.setTitle(movie.getTitle());
            return this.entityManager.merge(currentMovie);
        }
    }

    @Override
    public Movie getMovieById(final Integer id) {
        TypedQuery<Movie> q = this.entityManager.createNamedQuery("Movie.getMovieById", Movie.class);
        q.setParameter("id", id);
        try {
            return q.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Movie> searchMovieByTitle(final String title) {
        TypedQuery<Movie> q = this.entityManager.createNamedQuery("Movie.searchMoviesByTitle", Movie.class);
        q.setParameter("title", title);
        try {
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
