package com.rocketozzy.service;

import com.rocketozzy.model.Actor;
import com.rocketozzy.model.dto.ActorRequest;

import java.util.List;

public interface ActorService {

    Actor findById(String id);

    List<Actor> findActorsByMovie(String movieName);

    void saveActor(ActorRequest request);
}
