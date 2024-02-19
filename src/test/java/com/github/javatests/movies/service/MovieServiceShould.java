package com.github.javatests.movies.service;

import com.github.javatests.movies.data.MovieRepository;
import com.github.javatests.movies.model.Genre;
import com.github.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.javatests.movies.model.Genre.THRILLER;
import static org.junit.Assert.*;

public class MovieServiceShould {

  private MovieService movieService;

  @Before
  public void setUp() throws Exception {
    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

    Mockito.when(movieRepository.findAll()).thenReturn(
        Arrays.asList(
            new Movie(1,"Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
            new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan"),
            new Movie(3, "There's Something About Mary", 119, Genre.COMEDY, "Christopher Nolan"),
            new Movie(4, "Super 8", 112, Genre.THRILLER, "Christopher Nolan"),
            new Movie(5, "Scream", 111, Genre.ACTION, "Christopher Nolan"),
            new Movie(6, "Home Alone", 103, Genre.COMEDY, "Christopher Nolan"),
            new Movie(7, "Matrix", 136, Genre.ACTION, "Christopher Nolan")
        ));

    movieService = new MovieService(movieRepository);
  }

  @Test
  public void return_movies_by_genre() {
    Collection<Movie> movies = movieService.findMovieByGenre(Genre.COMEDY);
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(3,6)));
  }

  @Test
  public void return_movies_by_length() {
    Collection<Movie> movies = movieService.findMovieByLength(119);
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2,3,4,5,6)));
  }

  /*@Test
  public void return_movies_when_using_id() {
    Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(5, null, null, null, null));
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(5)));
  }*/

  /*@Test
  public void return_movies_when_using_negative_minutes() {
    assertThrows(
        IllegalArgumentException.class,
        () -> movieService.findMoviesByTemplate(new Movie(null, null, -15, Genre.ACTION, null))
    );
  }*/

  @Test
  public void return_movies_when_using_genre_and_minutes() {
    Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, null, 180, Genre.ACTION, null));
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(1,5,7)));
  }

  @Test
  public void return_movies_when_using_name_and_minutes() {
    Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, "Matrix", 136, null, null));
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(7)));
  }

  @Test
  public void return_movies_when_using_director_and_minutes() {
    Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, null, 110, null, "Chris"));
    assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(6)));
  }

  private static List<Integer> getMovieIds(Collection<Movie> movies) {
    return movies.stream().map(Movie::getId).collect(Collectors.toList());
  }
}