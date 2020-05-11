package org.codejudge.sb.service;

import org.codejudge.sb.dao.TheatreRepository;
import org.codejudge.sb.dto.TheatresDto;
import org.codejudge.sb.entity.Theatre;
import org.codejudge.sb.error.exception.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityService cityService;

    public TheatresDto getAllTheatresInCity(String city) throws GenericException {
        List<Theatre> theatres = theatreRepository.findAllByCity(city);
        if (CollectionUtils.isEmpty(theatres)) {
            throw new GenericException("Theatres not found!!", HttpStatus.NOT_FOUND);
        }
        return new TheatresDto.TheatresDtoBuilder(theatres).build();
    }

    public Theatre addTheatre(Theatre theatre) throws GenericException {
        Theatre.validateForUpsertion(theatre);
        validateCity(theatre);
        theatre = theatreRepository.saveAndFlush(theatre);
        if (theatre == null) {
            throw new GenericException("Couldn't add the theatre!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return theatre;
    }
    
    private void validateCity(Theatre theatre) throws GenericException {
		boolean validCity = false;
        String[] cities = cityService.getAllCities();
        if (cities != null && cities.length > 0) {
        	for (int i = 0; i < cities.length; i++) {
				String city = cities[i];
				if (theatre.getCity().equals(city)) {
					validCity = true;
					break;
				}
			}
        }
        if (!validCity) {
        	throw new GenericException("Invalid City!!", HttpStatus.BAD_REQUEST);
        }
	}

    public Theatre findTheatreById(Integer theatreId) throws GenericException {
        Theatre theatre = theatreRepository.findTheatreById(theatreId);
        if (theatre == null) {
            throw new GenericException("Theatre not found!", HttpStatus.NOT_FOUND);
        }
        return theatre;
    }

    public List<Theatre> findAllByCity(String city) throws GenericException {
        List<Theatre> theatres = theatreRepository.findAllByCity(city);
        if (CollectionUtils.isEmpty(theatres)) {
            throw new GenericException("Theatre not found!", HttpStatus.NOT_FOUND);
        }
        return theatres;
    }
}
