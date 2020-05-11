package org.codejudge.sb.service;

import org.springframework.stereotype.Service;

@Service
public class CityService {

	public String[] getAllCities() {
		return new String[] {"Bengaluru", "Mumbai", "Lucknow", "Delhi"};
	}

}
