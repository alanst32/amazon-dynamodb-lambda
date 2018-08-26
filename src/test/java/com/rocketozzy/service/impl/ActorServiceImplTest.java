package com.rocketozzy.service.impl;

import com.rocketozzy.model.Actor;
import com.rocketozzy.repository.ActorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ActorServiceImpl.class, ActorRepository.class})
public class ActorServiceImplTest {

    @Autowired
    ActorServiceImpl actorService;


    /**
     *
     */
    @Test
    public void test_findActorsByMovie_movieNotFound(){

        List<Actor> actors = actorService.findActorsByMovie("The Heat");

        Assert.assertTrue(org.springframework.util.CollectionUtils.isEmpty(actors));


    }


            /*
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
             */
}
