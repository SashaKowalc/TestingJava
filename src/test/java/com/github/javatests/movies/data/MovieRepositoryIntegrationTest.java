package com.github.javatests.movies.data;

import com.github.javatests.movies.model.Genre;
import com.github.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {


  private MovieRepositoryJdbc movieRepository;
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {

    dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "root", "");

    ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    movieRepository = new MovieRepositoryJdbc(jdbcTemplate);
  }

  @Test
  public void load_all_movies() throws SQLException {
    Collection<Movie> movies = movieRepository.findAll();

    assertThat(movies, CoreMatchers.is(Arrays.asList(
        new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan") ,
        new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan") ,
        new Movie(3, "Matrix", 136, Genre.ACTION, "Lana Wachowski"),
        new Movie(4, "SuperMan", 140, Genre.ACTION, "Richard Donner"),
        new Movie(5, "Super Mario Bros", 90, Genre.COMEDY, "Aaron Horvath")
    )));
  }

  @Test
  public void load_movie_by_id() {
    Movie movie = movieRepository.findById(2);

    assertThat(movie, CoreMatchers.is(new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan")));
  }

  @Test
  public void insert_a_movie() {
    Movie movie = new Movie("Super 8", 112, Genre.THRILLER, "J. J. Abrams");

    movieRepository.saveOrUpdate(movie);

    Movie movieFromDb = movieRepository.findById(6);

    assertThat(movieFromDb, CoreMatchers.is(new Movie(6, "Super 8", 112, Genre.THRILLER, "J. J. Abrams")));
  }

  @Test
  public void load_movie_by_name() {
    Collection<Movie> movies = movieRepository.findByName("Super");

    assertThat(movies, CoreMatchers.is(Arrays.asList(
        new Movie(4, "SuperMan", 140, Genre.ACTION, "Richard Donner"),
        new Movie(5, "Super Mario Bros", 90, Genre.COMEDY, "Aaron Horvath")
    )));
  }

  @Test
  public void load_movies_by_director() {
    Collection movies = movieRepository.findByDirector("Christopher");

    assertThat(movies, CoreMatchers.is(Arrays.asList(
        new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan") ,
        new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan")
    )));
  }

  @After
  public void tearDown() throws Exception {
    final Statement st = dataSource.getConnection().createStatement();
    st.execute("drop all objects delete files"); //"shotdown" is also enough for mem db
  }
}