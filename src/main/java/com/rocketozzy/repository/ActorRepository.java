package com.rocketozzy.repository;

import com.rocketozzy.model.Actor;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ActorRepository extends CrudRepository<Actor, String> {

    List<Actor> findActorsByMovie(String movieName);

    Actor findById(String id);
}
