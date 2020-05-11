package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
	
	@Autowired
    private CityService cityService;

    @GetMapping(value = "/cities/")
    @ApiOperation("Get all cities")
    public String[] getAllCities() throws GenericException {
        return cityService.getAllCities();
    }

}
