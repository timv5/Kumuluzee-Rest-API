package com.management.service;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.management.entities.Actor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class ActorServiceImpl implements ActorService{

    private Logger logger = Logger.getLogger(ActorServiceImpl.class.getName());

    @PersistenceContext(unitName = "management-jpa")
    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        logger.info("ActorServiceImpl initialized");
    }

    @Override
    public List<Actor> getAllActors(final QueryParameters query) {
        return JPAUtils.queryEntities(entityManager, Actor.class, query);
    }

    @Override
    public Long getActorCount(final QueryParameters query){
        return JPAUtils.queryEntitiesCount(entityManager, Actor.class, query);
    }

    @Transactional
    @Override
    public Actor createActor(final Actor actor) {
        if(actor != null){
            try {
                this.entityManager.persist(actor);
                return actor;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Actor deleteActor(final Integer id) {
        Actor actor = this.entityManager.find(Actor.class, id);
        if(actor != null){
            this.entityManager.remove(actor);
            return actor;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public Actor updateActor(final Integer id, final Actor actor) {
        Actor currentActor = this.entityManager.find(Actor.class, id);
        if(currentActor == null){
            return null;
        }else{
            currentActor.setDescription(actor.getDescription());
            currentActor.setBirthDate(actor.getBirthDate());
            currentActor.setFirstname(actor.getFirstname());
            currentActor.setLastname(actor.getLastname());
            return this.entityManager.merge(currentActor);
        }
    }

    @Override
    public Actor getActorById(final Integer id) {
        TypedQuery<Actor> q = this.entityManager.createNamedQuery("Actor.getActorById", Actor.class);
        q.setParameter("id", id);
        try {
            return q.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Actor> searchActorsByName(final String firstname) {
        TypedQuery<Actor> q = this.entityManager.createNamedQuery("Actor.searchActorsByName", Actor.class);
        q.setParameter("firstname", firstname);
        try {
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
