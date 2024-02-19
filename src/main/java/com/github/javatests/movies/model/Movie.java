package com.github.javatests.movies.model;

import java.util.Objects;

public class Movie {

  private Integer id;
  private String name;
  private Integer minutes;
  private Genre genre;
  private String director;

  public Movie() {
  }

  public Movie(Integer id, String name, Integer minutes, Genre genre, String director) {
    this.id = id;
    this.name = name;
    this.minutes = minutes;
    this.genre = genre;
    this.director = director;
  }

  public Movie(String name, Integer minutes, Genre genre, String director) {
    this.name = name;
    this.minutes = minutes;
    this.genre = genre;
    this.director = director;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMinutes() {
    return minutes;
  }

  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Movie movie = (Movie) object;
    return Objects.equals(minutes, movie.minutes) && Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && genre == movie.genre && Objects.equals(director, movie.director);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, minutes, genre, director);
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", minutes='" + minutes + '\'' +
        ", genre=" + genre + '\'' +
        ", director=" + director +
        '}';
  }
}
