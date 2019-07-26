package com.management.service;

import com.management.entities.Actor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
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

    public List<Actor> getAllActors() {
        TypedQuery<Actor> query = this.entityManager.createNamedQuery("Actor.getAll", Actor.class);
        return query.getResultList();
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
    public void deleteActor(final Integer id) {
        Actor actor = this.entityManager.find(Actor.class, id);
        if(actor != null){
            this.entityManager.remove(actor);
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
        TypedQuery<Actor> q = entityManager.createNamedQuery("Actor.getActorById", Actor.class);
        q.setParameter("id", id);
        try {
            return q.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
