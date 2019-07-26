package com.management.service;

import com.management.entities.Actor;

import java.util.List;

public interface ActorService {

    /**
     * Get all Actors
     * @return List of Actors
     */
    List<Actor> getAllActors();

    /**
     * Get Actor by Actor id
     * @return Actor
     */
    Actor getActorById(final Integer id);

    /**
     * Create new Actor
     * @param actor to be created
     * @return created Actor
     */
    Actor createActor(final Actor actor);

    /**
     * Delete Actor
     * @param id Actor id to be deleted
     */
    void deleteActor(final Integer id);

    /**
     * Update actor
     * @param actor
     * @return updated Actor
     */
    Actor updateActor(final Integer id, final Actor actor);

    /**
     * Find Actors by name
     * @param firstname
     * @return List of actors
     */
    List<Actor> searchActorsByName(final String firstname);

}
