package com.github.javatests.movies.data;

import com.github.javatests.movies.model.Genre;
import com.github.javatests.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

  private JdbcTemplate jdbcTemplate;

  public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Movie findById(Integer id) {
    System.out.println("Searching movie with ID: " + id);

    Object [] args = { id };

    return jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", args, movieMapper);
  }

  @Override
  public Collection<Movie> findAll() {
    return jdbcTemplate.query("SELECT * FROM movies", movieMapper);
  }

  @Override
  public void saveOrUpdate(Movie movie) {
    jdbcTemplate.update("INSERT INTO movies (name, minutes, genre, director) VALUES (?, ?, ?, ?)",
        movie.getName(),
        movie.getMinutes(),
        movie.getGenre().toString(),
        movie.getDirector());
  }

  @Override
  public Collection<Movie> findByName(String name) {
    Collection<Movie> allMovies = jdbcTemplate.query("select * from movies", movieMapper);

    Collection<Movie> result = new ArrayList<>();

    for (Movie movie : allMovies) {
      if (movie.getName().toLowerCase().contains(name.toLowerCase())) {
        result.add(movie);
      }
    }

    return result;
  }

  public Collection<Movie> findByDirector(String director) {
    Collection<Movie> allMovies = jdbcTemplate.query("select * from movies", movieMapper);

    Collection<Movie> result = new ArrayList<>();

    for (Movie movie : allMovies) {
      if(movie.getDirector().toLowerCase().contains(director.toLowerCase())) {
        result.add(movie);
      }
    }

    return result;
  }

  private static RowMapper<Movie> movieMapper = (rs, rowNum) ->
      new Movie(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getInt("minutes"),
          Genre.valueOf(rs.getString("genre")),
          rs.getString("director"));

}
