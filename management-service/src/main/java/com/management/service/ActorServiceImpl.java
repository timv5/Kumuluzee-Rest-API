package com.management.service;

import com.management.entities.Actor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ActorServiceImpl implements ActorService{

    private Logger logger = Logger.getLogger(ActorServiceImpl.class.getName());

    @PersistenceContext(unitName = "management-jpa")
    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        logger.info("ActorServiceImpl initialized");
    }

    public List<Actor> getAll() {
        TypedQuery<Actor> query = this.entityManager.createNamedQuery("Actor.getAll", Actor.class);
        return query.getResultList();
    }
}
