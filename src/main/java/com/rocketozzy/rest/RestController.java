package com.rocketozzy.rest;

import com.rocketozzy.model.Actor;
import com.rocketozzy.model.Movies;
import com.rocketozzy.model.dto.ActorRequest;
import com.rocketozzy.model.dto.MovieRequest;
import com.rocketozzy.service.ActorService;
import com.rocketozzy.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    Logger logger = Logger.getLogger("logger");

    @Autowired
    ActorService actorService;

    @Autowired
    MoviesService moviesService;

    @RequestMapping("/saveActor")
    public ResponseEntity<String> saveActor(@RequestBody ActorRequest actorRequest, HttpServletResponse response) {

        actorService.saveActor(actorRequest);

        response.setContentType("application/x-amz-json-1.0");

        return ResponseEntity.ok("Done");
    }


    @RequestMapping(value="/findActorById/{actorId}", method = RequestMethod.GET)
    public ResponseEntity<Actor> findActorById(@PathVariable String actorId){

        return ResponseEntity.ok(actorService.findById(actorId));
    }

    @RequestMapping(value="/findActorsByMovie", method = RequestMethod.POST)
    public ResponseEntity<List<Actor>> findActorsByMovie(@RequestBody MovieRequest movieRequest){

        if( movieRequest.getName() != null ){

            List<Actor> actors = actorService.findActorsByMovie(movieRequest.getName());
            return ResponseEntity.ok(actors);
        }

        return ResponseEntity.ok(new ArrayList<>());
    }

    @RequestMapping("/saveMovie")
    public ResponseEntity<String> saveMovie(@RequestBody MovieRequest movieRequest, HttpServletResponse response) {

        moviesService.saveMovie(movieRequest);

        response.setContentType("application/x-amz-json-1.0");

        return ResponseEntity.ok("Done");
    }

    @RequestMapping(value = "/findMovieByName", method = RequestMethod.POST)
    public ResponseEntity<Movies> findMovieByName(@RequestBody MovieRequest movieRequest){

        Movies movies = moviesService.findByName(movieRequest.getName());

        return ResponseEntity.ok(movies);
    }

}
