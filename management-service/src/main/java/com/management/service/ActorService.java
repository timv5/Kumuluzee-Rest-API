package com.management.service;

import com.management.entities.Actor;

import java.util.List;

public interface ActorService {

    /**
     * Get all Actors
     * @return List of Actors
     */
    List<Actor> getAll();

}
