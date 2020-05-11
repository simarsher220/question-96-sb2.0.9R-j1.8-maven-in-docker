package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.dto.TheatresShowsDto;
import org.codejudge.sb.entity.ShowMapping;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class ShowsController {

    @Autowired
    private ShowService showService;

    @PostMapping(value = "/shows/create")
    @ApiOperation("Add a show for a movie to the platform")
    public ResponseEntity addShow(@RequestBody ShowMapping show) throws Exception {
        return new ResponseEntity<>(showService.addShow(show), HttpStatus.CREATED);
    }

    @GetMapping(value = "/showsBy")
    @ApiOperation("Get all the shows for a particular movie by city and date")
    public TheatresShowsDto getShowsByMovieCityDate(@RequestParam("movie_id") Integer movieId, @RequestParam("city") String city, @RequestParam("date") Date date) throws GenericException {
        return showService.getShowsByMovieAndCityAndDate(movieId, city, date);
    }
}
