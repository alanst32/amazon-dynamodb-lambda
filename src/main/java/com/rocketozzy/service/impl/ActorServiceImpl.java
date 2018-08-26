package com.rocketozzy.service.impl;

import com.amazonaws.services.dynamodbv2.document.*;
import com.rocketozzy.model.Actor;
import com.rocketozzy.model.Movies;
import com.rocketozzy.model.dto.ActorRequest;
import com.rocketozzy.repository.ActorRepository;
import com.rocketozzy.repository.MoviesRepository;
import com.rocketozzy.service.ActorService;
import com.rocketozzy.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    DynamoDB dynamoDB;

    @Override
    public Actor findById(String id){

        return actorRepository.findById(id);
    }

    @Override
    public List<Actor> findActorsByMovie(String movieName) {

        Movies movie = moviesRepository.findByName(movieName);

        if (movie != null) {

            Table table = dynamoDB.getTable("Actor");

            HashMap<String, String> attr = QueryUtils.createAttrHash("#mv", "movie");

            HashMap<String, Object> values = QueryUtils.createValueHash(":movieId", movie.getId());

            // CONTAINS is not supported by table.query()

            table.scan("contains(#mv, :movieId)", attr, values);

            ItemCollection<ScanOutcome> items = table.scan("contains(#mv, :movieId)", attr, values);

            List<Actor> actors = StreamSupport.stream(items.spliterator(), false)
                    .map( item -> convertToActor(item) )
                    .collect(Collectors.toList());

            return actors;
        }

        return null;
    }

    @Override
    public void saveActor(ActorRequest request){

        UUID uuid = UUID.randomUUID();

        Actor actor = new Actor();
        actor.setId(uuid.toString());
        actor.setFirstName(request.getFirstName());
        actor.setLastName(request.getLastName());
        actor.setDateBirth(request.getDateBirth());
        actor.setMovie(request.getMovies());

        actorRepository.save(actor);
    }

    private Actor convertToActor(Item item){

        Actor actor = new Actor();
        actor.setId(item.getString("id"));
        actor.setFirstName(item.getString("firstName"));
        actor.setLastName(item.getString("lastName"));
        actor.setDateBirth(item.getString("dateBirth"));
        actor.setMovie(item.getList("movie"));

        return actor;
    }

}


