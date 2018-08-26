package com.rocketozzy.repository;

import com.rocketozzy.model.Movies;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MoviesRepository extends CrudRepository<Movies, String> {

    Movies findByName(String name);
}
