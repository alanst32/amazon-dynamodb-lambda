package com.rocketozzy.service.impl;

import com.rocketozzy.model.Movies;
import com.rocketozzy.model.dto.MovieRequest;
import com.rocketozzy.repository.MoviesRepository;
import com.rocketozzy.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public void saveMovie(MovieRequest movieRequest){

        UUID uuid = UUID.randomUUID();

        Movies movies = new Movies();
        movies.setId(uuid.toString());
        movies.setName(movieRequest.getName());
        movies.setCountry(movieRequest.getCountry());
        movies.setDateReleased(movieRequest.getDateReleased());

        moviesRepository.save(movies);
    }

    @Override
    public Movies findByName(String movieName){

        return moviesRepository.findByName(movieName);
    }
}
