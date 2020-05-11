package org.codejudge.sb.service;

import org.codejudge.sb.dao.ShowRepository;
import org.codejudge.sb.dto.TheatreShowsDto;
import org.codejudge.sb.dto.TheatresShowsDto;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.entity.ShowMapping;
import org.codejudge.sb.entity.Theatre;
import org.codejudge.sb.error.exception.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepo;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheatreService theatreService;

    public ShowMapping addShow(ShowMapping showMapping) throws Exception {
        Movie movie = movieService.getMovieById(showMapping.getMovieId());
        Theatre theatre = theatreService.findTheatreById(showMapping.getTheatreId());
        List<ShowMapping> shows = showRepo.findByTheatreId(showMapping.getTheatreId());
        if (null == movie || null == theatre) {
            throw new GenericException("Invalid data!", HttpStatus.BAD_REQUEST);
        }
        if (shows.size() > 0) {
            for (ShowMapping show : shows) {
                if (validateForTimings(showMapping, movie, show)) continue;
            }
        }
        ShowMapping.validateForUpsertion(showMapping);
        showMapping = showRepo.saveAndFlush(showMapping);
        if (showMapping == null) {
            throw new GenericException("Couldn't insert show!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return showMapping;
    }

    private boolean validateForTimings(ShowMapping showMapping, Movie movie, ShowMapping show) throws GenericException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        if (!showMapping.getDate().toString().equals(show.getDate().toString())) {
            return true;
        }
        LocalTime time = showMapping.getTime().toLocalTime();
        LocalTime compTime = show.getTime().toLocalTime();
        if (MINUTES.between(time, compTime) == 0
                || (MINUTES.between(time, compTime) < 0 && Math.abs(MINUTES.between(time, compTime)) - movie.getLength() < 0)
                || (MINUTES.between(time, compTime) > 0 && Math.abs(MINUTES.between(time, compTime)) - movieService.getMovieById(show.getMovieId()).getLength() < 0)) {
            throw new GenericException("Timing not suitable!", HttpStatus.BAD_REQUEST);
        }
        return false;
    }

    private List<ShowMapping> getShowListByMovieAndTheatreAndDate(Integer movieId, Integer theatreId, Date date) throws GenericException {
        List<ShowMapping> shows = showRepo.findByMovieIdAndTheatreId(movieId, theatreId);
        if (CollectionUtils.isEmpty(shows)) {
            throw new GenericException("No shows found!", HttpStatus.NOT_FOUND);
        }
        return shows.stream().filter(show -> show.getDate().equals(date)).collect(Collectors.toList());
    }

    public TheatresShowsDto getShowsByMovieAndCityAndDate(Integer movieId, String city, Date date) throws GenericException {
        List<TheatreShowsDto> theatreShowsDtos = new ArrayList<>();
        Movie movie = movieService.getMovieById(movieId);
        List<Theatre> theatres = theatreService.findAllByCity(city);
        for (Theatre theatre : theatres) {
            theatreShowsDtos.add(new TheatreShowsDto.TheatreShowsDtoBuilder(theatre.getTheatreId(),
                    theatre.getTheatreName(),
                    theatre.getTheatreLocation(),
                    theatre.getCity(),
                    theatre.getPincode(),
                    getShowListByMovieAndTheatreAndDate(movieId, theatre.getTheatreId(), date)).build());
        }
        return new TheatresShowsDto.TheatresShowsDtoBuilder(movie, theatreShowsDtos).build();
    }
}
