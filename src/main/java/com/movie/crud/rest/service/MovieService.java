package com.movie.crud.rest.service;

import com.movie.crud.rest.domain.Movie;
import com.movie.crud.rest.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

import static com.movie.crud.rest.utils.Utils.ifNotNull;

@Service
public class MovieService {

    private static final String MOVIE_DELETED_MESSAGE = "Movie deleted!";
    private static final String INVALID_MOVIE_MESSAGE = "Invalid movie!";
    private MovieRepository movieRepository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> getMovies() {
        return (Collection<Movie>) movieRepository.findAll();
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findOne(movieId);
    }


    public Movie createMovie(Movie movie) {
        Movie returnedMovie = movieRepository.findByName(movie.getName());
        if (returnedMovie == null) {
            return movieRepository.save(movie);
        }
        return returnedMovie;
    }

    public String deleteMovie(Long movieId) {
        if (movieRepository.exists(movieId)) {
            movieRepository.delete(movieId);
            return MOVIE_DELETED_MESSAGE;
        }
        return INVALID_MOVIE_MESSAGE;
    }

    public Movie updateMovie(Long movieId, Movie movie) {
        Movie existingMovie = movieRepository.findOne(movieId);
        if (existingMovie == null) {
            return createMovie(movie);
        }
        return updateExistingMovie(movie, existingMovie);
    }

    private Movie updateExistingMovie(Movie movie, Movie existingMovie) {
        existingMovie.setName(ifNotNull(movie.getName(), existingMovie.getName()));
        existingMovie.setRating(ifNotNull(movie.getRating(), existingMovie.getRating()));
        existingMovie.setAvailability(ifNotNull(movie.getAvailability(), existingMovie.getAvailability()));
        existingMovie.setReview(ifNotNull(movie.getReview(), existingMovie.getReview()));
        existingMovie.setThumb(ifNotNull(movie.getThumb(), existingMovie.getThumb()));

        return movieRepository.save(existingMovie);
    }

}
