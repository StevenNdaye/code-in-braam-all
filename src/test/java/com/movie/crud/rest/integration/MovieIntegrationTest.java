package com.movie.crud.rest.integration;

import com.movie.crud.rest.AbstractIntegrationTest;
import com.movie.crud.rest.domain.Movie;
import com.movie.crud.rest.repository.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MovieIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private MovieRepository movieRepository;
    private String url;

    @Before
    public void setUp() throws Exception {
        url = resourceUrl("/");
    }

    @Test
    public void shouldGetMovies() throws Exception {
        Movie movie = new Movie("movieName", 4, 10L, "movieReview", "someThumb");
        movieRepository.save(movie);

        ResponseEntity<Movie[]> response = restTemplate.getForEntity(url + "movies", Movie[].class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        Collection<Movie> movies = asList(response.getBody());
        assertThat(movies.iterator().next().getName(), is(movie.getName()));
    }

    @Test
    public void shouldGetAMovie() throws Exception {
        Movie movie = new Movie("movieName", 4, 10L, "movieReview", "someThumb");

        Movie savedMovie = movieRepository.save(movie);

        ResponseEntity<Movie> response = restTemplate.getForEntity(url + "movies/{movieId}", Movie.class, savedMovie.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldCreateAMovie() throws Exception {
        Movie movie = new Movie("movieName", 4, 10L, "movieReview", "someThumb");
        ResponseEntity<Movie> createdMovie = restTemplate.postForEntity(url + "movies", movie, Movie.class);

        Movie retrievedMovie = movieRepository.findOne(createdMovie.getBody().getId());

        assertThat(createdMovie.getBody().getId(), is(retrievedMovie.getId()));

        assertThat(createdMovie.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldUpdateAMovie() throws Exception {
        Movie movie = new Movie("movieName", 4, 10L, "movieReview", "someThumb");
        Movie savedMovie = movieRepository.save(movie);
        savedMovie.setRating(1);
        restTemplate.put(url + "movies/{movieId}", savedMovie, savedMovie.getId());

        assertThat(savedMovie.getRating(), is(1));
    }

    @Test
    public void shouldDeleteAMovie() throws Exception {
        Movie movie = new Movie("movieName", 4, 10L, "movieReview", "someThumb");
        Movie savedMovie = movieRepository.save(movie);

        restTemplate.delete(url + "movies/{movieId}", savedMovie.getId());

        Movie retrievedMovie = movieRepository.findOne(savedMovie.getId());

        assertNull(retrievedMovie);
    }

    @After
    public void tearDown() throws Exception {
        movieRepository.deleteAll();
    }
}
