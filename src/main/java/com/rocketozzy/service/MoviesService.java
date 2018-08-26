package com.rocketozzy.service;

import com.rocketozzy.model.Movies;
import com.rocketozzy.model.dto.MovieRequest;

public interface MoviesService {

    void saveMovie(MovieRequest movieRequest);

    Movies findByName(String movieName);
}
