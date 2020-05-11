package org.codejudge.sb.service;

import org.codejudge.sb.dao.MovieRepository;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.error.exception.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovieById(Integer movieId) throws GenericException {
        Movie movie = movieRepository.findMovieById(movieId);
        if (movie == null) {
            throw new GenericException("Movie not found!", HttpStatus.NOT_FOUND);
        }
        return movie;
    }

    public Movie addMovie(Movie movie) throws GenericException {
        Movie.validateForUpsertion(movie);
        try {
            movie = movieRepository.saveAndFlush(movie);
        }
        catch (Exception e) {
            throw new GenericException("Couldn't insert movie!", HttpStatus.BAD_REQUEST);
        }
        return movie;
    }
}
