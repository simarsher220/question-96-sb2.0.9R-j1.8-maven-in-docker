package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/movies/create")
    @ApiOperation("Add a new released movie to the booking platform")
    public ResponseEntity addMovie(@RequestBody Movie movieDto) throws GenericException {
        return new ResponseEntity<>(movieService.addMovie(movieDto), HttpStatus.CREATED);
    }

}
