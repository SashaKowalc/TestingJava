package com.github.javatests.movies.data;

import com.github.javatests.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(Integer id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
    Collection<Movie> findByName(String name);

}
