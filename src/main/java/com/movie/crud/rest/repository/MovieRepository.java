package com.movie.crud.rest.repository;

import com.movie.crud.rest.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findByName(String name);
}
